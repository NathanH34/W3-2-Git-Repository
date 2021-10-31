package edu.odu.cs.cs350;
import java.io.File;
import java.util.Iterator;

public class DupDetector {

    public static void main(String [] args) {
        int nSuggestions = 0;
        String propertiesPath = "";
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
            File file = new File(args[1]);
            SourceCodeFile src = new SourceCodeFile(file.getAbsolutePath());
            System.out.print(src.toString());
            Iterator<Token> it =  src.iterator();
            while(it.hasNext()) {
                System.out.print(it.next().toString());
            }
        } catch(Exception e) {
            System.out.print(e.toString());
        }
        
       
    }
    
    /**
     * Recursively search for files in a directory and its subdirectories
     * @param file directory to be searched
     */
    public static void searchFiles(File file) {
    	if(file.isFile()) { //check if path argument represents a file
        	SourceCodeFile src = new SourceCodeFile(file.getAbsolutePath());
            System.out.print(src.toString());
        }
    	else if(file.isDirectory()) { //check if path argument represents a directory
    		File[] files = file.listFiles();
    		for(File f: files) {
    			System.out.println(f.getAbsolutePath());
    			if(f.isDirectory()) { //If there is a sub-directory
    				searchFiles(f);   //search it
    			}
                else if(f.isFile()) { //If there is a file in the directory
                    searchFiles(f);   //**Should we confirm that file is .cpp or .h?
                }
    		}
    	}
    }
}
