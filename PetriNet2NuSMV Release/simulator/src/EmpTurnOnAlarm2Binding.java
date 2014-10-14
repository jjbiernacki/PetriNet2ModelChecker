package simulator;

import pkowalski.rtcp.runtime.model.Binding;

public class EmpTurnOnAlarm2Binding extends Binding{


    public EmpTurnOnAlarm2Binding(){
        // Add your code here:
        super();

    }


    @Override
    public boolean isTrivial() {
        return true;
    }

    @Override
    public String toString(){
return "()";
    }


}