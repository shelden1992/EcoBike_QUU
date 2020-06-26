package org.ecoBike.quu.bikeCatalog;

import org.ecoBike.quu.model.Bike;

import java.io.IOException;
import java.util.List;

public interface Catalog {
    void showWriteBikeCatalog() throws IOException;

    void addNewBikeToTempCatalog(Bike bike);

    void writeAndClearTempCatalog() throws IOException;

    List<Bike> getWritBikeCatalog() throws IOException;

    List<Bike> getTempCatalog();

    Boolean tempCatalogIsEmpty();
}
