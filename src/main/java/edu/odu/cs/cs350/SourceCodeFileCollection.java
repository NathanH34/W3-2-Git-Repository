package edu.odu.cs.cs350;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class SourceCodeFileCollection implements Iterable<SourceCodeFile> {
    private ArrayList<SourceCodeFile> files;


    /**
     * Default constructor
     */
    public SourceCodeFileCollection() {
        files = new ArrayList<SourceCodeFile>();
    }

    /**
     * Add new file path 
     * @param newFile SourceCodeFile that contains 
     */
    public void add(SourceCodeFile newFile){
        files.add(newFile);
        order();
    }

    /**
     * Order the source code files in the collection
     * @post the files are in ascending alphabetical order
     */
    public void order(){
        Collections.sort(files);
    }

    /**
     * Iterable implementation
     */
    @Override
    public final Iterator<SourceCodeFile> iterator() {
        return files.iterator();
    }

    /**
     * @return Newline separated strings for each file
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
