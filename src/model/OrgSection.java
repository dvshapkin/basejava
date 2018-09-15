package model;

import java.util.ArrayList;
import java.util.List;

public class OrgSection extends BaseSection<List<Organization>> {

    private static final long serialVersionUID = 1L;

    public OrgSection(String title) {
        super(title);
        content = new ArrayList<>();
    }

    public void addItem(Organization item) {
        content.add(item);
    }

    @Override
    String formatContent() {
        StringBuilder sb = new StringBuilder();
        for (Organization item : content) {
            sb.append("\t- " + item.toString() + '\n');
        }
        return sb.toString();
    }
}
