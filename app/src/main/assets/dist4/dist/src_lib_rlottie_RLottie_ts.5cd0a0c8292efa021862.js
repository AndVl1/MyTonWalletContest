"use strict";
(self["webpackChunkmytonwallet"] = self["webpackChunkmytonwallet"] || []).push([["src_lib_rlottie_RLottie_ts"],{

/***/ "./src/lib/rlottie/RLottie.ts":
/*!************************************!*\
  !*** ./src/lib/rlottie/RLottie.ts ***!
  \************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _util_animation__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../../util/animation */ "./src/util/animation.ts");
/* harmony import */ var _util_cycleRestrict__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../util/cycleRestrict */ "./src/util/cycleRestrict.ts");
/* harmony import */ var _util_generateUniqueId__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../util/generateUniqueId */ "./src/util/generateUniqueId.ts");
/* harmony import */ var _util_launchMediaWorkers__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../util/launchMediaWorkers */ "./src/util/launchMediaWorkers.ts");
/* harmony import */ var _util_windowEnvironment__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../util/windowEnvironment */ "./src/util/windowEnvironment.ts");
/* harmony import */ var _fasterdom_fasterdom__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../fasterdom/fasterdom */ "./src/lib/fasterdom/fasterdom.ts");
function _defineProperty(obj, key, value) { key = _toPropertyKey(key); if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }
function _toPropertyKey(arg) { var key = _toPrimitive(arg, "string"); return typeof key === "symbol" ? key : String(key); }
function _toPrimitive(input, hint) { if (typeof input !== "object" || input === null) return input; var prim = input[Symbol.toPrimitive]; if (prim !== undefined) { var res = prim.call(input, hint || "default"); if (typeof res !== "object") return res; throw new TypeError("@@toPrimitive must return a primitive value."); } return (hint === "string" ? String : Number)(input); }






