package com.GeneticAlgorithm.exercises.laboratory02;

import com.GeneticAlgorithm.genetic_algorithm.GeneticAlgorithm;
import com.GeneticAlgorithm.util.RastriginFunction;

public class Exercise02L02 {
    public static void main(String[] args) {
        Integer[] Xbin; //binary chromosome
        Double[] Xdec; //value of X (real number)
        double F; //value of function

        GeneticAlgorithm algorithm = new GeneticAlgorithm(10, -5.21, 5.21, 3);

        do {
            Xbin = algorithm.generateChromosome();
            Xdec = algorithm.decodeChromosome(Xbin);
        } while (Xdec[0] > 5.21 || Xdec[0] < -5.21 ||
                Xdec[1] > 5.21 || Xdec[1] < -5.21 ||
                Xdec[2] > 5.21 || Xdec[2] < -5.21 ||
                Xdec[3] > 5.21 || Xdec[3] < -5.21 ||
                Xdec[4] > 5.21 || Xdec[4] < -5.21 ||
                Xdec[5] > 5.21 || Xdec[5] < -5.21 ||
                Xdec[6] > 5.21 || Xdec[6] < -5.21 ||
                Xdec[7] > 5.21 || Xdec[7] < -5.21 ||
                Xdec[8] > 5.21 || Xdec[8] < -5.21 ||
                Xdec[9] > 5.21 || Xdec[9] < -5.21);

        F = algorithm.computeValue(Xdec, new RastriginFunction(10, 10));

        System.out.printf("F(X) = %f\n", F);
        System.out.println("Dla:");
        System.out.printf("\tx1 = %f\n", Xdec[0]);
        System.out.printf("\tx2 = %f\n", Xdec[1]);
        System.out.printf("\tx3 = %f\n", Xdec[2]);
        System.out.printf("\tx4 = %f\n", Xdec[3]);
        System.out.printf("\tx5 = %f\n", Xdec[4]);
        System.out.printf("\tx6 = %f\n", Xdec[5]);
        System.out.printf("\tx7 = %f\n", Xdec[6]);
        System.out.printf("\tx8 = %f\n", Xdec[7]);
        System.out.printf("\tx9 = %f\n", Xdec[8]);
        System.out.printf("\tx10 = %f\n", Xdec[9]);
    }
}
