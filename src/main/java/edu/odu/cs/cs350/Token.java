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

    public void setName(String tempName) {
        name = tempName;
    }

    public String getName() {
        return name;
    }

    public void setLexeme(Lexeme tempLexeme) {
        lex = tempLexeme;
    }

    public Lexeme getLexeme() {
        return lex;
    }

    public void setLine(int tempLine) {
        line = tempLine;
    }

    public int getLine() {
        return line;
    }

    public void setColumn(int tempColumn) {
        column = tempColumn;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Token) && (this.name == ((Token) obj).getName()) && (this.lex.equals(((Token) obj).getLexeme()));
    }
}