package com.GeneticAlgorithm.exercises.laboratory07;

import com.GeneticAlgorithm.genetic_algorithm.GeneticAlgorithm;
import com.GeneticAlgorithm.printing.Printing;
import com.GeneticAlgorithm.util.QuadraticFunction;
import com.GeneticAlgorithm.util.Solution;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Function;

public class Exercise01BL07 {
    static final Function<Double[], Double> f = new QuadraticFunction();

    public static void main(String[] args) throws IOException {
        for (int pop = 20; pop <= 200; pop += 20) {
            FileWriter out2 = new FileWriter(String.format("measurements/quadratic1Step/population%d/averagePerStep.csv",pop));
            CSVPrinter cvs2 = CSVFormat.EXCEL.withHeader("Generacja","Średnia wartość").print(out2);
            double[] average = new double[1001];

            for (int l = 0; l < 50; l++) {
                GeneticAlgorithm algorithm = new GeneticAlgorithm(2,-2,2,5,pop,0.02,0.6);
                Integer[][] population = algorithm.generatePopulation();
                FileWriter out1 = new FileWriter(String.format("measurements/quadratic1Step/population%d/resultsQuadratic%02d.csv",pop,l));
                CSVPrinter cvs1 = CSVFormat.EXCEL.withHeader("Generacja","Najlepszy aktualny wynik","Najlepszy wynik").print(out1);

                for (int j = 0; j <= 1000; j++) {
                    //Calculate values
                    Double[] valuesOfFunction = algorithm.computeValues(population, f);
                    double max = -Double.MAX_VALUE;
                    int k = 0;
                    for (int i = 0; i < valuesOfFunction.length; i++) {
                        if (valuesOfFunction[i] > max){
                            max = valuesOfFunction[i];
                            k = i;
                        }
                    }

                    average[j] += max;


                    algorithm.setTheBestSolution(algorithm.decodeChromosome(population[k]),max);


                    //double average = algorithm.computeAverageValue(population, f);
                    cvs1.printRecord(j, String.format("%f", max), String.format("%f", algorithm.getTheBestSolution().getY()));


                    Integer[][] temporaryPopulation = algorithm.roulette(population, f);
                    temporaryPopulation = algorithm.applyCrossover(temporaryPopulation, 2);
                    temporaryPopulation = algorithm.applyMutation(temporaryPopulation);
                    population = temporaryPopulation;
                }

                cvs1.close();
                Solution finalSolution = algorithm.getTheBestSolution();
                System.out.println("Rozmiar populacji: "+pop);
                System.out.println("Najlepsze rozwiązanie: ");
                Printing.printFunctionValue(finalSolution.getX(),finalSolution.getY());
                System.out.println();
            }

            for (int i = 0; i <= 1000; i++) {
                cvs2.printRecord(i, String.format("%f",average[i]/50));
            }

            cvs2.close();
        }
    }
}
