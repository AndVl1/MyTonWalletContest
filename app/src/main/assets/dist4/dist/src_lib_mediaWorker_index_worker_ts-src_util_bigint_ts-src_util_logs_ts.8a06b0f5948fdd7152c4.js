/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
/******/ 	var __webpack_modules__ = ({

/***/ "./src/config.ts":
/*!***********************!*\
  !*** ./src/config.ts ***!
  \***********************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   DEBUG: () => (/* binding */ DEBUG),
/* harmony export */   DEFAULT_DECIMAL_PLACES: () => (/* binding */ DEFAULT_DECIMAL_PLACES),
/* harmony export */   ONE_TON: () => (/* binding */ ONE_TON)
/* harmony export */ });
/* unused harmony exports APP_ENV, APP_NAME, APP_VERSION, APP_ENV_MARKER, DEBUG_MORE, IS_PRODUCTION, IS_TEST, IS_PERF, IS_EXTENSION, IS_FIREFOX_EXTENSION, IS_PACKAGED_ELECTRON, IS_CAPACITOR, IS_ANDROID_DIRECT, ELECTRON_HOST_URL, INACTIVE_MARKER, PRODUCTION_URL, BETA_URL, APP_REPO_URL, BASE_URL, BOT_USERNAME, SWAP_FEE_ADDRESS, STRICTERDOM_ENABLED, DEBUG_ALERT_MSG, PIN_LENGTH, NATIVE_BIOMETRICS_USERNAME, NATIVE_BIOMETRICS_SERVER, MNEMONIC_COUNT, PRIVATE_KEY_HEX_LENGTH, MNEMONIC_CHECK_COUNT, MOBILE_SCREEN_MAX_WIDTH, ANIMATION_END_DELAY, ANIMATED_STICKER_TINY_SIZE_PX, ANIMATED_STICKER_SMALL_SIZE_PX, ANIMATED_STICKER_MIDDLE_SIZE_PX, ANIMATED_STICKER_DEFAULT_PX, ANIMATED_STICKER_BIG_SIZE_PX, ANIMATED_STICKER_HUGE_SIZE_PX, TON_SYMBOL, DEFAULT_LANDSCAPE_ACTION_TAB_ID, WHOLE_PART_DELIMITER, DEFAULT_SLIPPAGE_VALUE, GLOBAL_STATE_CACHE_DISABLED, GLOBAL_STATE_CACHE_KEY, ANIMATION_LEVEL_MIN, ANIMATION_LEVEL_MED, ANIMATION_LEVEL_MAX, ANIMATION_LEVEL_DEFAULT, THEME_DEFAULT, MAIN_ACCOUNT_ID, TONHTTPAPI_MAINNET_URL, TONHTTPAPI_MAINNET_API_KEY, ELECTRON_TONHTTPAPI_MAINNET_API_KEY, TONHTTPAPI_V3_MAINNET_API_URL, TONAPIIO_MAINNET_URL, TONHTTPAPI_TESTNET_URL, TONHTTPAPI_TESTNET_API_KEY, ELECTRON_TONHTTPAPI_TESTNET_API_KEY, TONHTTPAPI_V3_TESTNET_API_URL, TONAPIIO_TESTNET_URL, BRILLIANT_API_BASE_URL, FRACTION_DIGITS, SHORT_FRACTION_DIGITS, SUPPORT_USERNAME, MY_TON_WALLET_PROMO_URL, TELEGRAM_WEB_URL, TON_EXPLORER_BASE_MAINNET_URL, TON_EXPLORER_BASE_TESTNET_URL, TON_EXPLORER_NAME, TOKEN_EXPLORER_MAINNET_URL, TOKEN_EXPLORER_TESTNET_URL, TOKEN_EXPLORER_NAME, GETGEMS_BASE_MAINNET_URL, GETGEMS_BASE_TESTNET_URL, EMPTY_HASH_VALUE, CHANGELLY_SUPPORT_EMAIL, CHANGELLY_LIVE_CHAT_URL, CHANGELLY_SECURITY_EMAIL, CHANGELLY_TERMS_OF_USE, CHANGELLY_PRIVACY_POLICY, CHANGELLY_AML_KYC, CHANGELLY_WAITING_DEADLINE, TONCOIN_SLUG, DEFAULT_SWAP_SECOND_TOKEN_SLUG, DEFAULT_CEX_SWAP_SECOND_TOKEN_SLUG, PROXY_HOSTS, TINY_TRANSFER_MAX_COST, LANG_CACHE_NAME, LANG_LIST, STAKING_CYCLE_DURATION_MS, VALIDATION_PERIOD_MS, MIN_BALANCE_FOR_UNSTAKE, STAKING_FORWARD_AMOUNT, DEFAULT_FEE, STAKING_POOLS, LIQUID_POOL, LIQUID_JETTON, STAKING_MIN_AMOUNT, NOMINATORS_STAKING_MIN_AMOUNT, TONCONNECT_PROTOCOL_VERSION, TONCONNECT_WALLET_JSBRIDGE_KEY, NFT_FRAGMENT_COLLECTIONS, TON_DNS_COLLECTION, MYCOIN_TOKEN, MYCOIN_SLUG, MYCOIN_TOKEN_TESTNET, MYCOIN_SLUG_TESTNET, TOKEN_INFO, TON_BLOCKCHAIN, INIT_SWAP_ASSETS, MULTITAB_DATA_CHANNEL_NAME, ACTIVE_TAB_STORAGE_KEY, INDEXED_DB_NAME, INDEXED_DB_STORE_NAME, WINDOW_PROVIDER_CHANNEL, MIN_ASSETS_TAB_VIEW, DEFAULT_PRICE_CURRENCY, SHORT_CURRENCY_SYMBOL_MAP, CURRENCY_LIST, BURN_ADDRESS, DEFAULT_WALLET_VERSION, POPULAR_WALLET_VERSIONS, DEFAULT_TIMEOUT, DEFAULT_RETRIES, DEFAULT_ERROR_PAUSE, HISTORY_PERIODS, BROWSER_HISTORY_LIMIT, NFT_BATCH_SIZE, NOTCOIN_VOUCHERS_ADDRESS, BURN_CHUNK_DURATION_APPROX_SEC, NOTCOIN_FORWARD_TON_AMOUNT, NOTCOIN_EXCHANGERS, CLAIM_ADDRESS, CLAIM_AMOUNT, CLAIM_COMMENT, RE_LINK_TEMPLATE, RE_TG_BOT_MENTION, DIESEL_ADDRESS, DIESEL_TOKENS */
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

