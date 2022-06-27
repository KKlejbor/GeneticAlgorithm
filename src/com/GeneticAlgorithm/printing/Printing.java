package com.GeneticAlgorithm.printing;

import com.GeneticAlgorithm.genetic_algorithm.GeneticAlgorithm;
import java.util.function.Function;

public class Printing {
    public static void printBinaryArray(Integer[] tab) {
        for (Integer integer : tab) {
            System.out.print(integer);
        }
        System.out.println();
    }

    public static void printChromosomes(Integer[][] population) {
        for (int i = 0; i < population.length; i++) {
            printBinaryArray(population[i]);
        }
    }

    public static void printFunctionValue(Double[] X, Double Y) {
        System.out.printf("F(X) = %f\n", Y);
        System.out.println("Dla:");
        for (int i = 0; i < X.length; i++) {
            System.out.printf("\tx%d = %f\n", i, X[i]);
        }
    }

    public static void printValues(Integer[][] population, GeneticAlgorithm algorithm, Function<Double[], Double> f) {
        for (int i = 0; i < algorithm.getPopulationSize(); i++) {
            Double[] X = algorithm.decodeChromosome(population[i]);
            printFunctionValue(X,f.apply(X));
        }
    }

    public static void printValuesAndChromosomes(Integer[][] population, GeneticAlgorithm algorithm, Function<Double[], Double> f) {
        for (int i = 0; i < algorithm.getPopulationSize(); i++) {
            Double[] X = algorithm.decodeChromosome(population[i]);
            System.out.printf("Element %03d. populacji\n",i);
            printBinaryArray(population[i]);
            System.out.println();
            printFunctionValue(X,f.apply(X));
        }
    }
}
