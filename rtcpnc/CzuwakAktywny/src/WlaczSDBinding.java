package CzuwakAktywny;

import pkowalski.rtcp.runtime.model.Binding;

public class WlaczSDBinding extends Binding{


    public WlaczSDBinding(){
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