package com.example.pollenrichbeandiissuedemo.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RouteUtils {

    public static String resolveProperty(String exchangeProperty) {
        return "${exchangeProperty.%s}".formatted(exchangeProperty);
    }

    public static String resolveHeader(String header) {
        return "${header.%s}".formatted(header);
    }

}
