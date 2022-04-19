package org.tila.tree;

import org.tila.scanner.Token;

public class TerminalNode {

    private Token token;

    private ParseTree intermediateRoot;

    public TerminalNode(Token token) {
        this.token = token;
        this.intermediateRoot = null;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public ParseTree getIntermediateRoot() {
        return intermediateRoot;
    }

    public void setIntermediateRoot(ParseTree intermediateRoot) {
        this.intermediateRoot = intermediateRoot;
    }

}
