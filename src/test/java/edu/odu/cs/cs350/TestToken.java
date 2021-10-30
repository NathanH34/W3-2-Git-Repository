package edu.odu.cs.cs350;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class TestToken {

    Lexeme blankLexeme = new Lexeme();
    double defaultLexeme = 3.14159265;
    TokenKinds defaultName = TokenKinds.DOUBLE;
    int defaultLine = 30;
    int defaultColumn = 14;

    /**
    * @throws java.lang.Exception
    */
    @BeforeEach
    public void setUp() throws Exception {
    }


    
    @Test
    public void testConstructorNoLexParameter() {
        Token token = new Token(defaultName, defaultLine, defaultColumn);
        assertThat(token.getName(), equalTo(defaultName));
        assertThat(token.getLexeme(), equalTo(blankLexeme));
        assertThat(token.getLine(), equalTo(defaultLine));
        assertThat(token.getColumn(), equalTo(defaultColumn));
    }

    @Test
    public void testConstructorWithLexParameter() {
        
    }
}
