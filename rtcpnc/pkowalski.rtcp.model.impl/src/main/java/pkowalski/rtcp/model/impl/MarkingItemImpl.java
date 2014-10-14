package pkowalski.rtcp.model.impl;

import pkowalski.rtcp.model.MarkingItem;
import pkowalski.rtcp.model.Variable;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-10
 * Time:    13:33:50
 *
 */
public class MarkingItemImpl implements MarkingItem{
    private int _multiplicity;
    @Override
    public int getMultiplicity() {
        return _multiplicity;
    }

   
    public void setMultiplicity(int value) {
        _multiplicity = value;
    }

    private Variable _variable;

    @Override
    public Variable getVariable() {
        return _variable;
    }


    public void setVariable(Variable value) {
        _variable = value;
    }

    @Override
    public boolean equals(Object other){
        if (other == null)
            return false;

        if (!(other instanceof MarkingItemImpl))
            return false;

        MarkingItem otherMarkingItem = (MarkingItem)other;

        return _variable.equals(otherMarkingItem.getVariable())
                && _multiplicity == otherMarkingItem.getMultiplicity();


    }
}
