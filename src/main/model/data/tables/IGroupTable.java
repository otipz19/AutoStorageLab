package main.model.data.tables;

import main.model.data.records.GroupRecord;
import main.model.dto.GroupDto;
import main.model.valueObjects.GroupName;

import java.util.List;
import java.util.UUID;

public interface IGroupTable {
    void resetData();
    void bulkInsert(List<GroupRecord> groupRecords);
    List<GroupRecord> getAll();
    GroupRecord get(UUID id);
    GroupRecord get(GroupName name);
    GroupRecord create(GroupDto toCreate);
    void delete(UUID id);
    void delete(GroupName name);
    void update(UUID id, GroupDto toUpdate);
    void update(GroupName name, GroupDto toUpdate);
    double calculateTotalPriceByGroup(UUID groupId);
}
