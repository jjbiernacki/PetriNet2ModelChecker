package simulator;

import pkowalski.rtcp.runtime.model.Binding;

public class ConfirmAlarmBinding extends Binding{
    protected DelayToken _t;
    public DelayToken getT() {
        return _t;
    }
    
    public void setT(DelayToken value){
        _t = value;
    }
    



    public ConfirmAlarmBinding(DelayToken t){
        // Add your code here:
        super();
        _t = t;

    }


    @Override
    public boolean isTrivial() {
        return false;
    }

    @Override
    public String toString(){
String format = "(%s/t)";
        return String.format(format, _t);
    }


}