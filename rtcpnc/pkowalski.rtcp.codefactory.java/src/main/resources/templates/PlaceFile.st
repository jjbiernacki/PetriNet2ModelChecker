package $package_name$;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;
import pkowalski.rtcp.runtime.model.Place;

public class $place_name$Place extends Place{
    public $place_name$Place(RtcpNetEventSender eventSender) {
        super(eventSender);
    }


    @Override
    protected void InitializeTokensAndTimestamp() {
        $tokens:{t|for(int i=0; i<$t.multiplicity$; i++){
    getTokens().add(new $t.colorname$Token($if(t.product)$
    $ProductTokenConstruction(values=t.value)$
$else$
    $t.value$
$endif$
));
}};separator="\n"$


        setTimeStamp($timestamp$);
    }


    @Override
    public String getName() {
        return "$place_name$";
    }
}
