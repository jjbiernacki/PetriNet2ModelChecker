package pkowalski.rtcp.runtime.coverability;

import pkowalski.rtcp.runtime.model.Place;
import pkowalski.rtcp.runtime.model.Token;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: lordjim
 * Date: 07.06.11
 * Time: 20:48
 * To change this template use File | Settings | File Templates.
 */
public class NetState {
    private Hashtable<Place, List<Token>> _marking;
    public Hashtable<Place, List<Token>> getMarking(){
        return _marking;
    }
    private Hashtable<Place, Integer> _timestamps;
    public Hashtable<Place, Integer> getTimestamps(){
        return _timestamps;
    }

    public boolean IsTimeInsensitiveStateSet(){
        return _timeInsensitiveState != null;
    }

    private NetState _timeInsensitiveState;
    public NetState getTimeInsensitiveState(){
        if (_timeInsensitiveState == null)
            return this;
        else
            return _timeInsensitiveState;
    }

    public void setTimeInsensitiveState(NetState state){
        _timeInsensitiveState = new NetState(state);
    }


    public NetState(List<Place> placeList){
        _marking = new Hashtable<Place, List<Token>>();
        _timestamps = new Hashtable<Place, Integer>();
        _placeList = placeList;
        _timeInsensitiveState = null;
    }

    public NetState(NetState other){
        _marking = new Hashtable<Place, List<Token>>(other.getMarking());
        _timestamps = new Hashtable<Place, Integer>(other.getTimestamps());
        if (other.IsTimeInsensitiveStateSet())
            _timeInsensitiveState = other.getTimeInsensitiveState();
        else
            _timeInsensitiveState = null;
        _placeList = other._placeList;
    }

    private List<Place> _placeList;

    @Override
    public String toString(){
        /*
        List<Place> places = new ArrayList<Place>(_marking.keySet());
        Collections.sort(places, new Comparator<Place>() {
            @Override
            public int compare(Place place, Place place1) {
                return place.getName().compareTo(place1.getName());
            }
        });
        */

        StringBuilder sb = new StringBuilder();
        sb.append("M=(");

        boolean addPlaceComa = false;
        for(Place place:_placeList){
            if (addPlaceComa){
                sb.append(", ");
            }
            else {
                addPlaceComa = true;
            }
            List<Token> tokens = _marking.get(place);
            sb.append("[");

            boolean addTokenComa = false;

            for(Token token: tokens){
                if (addTokenComa){
                    sb.append(", ");
                }
                else
                addTokenComa = true;
                sb.append(token.toString());

            }

            sb.append("]");
        }
        sb.append("), S=(");
        addPlaceComa = false;
        for(Place place:_placeList){
            if (addPlaceComa){
                sb.append(", ");
            }
            else {
                addPlaceComa = true;
            }

            sb.append(_timestamps.get(place));


        }

        sb.append(")");

        return sb.toString();

    }


}
