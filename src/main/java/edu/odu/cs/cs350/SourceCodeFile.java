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
    
    /**
     * Default constructor. 
     * path is set to empty string, must be set before calling tokenize()
     */
    public SourceCodeFile() {
        path = new File("");
        numTokens = 0;
        tokens = new ArrayList<Token>();
    }

    /**
     * Make a SourceCodeFile from a String of a file path. 
     * Initializes path with filePath and tokens as an empty list, then calls tokenize() 
     * @param filePath String representation of file to be opened and tokenized
     */
    public SourceCodeFile(String filePath) {
        path = new File(filePath);
        tokens = new ArrayList<Token>();
        tokenize();
    }
    /**
     * Mutator for path variable. 
     * DOES NOT MODIFY tokens! Call tokenize() to update numTokens/tokens List
     * 
     * @param filePath String containing the target file path
     * 
     */
    public void setPath(String filePath) {
        path = new File(filePath);
    }

    /**
     * path getter
     * @return the absolute path of the File, as a String
     */
    public String getPath() {
        return path.getAbsolutePath();
    }

    /**
     * numTokens getter
     * @return the number of tokens in the 
     */
    public int getNumTokens() {
        return numTokens;
    }
    
    /**
     * Use the current value of path member to read the file and populate tokens and numTokens.
     * Will overwrite any previous values of both tokens and numTokens
     * @pre (path.isFile()) && path is a c++ source code file 
     * @post tokens contains all tokens in path file as lexically analyzed by Scanner. 
     * @post numTokens = tokens.size()
     */
    public void tokenize() {
        try {
            FileReader input = new FileReader(path);
            Scanner scanner = new Scanner(input);
            tokens.clear();
            numTokens = 0;
            Token token = scanner.yylex();
            while(token != null && token.getName() != TokenKinds.EOF) {
                tokens.add(token);
                numTokens ++;
                token = scanner.yylex();
            }

        } catch (Exception e) {
           System.out.print(e.toString());
        }

    } 

    /**
     * Iterable implementation
     */
    @Override
    public final Iterator<Token> iterator() {
        return tokens.iterator();
    }

    /**
     * Returns a string describing the absolute path of the file, and the number of tokens in it
     */
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
