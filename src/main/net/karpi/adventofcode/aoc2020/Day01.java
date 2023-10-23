package main.net.karpi.adventofcode.aoc2020;

import main.net.karpi.adventofcode.helpers.AoCYear;
import main.net.karpi.adventofcode.helpers.InputLoader;

import java.util.*;

/**
 * Created by xkarpi06 on 13.10.2023
 * <p>
 * time: X min + Y min (start X:YZ)
 * <p>
 * stats:
 * Day       Time   Rank  Score       Time   Rank  Score
 */
public class Day01 {

    public static void main(String[] args) {
        List<Integer> input = new InputLoader(AoCYear.AOC_2020).loadInts("Day01Input");
        int[] arrInput = new int[input.size()];
        for (int i = 0; i < arrInput.length; i++) {
            arrInput[i] = input.get(i);
        }
        part1(input);
        part2(arrInput);
    }

    private static void part1(List<Integer> input) {
        for (int i = 0; i < input.size() - 1; i++) {
            for (int j = i+1; j < input.size(); j++) {
                if (input.get(i) + input.get(j) == 2020) {
                    System.out.println("p1> " + (input.get(i) * input.get(j)) + " (" + input.get(i) + "," + input.get(j) + ")");
                    return;
                }
            }
        }
    }

    private static void part2(int[] input) {
        for (int i = 0; i < input.length - 2; i++) {
            for (int j = i+1; j < input.length - 1; j++) {
                for (int k = j+1; k < input.length; k++) {
                    if (input[i] + input[j] + input[k] == 2020) {
                        System.out.println("p1> " + (input[i] * input[j] * input[k]) + " (" + input[i] + "," + input[j] + "," + input[k] + ")");
                        return;
                    }
                }
            }
        }
        System.out.println("p2> TODO ");
    }
}
