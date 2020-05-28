sap.ui.define([
    "./model/models",
    "sap/ui/core/UIComponent",
    "sap/ui/model/json/JSONModel"
], function (models, UIComponent, JSONModel) {
    "use strict";

    return UIComponent.extend("sap.ui.demo.walkthrough.Component", {

        metadata: {
            manifest: "json"
        },

        /**
         * The component is initialized by UI5 automatically during the startup of the app and calls the init method once.
         * @public
         * @override
         */
        init: function () {
            sap.ui.core.LocaleData
                .getInstance(sap.ui.getCore().getConfiguration().getFormatSettings().getFormatLocale())
                .mData["weekData-firstDay"] = 1;
            // call the base component's init function
            UIComponent.prototype.init.apply(this, arguments);

            // set the device model
            this.setModel(models.createDeviceModel(), "device");

            // create the views based on the url/hash
            this.getRouter().initialize();
        }
    });
});