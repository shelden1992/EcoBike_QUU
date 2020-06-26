package org.ecoBike.quu.bikeCatalog;

import lombok.Getter;
import org.ecoBike.quu.model.Bike;
import org.ecoBike.quu.model.TypeBike;
import org.ecoBike.quu.utils.FileUtils;
import org.ecoBike.quu.utils.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.ecoBike.quu.utils.FileUtils.readAllBike;

/**
 * Created by Shelupets Denys on 25.06.2020.
 */
@Getter
public class FoldinBikeCatalog implements Catalog {
    private static final List<Bike> FOLDING_BIKE_TEMP_CATALOG = new ArrayList<>();
    private static FoldinBikeCatalog instance;


    private FoldinBikeCatalog() {
    }

    public static synchronized FoldinBikeCatalog getInstance() {
        if (instance == null) {
            instance = new FoldinBikeCatalog();
        }
        return instance;
    }

    @Override
    public void addNewBikeToTempCatalog(Bike bike) {
        FOLDING_BIKE_TEMP_CATALOG.add(bike);
    }

    @Override
    public void writeAndClearTempCatalog() throws IOException {
        FileUtils.writeToFile(FOLDING_BIKE_TEMP_CATALOG);
        FOLDING_BIKE_TEMP_CATALOG.clear();
    }

    @Override
    public List<Bike> getWritBikeCatalog() throws IOException {
        return readAllBike().get(TypeBike.FOLDING_BIKE);
    }

    @Override
    public List<Bike> getTempCatalog() {
        return FOLDING_BIKE_TEMP_CATALOG;
    }

    @Override
    public Boolean tempCatalogIsEmpty() {
        return FOLDING_BIKE_TEMP_CATALOG.isEmpty();
    }

    @Override
    public void showWriteBikeCatalog() throws IOException {
        getWritBikeCatalog()
                .forEach(Bike -> StringUtils.writeText(Bike.showInfoForBike()));
    }
}
