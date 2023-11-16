package Model;

import java.io.Serializable;

public abstract class PIR implements Serializable {
    public abstract <T> T accept(PrintVisitor<T> visitor);
    public abstract <T> T accept(ModifyVisitor<T> visitor);
    public abstract String getIdentifier();
    public abstract void setIdentifier(String identifier);

    public abstract String getString();
}
