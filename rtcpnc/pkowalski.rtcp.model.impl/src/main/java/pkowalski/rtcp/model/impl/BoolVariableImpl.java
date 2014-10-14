package pkowalski.rtcp.model.impl;

import pkowalski.rtcp.model.BoolColor;
import pkowalski.rtcp.model.BoolVariable;
import pkowalski.rtcp.model.VariableNotInitialized;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-09
 * Time:    22:58:51
 *
 */
public class BoolVariableImpl extends ScalarVariableImpl implements BoolVariable{


    public Boolean getValue() throws VariableNotInitialized {
        return (Boolean) super.getValue();
    }

    public void setValue(Boolean value) {
        super.setValue(value);
    }
}
