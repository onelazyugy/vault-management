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
var core_1 = require('@angular/core');
var http_1 = require('@angular/http');
var Observable_1 = require('rxjs/Observable');
require('rxjs/add/operator/map');
require('rxjs/add/operator/catch');
require('rxjs/add/operator/do');
var common_service_1 = require('./common-service');
var SearchService = (function () {
    function SearchService(_http, CommonService) {
        this._http = _http;
        this.CommonService = CommonService;
        this._searchURL = '';
        this._retrievePasswordByIdURL = '';
    }
    SearchService.prototype.search = function (searchModel) {
        this._searchURL = this.CommonService.buildRequestURL() + "/rs/search";
        var bodyRequest = JSON.stringify(searchModel);
        var headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        var options = new http_1.RequestOptions({ headers: headers });
        return this._http.post(this._searchURL, bodyRequest, options)
            .map(function (res) { return res.json(); })
            .do(function (data) { return console.log('/search api result ==>: ' + JSON.stringify(data)); })
            .catch(this.handleError);
    };
    SearchService.prototype.getPassword = function (retrievePasswordModel) {
        this._retrievePasswordByIdURL = this.CommonService.buildRequestURL() + "/rs/queryEntryById/" + retrievePasswordModel.id;
        return this._http.get(this._retrievePasswordByIdURL)
            .map(function (res) { return res.json(); })
            .do(function (data) { return console.log('/queryEntryById api result ==>: ' + JSON.stringify(data)); })
            .catch(this.handleError);
    };
    SearchService.prototype.handleError = function (error) {
        console.error('handleError ==>: ' + error);
        return Observable_1.Observable.throw(error.json().error || 'Server error');
    };
    SearchService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http, common_service_1.CommonService])
    ], SearchService);
    return SearchService;
}());
exports.SearchService = SearchService;
//# sourceMappingURL=search-service.js.map