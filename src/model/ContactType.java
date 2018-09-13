package model;

public enum ContactType {
    PHONE("Телефон"),
    EMAIL("Почта"),
    SKYPE("Skype"),
    PROFILE("Профиль"),
    HOMEPAGE("Домашняя страница");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
