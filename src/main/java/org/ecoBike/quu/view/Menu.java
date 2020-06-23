package org.ecoBike.quu.view;

import org.ecoBike.quu.messagesMenu.Messages;
import org.ecoBike.quu.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Shelupets Denys on 24.06.2020.
 */
public class StartMenu {
    public static final int STOP_NUMBER = 7;
    private static Map<Integer, Messages> messagesMap;

    static {
        messagesMap = new HashMap<>();
        messagesMap.put(0, Messages.PLEASE_MAKE_CHOICE);
        messagesMap.put(1, Messages.SHOW_ENTIRE_ECOBIKE_CATALOG);
        messagesMap.put(2, Messages.ADD_NEW_FOLDING_BIKE);
        messagesMap.put(3, Messages.ADD_NEW_SPEEDELEC);
        messagesMap.put(4, Messages.ADD_NEW_E_BIKE);
        messagesMap.put(5, Messages.FIND_FIRST_ITEM_OF_PARTICULAR_BAND);
        messagesMap.put(6, Messages.WRITE_TO_FILE);
        messagesMap.put(7, Messages.STOP_PROGRAM);
    }

    public static void showMenu() {
        messagesMap.values().forEach(messages -> StringUtils.writeText(messages.getMessage()));
    }

    public static int makeChoice() {
        int numberMenu = 0;
        while (!(0 < numberMenu && numberMenu <= messagesMap.size() - 1 || (numberMenu == STOP_NUMBER))) {
            try (Scanner scanner = new Scanner(System.in)) {
                numberMenu = scanner.nextInt();
                Messages messages = messagesMap.get(numberMenu);
                System.out.println(messages.getMessage());

            } catch (RuntimeException e) {
                numberMenu = STOP_NUMBER;
                System.out.println("FOF");
            }
        }
        return numberMenu;
    }
}
