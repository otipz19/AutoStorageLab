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

public class DataSerializer {
    public static final OnClosingSerializationListener ON_CLOSING_SERIALIZATION_LISTENER = new OnClosingSerializationListener();

    private static final Path BASE_DIR = Paths.get(System.getProperty("user.dir"));
    private static final String GROUPS_FILE_NAME = "groups.dat";
    private static final String PRODUCTS_FILE_NAME = "products.dat";

    public static void save() {
        saveGroups();
        saveProducts();
    }

    private static void saveGroups() {
        List<GroupRecord> groups = DataContext.getInstance().getGroupTable().getAll();
        saveList(groups, GROUPS_FILE_NAME);
    }

    private static void saveProducts() {
        List<ProductRecord> products = DataContext.getInstance().getProductTable().getAll();
        saveList(products, PRODUCTS_FILE_NAME);
    }

    private static <T> void saveList(List<T> listToSave, String fileName) {
        File fileToSave = BASE_DIR.resolve(fileName).toFile();
        try (var stream = new ObjectOutputStream(new FileOutputStream(fileToSave))) {
            stream.writeObject(listToSave);
        } catch (IOException e) {
            BaseController.showExceptionMessage(e);
            throw new RuntimeException(e);
        }
    }

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

    private static void loadGroups() throws FileNotFoundException, DomainException {
        List<GroupRecord> groupRecords = readList(GROUPS_FILE_NAME);
        DataContext.getInstance().getGroupTable().bulkInsert(groupRecords);
    }

    private static void loadProducts() throws FileNotFoundException, DomainException {
        List<ProductRecord> productRecords = readList(PRODUCTS_FILE_NAME);
        DataContext.getInstance().getProductTable().bulkInsert(productRecords);
    }

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
