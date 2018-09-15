package model;

import java.io.Serializable;
import java.util.Objects;

public class Link implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String url;

    public Link(String title, String url) {
        Objects.requireNonNull(title, "title must not be null");
        this.title = title;
        this.url = url;
    }

    @Override
    public String toString() {
        return title + ", url='" + url + '\'';
    }
}
