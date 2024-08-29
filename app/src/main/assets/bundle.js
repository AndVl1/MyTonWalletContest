(function webpackUniversalModuleDefinition(root, factory) {
	if(typeof exports === 'object' && typeof module === 'object')
		module.exports = factory();
	else if(typeof define === 'function' && define.amd)
		define([], factory);
	else if(typeof exports === 'object')
		exports["api"] = factory();
	else
		root["api"] = factory();
})(this, () => {
return /******/ (() => { // webpackBootstrap
/******/ 	"use strict";
/******/ 	var __webpack_modules__ = ({

/***/ "./src/api/providers/worker/connector.ts":
/*!***********************************************!*\
  !*** ./src/api/providers/worker/connector.ts ***!
  \***********************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   callApi: () => (/* binding */ callApi),
/* harmony export */   initApi: () => (/* binding */ initApi)
/* harmony export */ });
/* unused harmony export callApiWithThrow */
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../../config */ "./src/config.ts");
/* harmony import */ var _util_capacitorStorageProxy__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../../util/capacitorStorageProxy */ "./src/util/capacitorStorageProxy/index.ts");
/* harmony import */ var _util_logs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../../util/logs */ "./src/util/logs.ts");
/* harmony import */ var _util_PostMessageConnector__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../../util/PostMessageConnector */ "./src/util/PostMessageConnector.ts");
/* harmony import */ var _util_schedulers__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../../util/schedulers */ "./src/util/schedulers.ts");
/* harmony import */ var _util_windowEnvironment__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../../../util/windowEnvironment */ "./src/util/windowEnvironment.ts");






const HEALTH_CHECK_TIMEOUT = 150;
const HEALTH_CHECK_MIN_DELAY = 5000; // 5 sec

let updateCallback;
let worker;
let connector;
let isInitialized = false;
function initApi(onUpdate, initArgs) {
  updateCallback = onUpdate;
  if (!connector) {
    worker = new Worker( /* webpackChunkName: "worker" */new URL(/* worker import */ __webpack_require__.p + __webpack_require__.u("worker"), __webpack_require__.b));
    connector = (0,_util_PostMessageConnector__WEBPACK_IMPORTED_MODULE_3__.createConnector)(worker, onUpdate);
    if (_config__WEBPACK_IMPORTED_MODULE_0__.IS_CAPACITOR) {
      (0,_util_capacitorStorageProxy__WEBPACK_IMPORTED_MODULE_1__.createWindowProvider)(worker);
    }
  }
  if (!isInitialized) {
    if (_util_windowEnvironment__WEBPACK_IMPORTED_MODULE_5__.IS_IOS) {
      setupIosHealthCheck();
    }
    isInitialized = true;
  }
  const args = typeof initArgs === 'function' ? initArgs() : initArgs;
  return connector.init(args);
}
async function callApi(fnName) {
  if (!connector) {
    (0,_util_logs__WEBPACK_IMPORTED_MODULE_2__.logDebugError)('API is not initialized');
    return undefined;
  }
  try {
    for (var _len = arguments.length, args = new Array(_len > 1 ? _len - 1 : 0), _key = 1; _key < _len; _key++) {
      args[_key - 1] = arguments[_key];
    }
    return await connector.request({
      name: fnName,
      args
    });
  } catch (err) {
    return undefined;
  }
}
function callApiWithThrow(fnName) {
  for (var _len2 = arguments.length, args = new Array(_len2 > 1 ? _len2 - 1 : 0), _key2 = 1; _key2 < _len2; _key2++) {
    args[_key2 - 1] = arguments[_key2];
  }
  return connector.request({
    name: fnName,
    args
  });
}
const startedAt = Date.now();

// Workaround for iOS sometimes stops interacting with worker
function setupIosHealthCheck() {
  window.addEventListener('focus', () => {
    void ensureWorkerPing();
    // Sometimes a single check is not enough
    setTimeout(() => ensureWorkerPing(), 1000);
  });
}
async function ensureWorkerPing() {
  let isResolved = false;
  try {
    await Promise.race([callApiWithThrow('ping'), (0,_util_schedulers__WEBPACK_IMPORTED_MODULE_4__.pause)(HEALTH_CHECK_TIMEOUT).then(() => isResolved ? undefined : Promise.reject(new Error('HEALTH_CHECK_TIMEOUT')))]);
  } catch (err) {
    // eslint-disable-next-line no-console
    console.error(err);
    if (Date.now() - startedAt >= HEALTH_CHECK_MIN_DELAY) {
      var _worker;
      (_worker = worker) === null || _worker === void 0 ? void 0 : _worker.terminate();
      worker = undefined;
      connector = undefined;
      updateCallback({
        type: 'requestReconnectApi'
      });
    }
  } finally {
    isResolved = true;
  }
}

/***/ }),

/***/ "./src/config.ts":
/*!***********************!*\
  !*** ./src/config.ts ***!
  \***********************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   APP_ENV: () => (/* binding */ APP_ENV),
/* harmony export */   DEBUG: () => (/* binding */ DEBUG),
/* harmony export */   DEBUG_ALERT_MSG: () => (/* binding */ DEBUG_ALERT_MSG),
/* harmony export */   DEBUG_MORE: () => (/* binding */ DEBUG_MORE),
/* harmony export */   DEFAULT_DECIMAL_PLACES: () => (/* binding */ DEFAULT_DECIMAL_PLACES),
/* harmony export */   IS_CAPACITOR: () => (/* binding */ IS_CAPACITOR),
/* harmony export */   IS_EXTENSION: () => (/* binding */ IS_EXTENSION),
/* harmony export */   IS_FIREFOX_EXTENSION: () => (/* binding */ IS_FIREFOX_EXTENSION),
/* harmony export */   LANG_LIST: () => (/* binding */ LANG_LIST),
/* harmony export */   ONE_TON: () => (/* binding */ ONE_TON),
/* harmony export */   WINDOW_PROVIDER_CHANNEL: () => (/* binding */ WINDOW_PROVIDER_CHANNEL)
/* harmony export */ });
/* unused harmony exports APP_NAME, APP_VERSION, APP_ENV_MARKER, IS_PRODUCTION, IS_TEST, IS_PERF, IS_PACKAGED_ELECTRON, IS_ANDROID_DIRECT, ELECTRON_HOST_URL, INACTIVE_MARKER, PRODUCTION_URL, BETA_URL, APP_REPO_URL, BASE_URL, BOT_USERNAME, SWAP_FEE_ADDRESS, STRICTERDOM_ENABLED, PIN_LENGTH, NATIVE_BIOMETRICS_USERNAME, NATIVE_BIOMETRICS_SERVER, MNEMONIC_COUNT, PRIVATE_KEY_HEX_LENGTH, MNEMONIC_CHECK_COUNT, MOBILE_SCREEN_MAX_WIDTH, ANIMATION_END_DELAY, ANIMATED_STICKER_TINY_SIZE_PX, ANIMATED_STICKER_SMALL_SIZE_PX, ANIMATED_STICKER_MIDDLE_SIZE_PX, ANIMATED_STICKER_DEFAULT_PX, ANIMATED_STICKER_BIG_SIZE_PX, ANIMATED_STICKER_HUGE_SIZE_PX, TON_SYMBOL, DEFAULT_LANDSCAPE_ACTION_TAB_ID, WHOLE_PART_DELIMITER, DEFAULT_SLIPPAGE_VALUE, GLOBAL_STATE_CACHE_DISABLED, GLOBAL_STATE_CACHE_KEY, ANIMATION_LEVEL_MIN, ANIMATION_LEVEL_MED, ANIMATION_LEVEL_MAX, ANIMATION_LEVEL_DEFAULT, THEME_DEFAULT, MAIN_ACCOUNT_ID, TONHTTPAPI_MAINNET_URL, TONHTTPAPI_MAINNET_API_KEY, ELECTRON_TONHTTPAPI_MAINNET_API_KEY, TONHTTPAPI_V3_MAINNET_API_URL, TONAPIIO_MAINNET_URL, TONHTTPAPI_TESTNET_URL, TONHTTPAPI_TESTNET_API_KEY, ELECTRON_TONHTTPAPI_TESTNET_API_KEY, TONHTTPAPI_V3_TESTNET_API_URL, TONAPIIO_TESTNET_URL, BRILLIANT_API_BASE_URL, FRACTION_DIGITS, SHORT_FRACTION_DIGITS, SUPPORT_USERNAME, MY_TON_WALLET_PROMO_URL, TELEGRAM_WEB_URL, TON_EXPLORER_BASE_MAINNET_URL, TON_EXPLORER_BASE_TESTNET_URL, TON_EXPLORER_NAME, TOKEN_EXPLORER_MAINNET_URL, TOKEN_EXPLORER_TESTNET_URL, TOKEN_EXPLORER_NAME, GETGEMS_BASE_MAINNET_URL, GETGEMS_BASE_TESTNET_URL, EMPTY_HASH_VALUE, CHANGELLY_SUPPORT_EMAIL, CHANGELLY_LIVE_CHAT_URL, CHANGELLY_SECURITY_EMAIL, CHANGELLY_TERMS_OF_USE, CHANGELLY_PRIVACY_POLICY, CHANGELLY_AML_KYC, CHANGELLY_WAITING_DEADLINE, TONCOIN_SLUG, DEFAULT_SWAP_SECOND_TOKEN_SLUG, DEFAULT_CEX_SWAP_SECOND_TOKEN_SLUG, PROXY_HOSTS, TINY_TRANSFER_MAX_COST, LANG_CACHE_NAME, STAKING_CYCLE_DURATION_MS, VALIDATION_PERIOD_MS, MIN_BALANCE_FOR_UNSTAKE, STAKING_FORWARD_AMOUNT, DEFAULT_FEE, STAKING_POOLS, LIQUID_POOL, LIQUID_JETTON, STAKING_MIN_AMOUNT, NOMINATORS_STAKING_MIN_AMOUNT, TONCONNECT_PROTOCOL_VERSION, TONCONNECT_WALLET_JSBRIDGE_KEY, NFT_FRAGMENT_COLLECTIONS, TON_DNS_COLLECTION, MYCOIN_TOKEN, MYCOIN_SLUG, MYCOIN_TOKEN_TESTNET, MYCOIN_SLUG_TESTNET, TOKEN_INFO, TON_BLOCKCHAIN, INIT_SWAP_ASSETS, MULTITAB_DATA_CHANNEL_NAME, ACTIVE_TAB_STORAGE_KEY, INDEXED_DB_NAME, INDEXED_DB_STORE_NAME, MIN_ASSETS_TAB_VIEW, DEFAULT_PRICE_CURRENCY, SHORT_CURRENCY_SYMBOL_MAP, CURRENCY_LIST, BURN_ADDRESS, DEFAULT_WALLET_VERSION, POPULAR_WALLET_VERSIONS, DEFAULT_TIMEOUT, DEFAULT_RETRIES, DEFAULT_ERROR_PAUSE, HISTORY_PERIODS, BROWSER_HISTORY_LIMIT, NFT_BATCH_SIZE, NOTCOIN_VOUCHERS_ADDRESS, BURN_CHUNK_DURATION_APPROX_SEC, NOTCOIN_FORWARD_TON_AMOUNT, NOTCOIN_EXCHANGERS, CLAIM_ADDRESS, CLAIM_AMOUNT, CLAIM_COMMENT, RE_LINK_TEMPLATE, RE_TG_BOT_MENTION, DIESEL_ADDRESS, DIESEL_TOKENS */
const APP_ENV = "development";
const APP_NAME =  false || 'MyTonWallet';
const APP_VERSION = "3.0.10";
const APP_ENV_MARKER = APP_ENV === 'staging' ? 'Beta' : APP_ENV === 'development' ? 'Dev' : undefined;
const DEBUG = APP_ENV !== 'production' && APP_ENV !== 'perf' && APP_ENV !== 'test';
const DEBUG_MORE = false;
const IS_PRODUCTION = APP_ENV === 'production';
const IS_TEST = APP_ENV === 'test';
const IS_PERF = APP_ENV === 'perf';
const IS_EXTENSION = false === '1';
const IS_FIREFOX_EXTENSION = false === '1';
const IS_PACKAGED_ELECTRON = false === '1';
const IS_CAPACITOR = false === '1';
const IS_ANDROID_DIRECT = false === '1';
const ELECTRON_HOST_URL = 'https://dumb-host';
const INACTIVE_MARKER = '[Inactive]';
const PRODUCTION_URL = 'https://mytonwallet.app';
const BETA_URL = 'https://beta.mytonwallet.app';
const APP_REPO_URL = 'https://github.com/mytonwalletorg/mytonwallet';
const BASE_URL = "https://mytonwallet.app";
const BOT_USERNAME =  false || 'MyTonWalletBot';
const SWAP_FEE_ADDRESS =  false || 'UQDUkQbpTVIgt7v66-JTFR-3-eXRFz_4V66F-Ufn6vOg0GOp';
const STRICTERDOM_ENABLED = DEBUG && !IS_PACKAGED_ELECTRON;
const DEBUG_ALERT_MSG = 'Shoot!\nSomething went wrong, please see the error details in Dev Tools Console.';
const PIN_LENGTH = 4;
const NATIVE_BIOMETRICS_USERNAME = 'MyTonWallet';
const NATIVE_BIOMETRICS_SERVER = 'https://mytonwallet.app';
const MNEMONIC_COUNT = 24;
const PRIVATE_KEY_HEX_LENGTH = 64;
const MNEMONIC_CHECK_COUNT = 3;
const MOBILE_SCREEN_MAX_WIDTH = 700; // px

const ANIMATION_END_DELAY = 50;
const ANIMATED_STICKER_TINY_SIZE_PX = 70;
const ANIMATED_STICKER_SMALL_SIZE_PX = 110;
const ANIMATED_STICKER_MIDDLE_SIZE_PX = 120;
const ANIMATED_STICKER_DEFAULT_PX = 150;
const ANIMATED_STICKER_BIG_SIZE_PX = 156;
const ANIMATED_STICKER_HUGE_SIZE_PX = 192;
const TON_SYMBOL = 'TON';
const DEFAULT_LANDSCAPE_ACTION_TAB_ID = 0;
const DEFAULT_DECIMAL_PLACES = 9;
const WHOLE_PART_DELIMITER = ' '; // https://www.compart.com/en/unicode/U+202F

