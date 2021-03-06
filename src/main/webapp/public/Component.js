sap.ui.define([
	"sap/ui/core/UIComponent",
	"sap/ui/Device",
	"./model/models",
	"./util/FioriClient"
], function(UIComponent, Device, models, FioriClient) {
	"use strict";

	return UIComponent.extend("nl.buildforce.gt.Component", {

		metadata: {
			manifest: "json",
            includes : [
                "./css/style.css"
            ]
		},

		/**
		 * The component is initialinl.buildforcezed by UI5 automatically during the startup of the app and calls the init method once.
		 * @public
		 * @override
		 */
		init: function() {
			// initialize FioriClient
			this._oFioriClient = new FioriClient();

			// call the base component's init function
			UIComponent.prototype.init.apply(this, arguments);

			// set the device model
			this.setModel(models.createDeviceModel(), "device");

			// create the views based on the url/hash
			this.getRouter().initialize();
		},

		getFioriClient: function() {
			return this._oFioriClient;
		}
	});
});