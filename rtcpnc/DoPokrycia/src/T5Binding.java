package DoPokrycia;

import pkowalski.rtcp.runtime.model.Binding;

public class T5Binding extends Binding{


    public T5Binding(){
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