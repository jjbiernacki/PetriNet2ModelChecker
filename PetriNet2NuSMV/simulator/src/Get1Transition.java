package simulator;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Binding;
import pkowalski.rtcp.runtime.model.Transition;


import java.util.ArrayList;
import java.util.List;

public class Get1Transition extends Transition{
    private static List<Binding> _bindings;

    public Get1Transition(RtcpNetEventSender eventSender) {
        super(eventSender);
    }

    @Override
    public List<Binding> GetBindings(){
        if (_bindings == null)
            _bindings = GenerateBindings();

        return _bindings;
    }

    private Get1Binding _binding;

    @Override
    public Get1Binding getBinding() {
        return _binding;
    }

    public void setBinding(Get1Binding value) {
        _binding = value;
    }

    @Override
    public void SetBinding(Binding binding) {
        setBinding((Get1Binding)binding);
    }

    private static List<Binding> GenerateBindings() {

        List<Binding> __bindings = new ArrayList<Binding>();

        __bindings.add(new Get1Binding());


        return __bindings;
    }

    public static boolean Guard(){
        return true;

    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public String getName() {
        return "Get1";
    }

}