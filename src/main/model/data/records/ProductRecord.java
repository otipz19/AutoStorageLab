package main.model.data.records;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.model.valueObjects.ManufacturerName;
import main.model.valueObjects.ProductAmount;
import main.model.valueObjects.ProductName;
import main.model.valueObjects.ProductPrice;

import java.util.UUID;

@Data
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
}
