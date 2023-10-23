package main.net.karpi.adventofcode.aoc2022;

import main.net.karpi.adventofcode.helpers.AoCYear;
import main.net.karpi.adventofcode.helpers.InputLoader;

import java.util.*;

public class Day01 {

    public static void main(String[] args) {
        List<String> measurements = new InputLoader(AoCYear.AOC_2022).loadStrings("Day01Input");
        part1(measurements);
        part2(measurements);
    }

    /**
     * Find the Elf carrying the most Calories. How many total Calories is that Elf carrying?
     */
    private static void part1(List<String> measurements) {
        var maxElfCalories = 0;
        var accElfCalories = 0;
        for (String value : measurements) {
            if (value.isEmpty()) {
                maxElfCalories = Math.max(maxElfCalories, accElfCalories);
                accElfCalories = 0;
            } else {
                accElfCalories += Integer.parseInt(value);
            }
        }
        System.out.println("p1> Max: " + maxElfCalories);
    }

    private static void part2(List<String> measurements) {
        List<Integer> top3 = new ArrayList<>(Arrays.asList(0, 0, 0));
        var accElfCalories = 0;
        for (String value : measurements) {
            if (value.isEmpty()) {
                if (accElfCalories > top3.get(2)) {
                    top3.add(accElfCalories);
                    Collections.sort(top3, Collections.reverseOrder());
                    top3.remove(3);
                }
                accElfCalories = 0;
            } else {
                accElfCalories += Integer.parseInt(value);
            }
        }
        System.out.println("p2> Max 3: " + (top3.get(0) + top3.get(1) + top3.get(2)));
        System.out.println("p2> Max 3: " + top3.stream().reduce(0, Integer::sum));
    }
}
