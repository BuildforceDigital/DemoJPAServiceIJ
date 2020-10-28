sap.ui.define([
    "sap/m/GroupHeaderListItem",
    "sap/m/MessageBox",
    "sap/ui/core/mvc/Controller"
], function (GroupHeaderListItem, MessageBox, Controller) {
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
            oRouter.navTo("RouteMain", true)
        },
        onDelete: function (oEvent) {
            const userName = this.getView().getBindingContext().getProperty("UserName");
            MessageBox.confirm("Do you sure want to delete User profile: " + userName,
                jQuery.proxy(function (bResult) {
                    if (bResult === "OK") {
                        const EMPID = this.getView().getBindingContext().getProperty("Id");
                        this._deleteUsr(EMPID, userName);
                    }
                }, this),
                "Delete User");
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
                path: oEvent.getSource().getBindingContext() /*.getPath()*/,
                parameters: {
                    $$updateGroupId: batchGroupId
                }/*, events: {dataRequested: () => {},
                    dataReceived: () => {}
                }*/
            });
        },
        updateEmp: function (oEvent) {
            const that = this,
                dialog = this.getView().byId("editDialog"),
                _oBinding = sap.ui.getCore().byId(this.getOwnerComponent().getId() + "---home--emptable").getBinding("items");

            /*rootComponentContainer-basicTemplate---home--emptable*/
            // console.log(sap.ui.getCore().byId(this.getOwnerComponent().getId()));

            dialog.getModel().submitBatch(batchGroupId).then(function () {
                _oBinding.refresh(/*batchGroupId*/);

                if (!dialog.getBindingContext().hasPendingChanges()) {
                    const userName = that.getView().getBindingContext().getProperty("UserName");
                    dialog.close();
                    MessageBox.success("User " + userName + " is updated successfully");
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
            /*this.getView().byId("empIconTabBar").setSelectedKey("timesheet");
            this.getView().bindElement({
                path: "/" + oEvent.getParameter("arguments").employeePath,
                parameters: {
                    $$updateGroupId: batchGroupId
                }
            });*/
        },
        getGroupHeader: function (oGroup) {
            const oDate = new Date(oGroup.key);

            return new GroupHeaderListItem({
                tooltip: oGroup.key,
                title:  `${oGroup.key} ${this._getWeekNumber(oDate)} ${oDate.toLocaleString('default', { weekday: 'short' })}`
                /*,
                count : "Total: 2h80, approved: 0h00, 0 %"*/
            })
        },
        computeDuration: function (strDateBeg, strDateEnd) {
            const formatTwoDigits = (n) => n < 10 ? '0' + n : n;

            if (strDateEnd) {
                const elapsed = new Date(new Date(strDateEnd) - new Date(strDateBeg));
                return elapsed.getUTCHours() + "h" + formatTwoDigits(elapsed.getUTCMinutes())
            }
        },

        _trimTextInput: function (oDlgEvent) {
            const field = oDlgEvent.getSource();

            this._oParentView.byId(field.getId()).setValue(field.getValue().trim())
        }
    })
});