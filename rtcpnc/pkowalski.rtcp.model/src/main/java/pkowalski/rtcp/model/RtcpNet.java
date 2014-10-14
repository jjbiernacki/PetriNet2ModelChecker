package pkowalski.rtcp.model;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-06
 * Time:    13:41:39
 *
 */
public interface RtcpNet {
    public List<Fusion> getFusions();
    public List<Color> getColors();
    public List<Variable> getVariables();
    public List<Page> getPages();
    public List<Page> getRootPages();

}
