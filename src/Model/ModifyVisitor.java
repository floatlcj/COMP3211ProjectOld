package Model;

public interface ModifyVisitor<T> {
    T visitNote(Note note);
    T visitTask(Task task);
    T visitSchedule(Schedule schedule);
    T visitContact(Contact contact);
}