const DEFAULT_SLIPPAGE_VALUE = 5;
const GLOBAL_STATE_CACHE_DISABLED = false;
const GLOBAL_STATE_CACHE_KEY = 'mytonwallet-global-state';
const ANIMATION_LEVEL_MIN = 0;
const ANIMATION_LEVEL_MED = 1;
const ANIMATION_LEVEL_MAX = 2;
const ANIMATION_LEVEL_DEFAULT = ANIMATION_LEVEL_MAX;
const THEME_DEFAULT = 'system';
const MAIN_ACCOUNT_ID = '0-ton-mainnet';
const TONHTTPAPI_MAINNET_URL =  false || 'https://tonhttpapi.mytonwallet.org/api/v2/jsonRPC';
const TONHTTPAPI_MAINNET_API_KEY = null;
const ELECTRON_TONHTTPAPI_MAINNET_API_KEY = null;
const TONHTTPAPI_V3_MAINNET_API_URL =  false || 'https://tonhttpapi-v3.mytonwallet.org/api/v3';
const TONAPIIO_MAINNET_URL =  false || 'https://tonapiio.mytonwallet.org';
const TONHTTPAPI_TESTNET_URL =  false || 'https://tonhttpapi-testnet.mytonwallet.org/api/v2/jsonRPC';
const TONHTTPAPI_TESTNET_API_KEY = null;
const ELECTRON_TONHTTPAPI_TESTNET_API_KEY = null;
const TONHTTPAPI_V3_TESTNET_API_URL =  false || 'https://tonhttpapi-v3-testnet.mytonwallet.org/api/v3';
const TONAPIIO_TESTNET_URL =  false || 'https://tonapiio-testnet.mytonwallet.org';
const BRILLIANT_API_BASE_URL =  false || 'https://api.mytonwallet.org';
const FRACTION_DIGITS = 9;
const SHORT_FRACTION_DIGITS = 2;
const SUPPORT_USERNAME = 'MyTonWalletSupport';
const MY_TON_WALLET_PROMO_URL = 'https://mytonwallet.io';
const TELEGRAM_WEB_URL = 'https://web.telegram.org/a/';
const TON_EXPLORER_BASE_MAINNET_URL = 'https://tonviewer.com/';
const TON_EXPLORER_BASE_TESTNET_URL = 'https://testnet.tonviewer.com/';
const TON_EXPLORER_NAME = 'Tonviewer';
const TOKEN_EXPLORER_MAINNET_URL = 'https://tonviewer.com/{address}?section=jetton';
const TOKEN_EXPLORER_TESTNET_URL = 'https://testnet.tonviewer.com/{address}?section=jetton';
const TOKEN_EXPLORER_NAME = 'TonViewer';
const GETGEMS_BASE_MAINNET_URL = 'https://getgems.io/';
const GETGEMS_BASE_TESTNET_URL = 'https://testnet.getgems.io/';
const EMPTY_HASH_VALUE = 'NOHASH';
const CHANGELLY_SUPPORT_EMAIL = 'support@changelly.com';
const CHANGELLY_LIVE_CHAT_URL = 'https://changelly.com/';
const CHANGELLY_SECURITY_EMAIL = 'security@changelly.com';
const CHANGELLY_TERMS_OF_USE = 'https://changelly.com/terms-of-use';
const CHANGELLY_PRIVACY_POLICY = 'https://changelly.com/privacy-policy';
const CHANGELLY_AML_KYC = 'https://changelly.com/aml-kyc';
const CHANGELLY_WAITING_DEADLINE = 3 * 60 * 60 * 1000; // 3 hour

const TONCOIN_SLUG = 'toncoin';
const DEFAULT_SWAP_SECOND_TOKEN_SLUG = 'ton-eqcxe6mutq'; // USD₮
const DEFAULT_CEX_SWAP_SECOND_TOKEN_SLUG = 'usdtrx';
const PROXY_HOSTS = null;
const TINY_TRANSFER_MAX_COST = 0.01;
const LANG_CACHE_NAME = 'mtw-lang-125';
const LANG_LIST = [{
  langCode: 'en',
  name: 'English',
  nativeName: 'English',
  rtl: false
}, {
  langCode: 'es',
  name: 'Spanish',
  nativeName: 'Español',
  rtl: false
}, {
  langCode: 'ru',
  name: 'Russian',
  nativeName: 'Русский',
  rtl: false
}, {
  langCode: 'zh-Hans',
  name: 'Chinese (Simplified)',
  nativeName: '简体',
  rtl: false
}, {
  langCode: 'zh-Hant',
  name: 'Chinese (Traditional)',
  nativeName: '繁體',
  rtl: false
}, {
  langCode: 'tr',
  name: 'Turkish',
  nativeName: 'Türkçe',
  rtl: false
}, {
  langCode: 'de',
  name: 'German',
  nativeName: 'Deutsch',
  rtl: false
}, {
  langCode: 'th',
  name: 'Thai',
  nativeName: 'ไทย',
  rtl: false
}, {
  langCode: 'uk',
  name: 'Ukrainian',
  nativeName: 'Українська',
  rtl: false
}, {
  langCode: 'pl',
  name: 'Polish',
  nativeName: 'Polski',
  rtl: false
}];
const STAKING_CYCLE_DURATION_MS = 131072000; // 36.4 hours
const VALIDATION_PERIOD_MS = 65536000; // 18.2 h.
const ONE_TON = 1000000000n;
const MIN_BALANCE_FOR_UNSTAKE = 1020000000n; // 1.02 TON
const STAKING_FORWARD_AMOUNT = ONE_TON;
const DEFAULT_FEE = 15000000n; // 0.015 TON

const STAKING_POOLS =  false ? 0 : [];
const LIQUID_POOL =  false || 'EQD2_4d91M4TVbEBVyBF8J1UwpMJc361LKVCz6bBlffMW05o';
const LIQUID_JETTON =  false || 'EQCqC6EhRJ_tpWngKxL6dV0k6DSnRUrs9GSVkLbfdCqsj6TE';
const STAKING_MIN_AMOUNT = ONE_TON;
const NOMINATORS_STAKING_MIN_AMOUNT = ONE_TON * 10001n;
const TONCONNECT_PROTOCOL_VERSION = 2;
const TONCONNECT_WALLET_JSBRIDGE_KEY = 'mytonwallet';
const NFT_FRAGMENT_COLLECTIONS = new Set(['0:0e41dc1dc3c9067ed24248580e12b3359818d83dee0304fabcf80845eafafdb2',
// Anonymous Telegram Numbers
'0:80d78a35f955a14b679faa887ff4cd5bfc0f43b4a4eea2a7e6927f3701b273c2' // Telegram Usernames
]);

const TON_DNS_COLLECTION = 'EQC3dNlesgVD8YbAazcauIrXBPfiVhMMr5YYk2in0Mtsz0Bz';
const MYCOIN_TOKEN = 'EQCFVNlRb-NHHDQfv3Q9xvDXBLJlay855_xREsq5ZDX6KN-w';
const MYCOIN_SLUG = 'ton-eqcfvnlrbn';
const MYCOIN_TOKEN_TESTNET = 'kQAWlxpEbwhCDFX9gp824ee2xVBhAh5VRSGWfbNFDddAbQoQ';
const MYCOIN_SLUG_TESTNET = 'ton-kqawlxpebw';
const TOKEN_INFO = {
  toncoin: {
    name: 'Toncoin',
    symbol: TON_SYMBOL,
    slug: TONCOIN_SLUG,
    cmcSlug: TONCOIN_SLUG,
    quote: {
      slug: TONCOIN_SLUG,
      price: 1.95,
      priceUsd: 1.95,
      percentChange24h: 0
    },
    decimals: DEFAULT_DECIMAL_PLACES
  }
};
const TON_BLOCKCHAIN = 'ton';
const INIT_SWAP_ASSETS = {
  toncoin: {
    name: 'Toncoin',
    symbol: TON_SYMBOL,
    blockchain: TON_BLOCKCHAIN,
    slug: TONCOIN_SLUG,
    decimals: DEFAULT_DECIMAL_PLACES,
    price: 0,
    priceUsd: 0,
    isPopular: true
  },
  'ton-eqcxe6mutq': {
    name: 'Tether USD',
    symbol: 'USD₮',
    blockchain: TON_BLOCKCHAIN,
    slug: 'ton-eqcxe6mutq',
    decimals: 9,
    // eslint-disable-next-line max-len
    image: 'https://cache.tonapi.io/imgproxy/T3PB4s7oprNVaJkwqbGg54nexKE0zzKhcrPv8jcWYzU/rs:fill:200:200:1/g:no/aHR0cHM6Ly90ZXRoZXIudG8vaW1hZ2VzL2xvZ29DaXJjbGUucG5n.webp',
    contract: 'EQCxE6mUtQJKFnGfaROTKOt1lZbDiiX1kCixRv7Nw2Id_sDs',
    price: 0,
    priceUsd: 0,
    isPopular: true
  }
};
const MULTITAB_DATA_CHANNEL_NAME = 'mtw-multitab';
const ACTIVE_TAB_STORAGE_KEY = 'mtw-active-tab';
const INDEXED_DB_NAME = 'keyval-store';
const INDEXED_DB_STORE_NAME = 'keyval';
const WINDOW_PROVIDER_CHANNEL = 'windowProvider';
const MIN_ASSETS_TAB_VIEW = 5;
const DEFAULT_PRICE_CURRENCY = 'USD';
const SHORT_CURRENCY_SYMBOL_MAP = {
  USD: '$',
  EUR: '€',
  RUB: '₽',
  CNY: '¥'
};
const CURRENCY_LIST = [{
  value: 'USD',
  name: 'US Dollar'
}, {
  value: 'EUR',
  name: 'Euro'
}, {
  value: 'RUB',
  name: 'Ruble'
}, {
  value: 'CNY',
  name: 'Yuan'
}, {
  value: 'BTC',
  name: 'Bitcoin'
}, {
  value: TON_SYMBOL,
  name: 'Toncoin'
}];
const BURN_ADDRESS = 'UQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAJKZ';
const DEFAULT_WALLET_VERSION = 'W5';
const POPULAR_WALLET_VERSIONS = ['v3R1', 'v3R2', 'v4R2', 'W5'];
const DEFAULT_TIMEOUT = 10000;
const DEFAULT_RETRIES = 3;
const DEFAULT_ERROR_PAUSE = 500;
const HISTORY_PERIODS = ['1D', '7D', '1M', '3M', '1Y', 'ALL'];
const BROWSER_HISTORY_LIMIT = 10;
const NFT_BATCH_SIZE = 4;
const NOTCOIN_VOUCHERS_ADDRESS = 'EQDmkj65Ab_m0aZaW8IpKw4kYqIgITw_HRstYEkVQ6NIYCyW';
const BURN_CHUNK_DURATION_APPROX_SEC = 30;
const NOTCOIN_FORWARD_TON_AMOUNT = 30000000n; // 0.03 TON
const NOTCOIN_EXCHANGERS = ['EQAPZauWVPUcm2hUJT9n36pxznEhl46rEn1bzBXN0RY_yiy2', 'EQASgm0Qv3h2H2mF0W06ikPqYq2ctT3dyXMJH_svbEKKB3iZ', 'EQArlmP-RhVIG2yAFGZyPZfM3m0YccxmpvoRi6sgRzWnAA0s', 'EQA6pL-spYqZp1Ck6o3rpY45Cl-bvLMW_j3qdVejOkUWpLnm', 'EQBJ_ehYjumQKbXfWUue1KHKXdTm1GuYJB0Fj2ST_DwORvpd', 'EQBRmYSjxh9xlZpUqEmGjF5UjukI9v_Cm2kCTu4CoBn3XkOD', 'EQBkiqncd7AFT5_23H-RoA2Vynk-Nzq_dLoeMVRthAU9RF0p', 'EQB_OzTHXbztABe0QHgr4PtAV8T64LR6aDunXgaAoihOdxwO', 'EQCL-x5kLg6tKVNGryItTuj6tG3FH5mhUEu0xRqQc-kbEmbe', 'EQCZh2yJ46RaQH3AYmjEA8SMMXi77Oein4-3lvqkHseIAhD-', 'EQChKo5IK3iNqUHUGDB9gtzjCjMTPtmsFqekuCA2MdreVEyu', 'EQC6DNCBv076TIliRMfOt20RpbS7rNKDfSky3WrFEapFt8AH', 'EQDE_XFZOYae_rl3ZMsgBCtRSmYhl8B4y2BZEP7oiGBDhlgy', 'EQDddqpGA2ePXQF47A2DSL3GF6ZzIVmimfM2d16cdymy2noT', 'EQDv0hNNAamhYltCh3pTJrq3oRB9RW2ZhEYkTP6fhj5BtZNu', 'EQD2mP7zgO7-imUJhqYry3i07aJ_SR53DaokMupfAAobt0Xw'];
const CLAIM_ADDRESS = 'EQB3zOTvPi1PmwdcTpqSfFKZnhi1GNKEVJM-LdoAirdLtash';
const CLAIM_AMOUNT = 30000000n; // 0.03 TON
const CLAIM_COMMENT = 'claim';

// eslint-disable-next-line max-len
const RE_LINK_TEMPLATE = /((ftp|https?):\/\/)?(?<host>(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\.[a-zA-Z][-a-zA-Z0-9]{1,62})\b([-a-zA-Z0-9()@:%_+.,~#?&/=]*)/g;
// eslint-disable-next-line max-len
const RE_TG_BOT_MENTION = /telegram[:\s-]*((@[a-z0-9_]+)|(https:\/\/)?(t\.me|telegram\.me|telegram\.dog)\/[a-z0-9_]+)/mig;
const DIESEL_ADDRESS =  false || 'EQDUkQbpTVIgt7v66-JTFR-3-eXRFz_4V66F-Ufn6vOg0D5s';
const DIESEL_TOKENS = new Set(['ton-eqcxe6mutq',
// USDT
'ton-eqavlwfdxg',
// Notcoin
'ton-eqcvxjy4eg' // DOGS
]);

/***/ }),

/***/ "./src/lib/big.js/index.js":
/*!*********************************!*\
  !*** ./src/lib/big.js/index.js ***!
  \*********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   Big: () => (/* binding */ Big)
/* harmony export */ });
/*
 *  big.js v6.2.1
 *  A small, fast, easy-to-use library for arbitrary-precision decimal arithmetic.
 *  Copyright (c) 2022 Michael Mclaughlin
 *  https://github.com/MikeMcl/big.js/LICENCE.md
 */

/************************************** EDITABLE DEFAULTS *****************************************/

// The default values below must be integers within the stated ranges.

/*
 * The maximum number of decimal places (DP) of the results of operations involving division:
 * div and sqrt, and pow with negative exponents.
 */
