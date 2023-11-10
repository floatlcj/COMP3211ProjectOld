package Controller;

public class PIMError extends RuntimeException{
    public PIMError(String message){
        System.out.println(message);
    }
}
