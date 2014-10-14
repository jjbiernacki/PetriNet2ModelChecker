package $package_name$;

import pkowalski.rtcp.runtime.model.Token;

import java.util.ArrayList;
import java.util.List;

public class $color_name$Token extends Token{
    $values:{value|private $value.objecttype$ _value$value.number$;
public $value.simpletype$ getValue$value.number$(){
    return _value$value.number$;
}
    
    };separator="\n\n"$


    public $color_name$Token($values:{value|$value.simpletype$ value$value.number$};separator=", "$){
        // Add your code here:
        super();

        $values:{value|_value$value.number$ = value$value.number$;};separator="\n"$


    }

    private static List<$color_name$Token> _allTokens;

    public static List<$color_name$Token> GetAllTokens(){
        if (_allTokens == null)
            _allTokens = GenerateAllTokens();

        return _allTokens;
    }

    private static List<$color_name$Token> GenerateAllTokens() {
        List<$color_name$Token> tokens = new ArrayList<$color_name$Token>();

        $values:{value|for($value.colorname$Token token$value.number$ : $value.colorname$Token.GetAllTokens())};separator="\n"$
            tokens.add(new $color_name$Token($values:{value|token$value.number$.getValue()};separator=", "$));

       

        return tokens;

    }

    @Override
    public String toString(){
        String s = "(" + $values:{v|$v.colorname$Token.toString(getValue$v.number$())};separator=" + \", \" + "$ + ")";
        return s;
    }

    @Override
    public boolean equals(Object other){
        if (other == null)
            return false;
        if (!(other instanceof $color_name$Token))
            return false;

        boolean eq = true;



        $values:{v|eq = eq && this._value$v.number$.equals((($color_name$Token)other)._value$v.number$);};separator="\n"$

        return eq;
    }

    @Override
    public int hashCode(){
        int hash = 13;

        hash = 27 * hash * this.getClass().hashCode();

        $values:{v|hash = 27 * hash * this._value$v.number$.hashCode();};separator="\n"$
        return hash;
    }


}
