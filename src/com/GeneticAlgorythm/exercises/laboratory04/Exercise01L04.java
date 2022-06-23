package com.GeneticAlgorythm.exercises.laboratory04;

import com.GeneticAlgorythm.geneticAlgorythm.GeneticAlgorithm;
import com.GeneticAlgorythm.printing.Printing;
import com.GeneticAlgorythm.util.QuadraticFunction;
import java.util.Arrays;

public class Exercise01L04 {
    private static final QuadraticFunction f = new QuadraticFunction();

    public static void main(String[] args) {
        GeneticAlgorithm algo = new GeneticAlgorithm(2, -2, 2, 5);

        Integer[] chromosome = algo.generateChromosome();
        Double[] X = algo.decodeChromosome(chromosome);
        Integer[] beforeMutation = Arrays.copyOf(chromosome,chromosome.length);

        System.out.print("Przed mutacją: ");
        Printing.printBinaryArray(chromosome);
        Printing.printFunctionValue(X, f.apply(X));
        System.out.println();

        chromosome = algo.mutation(chromosome);
        X = algo.decodeChromosome(chromosome);

        System.out.print("Po mutacją: ");
        Printing.printBinaryArray(chromosome);
        Printing.printFunctionValue(X, f.apply(X));

        System.out.printf("\nZmianie uległo %d bitów", GeneticAlgorithm.howManyBitsHaveChanged(beforeMutation,chromosome));
    }
}
