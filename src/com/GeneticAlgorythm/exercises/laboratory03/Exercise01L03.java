package com.GeneticAlgorythm.exercises.laboratory03;

import com.GeneticAlgorythm.genetic_algorithm.GeneticAlgorithm;
import com.GeneticAlgorythm.printing.Printing;
import com.GeneticAlgorythm.util.QuadraticFunction;

public class Exercise01L03 {
    private static final QuadraticFunction f = new QuadraticFunction();

    public static void main(String[] args) {
        GeneticAlgorithm algo = new GeneticAlgorithm(2, -2, 2, 5);

        Integer[] parent1, parent2, offspring1, offspring2;

        //declare arrays for storing decoded chromosomes
        Double[] offspring1Dec, offspring2Dec;

        //matrix for offspring (result of crossover)
        Integer[][] offspringBin;

        //generate parent chromosomes
        parent1 = algo.generateChromosome();
        parent2 = algo.generateChromosome();

        //print parent chromosome
        System.out.print("r1: ");
        Printing.printBinaryArray(parent1);
        System.out.print("r2: ");
        Printing.printBinaryArray(parent2);

        offspringBin = algo.pointCrossover(parent1, parent2);

        offspring1 = offspringBin[0];
        offspring2 = offspringBin[1];

        //print offspring chromosome
        System.out.print("p1: ");
        Printing.printBinaryArray(offspring1);
        System.out.print("p2: ");
        Printing.printBinaryArray(offspring2);

        //decode values of offspring chromosomes
        offspring1Dec = algo.decodeChromosome(offspring1);
        offspring2Dec = algo.decodeChromosome(offspring2);

        System.out.println("\nKrzyżowanie punktowe:");
        Printing.printFunctionValue(offspring1Dec, f.apply(offspring1Dec));

        System.out.println();

        Printing.printFunctionValue(offspring2Dec, f.apply(offspring2Dec));

        System.out.println();
        System.out.println();

        //print offspring
        System.out.print("p1: ");
        Printing.printBinaryArray(offspring1);
        System.out.print("p2: ");
        Printing.printBinaryArray(offspring2);

        offspringBin = algo.twoPointCrossover(parent1, parent2);

        offspring1 = offspringBin[0];
        offspring2 = offspringBin[1];

        //decoding of offspring chromosomes
        offspring1Dec = algo.decodeChromosome(offspring1);
        offspring2Dec = algo.decodeChromosome(offspring2);

        System.out.println("\nKrzyżowanie wielopunktowe:");
        Printing.printFunctionValue(offspring1Dec, f.apply(offspring1Dec));

        System.out.println();

        Printing.printFunctionValue(offspring2Dec, f.apply(offspring2Dec));
    }
}
