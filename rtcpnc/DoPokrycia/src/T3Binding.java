package DoPokrycia;

import pkowalski.rtcp.runtime.model.Binding;

public class T3Binding extends Binding{


    public T3Binding(){
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