"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var router_1 = require("@angular/router");
var forms_1 = require("@angular/forms"); //for ngModel
var common_1 = require("@angular/common"); //for ngIf and pipe and more...
var search_component_1 = require("../search/search.component");
var home_component_1 = require("./home.component");
var login_component_1 = require("../login/login.component");
//modal
var enter_password_dialog_component_1 = require("../search/enter.password.dialog.component");
var ng2_bootstrap_modal_1 = require("ng2-bootstrap-modal");
var HomeModule = (function () {
    function HomeModule() {
    }
    return HomeModule;
}());
HomeModule = __decorate([
    core_1.NgModule({
        declarations: [
            search_component_1.SearchComponent,
            login_component_1.LoginComponent,
            enter_password_dialog_component_1.PromptComponent
        ],
        imports: [
            forms_1.FormsModule,
            forms_1.ReactiveFormsModule,
            //for more
            ng2_bootstrap_modal_1.BootstrapModalModule,
            common_1.CommonModule,
            router_1.RouterModule.forChild([
                { path: 'home', component: home_component_1.HomeComponent }
            ])
        ],
        //for modal
        entryComponents: [
            enter_password_dialog_component_1.PromptComponent
        ],
        exports: [
            search_component_1.SearchComponent,
            login_component_1.LoginComponent
        ],
        providers: []
    })
], HomeModule);
exports.HomeModule = HomeModule;
//# sourceMappingURL=home.module.js.map