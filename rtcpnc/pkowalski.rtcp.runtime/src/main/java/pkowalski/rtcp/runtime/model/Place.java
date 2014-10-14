package pkowalski.rtcp.runtime.model;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 * WEAIiE, AGH Kraków
 * pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-07-03
 * Time:    20:57:10
 */
public abstract class Place extends ChangeEventGenerator{

    private int _TimeStamp;

    public int getTimeStamp() {
        return _TimeStamp;
    }

    public void setTimeStamp(int value) {
        _TimeStamp = value;
    }


    private final List<Token> _tokens;

    public List<Token> getTokens() {
        return _tokens;
    }

    public void setTokens(List<Token> tokens){
        _tokens.clear();

        _tokens.addAll(tokens);
    }


    @Override
    public void SendChangeEvent(){
        getEventSender().FirePlaceStateChanged(this);
    }


    public Place(RtcpNetEventSender eventSender){
        // Add your code here:
        super(eventSender);
        _tokens = new ArrayList<Token>();
        _arcs = new ArrayList<Arc>();
        

        InitializeTokensAndTimestamp();

    }

    protected abstract void InitializeTokensAndTimestamp();



    private final List<Arc> _arcs;

    public List<Arc> getArcs() {
        return _arcs;
    }

    public void AddArc(Arc arc){
        this.getArcs().add(arc);
    }
    public abstract String getName();

    @SuppressWarnings({"BooleanMethodIsAlwaysInverted"})
    public boolean ContainsToken(Token token){
        for (Token t : _tokens) {
            if (t.equals(token))
                return true;
        }

        return false;
    }

    public void RemoveToken(Token token){
        Token toRemove = null;

        for(Token t: getTokens()){
            if (t.equals(token)){
                toRemove = t;
                break;
            }
        }

        if (toRemove == null)
            throw new IllegalStateException();
        
        getTokens().remove(toRemove);
    }

}
