"use strict";
(self["webpackChunkmytonwallet"] = self["webpackChunkmytonwallet"] || []).push([["src_util_fetch_ts-src_util_ledger_index_ts"],{

/***/ "./src/api/blockchains/ton/util/index.ts":
/*!***********************************************!*\
  !*** ./src/api/blockchains/ton/util/index.ts ***!
  \***********************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   generateQueryId: () => (/* binding */ generateQueryId)
/* harmony export */ });
/* unused harmony exports cloneDeep, stringifyTxId, parseTxId, buildTokenSlug */
/* harmony import */ var _util_bigint__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../../../util/bigint */ "./src/util/bigint.ts");

function cloneDeep(value) {
  return JSON.parse(JSON.stringify(value), _util_bigint__WEBPACK_IMPORTED_MODULE_0__.bigintReviver);
}
function stringifyTxId(_ref) {
  let {
    lt,
    hash
  } = _ref;
  return `${lt}:${hash}`;
}
function parseTxId(txId) {
  const [lt, hash] = txId.split(':');
  return {
    lt: Number(lt),
    hash
  };
}
function buildTokenSlug(minterAddress) {
  const addressPart = minterAddress.replace(/[^a-z\d]/gi, '').slice(0, 10);
  return `ton-${addressPart}`.toLowerCase();
}
function generateQueryId() {
  return (0,_util_bigint__WEBPACK_IMPORTED_MODULE_0__.bigintRandom)(8);
}

/***/ }),

/***/ "./src/api/blockchains/ton/util/tonCore.ts":
/*!*************************************************!*\
  !*** ./src/api/blockchains/ton/util/tonCore.ts ***!
  \*************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   buildLiquidStakingWithdrawCustomPayload: () => (/* binding */ buildLiquidStakingWithdrawCustomPayload),
/* harmony export */   commentToBytes: () => (/* binding */ commentToBytes),
/* harmony export */   packBytesAsSnakeCell: () => (/* binding */ packBytesAsSnakeCell),
/* harmony export */   toBase64Address: () => (/* binding */ toBase64Address)
/* harmony export */ });
/* unused harmony exports walletClassMap, getTonClient, getTonWalletContract, resolveTokenWalletAddress, resolveTokenMinterAddress, getWalletPublicKey, getJettonMinterData, oneCellFromBoc, toRawAddress, buildTokenTransferBody, parseBase64, packBytesAsSnake, packBytesAsSnakeForEncryptedData, buildLiquidStakingDepositBody, buildLiquidStakingWithdrawBody, getTokenBalance, parseAddress, getIsRawAddress, getDnsItemDomain */
/* harmony import */ var _ton_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @ton/core */ "./node_modules/@ton/core/dist/index.js");
/* harmony import */ var _ton_core__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_ton_core__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var axios__WEBPACK_IMPORTED_MODULE_22__ = __webpack_require__(/*! axios */ "./node_modules/axios/lib/axios.js");
/* harmony import */ var _ton_ton_dist_wallets_WalletContractV1R1__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @ton/ton/dist/wallets/WalletContractV1R1 */ "./node_modules/@ton/ton/dist/wallets/WalletContractV1R1.js");
/* harmony import */ var _ton_ton_dist_wallets_WalletContractV1R2__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @ton/ton/dist/wallets/WalletContractV1R2 */ "./node_modules/@ton/ton/dist/wallets/WalletContractV1R2.js");
/* harmony import */ var _ton_ton_dist_wallets_WalletContractV1R3__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ton/ton/dist/wallets/WalletContractV1R3 */ "./node_modules/@ton/ton/dist/wallets/WalletContractV1R3.js");
/* harmony import */ var _ton_ton_dist_wallets_WalletContractV2R1__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @ton/ton/dist/wallets/WalletContractV2R1 */ "./node_modules/@ton/ton/dist/wallets/WalletContractV2R1.js");
/* harmony import */ var _ton_ton_dist_wallets_WalletContractV2R2__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ton/ton/dist/wallets/WalletContractV2R2 */ "./node_modules/@ton/ton/dist/wallets/WalletContractV2R2.js");
/* harmony import */ var _ton_ton_dist_wallets_WalletContractV3R1__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @ton/ton/dist/wallets/WalletContractV3R1 */ "./node_modules/@ton/ton/dist/wallets/WalletContractV3R1.js");
/* harmony import */ var _ton_ton_dist_wallets_WalletContractV3R2__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @ton/ton/dist/wallets/WalletContractV3R2 */ "./node_modules/@ton/ton/dist/wallets/WalletContractV3R2.js");
/* harmony import */ var _ton_ton_dist_wallets_WalletContractV4__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @ton/ton/dist/wallets/WalletContractV4 */ "./node_modules/@ton/ton/dist/wallets/WalletContractV4.js");
/* harmony import */ var _ton_ton_dist_wallets_WalletContractV5R1__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! @ton/ton/dist/wallets/WalletContractV5R1 */ "./node_modules/@ton/ton/dist/wallets/WalletContractV5R1.js");
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ../../../../config */ "./src/config.ts");
/* harmony import */ var _lib_axios_fetch_adapter__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! ../../../../lib/axios-fetch-adapter */ "./src/lib/axios-fetch-adapter/index.js");
/* harmony import */ var _util_logs__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! ../../../../util/logs */ "./src/util/logs.ts");
/* harmony import */ var _util_withCacheAsync__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! ../../../../util/withCacheAsync */ "./src/util/withCacheAsync.ts");
/* harmony import */ var _contracts_DnsItem__WEBPACK_IMPORTED_MODULE_14__ = __webpack_require__(/*! ../contracts/DnsItem */ "./src/api/blockchains/ton/contracts/DnsItem.ts");
/* harmony import */ var _contracts_JettonMaster__WEBPACK_IMPORTED_MODULE_15__ = __webpack_require__(/*! ../contracts/JettonMaster */ "./src/api/blockchains/ton/contracts/JettonMaster.ts");
/* harmony import */ var _contracts_JettonWallet__WEBPACK_IMPORTED_MODULE_16__ = __webpack_require__(/*! ../contracts/JettonWallet */ "./src/api/blockchains/ton/contracts/JettonWallet.ts");
/* harmony import */ var _common_utils__WEBPACK_IMPORTED_MODULE_17__ = __webpack_require__(/*! ../../../common/utils */ "./src/api/common/utils.ts");
/* harmony import */ var _environment__WEBPACK_IMPORTED_MODULE_18__ = __webpack_require__(/*! ../../../environment */ "./src/api/environment.ts");
/* harmony import */ var _constants__WEBPACK_IMPORTED_MODULE_19__ = __webpack_require__(/*! ../constants */ "./src/api/blockchains/ton/constants.ts");
/* harmony import */ var _index__WEBPACK_IMPORTED_MODULE_20__ = __webpack_require__(/*! ./index */ "./src/api/blockchains/ton/util/index.ts");
/* harmony import */ var _TonClient__WEBPACK_IMPORTED_MODULE_21__ = __webpack_require__(/*! ./TonClient */ "./src/api/blockchains/ton/util/TonClient.ts");
/* provided dependency */ var Buffer = __webpack_require__(/*! buffer */ "./node_modules/buffer/index.js")["Buffer"];























