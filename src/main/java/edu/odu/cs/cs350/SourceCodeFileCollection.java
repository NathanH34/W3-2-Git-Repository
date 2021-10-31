package edu.odu.cs.cs350;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class SourceCodeFileCollection implements Iterable<SourceCodeFile> {
    private ArrayList<SourceCodeFile> files;

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

    public void order(){
        Collections.sort(files);
    }

    /**
     *
     */
    public SourceCodeFile getFile(int i) {
        return files.get(i);
    }

    /**
     * Iterable implementation
     */
    @Override
    public final Iterator<SourceCodeFile> iterator() {
        return files.iterator();
    }

    @Override
    public final String toString() {
        StringBuilder fileCollection = new StringBuilder();
        for (SourceCodeFile f: files) {
            fileCollection.append(f.toString());
        }
        return fileCollection.toString();
    }
}
