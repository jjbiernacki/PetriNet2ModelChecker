package pkowalski.rtcp.runtime.model;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 * WEAIiE, AGH Kraków
 * pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-07-04
 * Time:    20:21:16
 */
@SuppressWarnings({"UnusedDeclaration"})
public class BoolToken extends Token{

    protected final Boolean _value;

    public boolean getValue() {
        return _value;
    }
    public BoolToken(boolean value){
        // Add your code here:
        super();
        _value = value;   
    }

}
