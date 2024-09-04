import 'webpack-dev-server';

import StatoscopeWebpackPlugin from '@statoscope/webpack-plugin';
// @ts-ignore
import PreloadWebpackPlugin from '@vue/preload-webpack-plugin';
// @ts-ignore
import WebpackBeforeBuildPlugin from 'before-build-webpack';
import CopyWebpackPlugin from 'copy-webpack-plugin';
import dotenv from 'dotenv';
import fs from 'fs';
import { GitRevisionPlugin } from 'git-revision-webpack-plugin';
import HtmlPlugin from 'html-webpack-plugin';
import yaml from 'js-yaml';
import MiniCssExtractPlugin from 'mini-css-extract-plugin';
import path from 'path';
import type { Compiler, Configuration } from 'webpack';
import {
  DefinePlugin, EnvironmentPlugin, NormalModuleReplacementPlugin, ProvidePlugin,
} from 'webpack';

import { PRODUCTION_URL } from './src/config';

dotenv.config();

// GitHub workflow uses an empty string as the default value if it's not in repository variables, so we cannot define a default value here
process.env.BASE_URL = process.env.BASE_URL || PRODUCTION_URL;

const {
  APP_ENV = 'production',
  BASE_URL,
  HEAD,
} = process.env;
const IS_CAPACITOR = process.env.IS_CAPACITOR === '1';
const IS_EXTENSION = process.env.IS_EXTENSION === '1';
const IS_PACKAGED_ELECTRON = process.env.IS_PACKAGED_ELECTRON === '1';
const IS_FIREFOX_EXTENSION = process.env.IS_FIREFOX_EXTENSION === '1';
const IS_OPERA_EXTENSION = process.env.IS_OPERA_EXTENSION === '1';

const gitRevisionPlugin = new GitRevisionPlugin();
const branch = HEAD || gitRevisionPlugin.branch();
const appRevision = !branch || branch === 'HEAD' ? gitRevisionPlugin.commithash()?.substring(0, 7) : branch;
const canUseStatoscope = !IS_EXTENSION && !IS_PACKAGED_ELECTRON && !IS_CAPACITOR;
const cspConnectSrcExtra = APP_ENV === 'development'
  ? `http://localhost:3000 ${process.env.CSP_CONNECT_SRC_EXTRA_URL}`
  : '';
const cspFrameSrcExtra = [
  'https://widget.changelly.com/',
  'https://dreamwalkers.io/',
  'https://avanchange.com/',
  'https://pay.wata.pro/',
  'https://royalpay.cc/',
].join(' ');

// The `connect-src` rule contains `https:` due to arbitrary requests are needed for jetton JSON configs.
// The `img-src` rule contains `https:` due to arbitrary image URLs being used as jetton logos.
// The `media-src` rule contains `data:` because of iOS sound initialization.
const CSP = `
  default-src 'none';
  manifest-src 'self';
  connect-src 'self' https: ${cspConnectSrcExtra};
  script-src 'self' 'wasm-unsafe-eval';
  style-src 'self' https://fonts.googleapis.com/;
  img-src 'self' data: https:;
  media-src 'self' data:;
  object-src 'none';
  base-uri 'none';
  font-src 'self' https://fonts.gstatic.com/;
  form-action 'none';
  frame-src 'self' ${cspFrameSrcExtra};`
  .replace(/\s+/g, ' ').trim();

const appVersion = require('./package.json').version;

const defaultI18nFilename = path.resolve(__dirname, './src/i18n/en.json');

