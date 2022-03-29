package org.tila.constants;

import org.tila.scanner.Token;
import org.tila.scanner.TokenType;

import java.util.ArrayList;
import java.util.Arrays;

public final class ConstantToken {
    public static final Token begin_token = new Token(TokenType.BEGIN, "begin", false);
    public static final Token end_token = new Token(TokenType.END, "end", false);
    public static final Token print_token = new Token(TokenType.PRINT, "print", false);

    public static final Token add_token = new Token(TokenType.ADD, "+", false);
    public static final Token multiply_token = new Token(TokenType.MULTIPLY, "*", false);
    public static final Token subtract_token = new Token(TokenType.SUBTRACT, "-", false);
    public static final Token pow_token = new Token(TokenType.POW, "^", false);
    public static final Token equal_token = new Token(TokenType.EQUAL, "=", false);


    public static final Token int_token = new Token(TokenType.INT, "int", false);
    public static final Token open_bracket = new Token(TokenType.OPEN_BRACKET, "(", false);
    public static final Token close_bracket = new Token(TokenType.CLOSE_BRACKET, ")", false);
    public static final Token while_token = new Token(TokenType.WHILE, "while", false);
    public static final Token do_token = new Token(TokenType.DO, "do", false);
    public static final Token semicolon_token = new Token(TokenType.SEMICOLON, ";", false);


    public static final Token number_token = new Token(TokenType.NUMBER, "0|[1-9][0-9]*", true);
    public static final Token id_token = new Token(TokenType.ID, "([a-z]|[A-Z])([a-z]|[A-Z]|[0-9])*", true);

    public static final ArrayList<Token> tokenList = new ArrayList<Token>(Arrays.asList(begin_token, end_token, print_token, add_token,
            multiply_token, subtract_token, pow_token, int_token, open_bracket, close_bracket, while_token, do_token, semicolon_token, equal_token, number_token, id_token));
}
