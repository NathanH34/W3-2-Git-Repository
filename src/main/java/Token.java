package edu.odu.cs.cs350;
public class Token {
    private String name;
    private Lexeme lex;

    public Token() {
        name = "fooToken";
        lex = new Lexeme();
    }
    public String getName() {
        return name;
    }
    public Lexeme getLexeme() {
        return lex;
    }
}