var DP = 20,
  // 0 to MAX_DP

  /*
   * The rounding mode (RM) used when rounding to the above decimal places.
   *
   *  0  Towards zero (i.e. truncate, no rounding).       (ROUND_DOWN)
   *  1  To nearest neighbour. If equidistant, round up.  (ROUND_HALF_UP)
   *  2  To nearest neighbour. If equidistant, to even.   (ROUND_HALF_EVEN)
   *  3  Away from zero.                                  (ROUND_UP)
   */
  RM = 1,
  // 0, 1, 2 or 3

  // The maximum value of DP and Big.DP.
  MAX_DP = 1E6,
  // 0 to 1000000

  // The maximum magnitude of the exponent argument to the pow method.
  MAX_POWER = 1E6,
  // 1 to 1000000

  /*
   * The negative exponent (NE) at and beneath which toString returns exponential notation.
   * (JavaScript numbers: -7)
   * -1000000 is the minimum recommended exponent value of a Big.
   */
  NE = -7,
  // 0 to -1000000

  /*
   * The positive exponent (PE) at and above which toString returns exponential notation.
   * (JavaScript numbers: 21)
   * 1000000 is the maximum recommended exponent value of a Big, but this limit is not enforced.
   */
  PE = 21,
  // 0 to 1000000

  /*
   * When true, an error will be thrown if a primitive number is passed to the Big constructor,
   * or if valueOf is called, or if toNumber is called on a Big which cannot be converted to a
   * primitive number without a loss of precision.
   */
  STRICT = false,
  // true or false

  /**************************************************************************************************/

  // Error messages.
  NAME = '[big.js] ',
  INVALID = NAME + 'Invalid ',
  INVALID_DP = INVALID + 'decimal places',
  INVALID_RM = INVALID + 'rounding mode',
  DIV_BY_ZERO = NAME + 'Division by zero',
  // The shared prototype object.
  P = {},
  UNDEFINED = void 0,
  NUMERIC = /^-?(\d+(\.\d*)?|\.\d+)(e[+-]?\d+)?$/i;

/*
 * Create and return a Big constructor.
 */
function _Big_() {
  /*
   * The Big constructor and exported function.
   * Create and return a new instance of a Big number object.
   *
   * n {number|string|Big} A numeric value.
   */
  function Big(n) {
    var x = this;

    // Enable constructor usage without new.
    if (!(x instanceof Big)) return n === UNDEFINED ? _Big_() : new Big(n);

    // Duplicate.
    if (n instanceof Big) {
      x.s = n.s;
      x.e = n.e;
      x.c = n.c.slice();
    } else {
      if (typeof n !== 'string') {
        if (Big.strict === true && typeof n !== 'bigint') {
          throw TypeError(INVALID + 'value');
        }

        // Minus zero?
        n = n === 0 && 1 / n < 0 ? '-0' : String(n);
      }
      parse(x, n);
    }

    // Retain a reference to this Big constructor.
    // Shadow Big.prototype.constructor which points to Object.
    x.constructor = Big;
  }
  Big.prototype = P;
  Big.DP = DP;
  Big.RM = RM;
  Big.NE = NE;
  Big.PE = PE;
  Big.strict = STRICT;
  Big.roundDown = 0;
  Big.roundHalfUp = 1;
  Big.roundHalfEven = 2;
  Big.roundUp = 3;
  return Big;
}

/*
 * Parse the number or string value passed to a Big constructor.
 *
 * x {Big} A Big number instance.
 * n {number|string} A numeric value.
 */
function parse(x, n) {
  var e, i, nl;
  if (!NUMERIC.test(n)) {
    throw Error(INVALID + 'number');
  }

  // Determine sign.
  x.s = n.charAt(0) == '-' ? (n = n.slice(1), -1) : 1;

  // Decimal point?
  if ((e = n.indexOf('.')) > -1) n = n.replace('.', '');

  // Exponential form?
  if ((i = n.search(/e/i)) > 0) {
    // Determine exponent.
    if (e < 0) e = i;
    e += +n.slice(i + 1);
    n = n.substring(0, i);
  } else if (e < 0) {
    // Integer.
    e = n.length;
  }
  nl = n.length;

  // Determine leading zeros.
  for (i = 0; i < nl && n.charAt(i) == '0';) ++i;
  if (i == nl) {
    // Zero.
    x.c = [x.e = 0];
  } else {
    // Determine trailing zeros.
    for (; nl > 0 && n.charAt(--nl) == '0';);
    x.e = e - i - 1;
    x.c = [];

    // Convert string to array of digits without leading/trailing zeros.
    for (e = 0; i <= nl;) x.c[e++] = +n.charAt(i++);
  }
  return x;
}

/*
 * Round Big x to a maximum of sd significant digits using rounding mode rm.
 *
 * x {Big} The Big to round.
 * sd {number} Significant digits: integer, 0 to MAX_DP inclusive.
 * rm {number} Rounding mode: 0 (down), 1 (half-up), 2 (half-even) or 3 (up).
 * [more] {boolean} Whether the result of division was truncated.
 */
function round(x, sd, rm, more) {
  var xc = x.c;
  if (rm === UNDEFINED) rm = x.constructor.RM;
  if (rm !== 0 && rm !== 1 && rm !== 2 && rm !== 3) {
    throw Error(INVALID_RM);
  }
  if (sd < 1) {
    more = rm === 3 && (more || !!xc[0]) || sd === 0 && (rm === 1 && xc[0] >= 5 || rm === 2 && (xc[0] > 5 || xc[0] === 5 && (more || xc[1] !== UNDEFINED)));
    xc.length = 1;
    if (more) {
      // 1, 0.1, 0.01, 0.001, 0.0001 etc.
      x.e = x.e - sd + 1;
      xc[0] = 1;
    } else {
      // Zero.
      xc[0] = x.e = 0;
    }
  } else if (sd < xc.length) {
    // xc[sd] is the digit after the digit that may be rounded up.
    more = rm === 1 && xc[sd] >= 5 || rm === 2 && (xc[sd] > 5 || xc[sd] === 5 && (more || xc[sd + 1] !== UNDEFINED || xc[sd - 1] & 1)) || rm === 3 && (more || !!xc[0]);

    // Remove any digits after the required precision.
    xc.length = sd;

    // Round up?
    if (more) {
      // Rounding up may mean the previous digit has to be rounded up.
      for (; ++xc[--sd] > 9;) {
        xc[sd] = 0;
        if (sd === 0) {
          ++x.e;
          xc.unshift(1);
          break;
        }
      }
    }

    // Remove trailing zeros.
    for (sd = xc.length; !xc[--sd];) xc.pop();
  }
  return x;
}

/*
 * Return a string representing the value of Big x in normal or exponential notation.
 * Handles P.toExponential, P.toFixed, P.toJSON, P.toPrecision, P.toString and P.valueOf.
 */
function stringify(x, doExponential, isNonzero) {
  var e = x.e,
    s = x.c.join(''),
    n = s.length;

  // Exponential notation?
  if (doExponential) {
    s = s.charAt(0) + (n > 1 ? '.' + s.slice(1) : '') + (e < 0 ? 'e' : 'e+') + e;

    // Normal notation.
  } else if (e < 0) {
    for (; ++e;) s = '0' + s;
    s = '0.' + s;
  } else if (e > 0) {
    if (++e > n) {
      for (e -= n; e--;) s += '0';
    } else if (e < n) {
      s = s.slice(0, e) + '.' + s.slice(e);
    }
  } else if (n > 1) {
    s = s.charAt(0) + '.' + s.slice(1);
  }
  return x.s < 0 && isNonzero ? '-' + s : s;
}

// Prototype/instance methods

/*
 * Return a new Big whose value is the absolute value of this Big.
 */
P.abs = function () {
  var x = new this.constructor(this);
  x.s = 1;
  return x;
};

/*
 * Return 1 if the value of this Big is greater than the value of Big y,
 *       -1 if the value of this Big is less than the value of Big y, or
 *        0 if they have the same value.
 */
P.cmp = function (y) {
  var isneg,
    x = this,
    xc = x.c,
    yc = (y = new x.constructor(y)).c,
    i = x.s,
    j = y.s,
    k = x.e,
    l = y.e;

  // Either zero?
  if (!xc[0] || !yc[0]) return !xc[0] ? !yc[0] ? 0 : -j : i;

  // Signs differ?
  if (i != j) return i;
  isneg = i < 0;

  // Compare exponents.
  if (k != l) return k > l ^ isneg ? 1 : -1;
  j = (k = xc.length) < (l = yc.length) ? k : l;

  // Compare digit by digit.
  for (i = -1; ++i < j;) {
    if (xc[i] != yc[i]) return xc[i] > yc[i] ^ isneg ? 1 : -1;
  }

  // Compare lengths.
  return k == l ? 0 : k > l ^ isneg ? 1 : -1;
};

/*
 * Return a new Big whose value is the value of this Big divided by the value of Big y, rounded,
 * if necessary, to a maximum of Big.DP decimal places using rounding mode Big.RM.
 */
P.div = function (y) {
  var x = this,
    Big = x.constructor,
    a = x.c,
    // dividend
    b = (y = new Big(y)).c,
    // divisor
    k = x.s == y.s ? 1 : -1,
    dp = Big.DP;
  if (dp !== ~~dp || dp < 0 || dp > MAX_DP) {
    throw Error(INVALID_DP);
  }

  // Divisor is zero?
  if (!b[0]) {
    throw Error(DIV_BY_ZERO);
  }

  // Dividend is 0? Return +-0.
  if (!a[0]) {
    y.s = k;
    y.c = [y.e = 0];
    return y;
  }
  var bl,
    bt,
    n,
    cmp,
    ri,
    bz = b.slice(),
    ai = bl = b.length,
    al = a.length,
    r = a.slice(0, bl),
    // remainder
    rl = r.length,
    q = y,
    // quotient
    qc = q.c = [],
    qi = 0,
    p = dp + (q.e = x.e - y.e) + 1; // precision of the result

  q.s = k;
  k = p < 0 ? 0 : p;

  // Create version of divisor with leading zero.
  bz.unshift(0);

  // Add zeros to make remainder as long as divisor.
  for (; rl++ < bl;) r.push(0);
  do {
    // n is how many times the divisor goes into current remainder.
    for (n = 0; n < 10; n++) {
      // Compare divisor and remainder.
      if (bl != (rl = r.length)) {
        cmp = bl > rl ? 1 : -1;
      } else {
        for (ri = -1, cmp = 0; ++ri < bl;) {
          if (b[ri] != r[ri]) {
            cmp = b[ri] > r[ri] ? 1 : -1;
            break;
          }
        }
      }

      // If divisor < remainder, subtract divisor from remainder.
      if (cmp < 0) {
        // Remainder can't be more than 1 digit longer than divisor.
        // Equalise lengths using divisor with extra leading zero?
        for (bt = rl == bl ? b : bz; rl;) {
          if (r[--rl] < bt[rl]) {
            ri = rl;
            for (; ri && !r[--ri];) r[ri] = 9;
            --r[ri];
            r[rl] += 10;
          }
          r[rl] -= bt[rl];
        }
        for (; !r[0];) r.shift();
      } else {
        break;
      }
    }

    // Add the digit n to the result array.
    qc[qi++] = cmp ? n : ++n;

    // Update the remainder.
    if (r[0] && cmp) r[rl] = a[ai] || 0;else r = [a[ai]];
  } while ((ai++ < al || r[0] !== UNDEFINED) && k--);

  // Leading zero? Do not remove if result is simply zero (qi == 1).
  if (!qc[0] && qi != 1) {
    // There can't be more than one zero.
    qc.shift();
    q.e--;
    p--;
  }

  // Round?
  if (qi > p) round(q, p, Big.RM, r[0] !== UNDEFINED);
  return q;
};

/*
 * Return true if the value of this Big is equal to the value of Big y, otherwise return false.
 */
P.eq = function (y) {
  return this.cmp(y) === 0;
};

/*
 * Return true if the value of this Big is greater than the value of Big y, otherwise return
 * false.
 */
P.gt = function (y) {
  return this.cmp(y) > 0;
};

/*
 * Return true if the value of this Big is greater than or equal to the value of Big y, otherwise
 * return false.
 */
P.gte = function (y) {
  return this.cmp(y) > -1;
};

/*
 * Return true if the value of this Big is less than the value of Big y, otherwise return false.
 */
P.lt = function (y) {
  return this.cmp(y) < 0;
};

/*
 * Return true if the value of this Big is less than or equal to the value of Big y, otherwise
 * return false.
 */
P.lte = function (y) {
  return this.cmp(y) < 1;
};

/*
 * Return a new Big whose value is the value of this Big minus the value of Big y.
 */
P.minus = P.sub = function (y) {
  var i,
    j,
    t,
    xlty,
    x = this,
    Big = x.constructor,
    a = x.s,
    b = (y = new Big(y)).s;

  // Signs differ?
  if (a != b) {
    y.s = -b;
    return x.plus(y);
  }
  var xc = x.c.slice(),
    xe = x.e,
    yc = y.c,
    ye = y.e;

  // Either zero?
  if (!xc[0] || !yc[0]) {
    if (yc[0]) {
      y.s = -b;
    } else if (xc[0]) {
      y = new Big(x);
    } else {
      y.s = 1;
    }
    return y;
  }

  // Determine which is the bigger number. Prepend zeros to equalise exponents.
  if (a = xe - ye) {
    if (xlty = a < 0) {
      a = -a;
      t = xc;
    } else {
      ye = xe;
      t = yc;
    }
    t.reverse();
    for (b = a; b--;) t.push(0);
    t.reverse();
  } else {
    // Exponents equal. Check digit by digit.
    j = ((xlty = xc.length < yc.length) ? xc : yc).length;
    for (a = b = 0; b < j; b++) {
      if (xc[b] != yc[b]) {
        xlty = xc[b] < yc[b];
        break;
      }
    }
  }

  // x < y? Point xc to the array of the bigger number.
  if (xlty) {
    t = xc;
    xc = yc;
    yc = t;
    y.s = -y.s;
  }

  /*
   * Append zeros to xc if shorter. No need to add zeros to yc if shorter as subtraction only
   * needs to start at yc.length.
   */
  if ((b = (j = yc.length) - (i = xc.length)) > 0) for (; b--;) xc[i++] = 0;

  // Subtract yc from xc.
  for (b = i; j > a;) {
    if (xc[--j] < yc[j]) {
      for (i = j; i && !xc[--i];) xc[i] = 9;
      --xc[i];
      xc[j] += 10;
    }
    xc[j] -= yc[j];
  }

  // Remove trailing zeros.
  for (; xc[--b] === 0;) xc.pop();

  // Remove leading zeros and adjust exponent accordingly.
  for (; xc[0] === 0;) {
    xc.shift();
    --ye;
  }
  if (!xc[0]) {
    // n - n = +0
    y.s = 1;

    // Result must be zero.
    xc = [ye = 0];
  }
  y.c = xc;
  y.e = ye;
  return y;
};

