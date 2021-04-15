package com.GeneticAlgorythm.util;

public class rastriginFunction implements Function{
    int A,N;

    public rastriginFunction(int amplitude, int numberOfVariables) {
        A = amplitude;
        N = numberOfVariables;
    }

    public rastriginFunction() {
    }

    @Override
    public double calculateValue(Double[] X) {
        double result = A * N;
        for (int i = 0; i < N; i++) {
            result += Math.pow(X[i], 2) - A * Math.cos(2 * Math.PI * X[i]);
        }
        return result;
    }
}
