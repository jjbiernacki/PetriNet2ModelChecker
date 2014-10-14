package $package_name$;

import pkowalski.rtcp.runtime.model.Token;

import java.util.ArrayList;
import java.util.List;

public class $color_name$Token extends Token{
    protected $color_name$Enum _value;

    public $color_name$Enum getValue() {
        return _value;
    }


    public $color_name$Token($color_name$Enum value){
        super();
        _value = value;

    }

    private static List<$color_name$Token> _allTokens;

    public static List<$color_name$Token> GetAllTokens() {
        if (_allTokens == null)
            _allTokens = GenerateValues();
        return _allTokens;
    }

    private static List<$color_name$Token> GenerateValues(){
        List<$color_name$Token> tokens = new ArrayList<$color_name$Token>();

        $idents:{ident|tokens.add(new $color_name$Token($color_name$Enum.$ident$));};separator="\n"$

        return tokens;
    }

    public static String toString($color_name$Enum value){
        switch(value){
            $idents:{ident|case $ident$: return "$ident$";};separator="\n"$
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
        if (!(other instanceof $color_name$Token))
            return false;



        boolean eq = this._value.equals((($color_name$Token) other)._value);

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
