/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
/******/ 	var __webpack_modules__ = ([
/* 0 */,
/* 1 */
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   callApi: () => (/* binding */ callApi),
/* harmony export */   callApiWithThrow: () => (/* binding */ callApiWithThrow),
/* harmony export */   initApi: () => (/* binding */ initApi)
/* harmony export */ });
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(2);
/* harmony import */ var _util_capacitorStorageProxy__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(3);
/* harmony import */ var _util_logs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(10);
/* harmony import */ var _util_PostMessageConnector__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(12);
/* harmony import */ var _util_schedulers__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(14);
/* harmony import */ var _util_windowEnvironment__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(15);






const HEALTH_CHECK_TIMEOUT = 150;
const HEALTH_CHECK_MIN_DELAY = 5000; // 5 sec
let updateCallback;
let worker;
let connector;
let isInitialized = false;
function initApi(onUpdate, initArgs) {
    updateCallback = onUpdate;
    if (!connector) {
        worker = new Worker(
        /* webpackChunkName: "worker" */ new URL(/* worker import */ __webpack_require__.p + __webpack_require__.u(2), __webpack_require__.b));
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
async function callApi(fnName, ...args) {
    if (!connector) {
        (0,_util_logs__WEBPACK_IMPORTED_MODULE_2__.logDebugError)('API is not initialized');
        return undefined;
    }
    try {
        return await connector.request({
            name: fnName,
            args,
        });
    }
    catch (err) {
        return undefined;
    }
}
function callApiWithThrow(fnName, ...args) {
    return connector.request({
        name: fnName,
        args,
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
        await Promise.race([
            callApiWithThrow('ping'),
            (0,_util_schedulers__WEBPACK_IMPORTED_MODULE_4__.pause)(HEALTH_CHECK_TIMEOUT)
                .then(() => (isResolved ? undefined : Promise.reject(new Error('HEALTH_CHECK_TIMEOUT')))),
        ]);
    }
    catch (err) {
        // eslint-disable-next-line no-console
        console.error(err);
        if (Date.now() - startedAt >= HEALTH_CHECK_MIN_DELAY) {
            worker?.terminate();
            worker = undefined;
            connector = undefined;
            updateCallback({ type: 'requestReconnectApi' });
        }
    }
    finally {
        isResolved = true;
    }
}


/***/ }),
/* 2 */
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   ACTIVE_TAB_STORAGE_KEY: () => (/* binding */ ACTIVE_TAB_STORAGE_KEY),
/* harmony export */   ANIMATED_STICKER_BIG_SIZE_PX: () => (/* binding */ ANIMATED_STICKER_BIG_SIZE_PX),
/* harmony export */   ANIMATED_STICKER_DEFAULT_PX: () => (/* binding */ ANIMATED_STICKER_DEFAULT_PX),
/* harmony export */   ANIMATED_STICKER_HUGE_SIZE_PX: () => (/* binding */ ANIMATED_STICKER_HUGE_SIZE_PX),
/* harmony export */   ANIMATED_STICKER_MIDDLE_SIZE_PX: () => (/* binding */ ANIMATED_STICKER_MIDDLE_SIZE_PX),
/* harmony export */   ANIMATED_STICKER_SMALL_SIZE_PX: () => (/* binding */ ANIMATED_STICKER_SMALL_SIZE_PX),
/* harmony export */   ANIMATED_STICKER_TINY_SIZE_PX: () => (/* binding */ ANIMATED_STICKER_TINY_SIZE_PX),
/* harmony export */   ANIMATION_END_DELAY: () => (/* binding */ ANIMATION_END_DELAY),
/* harmony export */   ANIMATION_LEVEL_DEFAULT: () => (/* binding */ ANIMATION_LEVEL_DEFAULT),
/* harmony export */   ANIMATION_LEVEL_MAX: () => (/* binding */ ANIMATION_LEVEL_MAX),
/* harmony export */   ANIMATION_LEVEL_MED: () => (/* binding */ ANIMATION_LEVEL_MED),
/* harmony export */   ANIMATION_LEVEL_MIN: () => (/* binding */ ANIMATION_LEVEL_MIN),
/* harmony export */   APP_ENV: () => (/* binding */ APP_ENV),
/* harmony export */   APP_ENV_MARKER: () => (/* binding */ APP_ENV_MARKER),
/* harmony export */   APP_NAME: () => (/* binding */ APP_NAME),
/* harmony export */   APP_REPO_URL: () => (/* binding */ APP_REPO_URL),
/* harmony export */   APP_VERSION: () => (/* binding */ APP_VERSION),
/* harmony export */   BASE_URL: () => (/* binding */ BASE_URL),
/* harmony export */   BETA_URL: () => (/* binding */ BETA_URL),
/* harmony export */   BOT_USERNAME: () => (/* binding */ BOT_USERNAME),
/* harmony export */   BRILLIANT_API_BASE_URL: () => (/* binding */ BRILLIANT_API_BASE_URL),
/* harmony export */   BROWSER_HISTORY_LIMIT: () => (/* binding */ BROWSER_HISTORY_LIMIT),
/* harmony export */   BURN_ADDRESS: () => (/* binding */ BURN_ADDRESS),
/* harmony export */   BURN_CHUNK_DURATION_APPROX_SEC: () => (/* binding */ BURN_CHUNK_DURATION_APPROX_SEC),
/* harmony export */   CHANGELLY_AML_KYC: () => (/* binding */ CHANGELLY_AML_KYC),
/* harmony export */   CHANGELLY_LIVE_CHAT_URL: () => (/* binding */ CHANGELLY_LIVE_CHAT_URL),
/* harmony export */   CHANGELLY_PRIVACY_POLICY: () => (/* binding */ CHANGELLY_PRIVACY_POLICY),
/* harmony export */   CHANGELLY_SECURITY_EMAIL: () => (/* binding */ CHANGELLY_SECURITY_EMAIL),
/* harmony export */   CHANGELLY_SUPPORT_EMAIL: () => (/* binding */ CHANGELLY_SUPPORT_EMAIL),
/* harmony export */   CHANGELLY_TERMS_OF_USE: () => (/* binding */ CHANGELLY_TERMS_OF_USE),
/* harmony export */   CHANGELLY_WAITING_DEADLINE: () => (/* binding */ CHANGELLY_WAITING_DEADLINE),
/* harmony export */   CLAIM_ADDRESS: () => (/* binding */ CLAIM_ADDRESS),
/* harmony export */   CLAIM_AMOUNT: () => (/* binding */ CLAIM_AMOUNT),
/* harmony export */   CLAIM_COMMENT: () => (/* binding */ CLAIM_COMMENT),
/* harmony export */   CURRENCY_LIST: () => (/* binding */ CURRENCY_LIST),
/* harmony export */   DEBUG: () => (/* binding */ DEBUG),
/* harmony export */   DEBUG_ALERT_MSG: () => (/* binding */ DEBUG_ALERT_MSG),
/* harmony export */   DEBUG_MORE: () => (/* binding */ DEBUG_MORE),
/* harmony export */   DEFAULT_CEX_SWAP_SECOND_TOKEN_SLUG: () => (/* binding */ DEFAULT_CEX_SWAP_SECOND_TOKEN_SLUG),
/* harmony export */   DEFAULT_DECIMAL_PLACES: () => (/* binding */ DEFAULT_DECIMAL_PLACES),
/* harmony export */   DEFAULT_ERROR_PAUSE: () => (/* binding */ DEFAULT_ERROR_PAUSE),
/* harmony export */   DEFAULT_FEE: () => (/* binding */ DEFAULT_FEE),
/* harmony export */   DEFAULT_LANDSCAPE_ACTION_TAB_ID: () => (/* binding */ DEFAULT_LANDSCAPE_ACTION_TAB_ID),
/* harmony export */   DEFAULT_PRICE_CURRENCY: () => (/* binding */ DEFAULT_PRICE_CURRENCY),
/* harmony export */   DEFAULT_RETRIES: () => (/* binding */ DEFAULT_RETRIES),
/* harmony export */   DEFAULT_SLIPPAGE_VALUE: () => (/* binding */ DEFAULT_SLIPPAGE_VALUE),
/* harmony export */   DEFAULT_SWAP_SECOND_TOKEN_SLUG: () => (/* binding */ DEFAULT_SWAP_SECOND_TOKEN_SLUG),
/* harmony export */   DEFAULT_TIMEOUT: () => (/* binding */ DEFAULT_TIMEOUT),
/* harmony export */   DEFAULT_WALLET_VERSION: () => (/* binding */ DEFAULT_WALLET_VERSION),
/* harmony export */   DIESEL_ADDRESS: () => (/* binding */ DIESEL_ADDRESS),
/* harmony export */   DIESEL_TOKENS: () => (/* binding */ DIESEL_TOKENS),
/* harmony export */   ELECTRON_HOST_URL: () => (/* binding */ ELECTRON_HOST_URL),
/* harmony export */   ELECTRON_TONHTTPAPI_MAINNET_API_KEY: () => (/* binding */ ELECTRON_TONHTTPAPI_MAINNET_API_KEY),
/* harmony export */   ELECTRON_TONHTTPAPI_TESTNET_API_KEY: () => (/* binding */ ELECTRON_TONHTTPAPI_TESTNET_API_KEY),
/* harmony export */   EMPTY_HASH_VALUE: () => (/* binding */ EMPTY_HASH_VALUE),
/* harmony export */   FRACTION_DIGITS: () => (/* binding */ FRACTION_DIGITS),
/* harmony export */   GETGEMS_BASE_MAINNET_URL: () => (/* binding */ GETGEMS_BASE_MAINNET_URL),
/* harmony export */   GETGEMS_BASE_TESTNET_URL: () => (/* binding */ GETGEMS_BASE_TESTNET_URL),
/* harmony export */   GLOBAL_STATE_CACHE_DISABLED: () => (/* binding */ GLOBAL_STATE_CACHE_DISABLED),
/* harmony export */   GLOBAL_STATE_CACHE_KEY: () => (/* binding */ GLOBAL_STATE_CACHE_KEY),
/* harmony export */   HISTORY_PERIODS: () => (/* binding */ HISTORY_PERIODS),
/* harmony export */   INACTIVE_MARKER: () => (/* binding */ INACTIVE_MARKER),
/* harmony export */   INDEXED_DB_NAME: () => (/* binding */ INDEXED_DB_NAME),
/* harmony export */   INDEXED_DB_STORE_NAME: () => (/* binding */ INDEXED_DB_STORE_NAME),
/* harmony export */   INIT_SWAP_ASSETS: () => (/* binding */ INIT_SWAP_ASSETS),
/* harmony export */   IS_ANDROID_DIRECT: () => (/* binding */ IS_ANDROID_DIRECT),
/* harmony export */   IS_CAPACITOR: () => (/* binding */ IS_CAPACITOR),
/* harmony export */   IS_EXTENSION: () => (/* binding */ IS_EXTENSION),
/* harmony export */   IS_FIREFOX_EXTENSION: () => (/* binding */ IS_FIREFOX_EXTENSION),
/* harmony export */   IS_PACKAGED_ELECTRON: () => (/* binding */ IS_PACKAGED_ELECTRON),
/* harmony export */   IS_PERF: () => (/* binding */ IS_PERF),
/* harmony export */   IS_PRODUCTION: () => (/* binding */ IS_PRODUCTION),
/* harmony export */   IS_TEST: () => (/* binding */ IS_TEST),
/* harmony export */   LANG_CACHE_NAME: () => (/* binding */ LANG_CACHE_NAME),
/* harmony export */   LANG_LIST: () => (/* binding */ LANG_LIST),
/* harmony export */   LIQUID_JETTON: () => (/* binding */ LIQUID_JETTON),
/* harmony export */   LIQUID_POOL: () => (/* binding */ LIQUID_POOL),
/* harmony export */   MAIN_ACCOUNT_ID: () => (/* binding */ MAIN_ACCOUNT_ID),
/* harmony export */   MIN_ASSETS_TAB_VIEW: () => (/* binding */ MIN_ASSETS_TAB_VIEW),
/* harmony export */   MIN_BALANCE_FOR_UNSTAKE: () => (/* binding */ MIN_BALANCE_FOR_UNSTAKE),
/* harmony export */   MNEMONIC_CHECK_COUNT: () => (/* binding */ MNEMONIC_CHECK_COUNT),
/* harmony export */   MNEMONIC_COUNT: () => (/* binding */ MNEMONIC_COUNT),
/* harmony export */   MOBILE_SCREEN_MAX_WIDTH: () => (/* binding */ MOBILE_SCREEN_MAX_WIDTH),
/* harmony export */   MULTITAB_DATA_CHANNEL_NAME: () => (/* binding */ MULTITAB_DATA_CHANNEL_NAME),
/* harmony export */   MYCOIN_SLUG: () => (/* binding */ MYCOIN_SLUG),
/* harmony export */   MYCOIN_SLUG_TESTNET: () => (/* binding */ MYCOIN_SLUG_TESTNET),
/* harmony export */   MYCOIN_TOKEN: () => (/* binding */ MYCOIN_TOKEN),
/* harmony export */   MYCOIN_TOKEN_TESTNET: () => (/* binding */ MYCOIN_TOKEN_TESTNET),
/* harmony export */   MY_TON_WALLET_PROMO_URL: () => (/* binding */ MY_TON_WALLET_PROMO_URL),
/* harmony export */   NATIVE_BIOMETRICS_SERVER: () => (/* binding */ NATIVE_BIOMETRICS_SERVER),
/* harmony export */   NATIVE_BIOMETRICS_USERNAME: () => (/* binding */ NATIVE_BIOMETRICS_USERNAME),
/* harmony export */   NFT_BATCH_SIZE: () => (/* binding */ NFT_BATCH_SIZE),
/* harmony export */   NFT_FRAGMENT_COLLECTIONS: () => (/* binding */ NFT_FRAGMENT_COLLECTIONS),
/* harmony export */   NOMINATORS_STAKING_MIN_AMOUNT: () => (/* binding */ NOMINATORS_STAKING_MIN_AMOUNT),
/* harmony export */   NOTCOIN_EXCHANGERS: () => (/* binding */ NOTCOIN_EXCHANGERS),
/* harmony export */   NOTCOIN_FORWARD_TON_AMOUNT: () => (/* binding */ NOTCOIN_FORWARD_TON_AMOUNT),
/* harmony export */   NOTCOIN_VOUCHERS_ADDRESS: () => (/* binding */ NOTCOIN_VOUCHERS_ADDRESS),
/* harmony export */   ONE_TON: () => (/* binding */ ONE_TON),
/* harmony export */   PIN_LENGTH: () => (/* binding */ PIN_LENGTH),
/* harmony export */   POPULAR_WALLET_VERSIONS: () => (/* binding */ POPULAR_WALLET_VERSIONS),
/* harmony export */   PRIVATE_KEY_HEX_LENGTH: () => (/* binding */ PRIVATE_KEY_HEX_LENGTH),
/* harmony export */   PRODUCTION_URL: () => (/* binding */ PRODUCTION_URL),
/* harmony export */   PROXY_HOSTS: () => (/* binding */ PROXY_HOSTS),
/* harmony export */   RE_LINK_TEMPLATE: () => (/* binding */ RE_LINK_TEMPLATE),
/* harmony export */   RE_TG_BOT_MENTION: () => (/* binding */ RE_TG_BOT_MENTION),
/* harmony export */   SHORT_CURRENCY_SYMBOL_MAP: () => (/* binding */ SHORT_CURRENCY_SYMBOL_MAP),
/* harmony export */   SHORT_FRACTION_DIGITS: () => (/* binding */ SHORT_FRACTION_DIGITS),
/* harmony export */   STAKING_CYCLE_DURATION_MS: () => (/* binding */ STAKING_CYCLE_DURATION_MS),
/* harmony export */   STAKING_FORWARD_AMOUNT: () => (/* binding */ STAKING_FORWARD_AMOUNT),
/* harmony export */   STAKING_MIN_AMOUNT: () => (/* binding */ STAKING_MIN_AMOUNT),
/* harmony export */   STAKING_POOLS: () => (/* binding */ STAKING_POOLS),
/* harmony export */   STRICTERDOM_ENABLED: () => (/* binding */ STRICTERDOM_ENABLED),
/* harmony export */   SUPPORT_USERNAME: () => (/* binding */ SUPPORT_USERNAME),
/* harmony export */   SWAP_FEE_ADDRESS: () => (/* binding */ SWAP_FEE_ADDRESS),
/* harmony export */   TELEGRAM_WEB_URL: () => (/* binding */ TELEGRAM_WEB_URL),
/* harmony export */   THEME_DEFAULT: () => (/* binding */ THEME_DEFAULT),
/* harmony export */   TINY_TRANSFER_MAX_COST: () => (/* binding */ TINY_TRANSFER_MAX_COST),
/* harmony export */   TOKEN_EXPLORER_MAINNET_URL: () => (/* binding */ TOKEN_EXPLORER_MAINNET_URL),
/* harmony export */   TOKEN_EXPLORER_NAME: () => (/* binding */ TOKEN_EXPLORER_NAME),
/* harmony export */   TOKEN_EXPLORER_TESTNET_URL: () => (/* binding */ TOKEN_EXPLORER_TESTNET_URL),
/* harmony export */   TOKEN_INFO: () => (/* binding */ TOKEN_INFO),
/* harmony export */   TONAPIIO_MAINNET_URL: () => (/* binding */ TONAPIIO_MAINNET_URL),
/* harmony export */   TONAPIIO_TESTNET_URL: () => (/* binding */ TONAPIIO_TESTNET_URL),
/* harmony export */   TONCOIN_SLUG: () => (/* binding */ TONCOIN_SLUG),
/* harmony export */   TONCONNECT_PROTOCOL_VERSION: () => (/* binding */ TONCONNECT_PROTOCOL_VERSION),
/* harmony export */   TONCONNECT_WALLET_JSBRIDGE_KEY: () => (/* binding */ TONCONNECT_WALLET_JSBRIDGE_KEY),
/* harmony export */   TONHTTPAPI_MAINNET_API_KEY: () => (/* binding */ TONHTTPAPI_MAINNET_API_KEY),
/* harmony export */   TONHTTPAPI_MAINNET_URL: () => (/* binding */ TONHTTPAPI_MAINNET_URL),
/* harmony export */   TONHTTPAPI_TESTNET_API_KEY: () => (/* binding */ TONHTTPAPI_TESTNET_API_KEY),
/* harmony export */   TONHTTPAPI_TESTNET_URL: () => (/* binding */ TONHTTPAPI_TESTNET_URL),
/* harmony export */   TONHTTPAPI_V3_MAINNET_API_URL: () => (/* binding */ TONHTTPAPI_V3_MAINNET_API_URL),
/* harmony export */   TONHTTPAPI_V3_TESTNET_API_URL: () => (/* binding */ TONHTTPAPI_V3_TESTNET_API_URL),
/* harmony export */   TON_BLOCKCHAIN: () => (/* binding */ TON_BLOCKCHAIN),
/* harmony export */   TON_DNS_COLLECTION: () => (/* binding */ TON_DNS_COLLECTION),
/* harmony export */   TON_EXPLORER_BASE_MAINNET_URL: () => (/* binding */ TON_EXPLORER_BASE_MAINNET_URL),
/* harmony export */   TON_EXPLORER_BASE_TESTNET_URL: () => (/* binding */ TON_EXPLORER_BASE_TESTNET_URL),
/* harmony export */   TON_EXPLORER_NAME: () => (/* binding */ TON_EXPLORER_NAME),
/* harmony export */   TON_SYMBOL: () => (/* binding */ TON_SYMBOL),
/* harmony export */   VALIDATION_PERIOD_MS: () => (/* binding */ VALIDATION_PERIOD_MS),
/* harmony export */   WHOLE_PART_DELIMITER: () => (/* binding */ WHOLE_PART_DELIMITER),
/* harmony export */   WINDOW_PROVIDER_CHANNEL: () => (/* binding */ WINDOW_PROVIDER_CHANNEL)
/* harmony export */ });
const APP_ENV = process.env.APP_ENV;
const APP_NAME = process.env.APP_NAME || 'MyTonWallet';
const APP_VERSION = process.env.APP_VERSION;
const APP_ENV_MARKER = APP_ENV === 'staging' ? 'Beta' : APP_ENV === 'development' ? 'Dev' : undefined;
const DEBUG = APP_ENV !== 'production' && APP_ENV !== 'perf' && APP_ENV !== 'test';
const DEBUG_MORE = false;
const IS_PRODUCTION = APP_ENV === 'production';
const IS_TEST = APP_ENV === 'test';
const IS_PERF = APP_ENV === 'perf';
const IS_EXTENSION = process.env.IS_EXTENSION === '1';
const IS_FIREFOX_EXTENSION = process.env.IS_FIREFOX_EXTENSION === '1';
const IS_PACKAGED_ELECTRON = process.env.IS_PACKAGED_ELECTRON === '1';
const IS_CAPACITOR = process.env.IS_CAPACITOR === '1';
const IS_ANDROID_DIRECT = process.env.IS_ANDROID_DIRECT === '1';
const ELECTRON_HOST_URL = 'https://dumb-host';
const INACTIVE_MARKER = '[Inactive]';
const PRODUCTION_URL = 'https://mytonwallet.app';
const BETA_URL = 'https://beta.mytonwallet.app';
const APP_REPO_URL = 'https://github.com/mytonwalletorg/mytonwallet';
const BASE_URL = process.env.BASE_URL;
const BOT_USERNAME = process.env.BOT_USERNAME || 'MyTonWalletBot';
const SWAP_FEE_ADDRESS = process.env.SWAP_FEE_ADDRESS || 'UQDUkQbpTVIgt7v66-JTFR-3-eXRFz_4V66F-Ufn6vOg0GOp';
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
const TONHTTPAPI_MAINNET_URL = process.env.TONHTTPAPI_MAINNET_URL
    || 'https://tonhttpapi.mytonwallet.org/api/v2/jsonRPC';
