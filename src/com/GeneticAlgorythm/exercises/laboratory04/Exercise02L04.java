package com.GeneticAlgorythm.exercises.laboratory04;

import com.GeneticAlgorythm.genetic_algorithm.GeneticAlgorithm;
import com.GeneticAlgorythm.printing.Printing;
import com.GeneticAlgorythm.util.RastriginFunction;
import java.util.Arrays;

public class Exercise02L04 {
    private static final RastriginFunction f = new RastriginFunction(10,10);

    public static void main(String[] args) {
        GeneticAlgorithm algo = new GeneticAlgorithm(10, -5.21, 5.21, 3);

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
