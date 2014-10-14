package pkowalski.rtcp.runtime.model;

import pkowalski.rtcp.runtime.events.RtcpNetEventSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 * WEAIiE, AGH Kraków
 * pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-07-03
 * Time:    14:44:02
 */
public abstract class Transition extends ChangeEventGenerator{
    private static final Random random = new Random();

    // TODO: Usunąć wszystkie zmienne z Transition, zamienić na propertę Binding w której będą te zmienne

    public abstract int getPriority();



    @Override
    public void SendChangeEvent(){
        getEventSender().FireTransitionFired(this);
    }

    public Transition(RtcpNetEventSender eventSender){
        // Add your code here:
        super(eventSender);
        _arcs = new ArrayList<Arc>();
        _availableBindings = new ArrayList<Binding>();

    }


    private final List<Arc> _arcs;

    public List<Arc> getArcs() {
        return _arcs;
    }

    public void AddArc(Arc arc){
        this.getArcs().add(arc);
    }

    private boolean _isAvailable;

    public boolean isAvailable() {
        return _isAvailable;
    }

    private boolean _isActive;

    public boolean isActive() {
        return _isActive;
    }

    public void setActive(boolean value) {
        _isActive = value;
    }

    public abstract String getName();
    
    private List<Binding> _availableBindings;
    public List<Binding> getAvailableBindings(){
        return _availableBindings;
    }
    private void setAvailableBindings(List<Binding> bindings){
        _availableBindings = bindings;
    }

    public void SetRandomBinding(){
        if (isAvailable()){
            if (getAvailableBindings().size() > 1){
                int bindingIndex = random.nextInt(getAvailableBindings().size());
                SetBinding(getAvailableBindings().get(bindingIndex));
            }
            else {
                SetBinding(getAvailableBindings().get(0));
            }
        }
    }

    boolean IsAvailable(){

        List<Binding> availableBindings = FindAvailableBindings();
        setAvailableBindings(availableBindings);
        return  availableBindings.size() > 0;
    }

    public List<Binding> FindAvailableBindings() {
        List<Binding> bindings = GetBindings();
        List<Binding> availableBindings = new ArrayList<Binding>();

        for (Binding binding : bindings) {
            SetBinding(binding);
            boolean isBindingAvailable = true;


            for (Arc arc : _arcs) {
                // Miejsce wejściowe przejście jest łączone z przejściem za pomocą łuku wyjściowego,
                // Miejsce wejściowe przejście jest łączone z przejściem za pomocą łuku wyjściowego

                if (arc instanceof OutArc){
                    // Dla miejsc wejściowych (poprzez łuki wejściowe):
                    OutArc outArc = (OutArc)arc;
                    Token token = outArc.getOutToken();
                    if (!(arc.getPlace().ContainsToken(token))){
                        // sprawdzenie czy miejsca wejściowe zawierają znaczniki zwracane przez wyrażenie łuku
                        //  wejściowego
                        isBindingAvailable = false;
                        break;
                    }

                    if (!(outArc.getOutTime() <= -arc.getPlace().getTimeStamp())){
                        // wiek znaczników w miejscu wejściowym (a raczej wiek miejsca) musi być większy lub równy
                        //  wyrażeniu czasowemu łuku wejściowego.
                        //  M. Szpyrka: "Modelowanie i analiza systemów wbudowanych z zastosowaniem RTCP-sieci", str. 41
                        //  Cytat: "Pieczątki czasowe przypisywane są do miejsc, zamiast do znaczników. Dodatnia wartość
                        //      pieczątki czasowej określa, jak długo znaczniki w danym miejscu pozostaną jeszcze
                        //      niedostępne. Z każdym taktem zegara wartość pieczątki czasowej maleje, a gdy osiągnie
                        //      wartość zero, znaczniki stają się dostępne dla przejść sieci. Kolejne takty zegara mogą
                        //      spowodować, że pieczątka czasowa będzie przyjmować wartości ujemne, co określane jest
                        //      jako starzenie się znaczników, na przykład wartość -3 oznacza wiek znacznika wynoszący
                        //      3 jednostki czasu."
                        isBindingAvailable = false;
                        break;
                    }

                }

                if (arc instanceof InArc){
                    // Dla miejsc wyjściowych (poprzez łuki wyjściowe):
                    if (!(arc.getPlace().getTimeStamp() <= 0)){
                        //  znaczniki w miejscu wyjściowym muszą być dostępne, tzn ich wiek musi być równy lub większy 0
                        //  Bardziej oznacza to dostępność miejsca niż znaczników.
                        isBindingAvailable = false;
                        break;
                    }
                }
            }

            if (isBindingAvailable)
                availableBindings.add(binding);


        }
        return availableBindings;
    }

    public abstract List<Binding> GetBindings();

    public abstract void SetBinding(Binding binding);

    public abstract Binding getBinding();


    /**
     * Wyznacza stan przejścia (aktywne/nieaktywne).
     * Formalna definicja przejścia aktywnego: M. Szpyrka: "Modelowanie i analiza systemów wbudowanych z zastosowaniem
     *  RTCP-sieci", str. 44
     * Cytat: "(...), przejście t jest aktywne w stanie (M, S) przy wiązaniu b, jeżeli wszystkie miejsca wejściowe
     *  zawierają znaczniki o odpowiednich wartościach i mają odpowiednie wartości pieczątek czasowych, wszystkie
     *  miejsca wyjściowe są dostępne oraz żadne inne przejście o wyższym priorytecie nie rywalizuje z przejściem t o
     *  to samo miejsce wejściowe lub wyjściowe.
     */
    public void CalculateAvailability(){
        _isAvailable = IsAvailable();
    }

    public void FireTransition(){
        this.SendChangeEvent();
        for(Arc arc : this.getArcs()){
            if (arc instanceof OutArc){
                OutArc outArc = (OutArc)arc;
                Token token = outArc.getOutToken();
                arc.getPlace().RemoveToken(token);
                arc.getPlace().setTimeStamp(0);

            }

            if (arc instanceof InArc){
                InArc inArc = (InArc)arc;
                Token token = inArc.getInToken();
                arc.getPlace().getTokens().add(token);
                arc.getPlace().setTimeStamp(inArc.getInTime());
            }

            arc.getPlace().SendChangeEvent();
        }
    }
}
