package pkowalski.rtcp.model;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-08
 * Time:    12:35:14
 *
 */
public interface EnumColor extends ScalarColor{
    public List<String> getEnumIdents();

    public EnumVariable Parse(String s)throws OutOfRangeException;
    public EnumVariable CreateEmpty();
}
