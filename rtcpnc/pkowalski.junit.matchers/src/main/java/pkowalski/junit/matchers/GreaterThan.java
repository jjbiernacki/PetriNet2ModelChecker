package pkowalski.junit.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;


@SuppressWarnings({"SameParameterValue"})
public class GreaterThan extends BaseMatcher<Integer>{
    private final Integer _value;


    public GreaterThan(Integer value){
        // Add your code here:
        super();
        _value = value;

    }

    @Override
    public boolean matches(Object o) {
        return ((Integer)o)>_value;
    }

    @Override
    public void describeTo(Description description) {
        
    }
}
