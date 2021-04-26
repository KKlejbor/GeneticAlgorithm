package com.GeneticAlgorythm.exercises.laboratory07;

import com.GeneticAlgorythm.util.RastriginFunctionMin;

import java.util.function.Function;

public class Exercise01L07 {
    static final Function<Double[], Double> f = new RastriginFunctionMin(10,10);
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
        }
    }
}
