package main.net.karpi.adventofcode.aoc2022;

import main.net.karpi.adventofcode.helpers.AoCYear;
import main.net.karpi.adventofcode.helpers.InputLoader;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by xkarpi06 on 15.09.2023
 * <p>
 * time: X min + Y min (start X:YZ)
 * <p>
 * stats:
 * Day       Time   Rank  Score       Time   Rank  Score
 */
public class Day03 {

    public static void main(String[] args) {
        List<String> input = new InputLoader(AoCYear.AOC_2022).loadStrings("Day03Input");
        part1(input);
        part2(input);
    }

    /**
     * Find the item type that appears in both compartments of each rucksack. What is the sum of the priorities
     * of those item types?
     */
    private static void part1(List<String> input) {
        var acc = 0;
        for (String item : input) {
            String firstHalf = item.substring(0, item.length() / 2);
            String secondHalf = item.substring(item.length() / 2);
            for (int i = 0; i < firstHalf.length(); i++) {
                if (secondHalf.contains(String.valueOf(firstHalf.charAt(i)))) {
                    acc += priority(firstHalf.charAt(i));
                    break;
                }
            }
        }
        System.out.println("p1> " + acc);
    }

    /**
     * Find the item type that corresponds to the badges of each three-Elf group. What is the sum of the priorities
     * of those item types?
     */
    private static void part2(List<String> input) {
        var acc = 0;
        for (int i = 0; i < input.size() - 2; i += 3) {
            String[] threeElves = {input.get(i), input.get(i+1), input.get(i+2)};
            for (int j = 0; j < threeElves[0].length(); j++) {
                char current = threeElves[0].charAt(j);
                if (threeElves[1].contains(String.valueOf(current)) && threeElves[2].contains(String.valueOf(current))) {
                    acc += priority(current);
                    break;
                }
            }
        }
        System.out.println("p2> " + acc);
    }

    private static int priority(char c) {
        if (Character.isUpperCase(c)) {
            return c - 38;
        } else {
            return c - 96;
        }
    }
}
