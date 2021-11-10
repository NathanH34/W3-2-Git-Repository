package edu.odu.cs.cs350;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.List;


import edu.odu.cs.cs350.sharedphrases.*;

public class SourceCodeFileCollection implements Iterable<SourceCodeFile> {
    private ArrayList<SourceCodeFile> files;
    private SharedPhrases phrases;


    public SourceCodeFileCollection() {
        files = new ArrayList<SourceCodeFile>();
        phrases = new SharedPhrases();
    }

    /**
     * Add new SourceCodeFile to list, and add its tokens as a sentence to the SharedPhrases member
     * @param newFile SourceCodeFile 
     */
    public void add(SourceCodeFile newFile){
        files.add(newFile);
        order();
        try {
            String encodedTokens = TokenEncoding.encode(newFile.getTokens());
            phrases.addSentence(encodedTokens, newFile.getPath());
        } catch (IllegalTokenKindException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Order the source code files in the collection
     * @post the files are in ascending alphabetical order
     */
    public void order(){
        Collections.sort(files);
    }

    /**
     * TODO: actually find refactorings, returns list of empty refactorings at the moment
     * TODO: add support for parameters for nSuggestions, and maxSubstitutions properties to modify what the returned list contains
     */
    public List<Refactoring> findRefactorings( int minLength ) {
        ArrayList<Refactoring> refactoringList = new ArrayList<Refactoring>();
        ArrayList<String> phraseList = getPhrasesOverLength(minLength);
        for(String phrase: phraseList){
            Refactoring r = new Refactoring();
            refactoringList.add(r);
        }
        return refactoringList;
    }

    private ArrayList<String> getPhrasesOverLength(int minLength) {
        ArrayList<String> phraseList = new ArrayList<String>();
        for(CharSequence phrase: phrases.allPhrases()) {
            int phraseLength = phrase.length();
            if(phraseLength > minLength){
                phraseList.add(phrase.toString());
            }
        }

        return phraseList;
    }

    /**
     * Helper function
     * @param sourcePaths
     * @return A sublist of files whose paths match the paths in sourcePaths
     */
    private List<SourceCodeFile> getFilesFromPaths (Set<String> sourcePaths) {
        ArrayList<SourceCodeFile> sourceFiles = new ArrayList<SourceCodeFile>();
        for(String source: sourcePaths) {
            for(SourceCodeFile file: files) {
                if(source.equals(file.getPath())) {
                    sourceFiles.add(file);
                }
            }
        }

        return sourceFiles;
    }

    @Override
    public final Iterator<SourceCodeFile> iterator() {
        return files.iterator();
    }

    /**
     * @return string with newline separators for each file path
     */
    @Override
    public final String toString() {
        StringBuilder fileCollection = new StringBuilder();
        for (SourceCodeFile f: files) {
            fileCollection.append(f.toString());
        }
        return fileCollection.toString();
    }
}
