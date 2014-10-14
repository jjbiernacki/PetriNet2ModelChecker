package pkowalski.rtcp.model.impl;

import pkowalski.rtcp.model.*;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-16
 * Time:    22:57:44
 *
 */
public class ArcImpl implements Arc{

    /*
    private Page _page;

    @Override
    public Page getPage() {
        return _page;
    }

    @Override
    public void setPage(Page value) {
        _page = value;
    }
    */

    private Place _place;

    @Override
    public Place getPlace() {
        return _place;
    }

    @Override
    public void setPlace(Place value) {
        _place = value;
    }

    private Transition _transition;

    @Override
    public Transition getTransition() {
        return _transition;
    }

    @Override
    public void setTransition(Transition value) {
        _transition = value;
    }

    private ArcDirection _arcDirection;

    @Override
    public ArcDirection getArcDirection() {
        return _arcDirection;
    }

    @Override
    public void setArcDirection(ArcDirection value) {
        _arcDirection = value;
    }

    private WeightExpression _inWeightExpression;

    @Override
    public WeightExpression getInExpression() {
        return _inWeightExpression;
    }

    @Override
    public void setInExpression(WeightExpression value) {
        _inWeightExpression = value;
    }

    private WeightExpression _outWeightExpression;

    @Override
    public WeightExpression getOutExpression() {
        return _outWeightExpression;
    }

    @Override
    public void setOutExpression(WeightExpression value) {
        _outWeightExpression = value;
    }

    private TimeExpression _inTimeExpression;

    @Override
    public TimeExpression getInTimeExpression() {
        return _inTimeExpression;
    }

    @Override
    public void setInTimeExpression(TimeExpression value) {
        _inTimeExpression = value;
    }

    private TimeExpression _outTimeExpression;

    @Override
    public TimeExpression getOutTimeExpression() {
        return _outTimeExpression;
    }

    @Override
    public void setOutTimeExpression(TimeExpression value) {
        _outTimeExpression = value;
    }

    @Override
    public String toString(){
        return String.format("[P: %s <=> T: %s]", _place.getName(), _transition.getName());
    }
}
