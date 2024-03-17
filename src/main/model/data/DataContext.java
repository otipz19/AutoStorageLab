package main.model.data;

import lombok.Getter;
import main.model.data.tables.GroupTable;
import main.model.data.tables.IGroupTable;
import main.model.data.tables.IProductTable;
import main.model.data.tables.ProductTable;

public class DataContext implements IDataContext {
    @Getter
    private static final DataContext instance;

    @Getter
    private final IGroupTable groupTable = new GroupTable();
    @Getter
    private final IProductTable productTable = new ProductTable();

    static {
        instance = new DataContext();
    }

    //Forbid to create new instances
    private DataContext(){}
}
