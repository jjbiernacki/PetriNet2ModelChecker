package Utils;

import org.junit.Before;
import org.junit.Test;
import pkowalski.junit.matchers.GreaterThan;
import pkowalski.junit.matchers.IteratorMatcher;
import pkowalski.utils.Func;
import pkowalski.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class WhereTests {
    
    private List<Integer> list;
    private Func<Boolean, Integer> predicate;

    @Test(expected = IllegalArgumentException.class)
    public void ListNullTest() throws Exception {
        Utils.Where(null, predicate);

    }

    @Test(expected = IllegalArgumentException.class)
    public void PredicateNullTest() throws Exception {
        Utils.Where(list, null);

    }

    @Test
    public void ListEmptyTest() throws Exception {
        list.clear();

        List<Integer> actual = Utils.Where(list, predicate);

        assertNotNull(actual);
        assertEquals(0,actual.size());

    }

    @Test
    public void Test() throws Exception {

        List<Integer> actual = Utils.Where(list, predicate);

        assertNotNull(actual);
        assertThat(actual,new IteratorMatcher<Integer, List<Integer>>(new GreaterThan(0)));

    }
    
    @Before
    public void setUp(){
        list = new ArrayList<Integer>(Arrays.asList(-5,-4,-3,-2,-1,0,1,2,3,4,5));

        predicate = new Func<Boolean, Integer>() {
            @Override
            public Boolean Invoke(Integer integer) throws Exception {
                return integer > 0;
            }
        };
    }

}
