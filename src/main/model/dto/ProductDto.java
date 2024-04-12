package main.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.model.valueObjects.*;

/**
 * The ProductDto class is a data transfer object that carries data between processes.
 * It represents a product with a name, description, manufacturer, amount, price, and group name.
 */
@Data
@AllArgsConstructor

public class ProductDto {
    private ProductName name;
    private String description;
    private ManufacturerName manufacturer;
    private ProductAmount amount;
    private ProductPrice price;
    private GroupName groupName;

    /**
     * Constructs a new ProductDto with the given name, description, manufacturer, amount, price, and group name.
     *
     * @param name the name of the product
     * @param description the description of the product
     * @param manufacturer the manufacturer of the product
     * @param amount the amount of the product
     * @param price the price of the product
     * @param groupName the name of the group that the product belongs to
     */
    public ProductDto(String name, String description, String manufacturer, int amount, double price, String groupName){
        this.name = new ProductName(name);
        this.description = description;
        this.manufacturer = new ManufacturerName(manufacturer);
        this.amount = new ProductAmount(amount);
        this.price = new ProductPrice(price);
        this.groupName = new GroupName(groupName);
    }
}
