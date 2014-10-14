package simulator;

import pkowalski.rtcp.runtime.model.Binding;

public class TurnOnAlarm1Binding extends Binding{


    public TurnOnAlarm1Binding(){
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