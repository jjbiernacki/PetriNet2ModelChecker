package pkowalski.rtcp.model;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-08
 * Time:    12:39:18
 *
 */
public interface ProductColor extends Color{
    public List<ScalarColor> getScalarColors();

    public ProductVariable Parse(String s)throws OutOfRangeException;
    public ProductVariable CreateEmpty();
}
