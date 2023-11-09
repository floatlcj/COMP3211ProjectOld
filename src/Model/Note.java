package Model;

public class Note extends PIR{
    protected String identifier;
    protected String text;

    Note(String name, String text){
        this.identifier = name;
        this.text = text;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getText() {
        return text;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public <T> T accept(pirVisitor<T> visitor) {
        return visitor.visitNote(this);
    }
}
