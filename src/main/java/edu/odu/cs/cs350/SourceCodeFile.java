package edu.odu.cs.cs350;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.annotation.processing.Filer;

public class SourceCodeFile implements Iterable<Token> {
    private File path;
    private int numTokens;
    private ArrayList<Token> tokens;
    
    public SourceCodeFile() {
        path = new File("");
        numTokens = 0;
        tokens = new ArrayList<Token>();
    }

    /**
     * 
     * @param filePath
     */
    public SourceCodeFile(String filePath) {
        path = new File(filePath);
        tokens = new ArrayList<Token>();
        try{
            FileReader reader = new FileReader(path);
            tokenize(reader);
        } catch (FileNotFoundException e) {
            System.out.println("File not found at " + path.getAbsolutePath());
        }
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
    
    public void tokenize(final FileReader input) {
        Scanner scanner = new Scanner(input);
        try {
            Token token = scanner.yylex();
            while(token != null && token.getName() != TokenKinds.EOF) {
                tokens.add(token);
                numTokens ++;
                token = scanner.yylex();
            }

        } catch (IOException e) {
           
        }

    } 
    @Override
    public final Iterator<Token> iterator() {
        return tokens.iterator();
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
