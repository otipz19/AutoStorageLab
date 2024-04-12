package main.model.data.records;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.model.valueObjects.GroupName;

import java.io.Serializable;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class GroupRecord implements Serializable {
    //Primary key
    private UUID id;

    private GroupName name;
    private String description;

    @Override
    public boolean equals(Object other){
        if(other instanceof GroupRecord otherRecord){
            return this.id.equals(otherRecord.id);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }
}
