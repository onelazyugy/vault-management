"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var platform_browser_1 = require("@angular/platform-browser");
var http_1 = require("@angular/http");
var router_1 = require("@angular/router");
//for modal
var ng2_bootstrap_modal_1 = require("ng2-bootstrap-modal");
var app_component_1 = require("./app.component");
var shared_logo_module_1 = require("./shared/shared-logo.module");
var shared_footer_module_1 = require("./shared/shared-footer.module");
var home_module_1 = require("./home/home.module");
var admin_module_1 = require("./admin/admin.module");
var home_component_1 = require("./home/home.component");
var admin_component_1 = require("./admin/admin.component");
var common_service_1 = require("./services/common-service");
var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    core_1.NgModule({
        imports: [
            platform_browser_1.BrowserModule,
            //for modal
            ng2_bootstrap_modal_1.BootstrapModalModule,
            http_1.HttpModule,
            shared_logo_module_1.LogoModule,
            shared_footer_module_1.FooterModule,
            home_module_1.HomeModule,
            admin_module_1.AdminModule,
            router_1.RouterModule.forRoot([
                { path: 'home', component: home_component_1.HomeComponent },
                { path: '', redirectTo: 'home', pathMatch: 'full' },
                { path: '**', redirectTo: 'home', pathMatch: 'full' } //any other route that are not found, redirect to welcome page or you can do a custom 404 page
            ], { useHash: true })
        ],
        declarations: [
            app_component_1.AppComponent,
            home_component_1.HomeComponent,
            admin_component_1.AdminComponent,
        ],
        providers: [
            common_service_1.CommonService //provide a common service for all modules to use
        ],
        bootstrap: [
            app_component_1.AppComponent
        ]
    })
], AppModule);
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map