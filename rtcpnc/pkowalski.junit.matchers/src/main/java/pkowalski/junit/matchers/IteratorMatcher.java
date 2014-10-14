package pkowalski.junit.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;


public class IteratorMatcher<I,T extends Iterable<I>> extends BaseMatcher<T>{

    private final Matcher<I> _matcher;


    public IteratorMatcher(Matcher<I> matcher){
        // Add your code here:
        super();
        _matcher = matcher;

    }


    @Override
    public boolean matches(Object o) {
        T iterable = (T)o;
        for (I i : iterable) {
            if (!_matcher.matches(i))
                return false;
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        
    }
}
