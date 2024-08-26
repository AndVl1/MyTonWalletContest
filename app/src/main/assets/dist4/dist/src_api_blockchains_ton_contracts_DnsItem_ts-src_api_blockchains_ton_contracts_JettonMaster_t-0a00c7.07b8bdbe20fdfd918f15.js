(self["webpackChunkmytonwallet"] = self["webpackChunkmytonwallet"] || []).push([["src_api_blockchains_ton_contracts_DnsItem_ts-src_api_blockchains_ton_contracts_JettonMaster_t-0a00c7"],{

/***/ "./src/api/blockchains/ton/contracts/DnsItem.ts":
/*!******************************************************!*\
  !*** ./src/api/blockchains/ton/contracts/DnsItem.ts ***!
  \******************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   DnsItem: () => (/* binding */ DnsItem)
/* harmony export */ });
/* unused harmony export dnsItemConfigToCell */
/* harmony import */ var _ton_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @ton/core */ "./node_modules/@ton/core/dist/index.js");
/* harmony import */ var _ton_core__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_ton_core__WEBPACK_IMPORTED_MODULE_0__);

// eslint-disable-next-line @typescript-eslint/no-unused-vars
function dnsItemConfigToCell(config) {
  return (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.beginCell)().endCell();
}
class DnsItem {
  constructor(address, init) {
    this.address = address;
    this.init = init;
  }
  static createFromAddress(address) {
    return new DnsItem(address);
  }
  static createFromConfig(config, code) {
    let workchain = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : 0;
    const data = dnsItemConfigToCell(config);
    const init = {
      code,
      data
    };
    return new DnsItem((0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.contractAddress)(workchain, init), init);
  }

  // eslint-disable-next-line class-methods-use-this
  async getDomain(provider) {
    const res = await provider.get('get_domain', []);
    const domain = res.stack.readString();
    return domain;
  }

  // eslint-disable-next-line class-methods-use-this
  async getTelemintDomain(provider) {
    const res = await provider.get('get_domain_full', []);
    const domain = res.stack.readString();
    const parts = domain.replace(/\\u0000/g, '.').replace(/\.$/, '').split('.');
    parts.reverse();
    return parts.join('.');
  }

  // eslint-disable-next-line class-methods-use-this
  async getNftData(provider) {
    const res = await provider.get('get_nft_data', []);
    const index = res.stack.readBigNumber();
    const collectionAddress = res.stack.readAddress();
    const owner = res.stack.readAddressOpt();
    return {
      index,
      collectionAddress,
      owner
    };
  }
}

/***/ }),

/***/ "./src/api/blockchains/ton/contracts/JettonConstants.ts":
/*!**************************************************************!*\
  !*** ./src/api/blockchains/ton/contracts/JettonConstants.ts ***!
  \**************************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   Op: () => (/* binding */ Op)
/* harmony export */ });
/* unused harmony export Errors */
function _defineProperty(obj, key, value) { key = _toPropertyKey(key); if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }
function _toPropertyKey(arg) { var key = _toPrimitive(arg, "string"); return typeof key === "symbol" ? key : String(key); }
function _toPrimitive(input, hint) { if (typeof input !== "object" || input === null) return input; var prim = input[Symbol.toPrimitive]; if (prim !== undefined) { var res = prim.call(input, hint || "default"); if (typeof res !== "object") return res; throw new TypeError("@@toPrimitive must return a primitive value."); } return (hint === "string" ? String : Number)(input); }
// eslint-disable-next-line max-classes-per-file
class Op {}
_defineProperty(Op, "transfer", 0xf8a7ea5);
_defineProperty(Op, "transfer_notification", 0x7362d09c);
_defineProperty(Op, "internal_transfer", 0x178d4519);
_defineProperty(Op, "excesses", 0xd53276db);
_defineProperty(Op, "burn", 0x595f07bc);
_defineProperty(Op, "burn_notification", 0x7bdd97de);
_defineProperty(Op, "provide_wallet_address", 0x2c76b973);
_defineProperty(Op, "take_wallet_address", 0xd1735400);
_defineProperty(Op, "mint", 21);
_defineProperty(Op, "change_admin", 3);
_defineProperty(Op, "change_content", 4);
class Errors {}
_defineProperty(Errors, "invalid_op", 709);
_defineProperty(Errors, "not_admin", 73);
_defineProperty(Errors, "unouthorized_burn", 74);
_defineProperty(Errors, "discovery_fee_not_matched", 75);
_defineProperty(Errors, "wrong_op", 0xffff);
_defineProperty(Errors, "not_owner", 705);
_defineProperty(Errors, "not_enough_ton", 709);
_defineProperty(Errors, "not_enough_gas", 707);
_defineProperty(Errors, "not_valid_wallet", 707);
_defineProperty(Errors, "wrong_workchain", 333);
_defineProperty(Errors, "balance_error", 706);

/***/ }),

/***/ "./src/api/blockchains/ton/contracts/JettonMaster.ts":
/*!***********************************************************!*\
  !*** ./src/api/blockchains/ton/contracts/JettonMaster.ts ***!
  \***********************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   JettonMinter: () => (/* binding */ JettonMinter)
