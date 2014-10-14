package pkowalski.rtcp.model.impl;

import pkowalski.rtcp.model.BoolColor;
import pkowalski.rtcp.model.BoolVariable;
import pkowalski.rtcp.model.OutOfRangeException;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-08
 * Time:    12:51:31
 *
 */
public class BoolColorImpl extends ScalarColorImpl implements BoolColor{
    private String _falseIdent;
    @Override
    public String getFalseIdent() {
        return _falseIdent;
    }


    public void setFalseIdent(String value) {
        _falseIdent = value;
    }

    private String _trueIdent;
    @Override
    public String getTrueIdent() {
        return _trueIdent;
    }

    
    public void setTrueIdent(String value) {
        _trueIdent = value;
    }

    @Override
    public BoolVariable Parse(String s) throws OutOfRangeException {
        BoolVariableImpl boolVariable = new BoolVariableImpl();
        boolVariable.setColor(this);
        if (s.equals(getTrueIdent())){
            boolVariable.setValue(true);
        }
        else if(s.equals(getFalseIdent())){
            boolVariable.setValue(false);
        }
        else
            throw new OutOfRangeException();

        return boolVariable;
    }

    @Override
    public BoolVariable CreateEmpty() {
        BoolVariableImpl boolVariable = new BoolVariableImpl();
        boolVariable.setColor(this);
        
        return boolVariable;

    }

    @SuppressWarnings({"CloneDoesntCallSuperClone"})
    @Override
    public Object clone(){
        BoolColorImpl boolColor = new BoolColorImpl();
        boolColor._falseIdent = _falseIdent;
        boolColor._trueIdent = _trueIdent;
        boolColor.setName(this.getName());


        return boolColor;
    }

    @Override
    public String toString(){
        return String.format("[%s = bool with(%s, %s)]",getName() ,_falseIdent, _trueIdent);
    }
}
