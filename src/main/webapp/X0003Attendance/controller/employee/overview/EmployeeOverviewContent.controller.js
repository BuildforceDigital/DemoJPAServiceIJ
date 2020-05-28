sap.ui.define([
    "sap/ui/demo/nav/controller/BaseController",
    "sap/ui/model/Filter",
    "sap/ui/model/FilterOperator",
    "sap/ui/model/Sorter",
    "sap/m/ViewSettingsDialog",
    "sap/m/ViewSettingsItem",
    "sap/m/GroupHeaderListItem",
    "sap/ui/core/library",
    "sap/ui/model/odata/type/DateTimeOffset"
], function (
    BaseController,
    Filter,
    FilterOperator,
    Sorter,
    ViewSettingsDialog,
    ViewSettingsItem,
    GroupHeaderListItem,
    CoreLibrary,
    DateTimeOffset
) {
    "use strict";

    return BaseController.extend("sap.ui.demo.nav.controller.employee.overview.EmployeeOverviewContent", {
        _getWeekNumber: (d) => {
            // Copy date so don't modify original
            // Make Sunday's day number 7
            const oDate = new Date(d), dayOfWeek = oDate.getUTCDay() || 7;
            // Set to nearest Thursday: current date + 4 - current day number
            oDate.setUTCDate(oDate.getUTCDate() + 4 - dayOfWeek);
            // Get first day of year
            const yearStart = new Date(Date.UTC(oDate.getUTCFullYear(), 0, 1));
            // Calculate full weeks to nearest Thursday
            const weekNo = Math.ceil((((oDate - yearStart) / 86400000) + 1) / 7);
            // Return array of year and week number
            return `W${weekNo}-${dayOfWeek}`
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

        onInit: function () {
            var oRouter = this.getRouter();

            // this._oTable = this.byId("employeesTable");
            // this._oVSD = null;
            // this._sSortField = null;
            // this._bSortDescending = false;
            // this._aValidSortFields = ["ID", "FullName", "UserName"];
            // this._sSearchQuery = null;
            // this._oRouterArgs = null;

            // this._initViewSettingsDialog();

            oRouter.getRoute("appHome").attachMatched(this._onRouteMatched, this);

        },

        handleChange: function (oEvent) {
            const sFrom = oEvent.getParameter("from"),
                sTo = oEvent.getParameter("to"),
                oEventSource = oEvent.getSource();

            oEventSource.setValueState(oEvent.getParameter("valid") ? CoreLibrary.ValueState.None : CoreLibrary.ValueState.Error);

            const bFilter = [new Filter("CheckInDateTime", FilterOperator.BT,
                /*"2019-11-01T14:35:20.544411Z"*/ sFrom.toISOString(),
                /*"2020-11-01T14:35:20.544411Z"*/ sTo.toISOString())];

            // filter binding
            var oList = this.byId("employeesTable");
            var oBinding = oList.getBinding("items");
            oBinding.filter(bFilter);
        },

        _onRouteMatched: function (oEvent) {
            // save the current query state
            this._oRouterArgs = oEvent.getParameter("arguments");
            this._oRouterArgs.query = this._oRouterArgs["?query"] || {};

            if (this._oRouterArgs.query) {

                // search/filter via URL hash
                this._applySearchFilter(this._oRouterArgs.query.search);

                // sorting via URL hash
                this._applySorter(this._oRouterArgs.query.sortField, this._oRouterArgs.query.sortDescending);

                // show dialog via url hash
                if (this._oRouterArgs.query.showDialog) {
                    this._oVSD.open();
                }

            }
        },

        onSortButtonPressed: function (oEvent) {
            var oRouter = this.getRouter();
            this._oRouterArgs.query.showDialog = 1;
            oRouter.navTo("employeeOverview", this._oRouterArgs);
        },

        onSearchEmployeesTable: function (oEvent) {
            var oRouter = this.getRouter();
            // update the hash with the current search term
            this._oRouterArgs.query.search = oEvent.getSource().getValue();
            oRouter.navTo("employeeOverview", this._oRouterArgs, true /*no history*/);
        },

        _initViewSettingsDialog: function () {
            var oRouter = this.getRouter();
            this._oVSD = new ViewSettingsDialog("vsd", {
                confirm: function (oEvent) {
                    var oSortItem = oEvent.getParameter("sortItem");
                    this._oRouterArgs.query.sortField = oSortItem.getKey();
                    this._oRouterArgs.query.sortDescending = oEvent.getParameter("sortDescending");
                    delete this._oRouterArgs.query.showDialog;
                    oRouter.navTo("employeeOverview", this._oRouterArgs, true /*without history*/);
                }.bind(this),
                cancel: function (oEvent) {
                    delete this._oRouterArgs.query.showDialog;
                    oRouter.navTo("employeeOverview", this._oRouterArgs, true /*without history*/);
                }.bind(this)
            });

            // init sorting (with simple sorters as custom data for all fields)
            this._oVSD.addSortItem(new ViewSettingsItem({
                key: "ID",
                text: "Employee ID",
                selected: true			// by default the MockData is sorted by EmployeeID
            }));

            this._oVSD.addSortItem(new ViewSettingsItem({
                key: "FullName",
                text: "First Name",
                selected: false
            }));

            this._oVSD.addSortItem(new ViewSettingsItem({
                key: "UserName",
                text: "Last Name",
                selected: false
            }));
        },

        _applySearchFilter: function (sSearchQuery) {
            var aFilters, oFilter, oBinding;

            // first check if we already have this search value
            if (this._sSearchQuery === sSearchQuery) {
                return;
            }
            this._sSearchQuery = sSearchQuery;
            this.byId("searchField").setValue(sSearchQuery);

            // add filters for search
            aFilters = [];
            if (sSearchQuery && sSearchQuery.length > 0) {
                aFilters.push(new Filter("FullName", FilterOperator.Contains, sSearchQuery));
                aFilters.push(new Filter("UserName", FilterOperator.Contains, sSearchQuery));
                oFilter = new Filter({filters: aFilters, and: false});  // OR filter
            } else {
                oFilter = null;
            }

            // update list binding
            //oBinding = this._oTable.getBinding("items");
            //oBinding.filter(oFilter, "Application");
        },

        /**
         * Applies sorting on our table control.
         * @param {string} sSortField        the name of the field used for sorting
         * @param {string} sortDescending    true or false as a string or boolean value to specify a descending sorting
         * @private
         */
        _applySorter: function (sSortField, sortDescending) {
            var bSortDescending, oBinding, oSorter;

            // only continue if we have a valid sort field
            if (sSortField && this._aValidSortFields.indexOf(sSortField) > -1) {

                // convert  the sort order to a boolean value
                if (typeof sortDescending === "string") {
                    bSortDescending = sortDescending === "true";
                } else if (typeof sortDescending === "boolean") {
                    bSortDescending = sortDescending;
                } else {
                    bSortDescending = false;
                }

                // sort only if the sorter has changed
                if (this._sSortField && this._sSortField === sSortField && this._bSortDescending === bSortDescending) {
                    return;
                }

                this._sSortField = sSortField;
                this._bSortDescending = bSortDescending;
                oSorter = new Sorter(sSortField, bSortDescending);

                // sync with View Settings Dialog
                this._syncViewSettingsDialogSorter(sSortField, bSortDescending);

                //oBinding = this._oTable.getBinding("items");
                //oBinding.sort(oSorter);
            }
        },

        _syncViewSettingsDialogSorter: function (sSortField, bSortDescending) {
            // the possible keys are: "EmployeeID" | "FirstName" | "LastName"
            // Note: no input validation is implemented here
            this._oVSD.setSelectedSortItem(sSortField);
            this._oVSD.setSortDescending(bSortDescending);
        }
    });

});