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
    private InputBikeService inputService;

    public BikeCatalogService(Repository repository, InputBikeService inputService) {
        this.repository = repository;
        this.inputService = inputService;
    }

    @Override
    public void writeTempList(String path) throws IOException {

        if (tempListIsEmpty()) {
            StringUtils.writeText(Messages.NOTHING_WRITE.getMessage());
        } else {
            repository.writeToFile(path);
            StringUtils.writeText(Messages.WRITE_SUCCESSFULLY.getMessage());
        }
    }


    @Override
    public List<Bike> getTempList() {
        return repository.getTempList();
    }

    @Override
    public void clearTempList() {
        repository.getTempList().clear();
    }

    @Override
    public Boolean tempListIsEmpty() {
        return getTempList().isEmpty();
    }


    @Override
    public void showWriteBikeCatalog(String path) throws IOException, NumberFormatException {
        getWriteList(path).forEach(Bike -> StringUtils.writeText(Bike.toString()));
    }

    public List<Bike> getWriteList(String path) throws IOException {
        return repository.getWriteBike(path);
    }

    @Override
    public void addBike(Class classBike) throws NumberFormatException {
        repository.addBikeToTempList(inputService.getNewBike(classBike));
        StringUtils.writeText(Messages.ADDED_SUCCESSFULLY.getMessage());
    }

    @Override
    public void findOne(String path) throws IOException, NumberFormatException {
        Class classBike = inputService.findBikeType();
        Bike newBike = inputService.getNewBike(classBike);
        new FindService(newBike).findBike(getWriteList(path));
    }

    @Override
    public List<Bike> readAll(String path) throws IOException, NumberFormatException {
        return repository.readAll(path);
    }

}
