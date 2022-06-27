package com.GeneticAlgorithm.util;

public class Solution {
    private final Double[] x;
    private final Double y;

    public Solution(Double[] x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Solution() {
        x = null;
        y = null;
    }

    public Double[] getX() {
        return x;
    }

    public Double getY() {
        return y;
    }
}
