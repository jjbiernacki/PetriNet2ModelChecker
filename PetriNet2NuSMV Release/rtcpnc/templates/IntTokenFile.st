package $package_name$;

import pkowalski.rtcp.runtime.model.IntToken;

import java.util.ArrayList;
import java.util.List;

public class $color_name$Token extends IntToken{
    public $color_name$Token(int value) {
        super(value);
    }

    private static List<$color_name$Token> _allTokens;
    public static List<$color_name$Token> GetAllTokens(){
        if (_allTokens == null)
            _allTokens = GenerateTokens();

        return _allTokens;
    }
    private static List<$color_name$Token> GenerateTokens(){
        List<$color_name$Token> tokens = new ArrayList<$color_name$Token>();

        for(int i = $lower_bound$; i <= $upper_bound$; i++){
            tokens.add(new $color_name$Token(i));
        }

        return tokens;
    }

    public static String toString(int value){
        return String.valueOf(value);
    }

    @Override
    public String toString(){
        return toString(getValue());

    }

    @Override
    public boolean equals(Object other){
        if ((other == null) || getClass() != other.getClass())
            return false;

        boolean eq;
        eq = this._value.equals((($color_name$Token)other)._value);

        return eq;
    }

    @Override
    public int hashCode(){
        int hash = 27;
        hash = 13 * hash * getClass().hashCode();
        hash = 13 * hash * _value.hashCode();

        return hash;
    }
}
