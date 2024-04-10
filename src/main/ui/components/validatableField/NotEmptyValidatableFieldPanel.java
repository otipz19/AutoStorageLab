package main.ui.components.validatableField;

public class NotEmptyValidatableFieldPanel extends ValidatableFieldPanel {

    public NotEmptyValidatableFieldPanel(Validator validator) {
        super(validator);
    }

    public NotEmptyValidatableFieldPanel(Validator validator, String fieldValue) {
        super(validator, fieldValue);
    }

    @Override
    public void setValidationState() {
        boolean isBlank = field.getText().isBlank();
        isValid = validator.isValid(field.getText()) && !isBlank;
        if (!isValid) {
            errorLabel.setText(isBlank ? "Required field!" : "Invalid input!");
        } else {
            errorLabel.setText("");
        }
    }
}
