package main.model.data.tables;

import main.model.data.records.GroupRecord;
import main.model.dto.GroupDto;
import main.model.valueObjects.GroupName;

import java.util.UUID;

public interface IGroupTable {
    GroupRecord get(UUID id);
    GroupRecord get(GroupName name);
    void create(GroupDto toCreate);
    void delete(UUID id);
    void delete(GroupName name);
    void update(UUID id, GroupDto toUpdate);
    void update(GroupName name, GroupDto toUpdate);
}
