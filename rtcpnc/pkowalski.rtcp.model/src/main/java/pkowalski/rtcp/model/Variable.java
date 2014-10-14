package pkowalski.rtcp.model;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-06
 * Time:    13:34:05
 *
 */
public interface Variable {
    public String getName();
    public void setName(String value);

    public Color getColor();
    public void setColor(Color value);

    public Object getValue() throws VariableNotInitialized;

}