/* harmony export */ });
/* unused harmony exports jettonMinterConfigToCell, jettonContentToCell */
/* harmony import */ var _ton_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @ton/core */ "./node_modules/@ton/core/dist/index.js");
/* harmony import */ var _ton_core__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_ton_core__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var _JettonConstants__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./JettonConstants */ "./src/api/blockchains/ton/contracts/JettonConstants.ts");


function jettonMinterConfigToCell(config) {
  return (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.beginCell)().storeCoins(0).storeAddress(config.admin).storeRef(config.content).storeRef(config.wallet_code).endCell();
}
function jettonContentToCell(content) {
  return (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.beginCell)().storeUint(content.type, 8).storeStringTail(content.uri) // Snake logic under the hood
  .endCell();
}
class JettonMinter {
  constructor(address, init) {
    this.address = address;
    this.init = init;
  }
  static createFromAddress(address) {
    return new JettonMinter(address);
  }
  static createFromConfig(config, code) {
    let workchain = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : 0;
    const data = jettonMinterConfigToCell(config);
    const init = {
      code,
      data
    };
    return new JettonMinter((0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.contractAddress)(workchain, init), init);
  }

  // eslint-disable-next-line class-methods-use-this
  async sendDeploy(provider, via, value) {
    await provider.internal(via, {
      value,
      sendMode: _ton_core__WEBPACK_IMPORTED_MODULE_0__.SendMode.PAY_GAS_SEPARATELY,
      body: (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.beginCell)().endCell()
    });
  }
  static jettonInternalTransfer(jetton_amount, forward_ton_amount, response_addr) {
    let query_id = arguments.length > 3 && arguments[3] !== undefined ? arguments[3] : 0;
    return (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.beginCell)().storeUint(_JettonConstants__WEBPACK_IMPORTED_MODULE_1__.Op.internal_transfer, 32).storeUint(query_id, 64).storeCoins(jetton_amount).storeAddress(undefined).storeAddress(response_addr).storeCoins(forward_ton_amount).storeBit(false).endCell();
  }
  static mintMessage(from, to, jetton_amount, forward_ton_amount, total_ton_amount) {
    let query_id = arguments.length > 5 && arguments[5] !== undefined ? arguments[5] : 0;
    const mintMsg = (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.beginCell)().storeUint(_JettonConstants__WEBPACK_IMPORTED_MODULE_1__.Op.internal_transfer, 32).storeUint(0, 64).storeCoins(jetton_amount).storeAddress(undefined).storeAddress(from) // Response addr
    .storeCoins(forward_ton_amount).storeMaybeRef(undefined).endCell();
    return (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.beginCell)().storeUint(_JettonConstants__WEBPACK_IMPORTED_MODULE_1__.Op.mint, 32).storeUint(query_id, 64) // op, queryId
    .storeAddress(to).storeCoins(total_ton_amount).storeCoins(jetton_amount).storeRef(mintMsg).endCell();
  }
  async sendMint(provider, via, to, jetton_amount, forward_ton_amount, total_ton_amount) {
    if (total_ton_amount <= forward_ton_amount) {
      throw new Error('Total ton amount should be > forward amount');
    }
    await provider.internal(via, {
      sendMode: _ton_core__WEBPACK_IMPORTED_MODULE_0__.SendMode.PAY_GAS_SEPARATELY,
      body: JettonMinter.mintMessage(this.address, to, jetton_amount, forward_ton_amount, total_ton_amount),
      value: total_ton_amount + (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.toNano)('0.015')
    });
  }

  /* provide_wallet_address#2c76b973 query_id:uint64 owner_address:MsgAddress include_address:Bool = InternalMsgBody;
  */
  static discoveryMessage(owner, include_address) {
    return (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.beginCell)().storeUint(0x2c76b973, 32).storeUint(0, 64) // op, queryId
    .storeAddress(owner).storeBit(include_address).endCell();
  }

  // eslint-disable-next-line class-methods-use-this
  async sendDiscovery(provider, via, owner, include_address) {
    let value = arguments.length > 4 && arguments[4] !== undefined ? arguments[4] : (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.toNano)('0.1');
    await provider.internal(via, {
      sendMode: _ton_core__WEBPACK_IMPORTED_MODULE_0__.SendMode.PAY_GAS_SEPARATELY,
      body: JettonMinter.discoveryMessage(owner, include_address),
      value
    });
  }
  static changeAdminMessage(newOwner) {
    return (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.beginCell)().storeUint(_JettonConstants__WEBPACK_IMPORTED_MODULE_1__.Op.change_admin, 32).storeUint(0, 64) // op, queryId
    .storeAddress(newOwner).endCell();
  }

