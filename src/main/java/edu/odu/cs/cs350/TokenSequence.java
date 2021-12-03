package edu.odu.cs.cs350;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;
import java.lang.StringBuilder;

public class TokenSequence {
    private ArrayList<Integer> parameterOrder;
    private LinkedHashMap<Lexeme, Integer> lexemeMap;
    private ArrayList<CPPToken> tokens;
	private int numParameters;

	public TokenSequence() {
        parameterOrder = new ArrayList<Integer>();
        lexemeMap = new LinkedHashMap<Lexeme, Integer>();
        tokens = new ArrayList<CPPToken>();
    }

	public TokenSequence(ArrayList<CPPToken> argTokenSequence) {
		parameterOrder = new ArrayList<Integer>();
        lexemeMap = new LinkedHashMap<Lexeme, Integer>();
        tokens = argTokenSequence;
		findLexemeMappings();
	}

    public ArrayList<Integer> getParameterOrder() {
    	return parameterOrder;
    }
    
    public LinkedHashMap<Lexeme, Integer> getLexemeMap() {
    	return lexemeMap;
    }
    
    public void setTokens(ArrayList<CPPToken> newTokens) {
    	tokens = newTokens;
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
					lexemeMap.put(token.getLexeme(),lexemeMap.size());
					parameterOrder.add(lexemeMap.get(token.getLexeme()));
				}
			}
		}
	}

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
