package pkowalski.rtcp.model;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-09
 * Time:    22:11:37
 *
 */
public interface IntVariable extends ScalarVariable{
    public Integer getValue()throws VariableNotInitialized;
}
