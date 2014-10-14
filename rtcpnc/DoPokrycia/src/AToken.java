package DoPokrycia;

import pkowalski.rtcp.runtime.model.Token;

import java.util.ArrayList;
import java.util.List;

public class AToken extends Token{
    protected AEnum _value;

    public AEnum getValue() {
        return _value;
    }


    public AToken(AEnum value){
        super();
        _value = value;

    }

    private static List<AToken> _allTokens;

    public static List<AToken> GetAllTokens() {
        if (_allTokens == null)
            _allTokens = GenerateValues();
        return _allTokens;
    }

    private static List<AToken> GenerateValues(){
        List<AToken> tokens = new ArrayList<AToken>();

        tokens.add(new AToken(AEnum.p));
        tokens.add(new AToken(AEnum.r));
        tokens.add(new AToken(AEnum.s));

        return tokens;
    }

    public static String toString(AEnum value){
        switch(value){
            case p: return "p";
            case r: return "r";
            case s: return "s";
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
        if (!(other instanceof AToken))
            return false;



        boolean eq = this._value.equals(((AToken) other)._value);

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