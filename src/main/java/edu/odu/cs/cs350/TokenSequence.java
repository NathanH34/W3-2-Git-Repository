package edu.odu.cs.cs350;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TokenSequence {
    private ArrayList<Integer> parameterOrder;
    private LinkedHashMap<Lexeme, Integer> lexemeMap;
    private ArrayList<CPPToken> tokens;

	public TokenSequence() {
        parameterOrder = new ArrayList<Integer>();
        lexemeMap = new LinkedHashMap<Lexeme, Integer>();
        tokens = new ArrayList<CPPToken>();
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
					
				}
			}
		}
	}
}
