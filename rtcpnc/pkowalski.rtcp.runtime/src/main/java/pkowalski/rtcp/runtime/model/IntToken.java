package pkowalski.rtcp.runtime.model;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 * WEAIiE, AGH Kraków
 * pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-07-04
 * Time:    20:52:17
 */
@SuppressWarnings({"UnusedDeclaration"})
public class IntToken extends Token{



    protected final Integer _value;

    public int getValue() {
        return _value;
    }




    public IntToken(int value){
        // Add your code here:
        super();

        _value = value;

    }

   
}
