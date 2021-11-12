package edu.odu.cs.cs350;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Collection;
import java.util.Set;


import edu.odu.cs.cs350.sharedphrases.*;

public class SourceCodeFileCollection {
    private TreeMap<String,SourceCodeFile> files;
    private SharedPhrases phrases;


    public SourceCodeFileCollection() {
        files = new TreeMap<String,SourceCodeFile>();
        phrases = new SharedPhrases();
    }

    /**
     * Add new SourceCodeFile to list, and add its tokens as a sentence to the SharedPhrases member
     * @param newFile SourceCodeFile 
     */
    public void add(SourceCodeFile newFile){
        files.put(newFile.getPath(), newFile);
        try {
            String encodedTokens = TokenEncoding.encode(newFile.getTokens());
            phrases.addSentence(encodedTokens, newFile.getPath());
        } catch (IllegalTokenKindException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * 
     * @param minLength the minimum length of each refactoring in tokens
     * @return all possible refactorings within the collection with Opportunity value over 0
     */
    public ArrayList<Refactoring> findRefactorings( int minLength) {
        ArrayList<Refactoring> refactoringList = new ArrayList<Refactoring>();
        ArrayList<String> phraseList = getPhrasesOverLength(minLength);
        for(String phrase: phraseList) {
            Refactoring r = makeRefactoring(phrase);
            refactoringList.add(r);
        }
        refactoringList.removeIf(refactoring -> refactoring.getOpportunityValue() == 0);
        return refactoringList;
    }

    /**
     * 
     * @param phrase
     * @return a Refactoring for that phrase with the correct sources and starting token
     */
    private Refactoring makeRefactoring(String phrase) {
        Refactoring ref = new Refactoring(phrase.length());
        Iterable<CharSequence> suffixes = phrases.phrasesCompleting(phrase); 
        for(CharSequence suffix: suffixes) {
            Set<String> sources = phrases.sourcesOf(suffix.toString()); //Method returns a set, but each maximal suffix should only have one source
            for(String path: sources){
                ArrayList<Integer> startLocation = new ArrayList<Integer>();
                int index = files.get(path).getNumTokens() - (suffix.length() - 1);
                startLocation.add(index);
                ref.addSource(files.get(path), startLocation);
            }
        }
        return ref;
    }

    /**
     * helper function, gets every shared phrase from sharedPhrases and filters for those above or equal to the given length. 
     * @param minLength
     * @return  a list of the phrases as strings.
     */
    private ArrayList<String> getPhrasesOverLength(int minLength) {
        ArrayList<String> phraseList = new ArrayList<String>();
        for(CharSequence phrase: phrases.allPhrases()) {
            int phraseLength = phrase.length();
            if(phraseLength >= minLength){
                phraseList.add(phrase.toString());
            }
        }

        return phraseList;
    }

    /**
     * @return string with newline separators for each file path
     */
    @Override
    public final String toString() {
        StringBuilder fileCollection = new StringBuilder();
        Collection<SourceCodeFile> sourceFiles = files.values();
        sourceFiles.forEach(file -> {fileCollection.append(file.toString());});
        return fileCollection.toString();
    }
}
