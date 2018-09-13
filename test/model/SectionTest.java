package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SectionTest extends BaseSectionTest<String> {

    @Before
    public void setUp() {
        section = new Section(SectionType.PERSONAL.getTitle());
        section.setContent("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
    }

    @Test
    public void getContent() {
        String content = "Content string";
        section.content = content;
        assertEquals(content, section.getContent());
    }

    @Test
    public void setContent() {
        String content = "Content string";
        section.setContent(content);
        assertEquals(content, section.content);
    }
}