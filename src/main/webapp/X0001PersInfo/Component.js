sap.ui.define([
	"sap/ui/core/UIComponent"
], function (UIComponent) {
	"use strict";

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

			// create the views based on the url/hash
			this.getRouter().initialize();
		}

	});

});