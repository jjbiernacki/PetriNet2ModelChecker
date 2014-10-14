package simulator;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Binding;
import pkowalski.rtcp.runtime.model.Transition;


import java.util.ArrayList;
import java.util.List;

public class GetSensorsValueTransition extends Transition{
    private static List<Binding> _bindings;

    public GetSensorsValueTransition(RtcpNetEventSender eventSender) {
        super(eventSender);
    }

    @Override
    public List<Binding> GetBindings(){
        if (_bindings == null)
            _bindings = GenerateBindings();

        return _bindings;
    }

    private GetSensorsValueBinding _binding;

    @Override
    public GetSensorsValueBinding getBinding() {
        return _binding;
    }

    public void setBinding(GetSensorsValueBinding value) {
        _binding = value;
    }

    @Override
    public void SetBinding(Binding binding) {
        setBinding((GetSensorsValueBinding)binding);
    }

    private static List<Binding> GenerateBindings() {

        List<Binding> __bindings = new ArrayList<Binding>();

        for(SensorsStateToken senSt : SensorsStateToken.GetAllTokens())
        for(SensorsStateToken senSt2 : SensorsStateToken.GetAllTokens())
            if(Guard(senSt, senSt2))
                __bindings.add(new GetSensorsValueBinding(senSt, senSt2));


        return __bindings;
    }

    public static boolean Guard(SensorsStateToken senSt, SensorsStateToken senSt2){
        return senSt.getValue() != SensorsStateEnum.normal && senSt2.getValue() != SensorsStateEnum.dblWarning;

    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String getName() {
        return "GetSensorsValue";
    }

}