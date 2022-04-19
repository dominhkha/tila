package org.tila.parser;

import com.google.gson.Gson;
import org.tila.lexer.Lexer;
import org.tila.log.TilaLogger;
import org.tila.scanner.NonterminalEnum;
import org.tila.scanner.Token;
import org.tila.scanner.TokenType;
import org.tila.tilaexception.ScannerException;
import org.tila.tree.*;
import org.tila.utils.FileUtils;
import org.tila.utils.Formater;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    private final Lexer lexer;
    private final List<Token> tokens;
    private int currentIndex;
    private final String sharedErrorMsg = "Invalid Statement in line ";

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.tokens = lexer.getTokens();
        this.currentIndex = 0;
    }


    private Expr primary(NonterminalEnum nonterminalEnum) {
        if (this.lexer.match(TokenType.NUMBER)) {
            return new Expr.Number(this.lexer.consume(TokenType.NUMBER, nonterminalEnum));
        }

        if (this.lexer.match(TokenType.ID)) {
            return new Expr.ID(this.lexer.consume(TokenType.ID, nonterminalEnum));
        }

        if (this.lexer.match(TokenType.OPEN_BRACKET)) {
            Token openBracket = this.lexer.consume(TokenType.OPEN_BRACKET, nonterminalEnum);
            Expr expr = this.parseExpr();
            Token closeBracket = this.lexer.consume(TokenType.CLOSE_BRACKET, nonterminalEnum);
            return new Expr.GroupingExpr(openBracket, expr, closeBracket);
        }

//        if no expected tokens match
        String errorMsg = Formater.expectError(nonterminalEnum.toString(), this.lexer.peek().getLine(), Arrays.asList(TokenType.NUMBER, TokenType.ID, TokenType.OPEN_BRACKET), this.lexer.peek());
        TilaLogger.ERROR(errorMsg);
        return null;

    }

    private Statement parseDecl() {
        if (this.lexer.match(TokenType.INT)) {
            Token type = this.lexer.consume(TokenType.INT, NonterminalEnum.DECLARATION);
            Token idName = this.lexer.consume(TokenType.ID, NonterminalEnum.DECLARATION);
            Expr.ID id = new Expr.ID(idName);
            return new Statement.Delc(type, id);
        }


        String errorMsg = Formater.expectError("Declaration", this.lexer.peek().getLine(), Arrays.asList(TokenType.INT), this.lexer.peek());
        TilaLogger.ERROR(errorMsg);
        return null;
    }

    private Statement parseAssignment() {
        Token id = this.lexer.consume(TokenType.ID, NonterminalEnum.ASSIGNMENT);
        if (this.lexer.match(TokenType.EQUAL)) {
            Token operator = this.lexer.consume(TokenType.EQUAL, NonterminalEnum.ASSIGNMENT);
            Expr right = this.parseExpr();
            return new Statement.Assigment(id, operator, right);
        }

        String errorMsg = Formater.expectError("Assignment", this.lexer.peek().getLine(), Arrays.asList(TokenType.EQUAL), this.lexer.peek());
        TilaLogger.ERROR(errorMsg);

        return null;

    }

    private Expr parseExpr() {
        Expr expr = this.primary(NonterminalEnum.EXPRESSION);

        while (this.lexer.match(TokenType.MULTIPLY, TokenType.SUBTRACT, TokenType.POW)) {
            Token operator = this.lexer.consumes(NonterminalEnum.EXPRESSION, TokenType.MULTIPLY, TokenType.SUBTRACT, TokenType.POW);
            Expr right = this.primary(NonterminalEnum.EXPRESSION);
            expr = new Expr.NormalExpr(expr, operator, right);
        }

        return expr;
    }


    private Statement parseLoop() {
        Token whileToken = this.lexer.consume(TokenType.WHILE, NonterminalEnum.LOOP);
        Expr condition = this.parseExpr();

        Token doToken = this.lexer.consume(TokenType.DO, NonterminalEnum.LOOP);
        Token beginToken = this.lexer.consume(TokenType.BEGIN, NonterminalEnum.LOOP);
        ArrayList<Statement> statements = this.parseStatements();
        Token endToken = this.lexer.consume(TokenType.END, NonterminalEnum.LOOP);
        return new Statement.Loop(whileToken, condition, doToken, beginToken, statements, endToken);
    }

    private Statement parseStatement() {

        if (this.lexer.check(TokenType.INT)) {
            return this.parseDecl();
        }
        if (this.lexer.check(TokenType.WHILE)) {
            return this.parseLoop();
        }
        if (this.lexer.check(TokenType.ID)) {
            return this.parseAssignment();
        }
        if (this.lexer.check(TokenType.PRINT)) {
            return this.parsePrint();
        }

//        if expected tokens match
        String errorStr = Formater.expectError("Statement", this.lexer.peek().getLine(), Arrays.asList(TokenType.INT, TokenType.WHILE, TokenType.ID, TokenType.PRINT), this.lexer.peek());
        TilaLogger.ERROR(errorStr);
        return null;
    }

    private Statement parsePrint() {
        if (this.lexer.check(TokenType.PRINT)) {
            Token printToken = this.lexer.consume(TokenType.PRINT, NonterminalEnum.PRINT);
            Expr expr = this.parseExpr();
            return new Statement.Print(printToken, expr);
        }
        String errorStr = Formater.expectError("Print", this.lexer.peek().getLine(), Arrays.asList(TokenType.PRINT), this.lexer.peek());
        TilaLogger.ERROR(errorStr);
        return null;
    }

    private ArrayList<Statement> parseStatements() {
        ArrayList<Statement> statements = new ArrayList<Statement>();
        while (true) {
            if (this.lexer.check(TokenType.END)) {
                break;
            }
            Statement stmt = parseStatement();
            statements.add(stmt);
            this.lexer.consume(TokenType.SEMICOLON, NonterminalEnum.STATEMENT);
        }
        return statements;
    }


    public Program parseProgram() {
        Token beginToken = this.lexer.consume(TokenType.BEGIN, NonterminalEnum.SYNTAX);
        ArrayList<Statement> statements = this.parseStatements();
        Token endToken = this.lexer.consume(TokenType.END, NonterminalEnum.SYNTAX);
        return new Program(beginToken, statements, endToken);
    }

    public static void exit() {
        System.exit(0);
    }

    public static void main(String[] args) throws IOException {
//        String inputFilePath = "D:\\tila\\local\\input.txt";
        String inputFilePath = args[0];
        FileUtils fileUtils = new FileUtils(inputFilePath);
        fileUtils.readFile();
        String inputCode = fileUtils.getContent();

        Lexer lexer = new Lexer(inputCode);
        Parser parser = new Parser(lexer);
        Program program = parser.parseProgram();
//        program.print();
//        System.out.println(program);
        Gson gson = new Gson();

        String json = gson.toJson(program);
        json = "{\"Program\":" + json + "}";
        TilaLogger.INFO("syntax tree: \n" + json);

//        System.out.println(json);
    }
}
