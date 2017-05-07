"use strict";
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
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
var ng2_bootstrap_modal_1 = require("ng2-bootstrap-modal");
var PromptComponent = (function (_super) {
    __extends(PromptComponent, _super);
    function PromptComponent(dialogService) {
        var _this = _super.call(this, dialogService) || this;
        _this.message = '';
        return _this;
    }
    PromptComponent.prototype.apply = function () {
        this.result = this.message;
        this.close();
    };
    return PromptComponent;
}(ng2_bootstrap_modal_1.DialogComponent));
PromptComponent = __decorate([
    core_1.Component({
        selector: 'prompt',
        template: "<div class=\"modal-dialog\">\n                <div class=\"modal-content\">\n                   <div class=\"modal-header\">\n                     <button type=\"button\" class=\"close\" (click)=\"close()\">&times;</button>\n                     <h4 class=\"modal-title\">{{title || 'Prompt'}}</h4>\n                   </div>\n                   <div class=\"modal-body\">\n                    <label>{{question}}</label><input type=\"text\" class=\"form-control\" [(ngModel)]=\"message\" name=\"name\" >\n                   </div>\n                   <div class=\"modal-footer\">\n                     <button type=\"button\" class=\"btn btn-primary\" (click)=\"apply()\">OK</button>\n                     <button type=\"button\" class=\"btn btn-default\" (click)=\"close()\" >Cancel</button>\n                   </div>\n                 </div>\n                </div>"
    }),
    __metadata("design:paramtypes", [ng2_bootstrap_modal_1.DialogService])
], PromptComponent);
exports.PromptComponent = PromptComponent;
//# sourceMappingURL=enter.password.dialog.component.js.map