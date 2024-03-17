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
}