const TONHTTPAPI_MAINNET_API_KEY = process.env.TONHTTPAPI_MAINNET_API_KEY;
const ELECTRON_TONHTTPAPI_MAINNET_API_KEY = process.env.ELECTRON_TONHTTPAPI_MAINNET_API_KEY;
const TONHTTPAPI_V3_MAINNET_API_URL = process.env.TONHTTPAPI_V3_MAINNET_API_KEY
    || 'https://tonhttpapi-v3.mytonwallet.org/api/v3';
const TONAPIIO_MAINNET_URL = process.env.TONAPIIO_MAINNET_URL || 'https://tonapiio.mytonwallet.org';
const TONHTTPAPI_TESTNET_URL = process.env.TONHTTPAPI_TESTNET_URL
    || 'https://tonhttpapi-testnet.mytonwallet.org/api/v2/jsonRPC';
const TONHTTPAPI_TESTNET_API_KEY = process.env.TONHTTPAPI_TESTNET_API_KEY;
const ELECTRON_TONHTTPAPI_TESTNET_API_KEY = process.env.ELECTRON_TONHTTPAPI_TESTNET_API_KEY;
const TONHTTPAPI_V3_TESTNET_API_URL = process.env.TONHTTPAPI_V3_TESTNET_API_KEY
    || 'https://tonhttpapi-v3-testnet.mytonwallet.org/api/v3';
