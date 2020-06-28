package org.ecoBike.quu.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Shelupets Denys on 23.06.2020.
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class SpeedelecBike extends Bike {
    private String brand;
    private int maximumSpeed;
    private int weightBike;
    private boolean presenceLights;
    private int batteryCapacity;
    private String color;
    private int price;

    public SpeedelecBike() {
    }

    public static Builder newBuilder() {
        return new SpeedelecBike().new Builder();
    }

    @Override
    public String writeFormat() {
        return String.format("\nSPEEDELEC %s; %d; %d; %s; %d; %s; %d", getBrand(), getMaximumSpeed(), getWeightBike(), isPresenceLights(), getBatteryCapacity(), getColor(), getPrice());
    }

    @Override
    public String toString() {
        String light = isPresenceLights() ? "" : " no";
        return String.format("SPEEDELEC %s with %d mAh battery and%s head/tail light.\n" +
                "Price: %d euros.", getBrand(), getBatteryCapacity(), light, getPrice());
    }

    public class Builder {


        private Builder() {

        }

        public Builder brand(String brand) {
            SpeedelecBike.this.brand = brand;

            return this;
        }

        public Builder weightBike(int weightBike) {
            SpeedelecBike.this.weightBike = weightBike;

            return this;
        }

        public Builder isPresenceLights(boolean presenceLights) {
            SpeedelecBike.this.presenceLights = presenceLights;

            return this;
        }

        public Builder batteryCapacity(int batteryCapacity) {
            SpeedelecBike.this.batteryCapacity = batteryCapacity;

            return this;
        }

        public Builder color(String color) {
            SpeedelecBike.this.color = color;

            return this;
        }

        public Builder price(int price) {
            SpeedelecBike.this.price = price;

            return this;
        }

        public Builder maximumSpeed(int maximumSpeed) {
            SpeedelecBike.this.maximumSpeed = maximumSpeed;

            return this;
        }

        public SpeedelecBike build() {
            return SpeedelecBike.this;
        }
    }
}
