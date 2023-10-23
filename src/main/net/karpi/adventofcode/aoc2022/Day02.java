package main.net.karpi.adventofcode.aoc2022;

import main.net.karpi.adventofcode.helpers.AoCYear;
import main.net.karpi.adventofcode.helpers.InputLoader;

import java.util.*;

/**
 * Created by xkarpi06 on 13.09.2023
 * <p>
 * time: X min + Y min (start X:YZ)
 * <p>
 * stats:
 * Day       Time   Rank  Score       Time   Rank  Score
 */
public class Day02 {

    public static void main(String[] args) {
        List<String> input = new InputLoader(AoCYear.AOC_2022).loadStrings("Day02Input");
        part1(input);
        part2(input);
    }

    private static void part1(List<String> input) {
        var acc = 0;
        for (String value : input) {
            var duel = Arrays.stream(value.split(" ")).map(x -> RPS.fromChar(x.charAt(0))).toList();
            var outcome = fight(duel.get(1), duel.get(0));
//            System.out.println(duel + " -> " + outcome);
            acc += outcome.pts + duel.get(1).score;
        }
        System.out.println("p1> pts: " + acc);
    }

    private static void part2(List<String> input) {
        var acc = 0;
        for (String value : input) {

        }
        System.out.println("p2> TODO ");
    }

    private enum RPS {
        ROCK(1),
        PAPER(2),
        SCISSORS(3);

        private int score;

        RPS(int score) {
            this.score = score;
        }

        private static RPS fromChar(char c) {
            switch (c) {
                case 'X':
                case 'A':
                    return ROCK;
                case 'Y':
                case 'B':
                    return PAPER;
                default:
                    return SCISSORS;
            }
        }

        private RPS beats() {
            switch (this) {
                case ROCK: return SCISSORS;
                case PAPER: return ROCK;
                default: return PAPER;
            }
        }
    }

    private enum Outcome {
        LOST(0),
        DRAW(3),
        WIN(6);

        private int pts;

        Outcome(int pts) {
            this.pts = pts;
        }

        private static Outcome fromChar(char c) {
            switch (c) {
                case 'X':
                    return LOST;
                case 'Y':
                    return DRAW;
                default:
                    return WIN;
            }
        }
    }

    private static Outcome fight(RPS my, RPS his) {
        if (my.beats() == his) {
            return Outcome.WIN;
        } else if (his.beats() == my) {
            return Outcome.LOST;
        } else {
            return Outcome.DRAW;
        }
    }

    private static RPS getMoveByOutcome(RPS his, Outcome outcome) {
        for (RPS it : RPS.values()) {
            if (fight(it, his) == outcome) {
                return it;
            }
        }
        return RPS.ROCK;
    }
}
