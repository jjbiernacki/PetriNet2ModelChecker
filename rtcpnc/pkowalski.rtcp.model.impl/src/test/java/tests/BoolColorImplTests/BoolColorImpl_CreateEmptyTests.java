package tests.BoolColorImplTests;

import org.junit.Before;
import org.junit.Test;
import pkowalski.rtcp.model.BoolVariable;
import pkowalski.rtcp.model.VariableNotInitialized;
import pkowalski.rtcp.model.impl.BoolColorImpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class BoolColorImpl_CreateEmptyTests {
    private BoolColorImpl target;

    @Test(expected = VariableNotInitialized.class)
    public void CreateEmptyTest() throws VariableNotInitialized {
        BoolVariable actual = target.CreateEmpty();


        assertNotNull(actual);
        assertTrue(actual.getColor() == target);

        actual.getValue();
        

    }

    

    @Before
    public void setUp(){
        target = new BoolColorImpl();
        
    }

}
