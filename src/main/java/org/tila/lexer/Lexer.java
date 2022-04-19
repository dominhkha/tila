package org.tila.lexer;

import org.tila.log.TilaLogger;
import org.tila.parser.Parser;
import org.tila.scanner.NonterminalEnum;
import org.tila.scanner.Scanner;
import org.tila.scanner.Token;
import org.tila.scanner.TokenType;
import org.tila.tilaexception.ScannerException;
import org.tila.utils.Formater;

import java.util.Arrays;
import java.util.List;

public class Lexer {

    private int currentIndex;
    private final List<Token> tokens;

    private Token currentToken;

    public Lexer(String inputCode) {
        Scanner scanner = new Scanner(inputCode);
        scanner.readTokens();
        this.tokens = scanner.getTokens();
        this.currentIndex = 0;
        this.currentToken = this.tokens.get(this.currentIndex);
    }

    public boolean prepareNextToken() {
        this.currentIndex += 1;
        if (this.currentIndex > this.tokens.size()) {
            return false;
        }
        this.currentToken = this.tokens.get(this.currentIndex);
        return true;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public Token getCurrentToken() {
        return currentToken;
    }

    public void setCurrentToken(Token currentToken) {
        this.currentToken = currentToken;
    }

    public boolean isAtEnd() {
        return this.currentIndex == this.tokens.size();
    }


    public Token peek() {
        return this.tokens.get(this.currentIndex);
    }

    public Token previous() {
        return this.tokens.get(this.currentIndex - 1);
    }

    public Token advance() {
        if (!this.isAtEnd()) this.currentIndex += 1;
        return this.previous();
    }

    public boolean check(TokenType type) {
        if (this.isAtEnd()) return false;
        return this.peek().getTokenType() == type;
    }

    public boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (this.check(type)) {
                return true;
            }
        }
        return false;
    }

    public Token consume(TokenType type, NonterminalEnum nonterminalEnum) {
        if (check(type)) return advance();

        String errorMsg = Formater.expectError(nonterminalEnum.toString(), this.peek().getLine(), Arrays.asList(type), this.peek());
        TilaLogger.ERROR(errorMsg);

        return null;
    }

    public Token consumes(NonterminalEnum nonterminalEnum, TokenType... types) {
        if (match(types)) return advance();

        String errorMsg = Formater.expectError(nonterminalEnum.toString(), this.peek().getLine(), Arrays.asList(types), this.peek());
        TilaLogger.ERROR(errorMsg);

        return null;
    }
}
