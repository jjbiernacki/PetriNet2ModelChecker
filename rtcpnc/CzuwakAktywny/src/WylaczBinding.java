package CzuwakAktywny;

import pkowalski.rtcp.runtime.model.Binding;

public class WylaczBinding extends Binding{
    protected StanToken _x;
    public StanToken getX() {
        return _x;
    }

    public void setX(StanToken value){
        _x = value;
    }


    protected StanSystemuToken _y;
    public StanSystemuToken getY() {
        return _y;
    }

    public void setY(StanSystemuToken value){
        _y = value;
    }




    public WylaczBinding(StanToken x, StanSystemuToken y){
        // Add your code here:
        super();
        _x = x;
        _y = y;

    }


    @Override
    public boolean isTrivial() {
        return false;
    }

    @Override
    public String toString(){
String format = "(%s/x, %s/y)";
        return String.format(format, _x, _y);
    }


}