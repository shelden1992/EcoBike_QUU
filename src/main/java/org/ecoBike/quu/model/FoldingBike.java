package org.ecoBike.quu.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Shelupets Denys on 23.06.2020.
 */

@Getter
@Setter
@EqualsAndHashCode
public class FoldingBike extends Bike implements Comparable {
    private int sizeWheels;
    private int numberGears;


    private FoldingBike(Builder builder) {
        super(builder.brand, builder.weightBike, builder.presenceLights, builder.color, builder.price);
        this.sizeWheels = builder.sizeWheels;
        this.numberGears = builder.numberGears;
    }

    @Override
    public String showInfoForBike() {
        String light = isPresenceLights() ? "" : " no";
        return String.format("FOLDING BIKE %s with %d gear(s) and%s head/tail light.\n" +
                "Price: %d euros.", getBrand(), getNumberGears(), light, getPrice());
    }

    @Override
    public String toString() {
        return String.format("\nFOLDING BIKE %s; %d; %d; %d; %s; %s; %d", getBrand(), getSizeWheels(), getNumberGears(), getWeightBike(), isPresenceLights(), getColor(), getPrice());
    }

    @Override
    public int compareTo(Object o) {
        return 1;
    }

    public static class Builder {
        private String brand;
        private int sizeWheels;
        private int numberGears;
        private int weightBike;
        private boolean presenceLights;
        private String color;
        private int price;

        public Builder(String brand) {
            this.brand = brand;
        }

        public Builder sizeWheels(int sizeWheels) {
            this.sizeWheels = sizeWheels;

            return this;
        }

        public Builder numberGears(int numberGears) {
            this.numberGears = numberGears;

            return this;
        }

        public Builder weightBike(int weightBike) {
            this.weightBike = weightBike;

            return this;
        }

        public Builder isPresenceLights(boolean presenceLights) {
            this.presenceLights = presenceLights;

            return this;
        }


        public Builder color(String color) {
            this.color = color;

            return this;
        }

        public Builder price(int price) {
            this.price = price;

            return this;
        }


        public FoldingBike build() {
            return new FoldingBike(this);
        }
    }
}
