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
var login_observable_service_1 = require('../login/login-observable.service');
var logout_observable_service_1 = require('../login/logout-observable.service');
var user_auth_service_1 = require('../services/user-auth.service');
var HomeComponent = (function () {
    function HomeComponent(loginObservableService, logoutObservableService, userAuthService) {
        var _this = this;
        this.loginObservableService = loginObservableService;
        this.logoutObservableService = logoutObservableService;
        this.userAuthService = userAuthService;
        this.isShowSearchBar = false;
        console.log('constructor of HomeComponent..');
        //user login notification
        this.subscription = loginObservableService.userLoginAnnounced$.subscribe(function (user) {
            console.log('home component received notification from login component ==>: ' + JSON.stringify(user));
            _this.isShowSearchBar = user.isLogin;
        });
        //user logout notification
        this.subscription = logoutObservableService.userLogoutAnnounced$.subscribe(function (notifyLogout) {
            console.log('home component received notification form app component ==>: ' + JSON.stringify(notifyLogout));
            if (notifyLogout === 'logout') {
                _this.isShowSearchBar = false;
            }
            if (notifyLogout === 'alive') {
                _this.isShowSearchBar = true;
            }
        });
    }
    HomeComponent.prototype.ngOnInit = function () {
        var _this = this;
        console.log("oninit HomeComponent...");
        //call backend server to check if user is login or not when refresh page
        var ux;
        this.userAuthService.userStillAlive().subscribe(function (ux) {
            console.log('/userStillAlive ==>: ' + JSON.stringify(ux));
            if (ux) {
                var isAlive = ux.userLogin;
                var currentName = ux.username;
                console.log('isAlive: ' + isAlive + ' | currentName: ' + currentName);
                if (isAlive) {
                    _this.isShowSearchBar = true;
                    //notify home component the show the search bar
                    var alive = 'alive';
                    _this.logoutObservableService.announceUserIsLogout(alive);
                }
            }
        });
    };
    HomeComponent.prototype.ngOnDestroy = function () {
        // prevent memory leak when component destroyed
        this.subscription.unsubscribe();
    };
    HomeComponent = __decorate([
        core_1.Component({
            templateUrl: 'app/home/home.component.html',
            providers: [user_auth_service_1.UserAuthService]
        }), 
        __metadata('design:paramtypes', [login_observable_service_1.LoginObservableService, logout_observable_service_1.LogoutObservableService, user_auth_service_1.UserAuthService])
    ], HomeComponent);
    return HomeComponent;
}());
exports.HomeComponent = HomeComponent;
//# sourceMappingURL=home.component.js.map