package org.ecoBike.quu.bikes;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by Shelupets Denys on 23.06.2020.
 */
@Getter
@EqualsAndHashCode
public class ElectroBike extends Bike {
    private final int maximumSpeed;
    private final int batteryCapacity;


    private ElectroBike(Builder builder) {
        super(builder.brand, builder.weightBike, builder.presenceLights, builder.color, builder.price);
        this.maximumSpeed = builder.maximumSpeed;
        this.batteryCapacity = builder.batteryCapacity;
    }

    @Override
    public String toString() {
        return String.format("\nE-BIKE %s; %d; %d; %s; %d; %s; %d", getBrand(), getMaximumSpeed(), getWeightBike(), isPresenceLights(), getBatteryCapacity(), getColor(), getPrice());
    }

    @Override
    public String showInfoForBike() {
        String light = isPresenceLights() ? "" : " no";
        return String.format("E-BIKE %s with %d mAh battery and%s head/tail light.\n" +
                "Price: %d euros.", getBrand(), getBatteryCapacity(), light, getPrice());
    }

    public static class Builder {
        private String brand;
        private int maximumSpeed;
        private int weightBike;
        private boolean presenceLights;
        private int batteryCapacity;
        private String color;
        private int price;

        public Builder(String brand) {
            this.brand = brand;
        }


        public Builder weightBike(int weightBike) {
            this.weightBike = weightBike;

            return this;
        }

        public Builder isPresenceLights(boolean presenceLights) {
            this.presenceLights = presenceLights;

            return this;
        }

        public Builder batteryCapacity(int batteryCapacity) {
            this.batteryCapacity = batteryCapacity;

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

        public Builder maximumSpeed(int maximumSpeed) {
            this.maximumSpeed = maximumSpeed;

            return this;
        }

        public ElectroBike build() {
            return new ElectroBike(this);
        }
    }
}
