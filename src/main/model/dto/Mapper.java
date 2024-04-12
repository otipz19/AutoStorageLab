package main.model.dto;

import main.model.data.records.GroupRecord;
import main.model.data.records.ProductRecord;
import main.model.valueObjects.GroupName;

import java.util.UUID;

/**
 * The Mapper class provides static methods for mapping between DTOs and record objects.
 */
public class Mapper {
    /**
     * Maps a GroupRecord to a GroupDto.
     *
     * @param record the GroupRecord to map
     * @return a GroupDto that represents the given GroupRecord
     */
    public static GroupDto map(GroupRecord record){
        return new GroupDto(record.getName(), record.getDescription());
    }

    /**
     * Maps a GroupDto to a GroupRecord.
     *
     * @param dto the GroupDto to map
     * @param groupId the UUID of the group
     * @return a GroupRecord that represents the given GroupDto
     */
    public static GroupRecord map(GroupDto dto, UUID groupId){
        return new GroupRecord(groupId, dto.getName(), dto.getDescription());
    }

    /**
     * Maps a ProductDto to a ProductRecord.
     *
     * @param dto the ProductDto to map
     * @param productId the UUID of the product
     * @param groupId the UUID of the group that the product belongs to
     * @return a ProductRecord that represents the given ProductDto
     */
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

    /**
     * Maps a ProductRecord to a ProductDto.
     *
     * @param record the ProductRecord to map
     * @param groupName the name of the group that the product belongs to
     * @return a ProductDto that represents the given ProductRecord
     */
    public static ProductDto map(ProductRecord record, GroupName groupName){
        return new ProductDto(
                record.getName(),
                record.getDescription(),
                record.getManufacturer(),
                record.getAmount(),
                record.getPrice(),
                groupName);
    }
}
