package main.model.data.records;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.model.valueObjects.ManufacturerName;
import main.model.valueObjects.ProductAmount;
import main.model.valueObjects.ProductName;
import main.model.valueObjects.ProductPrice;

import java.io.Serializable;
import java.util.UUID;

/**
 * The ProductRecord class represents a record of a product in the application.
 * It implements the Serializable interface to allow its instances to be written to an output stream.
 */
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

    /**
     * Checks if this ProductRecord is equal to another object.
     * Two ProductRecords are equal if their IDs are equal.
     *
     * @param other the object to compare with this ProductRecord
     * @return true if the other object is a ProductRecord and its ID is equal to this ProductRecord's ID, false otherwise
     */
    @Override
    public boolean equals(Object other){
        if(other instanceof ProductRecord otherRecord){
            return this.id.equals(otherRecord.id);
        }
        return false;
    }


    /**
     * Returns a hash code value for this ProductRecord.
     * The hash code is based on the ID of the ProductRecord.
     *
     * @return a hash code value for this ProductRecord
     */
    @Override
    public int hashCode(){
        return id.hashCode();
    }
}
