package com.GeneticAlgorithm.exercises.laboratory05;

import com.GeneticAlgorithm.genetic_algorithm.GeneticAlgorithm;
import com.GeneticAlgorithm.printing.Printing;
import com.GeneticAlgorithm.util.QuadraticFunction;

public class Exercise01L05 {
    private static final QuadraticFunction f = new QuadraticFunction();

    public static void main(String[] args) {
        GeneticAlgorithm algo = new GeneticAlgorithm(2, -2, 2, 5, 10);

        Integer[][] population = algo.generatePopulation();
        System.out.println("Wartości chromosomów: ");
        Printing.printValues(population, algo, f);
        System.out.println("Wartości chromosomów wraz wartościami funkcji");

        int howManyValuesLesserThanAverage = algo.howManyValuesLesserThanAverage(population, f);
        int howManyValuesGraterThanAverage = algo.howManyValuesGraterThanAverage(population, f);
        double averageValue = algo.computeAverageValue(population, f);
        Printing.printValuesAndChromosomes(population, algo, f);

        System.out.printf("W populacji znajduje się %d waartości mniejszych od średniej %f\n", howManyValuesLesserThanAverage, averageValue);
        System.out.printf("W populacji znajduje się %d waartości mniejszych od średniej %f\n", howManyValuesGraterThanAverage, averageValue);
    }
}
