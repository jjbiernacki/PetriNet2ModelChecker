package Utils;

import org.junit.Before;
import org.junit.Test;
import pkowalski.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class JoinStringsTests {
    private List<String> list;
    private String separator;
    private String expected;

    @Test(expected = IllegalArgumentException.class)
    public void ListNullTest(){
        Utils.JoinStrings(separator, null);
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void SeparatorNullTest(){
        Utils.JoinStrings(null,list);
    }

    @Test
    public void ListEmptyTest(){
        list.clear();

        String actual = Utils.JoinStrings(separator, list);

        String expected = "";
        assertNotNull(actual);
        assertEquals(expected,actual);

    }

    @Test
    public void Test(){

        String actual = Utils.JoinStrings(separator, list);

        assertNotNull(actual);

        assertEquals(expected, actual);

    }

    @Before
    public void setUp(){
        list = new ArrayList<String>(Arrays.asList(
                "1","2","3","4"
        ));
        separator = " ";

        expected = "1 2 3 4";
    }
}
