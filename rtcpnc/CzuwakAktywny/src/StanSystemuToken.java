package CzuwakAktywny;

import pkowalski.rtcp.runtime.model.IntToken;

import java.util.ArrayList;
import java.util.List;

public class StanSystemuToken extends IntToken{
    public StanSystemuToken(int value) {
        super(value);
    }

    private static List<StanSystemuToken> _allTokens;
    public static List<StanSystemuToken> GetAllTokens(){
        if (_allTokens == null)
            _allTokens = GenerateTokens();

        return _allTokens;
    }
    private static List<StanSystemuToken> GenerateTokens(){
        List<StanSystemuToken> tokens = new ArrayList<StanSystemuToken>();

        for(int i = 0; i <= 3; i++){
            tokens.add(new StanSystemuToken(i));
        }

        return tokens;
    }

    public static String toString(int value){
        return String.valueOf(value);
    }

    @Override
    public String toString(){
        return toString(getValue());

    }

    @Override
    public boolean equals(Object other){
        if ((other == null) || getClass() != other.getClass())
            return false;

        boolean eq;
        eq = this._value.equals(((StanSystemuToken)other)._value);

        return eq;
    }

    @Override
    public int hashCode(){
        int hash = 27;
        hash = 13 * hash * getClass().hashCode();
        hash = 13 * hash * _value.hashCode();

        return hash;
    }
}