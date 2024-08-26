"use strict";
(globalThis["webpackChunkmytonwallet"] = globalThis["webpackChunkmytonwallet"] || []).push([[4],{

/***/ 315:
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   SecureStoragePluginWeb: () => (/* binding */ SecureStoragePluginWeb)
/* harmony export */ });
/* harmony import */ var _capacitor_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(313);

class SecureStoragePluginWeb extends _capacitor_core__WEBPACK_IMPORTED_MODULE_0__.WebPlugin {
    constructor() {
        super(...arguments);
        this.PREFIX = 'cap_sec_';
        this.addPrefix = (key) => this.PREFIX + key;
        this.removePrefix = (key) => key.replace(this.PREFIX, '');
    }
    get(options) {
        const value = localStorage.getItem(this.addPrefix(options.key));
        return value !== null
            ? Promise.resolve({
                value: atob(value),
            })
            : Promise.reject('Item with given key does not exist');
    }
    set(options) {
        localStorage.setItem(this.addPrefix(options.key), btoa(options.value));
        return Promise.resolve({ value: true });
    }
    remove(options) {
        if (localStorage.getItem(this.addPrefix(options.key))) {
            localStorage.removeItem(this.addPrefix(options.key));
            return Promise.resolve({ value: true });
        }
        else {
            return Promise.reject('Item with given key does not exist');
        }
    }
    clear() {
        for (const key in localStorage) {
            if (key.indexOf(this.PREFIX) === 0) {
                localStorage.removeItem(key);
            }
        }
        return Promise.resolve({ value: true });
    }
    keys() {
        const keys = Object.keys(localStorage)
            .filter(k => k.indexOf(this.PREFIX) === 0)
            .map(this.removePrefix);
        return Promise.resolve({ value: keys });
    }
    getPlatform() {
        return Promise.resolve({ value: 'web' });
    }
}
//# sourceMappingURL=web.js.map

/***/ })

}]);