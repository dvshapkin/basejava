package model;

import java.util.ArrayList;
import java.util.List;

public class MultiSection extends BaseSection<List<String>> {

    public MultiSection(SectionType type, String title) {
        super(type, title);
        content = new ArrayList<>();
    }

    public void addItem(String item) {
        content.add(item);
    }

    String formatContent() {
        StringBuilder sb = new StringBuilder();
        for (String item : content) {
            sb.append("\t- " + item + '\n');
        }
        return sb.toString();
    }
}
