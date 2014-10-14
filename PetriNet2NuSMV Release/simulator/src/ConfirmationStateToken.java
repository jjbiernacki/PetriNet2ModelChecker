package simulator;

import pkowalski.rtcp.runtime.model.Token;

import java.util.ArrayList;
import java.util.List;

public class ConfirmationStateToken extends Token{
    protected ConfirmationStateEnum _value;

    public ConfirmationStateEnum getValue() {
        return _value;
    }


    public ConfirmationStateToken(ConfirmationStateEnum value){
        super();
        _value = value;

    }

    private static List<ConfirmationStateToken> _allTokens;

    public static List<ConfirmationStateToken> GetAllTokens() {
        if (_allTokens == null)
            _allTokens = GenerateValues();
        return _allTokens;
    }

    private static List<ConfirmationStateToken> GenerateValues(){
        List<ConfirmationStateToken> tokens = new ArrayList<ConfirmationStateToken>();

        tokens.add(new ConfirmationStateToken(ConfirmationStateEnum.on));
        tokens.add(new ConfirmationStateToken(ConfirmationStateEnum.off));

        return tokens;
    }

    public static String toString(ConfirmationStateEnum value){
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
        if (!(other instanceof ConfirmationStateToken))
            return false;



        boolean eq = this._value.equals(((ConfirmationStateToken) other)._value);

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