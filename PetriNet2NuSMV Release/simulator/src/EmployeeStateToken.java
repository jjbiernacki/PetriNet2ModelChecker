package simulator;

import pkowalski.rtcp.runtime.model.Token;

import java.util.ArrayList;
import java.util.List;

public class EmployeeStateToken extends Token{
    protected EmployeeStateEnum _value;

    public EmployeeStateEnum getValue() {
        return _value;
    }


    public EmployeeStateToken(EmployeeStateEnum value){
        super();
        _value = value;

    }

    private static List<EmployeeStateToken> _allTokens;

    public static List<EmployeeStateToken> GetAllTokens() {
        if (_allTokens == null)
            _allTokens = GenerateValues();
        return _allTokens;
    }

    private static List<EmployeeStateToken> GenerateValues(){
        List<EmployeeStateToken> tokens = new ArrayList<EmployeeStateToken>();

        tokens.add(new EmployeeStateToken(EmployeeStateEnum.act));
        tokens.add(new EmployeeStateToken(EmployeeStateEnum.idle));

        return tokens;
    }

    public static String toString(EmployeeStateEnum value){
        switch(value){
            case act: return "act";
            case idle: return "idle";
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
        if (!(other instanceof EmployeeStateToken))
            return false;



        boolean eq = this._value.equals(((EmployeeStateToken) other)._value);

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