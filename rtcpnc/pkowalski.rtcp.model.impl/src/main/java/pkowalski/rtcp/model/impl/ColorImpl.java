package pkowalski.rtcp.model.impl;

import pkowalski.rtcp.model.Color;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-08
 * Time:    12:44:31
 *
 */
public abstract class ColorImpl implements Color{
    ColorImpl(){}

    private String _name;
    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setName(String value) {
        _name = value;
    }

    @Override
    public String toString(){
        return _name;
    }

    @SuppressWarnings({"CloneDoesntDeclareCloneNotSupportedException"})
    @Override
    public abstract Object clone();
}
