package org.ecoBike.quu;

import org.ecoBike.quu.view.Menu;

/**
 * Created by Shelupets Denys on 23.06.2020.
 */
public class MainApp {
    public static final int STOP_NUMBER = 7;
    public static final int CONTINUE_NUMBER = 0;

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        while (true) {
            Menu.showMenu();
            int menuChoice = Menu.makeMenuChoice();
            Menu.menuSwitcher(menuChoice);
        }
    }
}
