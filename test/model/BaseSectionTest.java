package model;

import org.junit.Before;
import org.junit.Test;
import storage.AbstractStorageTest;

import java.util.List;

import static org.junit.Assert.*;

public class BaseSectionTest<T> {

    protected BaseSection<T> section;

    @Test
    public void getTitle() {
        String title = "Section title";
        section.setTitle(title);
        assertEquals(title, section.getTitle());
    }

    @Test
    public void setTitle() {
        String title = "Section title";
        section.setTitle(title);
        assertEquals(title, section.getTitle());
    }



    @Test
    public void toStringTest() {
        assertEquals(String.format("%s\n%s", section.title, section.formatContent()), section.toString());
    }
}