const path = require('path');
const webpack = require('webpack');
const { BundleAnalyzerPlugin } = require('webpack-bundle-analyzer');


module.exports = {
    entry: './src/api/index.ts', // Главная точка входа
    output: {
        filename: 'bundle.js', // Имя итогового файла
        path: path.resolve(__dirname, 'dist'), // Директория, куда будет помещён бандл
        chunkFilename: 'chunks/[name].js', // Настройка имени для асинхронных чанков
    },
    resolve: {
        extensions: ['.tsx', '.ts', '.js'], // Расширения, которые будет обрабатывать Webpack
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/, // Регулярное выражение для файлов с расширениями .ts и .tsx
                use: 'ts-loader', // Используемый загрузчик для TypeScript
                exclude: /node_modules/, // Исключаем сборку файлов из node_modules
            },
        ],
    },
    plugins: [
        new BundleAnalyzerPlugin({
            analyzerMode: 'static', // Генерирует статический HTML-файл
            reportFilename: 'bundle-report.html', // Имя файла отчета
            openAnalyzer: true, // Автоматически открывает отчет в браузере
        }),
        new webpack.DefinePlugin({
            'process.env.NODE_ENV': JSON.stringify('development'),
        }),
    ],
    mode: 'none', // Отключает оптимизацию и минификацию
    optimization: {
        minimize: false, // Отключает минификацию
        minimizer: [], // Убедитесь, что minimizer пуст
    },
};