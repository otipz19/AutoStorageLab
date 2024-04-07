package main.model.guard;

public class Guard {
    public static void againstNull(Object object){
        if(object == null){
            throw new NullValueGuardException();
        }
    }

    public static void againstNullOrBlank(String str){
        againstNull(str);
        if(str.isBlank()){
            throw new BlankStringGuardException();
        }
    }
}
