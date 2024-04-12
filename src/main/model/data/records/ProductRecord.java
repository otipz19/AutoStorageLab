package main.model.data.records;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.model.valueObjects.ManufacturerName;
import main.model.valueObjects.ProductAmount;
import main.model.valueObjects.ProductName;
import main.model.valueObjects.ProductPrice;

import java.io.Serializable;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ProductRecord implements Serializable {
    //Primary key
    private UUID id;

    private ProductName name;
    private String description;
    private ManufacturerName manufacturer;
    private ProductAmount amount;
    private ProductPrice price;

    //Foreign key to GroupTable
    private UUID groupId;

    @Override
    public boolean equals(Object other){
        if(other instanceof ProductRecord otherRecord){
            return this.id.equals(otherRecord.id);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }
}
