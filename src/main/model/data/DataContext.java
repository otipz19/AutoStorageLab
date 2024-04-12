package main.model.data;

import lombok.Getter;
import main.model.data.tables.GroupTable;
import main.model.data.tables.IGroupTable;
import main.model.data.tables.IProductTable;
import main.model.data.tables.ProductTable;

public class DataContext implements IDataContext {
    @Getter
    private static final DataContext instance = new DataContext();

    @Getter
    private final IGroupTable groupTable = new GroupTable();
    @Getter
    private final IProductTable productTable = new ProductTable();

    /**
     * The DataContext class provides a single point of access to the data tables in the application.
     * It implements the IDataContext interface to provide a contract for data context operations.
     */
    //Forbid to create new instances
    private DataContext(){}

    /**
     * Resets the data in the group and product tables.
     */
    public static void resetData(){
        instance.groupTable.resetData();
        instance.productTable.resetData();
    }
}
