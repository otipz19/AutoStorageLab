package main.model.dto;

import main.model.data.records.GroupRecord;
import main.model.data.records.ProductRecord;

import java.util.UUID;

public class Mapper {
    public static GroupDto map(GroupRecord record){
        return new GroupDto(record.getName(), record.getDescription());
    }

    public static GroupRecord map(GroupDto dto, UUID groupId){
        return new GroupRecord(groupId, dto.getName(), dto.getDescription());
    }

    public static ProductRecord map(ProductDto dto, UUID productId, UUID groupId){
        return new ProductRecord(
                productId,
                dto.getName(),
                dto.getDescription(),
                dto.getManufacturer(),
                dto.getAmount(),
                dto.getPrice(),
                groupId);
    }

    public static ProductDto map(ProductRecord record){
        return new ProductDto(
                record.getName(),
                record.getDescription(),
                record.getManufacturer(),
                record.getAmount(),
                record.getPrice());
    }
}
