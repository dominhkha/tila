package org.tila.scanner;

import org.tila.constants.ConstantToken;
import org.tila.utils.FileUtils;
import org.tila.utils.Formater;

import java.util.ArrayList;
import java.util.Arrays;

public class MyScanner {
    /**
     * input code as text file
     */
    private final String inputCode;
    /**
     * number of character in text file
     */
    private final int codeLength;
    /**
     * the present index while iterating inputCode
     */
    private int currentIndex;
    /**
     * the present line number while iteration inputCode
     */
    private int currentLine;
    /**
     * List of result tokens
     */
    private ArrayList<Token> tokens;
    /**
     * replace these list of values by additional white space.
     * Example: ';' will be replaced by ' ; '
     */
    private final ArrayList<String> formatReplacements = new ArrayList<String>(Arrays.asList(ConstantToken.add_token.getValue(),
            ConstantToken.multiply_token.getValue(), ConstantToken.subtract_token.getValue(), ConstantToken.pow_token.getValue(),
            ConstantToken.equal_token.getValue(), ConstantToken.open_bracket.getValue(), ConstantToken.close_bracket.getValue(),
            ConstantToken.semicolon_token.getValue()));

    public MyScanner(String inputCode) {
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
        myScanner.readTokens();
        System.out.println(myScanner);
    }

    public void readTokens() {
        while (!this.isEndOfCode()) {
            char currentChar = this.inputCode.charAt(this.currentIndex);
            if (Arrays.asList(' ', '\r', '\t', '\n').contains(currentChar)) {
                this.skipWhiteSpace();
            } else {
                String currentTokenStr = this.readCurrentTokenStr();
                Token currentToken = null;
                // iter over built-in list of tokens, if the current word matches visiting token by regrex or exact string, then break
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

                // the current word does not match any built-int token
                if (currentToken == null) {
                    throw new ScannerException("\"" + currentTokenStr + "\" is not recognized!");
                }
                this.tokens.add(currentToken);
            }
        }
    }

    /**
     * @return the string starts from current white space to the next white space
     */
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

    /**
     * ignore all white spaces and go to the next character
     */
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
