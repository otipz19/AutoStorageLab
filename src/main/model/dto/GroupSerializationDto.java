package main.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
public class GroupSerializationDto {
    private UUID id;
    private String name;
    private String description;
}
