package simulator;

import pkowalski.rtcp.runtime.model.IntToken;

import java.util.ArrayList;
import java.util.List;

public class DelayToken extends IntToken{
    public DelayToken(int value) {
        super(value);
    }

    private static List<DelayToken> _allTokens;
    public static List<DelayToken> GetAllTokens(){
        if (_allTokens == null)
            _allTokens = GenerateTokens();

        return _allTokens;
    }
    private static List<DelayToken> GenerateTokens(){
        List<DelayToken> tokens = new ArrayList<DelayToken>();

        for(int i = 0; i <= 300; i++){
            tokens.add(new DelayToken(i));
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
        eq = this._value.equals(((DelayToken)other)._value);

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