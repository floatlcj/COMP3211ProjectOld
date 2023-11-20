package hk.edu.polyu.comp.comp3211.group6.java.Controller;

public class PIMError extends RuntimeException{
    public PIMError(String message){
        System.out.println(message);
    }
}
