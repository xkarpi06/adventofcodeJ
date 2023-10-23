package main.net.karpi.adventofcode.aoc2022;

import main.net.karpi.adventofcode.helpers.AoCYear;
import main.net.karpi.adventofcode.helpers.InputLoader;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by xkarpi06 on 09.10.2023
 * <p>
 * time: X min + Y min (start X:YZ)
 * <p>
 * stats:
 * Day       Time   Rank  Score       Time   Rank  Score
 */
public class Day07 {

    /**
     * You browse around the filesystem to assess the situation and save the resulting terminal output
     */
    public static void main(String[] args) {
        List<String> input = new InputLoader(AoCYear.AOC_2022).loadStrings("Day07Input");
        var cmdLines = Parser.parseInput(input);
        var interpreter = new Interpreter();
        // skip first instruction & interpret terminal output
        cmdLines.forEach(interpreter::readLine);
//        cmdLines.stream().skip(1).toList().forEach(interpreter::readLine);

        part1(interpreter.getDirs());
        part2(interpreter.getDirs());
    }

    /**
     * Find all directories with a total size of at most 100000. What is the sum of the total sizes of those
     * directories?
     */
    private static void part1(List<Directory> dirs) {
        var sum = dirs.stream().filter(d -> d.getSize() <= 100_000).mapToInt(Directory::getSize).sum();
        System.out.println("p1> " + sum);
    }

    /**
     * The total disk space available to the filesystem is 70000000. To run the update, you need unused space of at
     * least 30000000. Find the smallest directory that, if deleted, would free up enough space on the filesystem to
     * run the update. What is the total size of that directory?
     */
    private static void part2(List<Directory> dirs) {
        int totalDiskSpace = 70_000_000;
        int updateNeeds = 30_000_000;
        int occupiedSpace = dirs.stream().filter(d -> d.getName().equals("/")).toList().get(0).getSize();
        int deleteAtLeast = updateNeeds - (totalDiskSpace - occupiedSpace);
        OptionalInt answer = dirs.stream().filter(d -> d.getSize() > deleteAtLeast).mapToInt(Directory::getSize).min();
        System.out.println("p2> " + (answer.isPresent() ? answer.getAsInt() : "err"));
    }

    private sealed interface CmdLine {

        sealed interface Cmd extends CmdLine {
            record Ls() implements Cmd {}
            record CdOut() implements Cmd {}
            record CdIn(String dir) implements Cmd {}
            record Unknown() implements Cmd {}
        }

        sealed interface Answer extends CmdLine {
            record Dir(String name) implements Answer {}
            record File(String name, int size) implements Answer {}
        }
    }

    private static class Parser {
        static List<CmdLine> parseInput(List<String> input) {
            return input.stream().map(line -> {
                if (line.startsWith("$")) {
                    return parseCmd(line);
                } else if (line.startsWith("dir")) {
                    return parseDir(line);
                } else {
                    return parseFile(line);
                }
            }).toList();
        }

        private static CmdLine.Answer.File parseFile(String line) {
            var sizeAndName = line.split(" ");
            return new CmdLine.Answer.File(sizeAndName[1], Integer.parseInt(sizeAndName[0]));
        }

        private static CmdLine.Cmd parseCmd(String line) {
            var parsed = line.split(" ");
            return switch (parsed[1]) {
                case "ls" -> new CmdLine.Cmd.Ls();
                case "cd" -> switch (parsed[2]) {
                    case ".." -> new CmdLine.Cmd.CdOut();
                    default -> new CmdLine.Cmd.CdIn(parsed[2]);
                };
                default -> new CmdLine.Cmd.Unknown();
            };
        }

        private static CmdLine.Answer.Dir parseDir(String line) {
            return new CmdLine.Answer.Dir(Arrays.asList(line.split(" ")).get(1));
        }
    }

    /**
     * Second solution to the Day 7 challenge.
     * This solution does not create a tree model of the filesystem, but only puts dirs into stack as they are entered (dirs
     * are popped as we step out of them) and for every file found, its size is added to all dirs inside this stack.
     */
    private static class Interpreter {
        private final Stack<Directory> dirStack = new Stack<>();

        // add dirs to stack & for each file add its size to all directories in stack!
        private final List<Directory> allDirs = new ArrayList<>();

        // navigate up, down, add file, ...
        void readLine(CmdLine line) {
            if (line instanceof CmdLine.Cmd.CdIn cdIn) {
                dirStack.push(new Directory(cdIn.dir));
            } else if (line instanceof CmdLine.Cmd.CdOut) {
                allDirs.add(dirStack.pop());
            } else if (line instanceof CmdLine.Answer.File file) {
                dirStack.forEach(stack -> stack.addFile(file.size));
            }
        }

        List<Directory> getDirs() {
            var x = new ArrayList<Directory>(allDirs);
            x.addAll(dirStack);
            return x;
        }
    }

    private static class Directory {
        private final String name;
        private int size = 0;

        public Directory(String name) {
            this.name = name;
        }

        void addFile(int size) {
            this.size += size;
        }

        public String getName() {
            return name;
        }

        public int getSize() {
            return size;
        }

        @Override
        public String toString() {
            return "Directory{" +
                    "name='" + name + '\'' +
                    ", size=" + size +
                    '}';
        }
    }
}