axios__WEBPACK_IMPORTED_MODULE_22__["default"].defaults.adapter = _lib_axios_fetch_adapter__WEBPACK_IMPORTED_MODULE_11__["default"];
const TON_MAX_COMMENT_BYTES = 127;
let clientByNetwork;
const walletClassMap = {
  simpleR1: _ton_ton_dist_wallets_WalletContractV1R1__WEBPACK_IMPORTED_MODULE_1__.WalletContractV1R1,
  simpleR2: _ton_ton_dist_wallets_WalletContractV1R2__WEBPACK_IMPORTED_MODULE_2__.WalletContractV1R2,
  simpleR3: _ton_ton_dist_wallets_WalletContractV1R3__WEBPACK_IMPORTED_MODULE_3__.WalletContractV1R3,
  v2R1: _ton_ton_dist_wallets_WalletContractV2R1__WEBPACK_IMPORTED_MODULE_4__.WalletContractV2R1,
  v2R2: _ton_ton_dist_wallets_WalletContractV2R2__WEBPACK_IMPORTED_MODULE_5__.WalletContractV2R2,
  v3R1: _ton_ton_dist_wallets_WalletContractV3R1__WEBPACK_IMPORTED_MODULE_6__.WalletContractV3R1,
  v3R2: _ton_ton_dist_wallets_WalletContractV3R2__WEBPACK_IMPORTED_MODULE_7__.WalletContractV3R2,
  v4R2: _ton_ton_dist_wallets_WalletContractV4__WEBPACK_IMPORTED_MODULE_8__.WalletContractV4,
  W5: _ton_ton_dist_wallets_WalletContractV5R1__WEBPACK_IMPORTED_MODULE_9__.WalletContractV5R1
};
function getTonClient() {
  let network = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : 'mainnet';
  if (!clientByNetwork) {
    clientByNetwork = {
      mainnet: new _TonClient__WEBPACK_IMPORTED_MODULE_21__.TonClient({
        endpoint: _config__WEBPACK_IMPORTED_MODULE_10__.TONHTTPAPI_MAINNET_URL,
        timeout: _config__WEBPACK_IMPORTED_MODULE_10__.DEFAULT_TIMEOUT,
        apiKey: _config__WEBPACK_IMPORTED_MODULE_10__.TONHTTPAPI_MAINNET_API_KEY,
        headers: (0,_environment__WEBPACK_IMPORTED_MODULE_18__.getEnvironment)().apiHeaders
      }),
      testnet: new _TonClient__WEBPACK_IMPORTED_MODULE_21__.TonClient({
        endpoint: _config__WEBPACK_IMPORTED_MODULE_10__.TONHTTPAPI_TESTNET_URL,
        timeout: _config__WEBPACK_IMPORTED_MODULE_10__.DEFAULT_TIMEOUT,
        apiKey: _config__WEBPACK_IMPORTED_MODULE_10__.TONHTTPAPI_TESTNET_API_KEY,
        headers: (0,_environment__WEBPACK_IMPORTED_MODULE_18__.getEnvironment)().apiHeaders
      })
    };
  }
  return clientByNetwork[network];
}
function getTonWalletContract(publicKeyHex, version) {
  const walletClass = walletClassMap[version];
  if (!walletClass) {
    throw new Error('Unsupported wallet contract version');
  }
  const publicKey = Buffer.from((0,_common_utils__WEBPACK_IMPORTED_MODULE_17__.hexToBytes)(publicKeyHex));
  return walletClass.create({
    workchain: _constants__WEBPACK_IMPORTED_MODULE_19__.WORKCHAIN,
    publicKey
  });
}
const resolveTokenWalletAddress = (0,_util_withCacheAsync__WEBPACK_IMPORTED_MODULE_13__["default"])(async (network, address, minterAddress) => {
  const minter = getTonClient(network).open(new _contracts_JettonMaster__WEBPACK_IMPORTED_MODULE_15__.JettonMinter(_ton_core__WEBPACK_IMPORTED_MODULE_0__.Address.parse(minterAddress)));
  const walletAddress = await minter.getWalletAddress(_ton_core__WEBPACK_IMPORTED_MODULE_0__.Address.parse(address));
  return toBase64Address(walletAddress, true, network);
});
const resolveTokenMinterAddress = (0,_util_withCacheAsync__WEBPACK_IMPORTED_MODULE_13__["default"])(async (network, tokenWalletAddress) => {
  const tokenWallet = getTonClient(network).open(new _contracts_JettonWallet__WEBPACK_IMPORTED_MODULE_16__.JettonWallet(_ton_core__WEBPACK_IMPORTED_MODULE_0__.Address.parse(tokenWalletAddress)));
  const data = await tokenWallet.getWalletData();
  return toBase64Address(data.minter, true, network);
});
const getWalletPublicKey = (0,_util_withCacheAsync__WEBPACK_IMPORTED_MODULE_13__["default"])(async (network, address) => {
  try {
    const res = await getTonClient(network).callGetMethod(_ton_core__WEBPACK_IMPORTED_MODULE_0__.Address.parse(address), 'get_public_key');
    const bigintKey = res.stack.readBigNumber();
    const hex = bigintKey.toString(16).padStart(64, '0');
    return (0,_common_utils__WEBPACK_IMPORTED_MODULE_17__.hexToBytes)(hex);
  } catch (err) {
    (0,_util_logs__WEBPACK_IMPORTED_MODULE_12__.logDebugError)('getWalletPublicKey', err);
    return undefined;
  }
});
function getJettonMinterData(network, address) {
  const contract = getTonClient(network).open(new _contracts_JettonMaster__WEBPACK_IMPORTED_MODULE_15__.JettonMinter(_ton_core__WEBPACK_IMPORTED_MODULE_0__.Address.parse(address)));
  return contract.getJettonData();
}
function oneCellFromBoc(bytes) {
  return _ton_core__WEBPACK_IMPORTED_MODULE_0__.Cell.fromBoc(Buffer.from(bytes));
}
function toBase64Address(address) {
  let isBounceable = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : _constants__WEBPACK_IMPORTED_MODULE_19__.DEFAULT_IS_BOUNCEABLE;
  let network = arguments.length > 2 ? arguments[2] : undefined;
  if (typeof address === 'string') {
    address = _ton_core__WEBPACK_IMPORTED_MODULE_0__.Address.parse(address);
  }
  return address.toString({
    urlSafe: true,
    bounceable: isBounceable,
    testOnly: network === 'testnet'
  });
}
function toRawAddress(address) {
  if (typeof address === 'string') {
    address = _ton_core__WEBPACK_IMPORTED_MODULE_0__.Address.parse(address);
  }
  return address.toRawString();
}
function buildTokenTransferBody(params) {
  const {
    queryId,
    tokenAmount,
    toAddress,
    responseAddress,
    forwardAmount
  } = params;
  let forwardPayload = params.forwardPayload;
  let builder = new _ton_core__WEBPACK_IMPORTED_MODULE_0__.Builder().storeUint(_constants__WEBPACK_IMPORTED_MODULE_19__.JettonOpCode.Transfer, 32).storeUint(queryId || (0,_index__WEBPACK_IMPORTED_MODULE_20__.generateQueryId)(), 64).storeCoins(tokenAmount).storeAddress(_ton_core__WEBPACK_IMPORTED_MODULE_0__.Address.parse(toAddress)).storeAddress(_ton_core__WEBPACK_IMPORTED_MODULE_0__.Address.parse(responseAddress)).storeBit(false).storeCoins(forwardAmount !== null && forwardAmount !== void 0 ? forwardAmount : 0n);
  if (forwardPayload instanceof Uint8Array) {
    const freeBytes = Math.round(builder.availableBits / 8);
    forwardPayload = packBytesAsSnake(forwardPayload, freeBytes);
  }
  if (!forwardPayload) {
    builder.storeBit(false);
  } else if (typeof forwardPayload === 'string') {
    builder = builder.storeBit(false).storeUint(0, 32).storeBuffer(Buffer.from(forwardPayload));
  } else if (forwardPayload instanceof Uint8Array) {
    builder = builder.storeBit(false).storeBuffer(Buffer.from(forwardPayload));
  } else {
    builder = builder.storeBit(true).storeRef(forwardPayload);
  }
  return builder.endCell();
}
function parseBase64(base64) {
  try {
    return _ton_core__WEBPACK_IMPORTED_MODULE_0__.Cell.fromBase64(base64);
  } catch (err) {
    (0,_util_logs__WEBPACK_IMPORTED_MODULE_12__.logDebugError)('parseBase64', err);
    return Uint8Array.from(Buffer.from(base64, 'base64'));
  }
}
function commentToBytes(comment) {
  const buffer = Buffer.from(comment);
  const bytes = new Uint8Array(buffer.length + 4);
  const startBuffer = Buffer.alloc(4);
  startBuffer.writeUInt32BE(_constants__WEBPACK_IMPORTED_MODULE_19__.OpCode.Comment);
  bytes.set(startBuffer, 0);
  bytes.set(buffer, 4);
  return bytes;
}
function packBytesAsSnake(bytes) {
  let maxBytes = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : TON_MAX_COMMENT_BYTES;
  const buffer = Buffer.from(bytes);
  if (buffer.length <= maxBytes) {
    return bytes;
  }
  return packBytesAsSnakeCell(bytes);
}
function packBytesAsSnakeCell(bytes) {
  const buffer = Buffer.from(bytes);
  const mainBuilder = new _ton_core__WEBPACK_IMPORTED_MODULE_0__.Builder();
  let prevBuilder;
  let currentBuilder = mainBuilder;
  for (const [i, byte] of buffer.entries()) {
    if (currentBuilder.availableBits < 8) {
      var _prevBuilder;
      (_prevBuilder = prevBuilder) === null || _prevBuilder === void 0 ? void 0 : _prevBuilder.storeRef(currentBuilder);
      prevBuilder = currentBuilder;
      currentBuilder = new _ton_core__WEBPACK_IMPORTED_MODULE_0__.Builder();
    }
    currentBuilder = currentBuilder.storeUint(byte, 8);
    if (i === buffer.length - 1) {
      var _prevBuilder2;
      (_prevBuilder2 = prevBuilder) === null || _prevBuilder2 === void 0 ? void 0 : _prevBuilder2.storeRef(currentBuilder);
    }
  }
  return mainBuilder.asCell();
}
function createNestedCell(data, maxCellSize) {
  const builder = new _ton_core__WEBPACK_IMPORTED_MODULE_0__.Builder();
  const dataSlice = Buffer.from(data.slice(0, maxCellSize));
  builder.storeBuffer(dataSlice);
  if (data.length > maxCellSize) {
    const remainingData = data.slice(maxCellSize);
    builder.storeRef(createNestedCell(remainingData, maxCellSize));
  }
  return builder.endCell();
}
function packBytesAsSnakeForEncryptedData(data) {
  const ROOT_BUILDER_BYTES = 39;
  const MAX_CELLS_AMOUNT = 16;
  const rootBuilder = new _ton_core__WEBPACK_IMPORTED_MODULE_0__.Builder();
  rootBuilder.storeBuffer(Buffer.from(data.slice(0, Math.min(data.length, ROOT_BUILDER_BYTES))));
  if (data.length > ROOT_BUILDER_BYTES + MAX_CELLS_AMOUNT * TON_MAX_COMMENT_BYTES) {
    throw new Error('Input text is too long');
  }
  rootBuilder.storeRef(createNestedCell(Buffer.from(data.slice(ROOT_BUILDER_BYTES)), TON_MAX_COMMENT_BYTES));
  return rootBuilder.endCell();
}
function buildLiquidStakingDepositBody(queryId) {
  return new _ton_core__WEBPACK_IMPORTED_MODULE_0__.Builder().storeUint(_constants__WEBPACK_IMPORTED_MODULE_19__.LiquidStakingOpCode.Deposit, 32).storeUint(queryId || 0, 64).asCell();
}
function buildLiquidStakingWithdrawBody(options) {
  const {
    queryId,
    amount,
    responseAddress,
    waitTillRoundEnd,
    fillOrKill
  } = options;
  const customPayload = buildLiquidStakingWithdrawCustomPayload(waitTillRoundEnd, fillOrKill);
  return new _ton_core__WEBPACK_IMPORTED_MODULE_0__.Builder().storeUint(_constants__WEBPACK_IMPORTED_MODULE_19__.JettonOpCode.Burn, 32).storeUint(queryId !== null && queryId !== void 0 ? queryId : 0, 64).storeCoins(amount).storeAddress(_ton_core__WEBPACK_IMPORTED_MODULE_0__.Address.parse(responseAddress)).storeBit(1).storeRef(customPayload).asCell();
}
function buildLiquidStakingWithdrawCustomPayload(waitTillRoundEnd, fillOrKill) {
  return new _ton_core__WEBPACK_IMPORTED_MODULE_0__.Builder().storeUint(Number(waitTillRoundEnd), 1).storeUint(Number(fillOrKill), 1).asCell();
}
function getTokenBalance(network, walletAddress) {
  const tokenWallet = getTonClient(network).open(new _contracts_JettonWallet__WEBPACK_IMPORTED_MODULE_16__.JettonWallet(_ton_core__WEBPACK_IMPORTED_MODULE_0__.Address.parse(walletAddress)));
  return tokenWallet.getJettonBalance();
}
function parseAddress(address) {
  try {
    if (_ton_core__WEBPACK_IMPORTED_MODULE_0__.Address.isRaw(address)) {
      return {
        address: _ton_core__WEBPACK_IMPORTED_MODULE_0__.Address.parseRaw(address),
        isRaw: true,
        isValid: true
      };
    } else if (_ton_core__WEBPACK_IMPORTED_MODULE_0__.Address.isFriendly(address)) {
      return {
        ..._ton_core__WEBPACK_IMPORTED_MODULE_0__.Address.parseFriendly(address),
        isUserFriendly: true,
        isValid: true
      };
    }
  } catch (err) {
    // Do nothing
  }
  return {
    isValid: false
  };
}
function getIsRawAddress(address) {
  return Boolean(parseAddress(address).isRaw);
}
async function getDnsItemDomain(network, address) {
  var _Object$entries$find;
  if (typeof address === 'string') address = _ton_core__WEBPACK_IMPORTED_MODULE_0__.Address.parse(address);
  const contract = getTonClient(network).open(new _contracts_DnsItem__WEBPACK_IMPORTED_MODULE_14__.DnsItem(address));
  const nftData = await contract.getNftData();
  const collectionAddress = toBase64Address(nftData.collectionAddress, true);
  const zone = (_Object$entries$find = Object.entries(_constants__WEBPACK_IMPORTED_MODULE_19__.DNS_ZONES_MAP).find(_ref => {
    let [, collection] = _ref;
    return collection === collectionAddress;
  })) === null || _Object$entries$find === void 0 ? void 0 : _Object$entries$find[0];
  const base = zone === '.t.me' ? await contract.getTelemintDomain() : await contract.getDomain();
  return `${base}${zone}`;
}

