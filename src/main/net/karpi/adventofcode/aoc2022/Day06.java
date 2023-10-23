package main.net.karpi.adventofcode.aoc2022;

import main.net.karpi.adventofcode.helpers.AoCYear;
import main.net.karpi.adventofcode.helpers.InputLoader;

import java.util.*;

/**
 * Created by xkarpi06 on 09.10.2023
 * <p>
 * time: X min + Y min (start X:YZ)
 * <p>
 * stats:
 * Day       Time   Rank  Score       Time   Rank  Score
 */
public class Day06 {

    public static void main(String[] args) {
        List<String> input = new InputLoader(AoCYear.AOC_2022).loadStrings("Day06Input");
        part1(input.get(0));
        part2(input.get(0));
    }

    /**
     * To fix the communication system, you need to add a subroutine to the device that detects a start-of-packet
     * marker. It is indicated by a sequence of four characters that are all different.
     *
     * How many characters need to be processed before the first start-of-packet marker is detected?
     */
    private static void part1(String input) {
        System.out.println("p1> " + findMarker(input, 4));
    }

    private static void part2(String input) {
        System.out.println("p1> " + findMarker(input, 14));
    }

    private static int findMarker(String stream, int markerSize) {
        for (int i = 0; i <= stream.length() - markerSize; i++) {
            String window = stream.substring(i, i + markerSize);
            if (distinctChars(window)) {
                return i + markerSize;
            }
        }
        return -1;
//        val index = stream.windowed(markerSize).indexOfFirst { it.toSet().size == markerSize }
//        return if (index >= 0) index + markerSize else index
    }

    private static boolean distinctChars(String str) {
        // Create a boolean array to track character occurrences
        boolean[] charSet = new boolean[256]; // Assuming ASCII characters

        for (int i = 0; i < str.length(); i++) {
            int charValue = str.charAt(i);

            // If the character is already marked as seen, return false
            if (charSet[charValue]) {
                return false;
            }

            // Mark the character as seen
            charSet[charValue] = true;
        }

        // If we reached this point, all characters are distinct
        return true;
    }

}
