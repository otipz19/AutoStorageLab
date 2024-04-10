package main.ui.components.validatableField;

public class NotEmptyValidatableFieldPanel extends ValidatableFieldPanel {

    public NotEmptyValidatableFieldPanel(Validator validator) {
        super(validator);
    }

    public NotEmptyValidatableFieldPanel(Validator validator, String fieldValue) {
        super(validator, fieldValue);
    }

    @Override
    protected void setValidationState(){
        super.setValidationState();
        if(field.getText().isBlank()){
            isValid = false;
            errorLabel.setText("Required field!");
        }
        errorLabel.setVisible(!isValid);
    }
}