  // eslint-disable-next-line class-methods-use-this
  async sendChangeAdmin(provider, via, newOwner) {
    await provider.internal(via, {
      sendMode: _ton_core__WEBPACK_IMPORTED_MODULE_0__.SendMode.PAY_GAS_SEPARATELY,
      body: JettonMinter.changeAdminMessage(newOwner),
      value: (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.toNano)('0.05')
    });
  }
  static changeContentMessage(content) {
    return (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.beginCell)().storeUint(_JettonConstants__WEBPACK_IMPORTED_MODULE_1__.Op.change_content, 32).storeUint(0, 64) // op, queryId
    .storeRef(content).endCell();
  }

  // eslint-disable-next-line class-methods-use-this
  async sendChangeContent(provider, via, content) {
    await provider.internal(via, {
      sendMode: _ton_core__WEBPACK_IMPORTED_MODULE_0__.SendMode.PAY_GAS_SEPARATELY,
      body: JettonMinter.changeContentMessage(content),
      value: (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.toNano)('0.05')
    });
  }

  // eslint-disable-next-line class-methods-use-this
  async getWalletAddress(provider, owner) {
    const res = await provider.get('get_wallet_address', [{
      type: 'slice',
      cell: (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.beginCell)().storeAddress(owner).endCell()
    }]);
    return res.stack.readAddress();
  }

  // eslint-disable-next-line class-methods-use-this
  async getJettonData(provider) {
    const res = await provider.get('get_jetton_data', []);
    const totalSupply = res.stack.readBigNumber();
    const mintable = res.stack.readBoolean();
    const adminAddress = res.stack.readAddress();
    const content = res.stack.readCell();
    const walletCode = res.stack.readCell();
    return {
      totalSupply,
      mintable,
      adminAddress,
      content,
      walletCode
    };
  }
  async getTotalSupply(provider) {
    const res = await this.getJettonData(provider);
    return res.totalSupply;
  }
  async getAdminAddress(provider) {
    const res = await this.getJettonData(provider);
    return res.adminAddress;
  }
  async getContent(provider) {
    const res = await this.getJettonData(provider);
    return res.content;
  }
}

/***/ }),

/***/ "./src/api/blockchains/ton/contracts/JettonWallet.ts":
/*!***********************************************************!*\
  !*** ./src/api/blockchains/ton/contracts/JettonWallet.ts ***!
  \***********************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   JettonWallet: () => (/* binding */ JettonWallet)
/* harmony export */ });
/* unused harmony export jettonWalletConfigToCell */
/* harmony import */ var _ton_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @ton/core */ "./node_modules/@ton/core/dist/index.js");
/* harmony import */ var _ton_core__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_ton_core__WEBPACK_IMPORTED_MODULE_0__);

// eslint-disable-next-line @typescript-eslint/no-unused-vars
function jettonWalletConfigToCell(config) {
  return (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.beginCell)().endCell();
}
class JettonWallet {
  constructor(address, init) {
    this.address = address;
    this.init = init;
  }
  static createFromAddress(address) {
    return new JettonWallet(address);
  }
  static createFromConfig(config, code) {
    let workchain = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : 0;
    const data = jettonWalletConfigToCell(config);
    const init = {
      code,
      data
    };
    return new JettonWallet((0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.contractAddress)(workchain, init), init);
  }

  // eslint-disable-next-line class-methods-use-this
  async sendDeploy(provider, via, value) {
    await provider.internal(via, {
      value,
      sendMode: _ton_core__WEBPACK_IMPORTED_MODULE_0__.SendMode.PAY_GAS_SEPARATELY,
      body: (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.beginCell)().endCell()
    });
  }

  // eslint-disable-next-line class-methods-use-this
  async getJettonBalance(provider) {
    const state = await provider.getState();
    if (state.state.type !== 'active') {
      return 0n;
    }
    const res = await provider.get('get_wallet_data', []);
    return res.stack.readBigNumber();
  }
  static transferMessage(jetton_amount, to, responseAddress, customPayload, forward_ton_amount, forwardPayload) {
    return (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.beginCell)().storeUint(0xf8a7ea5, 32).storeUint(0, 64) // op, queryId
    .storeCoins(jetton_amount).storeAddress(to).storeAddress(responseAddress).storeMaybeRef(customPayload).storeCoins(forward_ton_amount).storeMaybeRef(forwardPayload).endCell();
  }

  // eslint-disable-next-line class-methods-use-this
  async sendTransfer(provider, via, value, jetton_amount, to, responseAddress, customPayload, forward_ton_amount, forwardPayload) {
    await provider.internal(via, {
      sendMode: _ton_core__WEBPACK_IMPORTED_MODULE_0__.SendMode.PAY_GAS_SEPARATELY,
      body: JettonWallet.transferMessage(jetton_amount, to, responseAddress, customPayload, forward_ton_amount, forwardPayload),
      value
    });
  }

  /*
    burn#595f07bc query_id:uint64 amount:(VarUInteger 16)
                  response_destination:MsgAddress custom_payload:(Maybe ^Cell)
                  = InternalMsgBody;
  */
  static burnMessage(jetton_amount, responseAddress, customPayload) {
    return (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.beginCell)().storeUint(0x595f07bc, 32).storeUint(0, 64) // op, queryId
    .storeCoins(jetton_amount).storeAddress(responseAddress).storeMaybeRef(customPayload).endCell();
  }

