package simulator;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Binding;
import pkowalski.rtcp.runtime.model.Transition;


import java.util.ArrayList;
import java.util.List;

public class EmergencyCallTransition extends Transition{
    private static List<Binding> _bindings;

    public EmergencyCallTransition(RtcpNetEventSender eventSender) {
        super(eventSender);
    }

    @Override
    public List<Binding> GetBindings(){
        if (_bindings == null)
            _bindings = GenerateBindings();

        return _bindings;
    }

    private EmergencyCallBinding _binding;

    @Override
    public EmergencyCallBinding getBinding() {
        return _binding;
    }

    public void setBinding(EmergencyCallBinding value) {
        _binding = value;
    }

    @Override
    public void SetBinding(Binding binding) {
        setBinding((EmergencyCallBinding)binding);
    }

    private static List<Binding> GenerateBindings() {

        List<Binding> __bindings = new ArrayList<Binding>();

        __bindings.add(new EmergencyCallBinding());


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
        return "EmergencyCall";
    }

}