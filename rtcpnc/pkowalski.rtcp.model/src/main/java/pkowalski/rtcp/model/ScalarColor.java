package pkowalski.rtcp.model;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-08
 * Time:    12:41:03
 *
 */
public interface ScalarColor extends Color{
    public ScalarVariable Parse(String s) throws OutOfRangeException;

}
