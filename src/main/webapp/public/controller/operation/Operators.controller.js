sap.ui.define([
	"../BaseController"
], function (BaseController) {
	"use strict";
	return BaseController.extend("nl.buildforce.gt.controller.operation.Operators", {
		onListItemPressed : function(oEvent){
			var oItem, oCtx;
			oItem = oEvent.getSource();
            oCtx = oItem.getBindingContext();
            this.getRouter().navTo("operator",{operatorId : encodeURIComponent(oCtx.getProperty("guid"))});
		}
	});
});