package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MultiSectionTest extends BaseSectionTest<List<String>> {

    @Before
    public void setUp() {
        section = new MultiSection(SectionType.QUALIFICATIONS.getTitle());
        ((MultiSection) section).addItem("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        ((MultiSection) section).addItem("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        ((MultiSection) section).addItem("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
    }

    @Test
    public void getContent() {
        List<String> content = new ArrayList<>();
        content.add("Content item 1");
        content.add("Content item 2");
        content.add("Content item 3");
        section.content = content;
        assertEquals(content, section.getContent());
    }

    @Test
    public void setContent() {
        List<String> content = new ArrayList<>();
        content.add("Content item 1");
        content.add("Content item 2");
        content.add("Content item 3");
        section.setContent(content);
        assertEquals(content, section.content);
    }
}