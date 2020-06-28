package org.ecoBike.quu.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by Shelupets Denys on 23.06.2020.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class ElectroBike extends Bike {
    private String brand;
    private int maximumSpeed;
    private int weightBike;
    private boolean presenceLights;
    private int batteryCapacity;
    private String color;
    private int price;


    public ElectroBike() {
        super();
    }

    public static Builder newBuilder() {
        return new ElectroBike().new Builder();
    }

    @Override
    public String toString() {
        String light = isPresenceLights() ? "" : " no";
        return String.format("E-BIKE %s with %d mAh battery and%s head/tail light.\n" +
                "Price: %d euros.", getBrand(), getBatteryCapacity(), light, getPrice());

    }

    @Override
    public String writeFormat() {
        return String.format("\nE-BIKE %s; %d; %d; %s; %d; %s; %d", getBrand(), getMaximumSpeed(), getWeightBike(), isPresenceLights(), getBatteryCapacity(), getColor(), getPrice());
    }

    public class Builder {


        private Builder() {

        }

        public Builder brand(String brand) {
            ElectroBike.this.brand = brand;

            return this;
        }

        public Builder weightBike(int weightBike) {
            ElectroBike.this.weightBike = weightBike;

            return this;
        }

        public Builder isPresenceLights(boolean presenceLights) {
            ElectroBike.this.presenceLights = presenceLights;

            return this;
        }

        public Builder batteryCapacity(int batteryCapacity) {
            ElectroBike.this.batteryCapacity = batteryCapacity;

            return this;
        }

        public Builder color(String color) {
            ElectroBike.this.color = color;

            return this;
        }

        public Builder price(int price) {
            ElectroBike.this.price = price;

            return this;
        }

        public Builder maximumSpeed(int maximumSpeed) {
            ElectroBike.this.maximumSpeed = maximumSpeed;

            return this;
        }

        public ElectroBike build() {
            return ElectroBike.this;
        }
    }
}
