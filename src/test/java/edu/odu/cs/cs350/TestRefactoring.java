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
        assertEquals(1, r1.compareTo(r2));

        r1.setOpportunityValue(0);
        r2.setOpportunityValue(0);
        assertEquals(0, r1.compareTo(r2));
        
        r1.setOpportunityValue(0);
        r2.setOpportunityValue(10);
        assertEquals(-1, r1.compareTo(r2));
    }
    
    @Test
    public bool 
}
