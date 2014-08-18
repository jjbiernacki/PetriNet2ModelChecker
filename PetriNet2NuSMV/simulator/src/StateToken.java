package simulator;

import pkowalski.rtcp.runtime.model.Token;

import java.util.ArrayList;
import java.util.List;

public class StateToken extends Token{
    private DataEnum _value1;
    public DataEnum getValue1(){
        return _value1;
    }
        
        

    private Integer _value2;
    public int getValue2(){
        return _value2;
    }
        
        


    public StateToken(DataEnum value1, int value2){
        // Add your code here:
        super();

        _value1 = value1;
        _value2 = value2;


    }

    private static List<StateToken> _allTokens;

    public static List<StateToken> GetAllTokens(){
        if (_allTokens == null)
            _allTokens = GenerateAllTokens();

        return _allTokens;
    }

    private static List<StateToken> GenerateAllTokens() {
        List<StateToken> tokens = new ArrayList<StateToken>();

        for(DataToken token1 : DataToken.GetAllTokens())
        for(NumberToken token2 : NumberToken.GetAllTokens())
            tokens.add(new StateToken(token1.getValue(), token2.getValue()));

       

        return tokens;

    }

    @Override
    public String toString(){
        String s = "(" + DataToken.toString(getValue1()) + ", " + NumberToken.toString(getValue2()) + ")";
        return s;
    }

    @Override
    public boolean equals(Object other){
        if (other == null)
            return false;
        if (!(other instanceof StateToken))
            return false;

        boolean eq = true;



        eq = eq && this._value1.equals(((StateToken)other)._value1);
        eq = eq && this._value2.equals(((StateToken)other)._value2);

        return eq;
    }

    @Override
    public int hashCode(){
        int hash = 13;

        hash = 27 * hash * this.getClass().hashCode();

        hash = 27 * hash * this._value1.hashCode();
        hash = 27 * hash * this._value2.hashCode();
        return hash;
    }


}