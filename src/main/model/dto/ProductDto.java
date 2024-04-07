package main.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.model.valueObjects.*;

@Data
@AllArgsConstructor
public class ProductDto {
    private ProductName name;
    private String description;
    private ManufacturerName manufacturer;
    private ProductAmount amount;
    private ProductPrice price;
    private GroupName groupName;

    public ProductDto(String name, String description, String manufacturer, int amount, double price, String groupName){
        this.name = new ProductName(name);
        this.description = description;
        this.manufacturer = new ManufacturerName(manufacturer);
        this.amount = new ProductAmount(amount);
        this.price = new ProductPrice(price);
        this.groupName = new GroupName(groupName);
    }
}
