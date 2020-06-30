//package org.ecoBike.quu.services;
//
//import org.ecoBike.quu.model.FoldingBike;
//import org.ecoBike.quu.repositiry.Repository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.junit.jupiter.MockitoSettings;
//import org.mockito.quality.Strictness;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.catchThrowable;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.when;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//class BikeCatalogServiceTest {
//    private static final FoldingBike BIKE = FoldingBike.newBuilder()
//            .brand("Brand")
//            .sizeWheels(10)
//            .numberGears(10)
//            .weightBike(2000)
//            .isPresenceLights(false)
//            .color("red")
//            .price(100)
//            .build();
//    private static final String PATH = "src/test/java/org/ecoBike/quu/data/ecobike.txt";
//    @InjectMocks
//    BikeCatalogService bikeCatalog;
//    @Mock
//    Repository repository;
//    @Mock
//    InputBikeService inputBikeService;
//
//    @Test
//    void shouldWriteFileWhenTempListNotEmpty() throws IOException {
//        when(repository.getTempList()).thenReturn(new ArrayList<>() {{
//            add(BIKE);
//        }});
//        bikeCatalog.writeTempList(PATH);
//        verify(repository, times(1)).writeToFile(PATH);
//    }
//
//    @Test
//    void shouldReturnTempList() {
//        bikeCatalog.getTempList();
//        verify(repository, times(1)).getTempList();
//    }
//
//    @Test
//    void shouldReturnTrueIfTrueListIsEmpty() {
//        when(repository.getTempList()).thenReturn(new ArrayList<>());
//        Boolean isEmpty = bikeCatalog.tempListIsEmpty();
//        assertThat(isEmpty).isTrue();
//        verify(repository, times(1)).getTempList();
//    }
//
//    @Test
//    void shouldAddBikeToTempListWhenInputServiceNotThrowException() {
//        when(inputBikeService.getNewBike(any())).thenReturn(BIKE);
//        bikeCatalog.addBike(FoldingBike.class);
//        verify(repository).addBikeToTempList(BIKE);
//    }
//
//    @Test
//    void shouldNotAddBikeToTempListWhenInputServiceThrowException() {
//        when(inputBikeService.getNewBike(any())).thenThrow(new NumberFormatException());
//
//        Throwable thrown = catchThrowable(() -> {
//            bikeCatalog.addBike(any());
//        });
//
//        assertThat(thrown).isInstanceOf(NumberFormatException.class)
//                .hasStackTraceContaining("NumberFormatException");
//        verify(inputBikeService).getNewBike(any());
//    }
//
//    @Test
//    void testReadAll() throws IOException {
//        bikeCatalog.readAll(any());
//        verify(repository, times(1)).readAll(any());
//    }
//
//}