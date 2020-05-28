sap.ui.define([
    "../model/formatter",
    "sap/m/MessageBox",
    "sap/m/MessageToast",
    "sap/ui/core/mvc/Controller",
    "sap/ui/model/Filter",
    "sap/ui/model/FilterOperator"
], function (formatter, MessageBox, MessageToast, Controller, Filter, FilterOperator) {
    "use strict";
    const batchGroupId = "UserGroup";

    return Controller.extend("sap.ui.demo.basicTemplate.controller.Home", {

        formatter: formatter,

        /* =========================================================== */
        /* event handlers                                              */
        /* =========================================================== */

        inputChange: function (oEvent) {
            this.getView().byId("btnUpdate").setEnabled(true);
        },
        onClose: function (oEvent) {
            const oView = this.getView();

            if (oView.getBindingContext().hasPendingChanges()) {
                MessageBox.confirm("Do you want to update the pending changes?",
                    jQuery.proxy(function (bResult) {
                        if (bResult === "OK") {
                            this.updateEmp(oEvent);
                        } else {
                            oView.getModel().resetChanges(batchGroupId);
                        }
                    }, this),
                    "Warning");
            }
            var editDialog = (oEvent.getSource()).getEventingParent();
            editDialog.close();

        },

        /* =========================================================== */
        /* lifecycle methods                                           */
        /* =========================================================== */

        /**
         * Called when the worklist controller is instantiated.
         * @public
         */
        onInit: function () {
            function _onMessageBindingChange (oEvent) {
                const aContexts = oEvent.getSource().getContexts();
                let bMessageOpen = false;
                if (!bMessageOpen && aContexts.length) {// Extract and remove the technical messages
                    const aMessages = aContexts.map(function (oContext) {
                        return oContext.getObject();
                    });
                    sap.ui.getCore().getMessageManager().removeMessages(aMessages);
                    // this._setUIChanges(true);
                    // this._bTechnicalErrors = true;
                    MessageBox.error(aMessages[0].message, {
                        onClose: function (sAction) {
                            bMessageOpen = false;
                        }
                    });
                    // bMessageOpen = true;
                }
            }

            const oMessageManager = sap.ui.getCore().getMessageManager(),
                oMessageModel = oMessageManager.getMessageModel(),
                oMessageModelBinding =
                    oMessageModel.bindList("/", undefined, [], new sap.ui.model.Filter("technical", FilterOperator.EQ, true));
            oMessageModelBinding.attachChange(_onMessageBindingChange, this);
        },
        onEdit: function (oEvent) {
            const oView = this.getView();
            let dialog = oView.byId("editDialog");
            if (dialog) {
                // oView.setBindingContext(null);
            } else {
                dialog = sap.ui.xmlfragment(oView.getId(), "sap.ui.demo.basicTemplate.view.Edit", this);
                this.getView().addDependent(dialog)
            }
            dialog.open();
            oView.byId("btnUpdate").setEnabled(false);

            // const item = oEvent.getParameter("listItem").getParent().getId();

            oView.bindElement({
                path: oEvent.getSource().getSelectedItem().getBindingContext().getPath(),
                parameters: {
                    $$updateGroupId: batchGroupId
                },
                events: {
                    dataRequested: () => dialog.setBusy(true),
                    dataReceived: () => dialog.setBusy(false)
                }
            });
        },

        updateEmp: function (oEvent) {
            const that = this,
                dialog = this.getView().byId("editDialog"),
                empTable = // Does not work from Update button in the "Do you want to update the pending changes?"
                    sap.ui.getCore().byId(oEvent.getSource().getParent().getParent().getId()).byId("masterList"),
                oBinding = empTable.getBinding("items");

            dialog.setBusy(true);
            oBinding.refresh(batchGroupId); // Update list
            dialog.getModel().submitBatch(batchGroupId).then(function () {
                if (!dialog.getBindingContext().hasPendingChanges()) {
                    var userName = that.getView().getBindingContext().getProperty("UserName");
                    dialog.close();
                    MessageToast.show("User " + userName + " is updated successfully")
                }
            });
        }

    });
});