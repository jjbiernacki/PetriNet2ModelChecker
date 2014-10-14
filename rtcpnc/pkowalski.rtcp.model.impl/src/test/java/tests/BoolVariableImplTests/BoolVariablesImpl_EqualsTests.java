package tests.BoolVariableImplTests;

import org.junit.Before;
import org.junit.Test;

import pkowalski.rtcp.model.Color;
import pkowalski.rtcp.model.impl.BoolVariableImpl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;


public class BoolVariablesImpl_EqualsTests{

    BoolVariableImpl target;
    Color targetColor;
    Color otherColor;

    @Test
    public void NullTest(){
        target.setValue(true);
        boolean actual = target.equals(null);
        assertFalse(actual);
    }

    @Test
    public void NotInstanceOfTest(){
        target.setValue(true);
        Object other = new Object();
        boolean actual = target.equals(other);

        assertFalse(actual);
    }

    @Test
    public void TargetVariableNotInitializedTest(){
        BoolVariableImpl boolVariable = new BoolVariableImpl();
        boolVariable.setValue(true);
        boolVariable.setColor(otherColor);

        boolean actual = target.equals(boolVariable);

        assertFalse(actual);
    }

    @Test
    public void OtherVariableNotInitializedTest(){
        BoolVariableImpl boolVariable = new BoolVariableImpl();
        boolVariable.setColor(otherColor);
        target.setValue(true);

        boolean actual = target.equals(boolVariable);

        assertFalse(actual);
    }

    @Test
    public void DifferentColorAndValueTest(){
        BoolVariableImpl boolVariable = new BoolVariableImpl();
        boolVariable.setColor(otherColor);
        boolVariable.setValue(false);

        target.setValue(true);

        boolean actual = target.equals(boolVariable);

        assertFalse(actual);
    }

    @Test
    public void DifferentColorTest(){
        BoolVariableImpl boolVariable = new BoolVariableImpl();
        boolVariable.setColor(otherColor);
        boolVariable.setValue(true);

        target.setValue(true);

        boolean actual = target.equals(boolVariable);

        assertFalse(actual);

    }

    @Test
    public void DifferentValueTest(){
        BoolVariableImpl boolVariable = new BoolVariableImpl();
        boolVariable.setColor(targetColor);
        boolVariable.setValue(false);

        target.setValue(true);

        boolean actual = target.equals(boolVariable);

        assertFalse(actual);

    }

    @Test
    public void Test(){
        BoolVariableImpl boolVariable = new BoolVariableImpl();
        boolVariable.setColor(targetColor);
        boolVariable.setValue(true);

        target.setValue(true);

        boolean actual = target.equals(boolVariable);

        assertTrue(actual);

    }


    @Before
    public void setUp(){
        target = new BoolVariableImpl();

        targetColor = mock(Color.class);
        otherColor = mock(Color.class);
        
        target.setColor(targetColor);


    }
}
