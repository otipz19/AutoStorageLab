package main.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.model.valueObjects.GroupName;

@Data
@AllArgsConstructor
public class GroupDto {
    private GroupName name;
    private String description;
}
