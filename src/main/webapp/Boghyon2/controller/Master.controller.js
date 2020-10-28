sap.ui.define([
  "sap/ui/core/mvc/Controller",
  "sap/ui/core/EventBus",
], function(Controller) {
  "use strict";

  return Controller.extend("demo.controller.Master", {
    onInit: function() {
      const bus = this.getOwnerComponent().getEventBus();
      bus.subscribe("master", "refresh", this.shouldRefresh, this);
    },

    navToDetailOf: function(item) {
      const selectedUserName = item.getBindingContext().getProperty("UserName");
      const userName = window.encodeURIComponent(selectedUserName);
      this.getOwnerComponent().getRouter().navTo("masterDetail", { userName });
    },

    shouldRefresh: function(channelId, eventId, parametersMap) {
      const listBinding = this.byId("masterList").getBinding("items");
      this.refresh(listBinding, parametersMap["groupId"]);
    },
    
    refresh: function(binding, groupId) {
      if (binding.hasPendingChanges()) {
        // show message that there are some changes left
      } else {
        return binding.refresh(groupId);
      }
    },

  });
});