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
var usermodel_1 = require('./usermodel');
var login_observable_service_1 = require('./login-observable.service');
var user_auth_service_1 = require('../services/user-auth.service');
var LoginComponent = (function () {
    function LoginComponent(loginObservableService, userAuthService) {
        this.loginObservableService = loginObservableService;
        this.userAuthService = userAuthService;
        this.panelTitle = 'Login';
        this.user = { 'username': '', 'password': '', isLogin: false };
        this.model = new usermodel_1.UserModel('', '');
        this.messageLabel = '';
    }
    LoginComponent.prototype.onUserNameChange = function (value, loginForm) {
        this.model.username = value;
        if (loginForm.valid) {
            this.messageLabel = '';
        }
    };
    LoginComponent.prototype.onPasswordChange = function (value, loginForm) {
        this.model.password = value;
        if (loginForm.valid) {
            this.messageLabel = '';
        }
    };
    LoginComponent.prototype.login = function (loginForm) {
        var _this = this;
        console.log('login() model==>' + JSON.stringify(loginForm.value));
        var username = loginForm.value.username;
        var password = loginForm.value.password;
        if (username && password) {
            if (loginForm.value.password != '' && loginForm.value.username != '') {
                //call backend to verify the credentials
                this.user.username = username;
                this.user.password = password;
                this.userAuthService.login(this.user).subscribe(function (data) {
                    console.log('/login result ==>: ' + data);
                    if (data) {
                        //notify parents but empty the password first
                        _this.user.isLogin = true;
                        _this.user.password = null;
                        _this.loginObservableService.announceUserIsLogin(_this.user);
                    }
                    else {
                        _this.user.isLogin = false;
                        _this.messageLabel = 'incorrect credentials';
                    }
                }, function (error) {
                    console.log('Error login: ' + error);
                    _this.user.isLogin = false;
                    _this.messageLabel = 'failed login';
                    return false;
                }, function () {
                    console.log('Completed login request');
                });
            }
            else {
                this.messageLabel = 'field cannot be empty';
            }
        }
        else {
            this.messageLabel = 'fail';
        }
    };
    LoginComponent = __decorate([
        core_1.Component({
            selector: 'login',
            templateUrl: 'app/login/login.component.html',
            providers: [user_auth_service_1.UserAuthService]
        }), 
        __metadata('design:paramtypes', [login_observable_service_1.LoginObservableService, user_auth_service_1.UserAuthService])
    ], LoginComponent);
    return LoginComponent;
}());
exports.LoginComponent = LoginComponent;
//# sourceMappingURL=login.component.js.map