  // eslint-disable-next-line class-methods-use-this
  async sendBurn(provider, via, value, jetton_amount, responseAddress, customPayload) {
    await provider.internal(via, {
      sendMode: _ton_core__WEBPACK_IMPORTED_MODULE_0__.SendMode.PAY_GAS_SEPARATELY,
      body: JettonWallet.burnMessage(jetton_amount, responseAddress, customPayload),
      value
    });
  }

  /*
    withdraw_tons#107c49ef query_id:uint64 = InternalMsgBody;
  */
  static withdrawTonsMessage() {
    return (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.beginCell)().storeUint(0x6d8e5e3c, 32).storeUint(0, 64) // op, queryId
    .endCell();
  }

  // eslint-disable-next-line class-methods-use-this
  async sendWithdrawTons(provider, via) {
    await provider.internal(via, {
      sendMode: _ton_core__WEBPACK_IMPORTED_MODULE_0__.SendMode.PAY_GAS_SEPARATELY,
      body: JettonWallet.withdrawTonsMessage(),
      value: (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.toNano)('0.1')
    });
  }

  /*
    withdraw_jettons#10 query_id:uint64 wallet:MsgAddressInt amount:Coins = InternalMsgBody;
  */
  static withdrawJettonsMessage(from, amount) {
    return (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.beginCell)().storeUint(0x768a50b2, 32).storeUint(0, 64) // op, queryId
    .storeAddress(from).storeCoins(amount).storeMaybeRef(undefined).endCell();
  }

  // eslint-disable-next-line class-methods-use-this
  async sendWithdrawJettons(provider, via, from, amount) {
    await provider.internal(via, {
      sendMode: _ton_core__WEBPACK_IMPORTED_MODULE_0__.SendMode.PAY_GAS_SEPARATELY,
      body: JettonWallet.withdrawJettonsMessage(from, amount),
      value: (0,_ton_core__WEBPACK_IMPORTED_MODULE_0__.toNano)('0.1')
    });
  }

  // eslint-disable-next-line class-methods-use-this
  async getWalletData(provider) {
    const res = await provider.get('get_wallet_data', []);
    const balance = res.stack.readBigNumber();
    const owner = res.stack.readAddress();
    const minter = res.stack.readAddress();
    const code = res.stack.readCell();
    return {
      balance,
      owner,
      minter,
      code
    };
  }
}

/***/ }),

/***/ "./src/api/blockchains/ton/util/TonClient.ts":
/*!***************************************************!*\
  !*** ./src/api/blockchains/ton/util/TonClient.ts ***!
  \***************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   TonClient: () => (/* binding */ TonClient)
/* harmony export */ });
/* harmony import */ var axios__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! axios */ "./node_modules/axios/lib/axios.js");
/* harmony import */ var _ton_ton_dist_client_TonClient__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @ton/ton/dist/client/TonClient */ "./node_modules/@ton/ton/dist/client/TonClient.js");
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../../../config */ "./src/config.ts");
/* harmony import */ var _lib_axios_retry__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../../../lib/axios-retry */ "./src/lib/axios-retry/index.js");
/* harmony import */ var _lib_axios_retry__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(_lib_axios_retry__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _util_fetch__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../../../util/fetch */ "./src/util/fetch.ts");
/* harmony import */ var _util_logs__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../../../util/logs */ "./src/util/logs.ts");
function _defineProperty(obj, key, value) { key = _toPropertyKey(key); if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }
function _toPropertyKey(arg) { var key = _toPrimitive(arg, "string"); return typeof key === "symbol" ? key : String(key); }
function _toPrimitive(input, hint) { if (typeof input !== "object" || input === null) return input; var prim = input[Symbol.toPrimitive]; if (prim !== undefined) { var res = prim.call(input, hint || "default"); if (typeof res !== "object") return res; throw new TypeError("@@toPrimitive must return a primitive value."); } return (hint === "string" ? String : Number)(input); }






