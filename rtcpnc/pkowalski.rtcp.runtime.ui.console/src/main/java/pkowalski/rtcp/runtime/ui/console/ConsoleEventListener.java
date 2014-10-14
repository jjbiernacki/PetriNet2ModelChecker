package pkowalski.rtcp.runtime.ui.console;

import pkowalski.rtcp.runtime.events.GlobalClockTickEvent;
import pkowalski.rtcp.runtime.events.PlaceStateChangedEvent;
import pkowalski.rtcp.runtime.events.RtcpNetEventListener;
import pkowalski.rtcp.runtime.events.TransitionFiredEvent;
import pkowalski.rtcp.runtime.model.Arc;
import pkowalski.rtcp.runtime.model.InArc;
import pkowalski.rtcp.runtime.model.OutArc;
import pkowalski.rtcp.runtime.model.Token;
import pkowalski.utils.Utils;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 * WEAIiE, AGH Kraków
 * pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-07-07
 * Time:    21:30:21
 */
public class ConsoleEventListener implements RtcpNetEventListener{
    @Override
    public void GlobalClockChanged(GlobalClockTickEvent event) {
        System.out.println("=========================================================================================");
        System.out.println("Simulation time: " + event.getTime());
    }

    @Override
    public void PlaceStateChanged(PlaceStateChangedEvent event) {
        StringBuilder marking = new StringBuilder();



        Map<Token, Integer> tokenIntegerMap = Utils.GroupByMultiplicity(event.getPlace().getTokens());

        List<Token> tokens = new ArrayList<Token>(tokenIntegerMap.keySet());

        Collections.sort(tokens, new Comparator<Token>() {
            @Override
            public int compare(Token o1, Token o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });

        for (Token token :tokens) {
            if (marking.length() > 0){
                marking.append(" + ");
            }
            Integer multiplicity = tokenIntegerMap.get(token);

            marking.append(String.format("%s(%s)", multiplicity > 1 ? multiplicity : "", token));

        }

        if (event.getPlace().getTimeStamp() != 0){
            marking.append(String.format("@%s", event.getPlace().getTimeStamp()));
        }

        System.out.println(String.format("State of \'%s\' place: %s", event.getPlace().getName(), marking.toString()));

    }

    @Override
    public void TransitionFired(TransitionFiredEvent event) {
        
        System.out.println(String.format("Transition \'%s\' fired with binding: %s", event.getTransition().getName(), event.getTransition().getBinding()));
        for (Arc arc : event.getTransition().getArcs()) {
            if (arc instanceof InArc){
                InArc inArc = (InArc) arc;
                System.out.println(String.format(
                        "\t<<-- token \'%s\' added to place \'%s\' by arc expression: %s",
                        inArc.getInToken().toString(),
                        arc.getPlace().getName(),
                        inArc.getRawInExpression())
                );
            }
            if (arc instanceof OutArc){
                OutArc outArc = (OutArc) arc;
                System.out.println(String.format(
                        "\t-->> token \'%s\' removed from place \'%s\' by arc expression: %s",
                        outArc.getOutToken().toString(),
                        arc.getPlace().getName(),
                        outArc.getRawOutExpression()
                                
                ));
            }
        }
    }
}
