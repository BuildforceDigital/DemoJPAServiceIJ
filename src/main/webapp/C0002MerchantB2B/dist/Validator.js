sap.ui.define([
    "sap/ui/core/message/Message",
    "sap/ui/core/MessageType",
    "sap/ui/core/ValueState"
], (Message, MessageType, ValueState) => {
    /**
     * @name        sap.ui.demo.walkthrough.dist.Validator
     *
     * @class
     * @classdesc   Validator class.<br/>
     *
     * @version     October 2015
     * @author      Robin van het Hof
     */
    class Validator {
        constructor() {
            this._isValid = true;
            this._isValidationPerformed = false;
        }

        /**
         * Recursively validates the given oControl and any aggregations (i.e. child controls) it may have
         * @memberof nl.qualiture.plunk.demo.dist.Validator
         *
         * @param {(sap.ui.core.Control|sap.ui.layout.form.FormContainer|sap.ui.layout.form.FormElement)} oControl - The control or element to be validated.
         * @return {boolean} whether the oControl is valid or not.
         */
        validate(oControl) {
            this._isValid = true;
            sap.ui.getCore().getMessageManager().removeAllMessages();
            this._validate(oControl);
            return this._isValidationPerformed && this._isValid;
        }

        /**
         * Recursively validates the given oControl and any aggregations (i.e. child controls) it may have
         * @memberof nl.qualiture.plunk.demo.dist.Validator
         *
         * @param {(sap.ui.core.Control|sap.ui.layout.form.FormContainer|sap.ui.layout.form.FormElement)} oControl - The control or element to be validated.
         */
        _validate(oControl) {
            const aPossibleAggregations = ["items", "content", "form", "formContainers", "formElements", "fields", "sections", "subSections", "_grid", "cells", "_page"];
            // yes, I want to validate Select and Text controls too
            const aValidateProperties = ["value", "selectedKey", "text"];

            let aControlAggregation = null, isValidatedControl = false, editable;

            function _addMessage(oControlBinding, message) {
                const oContext = oControlBinding.getContext();
                sap.ui.getCore().getMessageManager().addMessages(
                    new Message({ message,
                        type: MessageType.Error,
                        target: (oContext ? `${oContext.getPath()}/` : "") + oControlBinding.getPath(),
                        processor: oControlBinding.getModel()
                    })
                );
            }

            // only validate controls and elements which have a 'visible' property
            // and are visible controls (invisible controls make no sense checking)
            if ((oControl instanceof sap.ui.core.Control
                || oControl instanceof sap.ui.layout.form.FormContainer
                || oControl instanceof sap.ui.layout.form.FormElement
            ) && oControl.getVisible()) {

                // check control for any properties worth validating
                for (const validatePropertiesCV of aValidateProperties) {
                    const oControlBinding = oControl.getBinding(validatePropertiesCV);
                    if (oControlBinding
                        // check if a data type exists (which may have validation constraints)
                        && oControlBinding.getType()
                    ) {
                        try {
                            editable = oControl.getProperty("editable");
                        } catch (ex) {
                            editable = true;
                        }

                        if (editable) {
                            try { // try validating the bound value
                                const oExternalValue = oControl.getProperty(validatePropertiesCV), oInternalValue =
                                        oControlBinding.getType().parseValue(oExternalValue, oControlBinding.sInternalType);
                                oControlBinding.getType().validateValue(oInternalValue);
                            } catch (ex) { // catch any validation errors
                                this._isValid = false;
                                oControl.setValueState(ValueState.Error);

                                _addMessage(oControlBinding, ex.message)
                            }

                            isValidatedControl = true;
                        }
                    } else if (oControl.getRequired && oControl.getRequired())
                        try {
                            const oExternalValue = oControl.getProperty(validatePropertiesCV);

                            this._isValid = (oExternalValue && oExternalValue !== "");
                            if (this._isValid) {
                                this._isValid = oControl.getAggregation("picker") || oControl.getProperty("selectedKey").length !== 0;
                                if (this._isValid) {
                                    oControl.setValueState(ValueState.None);
                                } else {    //TODO: i18n this
                                    oControl.setValueState(ValueState.Error, "Please choose an entry!");
                                }
                            } else {
                                const oMessage = "Please fill this mandatory field!";

                                oControl.setValueState(ValueState.Error, oMessage);

                                _addMessage(oControlBinding, oMessage);
                            }
                        } catch (ex) {
                            // Validation failed
                        }
                }

                // if the control could not be validated, it may have aggregations
                if (!isValidatedControl) {
                    for (const possibleAggregationsCV of aPossibleAggregations) {
                        aControlAggregation = oControl.getAggregation(possibleAggregationsCV);

                        if (aControlAggregation) {
                            // generally, aggregations are of type Array
                            if (aControlAggregation instanceof Array)
                                for (const controlAggregationCV of aControlAggregation) this._validate(controlAggregationCV);
                            // …however, with sap.ui.layout.form.Form, it is a single object *sigh*
                            else {
                                this._validate(aControlAggregation);
                            }
                        }
                    }
                }
            }
            this._isValidationPerformed = true;
        }
    }

    return Validator;
});