const TONAPIIO_TESTNET_URL = process.env.TONAPIIO_TESTNET_URL || 'https://tonapiio-testnet.mytonwallet.org';
const BRILLIANT_API_BASE_URL = process.env.BRILLIANT_API_BASE_URL || 'https://api.mytonwallet.org';
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
const PROXY_HOSTS = process.env.PROXY_HOSTS;
const TINY_TRANSFER_MAX_COST = 0.01;
const LANG_CACHE_NAME = 'mtw-lang-125';
const LANG_LIST = [{
        langCode: 'en',
        name: 'English',
        nativeName: 'English',
        rtl: false,
    }, {
        langCode: 'es',
        name: 'Spanish',
        nativeName: 'Español',
        rtl: false,
    }, {
        langCode: 'ru',
        name: 'Russian',
        nativeName: 'Русский',
        rtl: false,
    }, {
        langCode: 'zh-Hans',
        name: 'Chinese (Simplified)',
        nativeName: '简体',
        rtl: false,
    }, {
        langCode: 'zh-Hant',
        name: 'Chinese (Traditional)',
        nativeName: '繁體',
        rtl: false,
    }, {
        langCode: 'tr',
        name: 'Turkish',
        nativeName: 'Türkçe',
        rtl: false,
    }, {
        langCode: 'de',
        name: 'German',
        nativeName: 'Deutsch',
        rtl: false,
    }, {
        langCode: 'th',
        name: 'Thai',
        nativeName: 'ไทย',
        rtl: false,
    }, {
        langCode: 'uk',
        name: 'Ukrainian',
        nativeName: 'Українська',
        rtl: false,
    }, {
        langCode: 'pl',
        name: 'Polish',
        nativeName: 'Polski',
        rtl: false,
    }];
