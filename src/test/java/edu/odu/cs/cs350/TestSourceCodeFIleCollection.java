package edu.odu.cs.cs350;
import java.io.File;
import java.sql.Ref;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.odu.cs.cs350.sharedphrases.SharedPhrases;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class TestSourceCodeFIleCollection {
    
    SourceCodeFileCollection blank = new SourceCodeFileCollection();
    SourceCodeFile srcFile1 = new SourceCodeFile("src/test/data/TestFile1.cpp");
    SourceCodeFile srcFile2 = new SourceCodeFile("src/test/data/TestFile2.cpp");
    SourceCodeFile srcFile3 = new SourceCodeFile("src/test/data/TestFile3.cpp");
    SourceCodeFile srcFile4 = new SourceCodeFile("src/test/data/TestFile4.cpp");
    int defaultPhraseLength = 10;
    
    /**
    * @throws java.lang.Exception
    */
    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void testDefaultConstructor() {
        SourceCodeFileCollection col1 = new SourceCodeFileCollection();
        assertThat(col1.toString(), is(emptyString()));
        assertTrue(col1.findRefactorings(defaultPhraseLength).isEmpty());
    }

    @Test
    public void testAdd() {
        SourceCodeFileCollection col1 = new SourceCodeFileCollection();
        assertThat(col1.toString(), is(emptyString()));
        col1.add(srcFile1);
        ArrayList<Refactoring> rList = col1.findRefactorings(10);
        assertThat(rList.size(), is(0));
        col1.add(srcFile2);
        rList = col1.findRefactorings(4);
        assertTrue(rList.size() > 0);
        assertThat(col1.toString(), not(emptyString()));
        assertTrue(col1.toString().contains(srcFile1.getPath()));
        assertTrue(col1.toString().contains(srcFile2.getPath()));
        assertTrue(col1.toString().contains(String.valueOf(srcFile1.getNumTokens())));
        assertTrue(col1.toString().contains(String.valueOf(srcFile2.getNumTokens())));
        assertThat(col1, not(equalTo(blank)));
    }


    @Test
    public void testFindRefactorings() {
        SourceCodeFileCollection col1 = new SourceCodeFileCollection();
        col1.add(srcFile3);
        col1.add(srcFile4);
        ArrayList<Refactoring> rList = col1.findRefactorings(defaultPhraseLength);
        assertThat(rList.size(), not(equalTo(0)));
        Collections.sort(rList);
        Collections.reverse(rList);
        for(Refactoring r: rList){
            assertTrue(r.getSequenceLength() >= defaultPhraseLength);
            assertTrue(r.getOpportunityValue() > 0);
        }

        //check largest refactoring
        Refactoring ref = rList.get(0);
        assertThat(ref.getSequenceLength(), equalTo(srcFile4.getNumTokens()));
        assertThat(ref.getOpportunityValue(), equalTo(srcFile4.getNumTokens()));
        assertTrue(ref.toString().contains(srcFile4.getPath() + ":" + 1 + ":" + 1));
        assertTrue(ref.toString().contains(srcFile3.getPath() + ":" + 5 + ":" + 1));



        assertThat(col1.toString(), not(emptyString()));
        assertThat(col1, not(equalTo(blank)));
    }
}