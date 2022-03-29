package org.tila.scanner;

public class Token {
    private TokenType tokenType;
    private String value;
    private boolean isCheckRegrex;
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

    public String toString() {
        return "(Type: " + this.getTokenType() + ", Value: \"" + this.getValue() + "\"" + ", Line: " + String.valueOf(this.line) + ")";
    }
}
