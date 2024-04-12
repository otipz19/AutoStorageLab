package main.model.exceptions.crud;

import main.model.valueObjects.GroupName;

import java.util.UUID;

/**
 * The GroupNotFoundException class represents exceptions that occur when a group is not found in the system.
 * It extends the CrudException class.
 */
public class GroupNotFoundException extends CrudException {
    /**
     * Constructs a new GroupNotFoundException with the given group id.
     *
     * @param id the id of the group that was not found
     */
    public GroupNotFoundException(UUID id) {
        super("Group with id: " + id + " was not found");
    }

    /**
     * Constructs a new GroupNotFoundException with the given group name.
     *
     * @param name the name of the group that was not found
     */
    public GroupNotFoundException(GroupName name) {
        super("Group with name: " + name + " was not found");
    }
}