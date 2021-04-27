package com.GeneticAlgorythm.exercises.laboratory07;

import com.GeneticAlgorythm.geneticAlgorythm.GeneticAlgorithm;
import com.GeneticAlgorythm.printing.Printing;
import com.GeneticAlgorythm.util.QuadraticFunction;
import com.GeneticAlgorythm.util.Solution;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Function;

public class Exercise01AL07 {
    static final Function<Double[], Double> f = new QuadraticFunction();

    public static void main(String[] args) throws IOException {
        for (int pop = 20; pop <= 200; pop += 20) {
            GeneticAlgorithm algorithm = new GeneticAlgorithm(2,-2,2,5,pop,0.02,0.6);
            Integer[][] population = algorithm.generatePopulation();
            FileWriter out = new FileWriter(String.format("measurements/quadratic50Steps/resultsQuadratic%d.csv",pop));
            CSVPrinter cvs = CSVFormat.EXCEL.withHeader("Generacja","Średnia wartość","Najlepszy wynik").print(out);

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

                algorithm.setTheBestSolution(algorithm.decodeChromosome(population[k]),max);

                if (j % 50 == 0) {
                    double average = algorithm.computeAverageValue(population, f);
                    cvs.printRecord(j, String.format("%f", average), String.format("%f", algorithm.getTheBestSolution().getY()));
                }

                Integer[][] temporaryPopulation = algorithm.roulette(population, f);
                temporaryPopulation = algorithm.applyCrossover(temporaryPopulation, 2);
                temporaryPopulation = algorithm.applyMutation(temporaryPopulation);
                population = temporaryPopulation;
            }
            cvs.close();
            Solution finalSolution = algorithm.getTheBestSolution();
            System.out.println("Rozmiar populacji: "+pop);
            System.out.println("Najlepsze rozwiązanie: ");
            Printing.printFunctionValue(finalSolution.getX(),finalSolution.getY());
            System.out.println();
        }
    }
}
