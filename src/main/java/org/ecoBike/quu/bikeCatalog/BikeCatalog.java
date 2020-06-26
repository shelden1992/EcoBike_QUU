package org.ecoBike.quu.bikeCatalog;

import lombok.Getter;
import org.ecoBike.quu.model.Bike;
import org.ecoBike.quu.utils.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.ecoBike.quu.utils.FileUtils.readAllBike;

/**
 * Created by Shelupets Denys on 24.06.2020.
 */
@Getter
public class BikeCatalog implements Catalog {
    private static final List<Bike> ALL_WRITE_BIKES = new ArrayList<>();
    private static BikeCatalog instance;

    private BikeCatalog() {
    }

    public static synchronized BikeCatalog getInstance() {
        if (instance == null) {
            instance = new BikeCatalog();
        }
        return instance;
    }

    @Override
    public void showWriteBikeCatalog() throws IOException {
        getWritBikeCatalog()
                .forEach(Bike -> StringUtils.writeText(Bike.showInfoForBike()));
    }

    @Override
    public void addNewBikeToTempCatalog(Bike bike) {
    }

    @Override
    public void writeAndClearTempCatalog() throws IOException {
    }

    @Override
    public List<Bike> getWritBikeCatalog() throws IOException {
        return readAllBike().values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<Bike> getTempCatalog() {
        return null;
    }

    @Override
    public Boolean tempCatalogIsEmpty() {
        return null;
    }

}
