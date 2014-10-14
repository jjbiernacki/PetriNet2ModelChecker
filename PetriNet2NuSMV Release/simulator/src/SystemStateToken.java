package simulator;

import pkowalski.rtcp.runtime.model.Token;

import java.util.ArrayList;
import java.util.List;

public class SystemStateToken extends Token{
    protected SystemStateEnum _value;

    public SystemStateEnum getValue() {
        return _value;
    }


    public SystemStateToken(SystemStateEnum value){
        super();
        _value = value;

    }

    private static List<SystemStateToken> _allTokens;

    public static List<SystemStateToken> GetAllTokens() {
        if (_allTokens == null)
            _allTokens = GenerateValues();
        return _allTokens;
    }

    private static List<SystemStateToken> GenerateValues(){
        List<SystemStateToken> tokens = new ArrayList<SystemStateToken>();

        tokens.add(new SystemStateToken(SystemStateEnum.sv));
        tokens.add(new SystemStateToken(SystemStateEnum.a1));
        tokens.add(new SystemStateToken(SystemStateEnum.a2));
        tokens.add(new SystemStateToken(SystemStateEnum.term));

        return tokens;
    }

    public static String toString(SystemStateEnum value){
        switch(value){
            case sv: return "sv";
            case a1: return "a1";
            case a2: return "a2";
            case term: return "term";
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
        if (!(other instanceof SystemStateToken))
            return false;



        boolean eq = this._value.equals(((SystemStateToken) other)._value);

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