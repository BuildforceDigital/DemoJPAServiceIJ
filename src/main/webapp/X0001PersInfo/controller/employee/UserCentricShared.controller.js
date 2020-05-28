sap.ui.define(["sap/ui/demo/nav/controller/employee/UserCentric.controller"], function (UserCentric) {
    "use strict";

    return UserCentric.extend("sap.ui.demo.nav.controller.employee.UserCentricShared", {
        onInit() {
        },

        inputChange: function(oEvent) {
            const oView = this.getView();

            oView.getModel("usrCtrView").setProperty("/hasUIChanges", oView.getModel().hasPendingChanges())
        }

    })
});