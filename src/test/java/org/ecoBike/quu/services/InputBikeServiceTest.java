package org.ecoBike.quu.services;

import org.ecoBike.quu.model.Bike;
import org.ecoBike.quu.model.ElectroBike;
import org.ecoBike.quu.model.FoldingBike;
import org.ecoBike.quu.model.SpeedelecBike;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.InputMismatchException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InputBikeServiceTest {
    private static final FoldingBike FOLDING_BIKE = FoldingBike.newBuilder()
            .brand("Brand")
            .sizeWheels(10)
            .numberGears(10)
            .weightBike(2000)
            .isPresenceLights(false)
            .color("red")
            .price(100)
            .build();

    private static final ElectroBike ELECTRO_BIKE = ElectroBike.newBuilder()
            .brand("Brand")
            .maximumSpeed(30)
            .weightBike(2000)
            .isPresenceLights(true)
            .batteryCapacity(20000)
            .color("Dark green")
            .price(100)
            .build();


    @InjectMocks
    InputBikeService inputBikeService;
    @Mock
    BufferedReader reader;

    @Test
    void shouldReturnBikeWhenInputValidDataForFoldingBike() throws IOException {
        when(reader.readLine())
                .thenReturn("Brand")
                .thenReturn("10")
                .thenReturn("10")
                .thenReturn("2000")
                .thenReturn("n")
                .thenReturn("red")
                .thenReturn("100")
                .thenReturn("y");
        Bike newBike = inputBikeService.getNewBike(FoldingBike.class);
        assertThat(newBike).isNotNull().isEqualTo(FOLDING_BIKE);
    }

    @Test
    void shouldReturnBikeWhenInputValidDataForElectroAndSpeedelecBike() throws IOException {
        when(reader.readLine())
                .thenReturn("Brand")
                .thenReturn("30")
                .thenReturn("2000")
                .thenReturn("y")
                .thenReturn("20000")
                .thenReturn("Dark green")
                .thenReturn("100")
                .thenReturn("y");
        Bike newBike = inputBikeService.getNewBike(ElectroBike.class);
        assertThat(newBike).isNotNull().isEqualTo(ELECTRO_BIKE);
    }

    @Test
    void shouldThrowExceptionWhenInputNotValidParameterFoldingBike() throws IOException {
        when(reader.readLine()).thenReturn("Brand", "BadCase").thenThrow(InputMismatchException.class);
        Throwable thrown = catchThrowable(() -> {
            inputBikeService.getNewBike(FoldingBike.class);
        });
        assertThat(thrown).isInstanceOf(InputMismatchException.class)
                .hasStackTraceContaining("InputMismatchException");
    }

    @Test
    void shouldReturnElectroBikeClassWhenInputNumberOne() throws IOException {
        when(reader.readLine()).thenReturn("1");
        Class bikeType = inputBikeService.findBikeType();
        assertNotNull(bikeType);
        assertEquals(ElectroBike.class, bikeType);
    }

    @Test
    void shouldReturnFoldingBikeClassWhenInputNumberTwo() throws IOException {
        when(reader.readLine()).thenReturn("2");
        Class bikeType = inputBikeService.findBikeType();
        assertNotNull(bikeType);
        assertEquals(FoldingBike.class, bikeType);
    }

    @Test
    void shouldReturnSpeedelecBikeWhenInputNumberThree() throws IOException {
        when(reader.readLine()).thenReturn("3");
        Class bikeType = inputBikeService.findBikeType();
        assertNotNull(bikeType);
        assertEquals(SpeedelecBike.class, bikeType);
    }

}