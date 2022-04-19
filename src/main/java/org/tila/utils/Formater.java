package org.tila.utils;

import org.tila.scanner.Token;
import org.tila.scanner.TokenType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Formater {
    public static String replaceWithAdditionBlank(String input, ArrayList<String> oldValues) {
        String result = input;
        for (String str : oldValues) {
            result = result.replace(str, " " + str + " ");
        }
        return result;
    }

    public static String expectError(String invalidStr, int line, List<TokenType> tokens, Token gotToken) {
        String invalidSyntaxStr = "Invalid syntax: " + invalidStr + " in line " + line;
        String expectStr;
        String gotStr = "Got " + gotToken.toString();

        if (tokens.size() == 1) {
            expectStr = "\n\t\tMay expect " + tokens.get(0);
        } else {
            expectStr = "\n\t\tMay expect one of tokens " + tokens;
        }

        return String.join(". ", invalidSyntaxStr, expectStr, gotStr);

    }
}
