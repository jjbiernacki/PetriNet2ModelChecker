package simulator;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Binding;
import pkowalski.rtcp.runtime.model.Transition;


import java.util.ArrayList;
import java.util.List;

public class EmpTurnOnAlarm2Transition extends Transition{
    private static List<Binding> _bindings;

    public EmpTurnOnAlarm2Transition(RtcpNetEventSender eventSender) {
        super(eventSender);
    }

    @Override
    public List<Binding> GetBindings(){
        if (_bindings == null)
            _bindings = GenerateBindings();

        return _bindings;
    }

    private EmpTurnOnAlarm2Binding _binding;

    @Override
    public EmpTurnOnAlarm2Binding getBinding() {
        return _binding;
    }

    public void setBinding(EmpTurnOnAlarm2Binding value) {
        _binding = value;
    }

    @Override
    public void SetBinding(Binding binding) {
        setBinding((EmpTurnOnAlarm2Binding)binding);
    }

    private static List<Binding> GenerateBindings() {

        List<Binding> __bindings = new ArrayList<Binding>();

        __bindings.add(new EmpTurnOnAlarm2Binding());


        return __bindings;
    }

    public static boolean Guard(){
        return true;

    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String getName() {
        return "EmpTurnOnAlarm2";
    }

}