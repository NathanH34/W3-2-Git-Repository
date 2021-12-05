package edu.odu.cs.cs350;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class TestTokenSequence {
	TokenSequence blankTokenSequence = new TokenSequence();
    String defaultFilePath1 = "src/test/data/TestFile1.cpp";
    String defaultFilePath2 = "src/test/data/TestFile2.cpp";
    String defaultFilePath3 = "src/test/data/TestFile3.cpp";
    String defaultFilePath4 = "src/test/data/TestFile4.cpp";
    int sequenceLength = 8;
    int sequenceStartLocation = 0;
    LinkedHashMap<Lexeme, Integer> blankLexMap;
    ArrayList<Integer> blankParameterOrder;

    /**
    * @throws java.lang.Exception
    */
    @BeforeEach
    public void setUp() throws Exception {
    	
    }

    @Test
    public void testConstructor() {
        TokenSequence tokenSequence1 = new TokenSequence();
        assertEquals(tokenSequence1.getLexemeMap(), blankTokenSequence.getLexemeMap());
        assertEquals(tokenSequence1.getParameterOrder(), blankTokenSequence.getParameterOrder());
        assertEquals(tokenSequence1.getTokens(), blankTokenSequence.getTokens());
        
        ArrayList<CPPToken> cppTokens = new ArrayList<CPPToken>();
        CPPToken cppToken = new CPPToken(TokenKinds.INTEGER_LITERAL, 1, 1);
        cppTokens.add(cppToken);
        tokenSequence1.setTokens(cppTokens);
        assertNotEquals(tokenSequence1.getTokens(), blankTokenSequence.getTokens());
    }

    @Test
    public void testConstructorWithParam() {
        SourceCodeFile sourceCodeFile1 = new SourceCodeFile(defaultFilePath1);
        ArrayList<CPPToken> subSetTokenSequence = 
            sourceCodeFile1.makeTokenSequence(sequenceLength, sequenceStartLocation);
        
        TokenSequence tokenSequence1 = new TokenSequence(subSetTokenSequence, sourceCodeFile1, sequenceStartLocation);
        assertThat(tokenSequence1, not(equalTo(blankTokenSequence)));
        assertThat(tokenSequence1.getStartingLocation(), equalTo(sequenceStartLocation));
        assertThat(tokenSequence1.getSourceCode(), equalTo(sourceCodeFile1));
        assertThat(tokenSequence1.getParameterOrder(), not(equalTo(blankParameterOrder)));
        //lexemeMap and tokens check
    }
       
    @Test
    public void testToString() {
    	SourceCodeFile testFile1 = new SourceCodeFile(defaultFilePath3);
    	TokenSequence tokenSequence1 = new TokenSequence(testFile1.getTokens(), testFile1, sequenceStartLocation);
    	String expected1 = "x y 1 cout \n";
    	assertEquals(tokenSequence1.toString(), expected1);
    	
    	SourceCodeFile testFile2 = new SourceCodeFile(defaultFilePath2);
    	TokenSequence tokenSequence2 = new TokenSequence(testFile2.getTokens(), testFile2, sequenceStartLocation);
    	String expected2 = "iostream \n";
    	assertNotEquals(tokenSequence2.toString(), expected1);
    	assertEquals(tokenSequence2.toString(), expected2);
    }
}
