package org.ecoBike.quu.services;

import org.ecoBike.quu.model.Bike;

import java.io.IOException;
import java.util.List;

public interface Catalog {

    void addBikeToTempList(Bike bike);

    void writeAndClearTempList() throws IOException;

    List<Bike> getTempList();

    Boolean tempListIsEmpty();


    void setPath();

    void showWriteBikeCatalog() throws IOException, NumberFormatException;
}
