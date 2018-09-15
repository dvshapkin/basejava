package model;

import java.io.Serializable;

public abstract class BaseSection<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String title;
    protected T content;

    public BaseSection(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("%s\n%s", title, formatContent());
    }

    abstract String formatContent();
}
