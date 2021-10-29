package edu.odu.cs.cs350;
public class Token {
    //What kind of token is it
    private String name;
    //The lexeme associated with the token(if the token has one)
    private Lexeme lex;

    //Line and column where the token was found
    private int line;
    private int column;

    public Token() {
        name = "";
        lex = new Lexeme();
        line = 0;
        column = 0;
    }
    /**
     * 
     * @param theName
     * @param theLine
     * @param theColumn
     */
    public Token(String theName, int theLine, int theColumn) {
        name = theName;
        lex = new Lexeme();
        line = theLine;
        column = theColumn;
    }
    /**
     * 
     * @param theName
     * @param theLex
     * @param theLine
     * @param theColumn
     */
    public Token(String theName, String theLex, int theLine, int theColumn) {
        name = theName;
        lex = new Lexeme(theLex);
        line = theLine;
        column = theColumn;
    }

    public String getName() {
        return name;
    }

    public Lexeme getLexeme() {
        return lex;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
}