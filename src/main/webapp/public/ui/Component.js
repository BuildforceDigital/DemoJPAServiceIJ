jQuery.sap.declare("ui.Component");

sap.ui.core.UIComponent.extend("ui.Component", {
	
	metadata : {
		rootView : "misc.Signage",
		name : "Image Grid",
    version : "1.0",
		includes : [
      "../css/style.css"
		],
    dependencies : {
        libs : ["sap.m"]
    }
	},
	
	init : function() {
		sap.ui.core.UIComponent.prototype.init.apply(this, arguments);
		
		var oJSONModel = new sap.ui.model.json.JSONModel();
    oJSONModel.loadData("model/data.json");
    this.setModel(oJSONModel);
	}

});