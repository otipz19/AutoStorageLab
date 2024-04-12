package main.model.data.records;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.model.valueObjects.GroupName;

import java.io.Serializable;
import java.util.UUID;

/**
 * The GroupRecord class represents a record of a group in the application.
 * It implements the Serializable interface to allow its instances to be written to an output stream.
 */
@Getter
@AllArgsConstructor
public class GroupRecord implements Serializable {
    //Primary key
    private UUID id;

    private GroupName name;
    private String description;

    /**
     * Checks if this GroupRecord is equal to another object.
     * Two GroupRecords are equal if their IDs are equal.
     *
     * @param other the object to compare with this GroupRecord
     * @return true if the other object is a GroupRecord and its ID is equal to this GroupRecord's ID, false otherwise
     */
    @Override
    public boolean equals(Object other){
        if(other instanceof GroupRecord otherRecord){
            return this.id.equals(otherRecord.id);
        }
        return false;
    }

    /**
     * Returns a hash code value for this GroupRecord.
     * The hash code is based on the ID of the GroupRecord.
     *
     * @return a hash code value for this GroupRecord
     */
    @Override
    public int hashCode(){
        return id.hashCode();
    }
}
