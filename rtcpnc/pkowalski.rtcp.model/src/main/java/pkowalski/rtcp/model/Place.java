package pkowalski.rtcp.model;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-06
 * Time:    12:37:09
 *
 */
public interface Place{
    public Page getPage();
    public void setPage(Page value);

    public String getName();
    public void setName(String value);

    public Color getColor();

    public Marking getMarking();

    public Integer getTime();

    public Fusion getFusion();

    //public PortType getPortType();

    public List<Arc> getArcs();

}
