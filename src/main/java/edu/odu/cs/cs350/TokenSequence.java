package edu.odu.cs.cs350;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

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
    
	public void findLexemeMappings() {
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
		Set<Lexeme> Lexemes = lexemeMap.keySet();
		for () //For lexeme set,
		//convert lexeme to a string, then add to set of strings
		//return the final set
	}

	@Override
    public final String toString() {
		//call the pullLexemeStrings() function in return statement
		return ("\n");
    }

}
