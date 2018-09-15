package model;

import java.io.Serializable;
import java.util.*;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume>, Serializable {

    private static final long serialVersionUID = 1L;

    private final String uuid;
    private String fullName;
    private Map<SectionType, BaseSection> sections = new EnumMap<>(SectionType.class);
    private Map<ContactType, Contact> contacts = new EnumMap<>(ContactType.class);


    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "Resume.uuid must not be null");
        Objects.requireNonNull(fullName, "Resume.fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }


    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public Map<SectionType, BaseSection> getSections() {
        return sections;
    }

    public void setSections(Map<SectionType, BaseSection> sections) {
        this.sections = sections;
    }

    public void addSection(SectionType type, BaseSection section) {
        sections.put(type, section);
    }

    public BaseSection getSection(SectionType type) {
        return sections.get(type);
    }


    public Map<ContactType, Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Map<ContactType, Contact> contacts) {
        this.contacts = contacts;
    }

    public void addContact(ContactType type, Contact contact) {
        contacts.put(type, contact);
    }

    public Contact getContact(ContactType type) {
        return contacts.get(type);
    }


    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.getFullName());
        return cmp != 0 ? cmp : uuid.compareTo(o.uuid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }
}
