package org.ecoBike.quu.factory;

import org.ecoBike.quu.model.*;

import java.util.List;

/**
 * Created by Shelupets Denys on 24.06.2020.
 */
public class FactoryBikes {

    public static Bike getBike(TypeBike typeBike, List<String> listParameters) throws RuntimeException {
        if (TypeBike.SPEEDELEC.getName().equals(typeBike.getName())) {
            return newSpeedelecBike(listParameters);
        } else if (TypeBike.E_BIKE.getName().equals(typeBike.getName())) {
            return newElectroBike(listParameters);
        } else if (TypeBike.FOLDING_BIKE.getName().equals(typeBike.getName())) {
            return newFoldingBike(listParameters);
        }
        return null;


    }

    private static FoldingBike newFoldingBike(List<String> listParameters) {
        return new FoldingBike.Builder(listParameters.get(0))
                .sizeWheels(Integer.parseInt(listParameters.get(1)))
                .numberGears(Integer.parseInt(listParameters.get(2)))
                .weightBike(Integer.parseInt(listParameters.get(3)))
                .isPresenceLights(Boolean.parseBoolean(listParameters.get(4)))
                .color(listParameters.get(5))
                .price(Integer.parseInt(listParameters.get(6)))
                .build();
    }

    private static ElectroBike newElectroBike(List<String> listParameters) {
        return new ElectroBike.Builder(listParameters.get(0))
                .maximumSpeed(Integer.parseInt(listParameters.get(1)))
                .weightBike(Integer.parseInt(listParameters.get(2)))
                .isPresenceLights(Boolean.parseBoolean(listParameters.get(3)))
                .batteryCapacity(Integer.parseInt(listParameters.get(4)))
                .color(listParameters.get(5))
                .price(Integer.parseInt(listParameters.get(6)))
                .build();
    }

    private static SpeedelecBike newSpeedelecBike(List<String> listParameters) {
        return new SpeedelecBike.Builder(listParameters.get(0))
                .maximumSpeed(Integer.parseInt(listParameters.get(1)))
                .weightBike(Integer.parseInt(listParameters.get(2)))
                .isPresenceLights(Boolean.parseBoolean(listParameters.get(3)))
                .batteryCapacity(Integer.parseInt(listParameters.get(4)))
                .color(listParameters.get(5))
                .price(Integer.parseInt(listParameters.get(6)))
                .build();
    }
}
