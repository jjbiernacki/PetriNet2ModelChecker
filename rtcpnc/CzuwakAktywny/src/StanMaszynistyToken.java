package CzuwakAktywny;

import pkowalski.rtcp.runtime.model.Token;

import java.util.ArrayList;
import java.util.List;

public class StanMaszynistyToken extends Token{
    protected StanMaszynistyEnum _value;

    public StanMaszynistyEnum getValue() {
        return _value;
    }


    public StanMaszynistyToken(StanMaszynistyEnum value){
        super();
        _value = value;

    }

    private static List<StanMaszynistyToken> _allTokens;

    public static List<StanMaszynistyToken> GetAllTokens() {
        if (_allTokens == null)
            _allTokens = GenerateValues();
        return _allTokens;
    }

    private static List<StanMaszynistyToken> GenerateValues(){
        List<StanMaszynistyToken> tokens = new ArrayList<StanMaszynistyToken>();

        tokens.add(new StanMaszynistyToken(StanMaszynistyEnum.aktywny));

        return tokens;
    }

    public static String toString(StanMaszynistyEnum value){
        switch(value){
            case aktywny: return "aktywny";
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
        if (!(other instanceof StanMaszynistyToken))
            return false;



        boolean eq = this._value.equals(((StanMaszynistyToken) other)._value);

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