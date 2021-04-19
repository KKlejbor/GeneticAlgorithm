package com.GeneticAlgorythm.exercises.laboratory02;

import com.GeneticAlgorythm.geneticAlgorythm.GeneticAlgorithm;
import com.GeneticAlgorythm.util.QuadraticFunction;

public class Exercise01L02 {
    private static double f(double x1, double x2) {
        return -Math.pow(x1, 2) - Math.pow(x2, 2) + 2;
    }

    public static void main(String[] args) {
        Integer[] Xbin; //binary chromosome
        Double[] Xdec; //value of X (real number)
        double F; //value of function
        GeneticAlgorithm algorithm = new GeneticAlgorithm(2, -2, 2, 5);

        do {
            Xbin = algorithm.generateChromosome();
            Xdec = algorithm.decodeChromosome(Xbin);
        } while (Xdec[0] > 2 || Xdec[0] < -2 ||
                Xdec[1] > 2 || Xdec[1] < -2);

        F = algorithm.computeValue(Xdec, new QuadraticFunction());


        System.out.printf("F(X) = %f\n", F);
        System.out.println("Dla:");
        System.out.printf("\tx1 = %f\n", Xdec[0]);
        System.out.printf("\tx2 = %f\n", Xdec[1]);
    }
}
