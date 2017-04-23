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
var forms_1 = require('@angular/forms');
var search_service_1 = require('../services/search-service');
var searchmodel_1 = require('./searchmodel');
require('rxjs/add/operator/debounceTime');
require('rxjs/add/operator/distinctUntilChanged');
require('rxjs/add/operator/debounceTime');
var SearchComponent = (function () {
    function SearchComponent(searchService) {
        this.searchService = searchService;
        this.model = new searchmodel_1.SearchModel('');
        this.searchResults = [];
        this.searchString = '';
        this.seachControl = new forms_1.FormControl();
        this.searchFormGroup = new forms_1.FormGroup({});
    }
    SearchComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.seachControl
            .valueChanges
            .debounceTime(500)
            .subscribe(function (userSearchInput) {
            _this.model.query = userSearchInput;
            _this.searchService.search(_this.model).subscribe(function (data) {
                if (data) {
                    _this.searchResults = data;
                }
            }, function (error) {
                console.log('search request error: ' + error);
            }, function () {
                console.log('Completed search request');
            });
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