package tests.BoolVariableImplTests;

import org.junit.Assert;
import org.junit.Test;
import pkowalski.junit.utils.Property;
import pkowalski.junit.utils.TestCase;
import pkowalski.rtcp.model.Color;
import pkowalski.rtcp.model.VariableNotInitialized;
import pkowalski.rtcp.model.impl.BoolVariableImpl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class BoolVariablesImpl_PropertiesTests extends TestCase<BoolVariableImpl>{

    private Color color;

    @Test()
    public void ValueTest() throws Exception {
        PropertyTest(true, "Value", new Property<BoolVariableImpl, Boolean>() {
            @Override
            public Boolean getValue(BoolVariableImpl target)throws Exception{
                return target.getValue();
            }

            @Override
            public void setValue(BoolVariableImpl target, Boolean value) {
                target.setValue(value);
            }
        });

    }

    @Test(expected = VariableNotInitialized.class)
    public void ValueNotSetTest() throws VariableNotInitialized {

        target.getValue();
    }


    @Test()
    public void NameTest() throws Exception {
        PropertyTest("BOOLCOLOR", "Name", new Property<BoolVariableImpl, String>() {
            @Override
            public String getValue(BoolVariableImpl target) {
                return target.getName();
            }

            @Override
            public void setValue(BoolVariableImpl target, String value) {
                target.setName(value);
            }
        });

    }

    @Test()
    public void ColorTest() throws Exception {
        PropertyTest(color, "Color", new Property<BoolVariableImpl, Color>() {
            @Override
            public Color getValue(BoolVariableImpl target) {
                return target.getColor();
            }

            @Override
            public void setValue(BoolVariableImpl target, Color value) {
                target.setColor(value);
            }
        });

    }

    @Test()
    public void ToStringWithValueTest() {
        target.setColor(color);
        target.setName("BOOLVARIABLE");
        target.setValue(true);
        
        String expected = "COLOR : BOOLVARIABLE = true";

        String actual = target.toString();

        assertNotNull(actual);
        Assert.assertEquals(expected, actual);

    }

    @Test()
    public void ToStringWithValueNullTest() {
        target.setColor(color);
        target.setName("BOOLVARIABLE");
        

        String expected = "COLOR : BOOLVARIABLE = null";

        String actual = target.toString();

        assertNotNull(actual);
        Assert.assertEquals(expected, actual);



    }


    @Override
    public void setUp() {
        target = new BoolVariableImpl();
        color = mock(Color.class);
        when(color.getName()).thenReturn("COLOR");
    }
}
