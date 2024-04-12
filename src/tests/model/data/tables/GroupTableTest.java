package tests.model.data.tables;

import main.model.data.DataContext;
import main.model.data.records.GroupRecord;
import main.model.dto.GroupDto;
import main.model.exceptions.crud.GroupNameAlreadyExists;
import main.model.exceptions.crud.GroupNotFoundException;
import main.model.valueObjects.GroupName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The GroupTableTest class provides unit tests for the GroupTable class.
 */
class GroupTableTest {
    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    public void setUp() {
        DataContext.resetData();
    }

    /**
     * Tests the create method with a new group.
     */
    @Test
    public void create_givenNewGroup_putsNewRecordToAllIndexes() {
        GroupDto toCreate = getNewGroup();
        create(toCreate);
        GroupRecord createdGroup = assertDoesNotThrow(() -> get(toCreate.getName()));
        assertDoesNotThrow(() -> get(createdGroup.getId()));
    }

    /**
     * Tests the create method with a group that already exists.
     */
    @Test
    public void create_givenGroupWithPresentName_throwsException() {
        GroupDto first = getNewGroup();
        create(first);
        GroupDto second = getNewGroup();
        assertThrows(GroupNameAlreadyExists.class, () -> create(second));
    }

    /**
     * Tests the delete method by ID with a present group.
     */
    @Test
    public void delete_byId_givenPresentGroup_deletesFromAllIndexes() {
        GroupRecord group = createAndGet(getNewGroup());
        testDelete(() -> delete(group.getId()), group.getId(), group.getName());
    }

    /**
     * Tests the delete method by name with a present group.
     */
    @Test
    public void delete_byName_givenPresentGroup_deletesFromAllIndexes() {
        GroupRecord group = createAndGet(getNewGroup());
        testDelete(() -> delete(group.getName()), group.getId(), group.getName());
    }

    /**
     * Tests the update method by ID without renaming.
     */
    private void testDelete(Runnable deleteAction, UUID id, GroupName groupName) {
        assertDoesNotThrow(deleteAction::run);
        assertThrows(GroupNotFoundException.class, () -> get(id));
        assertThrows(GroupNotFoundException.class, () -> get(groupName));
    }

    /**
     * Tests the update method by name without renaming.
     */
    @Test
    public void update_byId_withoutRename_updatesDescription() {
        GroupRecord oldRecord = createAndGet(getNewGroup());
        String updatedDescription = "new description";
        GroupDto toUpdate = new GroupDto(oldRecord.getName(), updatedDescription);
        testUpdateWithoutRename(() -> update(oldRecord.getId(), toUpdate), oldRecord, updatedDescription);
    }

    /**
     * Tests the update method by ID with renaming.
     */
    @Test
    public void update_byName_withoutRename_updatesDescription() {
        GroupRecord oldRecord = createAndGet(getNewGroup());
        String updatedDescription = "new description";
        GroupDto toUpdate = new GroupDto(oldRecord.getName(), updatedDescription);
        testUpdateWithoutRename(() -> update(oldRecord.getName(), toUpdate), oldRecord, updatedDescription);
    }

    /**
     * Tests the update method by name with renaming.
     */
    private static void testUpdateWithoutRename(Runnable updateAction,
                                                GroupRecord oldRecord,
                                                String expectedDescription) {
        assertDoesNotThrow(updateAction::run);
        GroupRecord updatedRecord = get(oldRecord.getId());
        assertEquals(oldRecord.getName(), updatedRecord.getName());
        assertEquals(expectedDescription, updatedRecord.getDescription());
    }

    /**
     * Tests the update method by ID with renaming.
     */
    @Test
    public void update_byId_withRename_changesName() {
        GroupRecord oldRecord = createAndGet(getNewGroup());
        String updatedName = "newupdatedname";
        String updatedDescription = "newcooldesc";
        GroupDto toUpdate = new GroupDto(updatedName, updatedDescription);
        testUpdateWithRename(() -> update(oldRecord.getId(), toUpdate), oldRecord, updatedName, updatedDescription);
    }

    /**
     * Tests the update method by name with renaming.
     */
    @Test
    public void update_byName_withRename_changesName() {
        GroupRecord oldRecord = createAndGet(getNewGroup());
        String updatedName = "newupdatedname";
        String updatedDescription = "newcooldesc";
        GroupDto toUpdate = new GroupDto(updatedName, updatedDescription);
        testUpdateWithRename(() -> update(oldRecord.getName(), toUpdate), oldRecord, updatedName, updatedDescription);
    }

    /**
     * Tests the update method by name with renaming.
     */
    private static void testUpdateWithRename(Runnable updateAction, GroupRecord oldRecord, String updatedName, String updatedDescription) {
        assertDoesNotThrow(updateAction::run);
        GroupRecord updatedRecord = get(oldRecord.getId());
        assertThrows(GroupNotFoundException.class, () -> get(oldRecord.getName()));
        assertEquals(updatedName, updatedRecord.getName().getValue());
        assertEquals(updatedDescription, updatedRecord.getDescription());
    }

    /**
     * Tests the get method by ID with a present group.
     */
    private static GroupDto getNewGroup() {
        return new GroupDto("newgroup", "desc");
    }

    /**
     * Tests the get method by name with a present group.
     */
    private static GroupRecord createAndGet(GroupDto toCreate) {
        create(toCreate);
        return get(toCreate.getName());
    }

    /**
     * Tests the get method by ID with a present group.
     */
    private static GroupRecord get(UUID id) {
        return DataContext.getInstance().getGroupTable().get(id);
    }

    /**
     * Tests the get method by name with a present group.
     */
    private static GroupRecord get(GroupName name) {
        return DataContext.getInstance().getGroupTable().get(name);
    }

    /**
     * Tests the create method with a new group.
     */
    private static void create(GroupDto toCreate) {
        DataContext.getInstance().getGroupTable().create(toCreate);
    }

    /**
     * Tests the delete method by ID with a present group.
     */
    private static void delete(UUID id) {
        DataContext.getInstance().getGroupTable().delete(id);
    }

    /**
     * Tests the delete method by name with a present group.
     */
    private static void delete(GroupName name) {
        DataContext.getInstance().getGroupTable().delete(name);
    }

    /**
     * Tests the update method by ID with a present group.
     */
    private static void update(UUID id, GroupDto toUpdate) {
        DataContext.getInstance().getGroupTable().update(id, toUpdate);
    }

    /**
     * Tests the update method by name with a present group.
     */
    private static void update(GroupName name, GroupDto toUpdate) {
        DataContext.getInstance().getGroupTable().update(name, toUpdate);
    }
}