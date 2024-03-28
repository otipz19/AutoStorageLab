package main.model.data.tables;

import main.model.data.DataContext;
import main.model.data.records.ProductRecord;
import main.model.dto.Mapper;
import main.model.dto.ProductDto;
import main.model.exceptions.crud.GroupNotFoundException;
import main.model.exceptions.crud.ProductNameAlreadyExists;
import main.model.exceptions.crud.ProductNotFoundException;
import main.model.valueObjects.ProductName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ProductTable implements IProductTable {
    private final Map<UUID, ProductRecord> primaryKey = new HashMap<>();
    private final Map<ProductName, ProductRecord> nameIndex = new HashMap<>();
    private final Map<UUID, List<ProductRecord>> groupIdIndex = new HashMap<>();

    @Override
    public ProductRecord get(UUID id) {
        throwIfDoesNotExist(id);
        return primaryKey.get(id);
    }

    @Override
    public ProductRecord get(ProductName name) {
        throwIfDoesNotExist(name);
        return nameIndex.get(name);
    }

    @Override
    public List<ProductRecord> getByGroupId(UUID groupId) {
        throwIfGroupDoesNotExist(groupId);
        //copy of internal list
        return groupIdIndex.get(groupId).stream().toList();
    }

    @Override
    public void create(ProductDto toCreate) {
        throwIfExists(toCreate.getName());
        UUID id = UUID.randomUUID();
        UUID groupId = DataContext.getInstance().getGroupTable().get(toCreate.getGroupName()).getId();
        ProductRecord record = Mapper.map(toCreate, id, groupId);
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void delete(ProductName name) {

    }

    @Override
    public void deleteByGroupId(UUID groupId) {

    }

    @Override
    public void update(UUID id, ProductDto toUpdate) {

    }

    @Override
    public void update(ProductName name, ProductDto toUpdate) {

    }

    private void throwIfDoesNotExist(UUID id) {
        if(!primaryKey.containsKey(id)){
            throw new ProductNotFoundException(id);
        }
    }

    private void throwIfDoesNotExist(ProductName name){
        if(!nameIndex.containsKey(name)){
            throw new ProductNotFoundException(name);
        }
    }

    private void throwIfGroupDoesNotExist(UUID groupId){
        if(!groupIdIndex.containsKey(groupId)){
            throw new GroupNotFoundException(groupId);
        }
    }

    private void throwIfExists(ProductName name){
        if(nameIndex.containsKey(name)){
            throw new ProductNameAlreadyExists(name);
        }
    }
}