/***/ }),

/***/ "./src/api/common/utils.ts":
/*!*********************************!*\
  !*** ./src/api/common/utils.ts ***!
  \*********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   hexToBytes: () => (/* binding */ hexToBytes)
/* harmony export */ });
/* unused harmony exports sha256, bytesToHex, bytesToBase64, base64ToBytes, base64ToString, isKnownStakingPool */
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../config */ "./src/config.ts");
/* provided dependency */ var Buffer = __webpack_require__(/*! buffer */ "./node_modules/buffer/index.js")["Buffer"];

function sha256(bytes) {
  return crypto.subtle.digest('SHA-256', bytes);
}
function bytesToHex(bytes) {
  return Buffer.from(bytes).toString('hex');
}
function hexToBytes(hex) {
  return Uint8Array.from(Buffer.from(hex, 'hex'));
}
function bytesToBase64(bytes) {
  return Buffer.from(bytes).toString('base64');
}
function base64ToBytes(base64) {
  return Uint8Array.from(Buffer.from(base64, 'base64'));
}
function base64ToString(base64) {
  return Buffer.from(base64, 'base64').toString('utf-8');
}
function isKnownStakingPool(address) {
  return _config__WEBPACK_IMPORTED_MODULE_0__.STAKING_POOLS.some(poolPart => address.endsWith(poolPart));
}

/***/ }),

/***/ "./src/api/environment.ts":
/*!********************************!*\
  !*** ./src/api/environment.ts ***!
  \********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   getEnvironment: () => (/* binding */ getEnvironment)
/* harmony export */ });
/* unused harmony export setEnvironment */
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../config */ "./src/config.ts");
/*
 * This module is to be used instead of /src/util/environment.ts
 * when `window` is not available (e.g. in a web worker).
 */


const ELECTRON_ORIGIN = 'file://';
let environment;
function setEnvironment(args) {
  var _self;
  environment = {
    ...args,
    isDappSupported: _config__WEBPACK_IMPORTED_MODULE_0__.IS_EXTENSION || _config__WEBPACK_IMPORTED_MODULE_0__.IS_CAPACITOR || args.isElectron,
    isSseSupported: args.isElectron || _config__WEBPACK_IMPORTED_MODULE_0__.IS_CAPACITOR && !args.isNativeBottomSheet,
    // eslint-disable-next-line no-restricted-globals
    apiHeaders: {
      'X-App-Origin': args.isElectron ? ELECTRON_ORIGIN : (_self = self) === null || _self === void 0 ? void 0 : _self.origin
    },
    tonhttpapiMainnetKey: args.isElectron ? _config__WEBPACK_IMPORTED_MODULE_0__.ELECTRON_TONHTTPAPI_MAINNET_API_KEY : _config__WEBPACK_IMPORTED_MODULE_0__.TONHTTPAPI_MAINNET_API_KEY,
    tonhttpapiTestnetKey: args.isElectron ? _config__WEBPACK_IMPORTED_MODULE_0__.ELECTRON_TONHTTPAPI_TESTNET_API_KEY : _config__WEBPACK_IMPORTED_MODULE_0__.TONHTTPAPI_TESTNET_API_KEY
  };
  return environment;
}
function getEnvironment() {
  return environment;
}

