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
    ArrayList<Integer> defaultParameterOrder;
    LinkedHashMap<Lexeme, Integer> defaultLexemeMap;
    ArrayList<CPPToken> defaultTokens;

    /**
    * @throws java.lang.Exception
    */
    @BeforeEach
    public void setUp() throws Exception {
    	
    }


    public void testFindLexemeMappings() {
        
    }
}
