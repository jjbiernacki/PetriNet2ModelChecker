package tests.BoolColorImplTests;

import org.junit.Before;
import org.junit.Test;
import pkowalski.rtcp.model.BoolVariable;
import pkowalski.rtcp.model.OutOfRangeException;
import pkowalski.rtcp.model.VariableNotInitialized;
import pkowalski.rtcp.model.impl.BoolColorImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class BoolColorImpl_ParseTest {

    private BoolColorImpl target;
    private List<String> correctValues;
    private Map<String,Boolean> valuesMap;

//    @Test(expected = IllegalArgumentException.class)
//    public void SNullTest() throws OutOfRangeException {
//
//        target.Parse(null);
//    }

    @Test(expected = OutOfRangeException.class)
    public void SOutRangeTest() throws OutOfRangeException {
        target.Parse("XXX");
    }

    @Test()
    public void Test() throws OutOfRangeException, VariableNotInitialized {

        for (String correctValue : valuesMap.keySet()) {
            BoolVariable boolVariable = target.Parse(correctValue);
            String message = String.format("Parse error for value: %s", correctValue);
            assertNotNull(message,boolVariable);
            assertTrue(
                    message,
                    boolVariable.getColor() == target
            );
            assertEquals(
                    message,
                    valuesMap.get(correctValue),
                    boolVariable.getValue()
            );

        }
        
        
    }

    @Before
    public void setUp(){
        target = new BoolColorImpl();
        target.setName("BOOL");
        target.setTrueIdent("TRUE");
        target.setFalseIdent("FALSE");

        
        valuesMap = new HashMap<String, Boolean>();
        valuesMap.put("TRUE",true);
        valuesMap.put("FALSE", false);
    }


}
