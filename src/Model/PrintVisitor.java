package Model;

public interface PrintVisitor<T> {
    T visitNote(Note note);
    T visitContact(Contact contact);
    T visitTask(Task task);
    T visitSchedule(Schedule schedule);
}
