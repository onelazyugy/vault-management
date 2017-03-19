"use strict";
var AddEntryModel = (function () {
    function AddEntryModel(category, tag, password, confirmPassword, username, comment) {
        this.category = category;
        this.tag = tag;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.username = username;
        this.comment = comment;
    }
    return AddEntryModel;
}());
exports.AddEntryModel = AddEntryModel;
//# sourceMappingURL=addentrymodel.js.map