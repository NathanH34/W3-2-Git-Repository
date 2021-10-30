package edu.odu.cs.cs350;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.annotation.processing.Filer;

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
        tokenize();
    }

    public void setPath(String filePath) {
        path = new File(filePath);
    }

    public String getPath() {
        return path.getAbsolutePath();
    }

    public void setNumTokens(int numberTokens) {
        numTokens = numberTokens;
    }

    public int getNumTokens() {
        return numTokens;
    }
    
    public void tokenize() {
        FileReader reader;
        try {
            reader = new FileReader(path);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to open sourcefile at " + path.getAbsolutePath() + "Using default");
        }
        Scanner scanner = new Scanner(reader);
        
    }

    @Override
    public final String toString() {
        return (path.getAbsolutePath() + "   Tokens:" + numTokens + "\n");
    }

    /**
     * Returns true if the two sourceCodeFiles have the same absolute path.
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof SourceCodeFile) && (this.getPath().equals(((SourceCodeFile) obj).getPath()));
    }
}
