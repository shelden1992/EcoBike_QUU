package org.ecoBike.quu.messages;

/**
 * Created by Shelupets Denys on 26.06.2020.
 */
public enum LoggerMessage {
    SHOW_ALL_BIKE("Show all bikes"),
    ADD_NEW_ELECTRO_BIKE("Add new ElectroBike"),
    ADD_NEW_SPEEDELEC_BIKE("Add new SpeedelecBike"),
    ADD_NEW_FOLDING_BIKE("Add new FoldingBike"),
    WRITE_TO_FILE("Write to File"),
    ENTER_NOT_VALID_VALUE("Enter not valid value."),
    OUTPUT_EXCEPTION("Output exception"),

    ;
    String name;

    LoggerMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}