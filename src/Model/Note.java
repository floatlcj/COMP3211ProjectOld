package Model;

import java.io.Serializable;

public class Note extends PIR implements Serializable {
    private String identifier;
    private String text;

    public Note(String identifier, String text){
        this.identifier = identifier;
        this.text = text;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    public String getText() {
        return text;
    }

    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public <T> T accept(PrintVisitor<T> visitor) {
        return visitor.visitNote(this);
    }

    @Override
    public <T> T accept(ModifyVisitor<T> visitor) {
        return visitor.visitNote(this);
    }

    @Override
    public String getString() {
        return text;
    }

    public String toString(){
        String noteStr = identifier + "\n" + text;
        return noteStr;
    }
}
