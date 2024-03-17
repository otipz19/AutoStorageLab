package main.model.valueObjects;

import lombok.Getter;
import main.model.exceptions.InvalidRecordNameException;

@Getter
public abstract class Name {
    private final String value;

    public Name(String value){
        validate(value);
        this.value = value;
    }

    private static void validate(String value) {
        for(int i = 0; i < value.length(); i++){
            if(!Character.isLetter(value.charAt(i))){
                throw new InvalidRecordNameException(value);
            }
        }
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Name otherName){
            return this.value.equals(otherName.value);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return value.hashCode();
    }

    @Override
    public String toString(){
        return value;
    }
}
