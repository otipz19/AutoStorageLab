package main.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductSerializationDto {
    private UUID id;
    private String name;
    private String description;
    private String manufacturer;
    private int amount;
    private double price;
    private UUID groupId;
}
