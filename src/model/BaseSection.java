package model;

public abstract class BaseSection<T> {
    protected SectionType type;
    protected String title;
    protected T content;

    public BaseSection(SectionType type, String title) {
        this.type = type;
        this.title = title;
    }

    public SectionType getType() {
        return type;
    }

    public void setType(SectionType type) {
        this.type = type;
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
