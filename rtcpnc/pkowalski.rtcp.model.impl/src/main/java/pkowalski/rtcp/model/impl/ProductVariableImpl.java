package pkowalski.rtcp.model.impl;

import pkowalski.rtcp.model.ProductVariable;
import pkowalski.rtcp.model.ScalarVariable;
import pkowalski.rtcp.model.VariableNotInitialized;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-09
 * Time:    23:10:10
 *
 */
public class ProductVariableImpl extends VariableImpl implements ProductVariable{
    @Override
    public List<ScalarVariable> getValue()throws VariableNotInitialized{
        return (List<ScalarVariable>)super.getValue();

    }

    
    public void setValue(List<ScalarVariable> value){
        super.setValue(value);
    }
}
