package pkowalski.rtcp.model;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-08
 * Time:    12:32:21
 *
 */
public interface BoolColor extends ScalarColor{
    public String getFalseIdent();

    public String getTrueIdent();

    public BoolVariable Parse(String s) throws OutOfRangeException;
    public BoolVariable CreateEmpty();
}
