package pkowalski.rtcp.runtime.model;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 * WEAIiE, AGH Kraków
 * pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-07-04
 * Time:    20:56:35
 */
public interface OutArc {
    public Token getOutToken();
    public int getOutTime();
    public String getRawOutExpression();
    

}
