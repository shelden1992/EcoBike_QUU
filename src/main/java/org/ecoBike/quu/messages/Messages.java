package org.ecoBike.quu.messagesMenu;

/**
 * Created by Shelupets Denys on 24.06.2020.
 */
public enum Messages {
    PLEASE_MAKE_CHOICE("\n==================================================\n" +
            "Please make your choice:"),
    SHOW_ENTIRE_ECOBIKE_CATALOG("1 - Show the entire EcoBike catalog"),
    ADD_NEW_FOLDING_BIKE("2 – Add a new folding bike"),
    ADD_NEW_SPEEDELEC("3 – Add a new speedelec"),
    ADD_NEW_E_BIKE("4 – Add a new e-bike"),
    FIND_FIRST_ITEM_OF_PARTICULAR_BAND("5 – Find the first item of a particular brand"),
    WRITE_TO_FILE("6 – Write to file"),
    STOP_PROGRAM("7 – Stop the program\n" +
            "=================================================="),
    INCORRECT_INPUT("Incorrect input, please make you choice\n"),
    CONFIRM_INPUT("Do you confirm? Type y/n."),
    ADDED_SUCCESSFULLY("Added successfully!\n"),
    WRITE_SUCCESSFULLY("Write to file successfully!\n"),
    YOU_SURE_GO_OUT("You sure you want to go out? Data will be lost. Type y/n"),
    NOTHING_WRITE("Nothing to write"),
    GOOD_BY("Good By"),
    ;


    String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
