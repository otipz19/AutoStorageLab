package main.ui.components.editableField;

import main.ui.components.validatableField.NotEmptyValidatableFieldPanel;
import main.ui.screens.groupScreen.components.EditGroupButton;

public class EditableValidatableField extends NotEmptyValidatableFieldPanel implements IEditableConnectedField{
    private EditableFieldButton connectedBtn;

    public EditableValidatableField(Validator validator) {
        super(validator);
    }

    public EditableValidatableField(Validator validator, String fieldValue) {
        super(validator, fieldValue);
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
