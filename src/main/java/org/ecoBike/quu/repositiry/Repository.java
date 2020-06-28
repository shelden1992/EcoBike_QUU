package org.ecoBike.quu.repositiry;

import org.ecoBike.quu.model.Bike;

import java.io.IOException;
import java.util.List;

/**
 * Created by Shelupets Denys on 27.06.2020.
 */
public interface Repository {
    List<Bike> readAll() throws IOException, NumberFormatException;

    List<Bike> readFoldingBike() throws IOException;

    List<Bike> readElectroBike() throws IOException;

    List<Bike> readSpeedelecBike() throws IOException;

    void writeToFile() throws IOException;

    void setPath();

    void addBikeToTempList(Bike bike);

    List<Bike> getTempList();

    List<Bike> getWriteBike();
}
