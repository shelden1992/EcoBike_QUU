package org.ecoBike.quu.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ecoBike.quu.messages.LoggerMessage;
import org.ecoBike.quu.messages.Messages;
import org.ecoBike.quu.model.ElectroBike;
import org.ecoBike.quu.model.FoldingBike;
import org.ecoBike.quu.model.SpeedelecBike;
import org.ecoBike.quu.services.Catalog;
import org.ecoBike.quu.utils.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shelupets Denys on 24.06.2020.
 */
public class Menu {
    public static final String CONFIRM_CASE = "y";
    public static final String ESC_CASE = "esc";
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

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final Catalog catalog;

    public Menu(Catalog catalog) {
        this.catalog = catalog;
    }


    public static void showMenu() {
        messagesMap.values().forEach(messages -> StringUtils.writeText(messages.getMessage()));
    }

    public void startMenu() {
        String path = getPath();
        while (true) {
            try {
                showMenu();
                int menuChoice = makeMenuChoice();
                menuSwitcher(menuChoice, path);
            } catch (Exception e) {
                LOGGER.error(Messages.INCORRECT_INPUT.getMessage(), e);
                StringUtils.writeErrorText(Messages.INCORRECT_INPUT.getMessage());
            }
        }
    }

    public int makeMenuChoice() throws IOException, NumberFormatException {
        int numberMenu = 0;
        while (!((numberMenu = Integer.parseInt(reader.readLine())) > 0
                && numberMenu <= messagesMap.size() - 1)) {
            throw new NumberFormatException();

        }
        return numberMenu;
    }

    private void stopProgramIfTempCatalogsEmpty() throws IOException {
        if (!catalog.getTempList().isEmpty()) {
            StringUtils.writeText(Messages.YOU_SURE_GO_OUT.getMessage());

            if (CONFIRM_CASE.equalsIgnoreCase(reader.readLine())) {
                closeScannerAndExit();
            }
        } else {
            closeScannerAndExit();
        }
    }

    private void closeScannerAndExit() throws IOException {
        StringUtils.writeText(Messages.GOOD_BY.getMessage());
        reader.close();
        System.exit(0);
    }

    public void menuSwitcher(int numberMenu, String pathToFile) throws IOException, NumberFormatException {

        switch (numberMenu) {
            case 1:
                catalog.showWriteBikeCatalog(pathToFile);
                break;
            case 2:
                catalog.addBike(FoldingBike.class);
                break;
            case 3:
                catalog.addBike(SpeedelecBike.class);
                break;
            case 4:
                catalog.addBike(ElectroBike.class);
                break;
            case 5:
                catalog.findOne(pathToFile);
                break;
            case 6:
                writeTempListClearAndRefreshWriteList(pathToFile);
                break;
            case 7:
                stopProgramIfTempCatalogsEmpty();
                break;
            default:
                StringUtils.writeErrorText(Messages.INCORRECT_INPUT.getMessage());
                break;
        }

    }

    private void writeTempListClearAndRefreshWriteList(String pathToFile) throws IOException {
        catalog.writeTempList(pathToFile);
        catalog.clearTempList();
        catalog.readAll(pathToFile);
    }

    public String getPath() {
        String inputString = "";
        StringUtils.writeText(Messages.ENTER_FILE_PATH.getMessage());
        FileFilter fileFilter = file -> file.exists() && file.isFile() && file.getName().endsWith("ecobike.txt");
        try {
            while (!(fileFilter.accept(new File(inputString = reader.readLine())) || ESC_CASE.equalsIgnoreCase(inputString))
            ) {
                StringUtils.writeErrorText(Messages.INVALID_ENTER_PATH.getMessage());
            }
            if (ESC_CASE.equalsIgnoreCase(inputString)) {
                LOGGER.debug("Exit status = 0");
                StringUtils.writeText(Messages.GOOD_BY.getMessage());
                System.exit(0);
            }

            StringUtils.writeText(Messages.PATH_SUCCESSFULLY.getMessage());
        } catch (IOException e) {
            LOGGER.error(LoggerMessage.ENTER_NOT_VALID_VALUE.getName(), e);
            StringUtils.writeErrorText(Messages.INVALID_ENTER_PATH.getMessage());
        }
        return inputString;

    }

}
