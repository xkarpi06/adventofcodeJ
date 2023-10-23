package main.net.karpi.adventofcode.helpers;

public enum AoCYear {
    AOC_2020("aoc2020"),
    AOC_2022("aoc2022"),
    AOC_2023("aoc2023");

    private final String resourcePath;

    AoCYear(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getResourcePath() {
        return resourcePath;
    }
}
