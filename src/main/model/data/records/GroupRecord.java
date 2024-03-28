package main.model.data.records;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.model.valueObjects.GroupName;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class GroupRecord {
    //Primary key
    private UUID id;

    private GroupName name;
    private String description;

    public GroupRecord copy(){
        return new GroupRecord(id, name, description);
    }
}
