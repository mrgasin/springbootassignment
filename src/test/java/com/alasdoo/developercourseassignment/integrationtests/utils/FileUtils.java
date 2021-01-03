package com.alasdoo.developercourseassignment.integrationtests.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileUtils {
    private FileUtils() {
    }

    private static List<String> readPropertiesFile() {
        Path path;
        try {
            path = Paths.get(FileUtils.class.getClassLoader()
                    .getResource("application.properties").toURI());
            return Files.lines(path).collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static Map<String, String> convertToMap(List<String> values) {

        Map<String, String> map = new HashMap<>();
        values.forEach(string -> {
            if (string.contains("=")) {
                String[] splitted = string.toLowerCase().split("=");
                String key = splitted[0].trim();
                String value = splitted.length > 1 ? splitted[1].trim() : "";
                map.put(key, value);
            }
        });
        return map;
    }

    public static String getValue(String key) {
        Map<String, String> map = convertToMap(readPropertiesFile());
        return map.get(key);
    }
}
