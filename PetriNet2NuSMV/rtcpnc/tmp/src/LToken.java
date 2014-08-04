package tmp;

import pkowalski.rtcp.runtime.model.Token;

import java.util.ArrayList;
import java.util.List;

public class LToken extends Token{
    protected LEnum _value;

    public LEnum getValue() {
        return _value;
    }


    public LToken(LEnum value){
        super();
        _value = value;

    }

    private static List<LToken> _allTokens;

    public static List<LToken> GetAllTokens() {
        if (_allTokens == null)
            _allTokens = GenerateValues();
        return _allTokens;
    }

    private static List<LToken> GenerateValues(){
        List<LToken> tokens = new ArrayList<LToken>();

        tokens.add(new LToken(LEnum.a));
        tokens.add(new LToken(LEnum.b));

        return tokens;
    }

    public static String toString(LEnum value){
        switch(value){
            case a: return "a";
            case b: return "b";
            default: throw new IllegalStateException();
        }

    }

    @Override
    public String toString(){
        return toString(getValue());
    }

    @Override
    public boolean equals(Object other){
        if (other == null)
            return false;
        if (!(other instanceof LToken))
            return false;



        boolean eq = this._value.equals(((LToken) other)._value);

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