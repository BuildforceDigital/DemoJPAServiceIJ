sap.ui.define([
    "./BaseController",
    "sap/ui/model/json/JSONModel",
    "sap/ui/Device"
], function (BaseController, JSONModel, Device) {
    "use strict";

    return BaseController.extend("nl.buildforce.gt.controller.App", {

        onInit: function () {
            // shortcut to FioriClient helper
            this.oFioriClient = this.getComponent().getFioriClient();

            // ui view model
            var oViewModel = new JSONModel({
                "isFioriClientAvailable": this.oFioriClient.isAvailable()
            });
            this.setModel(oViewModel, "ui");
            window.oViewModel = oViewModel;

            // shortcut
            this.oToolPage = this.byId("toolPage");
            this.getCurrentLogin()
        },

        /*
        onAfterRendering: function() {
            // auto expand on tablet
            if (Device.system.tablet) {
                this.oToolPage.setSideExpanded(true);
            }
        },
        */

        onHelp: function () {
            sap.m.URLHelper.redirect("https://uacp2.hana.ondemand.com/viewer/p/SAP_FIORI_CLIENT", true);
        },

        onPrint: function () {
            if (this.oFioriClient.isAvailable()) {
                var oPage = this.getView().byId("idAppControl").getCurrentPage(),
                    $DomRef = oPage.$()[0];

                // cordova.plugins.printer.isAvailable();

                // print DOM element
                cordova.plugins.printer.print($DomRef);
            } else {
                // regular print
                window.print();
            }
        },

        onUser: function () {
            if (window.oViewModel.getProperty("/userName") === '') {
                jQuery.ajax(this.addCsrfHeader({
                    url: '/api/do_login',
                    type: 'POST',
                    data: 'Lelikerd',
                    success: window.oViewModel.setProperty("/userName", "Lelikerd")
                }));
            }
            else {
                jQuery.ajax(this.addCsrfHeader({
                    url: '/api/do_logout',
                    type: 'POST',
                    success: window.oViewModel.setProperty("/userName", "")
                }));
            }
        },

        addCsrfHeader: function (opts) {
            var token = Cookies.get('XSRF-TOKEN');
            if (token) {
                console.log('Setting csrf token00: ' + token);
                opts['headers'] = {
                    'X-XSRF-TOKEN': token
                }
            } else {
                console.log('No csrf token00.');
            }

            return opts;
        },

        getCurrentLogin: function () {
            function handleResponse(data, status) {
                if (status === 'success') {
                    window.oViewModel.setProperty("/userName", data);
                    console.log("succes " + data);
                } else {
                    window.oViewModel.setProperty("/userName", "");
                    console.log("no succes " + data.status);
                }
            }

            jQuery.ajax(this.addCsrfHeader({
                url: '/api/current_login',
                type: 'GET',
                success: this.handleResponse,
                error: this.handleResponse
            }));
        },

        onItemSelect: function (oEvent) {
            this.onNavTo(oEvent);

            if (Device.system.phone) {
                this.oToolPage.setSideExpanded(false);
            }
        },

        onSideNavButtonPress: function () {
            this.oToolPage.setSideExpanded(!this.oToolPage.getSideExpanded());
        }

    });
});