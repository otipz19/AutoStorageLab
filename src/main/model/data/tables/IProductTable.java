package main.model.data.tables;

import main.model.dto.GroupDto;
import main.model.dto.ProductDto;
import main.model.valueObjects.ProductName;

import java.util.List;
import java.util.UUID;

public interface IProductTable {
    ProductDto get(UUID id);
    ProductDto get(ProductName name);
    List<ProductDto> getByGroupId(UUID groupId);
    void create(ProductDto toCreate);
    void delete(UUID id);
    void delete(ProductName name);
    void deleteByGroupId(UUID groupId);
    void update(UUID id, ProductDto toUpdate);
    void update(ProductName name, ProductDto toUpdate);
}
