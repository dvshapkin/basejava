package model;

import java.io.Serializable;
import java.util.Objects;

public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    private String value;

    public Contact(String value) {
        Objects.requireNonNull(value, "contact must not be null");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
