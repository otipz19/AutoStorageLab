package main.ui.screens.productPanel.components;

import main.controllers.ProductsController;
import main.model.exceptions.DomainException;
import main.model.valueObjects.ProductAmount;
import main.ui.exceptions.InvalidFormInputException;
import main.ui.screens.productPanel.ProductUpdateScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AmountChangeListener implements ActionListener {
    private ProductUpdateScreen productUpdateScreen;
    private boolean shouldAdd;

    public AmountChangeListener(ProductUpdateScreen productUpdateScreen, boolean shouldAdd){
        this.productUpdateScreen = productUpdateScreen;
        this.shouldAdd = shouldAdd;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String input = JOptionPane.showInputDialog(
                    null,
                    "Input how many product to " + (shouldAdd ? "add" : "remove"),
                    (shouldAdd? "Add" : "Remove") + " product amount",
                    JOptionPane.QUESTION_MESSAGE
            );
            if(input == null){
                return;
            }
            int toAdd = Integer.parseInt(input);
            if (toAdd <= 0) {
                throw new InvalidFormInputException("Only positive values are allowed");
            }
            int oldAmount = productUpdateScreen.getProductDto().getAmount().getValue();
            ProductAmount newAmount = new ProductAmount(shouldAdd ? oldAmount + toAdd : oldAmount - toAdd);
            productUpdateScreen.getAmount().setText(newAmount.toString());
            ProductsController.updateProduct(productUpdateScreen);
            productUpdateScreen.recalculateTotalPrice();
        } catch (NumberFormatException ex) {
            showInputErrorMsg("Only numbers are allowed");
        } catch (InvalidFormInputException | DomainException ex) {
            showInputErrorMsg(ex.getMessage());
        }
    }

    private void showInputErrorMsg(String msg){
        JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
