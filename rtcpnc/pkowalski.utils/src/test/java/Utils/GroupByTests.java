package Utils;


import org.junit.Test;
import pkowalski.utils.Utils;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class GroupByTests extends UtilsTest{

    @Test(expected = IllegalArgumentException.class)
    public void KeySelectorNullTest(){
        Utils.GroupBy(list, null);
    }

    @Override
    public void ListNullTest() throws Exception {
        Utils.GroupBy(null, keySelector);
        
    }

    @Override
    public void EmptyListTest() throws Exception {
        list.clear();

        Map<Integer,Set<Integer>> actual = Utils.GroupBy(list, keySelector);

        assertNotNull(actual);
        assertEquals(0,actual.size());
    }

    @Override
    public void Test() throws Exception {
        final Map<Integer, Set<Integer>> actual = Utils.GroupBy(list, keySelector);

        assertNotNull(actual);

        assertTrue(actual.keySet().containsAll(
                Arrays.asList(1,2,3,4,5,6)
        ));

        for (Map.Entry<Integer, Set<Integer>> entry : actual.entrySet()) {
            assertNotNull(entry.getValue());
            assertTrue(entry.getValue().size() > 0);
            assertTrue(entry.getValue().contains(entry.getKey()));
        }
        
    }
}
