package org.tila.scanner;

import org.tila.constants.ConstantToken;
import org.tila.utils.FileUtils;
import org.tila.utils.Formater;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MyScanner {
    private String inputCode;
    //    private Token currentToken;
    private int currentIndex;
    private int currentLine;
    private int codeLength;
    private ArrayList<Token> tokens;
    private final ArrayList<String> formatReplacements = new ArrayList<String>(Arrays.asList(ConstantToken.add_token.getValue(),
            ConstantToken.multiply_token.getValue(), ConstantToken.subtract_token.getValue(), ConstantToken.pow_token.getValue(),
            ConstantToken.equal_token.getValue(), ConstantToken.open_bracket.getValue(), ConstantToken.close_bracket.getValue(),
            ConstantToken.semicolon_token.getValue()));

    public MyScanner(String inputCode) {
//        this.currentToken = null;
        this.inputCode = Formater.replaceWithAdditionBlank(inputCode, this.formatReplacements);
        this.currentIndex = 0;
        this.currentLine = 1;
        this.codeLength = this.inputCode.length();
        this.tokens = new ArrayList<Token>();
    }

    public static void main(String[] args) {
        String inputFilePath = "D:\\tila\\local\\input.txt";
        FileUtils fileUtils = new FileUtils(inputFilePath);
        fileUtils.readFile();
        String inputCode = fileUtils.getContent();
        MyScanner myScanner = new MyScanner(inputCode);
        myScanner.readToken();
        System.out.println(myScanner);
    }

    public boolean readToken() {
        while (!this.isEndOfCode()) {
            char currentChar = this.inputCode.charAt(this.currentIndex);
            if (Arrays.asList(' ', '\r', '\t', '\n').contains(currentChar)) {
                this.skipWhiteSpace();
            } else {
                String currentTokenStr = this.readCurrentTokenStr();
                Token currentToken = null;
                for (int i = 0; i < ConstantToken.tokenList.size(); i++) {
                    Token t = ConstantToken.tokenList.get(i);
                    if (t.isCheckRegrex()) {
                        if (currentTokenStr.matches(t.getValue())) {
                            currentToken = new Token(t.getTokenType(), currentTokenStr, this.currentLine);
                            break;
                        }
                    } else if (currentTokenStr.equals(t.getValue())) {
                        currentToken = new Token(t.getTokenType(), currentTokenStr, this.currentLine);
                        break;
                    }
                }
                if (currentToken == null) {
                    throw new ScannerException("\"" + currentTokenStr + "\" is not recognized!");
                }
                this.tokens.add(currentToken);
            }
        }
        return true;
    }


    private String readCurrentTokenStr() {
        StringBuilder result = new StringBuilder();
        char currentChar = this.inputCode.charAt(this.currentIndex);
        while (!isEndOfCode() && !Arrays.asList(' ', '\r', '\t', '\n').contains(currentChar)) {
            result.append(currentChar);
            this.currentIndex++;
            if (this.isEndOfCode()) break;
            currentChar = this.inputCode.charAt(this.currentIndex);
        }
        return result.toString();
    }

    private void skipWhiteSpace() {
        while (!isEndOfCode()) {
            if (Arrays.asList(' ', '\r', '\t', '\n').contains(this.inputCode.charAt(this.currentIndex))) {
                if (this.inputCode.charAt(this.currentIndex) == '\n') {
                    this.currentLine++;
                }
                this.currentIndex++;
            } else {
                break;
            }
        }
    }

    private boolean isEndOfCode() {
        return this.currentIndex >= this.codeLength;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Token token : this.tokens) {
            result.append(token.toString()).append("\n");
        }
        return result.toString();

    }
}