const STAKING_CYCLE_DURATION_MS = 131072000; // 36.4 hours
const VALIDATION_PERIOD_MS = 65536000; // 18.2 h.
const ONE_TON = 1000000000n;
const MIN_BALANCE_FOR_UNSTAKE = 1020000000n; // 1.02 TON
const STAKING_FORWARD_AMOUNT = ONE_TON;
const DEFAULT_FEE = 15000000n; // 0.015 TON
const STAKING_POOLS = process.env.STAKING_POOLS ? process.env.STAKING_POOLS.split(' ') : [];
const LIQUID_POOL = process.env.LIQUID_POOL || 'EQD2_4d91M4TVbEBVyBF8J1UwpMJc361LKVCz6bBlffMW05o';
const LIQUID_JETTON = process.env.LIQUID_JETTON || 'EQCqC6EhRJ_tpWngKxL6dV0k6DSnRUrs9GSVkLbfdCqsj6TE';
const STAKING_MIN_AMOUNT = ONE_TON;
const NOMINATORS_STAKING_MIN_AMOUNT = ONE_TON * 10001n;
const TONCONNECT_PROTOCOL_VERSION = 2;
const TONCONNECT_WALLET_JSBRIDGE_KEY = 'mytonwallet';
const NFT_FRAGMENT_COLLECTIONS = new Set([
    '0:0e41dc1dc3c9067ed24248580e12b3359818d83dee0304fabcf80845eafafdb2', // Anonymous Telegram Numbers
    '0:80d78a35f955a14b679faa887ff4cd5bfc0f43b4a4eea2a7e6927f3701b273c2', // Telegram Usernames
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
            percentChange24h: 0,
        },
        decimals: DEFAULT_DECIMAL_PLACES,
    },
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
        isPopular: true,
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
        isPopular: true,
    },
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
    CNY: '¥',
};
const CURRENCY_LIST = [
    {
        value: 'USD',
        name: 'US Dollar',
    }, {
        value: 'EUR',
        name: 'Euro',
    }, {
        value: 'RUB',
        name: 'Ruble',
    }, {
        value: 'CNY',
        name: 'Yuan',
    }, {
        value: 'BTC',
        name: 'Bitcoin',
    }, {
        value: TON_SYMBOL,
        name: 'Toncoin',
    },
];
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
const NOTCOIN_EXCHANGERS = [
    'EQAPZauWVPUcm2hUJT9n36pxznEhl46rEn1bzBXN0RY_yiy2',
    'EQASgm0Qv3h2H2mF0W06ikPqYq2ctT3dyXMJH_svbEKKB3iZ',
    'EQArlmP-RhVIG2yAFGZyPZfM3m0YccxmpvoRi6sgRzWnAA0s',
    'EQA6pL-spYqZp1Ck6o3rpY45Cl-bvLMW_j3qdVejOkUWpLnm',
    'EQBJ_ehYjumQKbXfWUue1KHKXdTm1GuYJB0Fj2ST_DwORvpd',
    'EQBRmYSjxh9xlZpUqEmGjF5UjukI9v_Cm2kCTu4CoBn3XkOD',
    'EQBkiqncd7AFT5_23H-RoA2Vynk-Nzq_dLoeMVRthAU9RF0p',
    'EQB_OzTHXbztABe0QHgr4PtAV8T64LR6aDunXgaAoihOdxwO',
    'EQCL-x5kLg6tKVNGryItTuj6tG3FH5mhUEu0xRqQc-kbEmbe',
    'EQCZh2yJ46RaQH3AYmjEA8SMMXi77Oein4-3lvqkHseIAhD-',
    'EQChKo5IK3iNqUHUGDB9gtzjCjMTPtmsFqekuCA2MdreVEyu',
    'EQC6DNCBv076TIliRMfOt20RpbS7rNKDfSky3WrFEapFt8AH',
    'EQDE_XFZOYae_rl3ZMsgBCtRSmYhl8B4y2BZEP7oiGBDhlgy',
    'EQDddqpGA2ePXQF47A2DSL3GF6ZzIVmimfM2d16cdymy2noT',
    'EQDv0hNNAamhYltCh3pTJrq3oRB9RW2ZhEYkTP6fhj5BtZNu',
    'EQD2mP7zgO7-imUJhqYry3i07aJ_SR53DaokMupfAAobt0Xw',
];
const CLAIM_ADDRESS = 'EQB3zOTvPi1PmwdcTpqSfFKZnhi1GNKEVJM-LdoAirdLtash';
const CLAIM_AMOUNT = 30000000n; // 0.03 TON
const CLAIM_COMMENT = 'claim';
// eslint-disable-next-line max-len
const RE_LINK_TEMPLATE = /((ftp|https?):\/\/)?(?<host>(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\.[a-zA-Z][-a-zA-Z0-9]{1,62})\b([-a-zA-Z0-9()@:%_+.,~#?&/=]*)/g;
// eslint-disable-next-line max-len
const RE_TG_BOT_MENTION = /telegram[:\s-]*((@[a-z0-9_]+)|(https:\/\/)?(t\.me|telegram\.me|telegram\.dog)\/[a-z0-9_]+)/mig;
const DIESEL_ADDRESS = process.env.DIESEL_ADDRESS || 'EQDUkQbpTVIgt7v66-JTFR-3-eXRFz_4V66F-Ufn6vOg0D5s';
const DIESEL_TOKENS = new Set([
    'ton-eqcxe6mutq', // USDT
    'ton-eqavlwfdxg', // Notcoin
]);


/***/ }),
/* 3 */
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   createWindowProvider: () => (/* binding */ createWindowProvider)
/* harmony export */ });
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(2);
/* harmony import */ var _createPostMessageInterface__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(4);
/* harmony import */ var _methods__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(11);



function createWindowProvider(worker) {
    (0,_createPostMessageInterface__WEBPACK_IMPORTED_MODULE_1__.createPostMessageInterface)((name, origin, ...args) => {
        const method = _methods__WEBPACK_IMPORTED_MODULE_2__[name];
        // @ts-ignore
        return method(...args);
    }, _config__WEBPACK_IMPORTED_MODULE_0__.WINDOW_PROVIDER_CHANNEL, worker, true);
}


/***/ }),
/* 4 */
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   createExtensionInterface: () => (/* binding */ createExtensionInterface),
/* harmony export */   createPostMessageInterface: () => (/* binding */ createPostMessageInterface)
/* harmony export */ });
/* harmony import */ var _ledger_tab__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(5);
/* harmony import */ var _bigint__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(6);
/* harmony import */ var _logs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(10);



const callbackState = new Map();
function createPostMessageInterface(api, channel, target = self, shouldIgnoreErrors) {
    function sendToOrigin(data, transferables) {
        data.channel = channel;
        if (transferables) {
            target.postMessage(data, transferables);
        }
        else {
            target.postMessage(data);
        }
    }
    if (!shouldIgnoreErrors) {
        handleErrors(sendToOrigin);
    }
    target.onmessage = (message) => {
        if (message.data?.channel === channel) {
            onMessage(api, message.data, sendToOrigin);
        }
    };
}
function createExtensionInterface(portName, api, channel, cleanUpdater, withAutoInit = false) {
    chrome.runtime.onConnect.addListener((port) => {
        if (port.name !== portName) {
            return;
        }
        /**
         * If the sender's URL includes the DETACHED_TAB_URL, we skip further processing
         * This condition ensures that we don't interact with tabs that have already been closed.
         */
        const url = port.sender?.url;
        if (url?.includes(_ledger_tab__WEBPACK_IMPORTED_MODULE_0__.DETACHED_TAB_URL)) {
            return;
        }
        const origin = url ? new URL(url).origin : undefined;
        const dAppUpdater = (update) => {
            sendToOrigin({
                type: 'update',
                update,
            });
        };
        function sendToOrigin(data) {
            data.channel = channel;
            const json = JSON.stringify(data);
            port.postMessage(json);
        }
        handleErrors(sendToOrigin);
        port.onMessage.addListener((data) => {
            if (typeof data === 'string') {
                data = JSON.parse(data, _bigint__WEBPACK_IMPORTED_MODULE_1__.bigintReviver);
            }
            if (data.channel === channel) {
                onMessage(api, data, sendToOrigin, dAppUpdater, origin);
            }
        });
        port.onDisconnect.addListener(() => {
            cleanUpdater?.(dAppUpdater);
        });
        if (withAutoInit) {
            onMessage(api, { type: 'init', name: 'init', args: [] }, sendToOrigin, dAppUpdater);
        }
    });
}
async function onMessage(api, data, sendToOrigin, onUpdate, origin) {
    if (!onUpdate) {
        onUpdate = (update) => {
            sendToOrigin({
                type: 'update',
                update,
            });
        };
    }
    switch (data.type) {
        case 'init': {
            const { args } = data;
            const promise = typeof api === 'function'
                ? api('init', origin, onUpdate, ...args)
                : api.init?.(onUpdate, ...args);
            await promise;
            break;
        }
        case 'callMethod': {
            const { messageId, name, args, withCallback, } = data;
            try {
                if (messageId && withCallback) {
                    const callback = (...callbackArgs) => {
                        const lastArg = callbackArgs[callbackArgs.length - 1];
                        sendToOrigin({
                            type: 'methodCallback',
                            messageId,
                            callbackArgs,
                        }, isTransferable(lastArg) ? [lastArg] : undefined);
                    };
                    callbackState.set(messageId, callback);
                    args.push(callback);
                }
                const response = typeof api === 'function'
                    ? await api(name, origin, ...args)
                    : await api[name](...args);
                const { arrayBuffer } = (typeof response === 'object' && 'arrayBuffer' in response && response) || {};
                if (messageId) {
                    sendToOrigin({
                        type: 'methodResponse',
                        messageId,
                        response,
                    }, arrayBuffer ? [arrayBuffer] : undefined);
                }
            }
            catch (err) {
                (0,_logs__WEBPACK_IMPORTED_MODULE_2__.logDebugError)(name, err);
                if (messageId) {
                    sendToOrigin({
                        type: 'methodResponse',
                        messageId,
                        error: { message: err.message },
                    });
                }
            }
            if (messageId) {
                callbackState.delete(messageId);
            }
            break;
        }
        case 'cancelProgress': {
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
    self.onerror = (e) => {
        // eslint-disable-next-line no-console
        console.error(e);
        sendToOrigin({ type: 'unhandledError', error: { message: e.error?.message || 'Uncaught exception in worker' } });
    };
    self.addEventListener('unhandledrejection', (e) => {
        // eslint-disable-next-line no-console
        console.error(e);
        sendToOrigin({ type: 'unhandledError', error: { message: e.reason?.message || 'Uncaught rejection in worker' } });
    });
}


/***/ }),
/* 5 */
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   DETACHED_TAB_URL: () => (/* binding */ DETACHED_TAB_URL),
/* harmony export */   closeLedgerTab: () => (/* binding */ closeLedgerTab),
/* harmony export */   onLedgerTabClose: () => (/* binding */ onLedgerTabClose),
/* harmony export */   openLedgerTab: () => (/* binding */ openLedgerTab)
/* harmony export */ });
const DETACHED_TAB_URL = '#detached';
let ledgerTabId;
function openLedgerTab() {
    return createLedgerTab();
}
async function closeLedgerTab() {
    if (!ledgerTabId)
        return;
    await chrome.tabs.query({ active: true }, () => {
        if (!ledgerTabId)
            return;
        chrome.tabs.remove(ledgerTabId);
    });
}
function onLedgerTabClose(id, onClose) {
    chrome.tabs.onRemoved.addListener((closedTabId) => {
        if (closedTabId !== id) {
            return;
        }
        ledgerTabId = undefined;
        onClose();
    });
}
async function createLedgerTab() {
    const tab = await chrome.tabs.create({ url: `index.html${DETACHED_TAB_URL}`, active: true });
    await chrome.windows.update(tab.windowId, { focused: true });
    ledgerTabId = tab.id;
    return ledgerTabId;
}


