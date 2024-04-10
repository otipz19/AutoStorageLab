package main.ui.screens.productCreateScreen;

import main.ui.components.validatableField.NotEmptyValidatableFieldPanel;

/**
 * A validatable field that notifies a ProductCreatePanel when its validation state changes.
 */
public class ValidatableNotifierField extends NotEmptyValidatableFieldPanel {
    /**
     * The ProductCreatePanel to notify when the validation state changes.
     */
    private ProductCreatePanel panel;

    /**
     * Constructs a new ValidatableNotifierField with the specified validator and panel.
     * @param validator The validator to use for validation.
     * @param panel The panel to notify when the validation state changes.
     */
    public ValidatableNotifierField(Validator validator, ProductCreatePanel panel) {
        super(validator);
        this.panel = panel;
    }

    /**
     * Constructs a new ValidatableNotifierField with the specified validator, field value, and panel.
     * @param validator The validator to use for validation.
     * @param fieldValue The initial field value.
     * @param panel The panel to notify when the validation state changes.
     */
    public ValidatableNotifierField(Validator validator, String fieldValue, ProductCreatePanel panel) {
        super(validator, fieldValue);
        this.panel = panel;
    }

    /**
     * Called after validation. Notifies the panel that the input has changed.
     */
    @Override
    protected void afterValidation(){
        panel.onInputChange();
    }
}