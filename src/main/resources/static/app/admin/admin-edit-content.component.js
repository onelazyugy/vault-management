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
var admin_services_1 = require("../services/admin-services");
var AdminEditContent = (function () {
    function AdminEditContent(AdminService) {
        this.AdminService = AdminService;
        this.entries = [];
        this.response = '';
        this.resultCount = 0;
    }
    AdminEditContent.prototype.ngOnInit = function () {
        console.log('onInit of AdminEditContent');
        // query all availble entries
        this.queryEntries();
    };
    AdminEditContent.prototype.queryEntries = function () {
        var _this = this;
        this.AdminService.queryEntries().subscribe(function (data) {
            if (data != null && data.adminEntryResponse != null) {
                console.log('Data from queryEntries: ' + JSON.stringify(data));
                if (data.adminEntryResponse.success) {
                    _this.entries = data.adminEntries;
                    _this.response = data.adminEntryResponse.message;
                    _this.resultCount = data.adminEntries.length;
                }
                else {
                    _this.response = data.adminEntryResponse.message;
                }
            }
        }, function (error) {
            _this.response = error;
            return false;
        }, function () {
            console.log('Completed queryEntries request');
        });
    };
    return AdminEditContent;
}());
AdminEditContent = __decorate([
    core_1.Component({
        selector: 'admin-edit',
        templateUrl: 'app/admin/admin-edit-content.component.html',
        providers: [admin_services_1.AdminService]
    }),
    __metadata("design:paramtypes", [admin_services_1.AdminService])
], AdminEditContent);
exports.AdminEditContent = AdminEditContent;
//# sourceMappingURL=admin-edit-content.component.js.map