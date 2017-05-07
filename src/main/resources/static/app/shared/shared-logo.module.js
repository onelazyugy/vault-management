"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var shared_logo_component_1 = require("./shared-logo.component");
var LogoModule = (function () {
    function LogoModule() {
    }
    return LogoModule;
}());
LogoModule = __decorate([
    core_1.NgModule({
        declarations: [
            shared_logo_component_1.LogoComponent
        ],
        imports: [],
        exports: [
            shared_logo_component_1.LogoComponent
        ]
    })
], LogoModule);
exports.LogoModule = LogoModule;
//# sourceMappingURL=shared-logo.module.js.map