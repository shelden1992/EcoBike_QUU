package org.ecoBike.quu.factory;

import org.ecoBike.quu.model.Bike;
import org.ecoBike.quu.model.ElectroBike;
import org.ecoBike.quu.model.FoldingBike;
import org.ecoBike.quu.model.SpeedelecBike;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


class FactoryBikesTest {

    @Test
    void shouldReturnFoldingBikeWhenValidArgument() {
        FoldingBike foldingBike = FoldingBike.newBuilder()
                .brand("Brand")
                .sizeWheels(10)
                .numberGears(10)
                .weightBike(2000)
                .isPresenceLights(false)
                .color("red")
                .price(100)
                .build();
        Bike bike = FactoryBikes.getBike(FoldingBike.class, Arrays.asList("Brand", "10", "10", "2000", "false", "red", "100"));

        assertThat(bike).isEqualTo(foldingBike);

    }

    @Test
    void shouldReturnElectroBikeWhenValidArgument() {
        ElectroBike electroBike = ElectroBike.newBuilder()
                .brand("Brand")
                .maximumSpeed(30)
                .weightBike(2000)
                .isPresenceLights(false)
                .batteryCapacity(20000)
                .color("Dark red")
                .price(100)
                .build();
        Bike bike = FactoryBikes.getBike(ElectroBike.class, Arrays.asList("Brand", "30", "2000", "false", "20000", "Dark red", "100"));

        assertThat(bike).isEqualTo(electroBike);

    }

    @Test
    void shouldReturnSpeedelecBikeWhenValidArgument() {
        SpeedelecBike speedelecBike = SpeedelecBike.newBuilder()
                .brand("Brand")
                .maximumSpeed(30)
                .weightBike(2000)
                .isPresenceLights(true)
                .batteryCapacity(20000)
                .color("Green")
                .price(100)
                .build();
        Bike bike = FactoryBikes.getBike(SpeedelecBike.class, Arrays.asList("Brand", "30", "2000", "true", "20000", "Green", "100"));

        assertThat(bike).isEqualTo(speedelecBike);

    }
}