/***/ }),
/* 6 */
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   BIGINT_PREFIX: () => (/* binding */ BIGINT_PREFIX),
/* harmony export */   bigintAbs: () => (/* binding */ bigintAbs),
/* harmony export */   bigintCountBits: () => (/* binding */ bigintCountBits),
/* harmony export */   bigintDivideToNumber: () => (/* binding */ bigintDivideToNumber),
/* harmony export */   bigintMultiplyToNumber: () => (/* binding */ bigintMultiplyToNumber),
/* harmony export */   bigintRandom: () => (/* binding */ bigintRandom),
/* harmony export */   bigintReviver: () => (/* binding */ bigintReviver)
/* harmony export */ });
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(2);
/* harmony import */ var _decimals__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(7);
/* harmony import */ var _random__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(9);



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
    return (value * _config__WEBPACK_IMPORTED_MODULE_0__.ONE_TON) / (0,_decimals__WEBPACK_IMPORTED_MODULE_1__.fromDecimal)(num);
}
function bigintMultiplyToNumber(value, num) {
    return (value * (0,_decimals__WEBPACK_IMPORTED_MODULE_1__.fromDecimal)(num)) / _config__WEBPACK_IMPORTED_MODULE_0__.ONE_TON;
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
/* 7 */
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   fromDecimal: () => (/* binding */ fromDecimal),
/* harmony export */   getIsPositiveDecimal: () => (/* binding */ getIsPositiveDecimal),
/* harmony export */   roundDecimal: () => (/* binding */ roundDecimal),
/* harmony export */   toBig: () => (/* binding */ toBig),
/* harmony export */   toDecimal: () => (/* binding */ toDecimal)
/* harmony export */ });
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(2);
/* harmony import */ var _lib_big_js__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(8);


_lib_big_js__WEBPACK_IMPORTED_MODULE_1__.Big.RM = 0; // RoundDown
_lib_big_js__WEBPACK_IMPORTED_MODULE_1__.Big.NE = -100000; // Disable exponential form
_lib_big_js__WEBPACK_IMPORTED_MODULE_1__.Big.PE = 100000; // Disable exponential form
const ten = (0,_lib_big_js__WEBPACK_IMPORTED_MODULE_1__.Big)(10);
function fromDecimal(value, decimals) {
    return BigInt((0,_lib_big_js__WEBPACK_IMPORTED_MODULE_1__.Big)(value).mul(ten.pow(decimals ?? _config__WEBPACK_IMPORTED_MODULE_0__.DEFAULT_DECIMAL_PLACES)).round().toString());
}
function toDecimal(value, decimals, noFloor = false) {
    return toBig(value, decimals ?? _config__WEBPACK_IMPORTED_MODULE_0__.DEFAULT_DECIMAL_PLACES, noFloor).toString();
}
function toBig(value, decimals = _config__WEBPACK_IMPORTED_MODULE_0__.DEFAULT_DECIMAL_PLACES, noFloor = false) {
    return (0,_lib_big_js__WEBPACK_IMPORTED_MODULE_1__.Big)(value.toString()).div(ten.pow(decimals)).round(decimals, noFloor ? _lib_big_js__WEBPACK_IMPORTED_MODULE_1__.Big.roundHalfUp : undefined);
}
function roundDecimal(value, decimals) {
    return (0,_lib_big_js__WEBPACK_IMPORTED_MODULE_1__.Big)(value).round(decimals).toString();
}
function getIsPositiveDecimal(value) {
    return !value.startsWith('-');
}


/***/ }),
/* 8 */
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   Big: () => (/* binding */ Big),
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
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
var DP = 20,          // 0 to MAX_DP

  /*
   * The rounding mode (RM) used when rounding to the above decimal places.
   *
   *  0  Towards zero (i.e. truncate, no rounding).       (ROUND_DOWN)
   *  1  To nearest neighbour. If equidistant, round up.  (ROUND_HALF_UP)
   *  2  To nearest neighbour. If equidistant, to even.   (ROUND_HALF_EVEN)
   *  3  Away from zero.                                  (ROUND_UP)
   */
  RM = 1,             // 0, 1, 2 or 3

  // The maximum value of DP and Big.DP.
  MAX_DP = 1E6,       // 0 to 1000000

  // The maximum magnitude of the exponent argument to the pow method.
  MAX_POWER = 1E6,    // 1 to 1000000

  /*
   * The negative exponent (NE) at and beneath which toString returns exponential notation.
   * (JavaScript numbers: -7)
   * -1000000 is the minimum recommended exponent value of a Big.
   */
  NE = -7,            // 0 to -1000000

  /*
   * The positive exponent (PE) at and above which toString returns exponential notation.
   * (JavaScript numbers: 21)
   * 1000000 is the maximum recommended exponent value of a Big, but this limit is not enforced.
   */
  PE = 21,            // 0 to 1000000

  /*
   * When true, an error will be thrown if a primitive number is passed to the Big constructor,
   * or if valueOf is called, or if toNumber is called on a Big which cannot be converted to a
   * primitive number without a loss of precision.
   */
  STRICT = false,     // true or false


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
    more =
      rm === 3 && (more || !!xc[0]) || sd === 0 && (
      rm === 1 && xc[0] >= 5 ||
      rm === 2 && (xc[0] > 5 || xc[0] === 5 && (more || xc[1] !== UNDEFINED))
    );

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
    more =
      rm === 1 && xc[sd] >= 5 ||
      rm === 2 && (xc[sd] > 5 || xc[sd] === 5 &&
        (more || xc[sd + 1] !== UNDEFINED || xc[sd - 1] & 1)) ||
      rm === 3 && (more || !!xc[0]);

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
    a = x.c,                  // dividend
    b = (y = new Big(y)).c,   // divisor
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

  var bl, bt, n, cmp, ri,
    bz = b.slice(),
    ai = bl = b.length,
    al = a.length,
    r = a.slice(0, bl),   // remainder
    rl = r.length,
    q = y,                // quotient
    qc = q.c = [],
    qi = 0,
    p = dp + (q.e = x.e - y.e) + 1;    // precision of the result

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
    if (r[0] && cmp) r[rl] = a[ai] || 0;
    else r = [a[ai]];

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
  var i, j, t, xlty,
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
  var e, k, t,
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
  if (dp === UNDEFINED) dp = 0;
  else if (dp !== ~~dp || dp < -MAX_DP || dp > MAX_DP) {
    throw Error(INVALID_DP);
  }
  return round(new this.constructor(this), dp + this.e + 1, rm);
};


