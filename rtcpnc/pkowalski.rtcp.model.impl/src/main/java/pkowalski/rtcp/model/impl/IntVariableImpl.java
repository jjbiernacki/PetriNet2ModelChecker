package pkowalski.rtcp.model.impl;

import pkowalski.rtcp.model.IntVariable;
import pkowalski.rtcp.model.VariableNotInitialized;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-09
 * Time:    22:17:09
 *
 */
public class IntVariableImpl extends ScalarVariableImpl implements IntVariable{

    public Integer getValue() throws VariableNotInitialized{
        return (Integer)super.getValue();
    }
    
    public void setValue(Integer value) {
        super.setValue(value);
    }
}
