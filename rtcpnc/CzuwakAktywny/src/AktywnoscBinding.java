package CzuwakAktywny;

import pkowalski.rtcp.runtime.model.Binding;

public class AktywnoscBinding extends Binding{
    protected OpoznienieToken _n;
    public OpoznienieToken getN() {
        return _n;
    }

    public void setN(OpoznienieToken value){
        _n = value;
    }




    public AktywnoscBinding(OpoznienieToken n){
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