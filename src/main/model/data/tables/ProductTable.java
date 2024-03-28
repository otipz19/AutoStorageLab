package main.model.data.tables;

import main.model.data.DataContext;
import main.model.data.records.ProductRecord;
import main.model.dto.Mapper;
import main.model.dto.ProductDto;
import main.model.exceptions.crud.GroupNotFoundException;
import main.model.exceptions.crud.ProductNameAlreadyExists;
import main.model.exceptions.crud.ProductNotFoundException;
import main.model.valueObjects.GroupName;
import main.model.valueObjects.ProductName;

import java.util.*;

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
        //here is group existence already validated
        UUID groupId = getGroupIdByGroupName(toCreate.getGroupName());
        ProductRecord record = Mapper.map(toCreate, id, groupId);
        addRecordToIndexes(id, record);
    }

    private static UUID getGroupIdByGroupName(GroupName groupName) {
        return DataContext.getInstance().getGroupTable().get(groupName).getId();
    }

    private void addRecordToIndexes(UUID id, ProductRecord record) {
        primaryKey.put(id, record);
        nameIndex.put(record.getName(), record);
        addRecordToGroupIdIndex(record);
    }

    private void addRecordToGroupIdIndex(ProductRecord record) {
        var groupIndex = groupIdIndex.get(record.getGroupId());
        if(groupIndex.isEmpty()){
            groupIndex = new ArrayList<ProductRecord>();
        }
        groupIndex.add(record);
        groupIdIndex.put(record.getGroupId(), groupIndex);
    }

    @Override
    public void delete(UUID id) {
        throwIfDoesNotExist(id);
        ProductRecord record = primaryKey.get(id);
        removeRecordFromIndexes(record);
    }

    @Override
    public void delete(ProductName name) {
        throwIfDoesNotExist(name);
        ProductRecord record = nameIndex.get(name);
        removeRecordFromIndexes(record);
    }

    @Override
    public void deleteByGroupId(UUID groupId) {
        throwIfGroupDoesNotExist(groupId);
        List<ProductRecord> records = groupIdIndex.get(groupId);
        for(ProductRecord record : records){
            removeRecordFromPrimaryKey(record);
            removeRecordFromNameIndex(record);
        }
        groupIdIndex.remove(groupId);
    }

    private void removeRecordFromIndexes(ProductRecord record) {
        removeRecordFromPrimaryKey(record);
        removeRecordFromNameIndex(record);
        removeRecordFromGroupIdIndex(record);
    }

    private void removeRecordFromNameIndex(ProductRecord record) {
        nameIndex.remove(record.getName());
    }

    private void removeRecordFromPrimaryKey(ProductRecord record) {
        primaryKey.remove(record.getId());
    }

    private void removeRecordFromGroupIdIndex(ProductRecord record) {
        var groupIndex = groupIdIndex.get(record.getGroupId());
        groupIndex.remove(record);
        groupIdIndex.put(record.getGroupId(), groupIndex);
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
