package pkowalski.rtcp.model;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-09
 * Time:    22:49:37
 *
 */
public interface EnumVariable extends ScalarVariable{
    public String getValue() throws VariableNotInitialized;
}
