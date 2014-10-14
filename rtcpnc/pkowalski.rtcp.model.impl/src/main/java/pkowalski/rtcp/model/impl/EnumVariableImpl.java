package pkowalski.rtcp.model.impl;

import pkowalski.rtcp.model.EnumVariable;
import pkowalski.rtcp.model.VariableNotInitialized;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-09
 * Time:    23:08:25
 *
 */
public class EnumVariableImpl extends ScalarVariableImpl implements EnumVariable{
    @Override
    public String getValue()throws VariableNotInitialized{
        return (String) super.getValue();
    }
    
    public void setValue(String value) {
        super.setValue(value);
    }
}
