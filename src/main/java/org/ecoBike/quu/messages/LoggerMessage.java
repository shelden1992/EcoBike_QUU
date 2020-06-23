package org.ecoBike.quu.messages;

/**
 * Created by Shelupets Denys on 26.06.2020.
 */
public enum Logger {
    SHOW_ALL_BIKE("Show all bikes"),
    ;
    String name;

    Logger(String name) {
        this.name =name;
    }
}
