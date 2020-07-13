sap.ui.define([
	"sap/ui/core/mvc/Controller",
	"sap/ui/model/json/JSONModel",
	"../model/formatter",
	"sap/ui/model/Filter",
	"sap/ui/model/FilterOperator"
], function (Controller, JSONModel, formatter, Filter, FilterOperator) {
	"use strict";

	return Controller.extend("sap.ui.demo.walkthrough.controller.InvoiceList", {

		formatter: formatter,

		onInit: function () {
            const oViewModel = new JSONModel({
                currency: "EUR"
            });
            this.getView().setModel(oViewModel, "view");
		},

		onFilterInvoices : function (oEvent) {

			// build filter array
            const aFilter = [];
            const sQuery = oEvent.getParameter("query");
            if (sQuery) {
				aFilter.push(new Filter("ProductName", FilterOperator.Contains, sQuery));
			}

			// filter binding
            const oList = this.byId("invoiceList");
            const oBinding = oList.getBinding("items");
            oBinding.filter(aFilter);
		}
	});

});