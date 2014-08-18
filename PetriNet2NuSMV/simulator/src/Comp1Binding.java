package simulator;

import pkowalski.rtcp.runtime.model.Binding;

public class Comp1Binding extends Binding{
    protected NumberToken _n;
    public NumberToken getN() {
        return _n;
    }
    
    public void setN(NumberToken value){
        _n = value;
    }
    



    public Comp1Binding(NumberToken n){
        // Add your code here:
        super();
        _n = n;

    }


    @Override
    public boolean isTrivial() {
        return false;
    }

    @Override
    public String toString(){
String format = "(%s/n)";
        return String.format(format, _n);
    }


}