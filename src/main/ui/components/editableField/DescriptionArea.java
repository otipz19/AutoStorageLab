package main.ui.components.editableField;

import javax.swing.*;

public class DescriptionArea extends JTextArea implements IEditableConnectedField {
    public DescriptionArea(){
        setEditable(false);
    }

    @Override
    public boolean isInputValid() {
        return true;
    }

    @Override
    public void setConnectedBtn(EditableFieldButton connectedBtn) {
        //ignored
    }
}