/***/ "./src/lib/mediaWorker/index.worker.ts":
/*!*********************************************!*\
  !*** ./src/lib/mediaWorker/index.worker.ts ***!
  \*********************************************/
/***/ ((__unused_webpack_module, __unused_webpack___webpack_exports__, __webpack_require__) => {

/* harmony import */ var _rlottie_rlottie_worker__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../rlottie/rlottie.worker */ "./src/lib/rlottie/rlottie.worker.ts");


/***/ }),

/***/ "./src/lib/rlottie/rlottie.worker.ts":
/*!*******************************************!*\
  !*** ./src/lib/rlottie/rlottie.worker.ts ***!
  \*******************************************/
/***/ ((__unused_webpack_module, __unused_webpack___webpack_exports__, __webpack_require__) => {

/* harmony import */ var pako_dist_pako_inflate__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! pako/dist/pako_inflate */ "./node_modules/pako/dist/pako_inflate.js");
/* harmony import */ var pako_dist_pako_inflate__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(pako_dist_pako_inflate__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var _util_createPostMessageInterface__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../util/createPostMessageInterface */ "./src/util/createPostMessageInterface.ts");


try {
  self.importScripts('rlottie-wasm.js');
} catch (err) {
  throw new Error('Failed to import rlottie-wasm.js');
}
let rLottieApi;
const rLottieApiPromise = new Promise(resolve => {
  Module.onRuntimeInitialized = () => {
    rLottieApi = {
      init: Module.cwrap('lottie_init', '', []),
      destroy: Module.cwrap('lottie_destroy', '', ['number']),
      resize: Module.cwrap('lottie_resize', '', ['number', 'number', 'number']),
      buffer: Module.cwrap('lottie_buffer', 'number', ['number']),
      render: Module.cwrap('lottie_render', '', ['number', 'number']),
      loadFromData: Module.cwrap('lottie_load_from_data', 'number', ['number', 'number'])
    };
    resolve();
  };
});
const HIGH_PRIORITY_MAX_FPS = 60;
const LOW_PRIORITY_MAX_FPS = 30;
const DESTROY_REPEAT_DELAY = 1000;
const renderers = new Map();
async function init(key, tgsUrl, imgSize, isLowPriority, customColor, onInit) {
  if (!rLottieApi) {
    await rLottieApiPromise;
  }
  const json = await extractJson(tgsUrl);
  const stringOnWasmHeap = allocate(intArrayFromString(json), 'i8', 0);
  const handle = rLottieApi.init();
  const framesCount = rLottieApi.loadFromData(handle, stringOnWasmHeap);
  rLottieApi.resize(handle, imgSize, imgSize);
  const imageData = new ImageData(imgSize, imgSize);
  const {
    reduceFactor,
    msPerFrame,
    reducedFramesCount
  } = calcParams(json, isLowPriority, framesCount);
  renderers.set(key, {
    imgSize,
    reduceFactor,
    handle,
    imageData,
    customColor
  });
  onInit(reduceFactor, msPerFrame, reducedFramesCount);
}
async function changeData(key, tgsUrl, isLowPriority, onInit) {
  if (!rLottieApi) {
    await rLottieApiPromise;
  }
  const json = await extractJson(tgsUrl);
  const stringOnWasmHeap = allocate(intArrayFromString(json), 'i8', 0);
  const {
    handle
  } = renderers.get(key);
  const framesCount = rLottieApi.loadFromData(handle, stringOnWasmHeap);
  const {
    reduceFactor,
    msPerFrame,
    reducedFramesCount
  } = calcParams(json, isLowPriority, framesCount);
  onInit(reduceFactor, msPerFrame, reducedFramesCount);
}
async function extractJson(tgsUrl) {
  const response = await fetch(tgsUrl);
  const contentType = response.headers.get('Content-Type');

  // Support deprecated JSON format cached locally
  if (contentType !== null && contentType !== void 0 && contentType.startsWith('text/')) {
    return response.text();
  }
  const arrayBuffer = await response.arrayBuffer();
  return (0,pako_dist_pako_inflate__WEBPACK_IMPORTED_MODULE_0__.inflate)(arrayBuffer, {
    to: 'string'
  });
}
function calcParams(json, isLowPriority, framesCount) {
  const animationData = JSON.parse(json);
  const maxFps = isLowPriority ? LOW_PRIORITY_MAX_FPS : HIGH_PRIORITY_MAX_FPS;
  const sourceFps = animationData.fr || maxFps;
  const reduceFactor = sourceFps % maxFps === 0 ? sourceFps / maxFps : 1;
  return {
    reduceFactor,
    msPerFrame: 1000 / (sourceFps / reduceFactor),
    reducedFramesCount: Math.ceil(framesCount / reduceFactor)
  };
}
async function renderFrames(key, frameIndex, onProgress) {
  if (!rLottieApi) {
    await rLottieApiPromise;
  }
  const {
    imgSize,
    reduceFactor,
    handle,
    imageData,
    customColor
  } = renderers.get(key);
  const realIndex = frameIndex * reduceFactor;
  rLottieApi.render(handle, realIndex);
  const bufferPointer = rLottieApi.buffer(handle);
  const data = Module.HEAPU8.subarray(bufferPointer, bufferPointer + imgSize * imgSize * 4);
  if (customColor) {
    const arr = new Uint8ClampedArray(data);
    applyColor(arr, customColor);
    imageData.data.set(arr);
  } else {
    imageData.data.set(data);
  }
  const imageBitmap = await createImageBitmap(imageData);
  onProgress(frameIndex, imageBitmap);
}
function applyColor(arr, color) {
  for (let i = 0; i < arr.length; i += 4) {
    arr[i] = color[0];
    arr[i + 1] = color[1];
    arr[i + 2] = color[2];
  }
}
function destroy(key) {
  let isRepeated = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : false;
  try {
    const renderer = renderers.get(key);
    rLottieApi.destroy(renderer.handle);
    renderers.delete(key);
  } catch (err) {
    // `destroy` sometimes can be called before the initialization is finished
    if (!isRepeated) {
      setTimeout(() => destroy(key, true), DESTROY_REPEAT_DELAY);
    }
  }
}
const api = {
  'rlottie:init': init,
  'rlottie:changeData': changeData,
  'rlottie:renderFrames': renderFrames,
  'rlottie:destroy': destroy
};
(0,_util_createPostMessageInterface__WEBPACK_IMPORTED_MODULE_1__.createPostMessageInterface)(api);

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
/******/ 		__webpack_modules__[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/ 	
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/ 	
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = __webpack_modules__;
/******/ 	
/******/ 	// the startup function
/******/ 	__webpack_require__.x = () => {
/******/ 		// Load entry module and return exports
/******/ 		// This entry module depends on other loaded chunks and execution need to be delayed
/******/ 		var __webpack_exports__ = __webpack_require__.O(undefined, ["vendors-node_modules_pako_dist_pako_inflate_js","src_util_createPostMessageInterface_ts-src_util_decimals_ts-src_util_random_ts"], () => (__webpack_require__("./src/lib/mediaWorker/index.worker.ts")))
/******/ 		__webpack_exports__ = __webpack_require__.O(__webpack_exports__);
/******/ 		return __webpack_exports__;
/******/ 	};
/******/ 	
/************************************************************************/
/******/ 	/* webpack/runtime/chunk loaded */
/******/ 	(() => {
/******/ 		var deferred = [];
/******/ 		__webpack_require__.O = (result, chunkIds, fn, priority) => {
/******/ 			if(chunkIds) {
/******/ 				priority = priority || 0;
/******/ 				for(var i = deferred.length; i > 0 && deferred[i - 1][2] > priority; i--) deferred[i] = deferred[i - 1];
/******/ 				deferred[i] = [chunkIds, fn, priority];
/******/ 				return;
/******/ 			}
/******/ 			var notFulfilled = Infinity;
/******/ 			for (var i = 0; i < deferred.length; i++) {
/******/ 				var [chunkIds, fn, priority] = deferred[i];
/******/ 				var fulfilled = true;
/******/ 				for (var j = 0; j < chunkIds.length; j++) {
/******/ 					if ((priority & 1 === 0 || notFulfilled >= priority) && Object.keys(__webpack_require__.O).every((key) => (__webpack_require__.O[key](chunkIds[j])))) {
/******/ 						chunkIds.splice(j--, 1);
/******/ 					} else {
/******/ 						fulfilled = false;
/******/ 						if(priority < notFulfilled) notFulfilled = priority;
/******/ 					}
/******/ 				}
/******/ 				if(fulfilled) {
/******/ 					deferred.splice(i--, 1)
/******/ 					var r = fn();
/******/ 					if (r !== undefined) result = r;
/******/ 				}
/******/ 			}
/******/ 			return result;
/******/ 		};
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/compat get default export */
/******/ 	(() => {
/******/ 		// getDefaultExport function for compatibility with non-harmony modules
/******/ 		__webpack_require__.n = (module) => {
/******/ 			var getter = module && module.__esModule ?
/******/ 				() => (module['default']) :
/******/ 				() => (module);
/******/ 			__webpack_require__.d(getter, { a: getter });
/******/ 			return getter;
/******/ 		};
/******/ 	})();
/******/ 	
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
/******/ 		// This function allow to reference async chunks and sibling chunks for the entrypoint
/******/ 		__webpack_require__.u = (chunkId) => {
/******/ 			// return url for filenames based on template
/******/ 			return "" + chunkId + "." + {"vendors-node_modules_pako_dist_pako_inflate_js":"0b02ebf78115ce39e5ee","src_util_createPostMessageInterface_ts-src_util_decimals_ts-src_util_random_ts":"382fb0ce2eabaeee6524"}[chunkId] + ".js";
/******/ 		};
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/get mini-css chunk filename */
/******/ 	(() => {
/******/ 		// This function allow to reference async chunks and sibling chunks for the entrypoint
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
/******/ 	/* webpack/runtime/importScripts chunk loading */
/******/ 	(() => {
/******/ 		// no baseURI
/******/ 		
/******/ 		// object to store loaded chunks
/******/ 		// "1" means "already loaded"
/******/ 		var installedChunks = {
/******/ 			"src_lib_mediaWorker_index_worker_ts-src_util_bigint_ts-src_util_logs_ts": 1
/******/ 		};
/******/ 		
/******/ 		// importScripts chunk loading
/******/ 		var installChunk = (data) => {
/******/ 			var [chunkIds, moreModules, runtime] = data;
/******/ 			for(var moduleId in moreModules) {
/******/ 				if(__webpack_require__.o(moreModules, moduleId)) {
/******/ 					__webpack_require__.m[moduleId] = moreModules[moduleId];
/******/ 				}
/******/ 			}
/******/ 			if(runtime) runtime(__webpack_require__);
/******/ 			while(chunkIds.length)
/******/ 				installedChunks[chunkIds.pop()] = 1;
/******/ 			parentChunkLoadingFunction(data);
/******/ 		};
/******/ 		__webpack_require__.f.i = (chunkId, promises) => {
/******/ 			// "1" is the signal for "already loaded"
/******/ 			if(!installedChunks[chunkId]) {
/******/ 				if(true) { // all chunks have JS
/******/ 					importScripts(__webpack_require__.p + __webpack_require__.u(chunkId));
/******/ 				}
/******/ 			}
/******/ 		};
/******/ 		
/******/ 		var chunkLoadingGlobal = self["webpackChunkmytonwallet"] = self["webpackChunkmytonwallet"] || [];
/******/ 		var parentChunkLoadingFunction = chunkLoadingGlobal.push.bind(chunkLoadingGlobal);
/******/ 		chunkLoadingGlobal.push = installChunk;
/******/ 		
/******/ 		// no HMR
/******/ 		
/******/ 		// no HMR manifest
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/startup chunk dependencies */
/******/ 	(() => {
/******/ 		var next = __webpack_require__.x;
/******/ 		__webpack_require__.x = () => {
/******/ 			return Promise.all([
/******/ 				__webpack_require__.e("vendors-node_modules_pako_dist_pako_inflate_js"),
/******/ 				__webpack_require__.e("src_util_createPostMessageInterface_ts-src_util_decimals_ts-src_util_random_ts")
/******/ 			]).then(next);
/******/ 		};
/******/ 	})();
/******/ 	
/************************************************************************/
/******/ 	
/******/ 	// run startup
/******/ 	var __webpack_exports__ = __webpack_require__.x();
/******/ 	
/******/ })()
;
//# sourceMappingURL=src_lib_mediaWorker_index_worker_ts-src_util_bigint_ts-src_util_logs_ts.8a06b0f5948fdd7152c4.js.map