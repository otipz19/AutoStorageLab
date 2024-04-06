package tests.model.data.tables;

import main.model.data.DataContext;
import main.model.data.records.GroupRecord;
import main.model.dto.GroupDto;
import main.model.valueObjects.GroupName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTableTest {
    private static GroupRecord group;

    @BeforeEach
    public void setUp(){
        DataContext.resetData();
        initGroup();
    }

    private static void initGroup() {
        GroupDto group = new GroupDto(new GroupName("group"), "desc");
        DataContext.getInstance().getGroupTable().create(group);
        ProductTableTest.group = DataContext.getInstance().getGroupTable().get(group.getName());
    }

//    @Test
//    public void create_givenProductWithNewName_
}
