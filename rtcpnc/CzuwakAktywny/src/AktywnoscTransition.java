package CzuwakAktywny;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Binding;
import pkowalski.rtcp.runtime.model.Transition;


import java.util.ArrayList;
import java.util.List;

public class AktywnoscTransition extends Transition{
    private static List<Binding> _bindings;

    public AktywnoscTransition(RtcpNetEventSender eventSender) {
        super(eventSender);
    }

    @Override
    public List<Binding> GetBindings(){
        if (_bindings == null)
            _bindings = GenerateBindings();

        return _bindings;
    }

    private AktywnoscBinding _binding;

    @Override
    public AktywnoscBinding getBinding() {
        return _binding;
    }

    public void setBinding(AktywnoscBinding value) {
        _binding = value;
    }

    @Override
    public void SetBinding(Binding binding) {
        setBinding((AktywnoscBinding)binding);
    }

    private static List<Binding> GenerateBindings() {

        List<Binding> __bindings = new ArrayList<Binding>();

        for(OpoznienieToken n : OpoznienieToken.GetAllTokens())
            if(Guard(n))
                __bindings.add(new AktywnoscBinding(n));


        return __bindings;
    }

    public static boolean Guard(OpoznienieToken n){
        return n.getValue() == 5 || n.getValue() == 8 || n.getValue() == 10;

    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String getName() {
        return "Aktywnosc";
    }

}