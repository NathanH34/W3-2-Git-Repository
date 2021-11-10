package edu.odu.cs.cs350;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import javax.print.attribute.SetOfIntegerSyntax;

public class Refactoring {
    private LinkedHashMap<SourceCodeFile, ArrayList<Integer>> sequenceStartLocations;
    private LinkedHashSet<SourceCodeFile> sourceFiles;
    private int sequenceLength;
    private int opportunityValue;

    public Refactoring() {
        sequenceStartLocations = new LinkedHashMap<SourceCodeFile, ArrayList<Integer>>();
        sequenceLength = 0;
        opportunityValue = 0;
    }

    public void setSequenceLength(int newLength) {
        sequenceLength = newLength;
    }

    /**
     * Adds a source file to the refactoring, along with the startings locations of the sequence in the file. 
     * @param file the file to be included in the refactoring
     * @param startLocations A list of integers corresponding to the beginning token indices of each instance of the sequence in the file.
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
        for(SourceCodeFile s: sourceFiles) {
            for(Integer i : sequenceStartLocations.get(s)) {

            }
        }

        return sb.toString();
    }
}
