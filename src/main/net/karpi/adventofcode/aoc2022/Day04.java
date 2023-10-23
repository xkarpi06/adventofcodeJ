package main.net.karpi.adventofcode.aoc2022;

import main.net.karpi.adventofcode.helpers.AoCYear;
import main.net.karpi.adventofcode.helpers.InputLoader;

import java.util.*;

/**
 * Created by xkarpi06 on 15.09.2023
 * <p>
 * time: X min + Y min (start X:YZ)
 * <p>
 * stats:
 * Day       Time   Rank  Score       Time   Rank  Score
 */
public class Day04 {

    public static void main(String[] args) {
        List<String> input = new InputLoader(AoCYear.AOC_2022).loadStrings("Day04Input");
        part1(input);
        part2(input);
    }

    /**
     * input:
     * 2-4,6-8
     * 2-3,4-5
     * 5-7,7-9
     * ...
     * In how many assignment pairs does one range fully contain the other?
     */
    private static void part1(List<String> input) {
        var acc = 0;
        for (String value : input) {
            String[] pair = value.split(",");
            List<int[]> ranges = Arrays.stream(pair).map(Day04::parseRange).toList();
            int[] rangeA = ranges.get(0);
            int[] rangeB = ranges.get(1);
            if (rangeA[0] <= rangeB[0] && rangeA[1] >= rangeB[1]) {
                acc++;
            } else if (rangeB[0] <= rangeA[0] && rangeB[1] >= rangeA[1]) {
                acc++;
            }
        }
        System.out.println("p1> " + acc);
    }

    private static int[] parseRange(String input) {
        List<Integer> result = Arrays.stream(input.split("-")).map(Integer::parseInt).toList();
        return new int[]{result.get(0), result.get(1)};
    }

    /**
     * input:
     * 2-4,6-8
     * 2-3,4-5
     * 5-7,7-9
     * ...
     * In how many assignment pairs do the ranges overlap?
     */
    private static void part2(List<String> input) {
        var acc = 0;
        for (String value : input) {
            String[] pair = value.split(",");
            List<int[]> ranges = Arrays.stream(pair).map(Day04::parseRange).toList();
            int[] rangeA = ranges.get(0);
            int[] rangeB = ranges.get(1);
            if (rangeA[1] >= rangeB[0] && rangeA[1] <= rangeB[1]) {
                acc++;
            } else if (rangeB[1] >= rangeA[0] && rangeB[1] <= rangeA[1]) {
                acc++;
            }
        }
        System.out.println("p2> " + acc);
    }
}
