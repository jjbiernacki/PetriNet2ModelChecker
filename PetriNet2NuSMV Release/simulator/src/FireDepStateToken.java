package simulator;

import pkowalski.rtcp.runtime.model.Token;

import java.util.ArrayList;
import java.util.List;

public class FireDepStateToken extends Token{
    protected FireDepStateEnum _value;

    public FireDepStateEnum getValue() {
        return _value;
    }


    public FireDepStateToken(FireDepStateEnum value){
        super();
        _value = value;

    }

    private static List<FireDepStateToken> _allTokens;

    public static List<FireDepStateToken> GetAllTokens() {
        if (_allTokens == null)
            _allTokens = GenerateValues();
        return _allTokens;
    }

    private static List<FireDepStateToken> GenerateValues(){
        List<FireDepStateToken> tokens = new ArrayList<FireDepStateToken>();

        tokens.add(new FireDepStateToken(FireDepStateEnum.calledFor));
        tokens.add(new FireDepStateToken(FireDepStateEnum.none));

        return tokens;
    }

    public static String toString(FireDepStateEnum value){
        switch(value){
            case calledFor: return "calledFor";
            case none: return "none";
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
        if (!(other instanceof FireDepStateToken))
            return false;



        boolean eq = this._value.equals(((FireDepStateToken) other)._value);

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