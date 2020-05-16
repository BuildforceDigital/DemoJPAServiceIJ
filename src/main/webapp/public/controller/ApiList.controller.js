sap.ui.define([
		'sap/ui/core/mvc/Controller'
	], function(Controller) {
	"use strict";

/*
	var DbSummaryController = Controller.extend("nl.buildforce.gt.controller.DbSummary", {

		onInit: function () {
			// set explored app's demo model on this sample
			var oModel = new JSONModel(jQuery.sap.getModulePath("sap.ui.demo.mock", "/products.json"));
			this.getView().setModel(oModel);
		}
	});


	return DbSummaryController;
*/

	return Controller.extend("nl.buildforce.gt.controller.ApiList", {
/*		onInit: function () {
			// set explored app's demo model on this sample
			var oModel = new JSONModel("model/DbSummary.json");
			this.getView().setModel(oModel);
		}*/

	});

});