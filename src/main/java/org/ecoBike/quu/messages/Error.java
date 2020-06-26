package org.ecoBike.quu.messages;

/**
 * Created by Shelupets Denys on 26.06.2020.
 */
public enum Error {
    ERROR1("Not valid number menu."),
    ERROR2("Parameter cannot be negative"),
    ERROR3(""),
    ;

    String name;

    public String getName() {
        return name;
    }

    Error(String name) {
        this.name = name;
    }
}
