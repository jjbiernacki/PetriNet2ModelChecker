package Utils;

import org.junit.Before;
import org.junit.Test;
import pkowalski.utils.Func;
import pkowalski.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ConvertIfTests extends UtilsTest{

    private Func<Boolean, Integer> predicate;
    private List<String> expectedList;

    @Test(expected = IllegalArgumentException.class)
    public void ListNullTest() throws Exception {
        Utils.ConvertIf(null, converter, predicate);
        

    }

    @Test(expected = IllegalArgumentException.class)
    public void ConverterNullTest() throws Exception {
        Utils.ConvertIf(list, null,predicate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void PredicateNullTest() throws Exception {
        Utils.ConvertIf(list, converter, null);
    }


    @Test
    public void EmptyListTest() throws Exception {
        list.clear();
        List<String> actual = Utils.ConvertIf(list, converter, predicate);

        assertNotNull(actual);
        assertEquals(actual.size(),0);
    }

    
    @Test
    public void Test() throws Exception {
        List<String> actual = Utils.ConvertIf(list, converter, predicate);
        assertNotNull(actual);
        assertArrayEquals(actual.toArray(),expectedList.toArray());
    }



    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        list.addAll(
                Arrays.asList(-5,-4,-3,-2,-1)
        );

        expectedList = new ArrayList<String>(
                Arrays.asList("1","2","3","4","5","6","6","6")
        );
        
        predicate = new Func<Boolean, Integer>() {
            @Override
            public Boolean Invoke(Integer integer) {
                return integer > 0;
            }
        };
    }

}
