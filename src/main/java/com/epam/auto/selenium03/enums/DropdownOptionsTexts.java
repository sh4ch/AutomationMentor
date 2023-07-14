package com.epam.auto.selenium03.enums;

import java.util.ArrayList;
import java.util.List;

public enum DropdownOptionsTexts {
    YELLOW("Yellow"),
    RED("Red"),
    GREEN("Green"),
    BLUE("Blue");

    private final String text;

    DropdownOptionsTexts(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static List<String> getDropdownOptionsTexts() {
        List<String> dropdownOptionsTexts = new ArrayList<>();
        for (DropdownOptionsTexts item : DropdownOptionsTexts.values()) {
            dropdownOptionsTexts.add(item.getText());
        }
        return dropdownOptionsTexts;
    }
}
