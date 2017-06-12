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
var forms_1 = require("@angular/forms");
var search_service_1 = require("../services/search-service");
var searchmodel_1 = require("./searchmodel");
var enter_password_dialog_component_1 = require("./enter.password.dialog.component");
var ng2_bootstrap_modal_1 = require("ng2-bootstrap-modal");
require("rxjs/add/operator/debounceTime");
require("rxjs/add/operator/distinctUntilChanged");
require("rxjs/add/operator/debounceTime");
var SearchComponent = (function () {
    function SearchComponent(searchService, dialogService) {
        this.searchService = searchService;
        this.dialogService = dialogService;
        this.model = new searchmodel_1.SearchModel('');
        this.searchResults = [];
        this.serviceResponseMessage = '';
        this.searchString = '';
        this.seachControl = new forms_1.FormControl();
        //input data from the prompt dialog modal
        this.passwordFromPromptDialog = '';
        this.searchFormGroup = new forms_1.FormGroup({});
    }
    SearchComponent.prototype.view = function (id) {
        var _this = this;
        this.dialogService.addDialog(enter_password_dialog_component_1.PromptComponent, {
            title: 'Prompt',
            question: 'Enter your master password*: ',
            data: id
        }, { closeByClickingOutside: false, backdropColor: 'rgba(6, 25, 13, 0.57)' })
            .subscribe(function (message) {
            //We get dialog result
            console.log('input result from prompt dialog: ' + message);
            _this.passwordFromPromptDialog = message;
        });
    };
    SearchComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.seachControl
            .valueChanges
            .debounceTime(500)
            .subscribe(function (userSearchInput) {
            _this.resultCount = 0;
            _this.model.query = userSearchInput;
            _this.searchService.search(_this.model).subscribe(function (data) {
                if (data != null && data.queryResponses != null) {
                    if (data.queryResponses.length > 0) {
                        _this.searchResults = data;
                        _this.resultCount = data.queryResponses.length;
                        _this.serviceResponseMessage = data.serviceResponseStatus.message;
                        _this.serviceResponseStatus = data.serviceResponseStatus.success;
                    }
                }
                else {
                    _this.searchResults = data;
                }
            }, function (error) {
                console.log('search request error: ' + error);
            }, function () {
                console.log('Completed search request');
            });
        });
    };
    return SearchComponent;
}());
SearchComponent = __decorate([
    core_1.Component({
        selector: 'search',
        templateUrl: 'app/search/search.component.html',
        providers: [search_service_1.SearchService]
    })
    //https://blog.thoughtram.io/angular/2016/06/22/model-driven-forms-in-angular-2.html
    //http://stackoverflow.com/questions/32051273/angular2-and-debounce
    ,
    __metadata("design:paramtypes", [search_service_1.SearchService, ng2_bootstrap_modal_1.DialogService])
], SearchComponent);
exports.SearchComponent = SearchComponent;
//# sourceMappingURL=search.component.js.map