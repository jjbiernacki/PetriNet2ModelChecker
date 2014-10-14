package simulator;

import pkowalski.rtcp.runtime.model.Token;

import java.util.ArrayList;
import java.util.List;

public class ClockStateToken extends Token{
    protected ClockStateEnum _value;

    public ClockStateEnum getValue() {
        return _value;
    }


    public ClockStateToken(ClockStateEnum value){
        super();
        _value = value;

    }

    private static List<ClockStateToken> _allTokens;

    public static List<ClockStateToken> GetAllTokens() {
        if (_allTokens == null)
            _allTokens = GenerateValues();
        return _allTokens;
    }

    private static List<ClockStateToken> GenerateValues(){
        List<ClockStateToken> tokens = new ArrayList<ClockStateToken>();

        tokens.add(new ClockStateToken(ClockStateEnum.on));
        tokens.add(new ClockStateToken(ClockStateEnum.off));

        return tokens;
    }

    public static String toString(ClockStateEnum value){
        switch(value){
            case on: return "on";
            case off: return "off";
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
        if (!(other instanceof ClockStateToken))
            return false;



        boolean eq = this._value.equals(((ClockStateToken) other)._value);

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