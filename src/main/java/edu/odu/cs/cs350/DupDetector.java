package edu.odu.cs.cs350;
import java.io.File;

public class DupDetector {
    private String PROPERTIES_EXTENSION = ".ini";

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
	            if(file.isFile()) { //check if path argument represents a file
	            	if(file.getName().contains(".ini")) {
	            		//work with properties file
	            		
	            	}
	            	System.out.println(file.getAbsolutePath()); //print the file's path 
	            }
	            if(file.isDirectory()) { //check if path argument represents a directory
	            	File dir = new File(args[i]);
	            	for(File f: dir.listFiles()) {
	            		System.out.println(f.getAbsolutePath()); //print the file's path
	            	}
	            }
            }
            
        } catch(Exception e) {
            System.out.println("The filepath must be entered");
        }

        /*
        src= new SourceCodeFile(filePath);
        System.out.print(src.reportNameAndTokens());
        */
    }
}
