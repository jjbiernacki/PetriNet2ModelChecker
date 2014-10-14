package Utils;

import org.junit.Test;
import pkowalski.utils.ReadOnlyPropertySelector;
import pkowalski.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GetDistinctTests extends UtilsTest{

    private List<Integer> expectedList;

    @Test(expected = IllegalArgumentException.class)
    public void KeyNullTest(){
        Utils.GetDistinct(list, null);

    }

    @Override
    public void ListNullTest() throws Exception {
        Utils.GetDistinct(null, new ReadOnlyPropertySelector<Object, Object>() {
            @Override
            public Object getPropertyValue(Object target) {
                return null; 
            }
        });
    }

    @Override
    public void EmptyListTest() throws Exception {
        list.clear();

        List<Integer> actual = Utils.GetDistinct(list, keySelector);

        assertNotNull(actual);
        assertEquals(actual.size(),0);
    }

    @Override
    public void Test() throws Exception {
        List<Integer> actual = Utils.GetDistinct(list, keySelector);

        assertNotNull(actual);
        assertArrayEquals(expectedList.toArray(),actual.toArray());
    }

    @Override

    public void setUp() throws Exception {
        super.setUp();

        expectedList = new ArrayList<Integer>(
                Arrays.asList(1,2,3,4,5,6)
        );

       
    }
}
