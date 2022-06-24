package com.GeneticAlgorythm.genetic_algorithm;

import com.GeneticAlgorythm.util.Solution;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;

public class GeneticAlgorithm {
    private final static Random randomNumberGenerator = new Random();
    private int geneLength;
    private int numberOfGenes;
    private double lowerBound;
    private double upperBound;
    private double probabilityOfMutation;
    private double probabilityOfCrossover;
    private int populationSize;
    private Solution theBestSolution;

    public GeneticAlgorithm() {
    }

    public GeneticAlgorithm(int numberOfGenes, double loweBound, double upperBound, int numberOfDecimalPlaces, int populationSize, double probabilityOfMutation, double probabilityOfCrossover) {
        geneLength = (int) Math.ceil(log2((upperBound - loweBound) * Math.pow(10, numberOfDecimalPlaces)));
        this.numberOfGenes = numberOfGenes;
        this.lowerBound = loweBound;
        this.upperBound = upperBound;
        this.probabilityOfMutation = probabilityOfMutation;
        this.populationSize = populationSize;
        this.probabilityOfCrossover = probabilityOfCrossover;
        theBestSolution = new Solution();
    }

    public GeneticAlgorithm(int numberOfGenes, double lowerBound, double upperBound, int numberOfDecimalPlaces, int populationSize) {
        this.geneLength = (int) Math.ceil(log2((upperBound - lowerBound) * Math.pow(10, numberOfDecimalPlaces)));
        this.numberOfGenes = numberOfGenes;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        probabilityOfMutation = 0.02;
        probabilityOfCrossover = 0.6;
        this.populationSize = populationSize;
        theBestSolution = new Solution();
    }

    public GeneticAlgorithm(int numberOfGenes, double lowerBound, double upperBound, int numberOfDecimalPlaces, double probabilityOfMutation) {
        this.geneLength = (int) Math.ceil(log2((upperBound - lowerBound) * Math.pow(10, numberOfDecimalPlaces)));
        this.numberOfGenes = numberOfGenes;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.probabilityOfMutation = probabilityOfMutation;
        probabilityOfCrossover = 0.6;
        populationSize = 20;
        theBestSolution = new Solution();
    }

    public GeneticAlgorithm(int numberOfGenes, double lowerBound, double upperBound, int numberOfDecimalPlaces) {
        geneLength = (int) Math.ceil(log2((upperBound - lowerBound) * Math.pow(10, numberOfDecimalPlaces)));
        this.numberOfGenes = numberOfGenes;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        probabilityOfMutation = 0.02;
        probabilityOfCrossover = 0.6;
        populationSize = 20;
        theBestSolution = new Solution();
    }

    private static double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    public static int howManyBitsHaveChanged(Integer[] array1, Integer[] array2) {
        int howMany = 0;
        for (int i = 0; i < array1.length; i++) {
            if (!array1[i].equals(array2[i]))
                howMany++;
        }
        return howMany;
    }

    public Solution getTheBestSolution() {
        return theBestSolution;
    }

    public void setTheBestSolution(Double[] x, Double y) {
        if(this.theBestSolution.getX() == null || y > theBestSolution.getY()) {
            theBestSolution = new Solution(x,y);
        }
    }

    public int getGeneLength() {
        return geneLength;
    }

