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
    String defaultFilePath = "src/test/data/TestFile1.cpp";
    int sequenceLength = 8;
    int sequenceStartLocation = 0;
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
        assertThat(tokenSequence1, equalTo(blankTokenSequence));
    }

    @Test
    public void testConstructorWithParam() {
        SourceCodeFile sourceCodeFile1 = new SourceCodeFile(defaultFilePath);
        ArrayList<CPPToken> subSetTokenSequence = 
            sourceCodeFile1.makeTokenSequence(sequenceLength, sequenceStartLocation);
        
        TokenSequence tokenSequence1 = new TokenSequence(subSetTokenSequence, sourceCodeFile1, sequenceStartLocation);
        assertThat(tokenSequence1, not(equalTo(blankTokenSequence)));
        assertThat(tokenSequence1.getStartingLocation(), equalTo(sequenceStartLocation));
        assertThat(tokenSequence1.getSourceCode(), equalTo(sourceCodeFile1));
        assertThat(tokenSequence1.getParameterOrder(), not(equalTo(blankParameterOrder)));
        //lexemeMap and tokens check
    }
}
