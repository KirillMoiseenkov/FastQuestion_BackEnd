package com.rob.FastQuestion.service;

import java.util.concurrent.CopyOnWriteArrayList;

public class RandomResistance {
    private static CopyOnWriteArrayList<Double> probabilityResistance = new CopyOnWriteArrayList<>();
    public static double resistance;

    private static void sumProbability() {
        Double countOfProb = (double) probabilityResistance.size();
        Double sumProb = probabilityResistance.stream().reduce(0d, Double::sum);
        double middleProb = sumProb / countOfProb;

        if (middleProb > 0.75d) {
            resistance = 0.30;
        } else if (middleProb < 0.35d) {
            resistance = 1.35;
        } else {
            resistance = 1;
        }
    }

    public static Double addToProbabilityResistance(Double prob) {
        probabilityResistance.add(prob);
        sumProbability();
        return getResistance(prob);
    }

    private static Double getResistance(Double rand) {
        return resistance * rand;
    }
}
