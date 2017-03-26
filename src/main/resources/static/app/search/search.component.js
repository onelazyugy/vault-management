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
var search_service_1 = require('../services/search-service');
var searchmodel_1 = require('./searchmodel');
require('rxjs/add/operator/debounceTime');
require('rxjs/add/operator/distinctUntilChanged');
var SearchComponent = (function () {
    /*
    txt: string;
    txtChanged: Subject<string> = new Subject<string>();
    */
    function SearchComponent(searchService) {
        this.searchService = searchService;
        this.model = new searchmodel_1.SearchModel('');
        this.searchResults = [];
    }
    SearchComponent.prototype.query = function () {
        var _this = this;
        console.log('login() model==>' + this.model.query);
        this.searchService.search(this.model).subscribe(function (data) {
            if (data) {
                console.log('search result: ' + data);
                _this.searchResults = data;
            }
        }, function (error) {
            console.log('search request error: ' + error);
        }, function () {
            console.log('Completed search request');
        });
    };
    SearchComponent = __decorate([
        core_1.Component({
            selector: 'search',
            templateUrl: 'app/search/search.component.html',
            providers: [search_service_1.SearchService]
        }), 
        __metadata('design:paramtypes', [search_service_1.SearchService])
    ], SearchComponent);
    return SearchComponent;
}());
exports.SearchComponent = SearchComponent;
//# sourceMappingURL=search.component.js.map