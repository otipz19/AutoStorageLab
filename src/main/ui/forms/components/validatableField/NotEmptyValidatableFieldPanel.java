package main.ui.forms.components.validatableField;

public class NotEmptyValidatableFieldPanel extends ValidatableFieldPanel {
    public NotEmptyValidatableFieldPanel(Validator validator) {
        super(adjustValidator(validator));
    }

    public NotEmptyValidatableFieldPanel(Validator validator, String fieldValue) {
        super(adjustValidator(validator), fieldValue);
    }

    private static Validator adjustValidator(Validator validator){
        return str -> !str.isBlank() && validator.isValid(str);
    }
}
