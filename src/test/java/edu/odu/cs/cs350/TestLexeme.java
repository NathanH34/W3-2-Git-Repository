package edu.odu.cs.cs350;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
public class TestLexeme {

    Lexeme blank = new Lexeme();
    String defaultLexeme = "if";

    /**
    * @throws java.lang.Exception
    */
    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void testConstructor() {
        Lexeme lex = new Lexeme ();
        assertThat(lex.getName(), is(""));
        assertThat(lex, equalTo(blank));
    }

    @Test
    public void testConstructorWithParameter() {
        Lexeme lex = new Lexeme (defaultLexeme);
        assertThat(lex.getName(), is(defaultLexeme));
        assertThat(lex, not(equalTo(blank)));
    }
}
