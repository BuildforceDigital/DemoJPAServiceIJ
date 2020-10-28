sap.ui.define([
  "sap/ui/core/mvc/Controller",
  "sap/m/MessageToast",
  "sap/ui/core/EventBus",
], function(Controller, MessageToast) {
  "use strict";
  const batchGroupId = "myUpdateGroupId";

  return Controller.extend("demo.controller.Detail", {
    onInit: function() {
      const router = this.getOwnerComponent().getRouter();
      const route = router.getRoute("masterDetail");
      route.attachPatternMatched(this.onPatternMatched, this);
      route.attachBeforeMatched(this.reset, this);
    },

    onPatternMatched: function(event) {
      this.bindSelectedItem(event.getParameter("arguments"));
    },

    bindSelectedItem: function({ userName }) {
      const decodedUserName = window.decodeURIComponent(userName);
      this.getView().bindElement({
        path: `/BPersonss('${decodedUserName}')`,
        parameters: {
          $$updateGroupId: batchGroupId
        },
        events: {
          dataRequested: () => this.byId("page").setBusy(true),
          dataReceived: () => this.byId("page").setBusy(false),
        },
      });
    },

    onSubmitPress: async function() {
      if (this.getOwnerComponent().getModel().hasPendingChanges()) {
        this.byId("page").setBusy(true);
        this.refreshMasterList(batchGroupId);
        await this.submitBatch(batchGroupId);
        this.resetEditingStatus();
        this.byId("page").setBusy(false);
        window.requestAnimationFrame(() => MessageToast.show("User updated"));
      }
    },

    refreshMasterList: function(groupId) {
      const bus = this.getOwnerComponent().getEventBus();
      bus.publish("master", "refresh", { groupId });
    },

    submitBatch: async function(id) {
      return await this.getOwnerComponent().getModel().submitBatch(id);
    },

    reset: function(event) {
      this.getOwnerComponent().getModel().resetChanges(batchGroupId);
      this.resetEditingStatus();
    },

    resetEditingStatus: function() {
      const model = this.getOwnerComponent().getModel("detailViewModel");
      model.setProperty("/editing", false, null, true);
    },

  });
});