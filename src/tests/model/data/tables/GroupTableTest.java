package tests.model.data.tables;

import main.model.data.DataContext;
import main.model.data.records.GroupRecord;
import main.model.dto.GroupDto;
import main.model.exceptions.crud.GroupNameAlreadyExists;
import main.model.exceptions.crud.GroupNotFoundException;
import main.model.valueObjects.GroupName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GroupTableTest {
    @BeforeEach
    public void setUp(){
        DataContext.resetData();
    }

    @Test
    public void create_givenNewGroup_putsNewRecordToAllIndexes() {
        GroupDto toCreate = getNewGroup();
        create(toCreate);
        GroupRecord createdGroup = assertDoesNotThrow(() -> get(toCreate.getName()));
        assertDoesNotThrow(() -> get(createdGroup.getId()));
    }

    @Test
    public void create_givenGroupWithPresentName_throwsException() {
        GroupDto first = getNewGroup();
        create(first);
        GroupDto second = getNewGroup();
        assertThrows(GroupNameAlreadyExists.class, () -> create(second));
    }

    @Test
    public void delete_byId_givenPresentGroup_deletesFromAllIndexes(){
        GroupRecord group = createAndGet(getNewGroup());
        testDelete(() -> delete(group.getId()), group.getId(), group.getName());
    }

    @Test
    public void delete_byName_givenPresentGroup_deletesFromAllIndexes(){
        GroupRecord group = createAndGet(getNewGroup());
        testDelete(() -> delete(group.getName()), group.getId(), group.getName());
    }

    private void testDelete(Runnable deleteAction, UUID id, GroupName groupName){
        assertDoesNotThrow(deleteAction::run);
        assertThrows(GroupNotFoundException.class, () -> get(id));
        assertThrows(GroupNotFoundException.class, () -> get(groupName));
    }

    private static GroupDto getNewGroup() {
        return new GroupDto("newgroup", "desc");
    }


    private static GroupRecord createAndGet(GroupDto toCreate){
        create(toCreate);
        return get(toCreate.getName());
    }

    private static GroupRecord get(UUID id){
        return DataContext.getInstance().getGroupTable().get(id);
    }

    private static GroupRecord get(GroupName name){
        return DataContext.getInstance().getGroupTable().get(name);
    }

    private static void create(GroupDto toCreate){
        DataContext.getInstance().getGroupTable().create(toCreate);
    }

    private static void delete(UUID id){
        DataContext.getInstance().getGroupTable().delete(id);
    }

    private static void delete(GroupName name){
        DataContext.getInstance().getGroupTable().delete(name);
    }
}