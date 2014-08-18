package simulator;

import pkowalski.rtcp.runtime.model.Binding;

public class Get2Binding extends Binding{


    public Get2Binding(){
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