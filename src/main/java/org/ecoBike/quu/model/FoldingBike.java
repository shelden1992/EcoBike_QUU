package org.ecoBike.quu.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by Shelupets Denys on 23.06.2020.
 */

@Getter
@EqualsAndHashCode(callSuper = true)
public class FoldingBike extends Bike {
    private String brand;
    private int sizeWheels;
    private int numberGears;
    private int weightBike;
    private boolean presenceLights;
    private String color;
    private int price;


    private FoldingBike() {
    }


    public static Builder newBuilder() {
        return new FoldingBike().new Builder();
    }

    @Override
    public String writeFormat() {
        return String.format("\nFOLDING BIKE %s; %d; %d; %d; %s; %s; %d", getBrand(), getSizeWheels(), getNumberGears(), getWeightBike(), isPresenceLights(), getColor(), getPrice());
    }

    @Override
    public String toString() {
        String light = isPresenceLights() ? "" : " no";
        return String.format("FOLDING BIKE %s with %d gear(s) and%s head/tail light.\n" +
                "Price: %d euros.", getBrand(), getNumberGears(), light, getPrice());
    }

    public class Builder {

        private Builder() {

        }

        public Builder brand(String brand) {
            FoldingBike.this.brand = brand;
            return this;
        }

        public Builder sizeWheels(int sizeWheels) {
            FoldingBike.this.sizeWheels = sizeWheels;
            return this;
        }

        public Builder numberGears(int numberGears) {
            FoldingBike.this.numberGears = numberGears;

            return this;
        }

        public Builder weightBike(int weightBike) {
            FoldingBike.this.weightBike = weightBike;

            return this;
        }

        public Builder isPresenceLights(boolean presenceLights) {
            FoldingBike.this.presenceLights = presenceLights;

            return this;
        }


        public Builder color(String color) {
            FoldingBike.this.color = color;

            return this;
        }

        public Builder price(int price) {
            FoldingBike.this.price = price;

            return this;
        }


        public FoldingBike build() {
            return FoldingBike.this;
        }
    }
}
