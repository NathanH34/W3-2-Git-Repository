package edu.odu.cs.cs350;
public class Lexeme {
    private String name;

    public Lexeme() {
        name = "";
    }
    public Lexeme(String theName) {
        name = theName;
    }
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Lexeme) && (this.name == ((Lexeme) obj).getName());
    }
}
