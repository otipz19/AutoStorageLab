package main.model.data;

import main.model.data.tables.IGroupTable;
import main.model.data.tables.IProductTable;

public interface IDataContext {
    IGroupTable getGroupTable();
    IProductTable getProductTable();
}
