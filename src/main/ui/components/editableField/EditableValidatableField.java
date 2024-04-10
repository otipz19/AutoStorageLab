package main.ui.components.editableField;

import main.ui.components.validatableField.NotEmptyValidatableFieldPanel;

public class EditableValidatableField extends NotEmptyValidatableFieldPanel implements IEditableConnectedField{
    private EditableFieldButton connectedBtn;

    public EditableValidatableField(Validator validator) {
        super(validator);
        field.setEditable(false);
    }

    public EditableValidatableField(Validator validator, String fieldValue) {
        super(validator, fieldValue);
        field.setEditable(false);
    }

    @Override
    public void setConnectedBtn(EditableFieldButton connectedBtn) {
        this.connectedBtn = connectedBtn;
    }

    @Override
    protected void afterValidation() {
        connectedBtn.onInputChange(isValid);
    }
}
