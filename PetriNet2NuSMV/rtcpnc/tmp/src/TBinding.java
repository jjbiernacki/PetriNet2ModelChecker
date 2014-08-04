package tmp;

import pkowalski.rtcp.runtime.model.Binding;

public class TBinding extends Binding{


    public TBinding(){
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