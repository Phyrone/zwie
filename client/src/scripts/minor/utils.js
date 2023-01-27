"use strict";
var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (g && (g = 0, op[0] && (_ = 0)), _) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
var __spreadArray = (this && this.__spreadArray) || function (to, from, pack) {
    if (pack || arguments.length === 2) for (var i = 0, l = from.length, ar; i < l; i++) {
        if (ar || !(i in from)) {
            if (!ar) ar = Array.prototype.slice.call(from, 0, i);
            ar[i] = from[i];
        }
    }
    return to.concat(ar || Array.prototype.slice.call(from));
};
exports.__esModule = true;
exports.singleValue = exports.ionValueToObject = exports.QueueClosedException = exports.AsyncQueue = exports.awaitWsReady = void 0;
var ion_js_1 = require("ion-js");
function awaitWsReady(websocket) {
    return __awaiter(this, void 0, void 0, function () {
        return __generator(this, function (_a) {
            return [2 /*return*/, new Promise(function (resolve, reject) {
                    websocket.onopen = function () {
                        resolve();
                    };
                    websocket.onerror = function (e) {
                        reject(e);
                    };
                })];
        });
    });
}
exports.awaitWsReady = awaitWsReady;
var AsyncQueue = /** @class */ (function () {
    function AsyncQueue() {
        this.queue = [];
        this.waiting = [];
    }
    AsyncQueue.prototype.push = function (value) {
        var pair = this.waiting.shift();
        if (pair) {
            pair[0](value);
        }
        else {
            this.queue.push(value);
        }
    };
    AsyncQueue.prototype.pop = function () {
        return __awaiter(this, void 0, void 0, function () {
            var v;
            var _this = this;
            return __generator(this, function (_a) {
                v = this.queue.shift();
                if (v)
                    return [2 /*return*/, Promise.reject(v)];
                else
                    return [2 /*return*/, new Promise(function (resolve, reject) {
                            _this.waiting.push([resolve, reject]);
                        })];
                return [2 /*return*/];
            });
        });
    };
    AsyncQueue.prototype.close = function () {
        this.waiting.forEach(function (_a) {
            var _ = _a[0], reject = _a[1];
            reject(new QueueClosedException());
        });
        this.waiting = [];
        this.queue = [];
    };
    return AsyncQueue;
}());
exports.AsyncQueue = AsyncQueue;
var QueueClosedException = /** @class */ (function (_super) {
    __extends(QueueClosedException, _super);
    function QueueClosedException() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    return QueueClosedException;
}(Error));
exports.QueueClosedException = QueueClosedException;
function ionValueToObject(value) {
    switch (value.getType()) {
        case ion_js_1.IonTypes.NULL:
            return null;
        case ion_js_1.IonTypes.BOOL:
            return value.booleanValue();
        case ion_js_1.IonTypes.INT:
            return value.numberValue();
        case ion_js_1.IonTypes.FLOAT:
            return value.numberValue();
        case ion_js_1.IonTypes.DECIMAL:
            return value.decimalValue();
        case ion_js_1.IonTypes.STRING:
            return value.stringValue();
        case ion_js_1.IonTypes.SYMBOL:
            return value.stringValue();
        case ion_js_1.IonTypes.TIMESTAMP:
            return value.timestampValue();
        case ion_js_1.IonTypes.CLOB:
            return value.stringValue();
        case ion_js_1.IonTypes.BLOB:
            return value.uInt8ArrayValue();
        case ion_js_1.IonTypes.LIST:
            return value.elements().map(ionValueToObject);
        case ion_js_1.IonTypes.STRUCT:
            var annotations = value.getAnnotations();
            var obj = {};
            for (var _i = 0, _a = value.fields(); _i < _a.length; _i++) {
                var _b = _a[_i], fieldName = _b[0], fieldValue = _b[1];
                obj[fieldName] = ionValueToObject(fieldValue);
            }
            if (annotations.length > 0) {
                return __spreadArray(__spreadArray([], annotations, true), [obj], false);
            }
            else
                return obj;
        default:
            throw new Error("Unknown IonType: " + value.getType());
    }
}
exports.ionValueToObject = ionValueToObject;
function singleValue(writable) {
    return new Promise(function (resolve, reject) {
        writable.subscribe(function (v) {
            resolve(v);
        })();
    });
}
exports.singleValue = singleValue;
