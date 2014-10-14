package tests.ArcImplTests;

import org.junit.Before;
import org.junit.Test;
import pkowalski.junit.utils.Property;
import pkowalski.junit.utils.TestCase;
import pkowalski.rtcp.model.*;
import pkowalski.rtcp.model.impl.ArcImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ArcImpl_PropertiesTests extends TestCase<ArcImpl>{

    //ArcImpl target;

    private Place place;
    private Transition transition;
    private ArcDirection arcDirection;
    private WeightExpression weightExpression;
    private TimeExpression timeExpression;

    private String expectedToString;
    

    @Test
    public void PlaceTest() throws Exception {

        PropertyTest(place, "Place", new Property<ArcImpl, Place>() {
            @Override
            public Place getValue(ArcImpl arc) {
                return arc.getPlace();
            }

            @Override
            public void setValue(ArcImpl arc, Place value) {
                arc.setPlace(value);
            }
        });
        
    }

    @Test()
    public void TransitionTest() throws Exception {
        PropertyTest(transition, "Transition", new Property<ArcImpl, Transition>() {
            @Override
            public Transition getValue(ArcImpl arc) {
                return arc.getTransition();
            }

            @Override
            public void setValue(ArcImpl arc, Transition value) {
                arc.setTransition(value);
            }
        });
    }

    @Test()
    public void ArcDirectionTest() throws Exception {
        PropertyTest(arcDirection, "ArcDirection", new Property<ArcImpl, ArcDirection>() {
            @Override
            public ArcDirection getValue(ArcImpl target) {
                return target.getArcDirection();
            }

            @Override
            public void setValue(ArcImpl target, ArcDirection value) {
                target.setArcDirection(value);
            }
        });
        
    }

    @Test()
    public void InExpressionTest() throws Exception {
        PropertyTest(weightExpression, "InExpression", new Property<ArcImpl, WeightExpression>() {
            @Override
            public WeightExpression getValue(ArcImpl target) {
                return target.getInExpression();
            }

            @Override
            public void setValue(ArcImpl target, WeightExpression value) {
                target.setInExpression(value);
            }
        });

    }

    @Test()
    public void OutExpressionTest() throws Exception {
        PropertyTest(weightExpression, "OutExpression", new Property<ArcImpl, WeightExpression>() {
            @Override
            public WeightExpression getValue(ArcImpl target) {
                return target.getOutExpression();
            }

            @Override
            public void setValue(ArcImpl target, WeightExpression value) {
                target.setOutExpression(value);
            }
        });

    }

    @Test()
    public void InTimeExpressionTest() throws Exception {
        PropertyTest(timeExpression, "InTimeExpression", new Property<ArcImpl, TimeExpression>() {
            @Override
            public TimeExpression getValue(ArcImpl target) {
                return target.getInTimeExpression();
            }

            @Override
            public void setValue(ArcImpl target, TimeExpression value) {
                target.setInTimeExpression(value);
            }
        });
        
    }

    @Test()
    public void OutTimeExpressionTest() throws Exception {
        PropertyTest(timeExpression, "OutTimeExpression", new Property<ArcImpl, TimeExpression>() {
            @Override
            public TimeExpression getValue(ArcImpl target) {
                return target.getOutTimeExpression();
            }

            @Override
            public void setValue(ArcImpl target, TimeExpression value) {
                target.setOutTimeExpression(value);
            }
        });

    }

    @Test
    public void ToStringTest(){
        target.setPlace(place);
        target.setTransition(transition);

        String actual = target.toString();

        assertNotNull("Not null value expected", actual);
        assertEquals(expectedToString, actual);
    }




    @Before
    @Override
    public void setUp(){
        target = new ArcImpl();

        place = mock(Place.class);
        when(place.getName()).thenReturn("TestPlace");

        transition = mock(Transition.class);
        when(transition.getName()).thenReturn("TestTransition");

        arcDirection = ArcDirection.InOut;
        weightExpression = mock(WeightExpression.class);
        timeExpression = mock(TimeExpression.class);

        expectedToString = "[P: TestPlace <=> T: TestTransition]";
    }
}
