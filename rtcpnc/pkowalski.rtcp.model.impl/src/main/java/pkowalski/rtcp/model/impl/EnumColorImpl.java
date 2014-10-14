package pkowalski.rtcp.model.impl;

import pkowalski.rtcp.model.EnumColor;
import pkowalski.rtcp.model.EnumVariable;
import pkowalski.rtcp.model.OutOfRangeException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-08
 * Time:    12:55:34
 *
 */
public class EnumColorImpl extends ScalarColorImpl implements EnumColor{

    public EnumColorImpl(){
        // Add your code here:
        super();
        Init();
    }


    private void Init(){
        _enumIdents = new ArrayList<String>();
    }



    private List<String> _enumIdents;
    @Override
    public List<String> getEnumIdents() {
        return _enumIdents;
    }

    @Override
    public EnumVariable Parse(String s) throws OutOfRangeException {
        EnumVariableImpl enumVariable = new EnumVariableImpl();
        enumVariable.setColor(this);

        for (String enumIdent : _enumIdents) {
            if (enumIdent.equals(s)){
                enumVariable.setValue(s);
                return enumVariable;
            }
        }

        throw new OutOfRangeException();
    }

    @Override
    public EnumVariable CreateEmpty() {
        EnumVariableImpl enumVariable = new EnumVariableImpl();
        enumVariable.setColor(this);

        return enumVariable;
    }

    @SuppressWarnings({"CloneDoesntCallSuperClone"})
    @Override
    public Object clone(){
        EnumColorImpl enumColor = new EnumColorImpl();
        enumColor._enumIdents = new ArrayList<String>();
        enumColor._enumIdents.addAll(_enumIdents);
        enumColor.setName(this.getName());

        return enumColor;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (String enumIdent : _enumIdents) {
            if (stringBuilder.length() > 0)
                stringBuilder.append(" | ");
            stringBuilder.append(enumIdent);
        }

        return String.format("[%s = with %s]", getName(), stringBuilder.toString());
    }
}
