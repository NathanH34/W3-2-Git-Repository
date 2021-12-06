package edu.odu.cs.cs350;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.xml.namespace.QName;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class TestRefactoring {
    
    Refactoring defaultRef = new Refactoring();
    SourceCodeFile srcFile1 = new SourceCodeFile("src/test/data/TestFile1.cpp");
    SourceCodeFile srcFile2 = new SourceCodeFile("src/test/data/TestFile2.cpp");
    SourceCodeFile srcFile3 = new SourceCodeFile("src/test/data/TestFile3.cpp");
    SourceCodeFile srcFile4 = new SourceCodeFile("src/test/data/TestFile4.cpp");
    ArrayList<Integer> locs = new ArrayList<Integer>();

    @Test
    public void testConstructor() {
        Refactoring ref = new Refactoring();
        assertThat(ref.getOpportunityValue(), is(0));
        assertThat(ref.getSequenceLength(), is(0));

    }

    @Test
    public void testSetSequenceLength() {
        Refactoring ref = new Refactoring();
        ref.setSequenceLength(20);
        assertThat(ref.getOpportunityValue(), is(0));
        assertThat(ref.getSequenceLength(), is(20));
    }

    @Test
    public void testAddSource() {
        Refactoring ref = new Refactoring();
        locs.add(Integer.valueOf(0));
        ref.setSequenceLength(20);
        ref.addSource(srcFile1, locs);
        assertThat(ref.getOpportunityValue(), is(0));
        ref.addSource(srcFile2, locs);
        assertThat(ref.getOpportunityValue(), is(20));

    }

    @Test
    public void testCompareTo(){
        Refactoring r1 = new Refactoring();
        Refactoring r2 = new Refactoring();
        
        r1.setOpportunityValue(10);
        r2.setOpportunityValue(0);
        assert(r1.compareTo(r2) > 0);

        r1.setOpportunityValue(0);
        r2.setOpportunityValue(0);
        assert(r1.compareTo(r2) == 0);
        
        r1.setOpportunityValue(0);
        r2.setOpportunityValue(10);
        assert(r1.compareTo(r2) < 0);
    }

    @Test
    public void testGenerateTokenSequence() {
        Refactoring refactoring1 = new Refactoring();
        Refactoring refactoring2 = new Refactoring();
        Refactoring refactoring3 = new Refactoring();

        // Set the parameters to generateTokenSequences
        refactoring1.addSource(srcFile1, locs);
        refactoring1.addSource(srcFile2, locs);
        locs.addAll(refactoring1.getStartingLocation(srcFile1));
    	locs.addAll(refactoring1.getStartingLocation(srcFile2));

        for(SourceCodeFile file : refactoring1.getSourceCodeFiles()) {
	        TokenSequence ts1 = refactoring1.generateTokenSequence(file.getTokens(), file, 0);
	        TokenSequence ts2 = refactoring1.generateTokenSequence(file.getTokens(), file, 1);
            assertThat(ts1.getStartingLocation(), is(0));
            assertThat(ts2.getStartingLocation(), is(1));
            assertFalse(ts1.equals(ts2));
        }

        refactoring2.addSource(srcFile3, locs);
        refactoring2.addSource(srcFile4, locs);
    	locs.addAll(refactoring2.getStartingLocation(srcFile3));
    	locs.addAll(refactoring2.getStartingLocation(srcFile4));

        for(SourceCodeFile file : refactoring2.getSourceCodeFiles()) {
	        TokenSequence ts1 = refactoring2.generateTokenSequence(file.getTokens(), file, 0);
	        TokenSequence ts2 = refactoring2.generateTokenSequence(file.getTokens(), file, 1);
            assertThat(ts1.getStartingLocation(), is(0));
            assertThat(ts2.getStartingLocation(), is(1));
            assertFalse(ts1.equals(ts2));
        }

        refactoring3.addSource(srcFile4, locs);
        locs.addAll(refactoring3.getStartingLocation(srcFile4));

        for(SourceCodeFile file : refactoring3.getSourceCodeFiles()) {
            TokenSequence ts1 = refactoring3.generateTokenSequence(file.getTokens(), file, 0);
            
            assert(ts1.getParameterOrder().isEmpty());
            assert(ts1.getLexemeMap().isEmpty());
            assert(ts1.getTokens().isEmpty());
            assert(ts1.getSourceCode() == file);
            assertThat(ts1.getStartingLocation(), is(0));
        }
    }

    @Test
    public void testCompareParameterOrder() {
        TokenSequence TS1 = new TokenSequence();
        TokenSequence TS2 = new TokenSequence();

        ArrayList<Integer> intAL1 = new ArrayList<Integer>();
        assert(intAL1.isEmpty()); // new, should be empty
        ArrayList<Integer> intAL2 = new ArrayList<Integer>();
        assert(intAL2.isEmpty()); // new, should be empty
        for (int i = 0; i < 4; i++){
            intAL1.add(i);
            intAL2.add(i);
        }
        TS1.setParameterOrder(intAL1);
        TS2.setParameterOrder(intAL2);
        ///assertEquals(TS1.getParameterOrder(), TS2.getParameterOrder());
        assert(TS1.getParameterOrder().equals(TS2.getParameterOrder()));

        intAL1.set(0, 5);
        TS1.setParameterOrder(intAL1);
        ///assertNotEquals(TS1.getParameterOrder(), TS2.getParameterOrder());
        assert(!TS1.getParameterOrder().equals(TS2.getParameterOrder()));

        ArrayList<CPPToken> tokenAL = new ArrayList<CPPToken>();
        Refactoring refact = new Refactoring();
        tokenAL = refact.compareParameterOrder(TS1, TS2); // should return empty array list
        assert(tokenAL.isEmpty());
    }
    
    @Test
    public void testToString() {
    	Refactoring refactoring = new Refactoring();
    	String expected = "Opportunity " + refactoring.getOpportunityValue() + ", " + refactoring.getSequenceLength() + " tokens\n";
    	assertEquals(refactoring.toString(), expected);
    	
    	Refactoring refactoring2 = new Refactoring(10);
    	String expected2 = "Opportunity " + refactoring.getOpportunityValue() + ", 10 tokens\n";
    	assertEquals(refactoring2.toString(), expected2);
    	
    	Refactoring refactoring3 = new Refactoring(15);
    	refactoring3.setOpportunityValue(20);
    	refactoring3.addSource(srcFile3, locs);
    	refactoring3.addSource(srcFile4, locs);
    	locs.addAll(refactoring3.getStartingLocation(srcFile3));
    	locs.addAll(refactoring3.getStartingLocation(srcFile4));
    	String expected3 = "Opportunity " + refactoring3.getOpportunityValue() + ", 15 tokens\n";
    	for(SourceCodeFile file : refactoring3.getSourceCodeFiles()) {
	    	for(int i=0; i<locs.size()-1; i++) {
	            TokenSequence ts1 = refactoring3.generateTokenSequence(file.getTokens(), file, i);
	            TokenSequence ts2 = refactoring3.generateTokenSequence(file.getTokens(), file, i+1);
	            ArrayList<CPPToken> testRefactorings = refactoring3.compareParameterOrder(ts1, ts2);
	            if(!testRefactorings.isEmpty()) {
	                ArrayList<Lexeme> testParams = new ArrayList<Lexeme>();
	                for(CPPToken token : testRefactorings) {
	                    if(!testParams.contains(token.getLexeme())) {
	                        testParams.add(token.getLexeme());
	                    }
	                }
	                file.setParameterizables(testParams);
	            }
	            expected3 += file.getPath() + ":" + file.getTokenAt(i) + ":" + file.getTokenAt(i).getColumn() + "\n";
	    	    assertEquals(refactoring3.toString(), expected3); 
	    	}
    	}
    }
}