    public int getNumberOfGenes() {
        return numberOfGenes;
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public double getProbabilityOfMutation() {
        return probabilityOfMutation;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public Integer[][] applyCrossover(Integer[][] parents, int numberOfPoints) {
        Integer[][] newPopulation = new Integer[populationSize][geneLength * numberOfGenes];
        Integer[][] offSpring;
        int indexOfSecondParent;

        for (int i = 0; i < populationSize; i++) {
            if (randomNumberGenerator.nextDouble() <= probabilityOfCrossover) {
                do {
                    indexOfSecondParent = generateRandomIndex(populationSize);
                } while (i == indexOfSecondParent);

                switch (numberOfPoints){
                    case 1:
                        offSpring = pointCrossover(parents[i], parents[indexOfSecondParent]);
                        break;
                    case 2:
                        offSpring = twoPointCrossover(parents[i], parents[indexOfSecondParent]);
                        break;
                    default:
                        offSpring = multipointCrossover(parents[i], parents[indexOfSecondParent], numberOfPoints);
                        break;
                }

                newPopulation[i] = offSpring[0];
                newPopulation[indexOfSecondParent] = offSpring[1];
            } else {
                newPopulation[i] = parents[i];
            }
        }

        return newPopulation;
    }

    public Integer[][] applyMutation(Integer[][] population) {
        for (int i = 0; i < populationSize; i++) {
            if (randomNumberGenerator.nextDouble() <= probabilityOfMutation) {
                population[i] = mutation(population[i]);
            }
        }

        return population;
    }


    public double computeAverageValue(Integer[][] population, Function<Double[], Double> f) {
        double average = 0;
        Double[] values = computeValues(population, f);

        for (int i = 0; i < populationSize; i++) {
            average += values[i];
        }

        return average / populationSize;
    }

    public double computeValue(Integer[] chromosome, Function<Double[], Double> f) {
        return f.apply(decodeChromosome(chromosome));
    }

    public double computeValue(Double[] decodedChromosome, Function<Double[], Double> f) {
        return f.apply(decodedChromosome);
    }

    public Double[] computeValues(Integer[][] population, Function<Double[], Double> f) {
        Double[] results = new Double[populationSize];

        for (int i = 0; i < populationSize; i++) {
            results[i] = computeValue(population[i], f);
        }

        return results;
    }

    public Double[] decodeChromosome(Integer[] tab) {
        double number;
        Double[] result = new Double[numberOfGenes];

        for (int i = 0; i < numberOfGenes; i++) {
            number = 0;
            for (int j = geneLength * (1 + i) - 1; j >= i * geneLength; j--) {
                number += tab[j] * Math.pow(2, j - i * geneLength);
            }
            result[i] = lowerBound + number * ((upperBound - lowerBound) / (Math.pow(2, geneLength) - 1));
        }

        return result;
    }

    public int howManyValuesGraterThanAverage(Integer[][] population, Function<Double[], Double> f) {
        double average = computeAverageValue(population, f);
        int howMany = 0;
        Double[] results = computeValues(population, f);

        for (int i = 0; i < populationSize; i++) {
            if (results[i] >= average)
                howMany++;
        }
        return howMany;
    }

    public int howManyValuesLesserThanAverage(Integer[][] population, Function<Double[], Double> f) {
        double average = computeAverageValue(population, f);
        int howMany = 0;
        Double[] results = computeValues(population, f);

        for (int i = 0; i < populationSize; i++) {
            if (results[i] < average)
                howMany++;
        }
        return howMany;
    }

    public Integer[] generateChromosome() {
        Integer[] result = new Integer[geneLength * numberOfGenes];

        for (int i = 0; i < geneLength * numberOfGenes; i++) {
            result[i] = randomNumberGenerator.nextInt(2);
        }

        return result;
    }

    public Integer[][] generatePopulation() {
        Integer[][] population = new Integer[populationSize][numberOfGenes * geneLength];

        for (int i = 0; i < populationSize; i++) {
            population[i] = generateChromosome();
        }

        return population;
    }

    private int generateRandomIndex() {
        return randomNumberGenerator.nextInt(geneLength * numberOfGenes - 1);
    }

    private int generateRandomIndex(int max) {
        return randomNumberGenerator.nextInt(max);
    }

    public Integer[][] multipointCrossover(Integer[] parent1, Integer[] parent2, int numberOfPoints) {

        Integer[] offspring1 = new Integer[numberOfGenes * geneLength];
        Integer[] offspring2 = new Integer[numberOfGenes * geneLength];
        Integer[] indexes;
        int index;
        ArrayList<Integer> uniqueIndexes = new ArrayList<>();

        for (int i = 0; i < numberOfPoints; i++) {
            do {
                index = generateRandomIndex();
            } while (uniqueIndexes.contains(index));
            uniqueIndexes.add(index);
        }
        uniqueIndexes.sort(null);
        indexes = uniqueIndexes.toArray(Integer[]::new);

        for (int j = 0; j <= indexes[0]; j++) {
            offspring1[j] = parent1[j];
            offspring2[j] = parent2[j];
        }

        for (int i = 1; i < numberOfPoints; i++) {
            if (i % 2 == 1) {
                for (int j = indexes[i - 1] + 1; j <= indexes[i]; j++) {
                    offspring1[j] = parent2[j];
                    offspring2[j] = parent1[j];
                }
            } else {
                for (int j = indexes[i - 1] + 1; j <= indexes[i]; j++) {
                    offspring1[j] = parent1[j];
                    offspring2[j] = parent2[j];
                }
            }
        }

        if (numberOfPoints % 2 == 0) {
            for (int j = indexes[numberOfPoints - 1] + 1; j <= numberOfGenes * geneLength - 1; j++) {
                offspring1[j] = parent1[j];
                offspring2[j] = parent2[j];
            }
        } else {
            for (int j = indexes[numberOfPoints - 1] + 1; j <= numberOfGenes * geneLength - 1; j++) {
                offspring1[j] = parent2[j];
                offspring2[j] = parent1[j];
            }
        }

        return new Integer[][]{offspring1, offspring2};
    }

    public Integer[] mutation(Integer[] chromosome) {
        for (int i = 0; i < chromosome.length; i++) {
            if (randomNumberGenerator.nextDouble() <= probabilityOfMutation) {
                if (chromosome[i] == 0)
                    chromosome[i] = 1;
                else
                    chromosome[i] = 0;
            }
        }
        return chromosome;
    }

    public Integer[][] pointCrossover(Integer[] parent1, Integer[] parent2) {
        int index = generateRandomIndex();

        Integer[] offspring1 = new Integer[numberOfGenes * geneLength];
        Integer[] offspring2 = new Integer[numberOfGenes * geneLength];

        for (int i = 0; i <= index; i++) {
            offspring1[i] = parent1[i];
            offspring2[i] = parent2[i];
        }

        for (int i = index + 1; i <= numberOfGenes * geneLength - 2; i++) {
            offspring1[i] = parent2[i];
            offspring2[i] = parent1[i];
        }

        offspring1[numberOfGenes * geneLength - 1] = parent1[numberOfGenes * geneLength - 1];
        offspring2[numberOfGenes * geneLength - 1] = parent2[numberOfGenes * geneLength - 1];

        return new Integer[][]{offspring1, offspring2};
    }

    public Integer[][] roulette(Integer[][] oldPopulation, Function<Double[], Double> f) {
        Integer[][] temporaryPopulation = new Integer[populationSize][geneLength * numberOfGenes];
        double[] probabilities = new double[populationSize];
        double minimalValue = 0;
        double sum = 0;
        double[] values = new double[populationSize];
        double probability;

        for (int i = 0; i < populationSize; i++) {
            values[i] = f.apply(decodeChromosome(oldPopulation[i]));
            if (values[i] < minimalValue)
                minimalValue = values[i];
        }

        if (minimalValue <= 0) {
            for (int i = 0; i < populationSize; i++) {
                values[i] += Math.abs(minimalValue) + 1;
                sum += values[i];
            }
        } else {
            for (int i = 0; i < populationSize; i++) {
                sum += values[i];
            }
        }

        for (int i = 0; i < populationSize; i++) {
            if (i == 0) {
                probabilities[i] = 0;
            } else if (i == populationSize - 1) {
                probabilities[i] = 1;
            } else {
                probabilities[i] = probabilities[i - 1] + (values[i] / sum);
            }

        }

        for (int i = 0; i < populationSize; i++) {
            probability = randomNumberGenerator.nextDouble();

            for (int j = 0; j < populationSize; j++) {
                if (probabilities[j] >= probability) {
                    temporaryPopulation[i] = oldPopulation[j];
                    break;
                }
            }
        }

        return temporaryPopulation;
    }

    public Integer[][] twoPointCrossover(Integer[] parent1, Integer[] parent2) {
        Integer[] offspring1 = new Integer[numberOfGenes * geneLength];
        Integer[] offspring2 = new Integer[numberOfGenes * geneLength];

        int index1 = generateRandomIndex();
        int index2;

        do {
            index2 = generateRandomIndex();
        } while (index1 == index2);

        if (index1 > index2) {
            int temp = index2;
            index2 = index1;
            index1 = temp;
        }

        for (int i = 0; i <= index1; i++) {
            offspring1[i] = parent1[i];
            offspring2[i] = parent2[i];
        }

        for (int i = index1 + 1; i <= index2; i++) {
            offspring1[i] = parent2[i];
            offspring2[i] = parent1[i];
        }

        for (int i = index2 + 1; i <= numberOfGenes * geneLength - 1; i++) {
            offspring1[i] = parent1[i];
            offspring2[i] = parent2[i];
        }

        return new Integer[][]{offspring1, offspring2};
    }
}
