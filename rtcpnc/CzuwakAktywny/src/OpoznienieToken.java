package CzuwakAktywny;

import pkowalski.rtcp.runtime.model.IntToken;

import java.util.ArrayList;
import java.util.List;

public class OpoznienieToken extends IntToken{
    public OpoznienieToken(int value) {
        super(value);
    }

    private static List<OpoznienieToken> _allTokens;
    public static List<OpoznienieToken> GetAllTokens(){
        if (_allTokens == null)
            _allTokens = GenerateTokens();

        return _allTokens;
    }
    private static List<OpoznienieToken> GenerateTokens(){
        List<OpoznienieToken> tokens = new ArrayList<OpoznienieToken>();

        for(int i = 5; i <= 10; i++){
            tokens.add(new OpoznienieToken(i));
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
        eq = this._value.equals(((OpoznienieToken)other)._value);

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