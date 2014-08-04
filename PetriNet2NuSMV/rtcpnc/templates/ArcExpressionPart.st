@Override
public $place_color_name$Token get$arc_direction$Token(){
	$place_color_name$Token _token;

	$token_variables:{v|$v.colorname$Token $v.name$ = getTransition().getBinding().get$v.ccname$();};separator="\n"$

	_token = $if(product)$$token_expression$$else$new $place_color_name$Token($token_expression$)$endif$;

	return _token;
}

@Override
public int get$arc_direction$Time(){
	int time;
	
	$time_variables:{v|$v.colorname$Token $v.name$ = getTransition().getBinding().get$v.ccname$();};separator="\n"$
	
	time = $time_expression$;
	
	return time;
}

@Override
public String getRaw$arc_direction$Expression() {
    return "$raw_expression$";
}