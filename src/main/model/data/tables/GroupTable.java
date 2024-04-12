package main.model.data.tables;

import main.model.data.DataContext;
import main.model.data.records.GroupRecord;
import main.model.data.records.ProductRecord;
import main.model.dto.GroupDto;
import main.model.dto.Mapper;
import main.model.exceptions.crud.GroupNameAlreadyExists;
import main.model.exceptions.crud.GroupNotFoundException;
import main.model.valueObjects.GroupName;

import java.util.*;

public class GroupTable implements IGroupTable {
    private final Map<UUID, GroupRecord> primaryKey = new HashMap<>();
    private final Map<GroupName, GroupRecord> nameIndex = new HashMap<>();

    @Override
    public void resetData(){
        primaryKey.clear();
        nameIndex.clear();
    }

    @Override
    public List<GroupRecord> getAll(){
        return primaryKey.values().stream().toList();
    }

    @Override
    public GroupRecord get(UUID id) {
        throwIfDoesNotExist(id);
        return primaryKey.get(id);
    }

    @Override
    public GroupRecord get(GroupName name) {
        throwIfDoesNotExist(name);
        return nameIndex.get(name);
    }

    @Override
    public GroupRecord create(GroupDto toCreate) {
        throwIfExists(toCreate.getName());
        UUID id = UUID.randomUUID();
        GroupRecord record = Mapper.map(toCreate, id);
        addRecordToIndexes(record, id);
        return record;
    }

    @Override
    public void delete(UUID id) {
        throwIfDoesNotExist(id);
        deleteAllRelatedProducts(id);
        deleteGroup(id);
    }

    @Override
    public void delete(GroupName name) {
        throwIfDoesNotExist(name);
        UUID id = nameIndex.get(name).getId();
        deleteAllRelatedProducts(id);
        deleteGroup(id);
    }

    private static void deleteAllRelatedProducts(UUID id) {
        DataContext.getInstance().getProductTable().deleteByGroupId(id);
    }

    private void deleteGroup(UUID id) {
        nameIndex.remove(primaryKey.get(id).getName());
        primaryKey.remove(id);
    }

    @Override
    public void update(UUID id, GroupDto toUpdate) {
        updateGroup(id, toUpdate);
    }

    @Override
    public void update(GroupName name, GroupDto toUpdate) {
        UUID id = get(name).getId();
        updateGroup(id, toUpdate);
    }

    private void updateGroup(UUID id, GroupDto toUpdate) {
        GroupRecord existing = get(id);
        GroupRecord newRecord = Mapper.map(toUpdate, id);
        if (isNameChanged(existing, newRecord)) {
            throwIfExists(toUpdate.getName());
            nameIndex.remove(existing.getName());
        }
        nameIndex.put(newRecord.getName(), newRecord);
        primaryKey.replace(id, newRecord);
    }

    private static boolean isNameChanged(GroupRecord existing, GroupRecord newRecord) {
        return !existing.getName().equals(newRecord.getName());
    }

    private void throwIfExists(GroupName name) {
        if (nameIndex.containsKey(name)) {
            throw new GroupNameAlreadyExists(name);
        }
    }

    private void throwIfDoesNotExist(UUID id) {
        if (!primaryKey.containsKey(id)) {
            throw new GroupNotFoundException(id);
        }
    }

    private void throwIfDoesNotExist(GroupName name) {
        if (!nameIndex.containsKey(name)) {
            throw new GroupNotFoundException(name);
        }
    }

    private void addRecordToIndexes(GroupRecord record, UUID id) {
        primaryKey.put(id, record);
        nameIndex.put(record.getName(), record);
    }

    public double calculateTotalPriceByGroup(UUID groupId) {
        List<ProductRecord> records = DataContext.getInstance().getProductTable().getByGroupId(groupId);
        return records.stream()
                .mapToDouble(record -> record.getPrice().getValue() * record.getAmount().getValue())
                .sum();
    }
}
