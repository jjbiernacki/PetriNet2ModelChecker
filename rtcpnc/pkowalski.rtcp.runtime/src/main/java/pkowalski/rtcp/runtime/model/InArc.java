package pkowalski.rtcp.runtime.model;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 * WEAIiE, AGH Kraków
 * pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-07-04
 * Time:    20:24:35
 */
public interface InArc {
    public Token getInToken();
    public int getInTime();
    public String getRawInExpression();

    
}
