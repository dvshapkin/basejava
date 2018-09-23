package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.*;
import org.junit.Before;
import org.junit.Test;
import util.Config;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {

    protected static final String PATH = Config.getInstance().getStorageDir();

    protected static final Resume RESUME_1 = new Resume("uuid1", "Name1");
    protected static final Resume RESUME_2 = new Resume("uuid2", "Name2");
    protected static final Resume RESUME_3 = new Resume("uuid3", "Name3");

    private static Map<ContactType, Contact> CONTACTS_MAP = new EnumMap<>(ContactType.class);

    private static Map<SectionType, BaseSection> SECTION_MAP = new EnumMap<>(SectionType.class);
    private static Section SIMPLE_SECTION = new Section(SectionType.OBJECTIVE.getTitle(), "Content");
    private static MultiSection MULTI_SECTION = new MultiSection("Title multi section");

    static {
        CONTACTS_MAP.put(ContactType.PHONE, new Contact("+7(383) 202-12-26"));
        CONTACTS_MAP.put(ContactType.EMAIL, new Contact("dvshapkin@mail.ru"));
        CONTACTS_MAP.put(ContactType.SKYPE, new Contact("dvshapkin"));
        CONTACTS_MAP.put(ContactType.PROFILE, new Contact("dvshapkin@github.com"));
        CONTACTS_MAP.put(ContactType.HOMEPAGE, new Contact("https://www.dvshapkin.ru"));
        RESUME_2.setContacts(CONTACTS_MAP);

        MULTI_SECTION.addItem("Content item 1");
        MULTI_SECTION.addItem("Content item 2");
        MULTI_SECTION.addItem("Content item 3");
    }

    protected Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_3);
        storage.save(RESUME_2);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void get() {
        Resume resume = storage.get(RESUME_2.getUuid());
        assertEquals(RESUME_2, resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        Resume resume = storage.get("dummy");
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        assertEquals(3, resumes.length);
        assertEquals(RESUME_1, resumes[0]);
        assertEquals(RESUME_3, resumes[1]);
        assertEquals(RESUME_2, resumes[2]);
    }

    @Test
    public void getAllSorted() {
        List<Resume> resumes = storage.getAllSorted();
        assertEquals(3, resumes.size());
        assertEquals(RESUME_1, resumes.get(0));
        assertEquals(RESUME_2, resumes.get(1));
        assertEquals(RESUME_3, resumes.get(2));
    }

    @Test
    public void update() {

    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("Иванов Иван"));
    }

    @Test
    public void save() {
        int oldSize = storage.size();
        Resume newResume = new Resume("Иванов Иван");
        storage.save(newResume);
        assertEquals(oldSize + 1, storage.size());
        assertEquals(newResume, storage.get(newResume.getUuid()));
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void delete() {
        int oldSize = storage.size();
        storage.delete(RESUME_3.getUuid());
        assertEquals(oldSize - 1, storage.size());
        Resume[] resumes = storage.getAll();
        assertEquals(RESUME_1, resumes[0]);
        assertEquals(RESUME_2, resumes[1]);

        oldSize = storage.size();
        storage.delete(RESUME_1.getUuid());
        assertEquals(oldSize - 1, storage.size());
        resumes = storage.getAll();
        assertEquals(RESUME_2, resumes[0]);

        oldSize = storage.size();
        storage.delete(RESUME_2.getUuid());
        assertEquals(oldSize - 1, storage.size());
        resumes = storage.getAll();
        assertEquals(0, resumes.length);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

}