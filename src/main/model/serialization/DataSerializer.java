package main.model.serialization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import main.controllers.BaseController;
import main.model.data.DataContext;
import main.model.data.records.GroupRecord;
import main.model.data.records.ProductRecord;
import main.model.dto.GroupSerializationDto;
import main.model.dto.Mapper;
import main.model.dto.ProductSerializationDto;
import main.model.exceptions.DomainException;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * The DataSerializer class provides static methods for saving and loading data.
 */
public class DataSerializer {
    public static final OnClosingSerializationListener ON_CLOSING_SERIALIZATION_LISTENER = new OnClosingSerializationListener();

    private static final Path BASE_DIR = Paths.get(System.getProperty("user.dir"));
    private static final String GROUPS_FILE_NAME = "groups.json";
    private static final String PRODUCTS_FILE_NAME = "products.json";

    private static final ObjectMapper SERIALIZER;

    static {
        SERIALIZER = new ObjectMapper();
        SERIALIZER.enable(SerializationFeature.INDENT_OUTPUT);
    }

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
        List<GroupRecord> records = DataContext.getInstance().getGroupTable().getAll();
        List<GroupSerializationDto> toSerialize = records.stream().map(Mapper::mapSerialization).toList();
        saveList(toSerialize, GROUPS_FILE_NAME);
    }

    /**
     * Saves the current state of the products.
     */
    private static void saveProducts() {
        List<ProductRecord> records = DataContext.getInstance().getProductTable().getAll();
        List<ProductSerializationDto> toSerialize = records.stream().map(Mapper::mapSerialization).toList();
        saveList(toSerialize, PRODUCTS_FILE_NAME);
    }

    /**
     * Saves a list to a file.
     *
     * @param listToSave the list to save
     * @param fileName   the name of the file
     */
    private static <T> void saveList(List<T> listToSave, String fileName) {
        File fileToSave = BASE_DIR.resolve(fileName).toFile();
        try (var writer = new BufferedWriter(new FileWriter(fileToSave))) {
            writer.write(SERIALIZER.writeValueAsString(listToSave));
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
        } catch (FileNotFoundException | NoSuchFileException e) {
            JOptionPane.showMessageDialog(null, "Save files were not found! Loading default profile");
            DataContext.resetData();
        } catch (DomainException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Domain error in save files: " + e.getMessage() + "\nLoading default profile",
                    "DOMAIN ERROR",
                    JOptionPane.ERROR_MESSAGE
            );
            DataContext.resetData();
        }
    }

    /**
     * Loads the saved state of the groups.
     */
    private static void loadGroups() throws FileNotFoundException, DomainException, NoSuchFileException {
        var typeReference = new TypeReference<List<GroupSerializationDto>>(){};
        List<GroupSerializationDto> deserialized = readList(GROUPS_FILE_NAME, typeReference);
        List<GroupRecord> records = deserialized.stream().map(Mapper::mapSerialization).toList();
        DataContext.getInstance().getGroupTable().bulkInsert(records);
    }

    /**
     * Loads the saved state of the products.
     */
    private static void loadProducts() throws FileNotFoundException, DomainException, NoSuchFileException {
        var typeReference = new TypeReference<List<ProductSerializationDto>>(){};
        List<ProductSerializationDto> deserialized = readList(PRODUCTS_FILE_NAME, typeReference);
        List<ProductRecord> records = deserialized.stream().map(Mapper::mapSerialization).toList();
        DataContext.getInstance().getProductTable().bulkInsert(records);
    }

    /**
     * Reads a list from a file.
     *
     * @param fileName the name of the file
     * @return the list read from the file
     * @throws FileNotFoundException if the file does not exist
     */
    private static <T> List<T> readList(String fileName, TypeReference<List<T>> typeReference)
            throws FileNotFoundException, NoSuchFileException {
        Path loadFile = BASE_DIR.resolve(fileName);
        try {
            List<String> lines = Files.readAllLines(loadFile);
            String fileContent = String.join("\n", lines);
            return SERIALIZER.readValue(fileContent, typeReference);
        } catch (FileNotFoundException | NoSuchFileException e) {
            throw e;
        } catch (IOException e) {
            BaseController.showExceptionMessage(e);
            throw new RuntimeException(e);
        }
    }
}