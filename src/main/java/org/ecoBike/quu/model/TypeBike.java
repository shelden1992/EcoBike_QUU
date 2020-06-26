package org.ecoBike.quu.model;

import lombok.Getter;

/**
 * Created by Shelupets Denys on 23.06.2020.
 */
@Getter
public enum TypeBike {
    SPEEDELEC("SPEEDELEC"), E_BIKE("E-BIKE"), FOLDING_BIKE("FOLDING BIKE");
    private final String name;

    TypeBike(String name) {
        this.name = name;
    }

}
