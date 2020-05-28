sap.ui.define([
    "sap/m/MessageBox",
    "sap/m/MessageToast",
    "sap/ui/demo/nav/controller/BaseController",
    "sap/ui/model/json/JSONModel",
    "sap/ui/model/Filter",
    "sap/ui/model/FilterOperator",
    "sap/ui/model/FilterType"
], function (MessageBox, MessageToast, BaseController, JSONModel, Filter, FilterOperator, FilterType) {
    "use strict";
    const _aValidTabKeys = ["Personal", "Notifications", "Documents", "Projects",
        "TimeSheets", "ManagedO", "Contact", "Account", "Calendar", "Emergency"];

    return BaseController.extend("sap.ui.demo.nav.controller.employee.UserCentric", {

        _navTo(strTab, bEdit) {
            this.getRouter().navTo("userCentricRoute", {
                userId: this.getView().getBindingContext().getProperty("ID"),
                query: (bEdit) ? {
                        tab: strTab,
                        edit: true
                    } : {
                        tab: strTab
                    }
            }, true /*without history*/)
        },

        onInit() {
            const oView = this.getView();
            const oJSONData = {
                    editState: false,
                    hasUIChanges: false,
                    selectedTabKey: ""
                },
                oMessageModel = sap.ui.getCore().getMessageManager().getMessageModel(),
                oMessageModelBinding = oMessageModel.bindList(
                    "/", undefined, [], new Filter("technical", FilterOperator.EQ, true));

            function _onRouteMatched(oEvent) {
                const oArgs = oEvent.getParameter("arguments"),
                    oView = this.getView();

                const oModel = oView.getModel("usrCtrView");

                oView.bindElement({
                    path: "/Users(ID=" + oArgs.userId + ")",
                    events: {
                        change: this._onBindingChange.bind(this),
                        dataRequested: () => oView.setBusy(true),
                        dataReceived: () => oView.setBusy(false)
                    },
                    parameters: {
                        $$updateGroupId: 'userGroup'
                    }
                });

                const oQuery = oArgs["?query"];
                if (oQuery && _aValidTabKeys.indexOf(oQuery.tab) > -1) {
                    oModel.setProperty("/editState", oQuery.edit === "true");
                    oModel.setProperty("/selectedTabKey", oQuery.tab);
                    this._displayAsInViewModel();
                } else {
                    // The default query param should be visible at all time
                    this._navTo(_aValidTabKeys[0])
                }
            }

            oView.setModel(new JSONModel(oJSONData), "usrCtrView");
            oView.setModel(oMessageModel, "message");

            oMessageModelBinding.attachChange(this._onMessageBindingChange, this);
            this._bTechnicalErrors = false;

            this.getRouter().getRoute("userCentricRoute").attachMatched(_onRouteMatched, this);
        },

        onResetChanges: function (oEvent) {
            const oModel = this.getView().getModel("usrCtrView");

            function _resetModel() {
                oModel.setProperty("/editState", false);
                oModel.setProperty("/hasUIChanges", false);
            }

            _resetModel();
            this._navTo(oModel.getProperty("/selectedTabKey"))
        },

        /**
         * Set hasUIChanges flag in View Model
         * @param {boolean} [bHasUIChanges] - set or clear hasUIChanges
         * if bHasUIChanges is not set, the hasPendingChanges-function of the OdataV4 model determines the result
         */
        _setUIChanges: function (bHasUIChanges) {
            const oView = this.getView();
            const oModel = oView.getModel("usrCtrView");

            oModel.setProperty("/editState", bHasUIChanges);
            oModel.setProperty("/hasUIChanges",
                this._bTechnicalErrors
                || (bHasUIChanges === undefined ? oView.getModel().hasPendingChanges() : bHasUIChanges));
        },

        onSave: function () {
            const fnSuccess = function () {
                this._setBusy(false);
                MessageToast.show("User data sent to the server");
                this._setUIChanges(false);
            }.bind(this);

            const fnError = function (oError) {
                this._setBusy(false);
                this._setUIChanges(false);
                MessageBox.error(oError.message);
            }.bind(this);

            this._setBusy(true); // Lock UI until submitBatch is resolved.
            this.getView().getModel().submitBatch("userGroup").then(fnSuccess, fnError);
            this._bTechnicalErrors = false; // If there were technical errors, a new save resets them.
        },

        _onMessageBindingChange: function (oEvent) {
            const aContexts = oEvent.getSource().getContexts();
            let bMessageOpen = false;

            if (!bMessageOpen && aContexts.length) {
                // Extract and remove the technical messages
                const aMessages = aContexts.map(function (oContext) {
                    return oContext.getObject();
                });
                sap.ui.getCore().getMessageManager().removeMessages(aMessages);

                this._setUIChanges(true);
                this._bTechnicalErrors = true;
                MessageBox.error(aMessages[0].message, {
                    onClose: function () {
                        bMessageOpen = false;
                    }
                })
            }
        },

        onEditTogglePress(oEvent) {
            // Assembly hash-based navigation URL
            const oModel = this.getView().getModel("usrCtrView");

            oModel.setProperty("/editState", !oModel.getProperty("/editState"));
            this._navTo(oModel.getProperty("/selectedTabKey"), oModel.getProperty("/editState"))
        },

        _displayAsInViewModel() {
            const strSelectedTab = this.getView().getModel("usrCtrView").getProperty("/selectedTabKey");
            // this.byId('personalTab').removeAllContent();

            this.getRouter().getTargets().display("resumeTab" + strSelectedTab);
        },


        _onBindingChange(oEvent) {
            // No data for the binding
            if (!this.getView().getBindingContext()) {
                this.getRouter().getTargets().display("objectNotFound");
            }
        },

        /**
         * We use this event handler to update the hash in case a new tab is selected.
         * @param oEvent
         */
        onTabSelect(oEvent) {
            this._navTo(oEvent.getParameter("selectedKey"))
        },
        /**
         * Set busy flag in View Model
         * @param {boolean} bIsBusy - set or clear busy
         */
        _setBusy: function (bIsBusy) {
            this.getView().getModel("usrCtrView").setProperty("/busy", bIsBusy);
        }

    });

});