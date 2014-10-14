package CzuwakAktywny;

import pkowalski.rtcp.runtime.model.Token;

import java.util.ArrayList;
import java.util.List;

public class StanPulpituToken extends Token{
    private Boolean _value1;
    public boolean getValue1(){
        return _value1;
    }
        
        

    private Boolean _value2;
    public boolean getValue2(){
        return _value2;
    }
        
        


    public StanPulpituToken(boolean value1, boolean value2){
        // Add your code here:
        super();

        _value1 = value1;
        _value2 = value2;


    }

    private static List<StanPulpituToken> _allTokens;

    public static List<StanPulpituToken> GetAllTokens(){
        if (_allTokens == null)
            _allTokens = GenerateAllTokens();

        return _allTokens;
    }

    private static List<StanPulpituToken> GenerateAllTokens() {
        List<StanPulpituToken> tokens = new ArrayList<StanPulpituToken>();

        for(StanToken token1 : StanToken.GetAllTokens())
        for(StanToken token2 : StanToken.GetAllTokens())
            tokens.add(new StanPulpituToken(token1.getValue(), token2.getValue()));

       

        return tokens;

    }

    @Override
    public String toString(){
        String s = "(" + StanToken.toString(getValue1()) + ", " + StanToken.toString(getValue2()) + ")";
        return s;
    }

    @Override
    public boolean equals(Object other){
        if (other == null)
            return false;
        if (!(other instanceof StanPulpituToken))
            return false;

        boolean eq = true;



        eq = eq && this._value1.equals(((StanPulpituToken)other)._value1);
        eq = eq && this._value2.equals(((StanPulpituToken)other)._value2);

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