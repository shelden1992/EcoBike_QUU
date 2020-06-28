package org.ecoBike.quu.services;

import org.ecoBike.quu.messages.LoggerMessage;
import org.ecoBike.quu.model.Bike;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Shelupets Denys on 27.06.2020.
 */
public class AddingBikeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddingBikeService.class);
    private final Catalog catalog;
    private final InputBikeService inputBikeService;


    public AddingBikeService(Catalog catalog) {
        this.catalog = catalog;
        this.inputBikeService = new InputBikeService();
    }

    public void addBikeToCatalog(Class classBike) throws NumberFormatException {
        Bike newBike = inputBikeService.getNewBike(classBike);
        catalog.addBikeToTempList(newBike);
        LOGGER.info(LoggerMessage.ADD_NEW_FOLDING_BIKE.getName());
    }
}
