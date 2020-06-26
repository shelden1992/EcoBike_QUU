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
public class SpeedelecBikeCatalog implements Catalog {
    private static final List<Bike> SPEEDELEC_BIKE_TEMP_CATALOG = new ArrayList<>();
    private static SpeedelecBikeCatalog instance;


    private SpeedelecBikeCatalog() {

    }

    public static synchronized SpeedelecBikeCatalog getInstance() {
        if (instance == null) {
            instance = new SpeedelecBikeCatalog();
        }
        return instance;
    }

    @Override
    public void addNewBikeToTempCatalog(Bike bike) {
        SPEEDELEC_BIKE_TEMP_CATALOG.add(bike);
    }

    @Override
    public void writeAndClearTempCatalog() throws IOException {
        FileUtils.writeToFile(SPEEDELEC_BIKE_TEMP_CATALOG);
        SPEEDELEC_BIKE_TEMP_CATALOG.clear();
    }

    @Override
    public List<Bike> getWritBikeCatalog() throws IOException {
        return readAllBike().get(TypeBike.SPEEDELEC);
    }

    @Override
    public List<Bike> getTempCatalog() {
        return SPEEDELEC_BIKE_TEMP_CATALOG;
    }

    @Override
    public Boolean tempCatalogIsEmpty() {
        return SPEEDELEC_BIKE_TEMP_CATALOG.isEmpty();
    }

    @Override
    public void showWriteBikeCatalog() throws IOException {
        getWritBikeCatalog()
                .forEach(Bike -> StringUtils.writeText(Bike.showInfoForBike()));
    }
}
