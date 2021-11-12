package edu.odu.cs.cs350;
import java.io.File;
import java.sql.Ref;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
        
        col1.add(srcFile2);
        assertThat(col1.toString(), not(emptyString()));
        assertThat(col1, not(equalTo(blank)));
    }


    @Test
    public void testFindRefactorings() {
        SourceCodeFileCollection col1 = new SourceCodeFileCollection();
        col1.add(srcFile3);
        col1.add(srcFile4);
        ArrayList<Refactoring> rList = col1.findRefactorings(10);

        assertThat(col1.toString(), not(emptyString()));
        assertThat(col1, not(equalTo(blank)));
    }
}