sap.ui.define([
  "sap/ui/core/mvc/Controller",
  "sap/m/MessageToast",
], function(Controller, MessageToast) {
  "use strict";
  const batchGroupId = "myUpdateGroupId";

  return Controller.extend("demo.controller.Detail", {
    onInit: function() {
      const route = this.getOwnerComponent().getRouter().getRoute("masterDetail");
      route.attachPatternMatched(this.onPatternMatched, this);
      route.attachBeforeMatched(this.reset, this);
    },

    onPatternMatched: function(event) {
      this.bindSelectedItem(event.getParameter("arguments"));
    },

    bindSelectedItem: function({ userName }) {
      const decodedUserName = window.decodeURIComponent(userName);
      this.getView().bindElement({
        path: `/People('${decodedUserName}')`,
        parameters: {
          $$updateGroupId: batchGroupId
        },
        events: {
          dataRequested: () => this.byId("page").setBusy(true),
          dataReceived: () => this.byId("page").setBusy(false),
        },
      });
    },

    onSubmitPress: function() {
      if (this.getOwnerComponent().getModel().hasPendingChanges()) {
        this.byId("page").setBusy(true);
        this.refreshMasterList(batchGroupId);
        this.submitBatch(batchGroupId).then(() => {
          this.resetEditingStatus();
          this.byId("page").setBusy(false);
          window.requestAnimationFrame(() => MessageToast.show("User updated"));
        });
      }
    },

    refreshMasterList: function(groupId) {
      const bus = this.getOwnerComponent().getEventBus();
      bus.publish("master", "refresh", groupId);
    },

    submitBatch: function(id) {
      return this.getOwnerComponent().getModel().submitBatch(id);
    },

    reset: function(event) {
      this.getOwnerComponent().getModel().resetChanges(batchGroupId);
      this.resetEditingStatus();
    },

    resetEditingStatus: function() {
      const model = this.getOwnerComponent().getModel("detailViewModel");
      model.setProperty("/editing", false);
    }

  });
});