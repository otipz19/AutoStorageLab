package main.model.data.tables;

import main.model.dto.GroupDto;
import main.model.valueObjects.GroupName;

import java.util.UUID;

public interface IGroupTable {
    GroupDto get(UUID id);
    GroupDto get(GroupName name);
    void create(GroupDto toCreate);
    void delete(UUID id);
    void delete(GroupName name);
    void update(UUID id, GroupDto toUpdate);
    void update(GroupName name, GroupDto toUpdate);
}
