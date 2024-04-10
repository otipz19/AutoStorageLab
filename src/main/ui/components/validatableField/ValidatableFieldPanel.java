package main.ui.components.validatableField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class ValidatableFieldPanel extends JPanel {
    public interface Validator {
        boolean isValid(String str);
    }

    protected final Validator validator;
    protected JTextField field;
    protected JLabel errorLabel;

    protected boolean isValid;

    public ValidatableFieldPanel(Validator validator) {
        this(validator, "");
    }

    public ValidatableFieldPanel(Validator validator, String fieldValue) {
        this.validator = validator;
        createLayout(fieldValue);
        setupValidation();
        //setValidationState();
    }

    public boolean isValidText() {
        return isValid;
    }

    public String getText() {
        return field.getText();
    }

    public void setText(String text) {
        field.setText(text);
    }

    public boolean isEditable() {
        return field.isEditable();
    }

    public void setEditable(boolean isEditable) {
        field.setEditable(isEditable);
    }

    private void createLayout(String fieldValue) {
        setLayout(new BorderLayout());
        addField(fieldValue);
        addErrorLabel();
    }

    private void addField(String fieldValue) {
        field = new JTextField(fieldValue);
        add(field, BorderLayout.CENTER);
    }

    private void addErrorLabel() {
        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        add(errorLabel, BorderLayout.SOUTH);
    }

    private void setupValidation() {
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setValidationState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setValidationState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                setValidationState();
            }
        });
    }

    protected void setValidationState() {
        this.isValid = validator.isValid(field.getText());
        errorLabel.setText("Invalid input!");
        errorLabel.setVisible(!isValid);
    }
}
