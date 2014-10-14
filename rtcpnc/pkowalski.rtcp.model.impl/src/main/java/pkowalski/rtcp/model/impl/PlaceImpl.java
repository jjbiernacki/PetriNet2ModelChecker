package pkowalski.rtcp.model.impl;

import pkowalski.rtcp.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-10
 * Time:    17:56:45
 *
 */
public class PlaceImpl implements Place{

    public PlaceImpl(){
        // Add your code here:
        super();
        Init();
    }

    private void Init(){
        _arcs = new ArrayList<Arc>();
    }

    private Page _page;

    @Override
    public Page getPage() {
        return _page;
    }

    @Override
    public void setPage(Page value) {
        _page = value;
    }

    private String _name;

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setName(String value) {
        _name = value;
    }

    private Color _color;

    @Override
    public Color getColor() {
        return _color;
    }


    public void setColor(Color value) {
        _color = value;
    }

    private Marking _marking;

    @Override
    public Marking getMarking() {
        return _marking;
    }


    public void setMarking(Marking value) {
        _marking = value;
    }

    private Integer _time;

    @Override
    public Integer getTime() {
        return _time;
    }


    public void setTime(Integer value) {
        _time = value;
    }

    private Fusion _fusion;

    @Override
    public Fusion getFusion() {
        return _fusion;
    }


    public void setFusion(Fusion value) {
        _fusion = value;
    }

    private PortType _portType;

    /*
    @Override
    public PortType getPortType() {
        return _portType;
    }

    
    public void setPortType(PortType value) {
        _portType = value;
    }
    */


    private List<Arc> _arcs;

    @Override
    public List<Arc> getArcs() {
        return _arcs;
    }

    @Override
    public String toString(){
        return _name;
    }
}
