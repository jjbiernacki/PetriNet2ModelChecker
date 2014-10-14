package tests.EnumColorImplTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pkowalski.rtcp.model.EnumVariable;
import pkowalski.rtcp.model.OutOfRangeException;
import pkowalski.rtcp.model.VariableNotInitialized;
import pkowalski.rtcp.model.impl.EnumColorImpl;

import java.util.Arrays;
import java.util.List;


public class EnumColorImpl_ParseTests {
    EnumColorImpl target;
    List<String> correctValues;


    @Test(expected = IllegalArgumentException.class)
    public void SNullTest() throws OutOfRangeException {
        target.Parse(null);
    }

    @Test(expected = OutOfRangeException.class)
    public void SOutOfRangeTest() throws OutOfRangeException {
        target.Parse("XXX");

    }

    @Test
    public void Test() throws OutOfRangeException, VariableNotInitialized {

        for (String correctValue : correctValues) {
            EnumVariable actual = target.Parse(correctValue);

            Assert.assertNotNull(actual);
            Assert.assertEquals("Assert error for value: " + correctValue,actual.getValue(),correctValue);
        }

    }

    @Before
    public void setUp(){
        correctValues = Arrays.asList("ENUM1", "ENUM2", "ENUM3","ENUM4");

        target = new EnumColorImpl();

        target.getEnumIdents().addAll(correctValues);


        
    }
}
