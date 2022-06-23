package com.GeneticAlgorythm.exercises.laboratory05;

import com.GeneticAlgorythm.geneticAlgorythm.GeneticAlgorithm;
import com.GeneticAlgorythm.printing.Printing;
import com.GeneticAlgorythm.util.RastriginFunction;

public class Exercise02L05 {
    private static final RastriginFunction f = new RastriginFunction(10, 10);

    public static void main(String[] args) {
        GeneticAlgorithm algo = new GeneticAlgorithm(10, -5.21, 5.21, 3,10);

        Integer[][] population = algo.generatePopulation();
        System.out.println("Wartości chromosomów: ");
        Printing.printValues(population, algo, f);
        System.out.println("Wartości chromosomów wraz wartościami funkcji");

        int howManyValuesLesserThanAverage = algo.howManyValuesLesserThanAverage(population, f);
        int howManyValuesGraterThanAverage = algo.howManyValuesGraterThanAverage(population, f);
        double averageValue = algo.computeAverageValue(population, f);
        Printing.printValuesAndChromosomes(population, algo, f);

        System.out.printf("W populacji znajduje się %d wartości mniejszych od średniej %f\n", howManyValuesLesserThanAverage, averageValue);
        System.out.printf("W populacji znajduje się %d wartości mniejszych od średniej %f\n", howManyValuesGraterThanAverage, averageValue);
    }
}
