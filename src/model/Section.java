package model;

public class Section extends BaseSection<String> {

    public Section(String title) {
        super(title);
        setContent("");
    }

    public Section(String title, String content) {
        this(title);
        setContent(content);
    }

    String formatContent() {
        return getContent();
    }
}
