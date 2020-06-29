package org.ecoBike.quu.services;

import org.ecoBike.quu.messages.Messages;
import org.ecoBike.quu.model.Bike;
import org.ecoBike.quu.utils.StringUtils;

import java.util.List;

/**
 * Created by Shelupets Denys on 28.06.2020.
 */
public class Searcher extends Thread {
    List<Bike> listToSearchIn;
    Bike searchBike;

    public Searcher(List<Bike> ListToSearchIn, Bike searchBike) {
        this.listToSearchIn = ListToSearchIn;
        this.searchBike = searchBike;
    }


    @Override
    public void run() {
        if (findBike()) {
            StringUtils.writeFormatText(Messages.FOUND_BIKE.getMessage(), currentThread().getName());
        } else {
            StringUtils.writeFormatText(Messages.NOT_FOUND_BIKE.getMessage(), currentThread().getName());
        }
    }


    private boolean findBike() {
        return listToSearchIn.stream().anyMatch(bike -> bike.equals(searchBike));
    }
}