/*
 * Return a new Big whose value is the value of this Big modulo the value of Big y.
 */
P.mod = function (y) {
  var ygtx,
    x = this,
    Big = x.constructor,
    a = x.s,
    b = (y = new Big(y)).s;
  if (!y.c[0]) {
    throw Error(DIV_BY_ZERO);
  }
  x.s = y.s = 1;
  ygtx = y.cmp(x) == 1;
  x.s = a;
  y.s = b;
  if (ygtx) return new Big(x);
  a = Big.DP;
  b = Big.RM;
  Big.DP = Big.RM = 0;
  x = x.div(y);
  Big.DP = a;
  Big.RM = b;
  return this.minus(x.times(y));
};

/*
 * Return a new Big whose value is the value of this Big negated.
 */
P.neg = function () {
  var x = new this.constructor(this);
  x.s = -x.s;
  return x;
};

/*
 * Return a new Big whose value is the value of this Big plus the value of Big y.
 */
P.plus = P.add = function (y) {
  var e,
    k,
    t,
    x = this,
    Big = x.constructor;
  y = new Big(y);

  // Signs differ?
  if (x.s != y.s) {
    y.s = -y.s;
    return x.minus(y);
  }
  var xe = x.e,
    xc = x.c,
    ye = y.e,
    yc = y.c;

  // Either zero?
  if (!xc[0] || !yc[0]) {
    if (!yc[0]) {
      if (xc[0]) {
        y = new Big(x);
      } else {
        y.s = x.s;
      }
    }
    return y;
  }
  xc = xc.slice();

  // Prepend zeros to equalise exponents.
  // Note: reverse faster than unshifts.
  if (e = xe - ye) {
    if (e > 0) {
      ye = xe;
      t = yc;
    } else {
      e = -e;
      t = xc;
    }
    t.reverse();
    for (; e--;) t.push(0);
    t.reverse();
  }

  // Point xc to the longer array.
  if (xc.length - yc.length < 0) {
    t = yc;
    yc = xc;
    xc = t;
  }
  e = yc.length;

  // Only start adding at yc.length - 1 as the further digits of xc can be left as they are.
  for (k = 0; e; xc[e] %= 10) k = (xc[--e] = xc[e] + yc[e] + k) / 10 | 0;

  // No need to check for zero, as +x + +y != 0 && -x + -y != 0

  if (k) {
    xc.unshift(k);
    ++ye;
  }

  // Remove trailing zeros.
  for (e = xc.length; xc[--e] === 0;) xc.pop();
  y.c = xc;
  y.e = ye;
  return y;
};

/*
 * Return a Big whose value is the value of this Big raised to the power n.
 * If n is negative, round to a maximum of Big.DP decimal places using rounding
 * mode Big.RM.
 *
 * n {number} Integer, -MAX_POWER to MAX_POWER inclusive.
 */
P.pow = function (n) {
  var x = this,
    one = new x.constructor('1'),
    y = one,
    isneg = n < 0;
  if (n !== ~~n || n < -MAX_POWER || n > MAX_POWER) {
    throw Error(INVALID + 'exponent');
  }
  if (isneg) n = -n;
  for (;;) {
    if (n & 1) y = y.times(x);
    n >>= 1;
    if (!n) break;
    x = x.times(x);
  }
  return isneg ? one.div(y) : y;
};

/*
 * Return a new Big whose value is the value of this Big rounded to a maximum precision of sd
 * significant digits using rounding mode rm, or Big.RM if rm is not specified.
 *
 * sd {number} Significant digits: integer, 1 to MAX_DP inclusive.
 * rm? {number} Rounding mode: 0 (down), 1 (half-up), 2 (half-even) or 3 (up).
 */
P.prec = function (sd, rm) {
  if (sd !== ~~sd || sd < 1 || sd > MAX_DP) {
    throw Error(INVALID + 'precision');
  }
  return round(new this.constructor(this), sd, rm);
};

/*
 * Return a new Big whose value is the value of this Big rounded to a maximum of dp decimal places
 * using rounding mode rm, or Big.RM if rm is not specified.
 * If dp is negative, round to an integer which is a multiple of 10**-dp.
 * If dp is not specified, round to 0 decimal places.
 *
 * dp? {number} Integer, -MAX_DP to MAX_DP inclusive.
 * rm? {number} Rounding mode: 0 (down), 1 (half-up), 2 (half-even) or 3 (up).
 */
P.round = function (dp, rm) {
  if (dp === UNDEFINED) dp = 0;else if (dp !== ~~dp || dp < -MAX_DP || dp > MAX_DP) {
    throw Error(INVALID_DP);
  }
  return round(new this.constructor(this), dp + this.e + 1, rm);
};

/*
 * Return a new Big whose value is the square root of the value of this Big, rounded, if
 * necessary, to a maximum of Big.DP decimal places using rounding mode Big.RM.
 */
P.sqrt = function () {
  var r,
    c,
    t,
    x = this,
    Big = x.constructor,
    s = x.s,
    e = x.e,
    half = new Big('0.5');

  // Zero?
  if (!x.c[0]) return new Big(x);

  // Negative?
  if (s < 0) {
    throw Error(NAME + 'No square root');
  }

  // Estimate.
  s = Math.sqrt(x + '');

  // Math.sqrt underflow/overflow?
  // Re-estimate: pass x coefficient to Math.sqrt as integer, then adjust the result exponent.
  if (s === 0 || s === 1 / 0) {
    c = x.c.join('');
    if (!(c.length + e & 1)) c += '0';
    s = Math.sqrt(c);
    e = ((e + 1) / 2 | 0) - (e < 0 || e & 1);
    r = new Big((s == 1 / 0 ? '5e' : (s = s.toExponential()).slice(0, s.indexOf('e') + 1)) + e);
  } else {
    r = new Big(s + '');
  }
  e = r.e + (Big.DP += 4);

  // Newton-Raphson iteration.
  do {
    t = r;
    r = half.times(t.plus(x.div(t)));
  } while (t.c.slice(0, e).join('') !== r.c.slice(0, e).join(''));
  return round(r, (Big.DP -= 4) + r.e + 1, Big.RM);
};

/*
 * Return a new Big whose value is the value of this Big times the value of Big y.
 */
P.times = P.mul = function (y) {
  var c,
    x = this,
    Big = x.constructor,
    xc = x.c,
    yc = (y = new Big(y)).c,
    a = xc.length,
    b = yc.length,
    i = x.e,
    j = y.e;

  // Determine sign of result.
  y.s = x.s == y.s ? 1 : -1;

  // Return signed 0 if either 0.
  if (!xc[0] || !yc[0]) {
    y.c = [y.e = 0];
    return y;
  }

  // Initialise exponent of result as x.e + y.e.
  y.e = i + j;

  // If array xc has fewer digits than yc, swap xc and yc, and lengths.
  if (a < b) {
    c = xc;
    xc = yc;
    yc = c;
    j = a;
    a = b;
    b = j;
  }

  // Initialise coefficient array of result with zeros.
  for (c = new Array(j = a + b); j--;) c[j] = 0;

  // Multiply.

  // i is initially xc.length.
  for (i = b; i--;) {
    b = 0;

    // a is yc.length.
    for (j = a + i; j > i;) {
      // Current sum of products at this digit position, plus carry.
      b = c[j] + yc[i] * xc[j - i - 1] + b;
      c[j--] = b % 10;

      // carry
      b = b / 10 | 0;
    }
    c[j] = b;
  }

  // Increment result exponent if there is a final carry, otherwise remove leading zero.
  if (b) ++y.e;else c.shift();

  // Remove trailing zeros.
  for (i = c.length; !c[--i];) c.pop();
  y.c = c;
  return y;
};

/*
 * Return a string representing the value of this Big in exponential notation rounded to dp fixed
 * decimal places using rounding mode rm, or Big.RM if rm is not specified.
 *
 * dp? {number} Decimal places: integer, 0 to MAX_DP inclusive.
 * rm? {number} Rounding mode: 0 (down), 1 (half-up), 2 (half-even) or 3 (up).
 */
P.toExponential = function (dp, rm) {
  var x = this,
    n = x.c[0];
  if (dp !== UNDEFINED) {
    if (dp !== ~~dp || dp < 0 || dp > MAX_DP) {
      throw Error(INVALID_DP);
    }
    x = round(new x.constructor(x), ++dp, rm);
    for (; x.c.length < dp;) x.c.push(0);
  }
  return stringify(x, true, !!n);
};

/*
 * Return a string representing the value of this Big in normal notation rounded to dp fixed
 * decimal places using rounding mode rm, or Big.RM if rm is not specified.
 *
 * dp? {number} Decimal places: integer, 0 to MAX_DP inclusive.
 * rm? {number} Rounding mode: 0 (down), 1 (half-up), 2 (half-even) or 3 (up).
 *
 * (-0).toFixed(0) is '0', but (-0.1).toFixed(0) is '-0'.
 * (-0).toFixed(1) is '0.0', but (-0.01).toFixed(1) is '-0.0'.
 */
P.toFixed = function (dp, rm) {
  var x = this,
    n = x.c[0];
  if (dp !== UNDEFINED) {
    if (dp !== ~~dp || dp < 0 || dp > MAX_DP) {
      throw Error(INVALID_DP);
    }
    x = round(new x.constructor(x), dp + x.e + 1, rm);

    // x.e may have changed if the value is rounded up.
    for (dp = dp + x.e + 1; x.c.length < dp;) x.c.push(0);
  }
  return stringify(x, false, !!n);
};

/*
 * Return a string representing the value of this Big.
 * Return exponential notation if this Big has a positive exponent equal to or greater than
 * Big.PE, or a negative exponent equal to or less than Big.NE.
 * Omit the sign for negative zero.
 */
P[Symbol.for('nodejs.util.inspect.custom')] = P.toJSON = P.toString = function () {
  var x = this,
    Big = x.constructor;
  return stringify(x, x.e <= Big.NE || x.e >= Big.PE, !!x.c[0]);
};

/*
 * Return the value of this Big as a primitve number.
 */
P.toNumber = function () {
  var n = Number(stringify(this, true, true));
  if (this.constructor.strict === true && !this.eq(n.toString())) {
    throw Error(NAME + 'Imprecise conversion');
  }
  return n;
};

/*
 * Return a string representing the value of this Big rounded to sd significant digits using
 * rounding mode rm, or Big.RM if rm is not specified.
 * Use exponential notation if sd is less than the number of digits necessary to represent
 * the integer part of the value in normal notation.
 *
 * sd {number} Significant digits: integer, 1 to MAX_DP inclusive.
 * rm? {number} Rounding mode: 0 (down), 1 (half-up), 2 (half-even) or 3 (up).
 */
P.toPrecision = function (sd, rm) {
  var x = this,
    Big = x.constructor,
    n = x.c[0];
  if (sd !== UNDEFINED) {
    if (sd !== ~~sd || sd < 1 || sd > MAX_DP) {
      throw Error(INVALID + 'precision');
    }
    x = round(new Big(x), sd, rm);
    for (; x.c.length < sd;) x.c.push(0);
  }
  return stringify(x, sd <= x.e || x.e <= Big.NE || x.e >= Big.PE, !!n);
};

/*
 * Return a string representing the value of this Big.
 * Return exponential notation if this Big has a positive exponent equal to or greater than
 * Big.PE, or a negative exponent equal to or less than Big.NE.
 * Include the sign for negative zero.
 */
P.valueOf = function () {
  var x = this,
    Big = x.constructor;
  if (Big.strict === true) {
    throw Error(NAME + 'valueOf disallowed');
  }
  return stringify(x, x.e <= Big.NE || x.e >= Big.PE, true);
};

// Export

var Big = _Big_();

/// <reference types="https://raw.githubusercontent.com/DefinitelyTyped/DefinitelyTyped/master/types/big.js/index.d.ts" />
/* unused harmony default export */ var __WEBPACK_DEFAULT_EXPORT__ = (Big);

/***/ }),

/***/ "./src/lib/fasterdom/fasterdom.ts":
/*!****************************************!*\
  !*** ./src/lib/fasterdom/fasterdom.ts ***!
  \****************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   requestForcedReflow: () => (/* binding */ requestForcedReflow)
/* harmony export */ });
/* unused harmony exports requestMeasure, requestMutation, requestNextMutation */
/* harmony import */ var _util_safeExec__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../util/safeExec */ "./src/util/safeExec.ts");
/* harmony import */ var _util_schedulers__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../util/schedulers */ "./src/util/schedulers.ts");
/* harmony import */ var _stricterdom__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./stricterdom */ "./src/lib/fasterdom/stricterdom.ts");



let pendingMeasureTasks = [];
let pendingMutationTasks = [];
let pendingForceReflowTasks = [];
const runUpdatePassOnRaf = throttleWithRafFallback(() => {
  const currentMeasureTasks = pendingMeasureTasks;
  pendingMeasureTasks = [];
  currentMeasureTasks.forEach(task => {
    (0,_util_safeExec__WEBPACK_IMPORTED_MODULE_0__["default"])(task);
  });

  // We use promises to provide correct order for Mutation Observer callback microtasks
  Promise.resolve().then(() => {
    (0,_stricterdom__WEBPACK_IMPORTED_MODULE_2__.setPhase)('mutate');
    const currentMutationTasks = pendingMutationTasks;
    pendingMutationTasks = [];
    currentMutationTasks.forEach(task => {
      (0,_util_safeExec__WEBPACK_IMPORTED_MODULE_0__["default"])(task);
    });
  }).then(() => {
    (0,_stricterdom__WEBPACK_IMPORTED_MODULE_2__.setPhase)('measure');
    const pendingForceReflowMutationTasks = [];
    // Will include tasks created during the loop
    for (const task of pendingForceReflowTasks) {
      (0,_util_safeExec__WEBPACK_IMPORTED_MODULE_0__["default"])(() => {
        const mutationTask = task();
        if (mutationTask) {
          pendingForceReflowMutationTasks.push(mutationTask);
        }
      });
    }
    pendingForceReflowTasks = [];
    return pendingForceReflowMutationTasks;
  }).then(pendingForceReflowMutationTasks => {
    (0,_stricterdom__WEBPACK_IMPORTED_MODULE_2__.setPhase)('mutate');

    // Will include tasks created during the loop
    for (const task of pendingForceReflowMutationTasks) {
      (0,_util_safeExec__WEBPACK_IMPORTED_MODULE_0__["default"])(task);
    }
  }).then(() => {
    (0,_stricterdom__WEBPACK_IMPORTED_MODULE_2__.setPhase)('measure');
  });
});
function requestMeasure(cb) {
  pendingMeasureTasks.push(cb);
  runUpdatePassOnRaf();
}
function requestMutation(cb) {
  pendingMutationTasks.push(cb);
  runUpdatePassOnRaf();
}
function requestNextMutation(cb) {
  requestMeasure(() => {
    requestMutation(cb);
  });
}
function requestForcedReflow(cb) {
  pendingForceReflowTasks.push(cb);
  runUpdatePassOnRaf();
}
function throttleWithRafFallback(fn) {
  return (0,_util_schedulers__WEBPACK_IMPORTED_MODULE_1__.throttleWith)(throttledFn => {
    (0,_util_schedulers__WEBPACK_IMPORTED_MODULE_1__.fastRaf)(throttledFn, true);
  }, fn);
}