/***/ }),

/***/ "./src/util/compareVersions.ts":
/*!*************************************!*\
  !*** ./src/util/compareVersions.ts ***!
  \*************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (/* binding */ compareVersions)
/* harmony export */ });
function compareVersions(versionA, versionB) {
  const partsA = versionA.split('.').map(Number);
  const partsB = versionB.split('.').map(Number);
  for (let i = 0; i < Math.max(partsA.length, partsB.length); i++) {
    const partA = partsA[i] || 0;
    const partB = partsB[i] || 0;
    if (partA > partB) return 1;
    if (partA < partB) return -1;
  }
  return 0;
}

/***/ }),

/***/ "./src/util/fetch.ts":
/*!***************************!*\
  !*** ./src/util/fetch.ts ***!
  \***************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   fetchWithRetry: () => (/* binding */ fetchWithRetry)
/* harmony export */ });
/* unused harmony exports fetchJson, fetchWithTimeout, handleFetchErrors */
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../config */ "./src/config.ts");
/* harmony import */ var _api_errors__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../api/errors */ "./src/api/errors.ts");
/* harmony import */ var _logs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./logs */ "./src/util/logs.ts");
/* harmony import */ var _schedulers__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./schedulers */ "./src/util/schedulers.ts");




const MAX_TIMEOUT = 30000; // 30 sec

async function fetchJson(url, data, init) {
  const urlObject = new URL(url);
  if (data) {
    Object.entries(data).forEach(_ref => {
      let [key, value] = _ref;
      if (value === undefined) {
        return;
      }
      if (Array.isArray(value)) {
        value.forEach(item => {
          urlObject.searchParams.append(key, item.toString());
        });
      } else {
        urlObject.searchParams.set(key, value.toString());
      }
    });
  }
  const response = await fetchWithRetry(urlObject, init);
  return response.json();
}
async function fetchWithRetry(url, init, options) {
  const {
    retries = _config__WEBPACK_IMPORTED_MODULE_0__.DEFAULT_RETRIES,
    timeouts = _config__WEBPACK_IMPORTED_MODULE_0__.DEFAULT_TIMEOUT,
    shouldSkipRetryFn = isNotTemporaryError
  } = options !== null && options !== void 0 ? options : {};
  let message = 'Unknown error.';
  let statusCode;
  for (let i = 1; i <= retries; i++) {
    try {
      var _timeouts;
      if (i > 1) {
        (0,_logs__WEBPACK_IMPORTED_MODULE_2__.logDebug)(`Retry request #${i}:`, url.toString(), statusCode);
      }
      const timeout = Array.isArray(timeouts) ? (_timeouts = timeouts[i - 1]) !== null && _timeouts !== void 0 ? _timeouts : timeouts[timeouts.length - 1] : Math.min(timeouts * i, MAX_TIMEOUT);
      const response = await fetchWithTimeout(url, init, timeout);
      statusCode = response.status;
      if (statusCode >= 400) {
        const {
          error
        } = await response.json().catch(() => undefined);
        throw new Error(error !== null && error !== void 0 ? error : `HTTP Error ${statusCode}`);
      }
      return response;
    } catch (err) {
      var _err$message;
      message = typeof err === 'string' ? err : (_err$message = err.message) !== null && _err$message !== void 0 ? _err$message : message;
      const shouldSkipRetry = shouldSkipRetryFn(message, statusCode);
      if (shouldSkipRetry) {
        throw new _api_errors__WEBPACK_IMPORTED_MODULE_1__.ApiServerError(message, statusCode);
      }
      if (i < retries) {
        await (0,_schedulers__WEBPACK_IMPORTED_MODULE_3__.pause)(_config__WEBPACK_IMPORTED_MODULE_0__.DEFAULT_ERROR_PAUSE * i);
      }
    }
  }
  throw new _api_errors__WEBPACK_IMPORTED_MODULE_1__.ApiServerError(message);
}
async function fetchWithTimeout(url, init) {
  let timeout = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : _config__WEBPACK_IMPORTED_MODULE_0__.DEFAULT_TIMEOUT;
  const controller = new AbortController();
  const id = setTimeout(() => {
    controller.abort();
  }, timeout);
  try {
    return await fetch(url, {
      ...init,
      signal: controller.signal,
      cache: 'no-cache' // TODO Remove it after a few releases
    });
  } finally {
    clearTimeout(id);
  }
}
async function handleFetchErrors(response, ignoreHttpCodes) {
  if (!response.ok && !(ignoreHttpCodes !== null && ignoreHttpCodes !== void 0 && ignoreHttpCodes.includes(response.status))) {
    var _error;
    // eslint-disable-next-line prefer-const
    let {
      error,
      errors
    } = await response.json().catch(() => undefined);
    if (!error && errors && errors.length) {
      var _errors$;
      error = (_errors$ = errors[0]) === null || _errors$ === void 0 ? void 0 : _errors$.msg;
    }
    throw new _api_errors__WEBPACK_IMPORTED_MODULE_1__.ApiServerError((_error = error) !== null && _error !== void 0 ? _error : `HTTP Error ${response.status}`, response.status);
  }
  return response;
}
function isNotTemporaryError(message, statusCode) {
  return statusCode && [400, 404].includes(statusCode);
}

/***/ }),

/***/ "./src/util/ledger/index.ts":
/*!**********************************!*\
  !*** ./src/util/ledger/index.ts ***!
  \**********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   buildLedgerTokenTransfer: () => (/* binding */ buildLedgerTokenTransfer),
