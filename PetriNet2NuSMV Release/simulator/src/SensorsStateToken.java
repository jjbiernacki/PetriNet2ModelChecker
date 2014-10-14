package simulator;

import pkowalski.rtcp.runtime.model.Token;

import java.util.ArrayList;
import java.util.List;

public class SensorsStateToken extends Token{
    protected SensorsStateEnum _value;

    public SensorsStateEnum getValue() {
        return _value;
    }


    public SensorsStateToken(SensorsStateEnum value){
        super();
        _value = value;

    }

    private static List<SensorsStateToken> _allTokens;

    public static List<SensorsStateToken> GetAllTokens() {
        if (_allTokens == null)
            _allTokens = GenerateValues();
        return _allTokens;
    }

    private static List<SensorsStateToken> GenerateValues(){
        List<SensorsStateToken> tokens = new ArrayList<SensorsStateToken>();

        tokens.add(new SensorsStateToken(SensorsStateEnum.normal));
        tokens.add(new SensorsStateToken(SensorsStateEnum.warning));
        tokens.add(new SensorsStateToken(SensorsStateEnum.dblWarning));

        return tokens;
    }

    public static String toString(SensorsStateEnum value){
        switch(value){
            case normal: return "normal";
            case warning: return "warning";
            case dblWarning: return "dblWarning";
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
        if (!(other instanceof SensorsStateToken))
            return false;



        boolean eq = this._value.equals(((SensorsStateToken) other)._value);

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