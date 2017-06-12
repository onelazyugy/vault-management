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
var router_1 = require("@angular/router");
var Observable_1 = require("rxjs/Observable");
require("rxjs/add/observable/of");
var user_auth_service_1 = require("../services/user-auth.service");
var AdminRouteGuard = (function () {
    function AdminRouteGuard(_router, userAuthService) {
        this._router = _router;
        this.userAuthService = userAuthService;
        this.isEnableAdminRoute = false;
        console.log('AdminRouteGuard contructor...');
    }
    AdminRouteGuard.prototype.canActivate = function (route) {
        var _this = this;
        console.log('canActivate method from AdminRouteGuard: ' + route.url[0].path);
        return this.userAuthService.isUserLogin().map(function (res) {
            console.log('isUserLogin api returns: ' + res);
            if (res) {
                return true;
            }
            _this._router.navigate(['/home']);
            return false;
        }).catch(function (err) {
            console.log('catch block.....: ' + err);
            _this._router.navigate(['/home']);
            return Observable_1.Observable.of(false);
        });
    };
    return AdminRouteGuard;
}());
AdminRouteGuard = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [router_1.Router, user_auth_service_1.UserAuthService])
], AdminRouteGuard);
exports.AdminRouteGuard = AdminRouteGuard;
//# sourceMappingURL=admin-guard.service.js.map