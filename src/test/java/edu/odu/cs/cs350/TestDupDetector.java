package edu.odu.cs.cs350;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;


public class TestDupDetector {


    String defaultFilePath = "src/test/data/TestFile1.cpp"; 
    File defaultFile = new File(defaultFilePath);
    String defaultDirPath = "src/test/data/";
    File defaultDirectory = new File("src/test/data");
    String propertiesFilePath = "src/test/data/properties.ini";
    String wrongPropertiesFilePath = "src/test/data/wrongProperties.ini";
    

    DupDetector blankDupDetector = new DupDetector();
    ArrayList<String> validExtensions = new ArrayList<String>();
    int validMinSequenceLength;
    int validMaxSubstitutions;

    /**
    * @throws java.lang.Exception
    */
    @BeforeEach
    public void setUp() throws Exception {
    	
    }

    @Test
    public void testSearchFiles() {
        SourceCodeFileCollection fileCollection = new SourceCodeFileCollection();
        ArrayList<String> validExtensions = new ArrayList<String>();
        DupDetector.searchFiles(defaultFile, fileCollection, validExtensions);
        assertThat(fileCollection.toString().contains(defaultFilePath), is(true));
        
        DupDetector.searchFiles(defaultDirectory, fileCollection, validExtensions);
        assertThat(fileCollection.toString().contains("src/test/data/TestFile1.cpp"), is(true));
        assertThat(fileCollection.toString().contains("src/test/data/TestFile2.cpp"), is(true));
        assertThat(fileCollection.toString().contains("src/test/data/TestFile3.cpp"), is(true));
        assertThat(fileCollection.toString().contains("src/test/data/TestFile4.cpp"), is(true));
    }

    @Test
    public void testLoadPropertiesFile() {
        DupDetector dupDetector1 = new DupDetector();
        assertThat(dupDetector1, equalTo(blankDupDetector));
        Properties properties1 = dupDetector1.loadPropertiesFile(propertiesFilePath);
        assertThat(properties1, is(notNullValue()));
        String wrongPropertiesPath = "src/test/resources/properties.ini";
        Properties properties2 = dupDetector1.loadPropertiesFile(wrongPropertiesPath);
        assertThat(properties2, is(nullValue())); //Make sure that properties2 would actually be null in the test
        Properties properties3 = dupDetector1.loadPropertiesFile(defaultFilePath);
        assertThat(properties3, is(nullValue())); //Make sure that properties3 would actually be null in the test
    }

    @Test
    public void testAddMaxSubstitutions() throws IOException {
        
    }

    @Test
    public void testAddMinSequenceLength() throws IOException {
        Properties testProperties = new Properties();

        validMinSequenceLength = DupDetector.addMinSequenceLength(testProperties);
    	assertThat(validMinSequenceLength, is(10));

    	FileInputStream propFileStream = new FileInputStream(propertiesFilePath);
    	testProperties.load(propFileStream);

        validMinSequenceLength = DupDetector.addMinSequenceLength(testProperties);
        assertThat(validMinSequenceLength, is(42));

        FileInputStream wrongPropFileStream = new FileInputStream(wrongPropertiesFilePath);
    	testProperties.load(wrongPropFileStream);

        validMinSequenceLength = DupDetector.addMinSequenceLength(testProperties);
        assertThat(validMinSequenceLength, not('A'));
        assertThat(validMinSequenceLength, is(10));
    }
    
    @Test
    public void testExtractCppExtensions() throws IOException {
    	ArrayList<String> validExtensions = new ArrayList<String>();
    	Properties properties = new Properties();
    	FileInputStream propFileStream = new FileInputStream(propertiesFilePath);
    	properties.load(propFileStream);
    	
    	validExtensions = DupDetector.extractCppExtensions(properties, validExtensions);
    	assertThat(validExtensions.contains("h"), is(true));
    	assertThat(validExtensions.contains("c"), is(true));
    	assertThat(validExtensions.contains("hpp"), is(true));
    	assertThat(validExtensions.contains("cpp"), is(true));
    }
    
    @Test
    public void testSearchForDefaults() {
    	SourceCodeFile srcFile = new SourceCodeFile(defaultFilePath);
    	SourceCodeFileCollection fileCollection = new SourceCodeFileCollection();
    	fileCollection.add(srcFile);
        ArrayList<String> validExtensions = new ArrayList<String>();
    	DupDetector.searchForDefaults(defaultFile, fileCollection, validExtensions);
    	assertThat(validExtensions.contains("h"), is(true));
    	assertThat(validExtensions.contains("cpp"), is(true));
    	assertThat(fileCollection.toString().contains(defaultFilePath), is(true));
    	
    }


}
