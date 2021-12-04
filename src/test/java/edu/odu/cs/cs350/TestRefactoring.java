package edu.odu.cs.cs350;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;


public class TestRefactoring {
    
    Refactoring defaultRef = new Refactoring();
    SourceCodeFile srcFile1 = new SourceCodeFile("src/test/data/TestFile1.cpp");
    SourceCodeFile srcFile2 = new SourceCodeFile("src/test/data/TestFile2.cpp");
    SourceCodeFile srcFile3 = new SourceCodeFile("src/test/data/TestFile3.cpp");
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
        Refactoring r1 = new Refactoring();
        Refactoring r2 = new Refactoring();

        /// Ensure the parameters are correct
        assertThat(r1.getSequenceLength(), is(0));
        assertThat(r2.getSequenceLength(), is(0));
        
        /// Correct the tests to pass the correct parameters for the generateTokenSequence
        r1.setSequenceLength(20);
        r1.addSource(srcFile1, locs);

        r2.setSequenceLength(20);
        r2.addSource(srcFile2, locs);

        // Set up the method call to be able to compare
        // r1.generateTokenSequence(0);
        // assertThat(r1.generateTokenSequence(1), is(r2));

        // Setup a TokenSequence to compare is Equal
        // Setup a TokenSequence to compare that it is false / different
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
        assertEquals(TS1.getParameterOrder(), TS2.getParameterOrder());

        intAL1.set(0, 5);
        assertNotEquals(TS1.getParameterOrder(), TS2.getParameterOrder());
    }
}
