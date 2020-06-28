package org.ecoBike.quu.services;

import lombok.Getter;
import org.ecoBike.quu.messages.Messages;
import org.ecoBike.quu.model.Bike;
import org.ecoBike.quu.repositiry.Repository;
import org.ecoBike.quu.utils.StringUtils;

import java.io.IOException;
import java.util.List;


/**
 * Created by Shelupets Denys on 24.06.2020.
 */
@Getter
public class BikeCatalogService implements Catalog {
    private Repository repository;


    public BikeCatalogService(Repository repository) {
        this.repository = repository;
    }


    @Override
    public void addBikeToTempList(Bike bike) {
        repository.addBikeToTempList(bike);
    }

    @Override
    public void writeAndClearTempList() throws IOException {

        if (tempListIsEmpty()) {
            StringUtils.writeText(Messages.NOTHING_WRITE.getMessage());
        } else {
            repository.writeToFile();
            repository.getTempList().clear();
            StringUtils.writeText(Messages.WRITE_SUCCESSFULLY.getMessage());
        }
    }


    @Override
    public List<Bike> getTempList() {
        return repository.getTempList();
    }

    @Override
    public Boolean tempListIsEmpty() {
        return getTempList().isEmpty();
    }

    @Override
    public void setPath() {
        repository.setPath();
    }

    @Override
    public void showWriteBikeCatalog() throws IOException, NumberFormatException {
        if (repository.getWriteBike().isEmpty()) {
            repository.readAll();
        }
        repository.getWriteBike().forEach(Bike -> StringUtils.writeText(Bike.toString()));

    }

}
