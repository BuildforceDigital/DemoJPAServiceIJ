sap.ui.define([
    "sap/ui/demo/walkthrough/controller/BaseController",
    'sap/ui/model/json/JSONModel',
    'sap/ui/model/SimpleType',
    "sap/ui/model/ValidateException",
    "sap/m/MessageBox",
    "sap/m/MessageToast"
], function (BaseController, JSONModel, SimpleType, ValidateException, MessageBox, MessageToast) {
    return BaseController.extend("sap.ui.demo.walkthrough.controller.LaunchTiles", {
        onPress(urlFragm) {
            sap.m.URLHelper.redirect(`../${urlFragm}/index.html`, false)
        },

        onInit() {
            // Attaches Popover on mouse hoover
            function attachPopoverOnMouseover(targetControl, popover) {
                let _timeId;

                function _showPopover(targetControl, popover) {
                    _timeId = setTimeout(() => popover.openBy(targetControl), 500);
                }

                function _clearPopover(popover) {
                    clearTimeout(_timeId) || popover.close();
                }

                targetControl.addEventDelegate({
                    onmouseover: _showPopover.bind(this, targetControl, popover),
                    onmouseout: _clearPopover.bind(this, popover)
                }, this);
            }

            // attachPopoverOnMouseover(this.byId("target"), this.byId("popover"));
        },
        /*
         *
         */
        onClickPic() {
            this.getRouter().navTo("RouteMain");
        }

    })
});