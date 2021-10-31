package edu.odu.cs.cs350;
import java.util.ArrayList;
import java.util.Collections;

public class SourceCodeFileCollection {
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

    public void output(){
        for (SourceCodeFile f: files) {
            System.out.print(f.toString());
        }
    }
}
