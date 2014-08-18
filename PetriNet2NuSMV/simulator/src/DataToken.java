package simulator;

import pkowalski.rtcp.runtime.model.Token;

import java.util.ArrayList;
import java.util.List;

public class DataToken extends Token{
    protected DataEnum _value;

    public DataEnum getValue() {
        return _value;
    }


    public DataToken(DataEnum value){
        super();
        _value = value;

    }

    private static List<DataToken> _allTokens;

    public static List<DataToken> GetAllTokens() {
        if (_allTokens == null)
            _allTokens = GenerateValues();
        return _allTokens;
    }

    private static List<DataToken> GenerateValues(){
        List<DataToken> tokens = new ArrayList<DataToken>();

        tokens.add(new DataToken(DataEnum.d));

        return tokens;
    }

    public static String toString(DataEnum value){
        switch(value){
            case d: return "d";
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
        if (!(other instanceof DataToken))
            return false;



        boolean eq = this._value.equals(((DataToken) other)._value);

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