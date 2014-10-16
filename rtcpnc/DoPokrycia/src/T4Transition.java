package DoPokrycia;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Binding;
import pkowalski.rtcp.runtime.model.Transition;


import java.util.ArrayList;
import java.util.List;

public class T4Transition extends Transition{
    private static List<Binding> _bindings;

    public T4Transition(RtcpNetEventSender eventSender) {
        super(eventSender);
    }

    @Override
    public List<Binding> GetBindings(){
        if (_bindings == null)
            _bindings = GenerateBindings();

        return _bindings;
    }

    private T4Binding _binding;

    @Override
    public T4Binding getBinding() {
        return _binding;
    }

    public void setBinding(T4Binding value) {
        _binding = value;
    }

    @Override
    public void SetBinding(Binding binding) {
        setBinding((T4Binding)binding);
    }

    private static List<Binding> GenerateBindings() {

        List<Binding> __bindings = new ArrayList<Binding>();

        __bindings.add(new T4Binding());


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
        return "T4";
    }

}