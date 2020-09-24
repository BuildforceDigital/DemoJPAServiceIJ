sap.ui.define([
  "sap/ui/core/UIComponent",
], function(UIComponent) {
  "use strict";

  return UIComponent.extend("demo.Component", {
    metadata: {
      manifest: "json"
    },

    init: function() {
      UIComponent.prototype.init.apply(this, arguments);
      this.getModel("detailViewModel").setProperty("/editing", false);
      this.getRouter().initialize();
      window.onbeforeunload = () => this.getModel().hasPendingChanges() || null;
    },
    
  });
});