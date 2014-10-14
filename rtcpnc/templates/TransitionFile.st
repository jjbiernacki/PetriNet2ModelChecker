package $package_name$;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Binding;
import pkowalski.rtcp.runtime.model.Transition;


import java.util.ArrayList;
import java.util.List;

public class $transition_name$Transition extends Transition{
    private static List<Binding> _bindings;

    public $transition_name$Transition(RtcpNetEventSender eventSender) {
        super(eventSender);
    }

    @Override
    public List<Binding> GetBindings(){
        if (_bindings == null)
            _bindings = GenerateBindings();

        return _bindings;
    }

    private $transition_name$Binding _binding;

    @Override
    public $transition_name$Binding getBinding() {
        return _binding;
    }

    public void setBinding($transition_name$Binding value) {
        _binding = value;
    }

    @Override
    public void SetBinding(Binding binding) {
        setBinding(($transition_name$Binding)binding);
    }

    private static List<Binding> GenerateBindings() {

        List<Binding> __bindings = new ArrayList<Binding>();

        $if(variables)$
        $variables:{v|for($v.colorname$Token $v.name$ : $v.colorname$Token.GetAllTokens())};separator="\n"$
            if(Guard($guard_variables:{gv|$gv.name$};separator=", "$))
                __bindings.add(new $transition_name$Binding($variables:{v|$v.name$};separator=", "$));
        $else$
        __bindings.add(new $transition_name$Binding());
        $endif$


        return __bindings;
    }

    public static boolean Guard($guard_variables:{gv|$gv.colorname$Token $gv.name$};separator=", "$){
        return $if(guard_expression)$$guard_expression$$else$true$endif$;

    }

    @Override
    public int getPriority() {
        return $priority$;
    }

    @Override
    public String getName() {
        return "$transition_name$";
    }

}