/***/ }),

/***/ "./src/lib/fasterdom/layoutCauses.ts":
/*!*******************************************!*\
  !*** ./src/lib/fasterdom/layoutCauses.ts ***!
  \*******************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
// https://gist.github.com/paulirish/5d52fb081b3570c81e3a

/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = ({
  Element: {
    props: ['clientLeft', 'clientTop', 'clientWidth', 'clientHeight', 'scrollWidth', 'scrollHeight', 'scrollLeft', 'scrollTop'],
    methods: ['getClientRects', 'getBoundingClientRect', 'scrollBy', 'scrollTo', 'scrollIntoView', 'scrollIntoViewIfNeeded']
  },
  HTMLElement: {
    props: ['offsetLeft', 'offsetTop', 'offsetWidth', 'offsetHeight', 'offsetParent', 'innerText'],
    methods: ['focus']
  },
  window: {
    props: ['scrollX', 'scrollY', 'innerHeight', 'innerWidth'],
    methods: ['getComputedStyle']
  },
  VisualViewport: {
    props: ['height', 'width', 'offsetTop', 'offsetLeft']
  },
  Document: {
    props: ['scrollingElement'],
    methods: ['elementFromPoint']
  },
  HTMLInputElement: {
    methods: ['select']
  },
  MouseEvent: {
    props: ['layerX', 'layerY', 'offsetX', 'offsetY']
  },
  Range: {
    methods: ['getClientRects', 'getBoundingClientRect']
  }
});

/***/ }),

/***/ "./src/lib/fasterdom/stricterdom.ts":
/*!******************************************!*\
  !*** ./src/lib/fasterdom/stricterdom.ts ***!
  \******************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   setPhase: () => (/* binding */ setPhase)
/* harmony export */ });
/* unused harmony exports getPhase, enableStrict, disableStrict, forceMeasure, forceMutation, setHandler */
/* harmony import */ var _layoutCauses__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./layoutCauses */ "./src/lib/fasterdom/layoutCauses.ts");

// eslint-disable-next-line no-console
const DEFAULT_ERROR_HANDLER = console.error;
let onError = DEFAULT_ERROR_HANDLER;
const nativeMethods = new Map();
let phase = 'measure';
let isStrict = false;
let observer;
function setPhase(newPhase) {
  phase = newPhase;
}
function getPhase() {
  return phase;
}
function enableStrict() {
  if (isStrict) return;
  isStrict = true;
  setupLayoutDetectors();
  setupMutationObserver();
}
function disableStrict() {
  if (!isStrict) return;
  clearMutationObserver();
  clearLayoutDetectors();
  isStrict = false;
}
function forceMeasure(cb) {
  if (phase !== 'mutate') {
    throw new Error('The current phase is \'measure\'');
  }
  phase = 'measure';
  const result = cb();
  phase = 'mutate';
  return result;
}
const forcedMutationAllowedFor = new Set();
function forceMutation(cb, nodes) {
  if (phase !== 'measure') {
    throw new Error('The current phase is \'mutate\'');
  }
  if (isStrict) {
    if (Array.isArray(nodes)) {
      nodes.forEach(node => {
        forcedMutationAllowedFor.add(node);
      });
    } else {
      forcedMutationAllowedFor.add(nodes);
    }
  }
  return cb();
}
function setHandler(handler) {
  onError = handler || DEFAULT_ERROR_HANDLER;
}
function setupLayoutDetectors() {
  Object.entries(_layoutCauses__WEBPACK_IMPORTED_MODULE_0__["default"]).forEach(_ref => {
    let [name, causes] = _ref;
    const entity = window[name];
    if (!entity) return;
    const prototype = typeof entity === 'object' ? entity : entity.prototype;
    if ('props' in causes) {
      causes.props.forEach(prop => {
        var _Object$getOwnPropert;
        const nativeGetter = (_Object$getOwnPropert = Object.getOwnPropertyDescriptor(prototype, prop)) === null || _Object$getOwnPropert === void 0 ? void 0 : _Object$getOwnPropert.get;
        if (!nativeGetter) {
          return;
        }
        nativeMethods.set(`${name}#${prop}`, nativeGetter);
        Object.defineProperty(prototype, prop, {
          get() {
            onMeasure(prop);
            return nativeGetter.call(this);
          }
        });
      });
    }
    if ('methods' in causes) {
      causes.methods.forEach(method => {
        const nativeMethod = prototype[method];
        nativeMethods.set(`${name}#${method}`, nativeMethod);

        // eslint-disable-next-line func-names
        prototype[method] = function () {
          onMeasure(method);
          for (var _len = arguments.length, args = new Array(_len), _key = 0; _key < _len; _key++) {
            args[_key] = arguments[_key];
          }
          return nativeMethod.apply(this, args);
        };
      });
    }
  });
}
function clearLayoutDetectors() {
  Object.entries(_layoutCauses__WEBPACK_IMPORTED_MODULE_0__["default"]).forEach(_ref2 => {
    let [name, causes] = _ref2;
    const entity = window[name];
    if (!entity) return;
    const prototype = typeof entity === 'object' ? entity : entity.prototype;
    if ('props' in causes) {
      causes.props.forEach(prop => {
        const nativeGetter = nativeMethods.get(`${name}#${prop}`);
        if (!nativeGetter) {
          return;
        }
        Object.defineProperty(prototype, prop, {
          get: nativeGetter
        });
      });
    }
    if ('methods' in causes) {
      causes.methods.forEach(method => {
        prototype[method] = nativeMethods.get(`${name}#${method}`);
      });
    }
  });
  nativeMethods.clear();
}
function setupMutationObserver() {
  observer = new MutationObserver(mutations => {
    if (phase !== 'mutate') {
      mutations.forEach(_ref3 => {
        let {
          target,
          type,
          attributeName
        } = _ref3;
        if (!document.contains(target)) {
          return;
        }
        if (forcedMutationAllowedFor.has(target)) {
          return;
        }
        if (type === 'childList' && target instanceof HTMLElement && target.contentEditable) {
          return;
        }
        if (attributeName !== null && attributeName !== void 0 && attributeName.startsWith('data-')) {
          return;
        }

        // eslint-disable-next-line no-console
        onError(new Error(`Unexpected mutation detected: \`${type === 'attributes' ? attributeName : type}\``));
      });
    }
    forcedMutationAllowedFor.clear();
  });
  observer.observe(document.body, {
    childList: true,
    attributes: true,
    subtree: true,
    characterData: false
  });
}
function clearMutationObserver() {
  var _observer;
  (_observer = observer) === null || _observer === void 0 ? void 0 : _observer.disconnect();
  observer = undefined;
}
function onMeasure(propName) {
  if (phase !== 'measure') {
    onError(new Error(`Unexpected measurement detected: \`${propName}\``));
  }
}

/***/ }),

/***/ "./src/util/PostMessageConnector.ts":
/*!******************************************!*\
  !*** ./src/util/PostMessageConnector.ts ***!
  \******************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   createConnector: () => (/* binding */ createConnector)
/* harmony export */ });
/* unused harmony export createExtensionConnector */
/* harmony import */ var _bigint__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./bigint */ "./src/util/bigint.ts");
/* harmony import */ var _generateUniqueId__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./generateUniqueId */ "./src/util/generateUniqueId.ts");
/* harmony import */ var _logs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./logs */ "./src/util/logs.ts");
function _defineProperty(obj, key, value) { key = _toPropertyKey(key); if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }
function _toPropertyKey(arg) { var key = _toPrimitive(arg, "string"); return typeof key === "symbol" ? key : String(key); }
function _toPrimitive(input, hint) { if (typeof input !== "object" || input === null) return input; var prim = input[Symbol.toPrimitive]; if (prim !== undefined) { var res = prim.call(input, hint || "default"); if (typeof res !== "object") return res; throw new TypeError("@@toPrimitive must return a primitive value."); } return (hint === "string" ? String : Number)(input); }



class ConnectorClass {
  constructor(target, onUpdate, channel, shouldUseJson) {
    let targetOrigin = arguments.length > 4 && arguments[4] !== undefined ? arguments[4] : '*';
    this.target = target;
    this.onUpdate = onUpdate;
    this.channel = channel;
    this.shouldUseJson = shouldUseJson;
    this.targetOrigin = targetOrigin;
    _defineProperty(this, "requestStates", new Map());
    _defineProperty(this, "requestStatesByCallback", new Map());
  }

  // eslint-disable-next-line class-methods-use-this
  destroy() {}
  init() {
    for (var _len = arguments.length, args = new Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }
    this.postMessage({
      type: 'init',
      args
    });
  }
  request(messageData) {
    const {
      requestStates,
      requestStatesByCallback
    } = this;
    const messageId = (0,_generateUniqueId__WEBPACK_IMPORTED_MODULE_1__["default"])();
    const payload = {
      type: 'callMethod',
      messageId,
      ...messageData
    };
    const requestState = {
      messageId
    };

    // Re-wrap type because of `postMessage`
    const promise = new Promise((resolve, reject) => {
      Object.assign(requestState, {
        resolve,
        reject
      });
    });
    if (typeof payload.args[payload.args.length - 1] === 'function') {
      payload.withCallback = true;
      const callback = payload.args.pop();
      requestState.callback = callback;
      requestStatesByCallback.set(callback, requestState);
    }
    requestStates.set(messageId, requestState);
    promise.catch(() => undefined).finally(() => {
      requestStates.delete(messageId);
      if (requestState.callback) {
        requestStatesByCallback.delete(requestState.callback);
      }
    });
    this.postMessage(payload);
    return promise;
  }
  cancelCallback(progressCallback) {
    progressCallback.isCanceled = true;
    const {
      messageId
    } = this.requestStatesByCallback.get(progressCallback) || {};
    if (!messageId) {
      return;
    }
    this.postMessage({
      type: 'cancelProgress',
      messageId
    });
  }
  onMessage(data) {
    if (typeof data === 'string') {
      try {
        data = JSON.parse(data, _bigint__WEBPACK_IMPORTED_MODULE_0__.bigintReviver);
      } catch (err) {
        (0,_logs__WEBPACK_IMPORTED_MODULE_2__.logDebugError)('PostMessageConnector: Failed to parse message', err);
        return;
      }
    }
    const {
      requestStates,
      channel
    } = this;
    if (data.channel !== channel) {
      return;
    }
    if (data.type === 'update' && this.onUpdate) {
      this.onUpdate(data.update);
    }
    if (data.type === 'methodResponse') {
      const requestState = requestStates.get(data.messageId);
      if (requestState) {
        if (data.error) {
          requestState.reject(data.error);
        } else {
          requestState.resolve(data.response);
        }
      }
    } else if (data.type === 'methodCallback') {
      var _requestState$callbac;
      const requestState = requestStates.get(data.messageId);
      requestState === null || requestState === void 0 || (_requestState$callbac = requestState.callback) === null || _requestState$callbac === void 0 ? void 0 : _requestState$callbac.call(requestState, ...data.callbackArgs);
    } else if (data.type === 'unhandledError') {
      var _data$error;
      throw new Error((_data$error = data.error) === null || _data$error === void 0 ? void 0 : _data$error.message);
    }
  }
  postMessage(data) {
    data.channel = this.channel;
    let rawData = data;
    if (this.shouldUseJson) {
      rawData = JSON.stringify(data);
    }
    if ('open' in this.target) {
      // Is Window
      this.target.postMessage(rawData, this.targetOrigin);
    } else {
      this.target.postMessage(rawData);
    }
  }
}
function createConnector(worker, onUpdate, channel, targetOrigin) {
  const connector = new ConnectorClass(worker, onUpdate, channel, undefined, targetOrigin);
  function handleMessage(_ref) {
    let {
      data
    } = _ref;
    connector.onMessage(data);
  }
  worker.addEventListener('message', handleMessage); // TS weirdly complains here

  connector.destroy = () => {
    worker.removeEventListener('message', handleMessage);
  };
  return connector;
}
function createExtensionConnector(name, onUpdate, getInitArgs, channel) {
  const connector = new ConnectorClass(connect(), onUpdate, channel, true);
  function connect() {
    // eslint-disable-next-line no-restricted-globals
    const port = self.chrome.runtime.connect({
      name
    });
    port.onMessage.addListener(data => {
      connector.onMessage(data);
    });

    // For some reason port can suddenly get disconnected
    port.onDisconnect.addListener(() => {
      connector.target = connect();
      connector.init(getInitArgs === null || getInitArgs === void 0 ? void 0 : getInitArgs());
    });
    return port;
  }
  connector.init(getInitArgs === null || getInitArgs === void 0 ? void 0 : getInitArgs());
  return connector;
}

/***/ }),

/***/ "./src/util/bigint.ts":
/*!****************************!*\
  !*** ./src/util/bigint.ts ***!
  \****************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   bigintReviver: () => (/* binding */ bigintReviver)
/* harmony export */ });
/* unused harmony exports BIGINT_PREFIX, bigintAbs, bigintDivideToNumber, bigintMultiplyToNumber, bigintRandom, bigintCountBits */
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../config */ "./src/config.ts");
/* harmony import */ var _decimals__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./decimals */ "./src/util/decimals.ts");
/* harmony import */ var _random__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./random */ "./src/util/random.ts");



