package edu.odu.cs.cs350;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import javax.print.attribute.SetOfIntegerSyntax;
import javax.xml.transform.Source;

public class Refactoring implements Comparable<Refactoring>  {
    private LinkedHashMap<SourceCodeFile, ArrayList<Integer>> sequenceStartLocations;
    private LinkedHashSet<SourceCodeFile> sourceFiles;
    private int sequenceLength;
    private int opportunityValue;

    public Refactoring() {
        sequenceStartLocations = new LinkedHashMap<SourceCodeFile, ArrayList<Integer>>();
        sourceFiles = new LinkedHashSet<SourceCodeFile>();
        sequenceLength = 0;
        opportunityValue = 0;
    }

    public Refactoring(int length) {
        sequenceStartLocations = new LinkedHashMap<SourceCodeFile, ArrayList<Integer>>();
        sourceFiles = new LinkedHashSet<SourceCodeFile>();
        sequenceLength = length;
        opportunityValue = 0;
    }

    public void setSequenceLength(int newLength) {
        sequenceLength = newLength;
    }

    public void setOpportunityValue(int newOppValue){
        opportunityValue = newOppValue;
    }

    /**
     * Adds a source file to the refactoring, along with the startings locations of the sequence in the file. 
     * @param file the file to be included in the refactoring
     * @param startLocations A list of integers corresponding to the beginning token indices of each instance of the sequence in the file.
     * post: opportunityValue is updated to new correct value based on new additions to the refactoring
     */
    public void addSource(SourceCodeFile file, ArrayList<Integer> startLocations) {
        if(sourceFiles.contains(file)) {
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            tmp.addAll(startLocations);
            tmp.addAll(sequenceStartLocations.get(file));
            sequenceStartLocations.put(file, tmp);
        } else {
            sourceFiles.add(file);
            sequenceStartLocations.put(file,startLocations);
        }
        updateOpportunityValue();
    }

    public int getOpportunityValue() {
        return opportunityValue;
    }

    public int getSequenceLength() {
        return sequenceLength;
    }

    public LinkedHashSet<SourceCodeFile> getSourceCodeFiles() {
    	return sourceFiles;
    }
    
    private void updateOpportunityValue() {
        int numInstances = 0;
        for(SourceCodeFile s: sourceFiles) {
            numInstances += sequenceStartLocations.get(s).size();
        }
        opportunityValue = (numInstances - 1)*sequenceLength;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Opportunity " + opportunityValue + ", " + sequenceLength + " tokens\n");
        for(SourceCodeFile s: sourceFiles) {
            for(Integer i : sequenceStartLocations.get(s)) {
                // change looping to something
                // for(int i=0; sequenceStartLocations.get(s) - 1 > i; i++)
                // TokenSequence tokenSeq1 = generateTokenSequence(i);
                // TokenSequence tokenSeq2 = generateTokenSequence(i+1);

                CPPToken token = s.getTokenAt(i);
                sb.append("\t" + s.getPath() + ":" + token.getLine() + ":" + token.getColumn() + "\n");

                // ArrayList<CPPToken> validRefactoring = compareParameterOrder(tokenSeq1, tokenSeq2);
                // if(!validRefactoring.isEmpty()) {
                //     // Yes? Print the token output for output section2
                //     //sb.append()
                // }
                // If invalid it skips over
            }
        }

        return sb.toString();
    }

    /**
     * Refactorings are sorted by the size of opportunityValue
     */
    @Override
    public int compareTo(Refactoring r) {
       return opportunityValue - (r.getOpportunityValue());
    }


    /**
     * Generate the token sequence for comparisons
     */
    public TokenSequence generateTokenSeqeunce(Integer i) {
        SourceCodeFile tempSource = new SourceCodeFile();
        ArrayList<CPPToken> tempTokenArrayList = new ArrayList<CPPToken>();
        tempTokenArrayList = tempSource.makeTokenSequence(sequenceLength, i);

        TokenSequence tokenSeq = new TokenSequence(tempTokenArrayList, tempSource, i);

        return tokenSeq;
    }

    /**
     * Compare the token sequence to the next token sequence for duplicity
     */
    public ArrayList<CPPToken> compareParameterOrder(TokenSequence sequence1, TokenSequence sequence2) {
        if (sequence1.getParameterOrder() == sequence2.getParameterOrder()){ /// check for nearly duplicate through Parameters
            if (sequence1.getLexemeMap() == sequence2.getLexemeMap()) {
                return sequence1.getTokens();
            }
            return sequence1.getTokens();
        } 
        else { /// if not duplicate, return a default array with negative starting location
            ArrayList<CPPToken> returnTokenArray = new ArrayList<CPPToken>();
            return returnTokenArray;
        }
    }
}
