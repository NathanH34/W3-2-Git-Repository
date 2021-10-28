package edu.odu.cs.cs350;

public class SourceCodeFile {
    private String path;
    private int numTokens;
    private Token[] tokens;
    
    public SourceCodeFile() {
        path = "~/foo.cpp";
        numTokens = 5;
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
