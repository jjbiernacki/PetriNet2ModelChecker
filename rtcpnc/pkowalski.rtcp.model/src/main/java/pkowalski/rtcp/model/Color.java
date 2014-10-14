package pkowalski.rtcp.model;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-06
 * Time:    12:41:13
 *
 */
public interface Color extends Cloneable{
    public String getName();
    public void setName(String value);

    public Variable Parse(String s) throws OutOfRangeException;

    public Variable CreateEmpty();

    @SuppressWarnings({"CloneDoesntDeclareCloneNotSupportedException"})
    public Object clone();

    

}
