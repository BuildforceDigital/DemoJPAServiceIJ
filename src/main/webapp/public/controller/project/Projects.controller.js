sap.ui.define([
	"../BaseController"
], function (BaseController) {
	"use strict";
	return BaseController.extend("nl.buildforce.gt.controller.project.Projects", {
		onListItemPressed : function(oEvent){
			var oItem, oCtx;
			oItem = oEvent.getSource();
            oCtx = oItem.getBindingContext();
            // TODO: Combined primary key
            this.getRouter().navTo("project", {/*guid : encodeURIComponent(oCtx.getProperty("guid")),*/
            projectCode : encodeURIComponent(oCtx.getProperty("projectCode"))});
		}
	});
});