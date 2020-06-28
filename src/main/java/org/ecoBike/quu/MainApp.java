package org.ecoBike.quu;


import org.ecoBike.quu.repositiry.FileRepository;
import org.ecoBike.quu.services.BikeCatalogService;
import org.ecoBike.quu.view.Menu;

/**
 * Created by Shelupets Denys on 23.06.2020.
 */
public class MainApp {

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        new Menu(new BikeCatalogService(new FileRepository()))
                .startMenu();
    }
}