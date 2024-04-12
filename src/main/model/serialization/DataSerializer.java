package main.model.serialization;

import main.controllers.BaseController;
import main.model.data.DataContext;
import main.model.data.records.GroupRecord;
import main.model.data.records.ProductRecord;
import main.model.dto.Mapper;
import main.model.exceptions.DomainException;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * The DataSerializer class provides static methods for saving and loading data.
 */
public class DataSerializer {
    public static final OnClosingSerializationListener ON_CLOSING_SERIALIZATION_LISTENER = new OnClosingSerializationListener();

    private static final Path BASE_DIR = Paths.get(System.getProperty("user.dir"));
    private static final String GROUPS_FILE_NAME = "groups.dat";
    private static final String PRODUCTS_FILE_NAME = "products.dat";

    /**
     * Saves the current state of the application.
     */
    public static void save() {
        saveGroups();
        saveProducts();
    }

    /**
     * Saves the current state of the groups.
     */
    private static void saveGroups() {
        List<GroupRecord> groups = DataContext.getInstance().getGroupTable().getAll();
        saveList(groups, GROUPS_FILE_NAME);
    }

    /**
     * Saves the current state of the products.
     */
    private static void saveProducts() {
        List<ProductRecord> products = DataContext.getInstance().getProductTable().getAll();
        saveList(products, PRODUCTS_FILE_NAME);
    }

    /**
     * Saves a list to a file.
     *
     * @param listToSave the list to save
     * @param fileName the name of the file
     */
    private static <T> void saveList(List<T> listToSave, String fileName) {
        File fileToSave = BASE_DIR.resolve(fileName).toFile();
        try (var stream = new ObjectOutputStream(new FileOutputStream(fileToSave))) {
            stream.writeObject(listToSave);
        } catch (IOException e) {
            BaseController.showExceptionMessage(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads the saved state of the application.
     */
    public static void load() {
        try {
            loadGroups();
            loadProducts();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Save files were not found! Loading default profile");
        } catch (DomainException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Domain error in save files: " + e.getMessage() + "\nLoading default profile",
                    "DOMAIN ERROR",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Loads the saved state of the groups.
     */
    private static void loadGroups() throws FileNotFoundException, DomainException {
        List<GroupRecord> groupRecords = readList(GROUPS_FILE_NAME);
        DataContext.getInstance().getGroupTable().bulkInsert(groupRecords);
    }

    /**
     * Loads the saved state of the products.
     */
    private static void loadProducts() throws FileNotFoundException, DomainException {
        List<ProductRecord> productRecords = readList(PRODUCTS_FILE_NAME);
        DataContext.getInstance().getProductTable().bulkInsert(productRecords);
    }

    /**
     * Reads a list from a file.
     *
     * @param fileName the name of the file
     * @return the list read from the file
     * @throws FileNotFoundException if the file does not exist
     */
    private static <T> List<T> readList(String fileName) throws FileNotFoundException {
        File loadFile = BASE_DIR.resolve(fileName).toFile();
        try (var stream = new ObjectInputStream(new FileInputStream(loadFile))) {
            return (List<T>) stream.readObject();
        } catch (FileNotFoundException e) {
            throw e;
        } catch (ClassNotFoundException | IOException e) {
            BaseController.showExceptionMessage(e);
            throw new RuntimeException(e);
        }
    }
}