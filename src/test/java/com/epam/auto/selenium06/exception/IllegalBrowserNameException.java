package com.epam.auto.selenium06.exception;

import com.epam.auto.selenium06.webdriver.Browser;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public class IllegalBrowserNameException extends IllegalArgumentException {
    public IllegalBrowserNameException() {
        super(String.format("Unsupported browser name. Supported browser names are %s",
                Arrays.stream(Browser.values())
                        .map(browser -> browser.name().toLowerCase(Locale.ROOT))
                        .collect(Collectors.joining(", "))));
    }

}
