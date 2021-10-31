package edu.odu.cs.cs350;
import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class TestDupDetector {

    String defaultFile = "src/test/resources/TestFile1.cpp";
    File defaultFilePath = new File(defaultFile);

    /**
    * @throws java.lang.Exception
    */
    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void testSearchFiles() {
        
    }

}