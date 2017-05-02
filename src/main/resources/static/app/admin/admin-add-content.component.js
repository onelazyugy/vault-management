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
var addentrymodel_1 = require('./addentrymodel');
var admin_services_1 = require('../services/admin-services');
var AdminAddContent = (function () {
    function AdminAddContent(AdminService) {
        this.AdminService = AdminService;
        this.addEntryResults = [];
        this.categories = ['bank', 'shop', 'bill', 'miscellaneous'];
        this.entryModel = new addentrymodel_1.AddEntryModel('default', '', '', '', '', '');
        this.hasCategoryError = false;
        this.responseMsg = '';
        this.successOrDangerClass = '';
        this.success = false;
    }
    AdminAddContent.prototype.addEntry = function (addEntryForm) {
        var _this = this;
        var password = addEntryForm.value.password;
        var confirmPassword = addEntryForm.value.passwordConfirm;
        if (password !== confirmPassword) {
            this.success = false;
            this.successOrDangerClass = 'danger';
            this.responseMsg = 'passwords do not match. Try again!';
            return false;
        }
        this.AdminService.addEntry(this.entryModel).subscribe(function (data) {
            if (data != null) {
                if (data.success) {
                    _this.addEntryResults = data;
                    _this.success = data.success;
                    _this.successOrDangerClass = 'success';
                    _this.responseMsg = data.message;
                    addEntryForm.reset();
                }
                else {
                    _this.addEntryResults = data;
                    _this.success = data.success;
                    _this.successOrDangerClass = 'danger';
                    _this.responseMsg = data.message;
                    addEntryForm.reset();
                }
            }
            else {
                _this.addEntryResults = data;
                _this.successOrDangerClass = 'danger';
                _this.responseMsg = 'data is null';
            }
        }, function (error) {
            _this.success = false;
            console.error('Error addEntry: ' + error);
            _this.responseMsg = 'error saving entry.';
            _this.successOrDangerClass = 'danger';
            return false;
        }, function () {
            console.log('Completed addEntry request');
        });
    };
    AdminAddContent.prototype.validateCategory = function (value) {
        if (value === 'default') {
            this.hasCategoryError = true;
        }
        else {
            this.hasCategoryError = false;
        }
    };
    AdminAddContent.prototype.onTagInputChange = function (value, addEntryForm) {
        this.entryModel.tag = value;
    };
    AdminAddContent.prototype.onUserNameInputChange = function (value, addEntryForm) {
        this.entryModel.username = value;
    };
    AdminAddContent.prototype.onPasswordInputChange = function (value, addEntryForm) {
        this.entryModel.password = value;
    };
    AdminAddContent.prototype.onPasswordConfirmInputChange = function (value, addEntryForm) {
        this.entryModel.confirmPassword = value;
    };
    AdminAddContent.prototype.onComentChange = function (value, addEntryForm) {
        this.entryModel.comment = value;
    };
    AdminAddContent = __decorate([
        core_1.Component({
            selector: 'admin-add',
            templateUrl: 'app/admin/admin-add-content.component.html',
            providers: [admin_services_1.AdminService]
        }), 
        __metadata('design:paramtypes', [admin_services_1.AdminService])
    ], AdminAddContent);
    return AdminAddContent;
}());
exports.AdminAddContent = AdminAddContent;
//# sourceMappingURL=admin-add-content.component.js.map