const BIGINT_PREFIX = 'bigint:';
function bigintReviver(key, value) {
  if (typeof value === 'string' && value.startsWith(BIGINT_PREFIX)) {
    return BigInt(value.slice(7));
  }
  return value;
}
function bigintAbs(value) {
  return value === -0n || value < 0n ? -value : value;
}
function bigintDivideToNumber(value, num) {
  return value * _config__WEBPACK_IMPORTED_MODULE_0__.ONE_TON / (0,_decimals__WEBPACK_IMPORTED_MODULE_1__.fromDecimal)(num);
}
function bigintMultiplyToNumber(value, num) {
  return value * (0,_decimals__WEBPACK_IMPORTED_MODULE_1__.fromDecimal)(num) / _config__WEBPACK_IMPORTED_MODULE_0__.ONE_TON;
}
function bigintRandom(bytes) {
  let value = BigInt(0);
  for (const randomNumber of (0,_random__WEBPACK_IMPORTED_MODULE_2__.randomBytes)(bytes)) {
    const randomBigInt = BigInt(randomNumber);
    // eslint-disable-next-line no-bitwise
    value = (value << BigInt(8)) + randomBigInt;
  }
  return value;
}
function bigintCountBits(value) {
  const binaryString = value.toString(2);
  return binaryString.length;
}

/***/ }),

/***/ "./src/util/capacitorStorageProxy/index.ts":
/*!*************************************************!*\
  !*** ./src/util/capacitorStorageProxy/index.ts ***!
  \*************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   createWindowProvider: () => (/* binding */ createWindowProvider)
/* harmony export */ });
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../config */ "./src/config.ts");
/* harmony import */ var _createPostMessageInterface__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../createPostMessageInterface */ "./src/util/createPostMessageInterface.ts");
/* harmony import */ var _methods__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./methods */ "./src/util/capacitorStorageProxy/methods.ts");



function createWindowProvider(worker) {
  (0,_createPostMessageInterface__WEBPACK_IMPORTED_MODULE_1__.createPostMessageInterface)(function (name, origin) {
    const method = _methods__WEBPACK_IMPORTED_MODULE_2__[name];

    // @ts-ignore
    for (var _len = arguments.length, args = new Array(_len > 2 ? _len - 2 : 0), _key = 2; _key < _len; _key++) {
      args[_key - 2] = arguments[_key];
    }
    return method(...args);
  }, _config__WEBPACK_IMPORTED_MODULE_0__.WINDOW_PROVIDER_CHANNEL, worker, true);
}

/***/ }),

/***/ "./src/util/capacitorStorageProxy/methods.ts":
/*!***************************************************!*\
  !*** ./src/util/capacitorStorageProxy/methods.ts ***!
  \***************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   clear: () => (/* binding */ clear),
/* harmony export */   getItem: () => (/* binding */ getItem),
/* harmony export */   init: () => (/* binding */ init),
/* harmony export */   keys: () => (/* binding */ keys),
/* harmony export */   removeItem: () => (/* binding */ removeItem),
/* harmony export */   setItem: () => (/* binding */ setItem)
/* harmony export */ });
let SecureStoragePlugin;
let resolvePromise;
const promise = new Promise(resolve => {
  resolvePromise = resolve;
});
async function init() {
  if (SecureStoragePlugin) return;
  ({
    SecureStoragePlugin
  } = await __webpack_require__.e(/*! import() | capacitorSecureStorage */ "capacitorSecureStorage").then(__webpack_require__.bind(__webpack_require__, /*! capacitor-secure-storage-plugin */ "./node_modules/capacitor-secure-storage-plugin/dist/esm/index.js")));
  resolvePromise();
}
async function getItem(key) {
  var _await$get$catch;
  await promise;
  return (_await$get$catch = await SecureStoragePlugin.get({
    key
  }).catch(err => {
    const message = typeof err === 'string' ? err : err.message;
    if (message.includes('key does not exist')) {
      return undefined;
    } else {
      throw err;
    }
  })) === null || _await$get$catch === void 0 ? void 0 : _await$get$catch.value;
}
async function setItem(key, value) {
  await promise;
  return SecureStoragePlugin.set({
    key,
    value
  });
}
async function removeItem(key) {
  await promise;
  return SecureStoragePlugin.remove({
    key
  });
}
async function clear() {
  await promise;
  return SecureStoragePlugin.clear();
}
async function keys() {
  await promise;
  return SecureStoragePlugin.keys();
}

/***/ }),

/***/ "./src/util/createPostMessageInterface.ts":
/*!************************************************!*\
  !*** ./src/util/createPostMessageInterface.ts ***!
  \************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   createPostMessageInterface: () => (/* binding */ createPostMessageInterface)
/* harmony export */ });
/* unused harmony export createExtensionInterface */
/* harmony import */ var _ledger_tab__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./ledger/tab */ "./src/util/ledger/tab.ts");
/* harmony import */ var _bigint__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./bigint */ "./src/util/bigint.ts");
/* harmony import */ var _logs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./logs */ "./src/util/logs.ts");



const callbackState = new Map();
function createPostMessageInterface(api, channel) {
  let target = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : self;
  let shouldIgnoreErrors = arguments.length > 3 ? arguments[3] : undefined;
  function sendToOrigin(data, transferables) {
    data.channel = channel;
    if (transferables) {
      target.postMessage(data, transferables);
    } else {
      target.postMessage(data);
    }
  }
  if (!shouldIgnoreErrors) {
    handleErrors(sendToOrigin);
  }
  target.onmessage = message => {
    var _message$data;
    if (((_message$data = message.data) === null || _message$data === void 0 ? void 0 : _message$data.channel) === channel) {
      onMessage(api, message.data, sendToOrigin);
    }
  };
}
function createExtensionInterface(portName, api, channel, cleanUpdater) {
  let withAutoInit = arguments.length > 4 && arguments[4] !== undefined ? arguments[4] : false;
  chrome.runtime.onConnect.addListener(port => {
    var _port$sender;
    if (port.name !== portName) {
      return;
    }

    /**
     * If the sender's URL includes the DETACHED_TAB_URL, we skip further processing
     * This condition ensures that we don't interact with tabs that have already been closed.
     */
    const url = (_port$sender = port.sender) === null || _port$sender === void 0 ? void 0 : _port$sender.url;
    if (url !== null && url !== void 0 && url.includes(_ledger_tab__WEBPACK_IMPORTED_MODULE_0__.DETACHED_TAB_URL)) {
      return;
    }
    const origin = url ? new URL(url).origin : undefined;
    const dAppUpdater = update => {
      sendToOrigin({
        type: 'update',
        update
      });
    };
    function sendToOrigin(data) {
      data.channel = channel;
      const json = JSON.stringify(data);
      port.postMessage(json);
    }
    handleErrors(sendToOrigin);
    port.onMessage.addListener(data => {
      if (typeof data === 'string') {
        data = JSON.parse(data, _bigint__WEBPACK_IMPORTED_MODULE_1__.bigintReviver);
      }
      if (data.channel === channel) {
        onMessage(api, data, sendToOrigin, dAppUpdater, origin);
      }
    });
    port.onDisconnect.addListener(() => {
      cleanUpdater === null || cleanUpdater === void 0 ? void 0 : cleanUpdater(dAppUpdater);
    });
    if (withAutoInit) {
      onMessage(api, {
        type: 'init',
        name: 'init',
        args: []
      }, sendToOrigin, dAppUpdater);
    }
  });
}
async function onMessage(api, data, sendToOrigin, onUpdate, origin) {
  if (!onUpdate) {
    onUpdate = update => {
      sendToOrigin({
        type: 'update',
        update
      });
    };
  }
  switch (data.type) {
    case 'init':
      {
        var _api$init;
        const {
          args
        } = data;
        const promise = typeof api === 'function' ? api('init', origin, onUpdate, ...args) : (_api$init = api.init) === null || _api$init === void 0 ? void 0 : _api$init.call(api, onUpdate, ...args);
        await promise;
        break;
      }
    case 'callMethod':
      {
        const {
          messageId,
          name,
          args,
          withCallback
        } = data;
        try {
          if (messageId && withCallback) {
            const callback = function () {
              for (var _len = arguments.length, callbackArgs = new Array(_len), _key = 0; _key < _len; _key++) {
                callbackArgs[_key] = arguments[_key];
              }
              const lastArg = callbackArgs[callbackArgs.length - 1];
              sendToOrigin({
                type: 'methodCallback',
                messageId,
                callbackArgs
              }, isTransferable(lastArg) ? [lastArg] : undefined);
            };
            callbackState.set(messageId, callback);
            args.push(callback);
          }
          const response = typeof api === 'function' ? await api(name, origin, ...args) : await api[name](...args);
          const {
            arrayBuffer
          } = typeof response === 'object' && 'arrayBuffer' in response && response || {};
          if (messageId) {
            sendToOrigin({
              type: 'methodResponse',
              messageId,
              response
            }, arrayBuffer ? [arrayBuffer] : undefined);
          }
        } catch (err) {
          (0,_logs__WEBPACK_IMPORTED_MODULE_2__.logDebugError)(name, err);
          if (messageId) {
            sendToOrigin({
              type: 'methodResponse',
              messageId,
              error: {
                message: err.message
              }
            });
          }
        }
        if (messageId) {
          callbackState.delete(messageId);
        }
        break;
      }
    case 'cancelProgress':
      {
        const callback = callbackState.get(data.messageId);
        if (callback) {
          callback.isCanceled = true;
        }
        break;
      }
  }
}
function isTransferable(obj) {
  return obj instanceof ArrayBuffer || obj instanceof ImageBitmap;
}
function handleErrors(sendToOrigin) {
  self.onerror = e => {
    var _e$error;
    // eslint-disable-next-line no-console
    console.error(e);
    sendToOrigin({
      type: 'unhandledError',
      error: {
        message: ((_e$error = e.error) === null || _e$error === void 0 ? void 0 : _e$error.message) || 'Uncaught exception in worker'
      }
    });
  };
  self.addEventListener('unhandledrejection', e => {
    var _e$reason;
    // eslint-disable-next-line no-console
    console.error(e);
    sendToOrigin({
      type: 'unhandledError',
      error: {
        message: ((_e$reason = e.reason) === null || _e$reason === void 0 ? void 0 : _e$reason.message) || 'Uncaught rejection in worker'
      }
    });
  });
}

/***/ }),

/***/ "./src/util/decimals.ts":
/*!******************************!*\
  !*** ./src/util/decimals.ts ***!
  \******************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   fromDecimal: () => (/* binding */ fromDecimal)
/* harmony export */ });
/* unused harmony exports toDecimal, toBig, roundDecimal, getIsPositiveDecimal */
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../config */ "./src/config.ts");
/* harmony import */ var _lib_big_js__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../lib/big.js */ "./src/lib/big.js/index.js");


_lib_big_js__WEBPACK_IMPORTED_MODULE_1__.Big.RM = 0; // RoundDown
_lib_big_js__WEBPACK_IMPORTED_MODULE_1__.Big.NE = -100000; // Disable exponential form
_lib_big_js__WEBPACK_IMPORTED_MODULE_1__.Big.PE = 100000; // Disable exponential form

const ten = (0,_lib_big_js__WEBPACK_IMPORTED_MODULE_1__.Big)(10);
function fromDecimal(value, decimals) {
  return BigInt((0,_lib_big_js__WEBPACK_IMPORTED_MODULE_1__.Big)(value).mul(ten.pow(decimals !== null && decimals !== void 0 ? decimals : _config__WEBPACK_IMPORTED_MODULE_0__.DEFAULT_DECIMAL_PLACES)).round().toString());
}
function toDecimal(value, decimals) {
  let noFloor = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : false;
  return toBig(value, decimals !== null && decimals !== void 0 ? decimals : _config__WEBPACK_IMPORTED_MODULE_0__.DEFAULT_DECIMAL_PLACES, noFloor).toString();
}
function toBig(value) {
  let decimals = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : _config__WEBPACK_IMPORTED_MODULE_0__.DEFAULT_DECIMAL_PLACES;
  let noFloor = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : false;
  return (0,_lib_big_js__WEBPACK_IMPORTED_MODULE_1__.Big)(value.toString()).div(ten.pow(decimals)).round(decimals, noFloor ? _lib_big_js__WEBPACK_IMPORTED_MODULE_1__.Big.roundHalfUp : undefined);
}
function roundDecimal(value, decimals) {
  return (0,_lib_big_js__WEBPACK_IMPORTED_MODULE_1__.Big)(value).round(decimals).toString();
}
function getIsPositiveDecimal(value) {
  return !value.startsWith('-');
}

/***/ }),

/***/ "./src/util/generateUniqueId.ts":
/*!**************************************!*\
  !*** ./src/util/generateUniqueId.ts ***!
  \**************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (/* binding */ generateUniqueId)
/* harmony export */ });
function generateUniqueId() {
  return Date.now().toString(36) + Math.random().toString(36).slice(2);
}

/***/ }),

/***/ "./src/util/handleError.ts":
/*!*********************************!*\
  !*** ./src/util/handleError.ts ***!
  \*********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   handleError: () => (/* binding */ handleError)
/* harmony export */ });
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../config */ "./src/config.ts");
/* harmony import */ var _schedulers__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./schedulers */ "./src/util/schedulers.ts");


const noop = () => {};
const throttledAlert = typeof window !== 'undefined' ? (0,_schedulers__WEBPACK_IMPORTED_MODULE_1__.throttle)(window.alert, 1000) : noop;

// eslint-disable-next-line no-restricted-globals
self.addEventListener('error', handleErrorEvent);
// eslint-disable-next-line no-restricted-globals
self.addEventListener('unhandledrejection', handleErrorEvent);
function handleErrorEvent(e) {
  // https://stackoverflow.com/questions/49384120/resizeobserver-loop-limit-exceeded
  if (e instanceof ErrorEvent && e.message === 'ResizeObserver loop limit exceeded') {
    return;
  }
  e.preventDefault();
  handleError(e instanceof ErrorEvent ? e.error || e.message : e.reason);
}
function handleError(err) {
  // eslint-disable-next-line no-console
  console.error(err);
  const message = typeof err === 'string' ? err : err.message;
  const stack = typeof err === 'object' ? err.stack : undefined;
  if (message.endsWith('Failed to import rlottie-wasm.js')) {
    return;
  }
  if (_config__WEBPACK_IMPORTED_MODULE_0__.APP_ENV === 'development' || _config__WEBPACK_IMPORTED_MODULE_0__.APP_ENV === 'staging') {
    throttledAlert(`${_config__WEBPACK_IMPORTED_MODULE_0__.DEBUG_ALERT_MSG}\n\n${message || err}\n${stack}`);
  }
}

