package tests.EnumVariableImplTests;

import org.junit.Test;
import pkowalski.junit.utils.Property;
import pkowalski.junit.utils.TestCase;
import pkowalski.rtcp.model.Color;
import pkowalski.rtcp.model.VariableNotInitialized;
import pkowalski.rtcp.model.impl.EnumVariableImpl;


public class EnumVariableImpl_PropertiesTests extends TestCase<EnumVariableImpl>{

    Color color;

    @Test()
    public void ColorTest() throws Exception {
        PropertyTest(color, "Color", new Property<EnumVariableImpl, Color>() {
            @Override
            public Color getValue(EnumVariableImpl target) {
                return target.getColor();
            }

            @Override
            public void setValue(EnumVariableImpl target, Color value) {
                target.setColor(value);
            }
        });

    }

    @Test()
    public void NameTest() throws Exception {
        PropertyTest("ENUMVARIABLE", "Name", new Property<EnumVariableImpl, String>() {
            @Override
            public String getValue(EnumVariableImpl target) {
                return target.getName();
            }

            @Override
            public void setValue(EnumVariableImpl target, String value) {
                target.setName(value);
            }
        });

    }

    @Test()
    public void ValueTest() throws Exception {
        PropertyTest("ENUMVALUE", "Value", new Property<EnumVariableImpl, String>() {
            @Override
            public String getValue(EnumVariableImpl target) throws Exception {
                return target.getValue();
            }

            @Override
            public void setValue(EnumVariableImpl target, String value) {
                target.setValue(value);
            }
        });

    }

    @Test(expected = VariableNotInitialized.class)
    public void ValueNotSetTest() throws VariableNotInitialized {
        target.getValue();
    }

    @Test
    public void ToStringWithValueTest(){
        target.setValue("ENUMVALUE");

        String expected = "";
        String actual = target.toString();
    }

    @Test
    public void ToStringWithValueNullTest(){

    }


    @Override
    public void setUp() {
        target = new EnumVariableImpl();
    }
}