const WAITING = Symbol('WAITING');
const HIGH_PRIORITY_QUALITY = _util_windowEnvironment__WEBPACK_IMPORTED_MODULE_4__.IS_ANDROID || _util_windowEnvironment__WEBPACK_IMPORTED_MODULE_4__.IS_IOS ? 0.75 : 1;
const LOW_PRIORITY_QUALITY = _util_windowEnvironment__WEBPACK_IMPORTED_MODULE_4__.IS_ANDROID ? 0.5 : 0.75;
const LOW_PRIORITY_QUALITY_SIZE_THRESHOLD = 24;
const HIGH_PRIORITY_CACHE_MODULO = _util_windowEnvironment__WEBPACK_IMPORTED_MODULE_4__.IS_SAFARI ? 2 : 4;
const LOW_PRIORITY_CACHE_MODULO = 0;
const workers = (0,_util_launchMediaWorkers__WEBPACK_IMPORTED_MODULE_3__["default"])().map(_ref => {
  let {
    connector
  } = _ref;
  return connector;
});
const instancesByRenderId = new Map();
let lastWorkerIndex = -1;
class RLottie {
  static init() {
    for (var _len = arguments.length, args = new Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }
    const [, canvas, renderId, params, viewId = (0,_util_generateUniqueId__WEBPACK_IMPORTED_MODULE_2__["default"])(),, onLoad] = args;
    let instance = instancesByRenderId.get(renderId);
    if (!instance) {
      // eslint-disable-next-line prefer-rest-params
      instance = new RLottie(...args);
      instancesByRenderId.set(renderId, instance);
    } else {
      instance.addView(viewId, canvas, onLoad, params === null || params === void 0 ? void 0 : params.coords);
    }
    return instance;
  }
  constructor(tgsUrl, container, renderId, params) {
    let viewId = arguments.length > 4 && arguments[4] !== undefined ? arguments[4] : (0,_util_generateUniqueId__WEBPACK_IMPORTED_MODULE_2__["default"])();
    let customColor = arguments.length > 5 ? arguments[5] : undefined;
    let onLoad = arguments.length > 6 ? arguments[6] : undefined;
    let onEnded = arguments.length > 7 ? arguments[7] : undefined;
    let onLoop = arguments.length > 8 ? arguments[8] : undefined;
    this.tgsUrl = tgsUrl;
    this.container = container;
    this.renderId = renderId;
    this.params = params;
    this.customColor = customColor;
    this.onLoad = onLoad;
    this.onEnded = onEnded;
    this.onLoop = onLoop;
    // Config
    _defineProperty(this, "views", new Map());
    _defineProperty(this, "imgSize", void 0);
    _defineProperty(this, "imageData", void 0);
    _defineProperty(this, "msPerFrame", 1000 / 60);
    _defineProperty(this, "reduceFactor", 1);
    _defineProperty(this, "cacheModulo", void 0);
    _defineProperty(this, "workerIndex", void 0);
    _defineProperty(this, "frames", []);
    _defineProperty(this, "framesCount", void 0);
    // State
    _defineProperty(this, "isAnimating", false);
    _defineProperty(this, "isWaiting", true);
    _defineProperty(this, "isEnded", false);
    _defineProperty(this, "isDestroyed", false);
    _defineProperty(this, "isRendererInited", false);
    _defineProperty(this, "approxFrameIndex", 0);
    _defineProperty(this, "prevFrameIndex", -1);
    _defineProperty(this, "stopFrameIndex", 0);
    _defineProperty(this, "speed", 1);
    _defineProperty(this, "direction", 1);
    _defineProperty(this, "lastRenderAt", void 0);
    this.addView(viewId, container, onLoad, params.coords);
    this.initConfig();
    this.initRenderer();
  }
  removeView(viewId) {
    const {
      canvas,
      ctx,
      isSharedCanvas,
      coords
    } = this.views.get(viewId);
    if (isSharedCanvas) {
      ctx.clearRect(coords.x, coords.y, this.imgSize, this.imgSize);
    } else {
      canvas.remove();
    }
    this.views.delete(viewId);
    if (!this.views.size) {
      this.destroy();
    }
  }
  isPlaying() {
    return this.isAnimating || this.isWaiting;
  }
  play() {
    let forceRestart = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;
    let viewId = arguments.length > 1 ? arguments[1] : undefined;
    if (viewId) {
      this.views.get(viewId).isPaused = false;
    }
    if (this.isEnded && forceRestart) {
      this.approxFrameIndex = Math.floor(0);
    }
    this.stopFrameIndex = undefined;
    this.direction = 1;
    this.doPlay();
  }
  pause(viewId) {
    this.lastRenderAt = undefined;
    if (viewId) {
      this.views.get(viewId).isPaused = true;
      const areAllContainersPaused = Array.from(this.views.values()).every(_ref2 => {
        let {
          isPaused
        } = _ref2;
        return isPaused;
      });
      if (!areAllContainersPaused) {
        return;
      }
    }
    if (this.isWaiting) {
      this.stopFrameIndex = this.approxFrameIndex;
    } else {
      this.isAnimating = false;
    }
    if (!this.params.isLowPriority) {
      this.frames = this.frames.map((frame, i) => {
        if (i === this.prevFrameIndex) {
          return frame;
        } else {
          if (frame && frame !== WAITING) {
            frame.close();
          }
          return undefined;
        }
      });
    }
  }
  playSegment(_ref3) {
    let [startFrameIndex, stopFrameIndex] = _ref3;
    let forceRestart = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : false;
    let viewId = arguments.length > 2 ? arguments[2] : undefined;
    if (viewId) {
      this.views.get(viewId).isPaused = false;
    }
    const frameIndex = Math.round(this.approxFrameIndex);
    this.stopFrameIndex = Math.floor(stopFrameIndex / this.reduceFactor);
    if (frameIndex !== stopFrameIndex || forceRestart) {
      this.approxFrameIndex = Math.floor(startFrameIndex / this.reduceFactor);
    }
    this.direction = startFrameIndex < stopFrameIndex ? 1 : -1;
    this.doPlay();
  }
  setSpeed(speed) {
    this.speed = speed;
  }
  setNoLoop(noLoop) {
    this.params.noLoop = noLoop;
  }
  setSharedCanvasCoords(viewId, newCoords) {
    const containerInfo = this.views.get(viewId);
    const {
      canvas,
      ctx
    } = containerInfo;
    let [canvasWidth, canvasHeight] = [canvas.width, canvas.height];
    if (!canvas.dataset.isJustCleaned || canvas.dataset.isJustCleaned === 'false') {
      const sizeFactor = this.calcSizeFactor();
      [canvasWidth, canvasHeight] = ensureCanvasSize(canvas, sizeFactor);
      ctx.clearRect(0, 0, canvasWidth, canvasHeight);
      canvas.dataset.isJustCleaned = 'true';
      (0,_fasterdom_fasterdom__WEBPACK_IMPORTED_MODULE_5__.requestMeasure)(() => {
        canvas.dataset.isJustCleaned = 'false';
      });
    }
    containerInfo.coords = {
      x: Math.round(((newCoords === null || newCoords === void 0 ? void 0 : newCoords.x) || 0) * canvasWidth),
      y: Math.round(((newCoords === null || newCoords === void 0 ? void 0 : newCoords.y) || 0) * canvasHeight)
    };
    const frame = this.getFrame(this.prevFrameIndex) || this.getFrame(Math.round(this.approxFrameIndex));
    if (frame && frame !== WAITING) {
      ctx.drawImage(frame, containerInfo.coords.x, containerInfo.coords.y);
    }
  }
  addView(viewId, container, onLoad, coords) {
    const sizeFactor = this.calcSizeFactor();
    let imgSize;
    if (container instanceof HTMLDivElement) {
      if (!(container.parentNode instanceof HTMLElement)) {
        throw new Error('[RLottie] Container is not mounted');
      }
      const {
        size
      } = this.params;
      imgSize = Math.round(size * sizeFactor);
      if (!this.imgSize) {
        this.imgSize = imgSize;
        this.imageData = new ImageData(imgSize, imgSize);
      }
      (0,_fasterdom_fasterdom__WEBPACK_IMPORTED_MODULE_5__.requestMutation)(() => {
        const canvas = document.createElement('canvas');
        const ctx = canvas.getContext('2d');
        canvas.style.width = `${size}px`;
        canvas.style.height = `${size}px`;
        canvas.width = imgSize;
        canvas.height = imgSize;
        container.appendChild(canvas);
        this.views.set(viewId, {
          canvas,
          ctx,
          onLoad
        });
      });
    } else {
      if (!container.isConnected) {
        throw new Error('[RLottie] Shared canvas is not mounted');
      }
      const canvas = container;
      const ctx = canvas.getContext('2d');
      imgSize = Math.round(this.params.size * sizeFactor);
      if (!this.imgSize) {
        this.imgSize = imgSize;
        this.imageData = new ImageData(imgSize, imgSize);
      }
      const [canvasWidth, canvasHeight] = ensureCanvasSize(canvas, sizeFactor);
      this.views.set(viewId, {
        canvas,
        ctx,
        isSharedCanvas: true,
        coords: {
          x: Math.round(coords.x * canvasWidth),
          y: Math.round(coords.y * canvasHeight)
        },
        onLoad
      });
    }
    if (this.isRendererInited) {
      this.doPlay();
    }
  }
  calcSizeFactor() {
    const {
      size,
      isLowPriority,
      // Reduced quality only looks acceptable on big enough images
      quality = isLowPriority && (!size || size > LOW_PRIORITY_QUALITY_SIZE_THRESHOLD) ? LOW_PRIORITY_QUALITY : HIGH_PRIORITY_QUALITY
    } = this.params;

    // Reduced quality only looks acceptable on high DPR screens
    return Math.max(_util_windowEnvironment__WEBPACK_IMPORTED_MODULE_4__.DPR * quality, 1);
  }
  destroy() {
    this.isDestroyed = true;
    this.pause();
    this.clearCache();
    this.destroyRenderer();
    instancesByRenderId.delete(this.renderId);
  }
  clearCache() {
    this.frames.forEach(frame => {
      if (frame && frame !== WAITING) {
        frame.close();
      }
    });

    // Help GC
    this.imageData = undefined;
    this.frames = [];
  }
  initConfig() {
    const {
      isLowPriority
    } = this.params;
    this.cacheModulo = isLowPriority ? LOW_PRIORITY_CACHE_MODULO : HIGH_PRIORITY_CACHE_MODULO;
  }
  setColor(newColor) {
    this.customColor = newColor;
  }
  initRenderer() {
    this.workerIndex = (0,_util_cycleRestrict__WEBPACK_IMPORTED_MODULE_1__["default"])(_util_launchMediaWorkers__WEBPACK_IMPORTED_MODULE_3__.MAX_WORKERS, ++lastWorkerIndex);
    workers[this.workerIndex].request({
      name: 'rlottie:init',
      args: [this.renderId, this.tgsUrl, this.imgSize, this.params.isLowPriority || false, this.customColor, this.onRendererInit.bind(this)]
    });
  }
  destroyRenderer() {
    workers[this.workerIndex].request({
      name: 'rlottie:destroy',
      args: [this.renderId]
    });
  }
  onRendererInit(reduceFactor, msPerFrame, framesCount) {
    this.isRendererInited = true;
    this.reduceFactor = reduceFactor;
    this.msPerFrame = msPerFrame;
    this.framesCount = framesCount;
    if (this.isWaiting) {
      this.doPlay();
    }
  }
  changeData(tgsUrl) {
    this.pause();
    this.tgsUrl = tgsUrl;
    this.initConfig();
    workers[this.workerIndex].request({
      name: 'rlottie:changeData',
      args: [this.renderId, this.tgsUrl, this.params.isLowPriority || false, this.onChangeData.bind(this)]
    });
  }
  onChangeData(reduceFactor, msPerFrame, framesCount) {
    this.reduceFactor = reduceFactor;
    this.msPerFrame = msPerFrame;
    this.framesCount = framesCount;
    this.isWaiting = false;
    this.isAnimating = false;
    this.doPlay();
  }
  doPlay() {
    if (!this.framesCount) {
      return;
    }
    if (this.isDestroyed) {
      return;
    }
    if (this.isAnimating) {
      return;
    }
    if (!this.isWaiting) {
      this.lastRenderAt = undefined;
    }
    this.isEnded = false;
    this.isAnimating = true;
    this.isWaiting = false;
    (0,_util_animation__WEBPACK_IMPORTED_MODULE_0__.animate)(() => {
      if (this.isDestroyed) {
        return false;
      }

      // Paused from outside
      if (!this.isAnimating) {
        const areAllLoaded = Array.from(this.views.values()).every(_ref4 => {
          let {
            isLoaded
          } = _ref4;
          return isLoaded;
        });
        if (areAllLoaded) {
          return false;
        }
      }
      const frameIndex = Math.round(this.approxFrameIndex);
      const frame = this.getFrame(frameIndex);
      if (!frame || frame === WAITING) {
        if (!frame) {
          this.requestFrame(frameIndex);
        }
        this.isAnimating = false;
        this.isWaiting = true;
        return false;
      }
      if (this.cacheModulo && frameIndex % this.cacheModulo === 0) {
        this.cleanupPrevFrame(frameIndex);
      }
      if (frameIndex !== this.prevFrameIndex) {
        this.views.forEach(containerData => {
          const {
            ctx,
            isLoaded,
            isPaused,
            coords: {
              x,
              y
            } = {},
            onLoad
          } = containerData;
          if (!isLoaded || !isPaused) {
            ctx.clearRect(x || 0, y || 0, this.imgSize, this.imgSize);
            ctx.drawImage(frame, x || 0, y || 0);
          }
          if (!isLoaded) {
            containerData.isLoaded = true;
            onLoad === null || onLoad === void 0 ? void 0 : onLoad();
          }
        });
        this.prevFrameIndex = frameIndex;
      }
      const now = Date.now();
      const currentSpeed = this.lastRenderAt ? this.msPerFrame / (now - this.lastRenderAt) : 1;
      const delta = this.direction * this.speed / currentSpeed;
      const expectedNextFrameIndex = Math.round(this.approxFrameIndex + delta);
      this.lastRenderAt = now;

      // Forward animation finished
      if (delta > 0 && (frameIndex === this.framesCount - 1 || expectedNextFrameIndex > this.framesCount - 1)) {
        var _this$onLoop;
        if (this.params.noLoop) {
          var _this$onEnded;
          this.isAnimating = false;
          this.isEnded = true;
          (_this$onEnded = this.onEnded) === null || _this$onEnded === void 0 ? void 0 : _this$onEnded.call(this);
          return false;
        }
        (_this$onLoop = this.onLoop) === null || _this$onLoop === void 0 ? void 0 : _this$onLoop.call(this);
        this.approxFrameIndex = 0;

        // Backward animation finished
      } else if (delta < 0 && (frameIndex === 0 || expectedNextFrameIndex < 0)) {
        var _this$onLoop2;
        if (this.params.noLoop) {
          var _this$onEnded2;
          this.isAnimating = false;
          this.isEnded = true;
          (_this$onEnded2 = this.onEnded) === null || _this$onEnded2 === void 0 ? void 0 : _this$onEnded2.call(this);
          return false;
        }
        (_this$onLoop2 = this.onLoop) === null || _this$onLoop2 === void 0 ? void 0 : _this$onLoop2.call(this);
        this.approxFrameIndex = this.framesCount - 1;

        // Stop frame reached
      } else if (this.stopFrameIndex !== undefined && (frameIndex === this.stopFrameIndex || delta > 0 && expectedNextFrameIndex > this.stopFrameIndex || delta < 0 && expectedNextFrameIndex < this.stopFrameIndex)) {
        this.stopFrameIndex = undefined;
        this.isAnimating = false;
        return false;

        // Preparing next frame
      } else {
        this.approxFrameIndex += delta;
      }
      const nextFrameIndex = Math.round(this.approxFrameIndex);
      if (!this.getFrame(nextFrameIndex)) {
        this.requestFrame(nextFrameIndex);
        this.isWaiting = true;
        this.isAnimating = false;
        return false;
      }
      return true;
    }, _fasterdom_fasterdom__WEBPACK_IMPORTED_MODULE_5__.requestMutation);
  }
  getFrame(frameIndex) {
    return this.frames[frameIndex];
  }
  requestFrame(frameIndex) {
    this.frames[frameIndex] = WAITING;
    workers[this.workerIndex].request({
      name: 'rlottie:renderFrames',
      args: [this.renderId, frameIndex, this.onFrameLoad.bind(this)]
    });
  }
  cleanupPrevFrame(frameIndex) {
    if (this.framesCount < 3) {
      return;
    }
    const prevFrameIndex = (0,_util_cycleRestrict__WEBPACK_IMPORTED_MODULE_1__["default"])(this.framesCount, frameIndex - 1);
    this.frames[prevFrameIndex] = undefined;
  }
  onFrameLoad(frameIndex, imageBitmap) {
    if (this.frames[frameIndex] !== WAITING) {
      return;
    }
    this.frames[frameIndex] = imageBitmap;
    if (this.isWaiting) {
      this.doPlay();
    }
  }
}
function ensureCanvasSize(canvas, sizeFactor) {
  const expectedWidth = Math.round(canvas.offsetWidth * sizeFactor);
  const expectedHeight = Math.round(canvas.offsetHeight * sizeFactor);
  if (canvas.width !== expectedWidth || canvas.height !== expectedHeight) {
    (0,_fasterdom_fasterdom__WEBPACK_IMPORTED_MODULE_5__.requestMutation)(() => {
      canvas.width = expectedWidth;
      canvas.height = expectedHeight;
    });
  }
  return [expectedWidth, expectedHeight];
}
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (RLottie);

