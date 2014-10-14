package pkowalski.rtcp.model;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-08
 * Time:    12:30:56
 *
 */
public interface IntColor extends ScalarColor{
    public int getLowerBound();

    public int getUpperBound();

    public IntVariable Parse(String s)throws OutOfRangeException;
    public IntVariable CreateEmpty();
}
