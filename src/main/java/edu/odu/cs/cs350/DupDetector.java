package edu.odu.cs.cs350;
import java.io.File;

public class DupDetector {

    public static void main(String [] args) {
        int nSuggestions = 0;
        String propertiesPath = "";
        String filePath = "";
        SourceCodeFile src;

        /**
         * Not final logic, just placeholder to process filepath args.
         */

        //Placeholder try block. nSuggestions does nothing but must be entered to run.
        try {
            nSuggestions = Integer.parseInt(args[0]);
        } catch(Exception e) {
            System.out.println("The nSuggestions argument must be entered");
        }
        try {
            filePath = args[1];
            for(int i=1; i<args.length; i++) { 
	            File file = new File(args[i]);
	            searchFiles(file);
            }
        } catch(Exception e) {
            System.out.println("The filepath must be entered");
        }

       
    }
    
    /**
     * Recursively search for files in a directory and its subdirectories
     * @param file directory to be searched
     */
    public static void searchFiles(File file) {
    	if(file.isFile()) { //check if path argument represents a file
        	System.out.println(file.getAbsolutePath()); //print the file's path 
        }
    	else if(file.isDirectory()) { //check if path argument represents a directory
    		File[] files = file.listFiles();
    		for(File f: files) {
    			System.out.println(f.getAbsolutePath());
    			if(f.isDirectory()) {
    				searchFiles(f);
    			}
    		}
    	}
    }
}
