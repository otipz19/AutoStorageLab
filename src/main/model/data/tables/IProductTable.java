package main.model.data.tables;

import main.model.data.records.ProductRecord;
import main.model.dto.GroupDto;
import main.model.dto.ProductDto;
import main.model.valueObjects.ProductName;

import java.util.List;
import java.util.UUID;

public interface IProductTable {
    List<ProductRecord> getAll();
    ProductRecord get(UUID id);
    ProductRecord get(ProductName name);
    List<ProductRecord> getByGroupId(UUID groupId);
    UUID create(ProductDto toCreate);
    void delete(UUID id);
    void delete(ProductName name);
    void deleteByGroupId(UUID groupId);
    void update(UUID id, ProductDto toUpdate);
    void update(ProductName name, ProductDto toUpdate);
}
