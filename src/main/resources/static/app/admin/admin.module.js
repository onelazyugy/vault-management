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
var router_1 = require('@angular/router');
var common_1 = require('@angular/common'); //for ngIf and pipe and more...
var forms_1 = require('@angular/forms'); //for ngModel
var admin_add_content_component_1 = require('./admin-add-content.component');
var admin_edit_content_component_1 = require('./admin-edit-content.component');
var admin_component_1 = require('./admin.component');
var admin_guard_service_1 = require('./admin-guard.service');
var user_auth_service_1 = require('../services/user-auth.service');
var AdminModule = (function () {
    function AdminModule() {
    }
    AdminModule = __decorate([
        core_1.NgModule({
            declarations: [
                admin_add_content_component_1.AdminAddContent,
                admin_edit_content_component_1.AdminEditContent
            ],
            imports: [
                forms_1.FormsModule,
                common_1.CommonModule,
                router_1.RouterModule.forChild([
                    { path: 'admin', canActivate: [admin_guard_service_1.AdminRouteGuard], component: admin_component_1.AdminComponent }
                ])
            ],
            exports: [
                admin_add_content_component_1.AdminAddContent,
                admin_edit_content_component_1.AdminEditContent
            ],
            providers: [
                admin_guard_service_1.AdminRouteGuard,
                user_auth_service_1.UserAuthService
            ]
        }), 
        __metadata('design:paramtypes', [])
    ], AdminModule);
    return AdminModule;
}());
exports.AdminModule = AdminModule;
//# sourceMappingURL=admin.module.js.map