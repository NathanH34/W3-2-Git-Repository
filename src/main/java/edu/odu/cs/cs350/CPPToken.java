package edu.odu.cs.cs350;
public class CPPToken {
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
    public CPPToken(TokenKinds theName, int theLine, int theColumn) {
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
    public CPPToken(TokenKinds theName, String theLex, int theLine, int theColumn) {
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
    public String toString() {
        return ("Token type: "+ name + " Lexeme: " + lex.toString() + " Line: " + line + " Column: " + column + "\n");
    }

    /**
     * equality comparator
     * @return checks if rhs is a CPPToken, and if its Name and Lexeme are equal to lhs Name and Lexeme
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof CPPToken) && (this.name == ((CPPToken) obj).getName()) && (this.lex.equals(((CPPToken) obj).getLexeme()));
    }
}