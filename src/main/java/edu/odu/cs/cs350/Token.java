package edu.odu.cs.cs350;
public class Token {
    //What kind of token is it
    private TokenKinds name;
    //The lexeme associated with the token(if the token has one)
    private Lexeme lex;

    //Line and column where the token was found
    private int line;
    private int column;

    /**
     * 
     * @param theName
     * @param theLine
     * @param theColumn
     */
    public Token(TokenKinds theName, int theLine, int theColumn) {
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
    public Token(TokenKinds theName, String theLex, int theLine, int theColumn) {
        name = theName;
        lex = new Lexeme(theLex);
        line = theLine;
        column = theColumn;
    }

    public void setName(TokenKinds tempName) {
        name = tempName;
    }

    public TokenKinds getName() {
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