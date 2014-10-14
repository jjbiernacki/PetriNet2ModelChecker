package simulator;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Binding;
import pkowalski.rtcp.runtime.model.Transition;


import java.util.ArrayList;
import java.util.List;

public class TurnOnAlarm1Transition extends Transition{
    private static List<Binding> _bindings;

    public TurnOnAlarm1Transition(RtcpNetEventSender eventSender) {
        super(eventSender);
    }

    @Override
    public List<Binding> GetBindings(){
        if (_bindings == null)
            _bindings = GenerateBindings();

        return _bindings;
    }

    private TurnOnAlarm1Binding _binding;

    @Override
    public TurnOnAlarm1Binding getBinding() {
        return _binding;
    }

    public void setBinding(TurnOnAlarm1Binding value) {
        _binding = value;
    }

    @Override
    public void SetBinding(Binding binding) {
        setBinding((TurnOnAlarm1Binding)binding);
    }

    private static List<Binding> GenerateBindings() {

        List<Binding> __bindings = new ArrayList<Binding>();

        __bindings.add(new TurnOnAlarm1Binding());


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
        return "TurnOnAlarm1";
    }

}