
package edu.odu.cs.cs350;
import java.io.File;
import java.util.Iterator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.List;
import java.util.ArrayList;


public class DupDetector {
    

    public static void main(String [] args) {
        int nSuggestions = 0;
        //String propertiesPath = "";
        SourceCodeFileCollection fileCollection = new SourceCodeFileCollection();
        ArrayList<String> validExtensions = new ArrayList<String>(); //should have h and cpp by default
        
        /**
         * TODO: placeholder to process filepath args, Not final logic
         */

        // Placeholder try block. nSuggestions does nothing but must be entered to run.
        try {
            nSuggestions = Integer.parseInt(args[0]);
        } catch(Exception e) {
            System.out.print(e.toString());
        }
        try {
            System.out.print("Files scanned: ");
        	for(int i=1; i<args.length; i++) { //go through all specified file paths
            	File file = new File(args[i]);
            	if(i==1 && file.getAbsolutePath().contains(".ini")) { //properties file was properly specified
            		//work with properties file
            		Properties propertyFile = loadPropertiesFile(args[i]);
            		validExtensions = extractCppExtensions(propertyFile, validExtensions);
            		minSequenceLength = extractMinLength(propertyFile);
            		for(int k=2; k<args.length; k++) { //go through files that were specified after properties file
            			File codeFile = new File(args[k]);
            			searchFiles(codeFile, fileCollection, validExtensions);
            		}
            		return;
            	}
            		
            	else { //no properties file was specified, search for .h and .cpp files
            		validExtensions.add("h");
                	validExtensions.add("cpp");
                	System.out.println(validExtensions.toString());
            		if(file.isFile()) { //check if path argument represents a file
	            		String extension = getFileExtension(file);
	            		String ext = extension.toLowerCase();
	            		if(validExtensions.contains(ext)) {
		            		SourceCodeFile src = new SourceCodeFile(file.getAbsolutePath());
		                    fileCollection.add(src);
	            		}
	            	}
	            	else if(file.isDirectory()) { //check if path argument represents a directory
	            		searchFiles(file, fileCollection, validExtensions);
	            	}
	            	validExtensions.toString();
            	}
            }      
        } catch(Exception e) {
            System.out.print(e.toString());
        }
        
		//Print Section 1 of output
		System.out.print(fileCollection.toString());
		//Print Section 2 of output
		ArrayList<Refactoring> refactorings = fileCollection.findRefactorings(10);
		for(Refactoring r: refactorings) {
			System.out.print(r.toString());
		}
    }
    
    /**
     * Recursively search for files in a directory and its sub-directories and print their absolute paths
     * @param path path to a source code file or a directory containing source code files
     */
    public static void searchFiles(File path, SourceCodeFileCollection collection, ArrayList<String> validExtensions) {    	
    	if(path.isFile() && validExtensions.contains(getFileExtension(path))) {     
    		SourceCodeFile srcFile = new SourceCodeFile(path.getAbsolutePath());
            collection.add(srcFile);  
            } else if(path.isDirectory()) {            	
            	File[] files = path.listFiles();
    			for(File f: files) {
    				searchFiles(f, collection, validExtensions);
    			}
    		}	
    }
    
    /**
     * Retrieve the extension of a file
     * @param file file to find the extension of
     * @return the file extension
     */
    public static String getFileExtension(File file) {
    	int index = file.getAbsolutePath().lastIndexOf('.');
    	String extension = file.getAbsolutePath().substring(index+1);
    	return extension;
    }

	/**
	 * Load in the properties file
 	 * @param arg the filepath to the properties file
 	 * @return the properties variable with loaded properties file
 	 */

	public static Properties loadPropertiesFile(String arg){
		Properties properties = new Properties();
		try{
			FileInputStream propertiesFileStream = new FileInputStream(arg);
   			try{
				properties.load(propertiesFileStream); //load properties from the properties file
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * Extract valid file extensions from the properties file
	 * @param propertyFile file to get file extensions from
	 * @param validExtensions file extensions to search for 
	 * @return a list of valid file extensions
	 */
	public static ArrayList<String> extractCppExtensions(Properties propertyFile, ArrayList<String> validExtensions){
		ArrayList<String> extensions = new ArrayList<String>();
		
		String cppExtensions = propertyFile.getProperty("CppExtensions");
		String[] srcExtensions = cppExtensions.split(","); //get valid file extensions separated by a comma
		for(int j=0; j<srcExtensions.length; j++) {
			if(!(validExtensions.contains(srcExtensions[j]))) {
				validExtensions.add(srcExtensions[j].toLowerCase());
			}
		}
		System.out.println(validExtensions.toString());
		
		return extensions;
	}

}