/***/ }),

/***/ "./src/util/cycleRestrict.ts":
/*!***********************************!*\
  !*** ./src/util/cycleRestrict.ts ***!
  \***********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (/* binding */ cycleRestrict)
/* harmony export */ });
function cycleRestrict(length, index) {
  return index - Math.floor(index / length) * length;
}

/***/ }),

/***/ "./src/util/launchMediaWorkers.ts":
/*!****************************************!*\
  !*** ./src/util/launchMediaWorkers.ts ***!
  \****************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   MAX_WORKERS: () => (/* binding */ MAX_WORKERS),
/* harmony export */   "default": () => (/* binding */ launchMediaWorkers)
/* harmony export */ });
/* harmony import */ var _config__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../config */ "./src/config.ts");
/* harmony import */ var _PostMessageConnector__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./PostMessageConnector */ "./src/util/PostMessageConnector.ts");


const MAX_WORKERS = Math.min(navigator.hardwareConcurrency || 4, 4);
let instances;
function launchMediaWorkers() {
  if (_config__WEBPACK_IMPORTED_MODULE_0__.IS_TEST) return [];
  if (!instances) {
    instances = new Array(MAX_WORKERS).fill(undefined).map(() => {
      const worker = new Worker(new URL(/* worker import */ __webpack_require__.p + __webpack_require__.u("src_lib_mediaWorker_index_worker_ts-src_util_bigint_ts-src_util_logs_ts"), __webpack_require__.b));
      const connector = (0,_PostMessageConnector__WEBPACK_IMPORTED_MODULE_1__.createConnector)(worker);
      return {
        worker,
        connector
      };
    });
  }
  return instances;
}

/***/ })

}]);
//# sourceMappingURL=src_lib_rlottie_RLottie_ts.5cd0a0c8292efa021862.js.map