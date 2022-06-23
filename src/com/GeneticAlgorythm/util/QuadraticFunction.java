package com.GeneticAlgorythm.util;

import java.util.function.Function;

public class QuadraticFunction implements Function<Double[], Double> {
    @Override
    public Double apply(Double[] X) {
        return -Math.pow(X[0], 2) - Math.pow(X[1], 2) + 2;
    }
}
