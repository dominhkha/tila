package org.tila.utils;

import org.tila.scanner.Token;
import org.tila.scanner.TokenType;

import java.util.ArrayList;
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
//        Example: ERROR: Invalid syntax: EXPRESSION in line 5.
//		            May expect CLOSE_BRACKET. Got "SEMICOLON": {"Type": "SEMICOLON", "Value": ";", "Line": 5}

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
