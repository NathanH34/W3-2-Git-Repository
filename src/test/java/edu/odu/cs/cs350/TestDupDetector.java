package edu.odu.cs.cs350;
import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;


public class TestDupDetector {

    String defaultFile = "src/test/data/";
    String propertiesFile = "src/test/data/properties.ini";
    File defaultFilePath = new File(defaultFile);
    DupDetector blankDupDetector = new DupDetector();
    ArrayList<String> validExtensions = new ArrayList<String>();

    /**
    * @throws java.lang.Exception
    */
    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void testSearchFiles() {
        
    }

    @Test
    public void testLoadPropertiesFile() {
        DupDetector dupDetector1 = new DupDetector();
        assertThat(dupDetector1, equalTo(blankDupDetector));
        Properties properties1 = dupDetector1.loadPropertiesFile(propertiesFile);
        assertThat(properties1, is(notNullValue()));
        String wrongPropertiesPath = "src/test/resources/properties.ini";
        Properties properties2 = dupDetector1.loadPropertiesFile(wrongPropertiesPath);
        assertThat(properties2, is(nullValue())); //Make sure that properties2 would actually be null in the test
        Properties properties3 = dupDetector1.loadPropertiesFile(defaultFile);
        assertThat(properties3, is(nullValue())); //Make sure that properties3 would actually be null in the test
    }

    @Test
    public void testExtractCppExtensions() {
        DupDetector dupDetector = new DupDetector();
        assertThat(dupDetector, equalTo(blankDupDetector));
        Properties properties = dupDetector.loadPropertiesFile(propertiesFile);
        validExtensions.add("cpp");
        validExtensions.add("h");
        
    }

}
