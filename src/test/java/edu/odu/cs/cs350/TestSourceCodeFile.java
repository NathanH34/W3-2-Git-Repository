package edu.odu.cs.cs350;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class TestSourceCodeFile {
    @Test
    public void testDefaultConstructor() {
       // SourceCodeFile src = new SourceCodeFile();
       // assertThat(src.getPath(), is(""));
       // assertThat(src.getNumTokens(), is(0));
       // assertThat(src.toString(), is("   Tokens:0\n"));
    }

    @Test
    public void testConstructorWithFilepath() {
       // SourceCodeFile src = new SourceCodeFile("");
      //  assertThat(src.getPath(), is(""));
       // assertThat(src.getNumTokens(), is(0));
       // assertThat(src.toString(), is());
    }
}