/*
 * Return a new Big whose value is the square root of the value of this Big, rounded, if
 * necessary, to a maximum of Big.DP decimal places using rounding mode Big.RM.
 */
P.sqrt = function () {
  var r, c, t,
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
  if (b) ++y.e;
  else c.shift();

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
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (Big);


/***/ }),
/* 9 */
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   random: () => (/* binding */ random),
/* harmony export */   randomBytes: () => (/* binding */ randomBytes),
/* harmony export */   sample: () => (/* binding */ sample)
/* harmony export */ });
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
/* 10 */
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   logDebug: () => (/* binding */ logDebug),
/* harmony export */   logDebugError: () => (/* binding */ logDebugError)
/* harmony export */ });
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(2);

function logDebugError(message, ...args) {
    if (_config__WEBPACK_IMPORTED_MODULE_0__.DEBUG) {
        // eslint-disable-next-line no-console
        console.error(`[DEBUG][${message}]`, ...args);
    }
}
function logDebug(message, ...args) {
    if (_config__WEBPACK_IMPORTED_MODULE_0__.DEBUG) {
        // eslint-disable-next-line no-console
        console.log(`[DEBUG] ${message}`, ...args);
    }
}


/***/ }),
/* 11 */
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
const promise = new Promise((resolve) => {
    resolvePromise = resolve;
});
async function init() {
    if (SecureStoragePlugin)
        return;
    ({ SecureStoragePlugin } = await __webpack_require__.e(/* import() | capacitorSecureStorage */ 0).then(__webpack_require__.bind(__webpack_require__, 312)));
    resolvePromise();
}
async function getItem(key) {
    await promise;
    return (await SecureStoragePlugin.get({ key }).catch((err) => {
        const message = typeof err === 'string' ? err : err.message;
        if (message.includes('key does not exist')) {
            return undefined;
        }
        else {
            throw err;
        }
    }))?.value;
}
async function setItem(key, value) {
    await promise;
    return SecureStoragePlugin.set({ key, value });
}
async function removeItem(key) {
    await promise;
    return SecureStoragePlugin.remove({ key });
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
/* 12 */
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   createConnector: () => (/* binding */ createConnector),
/* harmony export */   createExtensionConnector: () => (/* binding */ createExtensionConnector)
/* harmony export */ });
/* harmony import */ var _bigint__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(6);
/* harmony import */ var _generateUniqueId__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(13);
/* harmony import */ var _logs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(10);



class ConnectorClass {
    target;
    onUpdate;
    channel;
    shouldUseJson;
    targetOrigin;
    requestStates = new Map();
    requestStatesByCallback = new Map();
    constructor(target, onUpdate, channel, shouldUseJson, targetOrigin = '*') {
        this.target = target;
        this.onUpdate = onUpdate;
        this.channel = channel;
        this.shouldUseJson = shouldUseJson;
        this.targetOrigin = targetOrigin;
    }
    // eslint-disable-next-line class-methods-use-this
    destroy() {
    }
    init(...args) {
        this.postMessage({
            type: 'init',
            args,
        });
    }
    request(messageData) {
        const { requestStates, requestStatesByCallback } = this;
        const messageId = (0,_generateUniqueId__WEBPACK_IMPORTED_MODULE_1__["default"])();
        const payload = {
            type: 'callMethod',
            messageId,
            ...messageData,
        };
        const requestState = { messageId };
        // Re-wrap type because of `postMessage`
        const promise = new Promise((resolve, reject) => {
            Object.assign(requestState, { resolve, reject });
        });
        if (typeof payload.args[payload.args.length - 1] === 'function') {
            payload.withCallback = true;
            const callback = payload.args.pop();
            requestState.callback = callback;
            requestStatesByCallback.set(callback, requestState);
        }
        requestStates.set(messageId, requestState);
        promise
            .catch(() => undefined)
            .finally(() => {
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
        const { messageId } = this.requestStatesByCallback.get(progressCallback) || {};
        if (!messageId) {
            return;
        }
        this.postMessage({
            type: 'cancelProgress',
            messageId,
        });
    }
    onMessage(data) {
        if (typeof data === 'string') {
            try {
                data = JSON.parse(data, _bigint__WEBPACK_IMPORTED_MODULE_0__.bigintReviver);
            }
            catch (err) {
                (0,_logs__WEBPACK_IMPORTED_MODULE_2__.logDebugError)('PostMessageConnector: Failed to parse message', err);
                return;
            }
        }
        const { requestStates, channel } = this;
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
                }
                else {
                    requestState.resolve(data.response);
                }
            }
        }
        else if (data.type === 'methodCallback') {
            const requestState = requestStates.get(data.messageId);
            requestState?.callback?.(...data.callbackArgs);
        }
        else if (data.type === 'unhandledError') {
            throw new Error(data.error?.message);
        }
    }
    postMessage(data) {
        data.channel = this.channel;
        let rawData = data;
        if (this.shouldUseJson) {
            rawData = JSON.stringify(data);
        }
        if ('open' in this.target) { // Is Window
            this.target.postMessage(rawData, this.targetOrigin);
        }
        else {
            this.target.postMessage(rawData);
        }
    }
}
function createConnector(worker, onUpdate, channel, targetOrigin) {
    const connector = new ConnectorClass(worker, onUpdate, channel, undefined, targetOrigin);
    function handleMessage({ data }) {
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
        const port = self.chrome.runtime.connect({ name });
        port.onMessage.addListener((data) => {
            connector.onMessage(data);
        });
        // For some reason port can suddenly get disconnected
        port.onDisconnect.addListener(() => {
            connector.target = connect();
            connector.init(getInitArgs?.());
        });
        return port;
    }
    connector.init(getInitArgs?.());
    return connector;
}


/***/ }),
/* 13 */
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (/* binding */ generateUniqueId)
/* harmony export */ });
function generateUniqueId() {
    return Date.now().toString(36) + Math.random().toString(36).slice(2);
}


