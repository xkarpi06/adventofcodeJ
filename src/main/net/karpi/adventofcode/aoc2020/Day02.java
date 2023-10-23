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
public class Day02 {

    public static void main(String[] args) {
        List<String> input = new InputLoader(AoCYear.AOC_2020).loadStrings("Day02Input");
        var lines = parseInput(input);
        part1(lines);
        part2(lines);
    }

    private static void part1(List<PwdPolicy> input) {
        var acc = 0;
        for (PwdPolicy p : input) {
            var oldLen = p.pwd.length();
            var newLen = p.pwd.replace("" + p.letter, "").length();
            var times = oldLen - newLen;
            if (times >= p.min && times <= p.max) {
                acc++;
            }
        }
        System.out.println("p1> " + acc);
    }

    private static void part2(List<PwdPolicy> input) {
        var acc = 0;
        for (PwdPolicy p : input) {
            var count = 0;
            if (p.pwd.charAt(p.min - 1) == p.letter) {
                count++;
            }
            if (p.pwd.charAt(p.max - 1) == p.letter) {
                count++;
            }
            if (count == 1) {
                acc++;
            }
        }
        System.out.println("p2> " + acc);
    }

    private static List<PwdPolicy> parseInput(List<String> input) {
        List<PwdPolicy> output = new ArrayList<>();
        for (String s : input) {
            var splitted = s.split(" ");
            var minMax = splitted[0].split("-");
            output.add(new PwdPolicy(
                    splitted[1].charAt(0),
                    Integer.parseInt(minMax[0]),
                    Integer.parseInt(minMax[1]),
                    splitted[2]
            ));
        }
        return output;
    }

    private record PwdPolicy(
            char letter,
            int min,
            int max,
            String pwd
    ) {}
}
