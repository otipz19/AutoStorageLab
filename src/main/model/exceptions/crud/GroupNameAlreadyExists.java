package main.model.exceptions.crud;

import main.model.valueObjects.GroupName;

public class GroupNameAlreadyExists extends CrudException{
    public GroupNameAlreadyExists(GroupName name) {
        super("Group with specified name already exists: " + name);
    }
}
