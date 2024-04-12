package main.model.guard;

/**
 * The Guard class provides static methods to guard against certain conditions.
 */
public class Guard {
    /**
     * Throws a NullValueGuardException if the provided object is null.
     *
     * @param object the object to check
     * @throws NullValueGuardException if the object is null
     */
    public static void againstNull(Object object){
        if(object == null){
            throw new NullValueGuardException();
        }
    }

    /**
     * Throws a NullValueGuardException if the provided string is null, or a BlankStringGuardException if it is blank.
     *
     * @param str the string to check
     * @throws NullValueGuardException if the string is null
     * @throws BlankStringGuardException if the string is blank
     */
    public static void againstNullOrBlank(String str){
        againstNull(str);
        if(str.isBlank()){
            throw new BlankStringGuardException();
        }
    }
}