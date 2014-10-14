package simulator;

import pkowalski.rtcp.runtime.model.Binding;

public class TurnOnAlarm2_2Binding extends Binding{


    public TurnOnAlarm2_2Binding(){
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