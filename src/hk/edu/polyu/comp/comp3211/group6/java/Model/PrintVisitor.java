package hk.edu.polyu.comp.comp3211.group6.java.Model;

public interface PrintVisitor<T> {
    T visitNote(Note note);
    T visitContact(Contact contact);
    T visitTask(Task task);
    T visitSchedule(Schedule schedule);
}
