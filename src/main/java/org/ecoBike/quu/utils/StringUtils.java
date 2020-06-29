package org.ecoBike.quu.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Shelupets Denys on 23.06.2020.
 */
public class StringUtils {
    private static final String PATTERN_REPLACE_SPEEDELEC = "(?i)\\bSPEEDELEC\\b";
    private static final String PATTERN_REPLACE_ELECTRO_BIKE = "(?i)\\bE-BIKE\\b";
    private static final String PATTERN_REPLACE_FOLDING_BIKE = "(?i)\\bFOLDING BIKE\\b";

    public static void writeText(String text) {
        System.out.println(text);
    }

    public static void writeFormatText(String text, String insertText) {
        System.out.println(String.format(text, insertText));
    }

    public static String replaceAllType(String readString) {
        return readString.replaceAll(PATTERN_REPLACE_SPEEDELEC, "").replaceAll(PATTERN_REPLACE_ELECTRO_BIKE, "").replaceAll(PATTERN_REPLACE_FOLDING_BIKE, "");
    }

    public static List<String> getTokenListByPattern(String readString, String splitPattern) {
        return Arrays.stream(readString.split(splitPattern)).map(String::trim).collect(Collectors.toList());
    }

    public static void writeErrorText(String text) {
        System.err.println(text);
    }


}
