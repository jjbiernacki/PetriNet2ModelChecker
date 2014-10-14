package Utils;

import org.junit.Before;
import org.junit.Test;
import pkowalski.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GroupByMultiplicityTests {
    private List<Integer> list;


    @Test(expected = IllegalArgumentException.class)
    public void ListNullTest(){
        Utils.GroupByMultiplicity(null);
        
    }

    @Test
    public void EmptyListTest(){
        list.clear();

        Map<Integer,Integer> actual = Utils.GroupByMultiplicity(list);

        assertNotNull(actual);
        assertEquals(0,actual.size());
    }

    @Test
    public void Test(){
        Map<Integer,Integer> actual = Utils.GroupByMultiplicity(list);

        assertNotNull(actual);

        for (Map.Entry entry : actual.entrySet()) {
            assertEquals(entry.getKey(),entry.getValue());
        }

    }

    @Before
    public void setUp(){
        list = new ArrayList<Integer>(Arrays.asList(1,2,2,3,3,3,4,4,4,4));


    }

}
