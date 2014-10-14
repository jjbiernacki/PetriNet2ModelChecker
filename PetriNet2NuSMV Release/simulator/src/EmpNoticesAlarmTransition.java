package simulator;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Binding;
import pkowalski.rtcp.runtime.model.Transition;


import java.util.ArrayList;
import java.util.List;

public class EmpNoticesAlarmTransition extends Transition{
    private static List<Binding> _bindings;

    public EmpNoticesAlarmTransition(RtcpNetEventSender eventSender) {
        super(eventSender);
    }

    @Override
    public List<Binding> GetBindings(){
        if (_bindings == null)
            _bindings = GenerateBindings();

        return _bindings;
    }

    private EmpNoticesAlarmBinding _binding;

    @Override
    public EmpNoticesAlarmBinding getBinding() {
        return _binding;
    }

    public void setBinding(EmpNoticesAlarmBinding value) {
        _binding = value;
    }

    @Override
    public void SetBinding(Binding binding) {
        setBinding((EmpNoticesAlarmBinding)binding);
    }

    private static List<Binding> GenerateBindings() {

        List<Binding> __bindings = new ArrayList<Binding>();

        for(DelayToken t : DelayToken.GetAllTokens())
            if(Guard(t))
                __bindings.add(new EmpNoticesAlarmBinding(t));


        return __bindings;
    }

    public static boolean Guard(DelayToken t){
        return t.getValue() == 50 || t.getValue() == 70;

    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String getName() {
        return "EmpNoticesAlarm";
    }

}