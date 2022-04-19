package org.tila.tree;

import org.tila.scanner.NonterminalEnum;
import org.tila.scanner.Token;

import java.util.ArrayList;

public class Program {
    private final Token begin;
    private final ArrayList<Statement> statements;
    private final Token end;


    public Program(Token begin, ArrayList<Statement> statements, Token end) {
        this.begin = begin;
        this.statements = statements;
        this.end = end;
    }

    public String toString() {
        StringBuilder result = new StringBuilder("\"" + NonterminalEnum.PROGRAM + "\"" + ": {");
        result.append(begin.toString()).append(", ");
        result.append("\"" + NonterminalEnum.STATEMENTS + "\"" + ": [");
        for (int i = 0; i < statements.size(); i++) {
            if (i == statements.size() - 1) {
                result.append(statements.get(i).toString());
            } else {
                result.append(statements.get(i).toString()).append(", ");
            }
        }
        result.append("],");
        result.append(end.toString());
        return result.toString();

    }

    public void print() {
        System.out.println("Program: ");
        System.out.println(this);
    }
}
