package $package_name$;

import pkowalski.rtcp.runtime.model.Arc;
$if(inarc)$import pkowalski.rtcp.runtime.model.InArc;$endif$
$if(outarc)$import pkowalski.rtcp.runtime.model.OutArc;$endif$


public class $place_name$To$transition_name$Arc extends Arc implements $if(inarc)$InArc$if(outarc)$, $endif$$endif$$if(outarc)$OutArc$endif${
    public $place_name$To$transition_name$Arc($place_name$Place place, $transition_name$Transition transition) {
        super(place, transition);
    }

    @Override
    public $transition_name$Transition getTransition(){
        return ($transition_name$Transition)super.getTransition();
    }

    @Override
    public $place_name$Place getPlace(){
        return ($place_name$Place)super.getPlace();
    }

    $if(inarc)$$ArcExpressionPart(arc_direction="In",place_color_name=place_color_name, token_variables=in_token_variables, time_variables=in_time_variables, token_expression=in_token_expression, time_expression=in_time_expression, product=product, raw_expression=raw_in_expression)$$endif$

    $if(outarc)$$ArcExpressionPart(arc_direction="Out",place_color_name=place_color_name, token_variables=out_token_variables, time_variables=out_time_variables, token_expression=out_token_expression, time_expression=out_time_expression, product=product, raw_expression=raw_out_expression)$$endif$


}
