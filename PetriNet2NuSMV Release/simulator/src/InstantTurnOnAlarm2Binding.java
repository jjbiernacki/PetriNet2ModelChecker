package simulator;

import pkowalski.rtcp.runtime.model.Binding;

public class InstantTurnOnAlarm2Binding extends Binding{
    protected SystemStateToken _sysSt;
    public SystemStateToken getSysSt() {
        return _sysSt;
    }
    
    public void setSysSt(SystemStateToken value){
        _sysSt = value;
    }
    



    public InstantTurnOnAlarm2Binding(SystemStateToken sysSt){
        // Add your code here:
        super();
        _sysSt = sysSt;

    }


    @Override
    public boolean isTrivial() {
        return false;
    }

    @Override
    public String toString(){
String format = "(%s/sysSt)";
        return String.format(format, _sysSt);
    }


}