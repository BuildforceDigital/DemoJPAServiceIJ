sap.ui.define([
    "sap/ui/core/mvc/Controller",
    "sap/m/MessageToast",
], (Controller, MessageToast) => {
    "use strict";
    const batchGroupId = "myUpdateGroupId";

    return Controller.extend("demo.controller.Detail", {
        onInit: function () {
            const route = this.getOwnerComponent().getRouter().getRoute("masterDetail");
            const onPatternMatched = (event) => {
                const bindSelectedItem = ({userName}) => {
                    const decodedUserName = decodeURIComponent(userName);
                    this.getView().bindElement({
                        path: `/People('${decodedUserName}')`,
                        parameters: {
                            $$updateGroupId: batchGroupId
                        },
                        events: {
                            dataRequested: () => this.byId("page").setBusy(true),
                            dataReceived: () => this.byId("page").setBusy(false)
                        }
                    });
                }

                bindSelectedItem(event.getParameter("arguments"));
            };

            route.attachPatternMatched(onPatternMatched, this);
            route.attachBeforeMatched(this.reset, this);
        },

        onSubmitPress: function () {
            const refreshMasterList = (groupId) => {
                const bus = this.getOwnerComponent().getEventBus();
                bus.publish("master", "refresh", {groupId});
            };

            const submitBatch = (id) => {
                return this.getOwnerComponent().getModel().submitBatch(id);
            };

            if (this.getOwnerComponent().getModel().hasPendingChanges()) {
                this.byId("page").setBusy(true);
                refreshMasterList(batchGroupId);

                submitBatch(batchGroupId).then(() => {
                    this.resetEditingStatus();
                    this.byId("page").setBusy(false);
                    window.requestAnimationFrame(() => MessageToast.show("User updated"));
                });
            }
        },

        resetEditingStatus: function () {
            const model = this.getOwnerComponent().getModel("detailViewModel");
            model.setProperty("/editing", false);
        },

        reset: function (event) {
            this.getOwnerComponent().getModel().resetChanges(batchGroupId);
            this.resetEditingStatus()
        }
    });
});