/***/ }),

/***/ "./src/util/ledger/tab.ts":
/*!********************************!*\
  !*** ./src/util/ledger/tab.ts ***!
  \********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   DETACHED_TAB_URL: () => (/* binding */ DETACHED_TAB_URL)
/* harmony export */ });
/* unused harmony exports openLedgerTab, closeLedgerTab, onLedgerTabClose */
const DETACHED_TAB_URL = '#detached';
let ledgerTabId;
function openLedgerTab() {
  return createLedgerTab();
}
async function closeLedgerTab() {
  if (!ledgerTabId) return;
  await chrome.tabs.query({
    active: true
  }, () => {
    if (!ledgerTabId) return;
    chrome.tabs.remove(ledgerTabId);
  });
}
function onLedgerTabClose(id, onClose) {
  chrome.tabs.onRemoved.addListener(closedTabId => {
    if (closedTabId !== id) {
      return;
    }
    ledgerTabId = undefined;
    onClose();
  });
}
async function createLedgerTab() {
  const tab = await chrome.tabs.create({
    url: `index.html${DETACHED_TAB_URL}`,
    active: true
  });
  await chrome.windows.update(tab.windowId, {
    focused: true
  });
  ledgerTabId = tab.id;
  return ledgerTabId;
}

/***/ }),

/***/ "./src/util/logs.ts":
/*!**************************!*\
  !*** ./src/util/logs.ts ***!
  \**************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   logDebugError: () => (/* binding */ logDebugError)
/* harmony export */ });
/* unused harmony export logDebug */
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../config */ "./src/config.ts");

function logDebugError(message) {
  if (_config__WEBPACK_IMPORTED_MODULE_0__.DEBUG) {
    for (var _len = arguments.length, args = new Array(_len > 1 ? _len - 1 : 0), _key = 1; _key < _len; _key++) {
      args[_key - 1] = arguments[_key];
    }
    // eslint-disable-next-line no-console
    console.error(`[DEBUG][${message}]`, ...args);
  }
}
function logDebug(message) {
  if (_config__WEBPACK_IMPORTED_MODULE_0__.DEBUG) {
    for (var _len2 = arguments.length, args = new Array(_len2 > 1 ? _len2 - 1 : 0), _key2 = 1; _key2 < _len2; _key2++) {
      args[_key2 - 1] = arguments[_key2];
    }
    // eslint-disable-next-line no-console
    console.log(`[DEBUG] ${message}`, ...args);
  }
}

/***/ }),

/***/ "./src/util/random.ts":
/*!****************************!*\
  !*** ./src/util/random.ts ***!
  \****************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   randomBytes: () => (/* binding */ randomBytes)
/* harmony export */ });
/* unused harmony exports random, sample */
function random(min, max) {
  return Math.floor(Math.random() * (max - min + 1) + min);
}
function sample(arr) {
  return arr[random(0, arr.length - 1)];
}
function randomBytes(size) {
  // eslint-disable-next-line no-restricted-globals
  return self.crypto.getRandomValues(new Uint8Array(size));
}

/***/ }),

/***/ "./src/util/safeExec.ts":
/*!******************************!*\
  !*** ./src/util/safeExec.ts ***!
  \******************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (/* binding */ safeExec)
/* harmony export */ });
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../config */ "./src/config.ts");
/* harmony import */ var _handleError__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./handleError */ "./src/util/handleError.ts");


const SAFE_EXEC_ENABLED = !_config__WEBPACK_IMPORTED_MODULE_0__.DEBUG_MORE;
function safeExec(cb, rescue, always) {
  if (!SAFE_EXEC_ENABLED) {
    return cb();
  }
  try {
    return cb();
  } catch (err) {
    rescue === null || rescue === void 0 ? void 0 : rescue(err);
    (0,_handleError__WEBPACK_IMPORTED_MODULE_1__.handleError)(err);
    return undefined;
  } finally {
    always === null || always === void 0 ? void 0 : always();
  }
}

/***/ }),

/***/ "./src/util/schedulers.ts":
/*!********************************!*\
  !*** ./src/util/schedulers.ts ***!
  \********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   fastRaf: () => (/* binding */ fastRaf),
/* harmony export */   pause: () => (/* binding */ pause),
/* harmony export */   throttle: () => (/* binding */ throttle),
/* harmony export */   throttleWith: () => (/* binding */ throttleWith)
/* harmony export */ });
/* unused harmony exports debounce, throttleWithTickEnd, onIdle, rafPromise, onTickEnd, onBeforeUnload, waitFor */
function debounce(fn, ms) {
  let shouldRunFirst = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : true;
  let shouldRunLast = arguments.length > 3 && arguments[3] !== undefined ? arguments[3] : true;
  let waitingTimeout;
  return function () {
    for (var _len = arguments.length, args = new Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }
    if (waitingTimeout) {
      clearTimeout(waitingTimeout);
      waitingTimeout = undefined;
    } else if (shouldRunFirst) {
      fn(...args);
    }

    // eslint-disable-next-line no-restricted-globals
    waitingTimeout = self.setTimeout(() => {
      if (shouldRunLast) {
        fn(...args);
      }
      waitingTimeout = undefined;
    }, ms);
  };
}
function throttle(fn, ms) {
  let shouldRunFirst = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : true;
  let interval;
  let isPending;
  let args;
  return function () {
    isPending = true;
    for (var _len2 = arguments.length, _args = new Array(_len2), _key2 = 0; _key2 < _len2; _key2++) {
      _args[_key2] = arguments[_key2];
    }
    args = _args;
    if (!interval) {
      if (shouldRunFirst) {
        isPending = false;
        fn(...args);
      }

      // eslint-disable-next-line no-restricted-globals
      interval = self.setInterval(() => {
        if (!isPending) {
          // eslint-disable-next-line no-restricted-globals
          self.clearInterval(interval);
          interval = undefined;
          return;
        }
        isPending = false;
        fn(...args);
      }, ms);
    }
  };
}
function throttleWithTickEnd(fn) {
  return throttleWith(onTickEnd, fn);
}
function throttleWith(schedulerFn, fn) {
  let waiting = false;
  let args;
  return function () {
    for (var _len3 = arguments.length, _args = new Array(_len3), _key3 = 0; _key3 < _len3; _key3++) {
      _args[_key3] = arguments[_key3];
    }
    args = _args;
    if (!waiting) {
      waiting = true;
      schedulerFn(() => {
        waiting = false;
        fn(...args);
      });
    }
  };
}
function onIdle(cb, timeout) {
  // eslint-disable-next-line no-restricted-globals
  if (self.requestIdleCallback) {
    // eslint-disable-next-line no-restricted-globals
    self.requestIdleCallback(cb, {
      timeout
    });
  } else {
    onTickEnd(cb);
  }
}
const pause = ms => new Promise(resolve => {
  setTimeout(() => resolve(), ms);
});
function rafPromise() {
  return new Promise(resolve => {
    fastRaf(resolve);
  });
}
const FAST_RAF_TIMEOUT_FALLBACK_MS = 35; // < 30 FPS

let fastRafCallbacks;
let fastRafFallbackCallbacks;
let fastRafFallbackTimeout;

// May result in an immediate execution if called from another RAF callback which was scheduled
// (and therefore is executed) earlier than RAF callback scheduled by `fastRaf`
function fastRaf(callback) {
  let withTimeoutFallback = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : false;
  if (!fastRafCallbacks) {
    fastRafCallbacks = new Set([callback]);
    requestAnimationFrame(() => {
      const currentCallbacks = fastRafCallbacks;
      fastRafCallbacks = undefined;
      fastRafFallbackCallbacks = undefined;
      if (fastRafFallbackTimeout) {
        clearTimeout(fastRafFallbackTimeout);
        fastRafFallbackTimeout = undefined;
      }
      currentCallbacks.forEach(cb => cb());
    });
  } else {
    fastRafCallbacks.add(callback);
  }
  if (withTimeoutFallback) {
    if (!fastRafFallbackCallbacks) {
      fastRafFallbackCallbacks = new Set([callback]);
    } else {
      fastRafFallbackCallbacks.add(callback);
    }
    if (!fastRafFallbackTimeout) {
      fastRafFallbackTimeout = window.setTimeout(() => {
        const currentTimeoutCallbacks = fastRafFallbackCallbacks;
        if (fastRafCallbacks) {
          currentTimeoutCallbacks.forEach(fastRafCallbacks.delete, fastRafCallbacks);
        }
        fastRafFallbackCallbacks = undefined;
        if (fastRafFallbackTimeout) {
          clearTimeout(fastRafFallbackTimeout);
          fastRafFallbackTimeout = undefined;
        }
        currentTimeoutCallbacks.forEach(cb => cb());
      }, FAST_RAF_TIMEOUT_FALLBACK_MS);
    }
  }
}
let onTickEndCallbacks;
function onTickEnd(callback) {
  if (!onTickEndCallbacks) {
    onTickEndCallbacks = [callback];
    Promise.resolve().then(() => {
      const currentCallbacks = onTickEndCallbacks;
      onTickEndCallbacks = undefined;
      currentCallbacks.forEach(cb => cb());
    });
  } else {
    onTickEndCallbacks.push(callback);
  }
}
let beforeUnloadCallbacks;
function onBeforeUnload(callback) {
  let isLast = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : false;
  if (!beforeUnloadCallbacks) {
    beforeUnloadCallbacks = [];
    // eslint-disable-next-line no-restricted-globals
    self.addEventListener('beforeunload', () => {
      beforeUnloadCallbacks.forEach(cb => cb());
    });
  }
  if (isLast) {
    beforeUnloadCallbacks.push(callback);
  } else {
    beforeUnloadCallbacks.unshift(callback);
  }
  return () => {
    beforeUnloadCallbacks = beforeUnloadCallbacks.filter(cb => cb !== callback);
  };
}
async function waitFor(cb, interval, attempts) {
  let i = 0;
  let result = cb();
  while (!result && i < attempts) {
    await pause(interval);
    i++;
    result = cb();
  }
  return result;
}

/***/ }),

/***/ "./src/util/windowEnvironment.ts":
/*!***************************************!*\
  !*** ./src/util/windowEnvironment.ts ***!
  \***************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   IS_IOS: () => (/* binding */ IS_IOS)
/* harmony export */ });
/* unused harmony exports getPlatform, IS_PWA, PLATFORM_ENV, IS_MAC_OS, IS_WINDOWS, IS_LINUX, IS_ANDROID, IS_SAFARI, IS_OPERA, IS_FIREFOX, IS_TOUCH_ENV, IS_CHROME_EXTENSION, IS_ELECTRON, IS_WEB, DEFAULT_LANG_CODE, USER_AGENT_LANG_CODE, DPR, IS_LEDGER_SUPPORTED, IS_LEDGER_EXTENSION_TAB, IS_BIOMETRIC_AUTH_SUPPORTED, IS_DELEGATED_BOTTOM_SHEET, IS_DELEGATING_BOTTOM_SHEET, IS_MULTITAB_SUPPORTED, IS_DAPP_SUPPORTED, IS_IOS_APP, IS_ANDROID_APP, setScrollbarWidthProperty, REM */
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../config */ "./src/config.ts");
/* harmony import */ var _lib_fasterdom_fasterdom__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../lib/fasterdom/fasterdom */ "./src/lib/fasterdom/fasterdom.ts");
var _window$chrome;


function getPlatform() {
  const {
    userAgent,
    platform
  } = window.navigator;
  if (/Android/.test(userAgent)) return 'Android';
  if (/Linux/.test(platform)) return 'Linux';
  const iosPlatforms = ['iPhone', 'iPad', 'iPod'];
  if (iosPlatforms.indexOf(platform) !== -1
  // For new IPads with M1 chip and IPadOS platform returns "MacIntel"
  || platform === 'MacIntel' && 'maxTouchPoints' in navigator && navigator.maxTouchPoints > 2) {
    return 'iOS';
  }
  const macosPlatforms = ['Macintosh', 'MacIntel', 'MacPPC', 'Mac68K'];
  if (macosPlatforms.indexOf(platform) !== -1) return 'macOS';
  const windowsPlatforms = ['Win32', 'Win64', 'Windows', 'WinCE'];
  if (windowsPlatforms.indexOf(platform) !== -1) return 'Windows';
  return undefined;
}
function isIPad() {
  const {
    userAgent,
    platform
  } = window.navigator;
  return platform === 'iPad' || userAgent.includes('iPad') || platform === 'MacIntel' && 'maxTouchPoints' in navigator && navigator.maxTouchPoints > 2;
}
function getBrowserLanguage() {
  const {
    language
  } = navigator;
  const lang = language.startsWith('zh') ? language.endsWith('TW') || language.endsWith('HK') ? 'zh-Hant' : 'zh-Hans' : language.substring(0, 2);
  return _config__WEBPACK_IMPORTED_MODULE_0__.LANG_LIST.some(_ref => {
    let {
      langCode
    } = _ref;
    return langCode === lang;
  }) ? lang : 'en';
}
const IS_PWA = window.matchMedia('(display-mode: standalone)').matches || window.navigator.standalone || document.referrer.includes('android-app://');
const PLATFORM_ENV = getPlatform();
const IS_MAC_OS = PLATFORM_ENV === 'macOS';
const IS_WINDOWS = PLATFORM_ENV === 'Windows';
const IS_LINUX = PLATFORM_ENV === 'Linux';
const IS_IOS = PLATFORM_ENV === 'iOS';
const IS_ANDROID = PLATFORM_ENV === 'Android';
const IS_SAFARI = /^((?!chrome|android).)*safari/i.test(navigator.userAgent);
const IS_OPERA = navigator.userAgent.includes(' OPR/');
const IS_FIREFOX = navigator.userAgent.includes('Firefox/');
const IS_TOUCH_ENV = window.matchMedia('(pointer: coarse)').matches;
const IS_CHROME_EXTENSION = Boolean((_window$chrome = window.chrome) === null || _window$chrome === void 0 ? void 0 : _window$chrome.system);
const IS_ELECTRON = Boolean(window.electron);
const IS_WEB = !_config__WEBPACK_IMPORTED_MODULE_0__.IS_CAPACITOR && !IS_ELECTRON && !_config__WEBPACK_IMPORTED_MODULE_0__.IS_EXTENSION;
const DEFAULT_LANG_CODE = 'en';
const USER_AGENT_LANG_CODE = getBrowserLanguage();
const DPR = window.devicePixelRatio || 1;
const IS_LEDGER_SUPPORTED = !(IS_IOS || IS_ANDROID && _config__WEBPACK_IMPORTED_MODULE_0__.IS_CAPACITOR || _config__WEBPACK_IMPORTED_MODULE_0__.IS_FIREFOX_EXTENSION);
const IS_LEDGER_EXTENSION_TAB = __webpack_require__.g.location.hash.startsWith('#detached');
// Disable biometric auth on electron for now until this issue is fixed:
// https://github.com/electron/electron/issues/24573
const IS_BIOMETRIC_AUTH_SUPPORTED = Boolean(!_config__WEBPACK_IMPORTED_MODULE_0__.IS_CAPACITOR && window.navigator.credentials && (!IS_ELECTRON || IS_MAC_OS));
const IS_DELEGATED_BOTTOM_SHEET = _config__WEBPACK_IMPORTED_MODULE_0__.IS_CAPACITOR && __webpack_require__.g.location.search.startsWith('?bottom-sheet');
const IS_DELEGATING_BOTTOM_SHEET = _config__WEBPACK_IMPORTED_MODULE_0__.IS_CAPACITOR && IS_IOS && !IS_DELEGATED_BOTTOM_SHEET && !isIPad();
const IS_MULTITAB_SUPPORTED = 'BroadcastChannel' in window && !IS_LEDGER_EXTENSION_TAB;
const IS_DAPP_SUPPORTED = _config__WEBPACK_IMPORTED_MODULE_0__.IS_EXTENSION || IS_ELECTRON || _config__WEBPACK_IMPORTED_MODULE_0__.IS_CAPACITOR;
const IS_IOS_APP = IS_IOS && _config__WEBPACK_IMPORTED_MODULE_0__.IS_CAPACITOR;
const IS_ANDROID_APP = IS_ANDROID && _config__WEBPACK_IMPORTED_MODULE_0__.IS_CAPACITOR;
function setScrollbarWidthProperty() {
  const el = document.createElement('div');
  el.style.cssText = 'overflow-x: hidden; overflow-y: scroll; visibility:hidden; position:absolute;';
  el.classList.add('custom-scroll');
  document.body.appendChild(el);
  (0,_lib_fasterdom_fasterdom__WEBPACK_IMPORTED_MODULE_1__.requestForcedReflow)(() => {
    const width = el.offsetWidth - el.clientWidth;
    return () => {
      document.documentElement.style.setProperty('--scrollbar-width', `${width}px`);
      el.remove();
    };
  });
}
const REM = parseInt(getComputedStyle(document.documentElement).fontSize, 10);

