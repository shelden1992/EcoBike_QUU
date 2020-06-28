package org.ecoBike.quu.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ecoBike.quu.messages.LoggerMessage;
import org.ecoBike.quu.messages.Messages;
import org.ecoBike.quu.model.ElectroBike;
import org.ecoBike.quu.model.FoldingBike;
import org.ecoBike.quu.model.SpeedelecBike;
import org.ecoBike.quu.services.AddingBikeService;
import org.ecoBike.quu.services.Catalog;
import org.ecoBike.quu.utils.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Shelupets Denys on 24.06.2020.
 */
public class Menu {
    public static final String CONFIRM_CASE = "y";

    private static final Logger LOGGER = LogManager.getLogger(Menu.class);
    private static final Map<Integer, Messages> messagesMap;

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

    private Scanner scanner = new Scanner(System.in);
    private Catalog catalog;

    public Menu(Catalog catalog) {
        this.catalog = catalog;
    }


    public static void showMenu() {
        messagesMap.values().forEach(messages -> StringUtils.writeText(messages.getMessage()));
    }

    public void startMenu() {
        catalog.setPath();
        while (true) {
            try {
                showMenu();
                int menuChoice = makeMenuChoice();
                menuSwitcher(menuChoice);
            } catch (Exception e) {
                LOGGER.error(Messages.INCORRECT_INPUT.getMessage(), e);
                StringUtils.writeErrorText(Messages.INCORRECT_INPUT.getMessage());
            }
        }
    }

    public int makeMenuChoice() throws RuntimeException {
        int numberMenu = 0;
        while (!((numberMenu = Integer.parseInt(scanner.next())) > 0
                && numberMenu <= messagesMap.size() - 1)) {
            throw new NumberFormatException();

        }
        return numberMenu;
    }

    private void stopProgramIfTempCatalogsEmpty() {
        if (!catalog.getTempList().isEmpty()) {
            StringUtils.writeText(Messages.YOU_SURE_GO_OUT.getMessage());

            if (CONFIRM_CASE.equalsIgnoreCase(scanner.next())) {
                closeScannerAndExit();
            }
        } else {
            closeScannerAndExit();
        }
    }

    private void closeScannerAndExit() {
        StringUtils.writeText(Messages.GOOD_BY.getMessage());
        scanner.close();
        System.exit(0);
    }

    public void menuSwitcher(int numberMenu) throws IOException, NumberFormatException {

        switch (numberMenu) {
            case 1:
                catalog.showWriteBikeCatalog();
                LOGGER.info(LoggerMessage.SHOW_ALL_BIKE.getName());
                break;
            case 2:
                new AddingBikeService(catalog)
                        .addBikeToCatalog(FoldingBike.class);
                break;
            case 3:
                new AddingBikeService(catalog)
                        .addBikeToCatalog(SpeedelecBike.class);
                break;
            case 4:
                new AddingBikeService(catalog)
                        .addBikeToCatalog(ElectroBike.class);
                break;
//                case 5:
//                    new Thread().start();
//                    break;
            case 6:
                catalog.writeAndClearTempList();
                break;
            case 7:
                stopProgramIfTempCatalogsEmpty();
                break;
            default:
                StringUtils.writeErrorText(Messages.INCORRECT_INPUT.getMessage());
                break;
        }

    }


}
