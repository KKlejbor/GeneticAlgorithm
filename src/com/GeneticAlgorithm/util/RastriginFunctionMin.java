package com.GeneticAlgorithm.util;

public class RastriginFunctionMin extends RastriginFunction{
    public RastriginFunctionMin(int amplitude, int numberOfVariables) {
        super(amplitude, numberOfVariables);
    }

    @Override
    public Double apply(Double[] X) {
        return -super.apply(X);
    }
}
