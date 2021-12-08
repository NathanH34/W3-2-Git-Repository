package edu.odu.cs.cs350;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
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
    String testOutputPath = "src/test/data/";
    File testOutput = new File("src/test/data");

    int validMinSequenceLength;
    int validMaxSubstitutions;

    /**
    * @throws java.lang.Exception
    */
    @BeforeEach
    public void setUp() throws Exception {
    	
    }

    @Test
    public void testSearchFiles() throws IOException {
        SourceCodeFileCollection testFileCollection = new SourceCodeFileCollection();
        ArrayList<String> testExtensions = new ArrayList<String>();
    	Properties testProperties = new Properties();
    	FileInputStream propFileStream = new FileInputStream(propertiesFilePath);
    	testProperties.load(propFileStream);
    	testExtensions = DupDetector.extractCppExtensions(testProperties, testExtensions);
    	
        DupDetector.searchFiles(defaultFile, testFileCollection, testExtensions);
        assertTrue(testFileCollection.toString().contains(defaultFile.getAbsolutePath()));
    }

    @Test
    public void testGetFileExtension() {
    	String testExtension = DupDetector.getFileExtension(defaultFile);
    	assertThat(testExtension, is("cpp"));
    }
    
    @Test
    public void testLoadPropertiesFile() {
        Properties properties1 = DupDetector.loadPropertiesFile(propertiesFilePath);
        assertThat(properties1, is(notNullValue()));
        Properties properties2 = DupDetector.loadPropertiesFile(wrongPropertiesFilePath);
        assertThat(properties2, is(notNullValue())); //Make sure that properties2 would actually be null in the test       
    }
    
    @Test
    public void testSetMaxSubstitutions() throws IOException {
        Properties testProperties = new Properties();
        
        validMaxSubstitutions = DupDetector.setMaxSubstitutions(testProperties);
    	assertThat(validMaxSubstitutions, is(8));

    	FileInputStream propFileStream = new FileInputStream(propertiesFilePath);
    	testProperties.load(propFileStream);

        validMaxSubstitutions = DupDetector.setMaxSubstitutions(testProperties);
        assertThat(validMaxSubstitutions, is(69));

        FileInputStream wrongPropFileStream = new FileInputStream(wrongPropertiesFilePath);
    	testProperties.load(wrongPropFileStream);

        validMaxSubstitutions = DupDetector.setMaxSubstitutions(testProperties);
        assertThat(validMaxSubstitutions, not('B'));
        assertThat(validMaxSubstitutions, is(8));
    }

    @Test
    public void testSetMinSequenceLength() throws IOException {
        Properties testProperties = new Properties();

        validMinSequenceLength = DupDetector.setMinSequenceLength(testProperties);
    	assertThat(validMinSequenceLength, is(10));

    	FileInputStream propFileStream = new FileInputStream(propertiesFilePath);
    	testProperties.load(propFileStream);

        validMinSequenceLength = DupDetector.setMinSequenceLength(testProperties);
        assertThat(validMinSequenceLength, is(42));

        FileInputStream wrongPropFileStream = new FileInputStream(wrongPropertiesFilePath);
    	testProperties.load(wrongPropFileStream);

        validMinSequenceLength = DupDetector.setMinSequenceLength(testProperties);
        assertThat(validMinSequenceLength, not('A'));
        assertThat(validMinSequenceLength, is(10));
    }

    @Test
    public void testExtractCppExtensions() throws IOException {
    	ArrayList<String> testExtensions = new ArrayList<String>();
    	Properties testProperties = new Properties();
    	FileInputStream propFileStream = new FileInputStream(propertiesFilePath);
    	testProperties.load(propFileStream);
    	
    	testExtensions = DupDetector.extractCppExtensions(testProperties, testExtensions);
    	assertTrue(testExtensions.contains("h"));
    	assertTrue(testExtensions.contains("c"));
    	assertTrue(testExtensions.contains("hpp"));
    	assertTrue(testExtensions.contains("cpp"));
    	assertThat(testExtensions.size(), is(4));
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
    	assertThat(validExtensions.contains("hpp"), is(false));
    	assertThat(validExtensions.contains("c"), is(false));
    	assertThat(validExtensions.size(), is(2));
    }

    @Test
    public void testForOutput() {
        new ArrayList<String>(numOfSuggestions);

    	
    }
}
