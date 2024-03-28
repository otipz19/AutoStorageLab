package main.model.data.records;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.model.valueObjects.ManufacturerName;
import main.model.valueObjects.ProductAmount;
import main.model.valueObjects.ProductName;
import main.model.valueObjects.ProductPrice;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ProductRecord {
    //Primary key
    private UUID id;

    private ProductName name;
    private String description;
    private ManufacturerName manufacturer;
    private ProductAmount amount;
    private ProductPrice price;

    //Foreign key to GroupTable
    private UUID groupId;

    public ProductRecord copy(){
        return new ProductRecord(id, name, description, manufacturer, amount, price, groupId);
    }
}