_lib_axios_retry__WEBPACK_IMPORTED_MODULE_2___default()(axios__WEBPACK_IMPORTED_MODULE_5__["default"], {
  retries: _config__WEBPACK_IMPORTED_MODULE_1__.DEFAULT_RETRIES,
  shouldResetTimeout: true,
  retryDelay: retryCount => {
    return retryCount * _config__WEBPACK_IMPORTED_MODULE_1__.DEFAULT_ERROR_PAUSE;
  },
  onRetry: (retryNumber, error, requestConfig) => {
    var _error$response;
    (0,_util_logs__WEBPACK_IMPORTED_MODULE_4__.logDebug)(`Retry request #${retryNumber}:`, requestConfig.url, (_error$response = error.response) === null || _error$response === void 0 ? void 0 : _error$response.status);
  }
});
class TonClient extends _ton_ton_dist_client_TonClient__WEBPACK_IMPORTED_MODULE_0__.TonClient {
  constructor(parameters) {
    super(parameters);
    _defineProperty(this, "initParameters", void 0);
    this.initParameters = parameters;
  }
  getWalletInfo(address) {
    return this.callRpc('getWalletInformation', {
      address
    });
  }
  getAddressInfo(address) {
    return this.callRpc('getAddressInformation', {
      address
    });
  }
  callRpc(method, params) {
    return this.sendRequest(this.parameters.endpoint, {
      id: 1,
      jsonrpc: '2.0',
      method,
      params
    });
  }
  async sendFile(src) {
    const boc = typeof src === 'object' ? src.toString('base64') : src;
    await this.callRpc('sendBocReturnHashNoError', {
      boc
    });
  }
  async sendRequest(apiUrl, request) {
    const method = request.method;
    const headers = {
      ...this.initParameters.headers,
      'Content-Type': 'application/json'
    };
    if (this.parameters.apiKey) {
      headers['X-API-Key'] = this.parameters.apiKey;
    }
    const body = JSON.stringify(request);
    const response = await (0,_util_fetch__WEBPACK_IMPORTED_MODULE_3__.fetchWithRetry)(apiUrl, {
      method: 'POST',
      body,
      headers
    }, {
      shouldSkipRetryFn: (message, statusCode) => isNotTemporaryError(method, message, statusCode)
    });
    const data = await response.json();
    return data.result;
  }
}
function isNotTemporaryError(method, message, statusCode) {
  return Boolean(statusCode === 422 || (message === null || message === void 0 ? void 0 : message.match(/(exit code|exitcode=|duplicate message)/)));
}

/***/ }),

/***/ "./src/lib/axios-fetch-adapter/index.js":
/*!**********************************************!*\
  !*** ./src/lib/axios-fetch-adapter/index.js ***!
  \**********************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (/* binding */ fetchAdapter)
/* harmony export */ });
/* harmony import */ var axios__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! axios */ "./node_modules/axios/lib/axios.js");
/* harmony import */ var axios_unsafe_core_buildFullPath__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! axios/unsafe/core/buildFullPath */ "./node_modules/axios/lib/core/buildFullPath.js");
/* harmony import */ var axios_unsafe_core_settle__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! axios/unsafe/core/settle */ "./node_modules/axios/lib/core/settle.js");
/* harmony import */ var axios_unsafe_helpers_buildURL__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! axios/unsafe/helpers/buildURL */ "./node_modules/axios/lib/helpers/buildURL.js");
/* harmony import */ var axios_unsafe_utils__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! axios/unsafe/utils */ "./node_modules/axios/lib/utils.js");





const {
  isFormData,
  isStandardBrowserEnv,
  isUndefined
} = axios_unsafe_utils__WEBPACK_IMPORTED_MODULE_0__["default"];

/**
 * - Create a request object
 * - Get response body
 * - Check if timeout
 */
async function fetchAdapter(config) {
  const request = createRequest(config);
  const promiseChain = [getResponse(request, config)];
  if (config.timeout && config.timeout > 0) {
    promiseChain.push(new Promise(res => {
      setTimeout(() => {
        const message = config.timeoutErrorMessage ? config.timeoutErrorMessage : `timeout of ${config.timeout}ms exceeded`;
        res(createError(message, config, 'ECONNABORTED', request));
      }, config.timeout);
    }));
  }
  const data = await Promise.race(promiseChain);
  return new Promise((resolve, reject) => {
    if (data instanceof Error) {
      reject(data);
    } else {
      // eslint-disable-next-line @typescript-eslint/no-unused-expressions
      Object.prototype.toString.call(config.settle) === '[object Function]' ? config.settle(resolve, reject, data) : (0,axios_unsafe_core_settle__WEBPACK_IMPORTED_MODULE_1__["default"])(resolve, reject, data);
    }
  });
}

/**
 * Fetch API stage two is to get response body. This funtion tries to retrieve
 * response body based on response's type
 */
async function getResponse(request, config) {
  let stageOne;
  try {
    stageOne = await fetch(request);
  } catch (e) {
    return createError('Network Error', config, 'ERR_NETWORK', request);
  }
  const response = {
    ok: stageOne.ok,
    status: stageOne.status,
    statusText: stageOne.statusText,
    headers: new Headers(stageOne.headers),
    // Make a copy of headers
    config,
    request
  };
  if (stageOne.status >= 200 && stageOne.status !== 204) {
    switch (config.responseType) {
      case 'arraybuffer':
        response.data = await stageOne.arrayBuffer();
        break;
      case 'blob':
        response.data = await stageOne.blob();
        break;
      case 'json':
        response.data = await stageOne.json();
        break;
      case 'formData':
        response.data = await stageOne.formData();
        break;
      default:
        response.data = await stageOne.text();
        break;
    }
  }
  return response;
}

/**
 * This function will create a Request object based on configuration's axios
 */