/***/ })

/******/ 	});
/************************************************************************/
/******/ 	// The module cache
/******/ 	var __webpack_module_cache__ = {};
/******/ 	
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/ 		// Check if module is in cache
/******/ 		var cachedModule = __webpack_module_cache__[moduleId];
/******/ 		if (cachedModule !== undefined) {
/******/ 			return cachedModule.exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = __webpack_module_cache__[moduleId] = {
/******/ 			// no module.id needed
/******/ 			// no module.loaded needed
/******/ 			exports: {}
/******/ 		};
/******/ 	
/******/ 		// Execute the module function
/******/ 		__webpack_modules__[moduleId](module, module.exports, __webpack_require__);
/******/ 	
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/ 	
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = __webpack_modules__;
/******/ 	
/************************************************************************/
/******/ 	/* webpack/runtime/define property getters */
/******/ 	(() => {
/******/ 		// define getter functions for harmony exports
/******/ 		__webpack_require__.d = (exports, definition) => {
/******/ 			for(var key in definition) {
/******/ 				if(__webpack_require__.o(definition, key) && !__webpack_require__.o(exports, key)) {
/******/ 					Object.defineProperty(exports, key, { enumerable: true, get: definition[key] });
/******/ 				}
/******/ 			}
/******/ 		};
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/ensure chunk */
/******/ 	(() => {
/******/ 		__webpack_require__.f = {};
/******/ 		// This file contains only the entry chunk.
/******/ 		// The chunk loading function for additional chunks
/******/ 		__webpack_require__.e = (chunkId) => {
/******/ 			return Promise.all(Object.keys(__webpack_require__.f).reduce((promises, key) => {
/******/ 				__webpack_require__.f[key](chunkId, promises);
/******/ 				return promises;
/******/ 			}, []));
/******/ 		};
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/get javascript chunk filename */
/******/ 	(() => {
/******/ 		// This function allow to reference async chunks
/******/ 		__webpack_require__.u = (chunkId) => {
/******/ 			// return url for filenames based on template
/******/ 			return "" + chunkId + ".js";
/******/ 		};
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/get mini-css chunk filename */
/******/ 	(() => {
/******/ 		// This function allow to reference async chunks
/******/ 		__webpack_require__.miniCssF = (chunkId) => {
/******/ 			// return url for filenames based on template
/******/ 			return undefined;
/******/ 		};
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/global */
/******/ 	(() => {
/******/ 		__webpack_require__.g = (function() {
/******/ 			if (typeof globalThis === 'object') return globalThis;
/******/ 			try {
/******/ 				return this || new Function('return this')();
/******/ 			} catch (e) {
/******/ 				if (typeof window === 'object') return window;
/******/ 			}
/******/ 		})();
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/hasOwnProperty shorthand */
/******/ 	(() => {
/******/ 		__webpack_require__.o = (obj, prop) => (Object.prototype.hasOwnProperty.call(obj, prop))
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/load script */
/******/ 	(() => {
/******/ 		var inProgress = {};
/******/ 		var dataWebpackPrefix = "api:";
/******/ 		// loadScript function to load a script via script tag
/******/ 		__webpack_require__.l = (url, done, key, chunkId) => {
/******/ 			if(inProgress[url]) { inProgress[url].push(done); return; }
/******/ 			var script, needAttach;
/******/ 			if(key !== undefined) {
/******/ 				var scripts = document.getElementsByTagName("script");
/******/ 				for(var i = 0; i < scripts.length; i++) {
/******/ 					var s = scripts[i];
/******/ 					if(s.getAttribute("src") == url || s.getAttribute("data-webpack") == dataWebpackPrefix + key) { script = s; break; }
/******/ 				}
/******/ 			}
/******/ 			if(!script) {
/******/ 				needAttach = true;
/******/ 				script = document.createElement('script');
/******/ 		
/******/ 				script.charset = 'utf-8';
/******/ 				script.timeout = 120;
/******/ 				if (__webpack_require__.nc) {
/******/ 					script.setAttribute("nonce", __webpack_require__.nc);
/******/ 				}
/******/ 				script.setAttribute("data-webpack", dataWebpackPrefix + key);
/******/ 				script.src = url;
/******/ 			}
/******/ 			inProgress[url] = [done];
/******/ 			var onScriptComplete = (prev, event) => {
/******/ 				// avoid mem leaks in IE.
/******/ 				script.onerror = script.onload = null;
/******/ 				clearTimeout(timeout);
/******/ 				var doneFns = inProgress[url];
/******/ 				delete inProgress[url];
/******/ 				script.parentNode && script.parentNode.removeChild(script);
/******/ 				doneFns && doneFns.forEach((fn) => (fn(event)));
/******/ 				if(prev) return prev(event);
/******/ 			}
/******/ 			var timeout = setTimeout(onScriptComplete.bind(null, undefined, { type: 'timeout', target: script }), 120000);
/******/ 			script.onerror = onScriptComplete.bind(null, script.onerror);
/******/ 			script.onload = onScriptComplete.bind(null, script.onload);
/******/ 			needAttach && document.head.appendChild(script);
/******/ 		};
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/make namespace object */
/******/ 	(() => {
/******/ 		// define __esModule on exports
/******/ 		__webpack_require__.r = (exports) => {
/******/ 			if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 				Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 			}
/******/ 			Object.defineProperty(exports, '__esModule', { value: true });
/******/ 		};
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/publicPath */
/******/ 	(() => {
/******/ 		var scriptUrl;
/******/ 		if (__webpack_require__.g.importScripts) scriptUrl = __webpack_require__.g.location + "";
/******/ 		var document = __webpack_require__.g.document;
/******/ 		if (!scriptUrl && document) {
/******/ 			if (document.currentScript)
/******/ 				scriptUrl = document.currentScript.src;
/******/ 			if (!scriptUrl) {
/******/ 				var scripts = document.getElementsByTagName("script");
/******/ 				if(scripts.length) {
/******/ 					var i = scripts.length - 1;
/******/ 					while (i > -1 && !scriptUrl) scriptUrl = scripts[i--].src;
/******/ 				}
/******/ 			}
/******/ 		}
/******/ 		// When supporting browsers where an automatic publicPath is not supported you must specify an output.publicPath manually via configuration
/******/ 		// or pass an empty string ("") and set the __webpack_public_path__ variable from your code to use your own logic.
/******/ 		if (!scriptUrl) throw new Error("Automatic publicPath is not supported in this browser");
/******/ 		scriptUrl = scriptUrl.replace(/#.*$/, "").replace(/\?.*$/, "").replace(/\/[^\/]+$/, "/");
/******/ 		__webpack_require__.p = scriptUrl;
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/jsonp chunk loading */
/******/ 	(() => {
/******/ 		__webpack_require__.b = document.baseURI || self.location.href;
/******/ 		
/******/ 		// object to store loaded and loading chunks
/******/ 		// undefined = chunk not loaded, null = chunk preloaded/prefetched
/******/ 		// [resolve, reject, Promise] = chunk loading, 0 = chunk loaded
/******/ 		var installedChunks = {
/******/ 			"api": 0
/******/ 		};
/******/ 		
/******/ 		__webpack_require__.f.j = (chunkId, promises) => {
/******/ 				// JSONP chunk loading for javascript
/******/ 				var installedChunkData = __webpack_require__.o(installedChunks, chunkId) ? installedChunks[chunkId] : undefined;
/******/ 				if(installedChunkData !== 0) { // 0 means "already installed".
/******/ 		
/******/ 					// a Promise means "currently loading".
/******/ 					if(installedChunkData) {
/******/ 						promises.push(installedChunkData[2]);
/******/ 					} else {
/******/ 						if(true) { // all chunks have JS
/******/ 							// setup Promise in chunk cache
/******/ 							var promise = new Promise((resolve, reject) => (installedChunkData = installedChunks[chunkId] = [resolve, reject]));
/******/ 							promises.push(installedChunkData[2] = promise);
/******/ 		
/******/ 							// start chunk loading
/******/ 							var url = __webpack_require__.p + __webpack_require__.u(chunkId);
/******/ 							// create error before stack unwound to get useful stacktrace later
/******/ 							var error = new Error();
/******/ 							var loadingEnded = (event) => {
/******/ 								if(__webpack_require__.o(installedChunks, chunkId)) {
/******/ 									installedChunkData = installedChunks[chunkId];
/******/ 									if(installedChunkData !== 0) installedChunks[chunkId] = undefined;
/******/ 									if(installedChunkData) {
/******/ 										var errorType = event && (event.type === 'load' ? 'missing' : event.type);
/******/ 										var realSrc = event && event.target && event.target.src;
/******/ 										error.message = 'Loading chunk ' + chunkId + ' failed.\n(' + errorType + ': ' + realSrc + ')';
/******/ 										error.name = 'ChunkLoadError';
/******/ 										error.type = errorType;
/******/ 										error.request = realSrc;
/******/ 										installedChunkData[1](error);
/******/ 									}
/******/ 								}
/******/ 							};
/******/ 							__webpack_require__.l(url, loadingEnded, "chunk-" + chunkId, chunkId);
/******/ 						}
/******/ 					}
/******/ 				}
/******/ 		};
/******/ 		
/******/ 		// no prefetching
/******/ 		
/******/ 		// no preloaded
/******/ 		
/******/ 		// no HMR
/******/ 		
/******/ 		// no HMR manifest
/******/ 		
/******/ 		// no on chunks loaded
/******/ 		
/******/ 		// install a JSONP callback for chunk loading
/******/ 		var webpackJsonpCallback = (parentChunkLoadingFunction, data) => {
/******/ 			var [chunkIds, moreModules, runtime] = data;
/******/ 			// add "moreModules" to the modules object,
/******/ 			// then flag all "chunkIds" as loaded and fire callback
/******/ 			var moduleId, chunkId, i = 0;
/******/ 			if(chunkIds.some((id) => (installedChunks[id] !== 0))) {
/******/ 				for(moduleId in moreModules) {
/******/ 					if(__webpack_require__.o(moreModules, moduleId)) {
/******/ 						__webpack_require__.m[moduleId] = moreModules[moduleId];
/******/ 					}
/******/ 				}
/******/ 				if(runtime) var result = runtime(__webpack_require__);
/******/ 			}
/******/ 			if(parentChunkLoadingFunction) parentChunkLoadingFunction(data);
/******/ 			for(;i < chunkIds.length; i++) {
/******/ 				chunkId = chunkIds[i];
/******/ 				if(__webpack_require__.o(installedChunks, chunkId) && installedChunks[chunkId]) {
/******/ 					installedChunks[chunkId][0]();
/******/ 				}
/******/ 				installedChunks[chunkId] = 0;
/******/ 			}
/******/ 		
/******/ 		}
/******/ 		
/******/ 		var chunkLoadingGlobal = this["webpackChunkapi"] = this["webpackChunkapi"] || [];
/******/ 		chunkLoadingGlobal.forEach(webpackJsonpCallback.bind(null, 0));
/******/ 		chunkLoadingGlobal.push = webpackJsonpCallback.bind(null, chunkLoadingGlobal.push.bind(chunkLoadingGlobal));
/******/ 	})();
/******/ 	
/************************************************************************/
var __webpack_exports__ = {};
// This entry need to be wrapped in an IIFE because it need to be isolated against other modules in the chunk.
(() => {
/*!**************************!*\
  !*** ./src/api/index.ts ***!
  \**************************/
__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   callApi: () => (/* reexport safe */ _providers_worker_connector__WEBPACK_IMPORTED_MODULE_0__.callApi),
/* harmony export */   initApi: () => (/* reexport safe */ _providers_worker_connector__WEBPACK_IMPORTED_MODULE_0__.initApi)
/* harmony export */ });
/* harmony import */ var _providers_worker_connector__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./providers/worker/connector */ "./src/api/providers/worker/connector.ts");
// export { initApi, callApi } from './providers/direct/connector';

})();

/******/ 	return __webpack_exports__;
/******/ })()
;
});
//# sourceMappingURL=bundle.js.map