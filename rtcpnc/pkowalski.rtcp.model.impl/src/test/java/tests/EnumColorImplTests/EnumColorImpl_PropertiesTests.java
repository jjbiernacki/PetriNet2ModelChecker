package tests.EnumColorImplTests;

import org.junit.Assert;
import org.junit.Test;
import pkowalski.junit.utils.Property;
import pkowalski.junit.utils.TestCase;
import pkowalski.rtcp.model.impl.EnumColorImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;


public class EnumColorImpl_PropertiesTests extends TestCase<EnumColorImpl>{



    @Test()
    public void NameTest() throws Exception {
        PropertyTest("ENUMCOLOR", "Name", new Property<EnumColorImpl, String>() {
            @Override
            public String getValue(EnumColorImpl target) {
                return target.getName();
            }

            @Override
            public void setValue(EnumColorImpl target, String value) {
                target.setName(value);
            }
        });

    }

    @Test
    public void EnumIdentsTest(){
        List<String> actual = target.getEnumIdents();

        assertNotNull(actual);
    }

    @Test
    public void ToStringTest(){
        target.getEnumIdents().addAll(Arrays.asList("ENUM1", "ENUM2", "ENUM3", "ENUM4"));
        target.setName("ENUMCOLOR");

        String actual = target.toString();
        String expected = "[ENUMCOLOR = with ENUM1 | ENUM2 | ENUM3 | ENUM4]";

        Assert.assertEquals(expected, actual);

    }


        @Override
    public void setUp() {
        target = new EnumColorImpl();
    }
}