export default function createConfig(
  _: any,
  { mode = 'production' }: { mode: 'none' | 'development' | 'production' },
): Configuration {
  return {
    mode,
    target: 'web',

    optimization: {
      minimize: false,
      minimizer: [],

      usedExports: true,
      ...(APP_ENV === 'staging' && {
        chunkIds: 'named',
      }),
      ...(IS_EXTENSION && {
        minimize: false,
      }),
      ...(IS_CAPACITOR && {
        splitChunks: false,
      }),
    },

    entry: {
      api: './src/api/index.ts',
    },

    devServer: {
      port: 4321,
      host: '0.0.0.0',
      allowedHosts: 'all',
      hot: false,
      static: [
        {
          directory: path.resolve(__dirname, 'public'),
        },
        {
          directory: path.resolve(__dirname, 'src/lib/rlottie'),
        },
      ],
      devMiddleware: {
        stats: 'minimal',
      },
      headers: {
        'Content-Security-Policy': CSP,
      },
    },

    watchOptions: { ignored: defaultI18nFilename },

    output: {
      filename: 'bundle.js', // Имя итогового файла
      path: path.resolve(__dirname, 'dist'), // Директория, куда будет помещён бандл
      library: {
        name: 'api',
        type: 'umd', // Универсальный модуль для поддержки различных окружений
      },
      globalObject: 'this', // Обеспечивает доступ к глобальному объекту
      chunkFilename: '[name].js',
      assetModuleFilename: '[name].[contenthash][ext]',
      clean: true,
    },

    module: {
      rules: [
        {
          test: /\.(ts|tsx|js)$/,
          loader: 'babel-loader',
          exclude: /node_modules/,
        }
      ],
    },

    resolve: {
      extensions: ['.js', '.ts', '.tsx'],
      fallback: {
        crypto: false,
      },
      alias: {
        // It is used to remove duplicate dependencies
        'bn.js': path.join(__dirname, 'node_modules/bn.js/lib/bn.js'),
      },
    },

    plugins: [
      ...(IS_OPERA_EXTENSION ? [{
        apply: (compiler: Compiler) => {
          compiler.hooks.afterDone.tap('After Compilation', async () => {
            const dir = './dist/';

            for (const filename of await fs.promises.readdir(dir)) {
              const file = path.join(dir, filename);

              if (file.endsWith('.tgs')) {
                await fs.promises.rename(file, file.replace('.tgs', '.json'));
              } else if (filename.includes('main') && filename.endsWith('.js')) {
                const content = (await fs.promises.readFile(file))
                  .toString('utf-8')
                  .replace(/\.tgs"/g, '.json"');
                await fs.promises.writeFile(file, content);
              }
            }
          });
        },
      }] : []),
      /* eslint-disable no-null/no-null */
      new EnvironmentPlugin({
        APP_ENV: 'production',
        APP_NAME: null,
        APP_VERSION: appVersion,
        APP_REVISION: appRevision,
        TEST_SESSION: null,
        TONHTTPAPI_MAINNET_URL: null,
        TONHTTPAPI_MAINNET_API_KEY: null,
        TONHTTPAPI_TESTNET_URL: null,
        TONHTTPAPI_TESTNET_API_KEY: null,
        TONAPIIO_MAINNET_URL: null,
        TONAPIIO_TESTNET_URL: null,
        TONHTTPAPI_V3_MAINNET_API_KEY: null,
        TONHTTPAPI_V3_TESTNET_API_KEY: null,
        BRILLIANT_API_BASE_URL: null,
        PROXY_HOSTS: null,
        STAKING_POOLS: null,
        LIQUID_POOL: null,
        LIQUID_JETTON: null,
        IS_PACKAGED_ELECTRON: false,
        IS_ANDROID_DIRECT: false,
        ELECTRON_TONHTTPAPI_MAINNET_API_KEY: null,
        ELECTRON_TONHTTPAPI_TESTNET_API_KEY: null,
        BASE_URL,
        BOT_USERNAME: null,
        IS_EXTENSION: false,
        IS_FIREFOX_EXTENSION: false,
        IS_CAPACITOR: false,
        SWAP_FEE_ADDRESS: null,
        DIESEL_ADDRESS: null,
      }),
      /* eslint-enable no-null/no-null */
      new DefinePlugin({
        APP_REVISION: DefinePlugin.runtimeValue(
          () => {
            const { gitBranch, commit } = getGitMetadata();
            return JSON.stringify(!gitBranch || gitBranch === 'HEAD' ? commit : gitBranch);
          },
          mode === 'development' ? true : [],
        ),
      }),
      new ProvidePlugin({
        Buffer: ['buffer', 'Buffer'],
      }),
      new CopyWebpackPlugin({
        patterns: [
          {
            from: 'src/extension/manifest.json',
            transform: (content) => {
              const manifest = JSON.parse(content.toString());
              manifest.version = appVersion;
              manifest.content_security_policy = {
                extension_pages: CSP,
              };

              if (IS_FIREFOX_EXTENSION) {
                manifest.background = {
                  scripts: [manifest.background.service_worker],
                };
                manifest.host_permissions = ['<all_urls>'];
                manifest.permissions = manifest.permissions.filter((value: string) => value !== 'system.display');
                manifest.browser_specific_settings = {
                  gecko: {
                    id: '{98fcdaee-2b58-4f71-8a3c-f0c66f24dede}',
                    strict_min_version: '91.1.0', // Minimum version for using a proxy
                  },
                };
              }

              return JSON.stringify(manifest, undefined, 2);
            },
          },
          {
            from: 'src/_headers',
            transform: (content: Buffer) => content.toString().replace('{{CSP}}', CSP),
          },
        ],
      }),
      ...(canUseStatoscope ? [new StatoscopeWebpackPlugin({
        statsOptions: {
          context: __dirname,
        },
        saveReportTo: path.resolve('./public/statoscope-report.html'),
        saveStatsTo: path.resolve('./public/statoscope-build-statistics.json'),
        normalizeStats: true,
        open: false,
        extensions: [new WebpackContextExtension()], // eslint-disable-line @typescript-eslint/no-use-before-define
      })] : []),
      ...(IS_EXTENSION
        ? [
          new NormalModuleReplacementPlugin(
            /src\/api\/providers\/worker\/connector\.ts/,
            '../extension/connectorForPopup.ts',
          ),
        ]
        : []),
    ],

    devtool:
      IS_EXTENSION ? 'cheap-source-map' : APP_ENV === 'production' && IS_PACKAGED_ELECTRON ? undefined : 'source-map',
  };
}

function getGitMetadata() {
  const revisionPlugin = new GitRevisionPlugin();
  const commit = revisionPlugin.commithash()?.substring(0, 7);

  return {
    gitBranch: HEAD || gitRevisionPlugin.branch(),
    commit,
  };
}

class WebpackContextExtension {
  context: string;

  constructor() {
    this.context = '';
  }

  handleCompiler(compiler: Compiler) {
    this.context = compiler.context;
  }

  getExtension() {
    return {
      descriptor: { name: 'custom-webpack-extension-context', version: '1.0.0' },
      payload: { context: this.context },
    };
  }
}
