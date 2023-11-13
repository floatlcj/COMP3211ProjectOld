package Model;

import java.io.Serializable;

public abstract class PIR implements Serializable {
    public abstract <T> T accept(pirVisitor<T> visitor);
}
