package com.GeneticAlgorithm.exercises.laboratory03;

import com.GeneticAlgorithm.genetic_algorithm.GeneticAlgorithm;
import com.GeneticAlgorithm.printing.Printing;
import com.GeneticAlgorithm.util.RastriginFunction;

public class Exercise02L03 {
    private static final RastriginFunction f = new RastriginFunction(10, 10);

    public static void main(String[] args) {
        GeneticAlgorithm algo = new GeneticAlgorithm(10, -5.21, 5.21, 3);

        Integer[] parent1, parent2, offspring1, offspring2;

        //deklaracja tablic przechowujących liczby rzeczywiste
        Double[] offspring1Dec, offspring2Dec;

        //macierz przechowująca skrzyżowanych potomków
        Integer[][] offspringBin;

        //generowanie ciągów binarnych dla rodziców
        parent1 = algo.generateChromosome();
        parent2 = algo.generateChromosome();

        //drukowanie ciągów rodziców
        System.out.print("r1: ");
        Printing.printBinaryArray(parent1);
        System.out.print("r2: ");
        Printing.printBinaryArray(parent2);

        offspringBin = algo.pointCrossover(parent1, parent2);

        offspring1 = offspringBin[0];
        offspring2 = offspringBin[1];

        //drukowanie ciągów potomków
        System.out.print("p1: ");
        Printing.printBinaryArray(offspring1);
        System.out.print("p2: ");
        Printing.printBinaryArray(offspring2);

        //zamiana ciągów binarnych na liczby rzeczywiste
        offspring1Dec = algo.decodeChromosome(offspring1);
        offspring2Dec = algo.decodeChromosome(offspring2);

        System.out.println("\nKrzyżowanie punktowe:");
        Printing.printFunctionValue(offspring1Dec, f.apply(offspring1Dec));

        System.out.println();

        Printing.printFunctionValue(offspring2Dec, f.apply(offspring2Dec));

        System.out.println();
        System.out.println();

        //drukowanie ciągów potomków
        System.out.print("p1: ");
        Printing.printBinaryArray(offspring1);
        System.out.print("p2: ");
        Printing.printBinaryArray(offspring2);

        offspringBin = algo.twoPointCrossover(parent1, parent2);

        offspring1 = offspringBin[0];
        offspring2 = offspringBin[1];

        //drukowanie ciągów potomków
        System.out.print("p1: ");
        Printing.printBinaryArray(offspring1);
        System.out.print("p2: ");
        Printing.printBinaryArray(offspring2);

        //zamiana ciągów binarnych na liczby rzeczywiste
        offspring1Dec = algo.decodeChromosome(offspring1);
        offspring2Dec = algo.decodeChromosome(offspring2);

        System.out.println("\nKrzyżowanie wielopunktowe:");
        Printing.printFunctionValue(offspring1Dec, f.apply(offspring1Dec));

        System.out.println();

        Printing.printFunctionValue(offspring2Dec, f.apply(offspring2Dec));
    }
}
