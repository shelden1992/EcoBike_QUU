package org.ecoBike.quu.services;

import org.ecoBike.quu.model.Bike;

import java.util.List;

/**
 * Created by Shelupets Denys on 28.06.2020.
 */
public class FindService {
    Bike bike;

    public FindService(Bike bike) {
        this.bike = bike;

    }

    void findBike(List<Bike> list, int numberThreads) {
        int sizeOfa = list.size();
        int range = sizeOfa / numberThreads;
        for (int i = 0; i <= numberThreads - 1; i++) {
            Thread searcher;
            if (i == numberThreads - 1) {
                searcher = new Thread(new Searcher(list, bike));
            } else {
                searcher = new Thread(new Searcher(list.subList(i * range, i * range + range - 1), bike));
            }
            searcher.start();
        }

    }

    void findBike(List<Bike> list) {
        findBike(list, 5);
    }
}