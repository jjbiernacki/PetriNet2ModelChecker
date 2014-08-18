package simulator;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Binding;
import pkowalski.rtcp.runtime.model.Transition;


import java.util.ArrayList;
import java.util.List;

public class Comp1Transition extends Transition{
    private static List<Binding> _bindings;

    public Comp1Transition(RtcpNetEventSender eventSender) {
        super(eventSender);
    }

    @Override
    public List<Binding> GetBindings(){
        if (_bindings == null)
            _bindings = GenerateBindings();

        return _bindings;
    }

    private Comp1Binding _binding;

    @Override
    public Comp1Binding getBinding() {
        return _binding;
    }

    public void setBinding(Comp1Binding value) {
        _binding = value;
    }

    @Override
    public void SetBinding(Binding binding) {
        setBinding((Comp1Binding)binding);
    }

    private static List<Binding> GenerateBindings() {

        List<Binding> __bindings = new ArrayList<Binding>();

        for(NumberToken n : NumberToken.GetAllTokens())
            if(Guard(n))
                __bindings.add(new Comp1Binding(n));


        return __bindings;
    }

    public static boolean Guard(NumberToken n){
        return n.getValue() > 0;

    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String getName() {
        return "Comp1";
    }

}