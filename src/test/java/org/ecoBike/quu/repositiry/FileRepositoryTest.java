package org.ecoBike.quu.repositiry;

import org.ecoBike.quu.model.Bike;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FileRepositoryTest {
    private static final String PATH = "src/test/java/org/ecoBike/quu/data/ecobike.txt";
    @InjectMocks
    FileRepository fileRepository;

    @Test
    void shouldReturnValidListSizeOfAllBike() throws IOException {
        List<Bike> bikes = fileRepository.readAll(PATH);
        assertThat(bikes).isNotEmpty().hasSize(9);
    }

    @Test
    void shouldReturnValidListSizeOfFoldingBike() throws IOException {
        List<Bike> bikes = fileRepository.readFoldingBike(PATH);
        assertThat(bikes).isNotEmpty().hasSize(3);
    }

    @Test
    void shouldReturnValidListSizeOfElectroBike() throws IOException {
        List<Bike> bikes = fileRepository.readElectroBike(PATH);
        assertThat(bikes).isNotEmpty().hasSize(3);
    }

    @Test
    void shouldReturnValidListSizeOfSpeedelecBike() throws IOException {
        List<Bike> bikes = fileRepository.readSpeedelecBike(PATH);
        assertThat(bikes).isNotEmpty().hasSize(3);
    }

}