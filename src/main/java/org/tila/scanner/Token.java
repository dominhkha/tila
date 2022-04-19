package org.tila.scanner;

/**
 * Token Example : (TokenType: INT, value: 10, isCheckRegrex: true, line: 3)
 */
public class Token {
    /**
     * The type of token. Example: ID, NUMBER, etc.
     */
    private TokenType tokenType;

    /**
     * Text in source code
     */
    private String value;

    /**
     * identify this token by regular expression or match exact string
     */
    private boolean isCheckRegrex;

    /**
     * The line number where this token appears in source code
     */
    private int line;

    public Token(TokenType tokenType, String value, boolean isCheckRegrex) {
        this.tokenType = tokenType;
        this.value = value;
        this.isCheckRegrex = isCheckRegrex;
        this.line = 0;
    }

    public Token(TokenType tokenType, String value, int line) {
        this.tokenType = tokenType;
        this.value = value;
        this.isCheckRegrex = false;
        this.line = line;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isCheckRegrex() {
        return isCheckRegrex;
    }

    public void setCheckRegrex(boolean checkRegrex) {
        isCheckRegrex = checkRegrex;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String toString() {
        return "\"" + this.tokenType.toString() +"\""+ ": {\"Type\": \"" + this.getTokenType().toString() + "\", \"Value\": \"" + this.getValue() + "\"" + ", \"Line\": " + String.valueOf(this.line) + "}";
    }
}
