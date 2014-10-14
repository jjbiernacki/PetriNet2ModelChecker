package simulator;

import pkowalski.rtcp.runtime.model.Binding;

public class GetSensorsValueBinding extends Binding{
    protected SensorsStateToken _senSt;
    public SensorsStateToken getSenSt() {
        return _senSt;
    }
    
    public void setSenSt(SensorsStateToken value){
        _senSt = value;
    }
    

    protected SensorsStateToken _senSt2;
    public SensorsStateToken getSenSt2() {
        return _senSt2;
    }
    
    public void setSenSt2(SensorsStateToken value){
        _senSt2 = value;
    }
    



    public GetSensorsValueBinding(SensorsStateToken senSt, SensorsStateToken senSt2){
        // Add your code here:
        super();
        _senSt = senSt;
        _senSt2 = senSt2;

    }


    @Override
    public boolean isTrivial() {
        return false;
    }

    @Override
    public String toString(){
String format = "(%s/senSt, %s/senSt2)";
        return String.format(format, _senSt, _senSt2);
    }


}