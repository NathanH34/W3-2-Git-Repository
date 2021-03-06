package edu.odu.cs.cs350;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class TestCPPToken {

    Lexeme blankLexeme = new Lexeme();
    String defaultLexValue = "3.14159265";
    Lexeme defaultLex = new Lexeme(defaultLexValue);
    TokenKinds defaultName = TokenKinds.DOUBLE;
    int defaultLine = 30;
    int defaultColumn = 14;
    CPPToken defaultToken = new CPPToken(defaultName, defaultLine, defaultColumn);
    CPPToken defaultTokenWithLex = new CPPToken(defaultName, defaultLexValue, defaultLine, defaultColumn);
    String defaultToString = "Token type: "+ defaultName + " Lexeme: " + blankLexeme.toString() + " Line: " + defaultLine + " Column: " + defaultColumn + "\n";
    String defaultToStringWithLex = "Token type: "+ defaultName + " Lexeme: " + defaultLex.toString() + " Line: " + defaultLine + " Column: " + defaultColumn + "\n";

    /**
    * @throws java.lang.Exception
    */
    @BeforeEach
    public void setUp() throws Exception {
    }


    
    @Test
    public void testConstructorNoLexParameter() {
        CPPToken token = new CPPToken(defaultName, defaultLine, defaultColumn);
        assertThat(token.getName(), equalTo(defaultName));
        assertThat(token.getLexeme(), equalTo(blankLexeme));
        assertThat(token.getLine(), equalTo(defaultLine));
        assertThat(token.getColumn(), equalTo(defaultColumn));
        assertThat(token.toString(), equalTo(defaultToString));
        assertThat(token.getTokenKind(), equalTo(defaultName.ordinal()));
        assertEquals(token, defaultToken);
    }

    @Test
    public void testConstructorWithLexParameter() {
        CPPToken token = new CPPToken(defaultName, defaultLexValue, defaultLine, defaultColumn);
        assertThat(token.getName(), equalTo(defaultName));
        assertThat(token.getLexeme(), equalTo(defaultLex));
        assertThat(token.getLine(), equalTo(defaultLine));
        assertThat(token.getColumn(), equalTo(defaultColumn));
        assertThat(token.toString(), equalTo(defaultToStringWithLex));
        assertThat(token.getTokenKind(), equalTo(defaultName.ordinal()));
        assertEquals(token, defaultTokenWithLex);
    }

    @Test
    public void testSetName() {
        CPPToken token = new CPPToken(defaultName, defaultLexValue, defaultLine, defaultColumn);
        TokenKinds kind = TokenKinds.INT;
        token.setName(kind);
        assertThat(token.getName(), equalTo(kind));
        assertThat(token.getLexeme(), equalTo(defaultLex));
        assertThat(token.getLine(), equalTo(defaultLine));
        assertThat(token.getColumn(), equalTo(defaultColumn));
        assertThat(token.toString(), equalTo("Token type: "+ kind + " Lexeme: " + defaultLex.toString() + " Line: " + defaultLine + " Column: " + defaultColumn + "\n"));
        assertThat(token.getTokenKind(), equalTo(kind.ordinal()));
        assertNotEquals(token, defaultTokenWithLex);
    }

    @Test
    public void testSetLexeme() {
       CPPToken token = new CPPToken(defaultName, defaultLexValue, defaultLine, defaultColumn);
        Lexeme lex = new Lexeme("friday");
        token.setLexeme(lex);
        assertThat(token.getName(), equalTo(defaultName));
        assertThat(token.getLexeme(), equalTo(lex));
        assertThat(token.getLine(), equalTo(defaultLine));
        assertThat(token.getColumn(), equalTo(defaultColumn));
        assertThat(token.toString(), equalTo("Token type: "+ defaultName + " Lexeme: " + lex.toString() + " Line: " + defaultLine + " Column: " + defaultColumn + "\n"));
        assertThat(token.getTokenKind(), equalTo(defaultName.ordinal()));
        assertNotEquals(token, defaultTokenWithLex);
    }

    @Test
    public void testSetLine() {
       CPPToken token = new CPPToken(defaultName, defaultLexValue, defaultLine, defaultColumn);
        int line = 2;
        token.setLine(line);
        assertThat(token.getName(), equalTo(defaultName));
        assertThat(token.getLexeme(), equalTo(defaultLex));
        assertThat(token.getLine(), equalTo(line));
        assertThat(token.getColumn(), equalTo(defaultColumn));
        assertThat(token.toString(), equalTo("Token type: "+ defaultName + " Lexeme: " + defaultLex.toString() + " Line: " + line + " Column: " + defaultColumn + "\n"));
        assertThat(token.getTokenKind(), equalTo(defaultName.ordinal()));
        assertEquals(token, defaultTokenWithLex);
    }

    @Test
    public void testSetColumn() {
        CPPToken token = new CPPToken(defaultName, defaultLexValue, defaultLine, defaultColumn);
        int col = 2;
        token.setColumn(col);
        assertThat(token.getName(), equalTo(defaultName));
        assertThat(token.getLexeme(), equalTo(defaultLex));
        assertThat(token.getLine(), equalTo(defaultLine));
        assertThat(token.getColumn(), equalTo(col));
        assertThat(token.toString(), equalTo("Token type: "+ defaultName + " Lexeme: " + defaultLex.toString() + " Line: " + defaultLine + " Column: " + col + "\n"));
        assertThat(token.getTokenKind(), equalTo(defaultName.ordinal()));
        assertEquals(token, defaultTokenWithLex);
    }
    
    @Test
    public void testIsParameterizable() {
    	CPPToken token = new CPPToken(defaultName, defaultLexValue, defaultLine, defaultColumn);
    	token.setName(TokenKinds.INTEGER_LITERAL);
    	assertTrue(token.isParameterizable());
    	
    	CPPToken token2 = new CPPToken(defaultName, defaultLexValue, defaultLine, defaultColumn);
    	token2.setName(TokenKinds.AND);
    	assertTrue(!token2.isParameterizable());
    }
}
