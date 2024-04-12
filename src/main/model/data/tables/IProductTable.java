package main.model.data.tables;

import main.model.data.records.ProductRecord;
import main.model.dto.GroupDto;
import main.model.dto.ProductDto;
import main.model.valueObjects.ProductName;

import java.util.List;
import java.util.UUID;

public interface IProductTable {
    void resetData();
    void bulkInsert(List<ProductRecord> productRecords);
    List<ProductRecord> getAll();
    ProductRecord get(UUID id);
    ProductRecord get(ProductName name);

    /**
     * Throws GroupNotFoundException if group does not exist.
     * Returns empty list if group exists, but no related products do
     */
    List<ProductRecord> getByGroupId(UUID groupId);

    ProductRecord create(ProductDto toCreate);
    void delete(UUID id);
    void delete(ProductName name);
    void deleteByGroupId(UUID groupId);
    void update(UUID id, ProductDto toUpdate);
    void update(ProductName name, ProductDto toUpdate);
}
