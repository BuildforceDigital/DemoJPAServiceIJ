sap.ui.define([
    "sap/base/strings/formatMessage",
    "sap/m/MessageBox",
    "sap/m/MessageToast",
    "sap/ui/core/mvc/Controller",
    "sap/ui/model/Filter",
    "sap/ui/model/FilterOperator",
    "sap/ui/model/FilterType",
    "sap/ui/model/Sorter"
], function (formatMessage, MessageBox, MessageToast, Controller, Filter, FilterOperator, FilterType, Sorter) {
    return Controller.extend("sap.ui.demo.basicTemplate.controller.Main", {
        _deleteEmp: function (oSelected) {
            if (oSelected) {
                var that = this;
                var EMPID = oSelected.getBindingContext().getProperty("ID");
                var fullname = oSelected.getBindingContext().getProperty("FullName");
                oSelected.getBindingContext().delete("$auto").then(function () {
                    var successMessage = "User profile " + fullname + " is deleted";
                    MessageBox.show(successMessage, "SUCCESS", "Success");
                    var deleteButton = that.getView().byId("deleteButton");
                    deleteButton.setEnabled(false)
                }, function (oError) {
                    MessageBox.error(oError.message);
                });
            }
        },
        /* =========================================================== */
        /* lifecycle methods                                           */
        /* =========================================================== */
        onInit: function () {
            const oMessageManager = sap.ui.getCore().getMessageManager(),
                oMessageModel = oMessageManager.getMessageModel(),
                oMessageModelBinding =
                    oMessageModel.bindList("/", undefined, [], new sap.ui.model.Filter("technical", FilterOperator.EQ, true)),
                oViewModel = new sap.ui.model.json.JSONModel({
                    order: 0
                    /*,listItemCount: null*/
                });
            this.getView().setModel(oViewModel, "appView");
            this.getView().setModel(oMessageModel, "message");
            oMessageModelBinding.attachChange(this._onMessageBindingChange, this);
            // this._bTechnicalErrors = false;

            this._oTable = this.byId("emptable")
        },

        /* =========================================================== */
        /* event handlers                                              */
        /* =========================================================== */

        /**
         * After list data is available, this handler method updates the
         * master list counter
         * @param {sap.ui.base.Event} oEvent the update finished event
         * @public
         */
        onUpdateFinished: function (oEvent) {
            // update the master list object counter after new data is loaded
            if (this._oTable.getBinding("items").isLengthFinal()) {
                //sTitle = this.getResourceBundle().getText("masterTitleCount", [iTotalItems]);
                this.getView().getModel("appView")
                    .setProperty("/listItemCount", oEvent.getParameter("total"))
            }
        },


        /**
         * Convenience method for retrieving a translatable text.
         * @param {string} sTextId - the ID of the text to be retrieved.
         * @param {Array} [aArgs] - optional array of texts for placeholders.
         * @returns {string} the text belonging to the given ID.
         */
        _getText: function (sTextId, aArgs) {
            return this.getOwnerComponent().getModel("i18n").getResourceBundle().getText(sTextId, aArgs);
        },
        _onMessageBindingChange: function (oEvent) {
            const aContexts = oEvent.getSource().getContexts();
            let bMessageOpen = false;
            if (!bMessageOpen && aContexts.length) {// Extract and remove the technical messages
                var aMessages = aContexts.map(function (oContext) {
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
        },
        _onRefresh: function () {
            var empTable = this.getView().byId("emptable"),
                oBinding = empTable.getBinding("items");
            empTable.setBusy(true);
            oBinding.refresh();
            MessageToast.show("User list refreshed");
            empTable.setBusy(false);
        },
        /*_trimTextInput: function (oDlgEvent) {
          var field = oDlgEvent.getSource();
          this.getView().byId(field.getId()).setValue(field.getValue().trim())
        },*/
        onClose: function (oEvent) {
            var oDialog = (oEvent.getSource()).getEventingParent();
            oDialog.close();
        },
        onCreate: function (oEvent) {
            const oView = this.getView();
            if (!oView.byId("createDialog")) {
                oView.addDependent(sap.ui.xmlfragment(oView.getId(), "sap.ui.demo.basicTemplate.view.Create", this))
            }
            oView.byId("createDialog").open();
            //clearing input data
            oView.byId("fullNameTextField").setValue("");
            oView.byId("epemailTextField").setValue("");
        },
        onCreateEmp: function (oEvent) {
            function getRandomArbitrary(min, max) {
                return Math.floor(Math.random() * (max - min) + min)
            }

            function uuid4(a) {
                return a ? (a ^ Math.random() * 16 >> a / 4).toString(16) : ([1e7] + -1e3 + -4e3 + -8e3 + -1e11).replace(/[018]/g, uuid4)
            }


            //getting input data
            var email = this.getView().byId("epemailTextField").getValue().trim();
            //creating payload
            var data = {
                "ID": getRandomArbitrary(1000, 2000), //default ID
                "FullName": this.getView().byId("fullNameTextField").getValue().trim(),
                "UserName": email,
                "PrivateEmail": email,
                "Gender": "?"
            };

            var that = this;
            var oList = this.getView().byId("emptable");

            var oContext = oList.getBinding("items").create(data);
            // trigger batch request
            this.getView().getModel().submitBatch("UserGroup");
            oContext.created().then(function () {
                var dialog = that.getView().byId("createDialog");
                dialog.close();
                MessageBox.success("User created: " + oContext.getProperty("UserName"));
                that._onRefresh()
            });
            // Select and focus the table row that contains the newly created entry
            oList.getItems().some(function (oItem) {
                if (oItem.getBindingContext() === oContext) {
                    oItem.focus();
                    oItem.setSelected(true);
                    return true;
                }
            });
        },
        onDelete: function (oEvent) {
            const oSelected = this.getView().byId("emptable").getSelectedItem(),
            fullName = oSelected.getBindingContext().getProperty("FullName");

            MessageBox.confirm("Do you sure want to delete User profile: " + fullName,
                jQuery.proxy(function (bResult) {
                    if (bResult === "OK") {
                        this._deleteEmp(oSelected);
                    }
                }, this),
                "Delete User");
        },

        /*onInputTextChange: function (oDlgEvent) {
          this._trimTextInput(oDlgEvent)
        },*/
        onItemPress: function (oEvent) { //navigation
            var oItem = oEvent.getSource();
            var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
            oRouter.navTo("Detail", {
                employeePath: encodeURIComponent(oItem.getBindingContext().getPath().substr(1))
            });
        },
        onLogoutPress: function (oEvent) {
            MessageBox.confirm("Do you sure want to logout?",
                jQuery.proxy(function (bResult) {
                    if (bResult === "OK") {
                        var url = window.location.href;
                        var arr = url.split("/");
                        var location = arr[0] + "//" + arr[2];
                        window.location.replace(location + "/do/logout");
                    }
                }, this),
                "Logout");
        },
        onRefreshPress: function (oEvent) {
            this._onRefresh();
        },

        /**
         * Search for the FullName/lastname/email in the search field.
         */
        onSearch: function () {
            var oView = this.getView(),
                sValue = oView.byId("searchField").getValue(),
                oFilter = new Filter({
                    filters: [
                        new Filter({
                            path: "FullName",
                            operator: FilterOperator.Contains,
                            value1: sValue
                        })/*,
            new Filter({
                path: "Nickname",
                operator: FilterOperator.Contains,
                value1: sValue
            }),
            new Filter({
                path: "Gender",
                operator: FilterOperator.Contains,
                value1: sValue
            })*/
                    ]
                });

            oView.byId("emptable").getBinding("items").filter(oFilter, FilterType.Application);
        },
        onSelection: function (oEvent) {
            var deleteButton = this.getView().byId("deleteButton");
            deleteButton.setEnabled(true);
        },
        /**
         * Sort the table according to the last name.
         * Cycles between the three sorting states "none", "ascending" and "descending"
         */
        onSort: function () {
            var oView = this.getView(),
                aStates = [undefined, "asc", "desc"],
                aStateTextIds = ["sortNone", "sortAscending", "sortDescending"],
                sMessage,
                iOrder = oView.getModel("appView").getProperty("/order");

            // // Cycle between the states
            iOrder = (iOrder + 1) % aStates.length;
            var sOrder = aStates[iOrder];

            oView.getModel("appView").setProperty("/order", iOrder);
            oView.byId("emptable").getBinding("items").sort(sOrder && new Sorter("FullName", sOrder === "desc"));

            sMessage = this._getText("sortMessage", [this._getText(aStateTextIds[iOrder])]);
            MessageToast.show(sMessage);
        },

        formatDate: function (date) {
            var d = new Date(date),
                month = "" + (d.getMonth() + 1),
                day = "" + d.getDate(),
                year = d.getFullYear();

            if (month.length < 2) {
                month = "0" + month;
            }
            if (day.length < 2) {
                day = "0" + day;
            }

            return [year, month, day].join("-");
        },

        formatNumber: function (value) {
            return value.replace(/\,/g, "");
        }
        /* =========================================================== */
        /* begin: internal methods                                     */
        /* =========================================================== */

    });
});