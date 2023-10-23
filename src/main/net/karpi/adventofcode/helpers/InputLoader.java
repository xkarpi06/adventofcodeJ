package main.net.karpi.adventofcode.helpers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InputLoader {

    private AoCYear year;

    public InputLoader(AoCYear year) {
        this.year = year;
    }

    public List<Integer> loadInts(String resource) {
        return loadStrings(resource).stream()
                .map(Integer::parseInt)
                .toList();
    }

    public List<String> loadStrings(String resource) {
        String filePath = "res/" + year.getResourcePath() + "/" + resource;
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
