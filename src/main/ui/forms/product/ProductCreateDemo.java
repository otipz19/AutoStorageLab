package main.ui.forms.product;

import main.model.data.DataContext;
import main.model.dto.GroupDto;

import javax.swing.*;

public class ProductCreateDemo extends JFrame {
    public ProductCreateDemo(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setTitle("Product Create Demo");
        setLocationRelativeTo(null);
        ProductCreateForm.createProduct();
    }

    public static void main(String[] args) {
        GroupDto[] groups = new GroupDto[]{
                new GroupDto("first", "desc"),
                new GroupDto("second", "desc"),
                new GroupDto("third", "desc"),
                new GroupDto("fourth", "desc"),
                new GroupDto("fifth", "desc"),
        };
        for(GroupDto group : groups){
            DataContext.getInstance().getGroupTable().create(group);
        }
        new ProductCreateDemo().setVisible(true);
    }
}
