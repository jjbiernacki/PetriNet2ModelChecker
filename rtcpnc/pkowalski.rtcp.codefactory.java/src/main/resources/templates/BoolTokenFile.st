package $package_name$;

import pkowalski.rtcp.runtime.model.BoolToken;

import java.util.ArrayList;
import java.util.List;

public class $color_name$Token extends BoolToken{

    public $color_name$Token(boolean value){
        // Add your code here:
        super(value);

    }

    private static List<$color_name$Token> _allTokens;

    public static List<$color_name$Token> GetAllTokens(){
        if (_allTokens == null)
            _allTokens = GenerateTokens();
        return _allTokens;
    }

    public static List<$color_name$Token> GenerateTokens(){
        List<$color_name$Token> tokens = new ArrayList<$color_name$Token>();

        tokens.add(new $color_name$Token(true));
        tokens.add(new $color_name$Token(false));

        return tokens;
    }

    public static String toString(boolean value){
        return value ? "$true_ident$" : "$false_ident$";
    }

    @Override
    public String toString(){
        return toString(getValue());        
    }

    @Override
    public boolean equals(Object other){
        if ((other == null) || getClass() != other.getClass())
            return false;


        boolean eq = this._value.equals((($color_name$Token)other)._value);

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
