"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var Observable_1 = require("rxjs/Observable");
require("rxjs/add/operator/map");
require("rxjs/add/operator/catch");
require("rxjs/add/operator/do");
var common_service_1 = require("./common-service");
var UserAuthService = (function () {
    function UserAuthService(_http, commonService) {
        this._http = _http;
        this.commonService = commonService;
        this._loginUrl = "";
        this._isUserStillAliveUrl = "";
        this._isUserLoggedIn = "";
        this._logoutUrl = "";
    }
    UserAuthService.prototype.login = function (user) {
        this._loginUrl = this.commonService.buildRequestURL() + "/rs/login"; //this.buildRequestURL() + "/rs/login";
        var bodyRequest = JSON.stringify(user);
        var headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        var options = new http_1.RequestOptions({ headers: headers });
        return this._http.post(this._loginUrl, bodyRequest, options)
            .map(function (res) { return res.json(); })
            .do(function (data) { return console.log('/login api result ==>: ' + JSON.stringify(data)); })
            .catch(this.handleError);
    };
    UserAuthService.prototype.userStillAlive = function () {
        this._isUserStillAliveUrl = this.commonService.buildRequestURL() + "/rs/userStillAlive"; //this.buildRequestURL() + "/rs/userStillAlive";
        return this._http.get(this._isUserStillAliveUrl)
            .map(function (res) { return res.json(); })
            .do(function (data) { return console.log('/isUserStillAlive api result ==>: ' + JSON.stringify(data)); })
            .catch(this.handleError);
    };
    UserAuthService.prototype.isUserLogin = function () {
        this._isUserLoggedIn = this.commonService.buildRequestURL() + "/rs/isUserLoggedIn"; //this.buildRequestURL() + "/rs/isUserLoggedIn";
        return this._http.get(this._isUserLoggedIn)
            .map(function (res) { return res.json(); })
            .do(function (data) { return console.log('/isUserLoggedIn api result ==>: ' + JSON.stringify(data)); })
            .catch(this.handleError);
        ;
    };
    UserAuthService.prototype.logout = function () {
        this._logoutUrl = this.commonService.buildRequestURL() + "/rs/logout"; //this.buildRequestURL() + "/rs/logout";
        return this._http.get(this._logoutUrl)
            .map(function (res) { return res.json(); })
            .do(function (data) { return console.log('/logout api result ==>: ' + JSON.stringify(data)); })
            .catch(this.handleError);
    };
    UserAuthService.prototype.handleError = function (error) {
        console.error('handleError ==>: ' + error);
        return Observable_1.Observable.throw(error.json().error || 'Server error');
    };
    return UserAuthService;
}());
UserAuthService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http, common_service_1.CommonService])
], UserAuthService);
exports.UserAuthService = UserAuthService;
//# sourceMappingURL=user-auth.service.js.map