package org.tila.tree;

import org.tila.scanner.Token;
import org.tila.scanner.TokenType;

public abstract class Expr {
    final String exprType = this.getClass().getSimpleName();
    public static class NormalExpr extends Expr {
        final Expr left;
        final Token operator;
        final Expr right;
        public NormalExpr(Expr left, Token operator, Expr right) {
            this.left = left;
            this.operator = operator;
            this.right = right;
        }
        public String toString() {
            return this.left.toString() + ", " + this.operator.toString() + ", " + this.right.toString();
        }
    }

    public static class ID extends Expr {
        final Token token;
        public ID(Token token) {
            this.token = token;
        }

        public String toString() {
            return this.token.toString();
        }
    }

    public static class Number extends Expr {
        final Token value;
        public Number(Token value) {
            this.value = value;
        }

        public String toString() {
            return this.value.toString();
        }
    }


    public static class GroupingExpr extends Expr {
        final Token openBracket;
        final Expr expression;
        final Token closeBracket;

        public GroupingExpr(Token openBracket, Expr expression, Token closeBracket) {
            this.expression = expression;
            this.openBracket = openBracket;
            this.closeBracket = closeBracket;
        }

        public String toString() {
            StringBuilder result = new StringBuilder("\"GROUP\": {");
            result.append(this.openBracket.toString()).append(", ");
            result.append(this.expression.toString()).append(", ");
            result.append(this.closeBracket.toString()).append(", ");
            return result.toString();
        }
    }



}
