package edu.odu.cs.cs350;


public class SourceCodeFile {
    private String path;
    private int numTokens;
    private Token[] tokens;
    
    public SourceCodeFile() {
        path = "";
        numTokens = 0;
    }

    public SourceCodeFile(String filePath) {
        path = filePath;
        numTokens = 0;
    }

    public String getPath() {
        return path;
    }

    public int getNumTokens() {
        return numTokens;
    }
    
    public String reportNameAndTokens() {
        return (path + "   Tokens:" + numTokens + "\n");
    }

}
