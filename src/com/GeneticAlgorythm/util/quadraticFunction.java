package com.GeneticAlgorythm.util;

public class quadraticFunction implements Function {
    @Override
    public double calculateValue(Double[] X) {
        return -Math.pow(X[0], 2) - Math.pow(X[1], 2) + 2;
    }
}
