package main.ui.screens.productCreateScreen;

import main.ui.components.validatableField.NotEmptyValidatableFieldPanel;

public class ValidatableNotifierField extends NotEmptyValidatableFieldPanel {
    private ProductCreatePanel panel;

    public ValidatableNotifierField(Validator validator, ProductCreatePanel panel) {
        super(validator);
        this.panel = panel;
    }

    public ValidatableNotifierField(Validator validator, String fieldValue, ProductCreatePanel panel) {
        super(validator, fieldValue);
        this.panel = panel;
    }

    @Override
    protected void afterValidation(){
        panel.onInputChange();
    }
}
