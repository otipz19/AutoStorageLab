package main.ui.components.editableField;

import main.Icons;
import main.ui.components.buttons.RoundedButton;

import java.awt.event.ActionEvent;

public abstract class EditableFieldButton extends RoundedButton {
    private final IEditableConnectedField connectedField;
    private String validFieldText;

    public EditableFieldButton(IEditableConnectedField connectedField, String label, int radius) {
        super(label, radius);
        this.connectedField = connectedField;
        setIcon(Icons.buildPenIcon(20, 20));
        addActionListener(this::onClick);
    }

    public void onInputChange(boolean isValid) {
        if (connectedField.isEditable()) {
            if (!isValid) {
                setIcon(Icons.buildCrossIcon(20, 20));
            } else {
                setIcon(Icons.buildCheckmarkIcon(20, 20));
            }
        }
    }

    private void onClick(ActionEvent e) {
        if (connectedField.isEditable()) {
            if (connectedField.isInputValid()) {
                afterClick();
            } else {
                connectedField.setText(validFieldText);
            }
            connectedField.setEditable(false);
            setIcon(Icons.buildPenIcon(20, 20));
        } else {
            validFieldText = connectedField.getText();
            connectedField.setEditable(true);
            setIcon(Icons.buildCheckmarkIcon(20, 20));
        }
    }

    protected abstract void afterClick();
}
