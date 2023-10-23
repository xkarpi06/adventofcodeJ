package main.net.karpi.adventofcode.aoc2022;

import main.net.karpi.adventofcode.helpers.AoCYear;
import main.net.karpi.adventofcode.helpers.InputLoader;

import java.util.*;
import java.util.function.Predicate;

/**
 * Created by xkarpi06 on 06.10.2023
 * <p>
 * time: X min + Y min (start X:YZ)
 * <p>
 * stats:
 * Day       Time   Rank  Score       Time   Rank  Score
 */
public class Day05 {

    /**
     * The ship has a giant cargo crane capable of moving crates between stacks.
     */
    public static void main(String[] args) {
        List<String> input = new InputLoader(AoCYear.AOC_2022).loadStrings("Day05Input");
        part1(input);
        part2(input);
    }

    /**
     * The CrateMover 9000 can pick up and move one crate at a time.
     * After the rearrangement procedure completes, what crate ends up on top of each stack?
     */
    private static void part1(List<String> input) {
        craneWork(input, new Crane(Crane.Type.C9000));
    }

    private static void part2(List<String> input) {
        craneWork(input, new Crane(Crane.Type.C9001));
    }

    private static void craneWork(
            List<String> input,
            Crane crane
    ) {
        // take crates input and drop last line which is unused stack numbering
        int emptyLineIdx = input.indexOf("");
        List<String> crates = input.stream().limit(emptyLineIdx - 1).toList();
        List<String> instructions = input.stream().skip(emptyLineIdx + 1).toList();

        List<Stack<Character>> crateStacks = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            crateStacks.add(new Stack<>());
        }
        // read stacks from bottom up
        for (int i = crates.size() - 1; i > -1; i--) {
            char[] crateLetters = extractLetters(crates.get(i));
            for (int j = 0; j < crateLetters.length; j++) {
                if (crateLetters[j] != ' ') {
                    crateStacks.get(j).push(crateLetters[j]);
                }
            }
        }
        instructions.forEach(line -> {
            String[] splitted = line.split(" ");
            int move = Integer.parseInt(splitted[1]);
            int from = Integer.parseInt(splitted[3]) - 1;
            int to = Integer.parseInt(splitted[5]) - 1;
            crane.work(crateStacks, new Instruction(move, from, to));
        });
        System.out.print("result> ");
        StringBuilder result = new StringBuilder();
        for (Stack<Character> stack : crateStacks) {
            if (!stack.isEmpty()) {
                result.append(stack.peek());
            }
        }
        System.out.println(result);

    }

    private static char[] extractLetters(String line) {
        return line
                .replace("    ", " [*]")
                .replaceAll("[\\[\\] ]", "")
                .replace('*', ' ')
                .toCharArray();
    }

    private static class Crane {
        private final Type type;

        enum Type {C9000, C9001}

        public Crane(Type type) {
            this.type = type;
        }

        private void work(
                List<Stack<Character>> crateStacks,
                Instruction instruction
        ) {
            switch (type) {
                case C9000 -> workC9000(crateStacks, instruction);
                case C9001 -> workC9001(crateStacks, instruction);
            }
        }

        // moves one crate at a time
        private void workC9000(List<Stack<Character>> stacks, Instruction i) {
            for (int j = 0; j < i.move; j++) {
                Character crate = stacks.get(i.from).pop();
                stacks.get(i.to).push(crate);
            }
        }

        // moves many crates at a time
        private void workC9001(List<Stack<Character>> stacks, Instruction i) {
            List<Character> crates = new ArrayList<>();
            for (int j = 0; j < i.move; j++) {
                crates.add(stacks.get(i.from).pop());
            }
            for (int j = crates.size() - 1; j > -1; j--) {
                stacks.get(i.to).push(crates.get(j));
            }
        }
    }

    private record Instruction(int move, int from, int to) {
    }
}
