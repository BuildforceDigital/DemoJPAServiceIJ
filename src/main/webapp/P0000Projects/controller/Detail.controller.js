sap.ui.define([
    "sap/ui/core/mvc/Controller", "sap/m/MessageBox"
], function (Controller, MessageBox) {
    const batchGroupId = "UserGroup";

    "use strict";
    return Controller.extend("sap.ui.demo.basicTemplate.controller.Detail", {

        /* =========================================================== */
        /* lifecycle methods                                           */
        /* =========================================================== */

        onInit: function () {
            const oRouter = sap.ui.core.UIComponent.getRouterFor(this);
            oRouter.getRoute("Detail").attachMatched(this._onRouteMatched, this);
        },

        /* =========================================================== */
        /* event handlers                                              */
        /* =========================================================== */

        /*
        onAfterRendering: function () {
                    const tabBar = this.getView().byId("empIconTabBar");
                    tabBar.setSelectedKey("personal");
                },
        */
        onBack: function () {
            const oRouter = sap.ui.core.UIComponent.getRouterFor(this);
            oRouter.navTo("RouteMain", true);
        },
        onDelete: function (oEvent) {
            const userName = this.getView().getBindingContext().getProperty("ProjectCode");
            MessageBox.confirm("Do you sure want to delete Project profile: " + userName,
                jQuery.proxy(function (bResult) {
                    if (bResult === "OK") {
                        const EMPID = this.getView().getBindingContext().getProperty("ID");
                        this._deleteUsr(EMPID, userName);
                    }
                }, this),
                "Delete Project");
        },

        _deleteUsr: function (EMPID, userName) {
            const that = this,
                _oBinding = sap.ui.getCore().byId(this.getOwnerComponent().getId() + "---home--emptable").getBinding("items");

            this.getView().getBindingContext().delete("$auto").then(function () {
                const successMessage = "User " + userName + " is deleted";
                MessageBox.alert(successMessage, {
                    title: "Alert", // default
                    icon: "sap-icon://success",
                    onClose: function () {
                        _oBinding.refresh();
                        that.onBack()
                    }
                });
            });

        },
        formatNumber: function (value) {
            return value.replace(/,/g, "");
        },
        inputChange: function (oEvent) {
            this.getView().byId("btnUpdate").setEnabled(true);
        },
        onEdit: function (oEvent) {
            const oView = this.getView();
            if (!oView.byId("editDialog")) {
                oView.addDependent(sap.ui.xmlfragment(oView.getId(), "sap.ui.demo.basicTemplate.view.Edit", this));
            }
            oView.byId("editDialog").open();
            oView.byId("btnUpdate").setEnabled(false);
            // const ID = oView.getBindingContext().getProperty("ID");
            oView.bindElement({
                path: oEvent.getSource().getBindingContext().getPath(),
                parameters: {
                    $$updateGroupId: batchGroupId
                }
            });
        },
        updateEmp: function (oEvent) {
            const that = this,
                dialog = this.getView().byId("editDialog"),
                _oBinding = sap.ui.getCore().byId(this.getOwnerComponent().getId() + "---home--emptable").getBinding("items");

            dialog.getModel().submitBatch(batchGroupId).then(function () {
                _oBinding.refresh(/*batchGroupId*/);

                if (!dialog.getBindingContext().hasPendingChanges()) {
                    const userName = that.getView().getBindingContext().getProperty("ProjectCode");
                    dialog.close();
                    MessageBox.success("Project " + userName + " is updated successfully");
                }
            });
        },
        onClose: function (oEvent) {

            if (this.getView().getBindingContext().hasPendingChanges()) {
                MessageBox.confirm("Do you want to update the pending changes?",
                    jQuery.proxy(function (bResult) {
                        if (bResult === "OK") {
                            this.updateEmp();
                        } else {
                            this.getView().getModel().resetChanges(batchGroupId);
                        }
                    }, this),
                    "Warning");
            }
            const editDialog = (oEvent.getSource()).getEventingParent();
            editDialog.close();

        },

        /* =========================================================== */
        /* begin: internal methods                                     */
        /* =========================================================== */

        _onRouteMatched: function (oEvent) {
            this.getView().byId("empIconTabBar").setSelectedKey("personal");
            this.getView().bindElement({
                path: "/" + oEvent.getParameter("arguments").employeePath,
                parameters: {
                    $$updateGroupId: batchGroupId
                }
            });
        },
        _trimTextInput: function (oDlgEvent) {
            const field = oDlgEvent.getSource();

            this._oParentView.byId(field.getId()).setValue(field.getValue().trim())
        }
    })
});