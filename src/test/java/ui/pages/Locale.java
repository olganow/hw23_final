package ui.pages;

public enum Locale {

    EN("English"),
    RU("Русский"),
    ES("Español"),
    DE("Deutsch");

    private final String desc;

    Locale(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}