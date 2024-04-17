package main.model.dto;

import main.model.data.DataContext;
import main.model.data.records.GroupRecord;
import main.model.data.records.ProductRecord;
import main.model.valueObjects.*;

import java.util.List;
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
    public static GroupDto map(GroupRecord record) {
        return new GroupDto(record.getName(), record.getDescription());
    }

    /**
     * Maps a GroupDto to a GroupRecord.
     *
     * @param dto     the GroupDto to map
     * @param groupId the UUID of the group
     * @return a GroupRecord that represents the given GroupDto
     */
    public static GroupRecord map(GroupDto dto, UUID groupId) {
        return new GroupRecord(groupId, dto.getName(), dto.getDescription());
    }

    /**
     * Maps a ProductDto to a ProductRecord.
     *
     * @param dto       the ProductDto to map
     * @param productId the UUID of the product
     * @param groupId   the UUID of the group that the product belongs to
     * @return a ProductRecord that represents the given ProductDto
     */
    public static ProductRecord map(ProductDto dto, UUID productId, UUID groupId) {
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
     * @param record    the ProductRecord to map
     * @param groupName the name of the group that the product belongs to
     * @return a ProductDto that represents the given ProductRecord
     */
    public static ProductDto map(ProductRecord record, GroupName groupName) {
        return new ProductDto(
                record.getName(),
                record.getDescription(),
                record.getManufacturer(),
                record.getAmount(),
                record.getPrice(),
                groupName);
    }

    public static List<ProductDto> map(List<ProductRecord> records) {
        return records.stream()
                .map(r -> Mapper.map(r, DataContext.getInstance().getGroupTable().get(r.getGroupId()).getName()))
                .toList();
    }

    public static GroupSerializationDto mapSerialization(GroupRecord record){
        var dto = new GroupSerializationDto();
        dto.setId(record.getId());
        dto.setName(record.getName().getValue());
        dto.setDescription(record.getDescription());
        return dto;
    }

    public static ProductSerializationDto mapSerialization(ProductRecord record){
        var dto = new ProductSerializationDto();
        dto.setId(record.getId());
        dto.setName(record.getName().getValue());
        dto.setDescription(record.getDescription());
        dto.setManufacturer(record.getManufacturer().getValue());
        dto.setAmount(record.getAmount().getValue());
        dto.setPrice(record.getPrice().getValue());
        dto.setGroupId(record.getGroupId());
        return dto;
    }

    public static GroupRecord mapSerialization(GroupSerializationDto dto){
        return new GroupRecord(dto.getId(), new GroupName(dto.getName()), dto.getDescription());
    }

    public static ProductRecord mapSerialization(ProductSerializationDto dto){
        return new ProductRecord(
                dto.getId(),
                new ProductName(dto.getName()),
                dto.getDescription(),
                new ManufacturerName(dto.getManufacturer()),
                new ProductAmount(dto.getAmount()),
                new ProductPrice(dto.getPrice()),
                dto.getGroupId()
        );
    }
}
