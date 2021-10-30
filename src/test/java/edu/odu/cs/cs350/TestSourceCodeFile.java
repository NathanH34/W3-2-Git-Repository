package edu.odu.cs.cs350;
import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class TestSourceCodeFile {
    
   SourceCodeFile blankCodeFile = new SourceCodeFile();
   int noNumTokens = 0;
   String defaultFilePath = "/home/user/cs350";

   /**
   * @throws java.lang.Exception
   */
   @BeforeEach
   public void setUp() throws Exception {
   }
   
   @Test
    public void testDefaultConstructor() {
      SourceCodeFile src = new SourceCodeFile();
      assertThat(src.getPath(), is(""));
      assertThat(src.getNumTokens(), is(0));
      assertThat(src.toString(), is("   Tokens:0\n"));
      assertThat(src, equalTo(blankCodeFile));
    }
    
    @Test
    public void testConstructorWithFilepath() {
      SourceCodeFile src = new SourceCodeFile(defaultFilePath);
      assertThat(src.getPath(), is(defaultFilePath));
      assertThat(src.getNumTokens(), is(0));
      assertThat(src.toString(), is(defaultFilePath + "   Tokens:0\n"));
      assertThat(src, not(equalTo(blankCodeFile)));
      // Check Relative path?
    }
}
