package CzuwakAktywny;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Binding;
import pkowalski.rtcp.runtime.model.Transition;


import java.util.ArrayList;
import java.util.List;

public class WylaczTransition extends Transition{
    private static List<Binding> _bindings;

    public WylaczTransition(RtcpNetEventSender eventSender) {
        super(eventSender);
    }

    @Override
    public List<Binding> GetBindings(){
        if (_bindings == null)
            _bindings = GenerateBindings();

        return _bindings;
    }

    private WylaczBinding _binding;

    @Override
    public WylaczBinding getBinding() {
        return _binding;
    }

    public void setBinding(WylaczBinding value) {
        _binding = value;
    }

    @Override
    public void SetBinding(Binding binding) {
        setBinding((WylaczBinding)binding);
    }

    private static List<Binding> GenerateBindings() {

        List<Binding> __bindings = new ArrayList<Binding>();

        for(StanToken x : StanToken.GetAllTokens())
        for(StanSystemuToken y : StanSystemuToken.GetAllTokens())
            if(Guard(y))
                __bindings.add(new WylaczBinding(x, y));


        return __bindings;
    }

    public static boolean Guard(StanSystemuToken y){
        return y.getValue() == 1 || y.getValue() == 2;

    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public String getName() {
        return "Wylacz";
    }

}