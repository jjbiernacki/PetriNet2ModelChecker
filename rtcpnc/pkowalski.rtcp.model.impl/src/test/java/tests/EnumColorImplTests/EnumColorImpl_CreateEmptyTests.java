package tests.EnumColorImplTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pkowalski.rtcp.model.EnumVariable;
import pkowalski.rtcp.model.VariableNotInitialized;
import pkowalski.rtcp.model.impl.EnumColorImpl;


public class EnumColorImpl_CreateEmptyTests {
    EnumColorImpl target;

    @Test(expected = VariableNotInitialized.class)
    public void Test() throws VariableNotInitialized {
        EnumVariable actual = target.CreateEmpty();

        Assert.assertNotNull(actual);
        Assert.assertEquals(actual.getColor(), target);

        actual.getValue();
    }

    @Before
    public void setUp(){
        target = new EnumColorImpl();
    }

}
