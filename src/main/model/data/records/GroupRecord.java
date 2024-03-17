package main.model.data.records;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.model.valueObjects.GroupName;

import java.util.UUID;

@Data
@AllArgsConstructor
public class GroupRecord {
    //Primary key
    private UUID id;

    private GroupName name;
    private String description;
}
