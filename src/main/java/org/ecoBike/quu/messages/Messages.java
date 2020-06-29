package org.ecoBike.quu.messages;

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
    INCORRECT_INPUT("Incorrect input, or incorrect data file please make you choice\n"),
    CONFIRM_INPUT("Do you confirm? Type y/n."),
    ADDED_SUCCESSFULLY("Added successfully!\n"),
    WRITE_SUCCESSFULLY("Write to file successfully!\n"),
    YOU_SURE_GO_OUT("You sure you want to go out? Data will be lost. Type y/n"),
    NOTHING_WRITE("Nothing to write"),
    GOOD_BY("Bye-bye!"),
    ENTER_FILE_PATH("Enter path to file"),
    INVALID_ENTER_PATH("Invalid path! Enter correct path ot type \"esc\""),
    PATH_SUCCESSFULLY("Successfully"),
    ENTER_NUMBER_OF_TYPE("Enter the number of type of bike:\n" +
            "                1 - E-Bike\n" +
            "                2 - Folding Bike\n" +
            "                3 - Speedelec"),
    ENTER_CORRECT_NUMBER("Incorrect input! Enter 1-3 number"),
    FOUND_BIKE("Found bike %s"),
    NOT_FOUND_BIKE("No0t found bike %s");


    String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
