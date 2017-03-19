"use strict";
var CommonService = (function () {
    function CommonService() {
    }
    CommonService.prototype.buildRequestURL = function () {
        var hostname = location.hostname;
        var port = location.port;
        var protocol = location.protocol;
        if (port != "") {
            return location.protocol + "//" + location.hostname + ":" + location.port;
        }
        else {
            return location.protocol + "//" + location.hostname;
        }
    };
    return CommonService;
}());
exports.CommonService = CommonService;
//# sourceMappingURL=common-service.js.map