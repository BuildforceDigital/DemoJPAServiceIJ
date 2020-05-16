sap.ui.define([
	"../BaseController",
    'sap/ui/model/Filter',
    'sap/ui/model/json/JSONModel'
], function (BaseController, Filter, JSONModel) {
	"use strict";
	var _aValidTabKeys = ["cntCtp", "cntDct", "cntDocC", "cntDocI", "cntDocO", "cntEmpCom", "cntEmpCon", "cntPrj", "cntPrtC", "cntPrtP", "cntTmr"]
	return BaseController.extend("nl.buildforce.gt.controller.operation.Operator", {
		onInit: function (){
			var oRouter = this.getRouter();

			oRouter.getRoute("operator").attachMatched(this._onRouteMatched, this);


/*			// reuse table sample component
            var oComp = sap.ui.getCore().createComponent({
                name : 'sap.m.sample.Table'
            });
            oComp.setModel(this.getView().getModel());
            this._oTable = oComp.getTable();
            this.byId("idIconTabBar").insertContent(this._oTable);

            // update table
            this._oTable.setHeaderText(null);
            this._oTable.setShowSeparators("Inner");*/
		},

		_onRouteMatched : function (oEvent) {
			var oArgs, oView;

			oArgs = oEvent.getParameter("arguments");
			oView = this.getView();

			oView.bindElement({
				path : "/operators('" + oArgs.operatorId + "')",
				events : {
					change: this._onBindingChange.bind(this),
					dataRequested: function (oEvent) {
						oView.setBusy(true);
					},
					dataReceived: function (oEvent) {
						oView.setBusy(false);
					}
				}
			});
		},

		_onBindingChange : function (oEvent) {
			// No data for the binding
			if (!this.getView().getBindingContext()) {
				this.getRouter().getTargets().display("notFoundPage");
			}
		},

		handleIconTabBarSelect: function (oEvent) {
        			/*var oBinding = this._oTable.getBinding("items"),
        				sKey = oEvent.getParameter("key"),
        				// Array to combine filters
        				aFilters = [],
        				oCombinedFilterG,
        				oCombinedFilterKG,
        				// Boarder values for Filter
        				fMaxOkWeightKG = 1,
        				fMaxOkWeightG = fMaxOkWeightKG * 1000,
        				fMaxHeavyWeightKG = 5,
        				fMaxHeavyWeightG = fMaxHeavyWeightKG * 1000;
*/
        			/*if (sKey === "Ok") {
        				oCombinedFilterG = new Filter([new Filter("WeightMeasure", "LT", fMaxOkWeightG), new Filter("WeightUnit", "EQ", "G")], true);
        				oCombinedFilterKG = new Filter([new Filter("WeightMeasure", "LT", fMaxOkWeightKG), new Filter("WeightUnit", "EQ", "KG")], true);
        				aFilters.push(new Filter([oCombinedFilterKG, oCombinedFilterG], false));
        			} else if (sKey === "Heavy") {
        				oCombinedFilterG = new Filter([new Filter("WeightMeasure", "BT", fMaxOkWeightG, fMaxHeavyWeightG), new Filter("WeightUnit", "EQ", "G")], true);
        				oCombinedFilterKG = new Filter([new Filter("WeightMeasure", "BT", fMaxOkWeightKG, fMaxHeavyWeightKG), new Filter("WeightUnit", "EQ", "KG")], true);
        				aFilters.push(new Filter([oCombinedFilterKG, oCombinedFilterG], false));
        			} else if (sKey === "Overweight") {
        				oCombinedFilterKG = new Filter([new Filter("WeightMeasure", "GT", fMaxHeavyWeightKG), new Filter("WeightUnit", "EQ", "KG")], true);
        				oCombinedFilterG = new Filter([new Filter("WeightMeasure", "GT", fMaxHeavyWeightG), new Filter("WeightUnit", "EQ", "G")], true);
        				aFilters.push(new Filter([oCombinedFilterKG, oCombinedFilterG], false));
        			}
        			oBinding.filter(aFilters);*/
        		}


	});
});