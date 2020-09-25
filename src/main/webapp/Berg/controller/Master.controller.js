sap.ui.define([
  "sap/ui/core/mvc/Controller",
  "sap/ui/Device"
], function(Controller, Device) {
  "use strict";

  return Controller.extend("demo.controller.Master", {
    onInit: function() {
      const bus = this.getOwnerComponent().getEventBus();
      bus.subscribe("master", "refresh", this.shouldRefresh, this);
    },

    onItemPress: function(event) {
      const item = event.getParameter("listItem");
      this.navToDetailOf(item);
    },

    navToDetailOf: function(item) {
      const username = window.encodeURIComponent(item.getBindingContext().getProperty("UserName"));
      this.getOwnerComponent().getRouter().navTo("masterDetail", {userName: username},
          !Device.system.phone);
    },

    shouldRefresh: function(channelId, eventId, groupId) {
      const listBinding = this.byId("masterList").getBinding("items");
      this.refresh(listBinding, groupId);
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