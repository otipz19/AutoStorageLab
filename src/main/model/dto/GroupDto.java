package main.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.model.valueObjects.GroupName;

/**
 * The GroupDto class is a data transfer object that carries data between processes.
 * It represents a group with a name and a description.
 */
@Data
@AllArgsConstructor
public class GroupDto {
    private GroupName name;
    private String description;

    /**
     * Constructs a new GroupDto with the given name and description.
     *
     * @param name the name of the group
     * @param description the description of the group
     */
    public GroupDto(String name, String description){
        this.name = new GroupName(name);
        this.description = description;
    }
}