/* harmony export */   checkTonApp: () => (/* binding */ checkTonApp),
/* harmony export */   connectLedger: () => (/* binding */ connectLedger),
/* harmony export */   getLedgerWalletAddress: () => (/* binding */ getLedgerWalletAddress),
/* harmony export */   getLedgerWalletInfo: () => (/* binding */ getLedgerWalletInfo),
/* harmony export */   getNextLedgerWallets: () => (/* binding */ getNextLedgerWallets),
/* harmony export */   getTonAppInfo: () => (/* binding */ getTonAppInfo),
/* harmony export */   importLedgerWallet: () => (/* binding */ importLedgerWallet),
/* harmony export */   reconnectLedger: () => (/* binding */ reconnectLedger),
/* harmony export */   signLedgerProof: () => (/* binding */ signLedgerProof),
/* harmony export */   signLedgerTransactions: () => (/* binding */ signLedgerTransactions),
/* harmony export */   submitLedgerNftTransfer: () => (/* binding */ submitLedgerNftTransfer),
/* harmony export */   submitLedgerStake: () => (/* binding */ submitLedgerStake),
/* harmony export */   submitLedgerTransfer: () => (/* binding */ submitLedgerTransfer),
/* harmony export */   submitLedgerUnstake: () => (/* binding */ submitLedgerUnstake),
/* harmony export */   verifyAddress: () => (/* binding */ verifyAddress),
/* harmony export */   waitLedgerTonApp: () => (/* binding */ waitLedgerTonApp)
/* harmony export */ });
/* harmony import */ var _ledgerhq_errors__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @ledgerhq/errors */ "./node_modules/@ledgerhq/errors/lib-es/index.js");
/* harmony import */ var _ledgerhq_hw_transport_webhid__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @ledgerhq/hw-transport-webhid */ "./node_modules/@ledgerhq/hw-transport-webhid/lib-es/TransportWebHID.js");
/* harmony import */ var _ledgerhq_hw_transport_webusb__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @ledgerhq/hw-transport-webusb */ "./node_modules/@ledgerhq/hw-transport-webusb/lib-es/TransportWebUSB.js");
/* harmony import */ var _ton_core__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ton/core */ "./node_modules/@ton/core/dist/index.js");
/* harmony import */ var _ton_core__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(_ton_core__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var _ton_community_ton_ledger__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @ton-community/ton-ledger */ "./node_modules/@ton-community/ton-ledger/dist/index.js");
/* harmony import */ var _ton_core_dist_address_Address__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ton/core/dist/address/Address */ "./node_modules/@ton/core/dist/address/Address.js");
/* harmony import */ var _ton_core_dist_address_Address__WEBPACK_IMPORTED_MODULE_5___default = /*#__PURE__*/__webpack_require__.n(_ton_core_dist_address_Address__WEBPACK_IMPORTED_MODULE_5__);
/* harmony import */ var _ton_core_dist_boc_Builder__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @ton/core/dist/boc/Builder */ "./node_modules/@ton/core/dist/boc/Builder.js");
/* harmony import */ var _ton_core_dist_boc_Cell__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @ton/core/dist/boc/Cell */ "./node_modules/@ton/core/dist/boc/Cell.js");
/* harmony import */ var _ton_core_dist_boc_Cell__WEBPACK_IMPORTED_MODULE_7___default = /*#__PURE__*/__webpack_require__.n(_ton_core_dist_boc_Cell__WEBPACK_IMPORTED_MODULE_7__);
/* harmony import */ var _ton_core_dist_types_SendMode__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @ton/core/dist/types/SendMode */ "./node_modules/@ton/core/dist/types/SendMode.js");
/* harmony import */ var _api_types__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ../../api/types */ "./src/api/types/index.ts");
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ../../config */ "./src/config.ts");
/* harmony import */ var _api__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! ../../api */ "./src/api/index.ts");
/* harmony import */ var _api_blockchains_ton_constants__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! ../../api/blockchains/ton/constants */ "./src/api/blockchains/ton/constants.ts");
/* harmony import */ var _api_blockchains_ton_util_tonCore__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! ../../api/blockchains/ton/util/tonCore */ "./src/api/blockchains/ton/util/tonCore.ts");
/* harmony import */ var _api_errors__WEBPACK_IMPORTED_MODULE_14__ = __webpack_require__(/*! ../../api/errors */ "./src/api/errors.ts");
/* harmony import */ var _account__WEBPACK_IMPORTED_MODULE_15__ = __webpack_require__(/*! ../account */ "./src/util/account.ts");
/* harmony import */ var _compareVersions__WEBPACK_IMPORTED_MODULE_16__ = __webpack_require__(/*! ../compareVersions */ "./src/util/compareVersions.ts");
/* harmony import */ var _logs__WEBPACK_IMPORTED_MODULE_17__ = __webpack_require__(/*! ../logs */ "./src/util/logs.ts");
/* harmony import */ var _schedulers__WEBPACK_IMPORTED_MODULE_18__ = __webpack_require__(/*! ../schedulers */ "./src/util/schedulers.ts");
/* harmony import */ var _utils__WEBPACK_IMPORTED_MODULE_19__ = __webpack_require__(/*! ./utils */ "./src/util/ledger/utils.ts");
/* provided dependency */ var Buffer = __webpack_require__(/*! buffer */ "./node_modules/buffer/index.js")["Buffer"];




















