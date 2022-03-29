package org.tila.utils;

import java.util.ArrayList;

public class Formater {
    public static String replaceWithAdditionBlank(String input, ArrayList<String> oldValues) {
        String result = input;
        for (String str : oldValues) {
            result = result.replace(str, " " + str + " ");
        }
        return result;
    }
}
