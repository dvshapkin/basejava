package model;

public class Section extends BaseSection<String> {

    private static final long serialVersionUID = 1L;

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