/***/ }),
/* 14 */
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   debounce: () => (/* binding */ debounce),
/* harmony export */   fastRaf: () => (/* binding */ fastRaf),
/* harmony export */   onBeforeUnload: () => (/* binding */ onBeforeUnload),
/* harmony export */   onIdle: () => (/* binding */ onIdle),
/* harmony export */   onTickEnd: () => (/* binding */ onTickEnd),
/* harmony export */   pause: () => (/* binding */ pause),
/* harmony export */   rafPromise: () => (/* binding */ rafPromise),
/* harmony export */   throttle: () => (/* binding */ throttle),
/* harmony export */   throttleWith: () => (/* binding */ throttleWith),
/* harmony export */   throttleWithTickEnd: () => (/* binding */ throttleWithTickEnd),
/* harmony export */   waitFor: () => (/* binding */ waitFor)
/* harmony export */ });
function debounce(fn, ms, shouldRunFirst = true, shouldRunLast = true) {
    let waitingTimeout;
    return (...args) => {
        if (waitingTimeout) {
            clearTimeout(waitingTimeout);
            waitingTimeout = undefined;
        }
        else if (shouldRunFirst) {
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
function throttle(fn, ms, shouldRunFirst = true) {
    let interval;
    let isPending;
    let args;
    return (..._args) => {
        isPending = true;
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
    return (..._args) => {
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
        self.requestIdleCallback(cb, { timeout });
    }
    else {
        onTickEnd(cb);
    }
}
const pause = (ms) => new Promise((resolve) => {
    setTimeout(() => resolve(), ms);
});
function rafPromise() {
    return new Promise((resolve) => {
        fastRaf(resolve);
    });
}
const FAST_RAF_TIMEOUT_FALLBACK_MS = 35; // < 30 FPS
let fastRafCallbacks;
let fastRafFallbackCallbacks;
let fastRafFallbackTimeout;
// May result in an immediate execution if called from another RAF callback which was scheduled
// (and therefore is executed) earlier than RAF callback scheduled by `fastRaf`
function fastRaf(callback, withTimeoutFallback = false) {
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
            currentCallbacks.forEach((cb) => cb());
        });
    }
    else {
        fastRafCallbacks.add(callback);
    }
    if (withTimeoutFallback) {
        if (!fastRafFallbackCallbacks) {
            fastRafFallbackCallbacks = new Set([callback]);
        }
        else {
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
                currentTimeoutCallbacks.forEach((cb) => cb());
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
            currentCallbacks.forEach((cb) => cb());
        });
    }
    else {
        onTickEndCallbacks.push(callback);
    }
}
let beforeUnloadCallbacks;
function onBeforeUnload(callback, isLast = false) {
    if (!beforeUnloadCallbacks) {
        beforeUnloadCallbacks = [];
        // eslint-disable-next-line no-restricted-globals
        self.addEventListener('beforeunload', () => {
            beforeUnloadCallbacks.forEach((cb) => cb());
        });
    }
    if (isLast) {
        beforeUnloadCallbacks.push(callback);
    }
    else {
        beforeUnloadCallbacks.unshift(callback);
    }
    return () => {
        beforeUnloadCallbacks = beforeUnloadCallbacks.filter((cb) => cb !== callback);
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
/* 15 */
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   DEFAULT_LANG_CODE: () => (/* binding */ DEFAULT_LANG_CODE),
/* harmony export */   DPR: () => (/* binding */ DPR),
/* harmony export */   IS_ANDROID: () => (/* binding */ IS_ANDROID),
/* harmony export */   IS_ANDROID_APP: () => (/* binding */ IS_ANDROID_APP),
/* harmony export */   IS_BIOMETRIC_AUTH_SUPPORTED: () => (/* binding */ IS_BIOMETRIC_AUTH_SUPPORTED),
/* harmony export */   IS_CHROME_EXTENSION: () => (/* binding */ IS_CHROME_EXTENSION),
/* harmony export */   IS_DAPP_SUPPORTED: () => (/* binding */ IS_DAPP_SUPPORTED),
/* harmony export */   IS_DELEGATED_BOTTOM_SHEET: () => (/* binding */ IS_DELEGATED_BOTTOM_SHEET),
/* harmony export */   IS_DELEGATING_BOTTOM_SHEET: () => (/* binding */ IS_DELEGATING_BOTTOM_SHEET),
/* harmony export */   IS_ELECTRON: () => (/* binding */ IS_ELECTRON),
/* harmony export */   IS_FIREFOX: () => (/* binding */ IS_FIREFOX),
/* harmony export */   IS_IOS: () => (/* binding */ IS_IOS),
/* harmony export */   IS_IOS_APP: () => (/* binding */ IS_IOS_APP),
/* harmony export */   IS_LEDGER_EXTENSION_TAB: () => (/* binding */ IS_LEDGER_EXTENSION_TAB),
/* harmony export */   IS_LEDGER_SUPPORTED: () => (/* binding */ IS_LEDGER_SUPPORTED),
/* harmony export */   IS_LINUX: () => (/* binding */ IS_LINUX),
/* harmony export */   IS_MAC_OS: () => (/* binding */ IS_MAC_OS),
/* harmony export */   IS_MULTITAB_SUPPORTED: () => (/* binding */ IS_MULTITAB_SUPPORTED),
/* harmony export */   IS_OPERA: () => (/* binding */ IS_OPERA),
/* harmony export */   IS_PWA: () => (/* binding */ IS_PWA),
/* harmony export */   IS_SAFARI: () => (/* binding */ IS_SAFARI),
/* harmony export */   IS_TOUCH_ENV: () => (/* binding */ IS_TOUCH_ENV),
/* harmony export */   IS_WEB: () => (/* binding */ IS_WEB),
/* harmony export */   IS_WINDOWS: () => (/* binding */ IS_WINDOWS),
/* harmony export */   PLATFORM_ENV: () => (/* binding */ PLATFORM_ENV),
/* harmony export */   REM: () => (/* binding */ REM),
/* harmony export */   USER_AGENT_LANG_CODE: () => (/* binding */ USER_AGENT_LANG_CODE),
/* harmony export */   getPlatform: () => (/* binding */ getPlatform),
/* harmony export */   setScrollbarWidthProperty: () => (/* binding */ setScrollbarWidthProperty)
/* harmony export */ });
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(2);
/* harmony import */ var _lib_fasterdom_fasterdom__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(16);


function getPlatform() {
    const { userAgent, platform } = window.navigator;
    if (/Android/.test(userAgent))
        return 'Android';
    if (/Linux/.test(platform))
        return 'Linux';
    const iosPlatforms = ['iPhone', 'iPad', 'iPod'];
    if (iosPlatforms.indexOf(platform) !== -1
        // For new IPads with M1 chip and IPadOS platform returns "MacIntel"
        || (platform === 'MacIntel' && ('maxTouchPoints' in navigator && navigator.maxTouchPoints > 2))) {
        return 'iOS';
    }
    const macosPlatforms = ['Macintosh', 'MacIntel', 'MacPPC', 'Mac68K'];
    if (macosPlatforms.indexOf(platform) !== -1)
        return 'macOS';
    const windowsPlatforms = ['Win32', 'Win64', 'Windows', 'WinCE'];
    if (windowsPlatforms.indexOf(platform) !== -1)
        return 'Windows';
    return undefined;
}
function isIPad() {
    const { userAgent, platform } = window.navigator;
    return platform === 'iPad'
        || userAgent.includes('iPad')
        || (platform === 'MacIntel' && ('maxTouchPoints' in navigator && navigator.maxTouchPoints > 2));
}
function getBrowserLanguage() {
    const { language } = navigator;
    const lang = language.startsWith('zh')
        ? (language.endsWith('TW') || language.endsWith('HK') ? 'zh-Hant' : 'zh-Hans')
        : language.substring(0, 2);
    return (_config__WEBPACK_IMPORTED_MODULE_0__.LANG_LIST.some(({ langCode }) => langCode === lang) ? lang : 'en');
}
const IS_PWA = (window.matchMedia('(display-mode: standalone)').matches
    || window.navigator.standalone
    || document.referrer.includes('android-app://'));
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
const IS_CHROME_EXTENSION = Boolean(window.chrome?.system);
const IS_ELECTRON = Boolean(window.electron);
const IS_WEB = !_config__WEBPACK_IMPORTED_MODULE_0__.IS_CAPACITOR && !IS_ELECTRON && !_config__WEBPACK_IMPORTED_MODULE_0__.IS_EXTENSION;
const DEFAULT_LANG_CODE = 'en';
const USER_AGENT_LANG_CODE = getBrowserLanguage();
const DPR = window.devicePixelRatio || 1;
const IS_LEDGER_SUPPORTED = !(IS_IOS || (IS_ANDROID && _config__WEBPACK_IMPORTED_MODULE_0__.IS_CAPACITOR) || _config__WEBPACK_IMPORTED_MODULE_0__.IS_FIREFOX_EXTENSION);
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


/***/ }),
/* 16 */
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   disableStrict: () => (/* reexport safe */ _stricterdom__WEBPACK_IMPORTED_MODULE_2__.disableStrict),
/* harmony export */   enableStrict: () => (/* reexport safe */ _stricterdom__WEBPACK_IMPORTED_MODULE_2__.enableStrict),
/* harmony export */   forceMeasure: () => (/* reexport safe */ _stricterdom__WEBPACK_IMPORTED_MODULE_2__.forceMeasure),
/* harmony export */   forceMutation: () => (/* reexport safe */ _stricterdom__WEBPACK_IMPORTED_MODULE_2__.forceMutation),
/* harmony export */   getPhase: () => (/* reexport safe */ _stricterdom__WEBPACK_IMPORTED_MODULE_2__.getPhase),
/* harmony export */   requestForcedReflow: () => (/* binding */ requestForcedReflow),
/* harmony export */   requestMeasure: () => (/* binding */ requestMeasure),
/* harmony export */   requestMutation: () => (/* binding */ requestMutation),
/* harmony export */   requestNextMutation: () => (/* binding */ requestNextMutation),
/* harmony export */   setHandler: () => (/* reexport safe */ _stricterdom__WEBPACK_IMPORTED_MODULE_2__.setHandler),
/* harmony export */   setPhase: () => (/* reexport safe */ _stricterdom__WEBPACK_IMPORTED_MODULE_2__.setPhase)
/* harmony export */ });
/* harmony import */ var _util_safeExec__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(17);
/* harmony import */ var _util_schedulers__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(14);
/* harmony import */ var _stricterdom__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(19);



