package tests.AssignmentImplTests;

import org.junit.Before;
import org.junit.Test;
import pkowalski.junit.utils.Property;
import pkowalski.rtcp.model.Place;
import pkowalski.rtcp.model.impl.AssignmentImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class AssignmentImpl_PropertiesTests {
    private AssignmentImpl target;
    private String toStringExpected;

    @Test()
    public void SocketPlaceTest() {
        assertNotNull(target.getSocketPlace());
    }

    @Test()
    public void PortPlaceTest() {

        assertNotNull(target.getPortPlace());
    }

    @Test()
    public void ToStringTest() {

        String actual = target.toString();

        assertEquals(toStringExpected,actual);

    }

    @Before
    public void setUp(){
        Place socketPlace = mock(Place.class);
        when(socketPlace.getName()).thenReturn("Socket");

        Place portPlace = mock(Place.class);
        when(portPlace.getName()).thenReturn("Port");

        target = new AssignmentImpl(socketPlace, portPlace);

        toStringExpected = "[S: Socket = P: Port]";



    }


}
