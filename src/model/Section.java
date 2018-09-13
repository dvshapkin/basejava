package model;

public class Section extends BaseSection<String> {

    public Section(SectionType type, String title) {
        super(type, title);
        setContent("");
    }

    public Section(SectionType type, String title, String content) {
        this(type, title);
        setContent(content);
    }

    String formatContent() {
        return getContent();
    }
}
