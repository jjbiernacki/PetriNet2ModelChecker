package Utils;

import org.junit.Before;
import org.junit.Test;
import pkowalski.utils.Converter;
import pkowalski.utils.ReadOnlyPropertySelector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings({"UnusedDeclaration"})
public abstract class UtilsTest {
    List<Integer> list;
    Converter<Integer, String> converter;
    protected ReadOnlyPropertySelector<Integer, Integer> keySelector;

    @Before
    public void setUp() throws Exception {
        converter = new Converter<Integer, String>() {
            @Override
            public String Convert(Integer integer) {
                return integer.toString();
            }
        };
        list = new ArrayList<Integer>();
        list.addAll(
                Arrays.asList(1,2,3,4,5,6,6,6)
        );

         keySelector = new ReadOnlyPropertySelector<Integer, Integer>() {
            @Override
            public Integer getPropertyValue(Integer target) {
                return target;
            }
        };

        
    }

    @Test(expected = IllegalArgumentException.class)
    public abstract void ListNullTest() throws Exception;

    @Test
    public abstract void EmptyListTest() throws Exception;

    @Test
    public abstract void Test() throws Exception;
}
