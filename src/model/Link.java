package model;

import java.util.Objects;

public class Link {
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
