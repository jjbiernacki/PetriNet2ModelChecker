package simulator;

import pkowalski.rtcp.runtime.model.Binding;

public class EmergencyCallBinding extends Binding{


    public EmergencyCallBinding(){
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