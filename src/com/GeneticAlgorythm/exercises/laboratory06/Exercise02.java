package com.GeneticAlgorythm.exercises.laboratory06;

import com.GeneticAlgorythm.geneticAlgorythm.GeneticAlgorithm;
import com.GeneticAlgorythm.printing.Printing;
import com.GeneticAlgorythm.util.quadraticFunction;
import com.GeneticAlgorythm.util.rastriginFunction;

public class Exercise02 {
    public static void main(String[] args) {
        GeneticAlgorithm algorithm = new GeneticAlgorithm(10,-5.21,5.21,3,0.1,5);
        Integer[][] P1 = algorithm.generatePopulation();
        Integer[][] P2 = algorithm.roulette(P1,new rastriginFunction(10,10));
        System.out.println("Populacja P1:");
        Printing.printChromosomes(P1);
        System.out.printf("Średnia wartość funkcji przystowania dla populacji P1 %f\n\n", algorithm.computeAverageValue(P1,new rastriginFunction(10,10)));

        System.out.println("Populacja P2:");
        Printing.printChromosomes(P2);
        System.out.printf("Średnia wartość funkcji przystowania dla populacji P2 %f\n", algorithm.computeAverageValue(P2,new rastriginFunction(10,10)));
    }
}
