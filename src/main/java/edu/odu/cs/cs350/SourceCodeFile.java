package edu.odu.cs.cs350;
import java.io.File;

public class SourceCodeFile {
    private File path;
    private int numTokens;
    private Token[] tokens;
    
    public SourceCodeFile() {
        path = new File("");
        numTokens = 0;
    }

    /**
     * 
     * @param filePath
     */
    public SourceCodeFile(String filePath) {
        path = new File(filePath);
        numTokens = 0;
    }

    public String getPath() {
        return path.getAbsolutePath();
    }

    public int getNumTokens() {
        return numTokens;
    }
    
    @Override
    public final String toString() {
        return (path.getAbsolutePath() + "   Tokens:" + numTokens + "\n");
    }

}
