package pkowalski.rtcp.model.impl;

import pkowalski.rtcp.model.Color;
import pkowalski.rtcp.model.Variable;
import pkowalski.rtcp.model.VariableNotInitialized;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-08
 * Time:    13:40:49
 *
 */
public abstract class VariableImpl implements Variable{


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

    @Override
    public void setColor(Color value) {
        _color = value;
    }

    private Object _value;
    @Override
    public Object getValue() throws VariableNotInitialized{
        if (_empty){
            throw new VariableNotInitialized();
        }
        return _value;
    }
    
    void setValue(Object value) {
        _value = value;
        _empty = false;
    }

    @Override
    public String toString(){
        return String.format("%s : %s = %s", _color, _name, _value == null ? "null" : _value);
    }

    private boolean _empty;

    @Override
    public boolean equals(Object other){
        if (other == null)
            return false;

        if (!(other instanceof VariableImpl))
            return false;

        Variable otherVariable = (Variable)other;

        boolean areEquals;

        try{
            areEquals =
                this.getColor().equals(otherVariable.getColor())
            &&  this.getValue().equals(otherVariable.getValue());
        }
        catch (VariableNotInitialized ex){
            areEquals = false;
        }

        return areEquals;




    }
}
