package main.model.exceptions.crud;

import main.model.valueObjects.GroupName;

import java.util.UUID;

public class GroupNotFoundException extends CrudException {
    public GroupNotFoundException(UUID id) {
        super("Group with id: " + id + " was not found");
    }

    public GroupNotFoundException(GroupName name) {
        super("Group with name: " + name + " was not found");
    }
}
