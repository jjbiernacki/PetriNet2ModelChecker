package CzuwakAktywny;

import pkowalski.rtcp.runtime.model.Binding;

public class WlaczHamBinding extends Binding{


    public WlaczHamBinding(){
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