package org.ecoBike.quu.utils;

/**
 * Created by Shelupets Denys on 23.06.2020.
 */
public class FileUtils {
    private static final String PATH_TO_PROJECT = "data" + getSeparator() + "project";









    private static String getSeparator() {
        String osName = System.getProperty("os.name");
        String separator;

        if (osName.contains("Mac") || osName.contains("OS X") || osName.contains("Linux")) {
            separator = "/";
        } else {
            separator = "\\";
        }
        return separator;
    }
}
