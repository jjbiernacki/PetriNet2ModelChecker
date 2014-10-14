package tests.BoolColorImplTests;

import org.junit.Test;
import pkowalski.junit.utils.Property;
import pkowalski.junit.utils.TestCase;
import pkowalski.rtcp.model.impl.BoolColorImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class BoolColorImpl_PropertiesTests extends TestCase<BoolColorImpl>{


    

    @Test()
    public void TrueIdentTest() throws Exception {
        PropertyTest("TRUE", "TrueIdent", new Property<BoolColorImpl, String>() {
            @Override
            public String getValue(BoolColorImpl target) {
                return target.getTrueIdent();
            }

            @Override
            public void setValue(BoolColorImpl target, String value) {
                target.setTrueIdent(value);
            }
        });

    }



    @Test()
    public void FalseIdentTest() throws Exception {
        PropertyTest("FALSE", "FalseIdent", new Property<BoolColorImpl, String>() {
            @Override
            public String getValue(BoolColorImpl target) {
                return target.getFalseIdent();
            }

            @Override
            public void setValue(BoolColorImpl target, String value) {
                target.setFalseIdent(value);
            }
        });
        
    }

    @Test()
    public void CloneTest() {

        BoolColorImpl actual = (BoolColorImpl)target.clone();

        assertNotNull(actual);
        assertEquals(target.getFalseIdent(), actual.getFalseIdent());
        assertEquals(target.getTrueIdent(), actual.getTrueIdent());
        assertEquals(target.getName(), actual.getName());
    }

    @Test()
    public void ToStringTest() {

        target.setName("BOOLCOLOR");
        target.setTrueIdent("TRUE");
        target.setFalseIdent("FALSE");

        String actual = target.toString();

        String toStringExpected = "[BOOLCOLOR = bool with(FALSE, TRUE)]";

        assertNotNull(actual);
        assertEquals(toStringExpected, actual);
    }

    


    @Override
    public void setUp() {
        target = new BoolColorImpl();


    }
}
