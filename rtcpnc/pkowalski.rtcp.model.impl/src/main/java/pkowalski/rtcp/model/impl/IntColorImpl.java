package pkowalski.rtcp.model.impl;

import pkowalski.rtcp.model.IntColor;
import pkowalski.rtcp.model.IntVariable;
import pkowalski.rtcp.model.OutOfRangeException;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-08
 * Time:    12:43:38
 *
 */
public class IntColorImpl extends ScalarColorImpl implements IntColor{
    private int _lowerBound;
    @Override
    public int getLowerBound() {
        return _lowerBound;
    }


    public void setLowerBound(int value) {
        _lowerBound = value;
    }

    private int _upperBound;
    @Override
    public int getUpperBound() {
        return _upperBound;
    }

    
    public void setUpperBound(int value) {
        _upperBound = value;
    }

    @Override
    public IntVariable Parse(String s)throws OutOfRangeException, NumberFormatException {
        IntVariableImpl intVar = new IntVariableImpl();
        intVar.setColor(this);
        int val = Integer.parseInt(s);
        if (val < _lowerBound || val > _upperBound)
            throw new OutOfRangeException();
        intVar.setValue(val);

        return intVar;
    }

    @Override
    public IntVariable CreateEmpty() {
        IntVariable intVariable = new IntVariableImpl();
        intVariable.setColor(this);

        return intVariable;
    }

    @SuppressWarnings({"CloneDoesntCallSuperClone"})
    @Override
    public Object clone(){
        IntColorImpl intColor = new IntColorImpl();
        intColor._lowerBound = _lowerBound;
        intColor._upperBound = _upperBound;
        intColor.setName(this.getName());

        return intColor;
    }

    @Override
    public String toString(){
        return String.format("[%s = int with %s .. %s]", getName(), _lowerBound, _upperBound);
    }
}