function createRequest(config) {
  const headers = new Headers(config.headers);

  // HTTP basic authentication
  if (config.auth) {
    const username = config.auth.username || '';
    const password = config.auth.password ? decodeURI(encodeURIComponent(config.auth.password)) : '';
    headers.set('Authorization', `Basic ${btoa(`${username}:${password}`)}`);
  }
  const method = config.method.toUpperCase();
  const options = {
    headers,
    method
  };
  if (method !== 'GET' && method !== 'HEAD') {
    options.body = config.data;

    // In these cases the browser will automatically set the correct Content-Type,
    // but only if that header hasn't been set yet. So that's why we're deleting it.
    if (isFormData(options.body) && isStandardBrowserEnv()) {
      headers.delete('Content-Type');
    }
  }
  if (config.mode) {
    options.mode = config.mode;
  }
  if (config.cache) {
    options.cache = config.cache;
  }
  if (config.integrity) {
    options.integrity = config.integrity;
  }
  if (config.redirect) {
    options.redirect = config.redirect;
  }
  if (config.referrer) {
    options.referrer = config.referrer;
  }
  // This config is similar to XHR’s withCredentials flag, but with three available values instead of two.
  // So if withCredentials is not set, default value 'same-origin' will be used
  if (!isUndefined(config.withCredentials)) {
    options.credentials = config.withCredentials ? 'include' : 'omit';
  }
  const fullPath = (0,axios_unsafe_core_buildFullPath__WEBPACK_IMPORTED_MODULE_2__["default"])(config.baseURL, config.url);
  const url = (0,axios_unsafe_helpers_buildURL__WEBPACK_IMPORTED_MODULE_3__["default"])(fullPath, config.params, config.paramsSerializer);

  // Expected browser to throw error if there is any wrong configuration value
  return new Request(url, options);
}

/**
 * Note:
 *
 *   From version >= 0.27.0, createError function is replaced by AxiosError class.
 *   So I copy the old createError function here for backward compatible.
 *
 *
 *
 * Create an Error with the specified message, config, error code, request and response.
 *
 * @param {string} message The error message.
 * @param {Object} config The config.
 * @param {string} [code] The error code (for example, 'ECONNABORTED').
 * @param {Object} [request] The request.
 * @param {Object} [response] The response.
 * @returns {Error} The created error.
 */
function createError(message, config, code, request, response) {
  if (axios__WEBPACK_IMPORTED_MODULE_4__["default"].AxiosError && typeof axios__WEBPACK_IMPORTED_MODULE_4__["default"].AxiosError === 'function') {
    return new axios__WEBPACK_IMPORTED_MODULE_4__["default"].AxiosError(message, axios__WEBPACK_IMPORTED_MODULE_4__["default"].AxiosError[code], config, request, response);
  }
  const error = new Error(message);
  return enhanceError(error, config, code, request, response);
}

/**
 *
 * Note:
 *
 *   This function is for backward compatible.
 *
 *
 * Update an Error with the specified config, error code, and response.
 *
 * @param {Error} error The error to update.
 * @param {Object} config The config.
 * @param {string} [code] The error code (for example, 'ECONNABORTED').
 * @param {Object} [request] The request.
 * @param {Object} [response] The response.
 * @returns {Error} The error.
 */
function enhanceError(error, config, code, request, response) {
  error.config = config;
  if (code) {
    error.code = code;
  }
  error.request = request;
  error.response = response;
  error.isAxiosError = true;
  error.toJSON = function toJSON() {
    return {
      // Standard
      message: this.message,
      name: this.name,
      // Microsoft
      description: this.description,
      number: this.number,
      // Mozilla
      fileName: this.fileName,
      lineNumber: this.lineNumber,
      columnNumber: this.columnNumber,
      stack: this.stack,
      // Axios
      config: this.config,
      code: this.code,
      // eslint-disable-next-line no-null/no-null
      status: this.response && this.response.status ? this.response.status : null
    };
  };
  return error;
}

/***/ }),

/***/ "./src/lib/axios-retry/index.js":
/*!**************************************!*\
  !*** ./src/lib/axios-retry/index.js ***!
  \**************************************/
