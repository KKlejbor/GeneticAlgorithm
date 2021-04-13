package com.GeneticAlgorythm.geneticAlgorythm;

import java.util.Random;

public class GeneticAlgorithm {
    private final Random randomGenerator = new Random();
    private int geneLength; //gen length
    private int numberOfGenes; //ilość genów
    private double lowerBound; //dolna granica dziedziny
    private double upperBound; //górna granica dziedziny
    private double probabilityOfMutation; //prawdopodobieństwo mutacji

    public GeneticAlgorithm() {

    }

    public GeneticAlgorithm(int numberOfGenes, double loweBound, double upperBound, int numberOfDecimalPlaces, double probabilityOfMutation) {
        geneLength = (int) Math.ceil(log2((upperBound - loweBound) * Math.pow(10, numberOfDecimalPlaces)));
        this.numberOfGenes = numberOfGenes;
        this.lowerBound = loweBound;
        this.upperBound = upperBound;
        this.probabilityOfMutation = probabilityOfMutation;
    }

    public GeneticAlgorithm(int numberOfGenes, double lowerBound, double upperBound, int numberOfDecimalPlaces) {
        geneLength = (int) Math.ceil(log2((upperBound - lowerBound) * Math.pow(10, numberOfDecimalPlaces)));
        this.numberOfGenes = numberOfGenes;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        probabilityOfMutation = 0.1;
    }


    public static double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    public int getGeneLength() {
        return geneLength;
    }

    public void setGeneLength(int geneLength) {
        this.geneLength = geneLength;
    }

    public int getNumberOfGenes() {
        return numberOfGenes;
    }

    public void setNumberOfGenes(int numberOfGenes) {
        this.numberOfGenes = numberOfGenes;
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(double lowerBound) {
        this.lowerBound = lowerBound;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(double upperBound) {
        this.upperBound = upperBound;
    }

    public double getProbabilityOfMutation() {
        return probabilityOfMutation;
    }

    public void setProbabilityOfMutation(double probabilityOfMutation) {
        this.probabilityOfMutation = probabilityOfMutation;
    }

    public Integer[] generateChromosome() {
        Integer[] result = new Integer[this.geneLength * this.numberOfGenes];
        //Random losowanie = new Random();
        for (int i = 0; i < this.geneLength * this.numberOfGenes; i++) {
            result[i] = randomGenerator.nextInt(2);
        }
        return result;
    }

    public Integer[][] generatePopulation(int N){
        Integer[][] population = new Integer[this.numberOfGenes * geneLength][N];
        for (int i = 0; i < N; i++) {
            population[i] = generateChromosome();
        }
        return population;
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

    public Integer[][] pointCrossover(Integer[] parent1, Integer[] parent2) {
        int index = randomGenerator.nextInt(numberOfGenes * geneLength);
        Integer[] child1 = new Integer[numberOfGenes * geneLength];
        Integer[] child2 = new Integer[numberOfGenes * geneLength];

        for (int i = 0; i < index; i++) {
            child1[i] = parent1[i];
            child2[i] = parent2[i];
        }

        for (int i = index; i < numberOfGenes * geneLength; i++) {
            child1[i] = parent2[i];
            child2[i] = parent1[i];
        }

        return new Integer[][]{child1, child2};
    }

    public Integer[][] multipointCrossover(Integer[] parent1, Integer[] parent2) {
        Integer[] child1 = new Integer[this.numberOfGenes * this.geneLength];
        Integer[] child2 = new Integer[this.numberOfGenes * this.geneLength];

        int index1 = randomGenerator.nextInt(numberOfGenes * geneLength);
        int index2 = randomGenerator.nextInt(numberOfGenes * geneLength - (index1 + 1)) + (index1 + 1);

        for (int i = 0; i < index1; i++) {
            child1[i] = parent1[i];
            child2[i] = parent2[i];
        }

        for (int i = index1; i < index2; i++) {
            child1[i] = parent2[i];
            child2[i] = parent1[i];
        }

        for (int i = index2; i < this.numberOfGenes * this.geneLength; i++) {
            child1[i] = parent1[i];
            child2[i] = parent2[i];
        }

        return new Integer[][]{child1, child2};
    }

    public Integer[] mutation(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            if (randomGenerator.nextDouble() <= probabilityOfMutation) {
                if (array[i] == 0)
                    array[i] = 1;
                else
                    array[i] = 0;
            }
        }
        return array;
    }
}
