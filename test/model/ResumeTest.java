package model;

import org.junit.Before;
import org.junit.Test;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ResumeTest {

    private Resume resume;

    private static Map<SectionType, BaseSection> SECTION_MAP = new EnumMap<>(SectionType.class);
    private static Section SIMPLE_SECTION = new Section(SectionType.OBJECTIVE.getTitle(), "Content");
    private static MultiSection MULTI_SECTION = new MultiSection("Title multi section");
    static {
        MULTI_SECTION.addItem("Content item 1");
        MULTI_SECTION.addItem("Content item 2");
        MULTI_SECTION.addItem("Content item 3");
    }

    private static Map<ContactType, Contact> CONTACTS_MAP = new EnumMap<>(ContactType.class);
    private static Contact HOMEPAGE = new Contact("https://www.dvshapkin.ru");

    @Before
    public void setUp() {
        resume = new Resume("dummy");

        Section section = new Section("Title section 1", "Content section 1");
        SECTION_MAP.put(SectionType.PERSONAL, section);
        SECTION_MAP.put(SectionType.ACHIEVEMENT, MULTI_SECTION);
        resume.setSections(SECTION_MAP);


        CONTACTS_MAP.put(ContactType.PHONE, new Contact("+7(383) 202-12-26"));
        CONTACTS_MAP.put(ContactType.EMAIL, new Contact("dvshapkin@mail.ru"));
        CONTACTS_MAP.put(ContactType.SKYPE, new Contact("dvshapkin"));
        CONTACTS_MAP.put(ContactType.PROFILE, new Contact("dvshapkin@github.com"));
        //CONTACTS_MAP.put(ContactType.HOMEPAGE, new Contact("https://www.dvshapkin.ru"));
        resume.setContacts(CONTACTS_MAP);
    }

    @Test
    public void getSections() {
        assertEquals(SECTION_MAP, resume.getSections());
    }

    @Test
    public void addSection() {
        resume.addSection(SectionType.OBJECTIVE, SIMPLE_SECTION);
        assertEquals(3, resume.getSections().size());
        assertEquals(SIMPLE_SECTION, resume.getSections().get(SectionType.OBJECTIVE));
    }

    @Test
    public void getSection() {
        assertEquals(MULTI_SECTION, resume.getSection(SectionType.ACHIEVEMENT));
    }

    @Test
    public void getContacts() {
        assertEquals(CONTACTS_MAP, resume.getContacts());
    }

    @Test
    public void addContact() {
        resume.addContact(ContactType.HOMEPAGE, HOMEPAGE);
        assertEquals(5, resume.getContacts().size());
        assertEquals(HOMEPAGE, resume.getContacts().get(ContactType.HOMEPAGE));
    }

    @Test
    public void getContact() {
        assertEquals(HOMEPAGE, resume.getContact(ContactType.HOMEPAGE));
    }
}