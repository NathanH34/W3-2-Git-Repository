package edu.odu.cs.cs350;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.ArrayList;


public class DupDetector {
    
    public static void main(String [] args) {
        int nSuggestions = 0;
        //String propertiesPath = "";
        SourceCodeFileCollection fileCollection = new SourceCodeFileCollection();
        ArrayList<String> validExtensions = new ArrayList<String>(); //should have h and cpp by default
		int validMinSequenceLength = 10;
		int validMaxSubstitutions = 8;

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
            		Properties propertyFile = loadPropertiesFile(args[i]);

            		validExtensions = extractCppExtensions(propertyFile, validExtensions);

            		validMinSequenceLength = addMinSequenceLength(propertyFile);

					validMaxSubstitutions = addMaxSubstitutions(propertyFile);

            		for(int k=2; k<args.length; k++) { //go through files that were specified after properties file
            			File codeFile = new File(args[k]);
            			searchFiles(codeFile, fileCollection, validExtensions);
            		}
            	}
            	else if(i!=1 && file.getAbsolutePath().contains(".ini")) { //properties file was specified in the wrong place
            		System.err.println("Usage: nSuggestions [ properties ] path1 path2 ... ]");
            		System.err.println("[ ... ] denotes optional parameters");
            		return;
            	}
            	else { //no properties file was specified, search for .h and .cpp files
            		searchForDefaults(file, fileCollection, validExtensions);
            	}
            }      
        } catch(Exception e) {
            System.out.print(e.toString());
        }
        
		//Print Section 1 of output
		System.out.print(fileCollection.toString());
		System.out.println("\n");
		//Print Section 2 of output
		ArrayList<Refactoring> refactorings = fileCollection.findRefactorings(validMinSequenceLength);
		for(Refactoring r: refactorings) {
			System.out.print(r.toString());
			System.out.println("\n");
			
		}
	    
		int kSuggestions;
		kSuggestions = refactorings.size(); 
		System.out.println("Printed " + nSuggestions + " of " + kSuggestions + " suggestions");
	
    }

	/**
	 * Extract a valid maximum number of substitutions from the properties file
	 * @param propertyFile file to get maximum number of substitutions from
	 * @return the maximum number of substitutions for recommendation
	 */
	public static int addMaxSubstitutions(Properties propertyFile){

		// Maximum recommendations is defaulted to 8
		int maxSubstitutions = 8;
		int extractedMaxSubstitutions = 0;

		try{
			extractedMaxSubstitutions = Integer.parseInt(propertyFile.getProperty("MaxSubstitutions"));
		}
        catch (NumberFormatException e){
            e.printStackTrace();
        }

		if(extractedMaxSubstitutions < 1) {
			// Invalid input, return defaulted value
			return maxSubstitutions;
		} else {
			return extractedMaxSubstitutions;
		}
	}

	/**
	 * Extract a valid minimum sequence length from the properties file
	 * @param propertyFile file to get minimum sequence length from
	 * @return the minimum sequence length for refactorization consideration
	 */
	public static int addMinSequenceLength(Properties propertyFile){
		int extractedMinLength = 0;

		try{
			extractedMinLength = Integer.parseInt(propertyFile.getProperty("MinSequenceLength"));
		}
        catch (NumberFormatException e){
            e.printStackTrace();
        }

		if(extractedMinLength < 1) {
			// Invalid input, return defaulted value
			return 10;
		} else {
			return extractedMinLength;
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
	 * Extract and print valid file extensions from the properties file
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
				extensions.add(srcExtensions[j].toLowerCase());
			}
		}
		
		return extensions;
	}

	/**
	 * Search for .h and .cpp files and print their paths
	 * @param path path to file or directory to search
	 * @param fileCollection collection of source code files
	 * @param validExtensions list of extensions to search for 
	 */
	public static void searchForDefaults(File path, SourceCodeFileCollection fileCollection, ArrayList<String> validExtensions) {
		if(!(validExtensions.contains("h"))) {
			validExtensions.add("h");
		}
		if(!(validExtensions.contains("cpp"))) {
			validExtensions.add("cpp");
		}
    	System.out.println(validExtensions.toString());
		if(path.isFile()) { //check if path argument represents a file
    		String extension = getFileExtension(path);
    		String ext = extension.toLowerCase();
    		if(validExtensions.contains(ext)) {
        		SourceCodeFile src = new SourceCodeFile(path.getAbsolutePath());
                fileCollection.add(src);
    		}
    	}
    	else if(path.isDirectory()) { //check if path argument represents a directory
    		searchFiles(path, fileCollection, validExtensions);
    	}
	}
	
}
