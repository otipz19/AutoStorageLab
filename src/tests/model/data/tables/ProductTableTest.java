package tests.model.data.tables;

import main.model.data.DataContext;
import main.model.data.records.GroupRecord;
import main.model.data.records.ProductRecord;
import main.model.dto.GroupDto;
import main.model.dto.ProductDto;
import main.model.exceptions.crud.GroupNotFoundException;
import main.model.exceptions.crud.ProductNameAlreadyExists;
import main.model.exceptions.crud.ProductNotFoundException;
import main.model.valueObjects.GroupName;
import main.model.valueObjects.ProductName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTableTest {
    private static GroupRecord FIRST_GROUP;
    private static GroupRecord SECOND_GROUP;

    @BeforeEach
    public void setUp() {
        DataContext.resetData();
        initGroup();
    }

    private static void initGroup() {
        GroupDto firstDto = new GroupDto("firstgroup", "desc");
        GroupDto secondDto = new GroupDto("secondgroup", "desc");
        FIRST_GROUP = DataContext.getInstance().getGroupTable().create(firstDto);
        SECOND_GROUP = DataContext.getInstance().getGroupTable().create(secondDto);
    }

    @Test
    public void create_givenProductWithNewName_givenExistingGroup_putsNewRecordInAllIndexes() {
        ProductDto toCreate = getNewProductDto();

        ProductRecord newRecord = assertDoesNotThrow(() -> create(toCreate));
        assertDoesNotThrow(() -> get(newRecord.getId()));
        assertDoesNotThrow(() -> get(toCreate.getName()));
        assertTrue(getByGroupId(newRecord.getGroupId()).contains(newRecord));
    }

    @Test
    public void create_givenProductWithExistingName_throwsException() {
        ProductDto first = new ProductDto(
                "product",
                "desc",
                "man",
                1,
                1,
                FIRST_GROUP.getName().getValue());
        ProductDto second = new ProductDto(
                "product",
                "desc",
                "man",
                1,
                1,
                SECOND_GROUP.getName().getValue());
        create(first);

        assertThrows(ProductNameAlreadyExists.class, () -> create(second));
    }

    @Test
    public void create_givenProductWithNonExistingGroup_throwsException() {
        ProductDto toCreate = new ProductDto(
                "product",
                "desc",
                "man",
                1,
                1,
                "nonexisting");

        assertThrows(GroupNotFoundException.class, () -> create(toCreate));
    }

    @Test
    public void delete_byId_givenNonExistingId_throwsException() {
        create(getNewProductDto());

        assertThrows(ProductNotFoundException.class, () -> delete(UUID.randomUUID()));
    }

    @Test
    public void delete_byId_givenExistingId_deletesRecordFromAllIndexes() {
        ProductRecord toDelete = create(getNewProductDto());

        testExistingDelete(toDelete, () -> delete(toDelete.getId()));
    }

    @Test
    public void delete_byName_givenNonExistingName_throwsException() {
        create(getNewProductDto());

        assertThrows(ProductNotFoundException.class, () -> delete(new ProductName("somerandomname")));
    }

    @Test
    public void delete_by_name_givenExistingName_deletesRecordFromAllIndexes() {
        ProductRecord toDelete = create(getNewProductDto());

        testExistingDelete(toDelete, () -> delete(toDelete.getName()));
    }

    private void testExistingDelete(ProductRecord toDelete, Runnable deleteAction) {
        assertDoesNotThrow(deleteAction::run);
        assertThrows(ProductNotFoundException.class, () -> get(toDelete.getId()));
        assertThrows(ProductNotFoundException.class, () -> get(toDelete.getName()));
        assertFalse(getByGroupId(toDelete.getGroupId()).contains(toDelete));
    }

    @Test
    public void deleteByGroupId_givenNonExistingGroup_throwsException() {
        assertThrows(GroupNotFoundException.class, () -> deleteByGroupId(UUID.randomUUID()));
    }

    @Test
    public void deleteByGroupId_givenExistingGroup_deletesAllRelatedProducts() {
        ProductDto[] firstGroupProducts = new ProductDto[]{
                new ProductDto(
                        "first",
                        "desc",
                        "man",
                        1,
                        1,
                        FIRST_GROUP.getName().getValue()),
                new ProductDto(
                        "second",
                        "desc",
                        "man",
                        1,
                        1,
                        FIRST_GROUP.getName().getValue()),
                new ProductDto(
                        "third",
                        "desc",
                        "man",
                        1,
                        1,
                        FIRST_GROUP.getName().getValue()),
        };

        ProductDto secondGroupProduct = new ProductDto(
                "anotherone",
                "desc",
                "man",
                1,
                1,
                SECOND_GROUP.getName().getValue());

        for(ProductDto dto: firstGroupProducts){
            create(dto);
        }
        create(secondGroupProduct);

        List<ProductRecord> firstGroupRecords = getByGroupId(FIRST_GROUP.getId());

        assertDoesNotThrow(() -> deleteByGroupId(FIRST_GROUP.getId()));
        assertDoesNotThrow(() -> get(secondGroupProduct.getName()));
        for(ProductRecord product : firstGroupRecords){
            assertThrows(ProductNotFoundException.class, () -> get(product.getId()));
            assertThrows(ProductNotFoundException.class, () -> get(product.getName()));
            assertFalse(getByGroupId(FIRST_GROUP.getId()).contains(product));
        }
    }

    private ProductDto getNewProductDto() {
        return new ProductDto(
                "newproduct",
                "desc",
                "man",
                100,
                100,
                FIRST_GROUP.getName().getValue());
    }

    private ProductRecord create(ProductDto toCreate) {
        return DataContext.getInstance().getProductTable().create(toCreate);
    }

    private ProductRecord get(ProductName name) {
        return DataContext.getInstance().getProductTable().get(name);
    }

    private ProductRecord get(UUID id) {
        return DataContext.getInstance().getProductTable().get(id);
    }

    private List<ProductRecord> getByGroupId(UUID groupId) {
        return DataContext.getInstance().getProductTable().getByGroupId(groupId);
    }

    private void delete(UUID id) {
        DataContext.getInstance().getProductTable().delete(id);
    }

    private void delete(ProductName name) {
        DataContext.getInstance().getProductTable().delete(name);
    }

    private void deleteByGroupId(UUID groupId) {
        DataContext.getInstance().getProductTable().deleteByGroupId(groupId);
    }
}