/***/ (function(__unused_webpack_module, exports, __webpack_require__) {

"use strict";


var __awaiter = this && this.__awaiter || function (thisArg, _arguments, P, generator) {
  function adopt(value) {
    return value instanceof P ? value : new P(function (resolve) {
      resolve(value);
    });
  }
  return new (P || (P = Promise))(function (resolve, reject) {
    function fulfilled(value) {
      try {
        step(generator.next(value));
      } catch (e) {
        reject(e);
      }
    }
    function rejected(value) {
      try {
        step(generator["throw"](value));
      } catch (e) {
        reject(e);
      }
    }
    function step(result) {
      result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected);
    }
    step((generator = generator.apply(thisArg, _arguments || [])).next());
  });
};
var __importDefault = this && this.__importDefault || function (mod) {
  return mod && mod.__esModule ? mod : {
    "default": mod
  };
};
Object.defineProperty(exports, "__esModule", ({
  value: true
}));
exports.DEFAULT_OPTIONS = exports.exponentialDelay = exports.isNetworkOrIdempotentRequestError = exports.isIdempotentRequestError = exports.isSafeRequestError = exports.isRetryableError = exports.isNetworkError = exports.namespace = void 0;
const is_retry_allowed_1 = __importDefault(__webpack_require__(/*! ../is-retry-allowed */ "./src/lib/is-retry-allowed/index.js"));
exports.namespace = 'axios-retry';
function isNetworkError(error) {
  const CODE_EXCLUDE_LIST = ['ERR_CANCELED', 'ECONNABORTED'];
  if (error.response) {
    return false;
  }
  if (!error.code) {
    return false;
  }
  // Prevents retrying timed out & cancelled requests
  if (CODE_EXCLUDE_LIST.includes(error.code)) {
    return false;
  }
  // Prevents retrying unsafe errors
  return (0, is_retry_allowed_1.default)(error);
}
exports.isNetworkError = isNetworkError;
const SAFE_HTTP_METHODS = ['get', 'head', 'options'];
const IDEMPOTENT_HTTP_METHODS = SAFE_HTTP_METHODS.concat(['put', 'delete']);
function isRetryableError(error) {
  return error.code !== 'ECONNABORTED' && (!error.response || error.response.status >= 500 && error.response.status <= 599);
}
exports.isRetryableError = isRetryableError;
function isSafeRequestError(error) {
  var _a;
  if (!((_a = error.config) === null || _a === void 0 ? void 0 : _a.method)) {
    // Cannot determine if the request can be retried
    return false;
  }
  return isRetryableError(error) && SAFE_HTTP_METHODS.indexOf(error.config.method) !== -1;
}
exports.isSafeRequestError = isSafeRequestError;
function isIdempotentRequestError(error) {
  var _a;
  if (!((_a = error.config) === null || _a === void 0 ? void 0 : _a.method)) {
    // Cannot determine if the request can be retried
    return false;
  }
  return isRetryableError(error) && IDEMPOTENT_HTTP_METHODS.indexOf(error.config.method) !== -1;
}
exports.isIdempotentRequestError = isIdempotentRequestError;
function isNetworkOrIdempotentRequestError(error) {
  return isNetworkError(error) || isIdempotentRequestError(error);
}
exports.isNetworkOrIdempotentRequestError = isNetworkOrIdempotentRequestError;
function noDelay() {
  return 0;
}
function exponentialDelay() {
  let retryNumber = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : 0;
  let _error = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : undefined;
  let delayFactor = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : 100;
  const delay = Math.pow(2, retryNumber) * delayFactor;
  const randomSum = delay * 0.2 * Math.random(); // 0-20% of the delay
  return delay + randomSum;
}
exports.exponentialDelay = exponentialDelay;
exports.DEFAULT_OPTIONS = {
  retries: 3,
  retryCondition: isNetworkOrIdempotentRequestError,
  retryDelay: noDelay,
  shouldResetTimeout: false,
  onRetry: () => {}
};
function getRequestOptions(config, defaultOptions) {
  return Object.assign(Object.assign(Object.assign({}, exports.DEFAULT_OPTIONS), defaultOptions), config[exports.namespace]);
}
function setCurrentState(config, defaultOptions) {
  const currentState = getRequestOptions(config, defaultOptions || {});
  currentState.retryCount = currentState.retryCount || 0;
  currentState.lastRequestTime = currentState.lastRequestTime || Date.now();
  config[exports.namespace] = currentState;
  return currentState;
}
function fixConfig(axiosInstance, config) {
  // @ts-ignore
  if (axiosInstance.defaults.agent === config.agent) {
    // @ts-ignore
    delete config.agent;
  }
  if (axiosInstance.defaults.httpAgent === config.httpAgent) {
    delete config.httpAgent;
  }
  if (axiosInstance.defaults.httpsAgent === config.httpsAgent) {
    delete config.httpsAgent;
  }
}
function shouldRetry(currentState, error) {
  return __awaiter(this, void 0, void 0, function* () {
    const {
      retries,
      retryCondition
    } = currentState;
    const shouldRetryOrPromise = (currentState.retryCount || 0) < retries && retryCondition(error);
    // This could be a promise
    if (typeof shouldRetryOrPromise === 'object') {
      try {
        const shouldRetryPromiseResult = yield shouldRetryOrPromise;
        // keep return true unless shouldRetryPromiseResult return false for compatibility
        return shouldRetryPromiseResult !== false;
      } catch (_err) {
        return false;
      }
    }
    return shouldRetryOrPromise;
  });
}
const axiosRetry = (axiosInstance, defaultOptions) => {
  const requestInterceptorId = axiosInstance.interceptors.request.use(config => {
    setCurrentState(config, defaultOptions);
    return config;
  });
  const responseInterceptorId = axiosInstance.interceptors.response.use(null, error => __awaiter(void 0, void 0, void 0, function* () {
    const {
      config
    } = error;
    // If we have no information to retry the request
    if (!config) {
      return Promise.reject(error);
    }
    const currentState = setCurrentState(config, defaultOptions);
    if (yield shouldRetry(currentState, error)) {
      currentState.retryCount += 1;
      const {
        retryDelay,
        shouldResetTimeout,
        onRetry
      } = currentState;
      const delay = retryDelay(currentState.retryCount, error);
      // Axios fails merging this configuration to the default configuration because it has an issue
      // with circular structures: https://github.com/mzabriskie/axios/issues/370
      fixConfig(axiosInstance, config);
      if (!shouldResetTimeout && config.timeout && currentState.lastRequestTime) {
        const lastRequestDuration = Date.now() - currentState.lastRequestTime;
        const timeout = config.timeout - lastRequestDuration - delay;
        if (timeout <= 0) {
          return Promise.reject(error);
        }
        config.timeout = timeout;
      }
      config.transformRequest = [data => data];
      yield onRetry(currentState.retryCount, error, config);
      return new Promise(resolve => {
        setTimeout(() => resolve(axiosInstance(config)), delay);
      });
    }
    return Promise.reject(error);
  }));
  return {
    requestInterceptorId,
    responseInterceptorId
  };
};
// Compatibility with CommonJS
axiosRetry.isNetworkError = isNetworkError;
axiosRetry.isSafeRequestError = isSafeRequestError;
axiosRetry.isIdempotentRequestError = isIdempotentRequestError;
axiosRetry.isNetworkOrIdempotentRequestError = isNetworkOrIdempotentRequestError;
axiosRetry.exponentialDelay = exponentialDelay;
axiosRetry.isRetryableError = isRetryableError;
exports["default"] = axiosRetry;

/***/ }),

