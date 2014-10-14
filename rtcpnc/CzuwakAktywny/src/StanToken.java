package CzuwakAktywny;

import pkowalski.rtcp.runtime.model.BoolToken;

import java.util.ArrayList;
import java.util.List;

public class StanToken extends BoolToken{

    public StanToken(boolean value){
        // Add your code here:
        super(value);

    }

    private static List<StanToken> _allTokens;

    public static List<StanToken> GetAllTokens(){
        if (_allTokens == null)
            _allTokens = GenerateTokens();
        return _allTokens;
    }

    public static List<StanToken> GenerateTokens(){
        List<StanToken> tokens = new ArrayList<StanToken>();

        tokens.add(new StanToken(true));
        tokens.add(new StanToken(false));

        return tokens;
    }

    public static String toString(boolean value){
        return value ? "wl" : "wyl";
    }

    @Override
    public String toString(){
        return toString(getValue());        
    }

    @Override
    public boolean equals(Object other){
        if ((other == null) || getClass() != other.getClass())
            return false;


        boolean eq = this._value.equals(((StanToken)other)._value);

        return eq;
    }

    @Override
    public int hashCode(){
        int hash = 13;
        hash = 27 * hash * this.getClass().hashCode();
        hash = 27 * hash * this._value.hashCode();

        return hash;

    }

}