package main.ui.components;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class ValidatableFieldPanel extends JPanel {
    public interface Validator {
        boolean isValid(String str);
    }

    private final Validator validator;
    private JTextField field;
    private JLabel errorLabel;

    private boolean isValid;

    public ValidatableFieldPanel(Validator validator) {
        this(validator, "");
    }

    public ValidatableFieldPanel(Validator validator, String fieldValue){
        this.validator = validator;
        createLayout(fieldValue);
        setupValidation();
        setValidationState();
    }

    public boolean isValid(){
        return isValid;
    }

    public String getText(){
        return field.getText();
    }

    private void createLayout(String fieldValue) {
        setLayout(new GridLayout(2, 1));
        addField(fieldValue);
        addErrorLabel();
    }

    private void addField(String fieldValue) {
        field = new JTextField(fieldValue);
        add(field);
    }

    private void addErrorLabel() {
        errorLabel = new JLabel("Invalid input!");
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        add(errorLabel);
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

    private void setValidationState(){
        this.isValid = validator.isValid(field.getText());
        errorLabel.setVisible(!isValid);
    }
}