let pendingMeasureTasks = [];
let pendingMutationTasks = [];
let pendingForceReflowTasks = [];
const runUpdatePassOnRaf = throttleWithRafFallback(() => {
    const currentMeasureTasks = pendingMeasureTasks;
    pendingMeasureTasks = [];
    currentMeasureTasks.forEach((task) => {
        (0,_util_safeExec__WEBPACK_IMPORTED_MODULE_0__["default"])(task);
    });
    // We use promises to provide correct order for Mutation Observer callback microtasks
    Promise.resolve()
        .then(() => {
        (0,_stricterdom__WEBPACK_IMPORTED_MODULE_2__.setPhase)('mutate');
        const currentMutationTasks = pendingMutationTasks;
        pendingMutationTasks = [];
        currentMutationTasks.forEach((task) => {
            (0,_util_safeExec__WEBPACK_IMPORTED_MODULE_0__["default"])(task);
        });
    })
        .then(() => {
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
    })
        .then((pendingForceReflowMutationTasks) => {
        (0,_stricterdom__WEBPACK_IMPORTED_MODULE_2__.setPhase)('mutate');
        // Will include tasks created during the loop
        for (const task of pendingForceReflowMutationTasks) {
            (0,_util_safeExec__WEBPACK_IMPORTED_MODULE_0__["default"])(task);
        }
    })
        .then(() => {
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
    return (0,_util_schedulers__WEBPACK_IMPORTED_MODULE_1__.throttleWith)((throttledFn) => {
        (0,_util_schedulers__WEBPACK_IMPORTED_MODULE_1__.fastRaf)(throttledFn, true);
    }, fn);
}



/***/ }),
/* 17 */
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (/* binding */ safeExec)
/* harmony export */ });
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(2);
/* harmony import */ var _handleError__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(18);


const SAFE_EXEC_ENABLED = !_config__WEBPACK_IMPORTED_MODULE_0__.DEBUG_MORE;
function safeExec(cb, rescue, always) {
    if (!SAFE_EXEC_ENABLED) {
        return cb();
    }
    try {
        return cb();
    }
    catch (err) {
        rescue?.(err);
        (0,_handleError__WEBPACK_IMPORTED_MODULE_1__.handleError)(err);
        return undefined;
    }
    finally {
        always?.();
    }
}


/***/ }),
/* 18 */
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   handleError: () => (/* binding */ handleError)
/* harmony export */ });
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(2);
/* harmony import */ var _schedulers__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(14);


const noop = () => {
};
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
    handleError(e instanceof ErrorEvent ? (e.error || e.message) : e.reason);
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
        throttledAlert(`${_config__WEBPACK_IMPORTED_MODULE_0__.DEBUG_ALERT_MSG}\n\n${(message) || err}\n${stack}`);
    }
}


/***/ }),
/* 19 */
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   disableStrict: () => (/* binding */ disableStrict),
/* harmony export */   enableStrict: () => (/* binding */ enableStrict),
/* harmony export */   forceMeasure: () => (/* binding */ forceMeasure),
/* harmony export */   forceMutation: () => (/* binding */ forceMutation),
/* harmony export */   getPhase: () => (/* binding */ getPhase),
/* harmony export */   setHandler: () => (/* binding */ setHandler),
/* harmony export */   setPhase: () => (/* binding */ setPhase)
/* harmony export */ });
/* harmony import */ var _layoutCauses__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(20);

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
    if (isStrict)
        return;
    isStrict = true;
    setupLayoutDetectors();
    setupMutationObserver();
}
function disableStrict() {
    if (!isStrict)
        return;
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
            nodes.forEach((node) => {
                forcedMutationAllowedFor.add(node);
            });
        }
        else {
            forcedMutationAllowedFor.add(nodes);
        }
    }
    return cb();
}
function setHandler(handler) {
    onError = handler || DEFAULT_ERROR_HANDLER;
}
function setupLayoutDetectors() {
    Object.entries(_layoutCauses__WEBPACK_IMPORTED_MODULE_0__["default"]).forEach(([name, causes]) => {
        const entity = window[name];
        if (!entity)
            return;
        const prototype = typeof entity === 'object' ? entity : entity.prototype;
        if ('props' in causes) {
            causes.props.forEach((prop) => {
                const nativeGetter = Object.getOwnPropertyDescriptor(prototype, prop)?.get;
                if (!nativeGetter) {
                    return;
                }
                nativeMethods.set(`${name}#${prop}`, nativeGetter);
                Object.defineProperty(prototype, prop, {
                    get() {
                        onMeasure(prop);
                        return nativeGetter.call(this);
                    },
                });
            });
        }
        if ('methods' in causes) {
            causes.methods.forEach((method) => {
                const nativeMethod = prototype[method];
                nativeMethods.set(`${name}#${method}`, nativeMethod);
                // eslint-disable-next-line func-names
                prototype[method] = function (...args) {
                    onMeasure(method);
                    return nativeMethod.apply(this, args);
                };
            });
        }
    });
}
function clearLayoutDetectors() {
    Object.entries(_layoutCauses__WEBPACK_IMPORTED_MODULE_0__["default"]).forEach(([name, causes]) => {
        const entity = window[name];
        if (!entity)
            return;
        const prototype = typeof entity === 'object' ? entity : entity.prototype;
        if ('props' in causes) {
            causes.props.forEach((prop) => {
                const nativeGetter = nativeMethods.get(`${name}#${prop}`);
                if (!nativeGetter) {
                    return;
                }
                Object.defineProperty(prototype, prop, { get: nativeGetter });
            });
        }
        if ('methods' in causes) {
            causes.methods.forEach((method) => {
                prototype[method] = nativeMethods.get(`${name}#${method}`);
            });
        }
    });
    nativeMethods.clear();
}
function setupMutationObserver() {
    observer = new MutationObserver((mutations) => {
        if (phase !== 'mutate') {
            mutations.forEach(({ target, type, attributeName }) => {
                if (!document.contains(target)) {
                    return;
                }
                if (forcedMutationAllowedFor.has(target)) {
                    return;
                }
                if (type === 'childList' && target instanceof HTMLElement && target.contentEditable) {
                    return;
                }
                if (attributeName?.startsWith('data-')) {
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
        characterData: false,
    });
}
function clearMutationObserver() {
    observer?.disconnect();
    observer = undefined;
}
function onMeasure(propName) {
    if (phase !== 'measure') {
        onError(new Error(`Unexpected measurement detected: \`${propName}\``));
    }
}


/***/ }),
/* 20 */
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
// https://gist.github.com/paulirish/5d52fb081b3570c81e3a
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = ({
    Element: {
        props: [
            'clientLeft', 'clientTop', 'clientWidth', 'clientHeight',
            'scrollWidth', 'scrollHeight', 'scrollLeft', 'scrollTop',
        ],
        methods: [
            'getClientRects', 'getBoundingClientRect',
            'scrollBy', 'scrollTo', 'scrollIntoView', 'scrollIntoViewIfNeeded',
        ],
    },
    HTMLElement: {
        props: [
            'offsetLeft', 'offsetTop', 'offsetWidth', 'offsetHeight', 'offsetParent',
            'innerText',
        ],
        methods: ['focus'],
    },
    window: {
        props: [
            'scrollX', 'scrollY',
            'innerHeight', 'innerWidth',
        ],
        methods: ['getComputedStyle'],
    },
    VisualViewport: {
        props: [
            'height', 'width', 'offsetTop', 'offsetLeft',
        ],
    },
    Document: {
        props: ['scrollingElement'],
        methods: ['elementFromPoint'],
    },
    HTMLInputElement: {
        methods: ['select'],
    },
    MouseEvent: {
        props: ['layerX', 'layerY', 'offsetX', 'offsetY'],
    },
    Range: {
        methods: ['getClientRects', 'getBoundingClientRect'],
    },
});


/***/ })
/******/ 	]);
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
/******/ 			return "chunks/" + ({"0":"capacitorSecureStorage","2":"worker"}[chunkId] || chunkId) + ".js";
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
/******/ 		var dataWebpackPrefix = "mytonwallet:";
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
/******/ 		
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
/******/ 			if (document.currentScript && document.currentScript.tagName.toUpperCase() === 'SCRIPT')
/******/ 				scriptUrl = document.currentScript.src;
/******/ 			if (!scriptUrl) {
/******/ 				var scripts = document.getElementsByTagName("script");
/******/ 				if(scripts.length) {
/******/ 					var i = scripts.length - 1;
/******/ 					while (i > -1 && (!scriptUrl || !/^http(s?):/.test(scriptUrl))) scriptUrl = scripts[i--].src;
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
/******/ 			1: 0
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
/******/ 		var chunkLoadingGlobal = globalThis["webpackChunkmytonwallet"] = globalThis["webpackChunkmytonwallet"] || [];
/******/ 		chunkLoadingGlobal.forEach(webpackJsonpCallback.bind(null, 0));
/******/ 		chunkLoadingGlobal.push = webpackJsonpCallback.bind(null, chunkLoadingGlobal.push.bind(chunkLoadingGlobal));
/******/ 	})();
/******/ 	
/************************************************************************/
var __webpack_exports__ = {};
__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   callApi: () => (/* reexport safe */ _providers_worker_connector__WEBPACK_IMPORTED_MODULE_0__.callApi),
/* harmony export */   initApi: () => (/* reexport safe */ _providers_worker_connector__WEBPACK_IMPORTED_MODULE_0__.initApi)
/* harmony export */ });
/* harmony import */ var _providers_worker_connector__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(1);
// export { initApi, callApi } from './providers/direct/connector';


/******/ })()
;