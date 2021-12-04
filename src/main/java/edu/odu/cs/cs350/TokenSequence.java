package edu.odu.cs.cs350;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Set;
import java.lang.StringBuilder;

public class TokenSequence {
	private ArrayList<Integer> parameterOrder;
    private LinkedHashMap<Lexeme, Integer> lexemeMap;
    private ArrayList<CPPToken> tokens;
	private SourceCodeFile sourceCode;
	private int startingLocation;

	public TokenSequence() {
        parameterOrder = new ArrayList<Integer>();
        lexemeMap = new LinkedHashMap<Lexeme, Integer>();
        tokens = new ArrayList<CPPToken>();
		sourceCode = new SourceCodeFile();
		startingLocation = 0;
    }

	public TokenSequence(List<CPPToken> list, SourceCodeFile argSourceCode, int argStartingLocation) {
		parameterOrder = new ArrayList<Integer>();
        lexemeMap = new LinkedHashMap<Lexeme, Integer>();
        tokens = new ArrayList<CPPToken>(list);
		sourceCode = argSourceCode;
		startingLocation = argStartingLocation;
		findLexemeMappings();
	}

	public LinkedHashMap<Lexeme, Integer> getLexemeMap() {
    	return lexemeMap;
    }
    
	public SourceCodeFile getSourceCode() {
		return sourceCode;
	}

	public int getStartingLocation() {
		return startingLocation;
	}

    public void setTokens(ArrayList<CPPToken> newTokens) {
    	tokens = newTokens;
    }
    
	public void setParameterOrder(ArrayList<Integer> newParam) {
    	parameterOrder = newParam;
    }
	
	public ArrayList<Integer> getParameterOrder() {
		return parameterOrder;
	}

    public ArrayList<CPPToken> getTokens() {
    	return tokens;
    }
    
	private void findLexemeMappings() {
		for(CPPToken token : this.tokens) {
			if(token.isParameterizable()) {
				if(lexemeMap.containsKey(token.getLexeme())) { //the lexeme is in the lexemeMap
					parameterOrder.add(lexemeMap.get(token.getLexeme()));
				}
				else { //the lexeme is not in the lexeme map
					lexemeMap.put(token.getLexeme(), lexemeMap.size());
					parameterOrder.add(lexemeMap.get(token.getLexeme()));
				}
			}
		}
	}

	/**
	 * Extract and convert the lexemes from the LexemeMap to strings
	 * @return
	 */
	private String pullLexemeStrings() {
		StringBuilder parametersToPrint = new StringBuilder();
		for(Lexeme lex : lexemeMap.keySet())
			parametersToPrint.append(lex.toString() + " ");
		return parametersToPrint.toString();
	}

	@Override
    public final String toString() {
		String parametersToPrint = pullLexemeStrings();
		return (parametersToPrint + "\n");
    }

}
