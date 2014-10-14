package pkowalski.junit.utils;

import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


public abstract class TestCase<TTarget> {
    protected TTarget target;
    protected <TProperty> void PropertyTest(TProperty testValue, String propertyName, Property<TTarget,TProperty> property)throws Exception{
        property.setValue(target,null);

        assertNull(String.format("Null value exprected for property \'%s\'.", propertyName),property.getValue(target));

        property.setValue(target, testValue);

        assertNotNull(String.format("Not null value expected for property \'%s\'.",propertyName),property.getValue(target));

        assertEquals(
                String.format("Property \'%s\' returns different value",propertyName),
                testValue,
                property.getValue(target)
        );
    }

    @Before
    public abstract void setUp();
}
