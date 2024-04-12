package main.model.exceptions.crud;

import main.model.valueObjects.GroupName;

/**
 * The GroupNameAlreadyExists class represents exceptions that occur when a group name already exists in the system.
 * It extends the CrudException class.
 */
public class GroupNameAlreadyExists extends CrudException{
    /**
     * Constructs a new GroupNameAlreadyExists exception with the given group name.
     *
     * @param name the name of the group that already exists
     */
    public GroupNameAlreadyExists(GroupName name) {
        super("Group with specified name already exists: " + name);
    }
}