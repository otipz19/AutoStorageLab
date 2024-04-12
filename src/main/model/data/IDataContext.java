package main.model.data;

import main.model.data.tables.IGroupTable;
import main.model.data.tables.IProductTable;

/**
 * The IDataContext interface provides a contract for data context operations.
 * It defines methods to get instances of IGroupTable and IProductTable.
 */
public interface IDataContext {
    /**
     * Returns an instance of IGroupTable.
     *
     * @return an instance of IGroupTable
     */
    IGroupTable getGroupTable();

    /**
     * Returns an instance of IProductTable.
     *
     * @return an instance of IProductTable
     */
    IProductTable getProductTable();
}
