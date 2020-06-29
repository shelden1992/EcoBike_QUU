package org.ecoBike.quu.services;

import org.ecoBike.quu.model.Bike;

import java.io.IOException;
import java.util.List;

public interface Catalog {

    void writeTempList(String path) throws IOException;

    List<Bike> getTempList();

    void clearTempList();

    Boolean tempListIsEmpty();

    void showWriteBikeCatalog(String path) throws IOException, NumberFormatException;

    List<Bike> getWriteList(String path) throws IOException;

    void addBike(Class classBike) throws NumberFormatException;

    void findOne(String path) throws IOException, NumberFormatException;

    List<Bike> readAll(String path) throws IOException, NumberFormatException;
}
