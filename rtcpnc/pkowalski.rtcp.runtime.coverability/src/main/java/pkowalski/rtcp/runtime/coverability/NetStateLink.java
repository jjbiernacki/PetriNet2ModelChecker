package pkowalski.rtcp.runtime.coverability;

import pkowalski.rtcp.runtime.model.Binding;
import pkowalski.rtcp.runtime.model.Transition;

/**
 * Created by IntelliJ IDEA.
 * User: lordjim
 * Date: 12.06.11
 * Time: 12:11
 * To change this template use File | Settings | File Templates.
 */
public class NetStateLink {
    private NetState _predecessor;
    private NetState _successor;
    private Transition _transition;
    private Binding _binding;
    private int _time;

    public NetState getPredecessor(){
        return _predecessor;
    }

    public NetState getSuccessor(){
        return _successor;
    }


    public Transition getTransition() {
        return _transition;
    }

    public Binding getBinding() {
        return _binding;
    }

    public int getTime() {
        return _time;
    }

    public void setPredecessor(NetState _predecessor) {
        this._predecessor = _predecessor;
    }

    public void setSuccessor(NetState _successor) {
        this._successor = _successor;
    }

    public void setTransition(Transition _transition) {
        this._transition = _transition;
    }

    public void setBinding(Binding _binding) {
        this._binding = _binding;
    }

    public void setTime(int _time) {
        this._time = _time;
    }
}
