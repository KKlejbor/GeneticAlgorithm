package com.GeneticAlgorithm.util;

import java.util.function.Function;

public class RastriginFunction implements Function<Double[], Double>{
    int A,N;

    public RastriginFunction(int amplitude, int numberOfVariables) {
        A = amplitude;
        N = numberOfVariables;
    }

    @Override
    public Double apply(Double[] X) {
        double result = A * N;
        for (int i = 0; i < N; i++) {
            result += Math.pow(X[i], 2) - A * Math.cos(2 * Math.PI * X[i]);
        }
        return result;
    }
}
