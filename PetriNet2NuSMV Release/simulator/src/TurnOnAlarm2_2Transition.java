package simulator;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Binding;
import pkowalski.rtcp.runtime.model.Transition;


import java.util.ArrayList;
import java.util.List;

public class TurnOnAlarm2_2Transition extends Transition{
    private static List<Binding> _bindings;

    public TurnOnAlarm2_2Transition(RtcpNetEventSender eventSender) {
        super(eventSender);
    }

    @Override
    public List<Binding> GetBindings(){
        if (_bindings == null)
            _bindings = GenerateBindings();

        return _bindings;
    }

    private TurnOnAlarm2_2Binding _binding;

    @Override
    public TurnOnAlarm2_2Binding getBinding() {
        return _binding;
    }

    public void setBinding(TurnOnAlarm2_2Binding value) {
        _binding = value;
    }

    @Override
    public void SetBinding(Binding binding) {
        setBinding((TurnOnAlarm2_2Binding)binding);
    }

    private static List<Binding> GenerateBindings() {

        List<Binding> __bindings = new ArrayList<Binding>();

        __bindings.add(new TurnOnAlarm2_2Binding());


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
        return "TurnOnAlarm2_2";
    }

}