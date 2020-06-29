package org.ecoBike.quu.repositiry;

import org.ecoBike.quu.model.Bike;

import java.io.IOException;
import java.util.List;

/**
 * Created by Shelupets Denys on 27.06.2020.
 */
public interface Repository {
    List<Bike> readAll(String path) throws IOException, NumberFormatException;

    List<Bike> readFoldingBike(String path) throws IOException, NumberFormatException;

    List<Bike> readElectroBike(String path) throws IOException, NumberFormatException;

    List<Bike> readSpeedelecBike(String path) throws IOException, NumberFormatException;

    void writeToFile(String path) throws IOException;

    void addBikeToTempList(Bike bike);

    List<Bike> getTempList();

    List<Bike> getWriteBike(String path) throws IOException;
}
