package org.ecoBike.quu.model;

import lombok.*;

import java.util.StringJoiner;

/**
 * Created by Shelupets Denys on 23.06.2020.
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
abstract public class Bike {
    private String brand;
    private int weightBike;
    private boolean presenceLights;
    private String color;
    private int price;


    @Override
    public String toString() {
        return new StringJoiner(", ", Bike.class.getSimpleName() + "[", "]")
                .add("brand='" + brand + "'")
                .add("weightBike=" + weightBike)
                .add("presenceLights=" + presenceLights)
                .add("color='" + color + "'")
                .add("price=" + price)
                .toString();
    }

    public abstract String showInfoForBike();


}
