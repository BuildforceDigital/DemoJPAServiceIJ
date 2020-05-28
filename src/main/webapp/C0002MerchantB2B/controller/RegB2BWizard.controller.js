sap.ui.define([
    "sap/ui/demo/walkthrough/controller/BaseController",
    'sap/ui/model/json/JSONModel',
    'sap/ui/model/SimpleType',
    "sap/ui/model/ValidateException",
    "sap/m/MessageBox",
    "sap/m/MessageToast",
    "sap/ui/demo/walkthrough/dist/Validator"
], function (BaseController, JSONModel, SimpleType, ValidateException, MessageBox, MessageToast, Validator) {
    const batchGroupId = "myUpdateGroupId";

    const _validateValue = oValue => {
        const rexName = /^[a-z]+.*/i;

        if (oValue.length < 2) {
            const text = `"${oValue}" is to few characters for name.`;
            throw new sap.ui.model.ValidateException(text);
        }
        if (!(oValue.match(rexName))) {
            const text = `"${oValue}\" is not a valid name.`;
            throw new sap.ui.model.ValidateException(text);
        }
    };
    return BaseController.extend("sap.ui.demo.walkthrough.controller.RegB2BWizard", {
        _populateFields() {
            this.getView().bindElement({
                path: '/RegB2BSubmits(0)', parameters: {$$updateGroupId: batchGroupId},
                events: {
                    dataRequested: () => this.byId("wizardContentPage").setBusy(true),
                    dataReceived: () => {
                        this.byId("wizardContentPage").setBusy(false);

                        /*const oModel = this.getView().getModel();

                        this.byId("wizardContentPage")*/




                    }
                }
            })
        },
        onInit() {
            // Attaches Popover on mouse hoover
            function attachPopoverOnMouseover(targetControl, popover) {
                let _timeId;

                function _showPopover(targetControl, popover) {
                    _timeId = setTimeout(() => popover.openBy(targetControl), 500);
                }

                function _clearPopover(popover) {
                    clearTimeout(_timeId) || popover.close();
                }

                targetControl.addEventDelegate({
                    onmouseover: _showPopover.bind(this, targetControl, popover),
                    onmouseout: _clearPopover.bind(this, popover)
                }, this);
            }

            attachPopoverOnMouseover(this.byId("target"), this.byId("popover"));

            // Attaches validation handlers
            sap.ui.getCore().attachValidationError(oEvent => oEvent.getParameter("element").setValueState(sap.ui.core.ValueState.Error));
            sap.ui.getCore().attachValidationSuccess(oEvent => oEvent.getParameter("element").setValueState(sap.ui.core.ValueState.None));

            this.model = new sap.ui.model.json.JSONModel({
                discountCoupon: false,
                nextBtnText: "Step 2",
                w01: {
                    calendarBilling: true,
                    bundleChoice: null,
                    date: new Date(),
                    // discountCouponInput: "",
                    valid: false
                }/*,
                w02: {
                    personalName: "",
                    nickName: "",
                    gender: "",
                    jobTitle: "",
                    independentContractor: false,
                    department: "",
                    birthday: "",
                    nationality: "",
                    valid: false
                },
                w03: {
                    formalLegalOrganizationName: "",
                    division: "",
                    commercialRegisterNr: null,
                    vatIdentification: "",
                    valid: false
                }*/,
                w04: { // Institution address
                    /*companyCityName: "",
                    companyCountry: "NL",
                    companyPostalCode: "",
                    companyStreetAddress: "",
                    companyDelivery: "",*/
                    correspondingVisitAddress: true,
                    valid: false
                } /*,
                w05: { // Institution visit address
                    physCityName: "",
                    physCountry: "NL",
                    physPostalCode: "",
                    physStreetAddress: "",
                    physDelivery: "",
                    valid: false
                },
                w06: {
                    billingEmailAddress:  "",
                    organizationWebSite:  "",
                    organizationTelephone:"",
                    valid: false
                },
                w07: {
                    persEmailAddress: "",
                    persTelephone:    "",
                    persContactNote:    "",
                    secretPassword: null,
                    valid: false
                }*/
            });
            this.theView = this.getView();
            this.theView.setModel(this.model, "regB2BWizard");
            this._wizard = this.byId("idMerchantB2BWizard");
            this._oWizardContentPage = this.byId("wizardContentPage");

            this._oNavContainer = this.byId("wizardNavContainer");
            this._oWizardReviewPage = sap.ui.xmlfragment("sap.ui.demo.walkthrough.view.ReviewPage", this);
            this._oNavContainer.addPage(this._oWizardReviewPage);
            this._PaymentPage = sap.ui.xmlfragment("sap.ui.demo.walkthrough.view.PaymentPage", this);
            this._oNavContainer.addPage(this._PaymentPage);
            this._ThankYouPage = sap.ui.xmlfragment("sap.ui.demo.walkthrough.view.ThankYouPage", this);
            this._oNavContainer.addPage(this._ThankYouPage);

            this._populateFields()
        },

        procesRegistration: (oEvent) => {
            /*oEvent.getSource().getEventingParent().getModel().submitBatch(batchGroupId)
                .then(() => MessageToast.show("Updated successfully"))*/
        },


        /**
         *  Custom Types
         */

        /*
         * Custom type for validating a Name
         * @class
         * @extends sap.ui.model.SimpleType
         */
        CustomReqNameType: SimpleType.extend("ChkReqName", {
            formatValue(oValue) {return oValue;},
            parseValue(oValue) {//parsing step takes place before validating step, value could be altered here
                return oValue.trim();
            },
            validateValue(oValue) {_validateValue(oValue)}
        }),

        /*
         * Custom type for validating an Optional Name
         * @class
         * @extends sap.ui.model.SimpleType
         */
        CustomNameOptType: SimpleType.extend("ChkOptName", {
            formatValue(oValue) {return oValue;},
            parseValue(oValue) {//parsing step takes place before validating step, value could be altered here
                return oValue.trim();
            },
            validateValue(oValue) {if (oValue.length) _validateValue(oValue)}
        }),

        /**
         * Custom type for validating an E-Mail address
         * @class
         * @extends sap.ui.model.SimpleType
         */
        customEMailType: SimpleType.extend("email", {
            formatValue(oValue) {return oValue;},
            parseValue(oValue) {//parsing step takes place before validating step, value could be altered here
                return oValue.trim();
            },
            validateValue(oValue) {
                // The following Regex is NOT a completely correct one and only used for demonstration purposes.
                // RFC 5322 cannot even checked by a Regex and the Regex for RFC 822 is very long and complex.
                const rexMail = /^\w+[\w-+.]*@\w+([-.]\w+)*\.[a-zA-Z]{2,}$/;

                if (!oValue) {
                    const text = "This is a required field.";
                    throw new sap.ui.model.ValidateException(text);
                }
                if (!oValue.match(rexMail)) {
                    const text = `${oValue} is not a valid email address.`;
                    throw new sap.ui.model.ValidateException(text);
                }
            }
        }),

        /*
         * Inline validations for W01
         */

        _checkW01valid() {
            const error = this.byId("idDtpW01StartDate").getValueState() === 'Error' ||
                (this.model.getProperty("/w01/bundleChoice") === null);
            this.model.setProperty("/w01/valid", !error)
        },

        onRegister() {
            this.getView().getModel().submitBatch(batchGroupId).then(
                () => {MessageToast.show("Updated successfully");},
                () => {MessageToast.show("Rejected");});
        },

        // Inline validation for Date Picker
        onDtpW01Change(oEvent) {
            const oElement = oEvent.getSource(); // , valid = oEvent.getParameter("valid");
            // this.model.setProperty("/w01/valid", valid);

            if (oEvent.getParameter("valid")) {
                oElement.setValueState("Information");
            } else {
                oElement.setValueState("Error");
                MessageToast.show("Entered date is in the past.");
            }
            this._checkW01valid()
        },
        onTileW01Click(oEvent) {
            // Toggle selection
            if (this.model.getProperty("/w01/bundleChoice") !== null) {
                this.model.setProperty("/w01/bundleChoice", null);
            } else {
                const oCtx = oEvent.getSource().getBindingContext();
                this.model.setProperty("/w01/bundleChoice", oCtx.getProperty("ID"));
                MessageToast.show(`Bundle ${oCtx.getProperty("Name")} is selected.\nClick again for deselection.`);
            }
            this._checkW01valid()
        },

        /*
         * Inline validations for W02
         */
        onTxtW02Change(oEvent) {
            this._validateInput(oEvent);

            const error = !this.byId("idTxtW02PersN").getValue().length ||
                this.byId("idTxtW02Dep").getValueState() === 'Error' ||
                this.byId("idTxtW02JobT").getValueState() === 'Error' ||
                this.byId("idTxtW02NickN").getValueState() === 'Error' ||
                this.byId("idTxtW02PersN").getValueState() === 'Error';
            this.model.setProperty("/w02/valid", !error);
        },

        /*
         * Inline validations for W03
         */
        onTxtW03Change(oEvent) {
            this._validateInput(oEvent);

            const error = !this.byId("idTxtW03CompanyName").getValue().length ||
                !this.byId("idTxtW03CoC").getValue().length ||
                this.byId("idTxtW03CompanyName").getValueState() === 'Error' ||
                this.byId("idTxtW03Subsidiary").getValueState() === 'Error' ||
                this.byId("idTxtW03CoC").getValueState() === 'Error' ||
                this.byId("idTxtW03VAT").getValueState() === 'Error';
            this.model.setProperty("/w03/valid", !error);
        },

        /*
         * Inline validations for W04
         */
        onChkW04SelCorrVisitAddress() {
            this._setDiscardableProperty({
                message: "Are you sure you want to change the payment type ? This will discard your progress.",
                discardStep: this.byId("Step_5") /*,
                modelPath: "/selectedPayment",
                historyPath: "prevPaymentSelect"*/

            });
        },

        onTxtW04Change(oEvent) {
            this._validateInput(oEvent);

            const error =
                !this.byId("idTxtW04PostalCode").getValue().length ||
                !this.byId("idTxtW04City").getValue().length ||
                !this.byId("idTxtW04StreetAddr").getValue().length ||
                this.byId("idTxtW04PostalCode").getValueState() === 'Error' ||
                this.byId("idTxtW04City").getValueState() === 'Error' ||
                this.byId("idTxtW04StreetAddr").getValueState() === 'Error' ||
                this.byId("idTxtW04Delivery").getValueState() === 'Error';
            this.model.setProperty("/w04/valid", !error);
        },

        /*
         * Inline validations for W05
         */

        onTxtW05Change(oEvent) {
            this._validateInput(oEvent);

            const error =
                !this.byId("idTxtW05PostalCode").getValue().length ||
                !this.byId("idTxtW05City").getValue().length ||
                !this.byId("idTxtW05StreetAddr").getValue().length ||
                this.byId("idTxtW05PostalCode").getValueState() === 'Error' ||
                this.byId("idTxtW05City").getValueState() === 'Error' ||
                this.byId("idTxtW05StreetAddr").getValueState() === 'Error' ||
                this.byId("idTxtW05Delivery").getValueState() === 'Error';
            this.model.setProperty("/w05/valid", !error);
        },

        /*
         * Inline validations for W06
         */

        _checkW06valid() {
            const error = !this.byId("idTxtW06BillingEmailAddress").getValue().length ||
                !this.byId("idTxtW06BillingEmailAddressConf").getValue().length ||
                this.byId("idTxtW06BillingEmailAddress").getValueState() === 'Error' ||
                this.byId("idTxtW06BillingEmailAddressConf").getValueState() === 'Error' ||
                this.byId("idTxtW06OrganizationUrl").getValueState() === 'Error' ||
                this.byId("idTxtW06OrganizationTelephone").getValueState() === 'Error';

            this.model.setProperty("/w06/valid", !error);
        },
        onTxtW06BillingEmailAddressChange(oEvent) {
            const oControl = this.byId("idTxtW06BillingEmailAddressConf");

            this._validateInput(oEvent);

            oControl.setValue(null);
            oControl.setValueState("None");

            this._checkW06valid()
        },
        onTxtW06ConfEmailAddressChange(oEvent) {
            if (oEvent.getSource().getValue()) {
                const state =
                    oEvent.getSource().getValue() === this.model.getProperty("/w06/billingEmailAddress")
                        ? "Success" : "Error";
                oEvent.getSource().setValueState(state);

                if (state === "Error") {
                    MessageToast.show("E-Mail must be corresponding.");
                }
            }
            this._checkW06valid()
        },
        onTxtW06eCom(oEvent) {
            this._validateInput(oEvent);

            this._checkW06valid()
        },

        /*
         * Inline validations for W07
         */

        _checkW07all: function (oEvent) {

 /*
            // Wizard W07 section
            {this._validateInput2("idTxtW07PersEmailAddress");


                const oInput = this.byId("idTxtW07PersEmailAddressConf");

                const state =
                    oInput.getValue() === this.model.getProperty("/w07/persEmailAddress")
                        ? "None" : "Error";
                oInput.setValueState(state);

                if (state === "Error") {
                    MessageToast.show("E-Mail must be corresponding2.");
                }

            }

            this._validateInput2("idTxtW07Password");

            {
                const passWord = this.model.getProperty("/w07/secretPassword");

                if (passWord && passWord !== "") {
                    const oControl = this.byId("idTxtW07PasswordConf"),
                        state = oControl.getValue() === passWord ? "Success" : "Error";

                    oControl.setValueState(state);

                    if (state === "Error") {
                        MessageToast.show("Passwords are not corresponding.");
                    }
                }
            }


            // Wizard W06 section
            {this._validateInput2("idTxtW06BillingEmailAddress");

                const oInput = this.byId("idTxtW06BillingEmailAddressConf");

                const state =
                    oInput.getValue() === this.model.getProperty("/w06/billingEmailAddress") ? "None" : "Error";
                oInput.setValueState(state);

                if (state === "Error") {
                    MessageToast.show("E-Mail must be corresponding2.");
                }

            }

            this._validateInput2("idTxtW05PostalCode");
            this._validateInput2("idTxtW05City");
            this._validateInput2("idTxtW05StreetAddr");
            this._validateInput2("idTxtW05Delivery");

            this._validateInput2("idTxtW04PostalCode");
            this._validateInput2("idTxtW04City");
            this._validateInput2("idTxtW04StreetAddr");
            this._validateInput2("idTxtW04Delivery");

            this._validateInput2("idTxtW03CompanyName");
            this._validateInput2("idTxtW03CoC");
            this._validateInput2("idTxtW03Subsidiary");
            this._validateInput2("idTxtW03VAT");

            this._validateInput2("idTxtW02PersN");
            this._validateInput2("idTxtW02Dep");
            this._validateInput2("idTxtW02JobT");
            this._validateInput2("idTxtW02NickN");
        */
                // Create new validator instance

            const btnText = this._wizard.getCurrentStep().substr(-6),
                validator = new Validator();

            if (validator.validate(this.byId(btnText))) {
                this._wizard.nextStep();
            }
        },

        _checkW07valid() {
            const error = !this.byId("idTxtW07PersEmailAddress").getValue().length ||
                !this.byId("idTxtW07PersEmailAddressConf").getValue().length ||
                !this.byId("idTxtW07Password").getValue().length ||
                !this.byId("idTxtW07PasswordConf").getValue().length ||
                this.byId("idTxtW07PersEmailAddress").getValueState() === 'Error' ||
                this.byId("idTxtW07PersEmailAddressConf").getValueState() === 'Error' ||
                this.byId("idTxtW07persTelephone").getValueState() === 'Error' ||
                this.byId("idTxtW07Password").getValueState() === 'Error' ||
                this.byId("idTxtW07PasswordConf").getValueState() === 'Error';


            this.model.setProperty("/w07/valid", !error);
        },
        onTxtW07eCom(oEvent) {
            this._validateInput(oEvent);

            this._checkW07valid()
        },
        onTxtW07PersEmailAddressChange(oEvent) {
            const oControl = this.byId("idTxtW07PersEmailAddressConf");

            this._validateInput(oEvent);
            oControl.setValue(null);
            oControl.setValueState("None");

            this._checkW07valid()
        },
        onTxtW07ConfEmailAddressChange(oEvent) {
            if (oEvent.getSource().getValue()) {
                const state =
                    oEvent.getSource().getValue() === this.model.getProperty("/w07/persEmailAddress")
                        ? "Success" : "Error";
                oEvent.getSource().setValueState(state);

                if (state === "Error") {
                    MessageToast.show("E-Mail must be corresponding.");
                }
            }
            this._checkW07valid()
        },
        onTxtW07PasswordChange() {
            const oControl = this.byId("idTxtW07PasswordConf");

            oControl.setValue(null);
            oControl.setValueState("None");
            this._checkW07valid()
        },
        onTxtW07ConfPasswordChange() {
            const passWord = this.model.getProperty("/w07/secretPassword");

            if (passWord && passWord !== "") {
                const oControl = this.byId("idTxtW07PasswordConf"),
                    state = oControl.getValue() === passWord ? "Success" : "Error";

                oControl.setValueState(state);

                if (state === "Error") {
                    MessageToast.show("Passwords are not corresponding.");
                }
            }
            this._checkW07valid()
        },

        /*
         *
         */
        onClickPic() {this.getRouter().navTo("RouteMain");},
        onWizardCancel() {
            this._handleMessageBoxOpen("Are you sure you want to cancel your purchase?",
                "warning", this._navBackToBundleSelect());
        },
        onWizardCompleted() {this._oNavContainer.to(this._oWizardReviewPage);},
        onPayment() {this._oNavContainer.to(this._PaymentPage);},
        onSuccessPayment(oEvent) {
            console.log("Payment accepted.");
            this.procesRegistration(oEvent);
            this._oNavContainer.to(this._ThankYouPage);
        },
        onDeclinedPayment() {
            console.log("Payment declined.");
            this._oNavContainer.to(this._ThankYouPage);
        },
        handleWizardSubmit() {
            const oRouter = sap.ui.core.UIComponent.getRouterFor(this.getView);
            oRouter.navTo("detail");

/*            this._handleMessageBoxOpen("Are you sure you want to submit your report?",
                "confirm", this._navBackToBundleSelect());*/
        },
        onBtnWizardStep(oEvent) {
            this._wizard.nextStep();

            const btnText = this._wizard.getCurrentStep().substr(-6);

            this.model.setProperty("/nextBtnText", btnText);
            oEvent.getSource().setText(btnText);
        },
        _handleMessageBoxOpen: (sMessage, sMessageBoxType, fnNav) => {
            MessageBox[sMessageBoxType](sMessage, {
                actions: [MessageBox.Action.YES, MessageBox.Action.NO],
                onClose: oAction => {
                    if (oAction === MessageBox.Action.YES) {
                        this._wizard.discardProgress(this._wizard.getSteps()[0]);

                        fnNav
                    }
                }
            });
        },
        _navBackToBundleSelect() {this._navBackToStep(this.byId("Step01"));},
        _navBackToPersInfo() {this._navBackToStep(this.byId("Step_3"));},
        _navBackToInstName() {this._navBackToStep(this.byId("Step_4"));},
        _navBackToInstAddress() {this._navBackToStep(this.byId("Step_5"));},
        _navBackToInstVisitAddress() {this._navBackToStep(this.byId("Step_6"));},
        _navBackToEBusinessCom() {this._navBackToStep(this.byId("Step_7"));},
        _navBackToEPersCom() {this._navBackToStep(this.byId("Resume"));},
        _navBackToStep(step) {
            const fnAfterNavigate = () => {
                this._wizard.goToStep(step);
                this._oNavContainer.detachAfterNavigate(fnAfterNavigate);
            };

            this._oNavContainer.attachAfterNavigate(fnAfterNavigate);

            this._oNavContainer.to(this._oWizardContentPage);
        },
        _setDiscardableProperty({discardStep, message}) {
            if (this._wizard.getProgressStep() !== discardStep) {
                MessageBox.warning(message, {
                    actions: [MessageBox.Action.YES, MessageBox.Action.NO],
                    onClose: oAction => {
                        if (oAction === MessageBox.Action.YES) {
                            this._wizard.discardProgress(discardStep);

                            this.byId("Step_5").setNextStep(this.theView.byId(this.byId("chkDifferentVisitAddress").getState() ? "Step_7" : "Step_6")); // history[params.historyPath] = this.model.getProperty(params.modelPath);
                        } else {// this.model.setProperty(params.modelPath, history[params.historyPath]);
                        }
                    }
                });
            } else {
                this.byId("Step_5").setNextStep(this.theView.byId(this.byId("chkDifferentVisitAddress").getState() ? "Step_7" : "Step_6")); // history[params.historyPath] = this.model.getProperty(params.modelPath);
            }
        },
        _validateInput(oEvent) {
            const oInput = oEvent.getSource(),
                oInpType = oInput.getBinding("value").getType();
            let bValidationError = false;

            try {
                oInpType.validateValue(oInput.getValue());
            } catch (oException) {
                MessageToast.show(oException.message);
                bValidationError = true;
            }

            oInput.setValueState(bValidationError ? "Error" : "None");
            return bValidationError;
        },
        _validateInput2(inputId) {
            const oInput = this.byId(inputId),
                oInpType = oInput.getBinding("value").getType();
            let bValidationError = false;

            try {
                oInpType.validateValue(oInput.getValue());
            } catch (oException) {
                MessageToast.show(oException.message);
                bValidationError = true;
            }

            oInput.setValueState(bValidationError ? "Error" : "None");
            return bValidationError;
        }
    });
});