const CHAIN = 0; // workchain === -1 ? 255 : 0;
const WALLET_VERSION = 'v4R2';
const INTERNAL_WALLET_VERSION = 'v4';
const ATTEMPTS = 10;
const PAUSE = 125;
const IS_BOUNCEABLE = false;
const VERSION_WITH_UNSAFE = '2.1.0';
const VERSION_WITH_JETTON_ID = '2.2.0';
const VESTING_SUBWALLET_ID = 0x10C;
const knownJettonAddresses = _ton_community_ton_ledger__WEBPACK_IMPORTED_MODULE_4__.KNOWN_JETTONS.map(_ref => {
  let {
    masterAddress
  } = _ref;
  return masterAddress.toString({
    bounceable: true,
    urlSafe: true
  });
});
let transport;
let tonTransport;
async function importLedgerWallet(network, accountIndex) {
  const walletInfo = await getLedgerWalletInfo(network, accountIndex);
  return (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('importLedgerWallet', network, walletInfo);
}
async function reconnectLedger() {
  try {
    var _tonTransport;
    if (tonTransport && (await ((_tonTransport = tonTransport) === null || _tonTransport === void 0 ? void 0 : _tonTransport.isAppOpen()))) {
      return true;
    }
  } catch {
    // do nothing
  }
  return (await connectLedger()) && (await waitLedgerTonApp());
}
async function connectLedger() {
  try {
    if (await _ledgerhq_hw_transport_webhid__WEBPACK_IMPORTED_MODULE_1__["default"].isSupported()) {
      transport = await connectHID();
    } else if (await _ledgerhq_hw_transport_webusb__WEBPACK_IMPORTED_MODULE_2__["default"].isSupported()) {
      transport = await connectUSB();
    } else {
      (0,_logs__WEBPACK_IMPORTED_MODULE_17__.logDebugError)('connectLedger: HID and/or USB are not supported');
      return false;
    }
    tonTransport = new _ton_community_ton_ledger__WEBPACK_IMPORTED_MODULE_4__.TonTransport(transport);
    return true;
  } catch (err) {
    (0,_logs__WEBPACK_IMPORTED_MODULE_17__.logDebugError)('connectLedger', err);
    return false;
  }
}
function waitLedgerTonAppDeadline() {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve(false);
    }, PAUSE * ATTEMPTS);
  });
}
async function checkTonApp() {
  for (let i = 0; i < ATTEMPTS; i++) {
    try {
      const isTonOpen = await tonTransport.isAppOpen();
      if (isTonOpen) {
        var _tonTransport2;
        // Workaround for Ledger S, this is a way to check if it is unlocked.
        // There will be an error with code 0x530c
        await ((_tonTransport2 = tonTransport) === null || _tonTransport2 === void 0 ? void 0 : _tonTransport2.getAddress(getLedgerAccountPathByIndex(0), {
          walletVersion: INTERNAL_WALLET_VERSION
        }));
        return true;
      }
    } catch (err) {
      if (!(err !== null && err !== void 0 && err.message.includes('0x530c'))) {
        (0,_logs__WEBPACK_IMPORTED_MODULE_17__.logDebugError)('waitLedgerTonApp', err);
      }
    }
    await (0,_schedulers__WEBPACK_IMPORTED_MODULE_18__.pause)(PAUSE);
  }
  return false;
}
function waitLedgerTonApp() {
  return Promise.race([checkTonApp(), waitLedgerTonAppDeadline()]);
}
async function connectHID() {
  for (let i = 0; i < ATTEMPTS; i++) {
    const [device] = await _ledgerhq_hw_transport_webhid__WEBPACK_IMPORTED_MODULE_1__["default"].list();
    if (!device) {
      await _ledgerhq_hw_transport_webhid__WEBPACK_IMPORTED_MODULE_1__["default"].create();
      await (0,_schedulers__WEBPACK_IMPORTED_MODULE_18__.pause)(PAUSE);
      continue;
    }
    if (device.opened) {
      return new _ledgerhq_hw_transport_webhid__WEBPACK_IMPORTED_MODULE_1__["default"](device);
    } else {
      return _ledgerhq_hw_transport_webhid__WEBPACK_IMPORTED_MODULE_1__["default"].open(device);
    }
  }
  throw new Error('Failed to connect');
}
async function connectUSB() {
  for (let i = 0; i < ATTEMPTS; i++) {
    const [device] = await _ledgerhq_hw_transport_webusb__WEBPACK_IMPORTED_MODULE_2__["default"].list();
    if (!device) {
      await _ledgerhq_hw_transport_webusb__WEBPACK_IMPORTED_MODULE_2__["default"].create();
      await (0,_schedulers__WEBPACK_IMPORTED_MODULE_18__.pause)(PAUSE);
      continue;
    }
    if (device.opened) {
      var _await$TransportWebUS;
      return (_await$TransportWebUS = await _ledgerhq_hw_transport_webusb__WEBPACK_IMPORTED_MODULE_2__["default"].openConnected()) !== null && _await$TransportWebUS !== void 0 ? _await$TransportWebUS : await _ledgerhq_hw_transport_webusb__WEBPACK_IMPORTED_MODULE_2__["default"].request();
    } else {
      return _ledgerhq_hw_transport_webusb__WEBPACK_IMPORTED_MODULE_2__["default"].open(device);
    }
  }
  throw new Error('Failed to connect');
}
async function submitLedgerStake(accountId, amount, type, fee) {
  const {
    network
  } = (0,_account__WEBPACK_IMPORTED_MODULE_15__.parseAccountId)(accountId);
  const address = await (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('fetchAddress', accountId);
  let result;
  if (type === 'liquid') {
    amount += _config__WEBPACK_IMPORTED_MODULE_10__.ONE_TON;
    const payload = {
      type: 'tonstakers-deposit',
      queryId: 0n,
      // eslint-disable-next-line no-null/no-null
      appId: null
    };
    result = await submitLedgerTransfer({
      accountId,
      password: '',
      toAddress: _config__WEBPACK_IMPORTED_MODULE_10__.LIQUID_POOL,
      amount
    }, _config__WEBPACK_IMPORTED_MODULE_10__.TONCOIN_SLUG, {
      type: 'stake'
    }, payload);
  } else {
    const backendState = await (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('fetchBackendStakingState', address);
    const poolAddress = (0,_api_blockchains_ton_util_tonCore__WEBPACK_IMPORTED_MODULE_13__.toBase64Address)(backendState.nominatorsPool.address, true, network);
    result = await submitLedgerTransfer({
      accountId,
      password: '',
      toAddress: poolAddress,
      amount,
      comment: _api_blockchains_ton_constants__WEBPACK_IMPORTED_MODULE_12__.STAKE_COMMENT,
      fee
    }, _config__WEBPACK_IMPORTED_MODULE_10__.TONCOIN_SLUG, {
      type: 'stake'
    });
  }
  if (result) {
    await (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('updateAccountMemoryCache', accountId, address, {
      stakedAt: Date.now()
    });
  }
  await (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('onStakingChangeExpected');
  return result;
}
async function submitLedgerUnstake(accountId, type, amount) {
  const {
    network
  } = (0,_account__WEBPACK_IMPORTED_MODULE_15__.parseAccountId)(accountId);
  const address = await (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('fetchAddress', accountId);
  const {
    backendState,
    state: stakingState
  } = await (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('getStakingState', accountId);
  let result;
  if (type === 'liquid') {
    const tokenWalletAddress = await (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('resolveTokenWalletAddress', network, address, _config__WEBPACK_IMPORTED_MODULE_10__.LIQUID_JETTON);
    const mode = stakingState.type === 'liquid' && !stakingState.instantAvailable ? _api_types__WEBPACK_IMPORTED_MODULE_9__.ApiLiquidUnstakeMode.BestRate : _api_types__WEBPACK_IMPORTED_MODULE_9__.ApiLiquidUnstakeMode.Default;
    const fillOrKill = false;
    const waitTillRoundEnd = mode === _api_types__WEBPACK_IMPORTED_MODULE_9__.ApiLiquidUnstakeMode.BestRate;
    const payload = {
      type: 'jetton-burn',
      queryId: 0n,
      amount,
      responseDestination: _ton_core_dist_address_Address__WEBPACK_IMPORTED_MODULE_5__.Address.parse(address),
      customPayload: (0,_api_blockchains_ton_util_tonCore__WEBPACK_IMPORTED_MODULE_13__.buildLiquidStakingWithdrawCustomPayload)(fillOrKill, waitTillRoundEnd)
    };
    result = await submitLedgerTransfer({
      accountId,
      password: '',
      toAddress: tokenWalletAddress,
      amount: _config__WEBPACK_IMPORTED_MODULE_10__.ONE_TON
    }, _config__WEBPACK_IMPORTED_MODULE_10__.TONCOIN_SLUG, {
      type: 'unstakeRequest'
    }, payload);
  } else {
    const poolAddress = (0,_api_blockchains_ton_util_tonCore__WEBPACK_IMPORTED_MODULE_13__.toBase64Address)(backendState.nominatorsPool.address, true, network);
    result = await submitLedgerTransfer({
      accountId,
      password: '',
      toAddress: poolAddress,
      amount: _config__WEBPACK_IMPORTED_MODULE_10__.ONE_TON,
      comment: _api_blockchains_ton_constants__WEBPACK_IMPORTED_MODULE_12__.UNSTAKE_COMMENT
    }, _config__WEBPACK_IMPORTED_MODULE_10__.TONCOIN_SLUG, {
      type: 'unstakeRequest'
    });
  }
  await (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('onStakingChangeExpected');
  return result;
}
async function submitLedgerTransfer(options, slug, localTransactionParams, payload) {
  const {
    accountId,
    tokenAddress,
    comment,
    fee
  } = options;
  let {
    toAddress,
    amount
  } = options;
  const {
    network
  } = (0,_account__WEBPACK_IMPORTED_MODULE_15__.parseAccountId)(accountId);
  await (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('waitLastTransfer', accountId);
  const fromAddress = await (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('fetchAddress', accountId);
  const [path, walletInfo, appInfo] = await Promise.all([getLedgerAccountPath(accountId), (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('getWalletInfo', network, fromAddress), getTonAppInfo()]);
  const {
    seqno,
    balance
  } = walletInfo;
  const parsedAddress = _ton_core_dist_address_Address__WEBPACK_IMPORTED_MODULE_5__.Address.parseFriendly(toAddress);
  let isBounceable = parsedAddress.isBounceable;
  const normalizedAddress = parsedAddress.address.toString({
    urlSafe: true,
    bounceable: _api_blockchains_ton_constants__WEBPACK_IMPORTED_MODULE_12__.DEFAULT_IS_BOUNCEABLE
  });
  const {
    isUnsafeSupported,
    isJettonIdSupported
  } = appInfo;
  if (tokenAddress) {
    ({
      toAddress,
      amount,
      payload
    } = await buildLedgerTokenTransfer(network, tokenAddress, fromAddress, toAddress, amount, comment, isJettonIdSupported));
    isBounceable = true;
  } else if (comment) {
    if ((0,_utils__WEBPACK_IMPORTED_MODULE_19__.isValidLedgerComment)(comment)) {
      payload = {
        type: 'comment',
        text: comment
      };
    } else if (isUnsafeSupported) {
      payload = {
        type: 'unsafe',
        message: buildCommentPayload(comment)
      };
    } else {
      return {
        error: _api_types__WEBPACK_IMPORTED_MODULE_9__.ApiTransactionError.NotSupportedHardwareOperation
      };
    }
  }
  const isFullTonBalance = !tokenAddress && balance === amount;
  const sendMode = isFullTonBalance ? _ton_core_dist_types_SendMode__WEBPACK_IMPORTED_MODULE_8__.SendMode.CARRY_ALL_REMAINING_BALANCE : _ton_core_dist_types_SendMode__WEBPACK_IMPORTED_MODULE_8__.SendMode.PAY_GAS_SEPARATELY + _ton_core_dist_types_SendMode__WEBPACK_IMPORTED_MODULE_8__.SendMode.IGNORE_ERRORS;
  try {
    const signedCell = await tonTransport.signTransaction(path, {
      to: _ton_core_dist_address_Address__WEBPACK_IMPORTED_MODULE_5__.Address.parse(toAddress),
      sendMode,
      seqno: seqno,
      timeout: getTransferExpirationTime(),
      bounce: isBounceable,
      amount: BigInt(amount),
      payload
    });
    const message = {
      base64: signedCell.toBoc().toString('base64'),
      seqno: seqno,
      params: {
        amount: options.amount,
        fromAddress: fromAddress,
        toAddress: normalizedAddress,
        comment,
        fee: fee,
        slug,
        ...localTransactionParams
      }
    };
    return await (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('sendSignedTransferMessage', accountId, message);
  } catch (err) {
    handleLedgerErrors(err);
    (0,_logs__WEBPACK_IMPORTED_MODULE_17__.logDebugError)('submitLedgerTransfer', err);
    return undefined;
  }
}
async function submitLedgerNftTransfer(options) {
  const {
    accountId,
    nftAddress,
    comment,
    nft,
    fee
  } = options;
  let {
    toAddress
  } = options;
  const {
    network
  } = (0,_account__WEBPACK_IMPORTED_MODULE_15__.parseAccountId)(accountId);
  await (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('waitLastTransfer', accountId);
  const fromAddress = await (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('fetchAddress', accountId);
  const [path, walletInfo, appInfo] = await Promise.all([getLedgerAccountPath(accountId), (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('getWalletInfo', network, fromAddress), getTonAppInfo()]);
  if (!appInfo.isUnsafeSupported) {
    return {
      error: _api_types__WEBPACK_IMPORTED_MODULE_9__.ApiTransactionError.NotSupportedHardwareOperation
    };
  }
  const {
    seqno
  } = walletInfo;
  const isNotcoinBurn = (nft === null || nft === void 0 ? void 0 : nft.collectionAddress) === _config__WEBPACK_IMPORTED_MODULE_10__.NOTCOIN_VOUCHERS_ADDRESS && (toAddress === _config__WEBPACK_IMPORTED_MODULE_10__.BURN_ADDRESS || _config__WEBPACK_IMPORTED_MODULE_10__.NOTCOIN_EXCHANGERS.includes(toAddress));
  // eslint-disable-next-line no-null/no-null
  let forwardPayload = null;
  let forwardAmount = _api_blockchains_ton_constants__WEBPACK_IMPORTED_MODULE_12__.NFT_TRANSFER_TONCOIN_FORWARD_AMOUNT;
  if (isNotcoinBurn) {
    ({
      forwardPayload,
      toAddress
    } = buildNotcoinVoucherExchange(nftAddress, nft.index));
    forwardAmount = 50000000n;
  } else if (comment) {
    forwardPayload = buildCommentPayload(comment);
  }
  try {
    const signedCell = await tonTransport.signTransaction(path, {
      to: _ton_core_dist_address_Address__WEBPACK_IMPORTED_MODULE_5__.Address.parse(nftAddress),
      sendMode: _ton_core_dist_types_SendMode__WEBPACK_IMPORTED_MODULE_8__.SendMode.PAY_GAS_SEPARATELY + _ton_core_dist_types_SendMode__WEBPACK_IMPORTED_MODULE_8__.SendMode.IGNORE_ERRORS,
      seqno: seqno,
      timeout: getTransferExpirationTime(),
      bounce: true,
      amount: _api_blockchains_ton_constants__WEBPACK_IMPORTED_MODULE_12__.NFT_TRANSFER_TONCOIN_AMOUNT,
      payload: {
        type: 'nft-transfer',
        queryId: 0n,
        newOwner: _ton_core_dist_address_Address__WEBPACK_IMPORTED_MODULE_5__.Address.parse(toAddress),
        responseDestination: _ton_core_dist_address_Address__WEBPACK_IMPORTED_MODULE_5__.Address.parse(fromAddress),
        // eslint-disable-next-line no-null/no-null
        customPayload: null,
        forwardAmount,
        forwardPayload
      }
    });
    const message = {
      base64: signedCell.toBoc().toString('base64'),
      seqno: seqno,
      params: {
        amount: _api_blockchains_ton_constants__WEBPACK_IMPORTED_MODULE_12__.NFT_TRANSFER_TONCOIN_AMOUNT,
        fromAddress: fromAddress,
        toAddress: options.toAddress,
        comment,
        fee: fee,
        slug: _config__WEBPACK_IMPORTED_MODULE_10__.TONCOIN_SLUG,
        type: 'nftTransferred',
        nft,
        normalizedAddress: (0,_api_blockchains_ton_util_tonCore__WEBPACK_IMPORTED_MODULE_13__.toBase64Address)(nftAddress, true, network)
      }
    };
    return await (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('sendSignedTransferMessage', accountId, message);
  } catch (error) {
    (0,_logs__WEBPACK_IMPORTED_MODULE_17__.logDebugError)('submitLedgerNftTransfer', error);
    return undefined;
  }
}
function buildNotcoinVoucherExchange(nftAddress, nftIndex) {
  // eslint-disable-next-line no-bitwise
  const first4Bits = _ton_core_dist_address_Address__WEBPACK_IMPORTED_MODULE_5__.Address.parse(nftAddress).hash.readUint8() >> 4;
  const toAddress = _config__WEBPACK_IMPORTED_MODULE_10__.NOTCOIN_EXCHANGERS[first4Bits];
  const forwardPayload = new _ton_core_dist_boc_Builder__WEBPACK_IMPORTED_MODULE_6__.Builder().storeUint(0x5fec6642, 32).storeUint(nftIndex, 64).endCell();
  return {
    forwardPayload,
    toAddress
  };
}
async function buildLedgerTokenTransfer(network, tokenAddress, fromAddress, toAddress, amount, comment, isJettonIdSupported) {
  const tokenWalletAddress = await (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('resolveTokenWalletAddress', network, fromAddress, tokenAddress);
  const realTokenAddress = await (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('resolveTokenMinterAddress', network, tokenWalletAddress);
  if (tokenAddress !== realTokenAddress) {
    throw new Error('Invalid contract');
  }

  // eslint-disable-next-line no-null/no-null
  const forwardPayload = comment ? buildCommentPayload(comment) : null;
  const payload = {
    type: 'jetton-transfer',
    queryId: 0n,
    amount,
    destination: _ton_core_dist_address_Address__WEBPACK_IMPORTED_MODULE_5__.Address.parse(toAddress),
    responseDestination: _ton_core_dist_address_Address__WEBPACK_IMPORTED_MODULE_5__.Address.parse(fromAddress),
    // eslint-disable-next-line no-null/no-null
    customPayload: null,
    forwardAmount: _api_blockchains_ton_constants__WEBPACK_IMPORTED_MODULE_12__.TOKEN_TRANSFER_TONCOIN_FORWARD_AMOUNT,
    forwardPayload,
    // eslint-disable-next-line no-null/no-null
    knownJetton: isJettonIdSupported ? getKnownJettonId(tokenAddress) : null
  };
  return {
    amount: _api_blockchains_ton_constants__WEBPACK_IMPORTED_MODULE_12__.TOKEN_TRANSFER_TONCOIN_AMOUNT,
    toAddress: tokenWalletAddress,
    payload
  };
}
function getKnownJettonId(tokenAddress) {
  const index = knownJettonAddresses.indexOf(tokenAddress);
  // eslint-disable-next-line no-null/no-null
  return index > -1 ? {
    jettonId: index,
    workchain: _api_blockchains_ton_constants__WEBPACK_IMPORTED_MODULE_12__.WORKCHAIN
  } : null;
}
function buildCommentPayload(comment) {
  const bytes = (0,_api_blockchains_ton_util_tonCore__WEBPACK_IMPORTED_MODULE_13__.commentToBytes)(comment);
  return (0,_api_blockchains_ton_util_tonCore__WEBPACK_IMPORTED_MODULE_13__.packBytesAsSnakeCell)(bytes);
}
async function signLedgerTransactions(accountId, messages, options) {
  const {
    isTonConnect,
    vestingAddress
  } = options !== null && options !== void 0 ? options : {};
  const {
    network
  } = (0,_account__WEBPACK_IMPORTED_MODULE_15__.parseAccountId)(accountId);
  await (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('waitLastTransfer', accountId);
  const [path, fromAddress, appInfo] = await Promise.all([getLedgerAccountPath(accountId), (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('fetchAddress', accountId), getTonAppInfo()]);
  const {
    isUnsafeSupported,
    isJettonIdSupported
  } = appInfo;
  if (isTonConnect && !isUnsafeSupported) {
    throw new _api_errors__WEBPACK_IMPORTED_MODULE_14__.ApiUnsupportedVersionError('Please update Ledger TON app.');
  }
  const seqno = await (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('getWalletSeqno', accountId, vestingAddress);
  const walletSpecifiers = vestingAddress ? {
    subwalletId: VESTING_SUBWALLET_ID,
    includeWalletOp: false
  } : undefined;
  const preparedParams = await Promise.all(messages.map(async (message, index) => {
    var _ledgerPayload;
    const {
      toAddress,
      amount,
      stateInit: stateInitBase64,
      rawPayload
    } = message;
    const isBounceable = _ton_core_dist_address_Address__WEBPACK_IMPORTED_MODULE_5__.Address.isFriendly(toAddress) ? _ton_core_dist_address_Address__WEBPACK_IMPORTED_MODULE_5__.Address.parseFriendly(toAddress).isBounceable : _api_blockchains_ton_constants__WEBPACK_IMPORTED_MODULE_12__.DEFAULT_IS_BOUNCEABLE;
    let ledgerPayload;
    if (rawPayload) {
      ledgerPayload = (0,_ton_community_ton_ledger__WEBPACK_IMPORTED_MODULE_4__.parseMessage)(_ton_core_dist_boc_Cell__WEBPACK_IMPORTED_MODULE_7__.Cell.fromBase64(rawPayload), {
        disallowModification: true
      });
    }
    if (((_ledgerPayload = ledgerPayload) === null || _ledgerPayload === void 0 ? void 0 : _ledgerPayload.type) === 'jetton-transfer') {
      const tokenAddress = await (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('resolveTokenMinterAddress', network, toAddress);
      // eslint-disable-next-line no-null/no-null
      ledgerPayload.knownJetton = isJettonIdSupported ? getKnownJettonId(tokenAddress) : null;
    }
    const stateInit = stateInitBase64 ? (0,_ton_core__WEBPACK_IMPORTED_MODULE_3__.loadStateInit)(_ton_core_dist_boc_Cell__WEBPACK_IMPORTED_MODULE_7__.Cell.fromBase64(stateInitBase64).asSlice()) : undefined;
    return {
      to: _ton_core_dist_address_Address__WEBPACK_IMPORTED_MODULE_5__.Address.parse(toAddress),
      sendMode: _ton_core_dist_types_SendMode__WEBPACK_IMPORTED_MODULE_8__.SendMode.PAY_GAS_SEPARATELY + _ton_core_dist_types_SendMode__WEBPACK_IMPORTED_MODULE_8__.SendMode.IGNORE_ERRORS,
      seqno: seqno + index,
      timeout: getTransferExpirationTime(),
      bounce: isBounceable,
      amount: BigInt(amount),
      payload: ledgerPayload,
      walletSpecifiers,
      stateInit
    };
  }));
  const signedMessages = [];
  const attempts = ATTEMPTS + preparedParams.length;
  let index = 0;
  let attempt = 0;
  while (index < preparedParams.length && attempt < attempts) {
    const params = preparedParams[index];
    const message = messages[index];
    try {
      var _message$payload;
      const base64 = (await tonTransport.signTransaction(path, params)).toBoc().toString('base64');
      signedMessages.push({
        base64,
        seqno: params.seqno,
        params: {
          amount: message.amount,
          fromAddress: fromAddress,
          toAddress: message.toAddress,
          comment: ((_message$payload = message.payload) === null || _message$payload === void 0 ? void 0 : _message$payload.type) === 'comment' ? message.payload.comment : undefined,
          fee: 0n,
          slug: _config__WEBPACK_IMPORTED_MODULE_10__.TONCOIN_SLUG
        }
      });
      index++;
    } catch (err) {
      handleLedgerErrors(err);
      (0,_logs__WEBPACK_IMPORTED_MODULE_17__.logDebugError)('signLedgerTransactions', err);
    }
    attempt++;
  }
  return signedMessages;
}
async function signLedgerProof(accountId, proof) {
  const path = await getLedgerAccountPath(accountId);
  const {
    timestamp,
    domain,
    payload
  } = proof;
  const result = await tonTransport.getAddressProof(path, {
    domain,
    timestamp,
    payload: Buffer.from(payload)
  });
  return result.signature.toString('base64');
}
async function getNextLedgerWallets(network) {
  let lastExistingIndex = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : -1;
  let alreadyImportedAddresses = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : [];
  const result = [];
  let index = lastExistingIndex + 1;
  try {
    // eslint-disable-next-line no-constant-condition
    while (true) {
      const walletInfo = await getLedgerWalletInfo(network, index);
      if (alreadyImportedAddresses.includes(walletInfo.address)) {
        index += 1;
        continue;
      }
      if (walletInfo.balance !== 0n) {
        result.push(walletInfo);
        index += 1;
        continue;
      }
      if (!result.length) {
        result.push(walletInfo);
      }
      return result;
    }
  } catch (err) {
    return (0,_api_errors__WEBPACK_IMPORTED_MODULE_14__.handleServerError)(err);
  }
}
async function getLedgerWalletInfo(network, accountIndex) {
  var _deviceModel, _deviceModel2;
  const {
    address,
    publicKey
  } = await getLedgerWalletAddress(accountIndex);
  const balance = await (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('getWalletBalance', network, address);
  return {
    index: accountIndex,
    address,
    publicKey: publicKey.toString('hex'),
    balance,
    version: WALLET_VERSION,
    driver: 'HID',
    deviceId: (_deviceModel = transport.deviceModel) === null || _deviceModel === void 0 ? void 0 : _deviceModel.id,
    deviceName: (_deviceModel2 = transport.deviceModel) === null || _deviceModel2 === void 0 ? void 0 : _deviceModel2.productName
  };
}
function getLedgerWalletAddress(index, isTestnet) {
  const path = getLedgerAccountPathByIndex(index, isTestnet);
  return tonTransport.getAddress(path, {
    chain: CHAIN,
    bounceable: _api_blockchains_ton_constants__WEBPACK_IMPORTED_MODULE_12__.WALLET_IS_BOUNCEABLE,
    walletVersion: INTERNAL_WALLET_VERSION
  });
}
async function verifyAddress(accountId) {
  const path = await getLedgerAccountPath(accountId);
  await tonTransport.validateAddress(path, {
    bounceable: IS_BOUNCEABLE,
    walletVersion: INTERNAL_WALLET_VERSION
  });
}
async function getLedgerAccountPath(accountId) {
  const accountInfo = await (0,_api__WEBPACK_IMPORTED_MODULE_11__.callApi)('fetchAccount', accountId);
  const index = accountInfo.ledger.index;
  return getLedgerAccountPathByIndex(index);
}
function getLedgerAccountPathByIndex(index, isTestnet) {
  let workchain = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : _api_blockchains_ton_constants__WEBPACK_IMPORTED_MODULE_12__.WORKCHAIN;
  const network = isTestnet ? 1 : 0;
  const chain = workchain === -1 ? 255 : 0;
  return [44, 607, network, chain, index, 0];
}
function getTransferExpirationTime() {
  return Math.floor(Date.now() / 1000 + _api_blockchains_ton_constants__WEBPACK_IMPORTED_MODULE_12__.TRANSFER_TIMEOUT_SEC);
}
async function getTonAppInfo() {
  var _deviceModel3;
  const version = await tonTransport.getVersion();
  const isUnsafeSupported = (0,_compareVersions__WEBPACK_IMPORTED_MODULE_16__["default"])(version, VERSION_WITH_UNSAFE) >= 0;
  const isJettonIdSupported = (0,_compareVersions__WEBPACK_IMPORTED_MODULE_16__["default"])(version, VERSION_WITH_JETTON_ID) >= 0 && ((_deviceModel3 = transport.deviceModel) === null || _deviceModel3 === void 0 ? void 0 : _deviceModel3.id) !== 'nanoS';
  return {
    version,
    isUnsafeSupported,
    isJettonIdSupported
  };
}
function handleLedgerErrors(err) {
  if (err !== null && err !== void 0 && err.message.includes('(0xbd00)')) {
    throw new _api_errors__WEBPACK_IMPORTED_MODULE_14__.ApiHardwareBlindSigningNotEnabled();
  }
  if ((err === null || err === void 0 ? void 0 : err.statusCode) === _ledgerhq_errors__WEBPACK_IMPORTED_MODULE_0__.StatusCodes.CONDITIONS_OF_USE_NOT_SATISFIED) {
    throw new _api_errors__WEBPACK_IMPORTED_MODULE_14__.ApiUserRejectsError();
  }
}

/***/ }),

/***/ "./src/util/ledger/utils.ts":
/*!**********************************!*\
  !*** ./src/util/ledger/utils.ts ***!
  \**********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   isValidLedgerComment: () => (/* binding */ isValidLedgerComment)
/* harmony export */ });
/* unused harmony export isLedgerCommentLengthValid */
/* harmony import */ var _stringFormat__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../stringFormat */ "./src/util/stringFormat.ts");

const MAX_COMMENT_SIZE = 120;
function isValidLedgerComment(comment) {
  return (0,_stringFormat__WEBPACK_IMPORTED_MODULE_0__.isAscii)(comment) && isLedgerCommentLengthValid(comment);
}
function isLedgerCommentLengthValid(comment) {
  return comment.length <= MAX_COMMENT_SIZE;
}

/***/ }),

/***/ "./src/util/stringFormat.ts":
/*!**********************************!*\
  !*** ./src/util/stringFormat.ts ***!
  \**********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   isAscii: () => (/* binding */ isAscii)
/* harmony export */ });
/* unused harmony export insertSubstring */
function isAscii(str) {
  for (let i = 0; i < str.length; i++) {
    if (str.charCodeAt(i) > 127) {
      return false;
    }
  }
  return true;
}
function insertSubstring(str, start, newSubStr) {
  if (start < 0) {
    start = str.length - start;
  }
  return str.slice(0, start) + newSubStr + str.slice(start);
}

/***/ })

}]);
//# sourceMappingURL=src_util_fetch_ts-src_util_ledger_index_ts.10d7315e7af5701fb98e.js.map