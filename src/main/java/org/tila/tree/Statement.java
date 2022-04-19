package org.tila.tree;

import org.tila.scanner.NonterminalEnum;
import org.tila.scanner.Token;
import org.tila.scanner.TokenType;

import java.util.ArrayList;

public abstract class Statement {
    final String statement = this.getClass().getSimpleName();
    public static class Delc extends Statement {
        final Token type;
        final Expr.ID id;

        public Delc(Token type, Expr.ID id) {
            this.type = type;
            this.id = id;
        }


        public String toString() {
            return "\"" + NonterminalEnum.DECLARATION + "\"" + ": {" + this.type.toString() + ", " + this.id.toString() + "}";
        }

    }

    public static class Assigment extends Statement {
        final Token id;
        final Token equalOp;
        final Expr expr;

        public Assigment(Token id, Token equalOp, Expr rightValue) {
            this.id = id;
            this.equalOp = equalOp;
            this.expr = rightValue;
        }

        public String toString() {
            StringBuilder result = new StringBuilder("\"" + NonterminalEnum.ASSIGNMENT + "\"" + ": {");
            result.append(this.id.toString()).append(", ");
            result.append(this.equalOp.toString()).append(", ");
            result.append(this.expr.toString()).append(", ");
            result.append("}");
            return result.toString();
        }
    }

    public static class Print extends Statement {
        final Token print;
        final Expr expr;

        public Print(Token printToken, Expr expr) {
            this.print = printToken;
            this.expr = expr;
        }

        public String toString() {
            return "\"" + NonterminalEnum.PRINT + "\"" + ": {" + this.print.toString() + ", " + this.expr.toString() + "}";
        }
    }

    public static class Loop extends Statement {
        final Token whileToken;
        final Expr expr;
        final Token doToken;
        final Token begin;
        final ArrayList<Statement> statements;
        final Token end;

        public Loop(Token whileToken, Expr expr, Token doToken, Token beginToken, ArrayList<Statement> statements, Token endToken) {
            this.whileToken = whileToken;
            this.expr = expr;
            this.doToken = doToken;
            this.begin = beginToken;
            this.statements = statements;
            this.end = endToken;
        }

        public String toString() {
            StringBuilder result = new StringBuilder("\"" + NonterminalEnum.LOOP + "\"" + ": {");
            result.append(whileToken.toString()).append(", ");
            result.append(expr.toString()).append(", ");
            result.append(doToken.toString()).append(", ");
            result.append(begin.toString()).append(", ");
            for (Statement statement : statements) {
                result.append(statement.toString()).append(", ");
            }
            result.append(end.toString());
            return result.toString();
        }
    }
}
