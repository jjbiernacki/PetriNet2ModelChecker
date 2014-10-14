package CzuwakAktywny;

import pkowalski.rtcp.runtime.model.Binding;

public class WlaczSSBinding extends Binding{


    public WlaczSSBinding(){
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