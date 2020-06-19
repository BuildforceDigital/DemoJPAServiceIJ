sap.ui.define([
	"sap/ui/core/util/MockServer"
], function (MockServer) {
	"use strict";
	return {
		init: function () {
			// create
			var oMockServer = new MockServer({
				rootUri: "/here/goes/your/serviceUrl/"
			});
			var oUriParameters = jQuery.sap.getUriParameters();
			// configure
			MockServer.config({
				// autoRespond: true,
				// autoRespondAfter: oUriParameters.get("serverDelay") || 1000
			});
			// simulate
			// var sPath = jQuery.sap.getModulePath("nl.buildforce.gt.localService");
			oMockServer.simulate("/api/$metadata.xml", "/api");
			// start
			oMockServer.start();
		}
	};
});