sap.ui.define([
	"sap/ui/core/UIComponent",
	"sap/ui/model/json/JSONModel",
	"sap/ui/demo/nav/model/Models"
], function (UIComponent, JSONModel, Models) {
	"use strict";

	const oViewModel = new JSONModel({
		now: new Date()
		/*busy : false,
        hasUIChanges : false,
        usernameEmpty : true,
        order : 0*/
	});

	return UIComponent.extend("sap.ui.demo.nav.Component", {

		metadata: {
			manifest: "json"
		},

		init: function () {
			sap.ui.core.LocaleData
				.getInstance(sap.ui.getCore().getConfiguration().getFormatSettings().getFormatLocale())
				.mData["weekData-firstDay"] = 1;

			// call the init function of the parent
			UIComponent.prototype.init.apply(this, arguments);

			// set the device model
			this.setModel(Models.createDeviceModel(), "device");
			this.setModel(oViewModel, "appModel");

			// create the views based on the url/hash
			this.getRouter().initialize();
		}

	});

});