package com.epam.auto.selenium06.exception;

public class WebDriverLaunchTypeException extends IllegalArgumentException {
    public WebDriverLaunchTypeException() {
        super("Unsupported lunch type. Supported lunch types are local and remote");
    }

}
