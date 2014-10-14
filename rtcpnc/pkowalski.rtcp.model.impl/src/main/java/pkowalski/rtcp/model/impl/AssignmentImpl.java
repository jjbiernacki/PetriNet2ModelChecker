package pkowalski.rtcp.model.impl;

import pkowalski.rtcp.model.Assignment;
import pkowalski.rtcp.model.Place;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 * WEAIiE, AGH Kraków
 * pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-30
 * Time:    16:07:13
 */
public class AssignmentImpl implements Assignment{


    public AssignmentImpl(Place socket, Place port) {
        // Add your code here:
        super();
        _socketPlace = socket;
        _portPlace = port;
    }


    private final Place _socketPlace;

    @Override
    public Place getSocketPlace() {
        return _socketPlace;
    }

    private final Place _portPlace;

    @Override
    public Place getPortPlace() {
        return _portPlace;
    }

    @Override
    public String toString(){
        return String.format("[S: %s = P: %s]", _socketPlace.getName(), _portPlace.getName());
    }
}
