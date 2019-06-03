package com.wentong.demo.bytecode;

public class ScoreCalculator {

    private void record(double score) {

    }

    private double getAverage() {
        return 0;
    }

    public static void main(String[] args) {
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        int score1 = 1;
        int score2 = 2;
        scoreCalculator.record(score1);
        scoreCalculator.record(score2);
        double average = scoreCalculator.getAverage();
    }

}
