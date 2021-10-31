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
     *  No lexeme constructor
     * @param theName the type of TokenKinds the token is
     * @param theLine the line the token starts at
     * @param theColumn the column the token starts at
     */
    public Token(TokenKinds theName, int theLine, int theColumn) {
        name = theName;
        lex = new Lexeme();
        line = theLine;
        column = theColumn;
    }
    /**
     *  Constructor with lexeme
     * @param theName the type of TokenKinds the token is
     * @param theLex the lexeme of the Token
     * @param theLine the line the token starts at
     * @param theColumn the column the token starts at
     */
    public Token(TokenKinds theName, String theLex, int theLine, int theColumn) {
        name = theName;
        lex = new Lexeme(theLex);
        line = theLine;
        column = theColumn;
    }

    /**
     * name setter
     * @param tempName the name of type TokenKinds to be set
     */
    public void setName(TokenKinds tempName) {
        name = tempName;
    }

    /**
     *  name getter
     * @return the type of TokenKinds the Token is
     */
    public TokenKinds getName() {
        return name;
    }

    /**
     * lex setter
     * @param tempLexeme The lexeme the token will be set to
     */
    public void setLexeme(Lexeme tempLexeme) {
        lex = tempLexeme;
    }

    /**
     * lex getter
     * @return the current lexeme of the token. If the token type has no lexeme, should return the default lexeme
     */
    public Lexeme getLexeme() {
        return lex;
    }

    /**
     * line setter
     * @param tempLine the line number to be set
     */
    public void setLine(int tempLine) {
        line = tempLine;
    }

    /**
     *  line getter
     * @return line
     */
    public int getLine() {
        return line;
    }

    /**
     * column setter
     * @param tempColumn the column to be set
     */
    public void setColumn(int tempColumn) {
        column = tempColumn;
    }

    /**
     * column getter
     * @return column
     */
    public int getColumn() {
        return column;
    }

    /**
     * toString, returns a string formatted with the token type, lexeme, line and column numbers
     */
    @Override 
    public String toString() {
        return ("Token type: "+ name + " Lexeme: " + lex.toString() + " Line: " + line + " Column: " + column + "\n");
    }

    /**
     * equality comparator
     * @return true if the name and lexeme are the same for two tokens. 
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Token) && (this.name == ((Token) obj).getName()) && (this.lex.equals(((Token) obj).getLexeme()));
    }
}