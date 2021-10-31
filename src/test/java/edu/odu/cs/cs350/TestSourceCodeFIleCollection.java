package edu.odu.cs.cs350;
import java.io.File;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class TestSourceCodeFIleCollection {
    
    SourceCodeFileCollection blank = new SourceCodeFileCollection();
    SourceCodeFile srcFile1 = new SourceCodeFile("src/test/resources/TestFile1.cpp");
    SourceCodeFile srcFile2 = new SourceCodeFile("src/test/resources/TestFile2.cpp");
    SourceCodeFile srcFile3 = new SourceCodeFile("src/test/resources/TestFile3.cpp");

    
    /**
    * @throws java.lang.Exception
    */
    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void testDefaultConstructor() {
        SourceCodeFileCollection col1 = new SourceCodeFileCollection();
        assertThat(col1, equalTo(blank));
        assertThat(col1.toString(), is(emptyString()));
        Iterator<SourceCodeFile> it = col1.iterator();
        assertFalse(it.hasNext());
    }

    @Test
    public void testAdd() {
        SourceCodeFileCollection col1 = new SourceCodeFileCollection();
        assertThat(col1, equalTo(blank));
        assertThat(col1.toString(), is(emptyString()));
        col1.add(srcFile1);
        col1.add(srcFile2);
        Iterator<SourceCodeFile> it = col1.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), equalTo(srcFile1));
        assertThat(it.next(), equalTo(srcFile2));
        assertThat(col1.toString(), not(emptyString()));
        assertThat(col1, not(equalTo(blank)));
    }

    @Test
    public void testOrder() {
        SourceCodeFileCollection col1 = new SourceCodeFileCollection();
        assertThat(col1, equalTo(blank));
        assertThat(col1.toString(), is(emptyString()));
        col1.add(srcFile2);
        col1.add(srcFile1);
        Iterator<SourceCodeFile> it = col1.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), equalTo(srcFile1));
        assertThat(it.next(), equalTo(srcFile2));
        assertThat(col1.toString(), not(emptyString()));
        assertThat(col1, not(equalTo(blank)));
    }

}
