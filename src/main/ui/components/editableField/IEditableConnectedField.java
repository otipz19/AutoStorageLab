package main.ui.components.editableField;

public interface IEditableConnectedField {
    boolean isInputValid();
    boolean isEditable();
    void setEditable(boolean isEditable);
    String getText();
    void setText(String text);
    void setConnectedBtn(EditableFieldButton connectedBtn);
}
