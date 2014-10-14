package pkowalski.rtcp.model.impl;

import pkowalski.rtcp.model.Fusion;
import pkowalski.rtcp.model.Place;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-10
 * Time:    17:50:16
 *
 */
public class FusionImpl implements Fusion{

    public FusionImpl(){
        // Add your code here:
        super();
        _places = new ArrayList<Place>();
        
    }


    private String _name;

    @Override
    public String getName() {
        return _name;
    }

    
    public void setName(String value) {
        _name = value;
    }

    private final List<Place> _places;

    @Override
    public List<Place> getPlaces() {
        return _places;
    }

    @Override
    public Place getPatternPlace() {
        if (_places.size() == 0)
            throw new IllegalStateException("Fuzja miejsc nie posiada zadnych przypisanych miejsc");

        return _places.get(0);
    }

    @Override
    public String toString(){
        return _name;
    }
}
