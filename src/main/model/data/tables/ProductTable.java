package main.model.data.tables;

import main.model.data.DataContext;
import main.model.data.records.GroupRecord;
import main.model.data.records.ProductRecord;
import main.model.dto.Mapper;
import main.model.dto.ProductDto;
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
    public void resetData(){
        primaryKey.clear();
        nameIndex.clear();
        groupIdIndex.clear();
    }

    @Override
    public List<ProductRecord> getAll(){
        return primaryKey.values().stream().toList();
    }

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


    /**
     * Throws GroupNotFoundException if group does not exist.
     * Returns empty list if group exists, but no related products do
     */
    @Override
    public List<ProductRecord> getByGroupId(UUID groupId) {
        throwIfGroupDoesNotExist(groupId);
        List<ProductRecord> related = groupIdIndex.get(groupId);
        if(related == null){
            return new ArrayList<>();
        }
        //copy of internal list
        return related.stream().toList();
    }

    @Override
    public ProductRecord create(ProductDto toCreate) {
        throwIfExists(toCreate.getName());
        UUID id = UUID.randomUUID();
        //here is group existence already validated
        UUID groupId = getGroupIdByGroupName(toCreate.getGroupName());
        ProductRecord record = Mapper.map(toCreate, id, groupId);
        addRecordToIndexes(record);
        return record;
    }

    private static UUID getGroupIdByGroupName(GroupName groupName) {
        return DataContext.getInstance().getGroupTable().get(groupName).getId();
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
        //records would be null if group exists, but no related products present
        if(records != null){
            for (ProductRecord record : records) {
                removeRecordFromPrimaryKey(record);
                removeRecordFromNameIndex(record);
            }
            groupIdIndex.remove(groupId);
        }
    }

    @Override
    public void update(UUID id, ProductDto toUpdate) {
        //existence already validated in get
        ProductRecord oldRecord = get(id);
        updateRecord(toUpdate, oldRecord);
    }

    @Override
    public void update(ProductName name, ProductDto toUpdate) {
        ProductRecord oldRecord = get(name);
        updateRecord(toUpdate, oldRecord);
    }

    private void updateRecord(ProductDto toUpdate, ProductRecord oldRecord) {
        GroupRecord oldGroup = DataContext.getInstance().getGroupTable().get(oldRecord.getGroupId());
        UUID groupId = oldGroup.getId();
        if(!toUpdate.getGroupName().equals(oldGroup.getName())){
            groupId = DataContext.getInstance().getGroupTable().get(toUpdate.getGroupName()).getId();
        }
        ProductRecord recordToUpdate = Mapper.map(toUpdate, oldRecord.getId(), groupId);
        addRecordToPrimaryKey(recordToUpdate);
        if (!recordToUpdate.getName().equals(oldRecord.getName())) {
            throwIfExists(recordToUpdate.getName());
            removeRecordFromNameIndex(oldRecord);
            addRecordToNameIndex(recordToUpdate);
        }
        if (!oldGroup.getId().equals(groupId)){
            removeRecordFromGroupIdIndex(oldRecord);
            addRecordToGroupIdIndex(recordToUpdate);
        }
    }

    private void addRecordToIndexes(ProductRecord record) {
        addRecordToPrimaryKey(record);
        addRecordToNameIndex(record);
        addRecordToGroupIdIndex(record);
    }

    private void addRecordToPrimaryKey(ProductRecord record) {
        primaryKey.put(record.getId(), record);
    }

    private void addRecordToNameIndex(ProductRecord record){
        nameIndex.put(record.getName(), record);
    }

    private void addRecordToGroupIdIndex(ProductRecord record) {
        var groupIndex = groupIdIndex.get(record.getGroupId());
        if (groupIndex == null) {
            groupIndex = new ArrayList<ProductRecord>();
            groupIdIndex.put(record.getGroupId(), groupIndex);
        }
        groupIndex.add(record);
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
        groupIdIndex.get(record.getGroupId()).remove(record);
    }

    private void throwIfDoesNotExist(UUID id) {
        if (!primaryKey.containsKey(id)) {
            throw new ProductNotFoundException(id);
        }
    }

    private void throwIfDoesNotExist(ProductName name) {
        if (!nameIndex.containsKey(name)) {
            throw new ProductNotFoundException(name);
        }
    }

    private void throwIfGroupDoesNotExist(UUID groupId) {
        DataContext.getInstance().getGroupTable().get(groupId);
    }

    private void throwIfExists(ProductName name) {
        if (nameIndex.containsKey(name)) {
            throw new ProductNameAlreadyExists(name);
        }
    }
}
