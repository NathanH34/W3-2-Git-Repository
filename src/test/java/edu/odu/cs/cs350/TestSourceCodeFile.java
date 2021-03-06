package edu.odu.cs.cs350;
import java.io.File;
import java.util.Iterator;
import java.util.ArrayList;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class TestSourceCodeFile {
    
  SourceCodeFile blankCodeFile = new SourceCodeFile();
  int noNumTokens = 0;
  int defaultFileNumTokens = 20;
  int filePathTwoTokens = 4;
  String defaultFilePath = "src/test/data/TestFile1.cpp";
  String filePathTwo = "src/test/data/TestFile2.cpp";
  String filePathFour = "src/test/data/TestFile4.cpp";
  File fileTwo = new File(filePathTwo);
  File defaultFile = new File(defaultFilePath);
  File blankFile = new File("");

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  public void setUp() throws Exception {
  }

  @Test
  public void testDefaultConstructor() {
    SourceCodeFile src = new SourceCodeFile();
    assertThat(src.getPath(), is(blankFile.getAbsolutePath()));
    assertThat(src.getNumTokens(), is(noNumTokens));
    assertTrue(src.toString().contains(blankFile.getAbsolutePath() + "   Tokens:0\n"));
    assertThat(src, equalTo(blankCodeFile));
    Iterator<CPPToken> it = src.iterator();
    assertFalse(it.hasNext());
  }
    
  @Test
  public void testConstructorWithFilepath() {
    SourceCodeFile src = new SourceCodeFile(defaultFilePath);
    assertThat(src.getPath(), is(defaultFile.getAbsolutePath()));
    assertThat(src.getNumTokens(), is(defaultFileNumTokens));
    assertTrue(src.toString().contains(defaultFile.getAbsolutePath() + "   Tokens:"+defaultFileNumTokens + "\n"));
    assertThat(src, not(equalTo(blankCodeFile)));
    Iterator<CPPToken> it = src.iterator();
    assertTrue(it.hasNext());
    int sum = 0;
    while(it.hasNext()) {
      it.next();
      sum++;
    }
    assertEquals(sum, src.getNumTokens());
  }

  @Test
  public void testSetPathAndTokenize() {
    SourceCodeFile src = new SourceCodeFile(defaultFilePath);
    SourceCodeFile src2 = new SourceCodeFile(defaultFilePath);
    SourceCodeFile src3 = new SourceCodeFile(filePathTwo);
    src.setPath(filePathTwo);
    assertThat(src.getPath(), is(fileTwo.getAbsolutePath()));
    //Expect wrong numTokens and tokens list, since path is set but tokenize not called. Maybe should be changed
    assertThat(src.getNumTokens(), is(defaultFileNumTokens));
    assertTrue(src.toString().contains(fileTwo.getAbsolutePath() + "   Tokens:"+defaultFileNumTokens + "\n"));
    assertNotEquals(src, src2);
    Iterator<CPPToken> it = src.iterator();
    assertTrue(it.hasNext());
    int sum = 0;
    while(it.hasNext()) {
      it.next();
      sum++;
    }
    assertThat(sum, is(defaultFileNumTokens));

    //Call tokenize, should update tokens and numTokens to new file
    src.tokenize();
    assertThat(src.getNumTokens(), is(filePathTwoTokens));
    assertTrue(src.toString().contains(fileTwo.getAbsolutePath() + "   Tokens:"+filePathTwoTokens + "\n"));
    assertNotEquals(src, src2);
    assertEquals(src, src3);
    it = src.iterator();
    assertTrue(it.hasNext());
    sum = 0;
    while(it.hasNext()) {
      it.next();
      sum++;
    }
    assertThat(sum, is(filePathTwoTokens));
  }

  @Test
  public void testMakeTokenSequence() {
    SourceCodeFile newSourceCodeFile = new SourceCodeFile(defaultFilePath);
    int sequenceLength = 10;
    int sequenceStartLocation = 1;
    newSourceCodeFile.tokenize();
    ArrayList<CPPToken> testTokenSequence = newSourceCodeFile.makeTokenSequence(sequenceLength, sequenceStartLocation);
    assertFalse(testTokenSequence.isEmpty());
  }

  @Test
  public void testGetSequence() {
    SourceCodeFile src = new SourceCodeFile(filePathFour);
    TokenSequence seq1 = src.getSequence(0, 4);
    ArrayList<CPPToken> tokens = seq1.getTokens();
    assertThat(tokens.size(), is(4));
    assertThat(tokens.get(0).getName(), is(TokenKinds.IF));
    assertThat(tokens.get(3).getName(), is(TokenKinds.GT));
  }
}
