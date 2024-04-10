package main.ui.components.validatableField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class ValidatableFieldPanel extends JPanel {
    public interface Validator {
        boolean isValid(String str);
    }

    private boolean firstRender = true;

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

    public boolean isValid() {
        return isValid;
    }

    public String getText() {
        return field.getText();
    }

    public void setText(String text) {
        field.setText(text);
    }

    public void setEditable(boolean isEditable) {
        field.setEditable(isEditable);
    }

    public boolean isEditable() {
        return field.isEditable();
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
        add(errorLabel, BorderLayout.SOUTH);
    }

    private void setupValidation() {
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //For the God's sake, I don't know how, but it works!
                if (firstRender) {
                    firstRender = false;
                } else {
                    setValidationState();
                }
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

    public void setValidationState() {
        isValid = validator.isValid(field.getText());
        if (isValid) {
            errorLabel.setText("");
        } else {
            errorLabel.setText("Invalid input!");
        }
    }
}
