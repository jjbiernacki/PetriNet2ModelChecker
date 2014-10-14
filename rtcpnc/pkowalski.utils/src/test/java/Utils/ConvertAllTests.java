package Utils;


import org.junit.Test;
import pkowalski.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ConvertAllTests extends UtilsTest{
    private List<String> expectedList;


    @Override
    @Test(expected = java.lang.IllegalArgumentException.class)
    public void ListNullTest() throws Exception {
        Utils.ConvertAll(null,converter);
        
                
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConverterNullTest() throws Exception {
        Utils.ConvertAll(list, null);
                
    }

    @Override
    @Test
    public void EmptyListTest() throws Exception {
        list.clear();
        List<String> actual = Utils.ConvertAll(list, converter);

        assertNotNull(actual);
        assertEquals(list.size(),actual.size());
        
    }

    @Override
    @Test
    public void Test() throws Exception {
        List<String> actual = Utils.ConvertAll(list, converter);

        assertNotNull(actual);
        assertArrayEquals(expectedList.toArray(), actual.toArray());
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        expectedList = new ArrayList<String>(
                Arrays.asList("1","2","3","4","5","6","6","6")
        );
    }

}
