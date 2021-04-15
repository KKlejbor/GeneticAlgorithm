package com.GeneticAlgorythm.printing;

import com.GeneticAlgorythm.geneticAlgorythm.GeneticAlgorithm;
import com.GeneticAlgorythm.util.Function;

public class Printing {
    public static void printBinaryArray(Integer[] tab) {
        for (Integer integer : tab) {
            System.out.print(integer);
        }
        System.out.println();
    }

    public static void printFunctionValue(Double[] X, Double Y) {
        System.out.printf("F(X) = %f\n", Y);
        System.out.println("Dla:");
        for (int i = 0; i < X.length; i++) {
            System.out.printf("\tx%d = %f\n", i, X[i]);
        }
    }

    public static int howManyBitsHaveChanged(Integer[] array1, Integer[] array2){
        int howMany = 0;
        for (int i = 0; i < array1.length; i++) {
            if(!array1[i].equals(array2[i]))
                howMany++;
        }
        return howMany;
    }

    public static void printChromosomes(Integer[][] population) {
        for (int i = 0; i < population.length; i++) {
            printBinaryArray(population[i]);
        }
    }

    public static void printValues(Integer[][] population, GeneticAlgorithm algorithm, Function f) {
        for (int i = 0; i < algorithm.getPopulationSize(); i++) {
            Double[] X = algorithm.decodeChromosome(population[i]);
            printFunctionValue(X,f.calculateValue(X));
        }
    }

    public static void printValuesAndChromosomes(Integer[][] population, GeneticAlgorithm algorithm, Function f) {
        for (int i = 0; i < algorithm.getPopulationSize(); i++) {
            Double[] X = algorithm.decodeChromosome(population[i]);
            System.out.printf("Element %03d. populacji\n",i);
            printBinaryArray(population[i]);
            System.out.println();
            printFunctionValue(X,f.calculateValue(X));
        }
    }
}
