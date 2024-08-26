"use strict";
(self["webpackChunkmytonwallet"] = self["webpackChunkmytonwallet"] || []).push([["mobile_plugins_native-dialog_dist_esm_web_js"],{

/***/ "./mobile/plugins/native-dialog/dist/esm/web.js":
/*!******************************************************!*\
  !*** ./mobile/plugins/native-dialog/dist/esm/web.js ***!
  \******************************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   DialogWeb: () => (/* binding */ DialogWeb)
/* harmony export */ });
/* harmony import */ var _capacitor_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @capacitor/core */ "./node_modules/@capacitor/core/dist/index.js");

class DialogWeb extends _capacitor_core__WEBPACK_IMPORTED_MODULE_0__.WebPlugin {
  async alert(options) {
    window.alert(options.message);
  }
  async prompt(options) {
    const val = window.prompt(options.message, options.inputText || '');
    return {
      value: val !== null ? val : '',
      cancelled: val === null
    };
  }
  async confirm(options) {
    const val = window.confirm(options.message);
    return {
      value: val
    };
  }
}

/***/ })

}]);
//# sourceMappingURL=mobile_plugins_native-dialog_dist_esm_web_js.115605cd50c17e118bdd.js.map