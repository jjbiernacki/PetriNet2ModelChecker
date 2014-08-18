package simulator;

import pkowalski.rtcp.runtime.model.Binding;

public class PutBinding extends Binding{


    public PutBinding(){
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