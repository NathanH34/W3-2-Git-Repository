package edu.odu.cs.cs350;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class TestToken {

    Token blankToken = new Token();
    Lexeme blankLexeme = new Lexeme();
    double defaultLexeme = 3.14159265;
    String defaultName = "Double";
    int defaultLine = 30;
    int defaultColumn = 14;

    /**
    * @throws java.lang.Exception
    */
    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void testConstructor() {
        Token token = new Token();
        assertThat(token.getName(), equalTo(""));
        assertThat(token.getLexeme(), equalTo(blankLexeme));
        assertThat(token.getLine(), is(0));
        assertThat(token.getColumn(), is(0));
        assertThat(token, equalTo(blankToken));
    }
    
    @Test
    public void testConstructorNoLexParameter() {
        Token token = new Token(defaultName, defaultLine, defaultColumn);
        assertThat(token.getName(), equalTo(defaultName));
        assertThat(token.getLexeme(), equalTo(blankLexeme));
        assertThat(token.getLine(), equalTo(defaultLine));
        assertThat(token.getColumn(), equalTo(defaultColumn));
        assertThat(token, not(equalTo(blankToken)));
    }

    @Test
    public void testConstructorWithLexParameter() {
        
    }
}
