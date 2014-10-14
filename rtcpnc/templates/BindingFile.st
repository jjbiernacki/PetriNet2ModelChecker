package $package_name$;

import pkowalski.rtcp.runtime.model.Binding;

public class $transition_name$Binding extends Binding{
    $variables:{v|protected $v.colorname$Token _$v.name$;
public $v.colorname$Token get$v.ccname$() {
    return _$v.name$;
}

public void set$v.ccname$($v.colorname$Token value){
    _$v.name$ = value;
}

};separator="\n"$


    public $transition_name$Binding($variables:{v|$v.colorname$Token $v.name$};separator=", "$){
        // Add your code here:
        super();
        $variables:{v|_$v.name$ = $v.name$;};separator="\n"$

    }


    @Override
    public boolean isTrivial() {
        return $trivial$;
    }

    @Override
    public String toString(){
        $if(trivial)$return "()";$else$String format = "($variables:{v|%s/$v.name$};separator=", "$)";
        return String.format(format, $variables:{v|_$v.name$};separator=", "$);$endif$
    }


}
