package edu.odu.cs.cs350;


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
        } catch(Exception e) {
            System.out.println("The filepath must be entered");
        }

        src= new SourceCodeFile(filePath);
        System.out.print(src.reportNameAndTokens());
    }
}