/***/ "./src/lib/is-retry-allowed/index.js":
/*!*******************************************!*\
  !*** ./src/lib/is-retry-allowed/index.js ***!
  \*******************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (/* binding */ isRetryAllowed)
/* harmony export */ });
const denyList = new Set(['ENOTFOUND', 'ENETUNREACH',
// SSL errors from https://github.com/nodejs/node/blob/fc8e3e2cdc521978351de257030db0076d79e0ab/src/crypto/crypto_common.cc#L301-L328
'UNABLE_TO_GET_ISSUER_CERT', 'UNABLE_TO_GET_CRL', 'UNABLE_TO_DECRYPT_CERT_SIGNATURE', 'UNABLE_TO_DECRYPT_CRL_SIGNATURE', 'UNABLE_TO_DECODE_ISSUER_PUBLIC_KEY', 'CERT_SIGNATURE_FAILURE', 'CRL_SIGNATURE_FAILURE', 'CERT_NOT_YET_VALID', 'CERT_HAS_EXPIRED', 'CRL_NOT_YET_VALID', 'CRL_HAS_EXPIRED', 'ERROR_IN_CERT_NOT_BEFORE_FIELD', 'ERROR_IN_CERT_NOT_AFTER_FIELD', 'ERROR_IN_CRL_LAST_UPDATE_FIELD', 'ERROR_IN_CRL_NEXT_UPDATE_FIELD', 'OUT_OF_MEM', 'DEPTH_ZERO_SELF_SIGNED_CERT', 'SELF_SIGNED_CERT_IN_CHAIN', 'UNABLE_TO_GET_ISSUER_CERT_LOCALLY', 'UNABLE_TO_VERIFY_LEAF_SIGNATURE', 'CERT_CHAIN_TOO_LONG', 'CERT_REVOKED', 'INVALID_CA', 'PATH_LENGTH_EXCEEDED', 'INVALID_PURPOSE', 'CERT_UNTRUSTED', 'CERT_REJECTED', 'HOSTNAME_MISMATCH']);

// TODO: Use `error?.code` when targeting Node.js 14
function isRetryAllowed(error) {
  return !denyList.has(error && error.code);
}

/***/ }),

/***/ "./src/util/withCacheAsync.ts":
/*!************************************!*\
  !*** ./src/util/withCacheAsync.ts ***!
  \************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (/* binding */ withCacheAsync)
/* harmony export */ });
const cache = new WeakMap();
function withCacheAsync(fn) {
  let canBeCached = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : value => !!value;
  return async function () {
    let fnCache = cache.get(fn);
    for (var _len = arguments.length, args = new Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }
    const cacheKey = buildCacheKey(args);
    if (fnCache) {
      const cached = fnCache.get(cacheKey);
      if (cached) {
        return cached;
      }
    } else {
      fnCache = new Map();
      cache.set(fn, fnCache);
    }
    const newValue = await fn(...args);
    if (canBeCached(newValue)) {
      fnCache.set(cacheKey, newValue);
    }
    return newValue;
  };
}
function buildCacheKey(args) {
  return args.reduce((cacheKey, arg) => {
    return `${cacheKey}_${typeof arg === 'object' ? JSON.stringify(args) : arg}`;
  }, '');
}

/***/ }),

/***/ "?dba7":
/*!************************!*\
  !*** crypto (ignored) ***!
  \************************/
/***/ (() => {

/* (ignored) */

/***/ })

}]);
//# sourceMappingURL=src_api_blockchains_ton_contracts_DnsItem_ts-src_api_blockchains_ton_contracts_JettonMaster_t-0a00c7.07b8bdbe20fdfd918f15.js.map