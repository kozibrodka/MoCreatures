package net.kozibrodka.mocreatures.glasscfg.enums;

public enum DifficultyEnum {
    EASY("Easy"),
    NORMAL("Normal"),
    HARD("Hard");

    final String stringValue;

    DifficultyEnum() {
        this.stringValue = "Normal";
    }

    DifficultyEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}