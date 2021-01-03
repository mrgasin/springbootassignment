package com.alasdoo.developercourseassignment.integrationtests.utils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class CaseFormat {
    private CaseFormat() {
    }

    public static String toLowerUnderscore(String upperCamel) {
        return Stream
                .of(upperCamel.split("(?=[A-Z])"))
                .map(String::toLowerCase)
                .map(String::trim)
                .collect(Collectors.joining("_"));
    }
}
