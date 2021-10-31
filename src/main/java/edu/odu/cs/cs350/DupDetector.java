package edu.odu.cs.cs350;
import java.io.File;
import java.util.Iterator;

public class DupDetector {
    

    public static void main(String [] args) {
        int nSuggestions = 0;
        String propertiesPath = "";
        SourceCodeFileCollection fileCollection = new SourceCodeFileCollection();
        /**
         * Not final logic, just placeholder to process filepath args.
         */

        //Placeholder try block. nSuggestions does nothing but must be entered to run.
        try {
            nSuggestions = Integer.parseInt(args[0]);
        } catch(Exception e) {
            System.out.print(e.toString());
        }
        try {
            for(int i=1; i<args.length; i++) { //go through all specified file paths
            	File file = new File(args[i]);
            	if(file.isFile()) { //check if path argument represents a file
	            	SourceCodeFile src = new SourceCodeFile(file.getAbsolutePath());
                    fileCollection.add(src)
            	}
            	else if(file.isDirectory()) { //check if path argument represents a directory
            		searchFiles(file, fileCollection);
            	}
            }      
        } catch(Exception e) {
            System.out.print(e.toString());
        }
           
    }
    
    /**
     * Recursively search for files in a directory and its sub-directories and print their absolute paths
     * @param dir directory to be searched
     */
    public static void searchFiles(File dir, SourceCodeFileCollection collection) {
    	File[] files = dir.listFiles();
    	for(File f: files) {
            if(f.isFile()) {
                SourceCodeFile srcFile = new SourceCodeFile(f.getAbsolutePath());
    		    collection.add(srcFile);
            } else if(f.isDirectory()) {
    			searchFiles(f, collection);
    		}
    	}
    }
}
