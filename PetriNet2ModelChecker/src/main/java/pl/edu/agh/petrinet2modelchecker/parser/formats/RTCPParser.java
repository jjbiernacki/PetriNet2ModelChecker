package pl.edu.agh.petrinet2modelchecker.parser.formats;

import pl.edu.agh.petrinet2modelchecker.exceptions.SyntaxException;
import pl.edu.agh.petrinet2modelchecker.model.rtcp.Marking;
import pl.edu.agh.petrinet2modelchecker.model.rtcp.RTCPPlace;
import pl.edu.agh.petrinet2modelchecker.model.rtcp.RTCPReachabilityGraph;
import pl.edu.agh.petrinet2modelchecker.model.rtcp.RTCPState;
import pl.edu.agh.petrinet2modelchecker.parser.interfaces.RTCPParsable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 * Created by agnieszka on 19.08.14.
 */
public class RTCPParser implements RTCPParsable {

    private List<RTCPPlace> RTCPPlaces = new ArrayList<RTCPPlace>();
    private TreeSet<RTCPState> RTCPStates = new TreeSet<RTCPState>();
    private TreeSet<String> transitions = new TreeSet<String>();
    private long omega = Long.MIN_VALUE;
    private long minTimeOmega = Long.MAX_VALUE;

    @Override
    public RTCPReachabilityGraph parseFile(final String filepath) throws FileNotFoundException, SyntaxException {
        Scanner in = new Scanner(new FileReader(filepath));

        findPlaces(in);
        in = new Scanner(new FileReader(filepath));
        findMarking(in);
        in = new Scanner(new FileReader(filepath));
        findSuccessors(in);

        RTCPReachabilityGraph RTCPReachabilityGraph = new RTCPReachabilityGraph();
        RTCPReachabilityGraph.setRTCPPlaces(RTCPPlaces);
        RTCPReachabilityGraph.setRTCPStates(RTCPStates);
        RTCPReachabilityGraph.setOmega(omega);
        RTCPReachabilityGraph.setMinTimeOmega(minTimeOmega);
        RTCPReachabilityGraph.setTransitions(transitions);

        return RTCPReachabilityGraph;

    }
    private void findSuccessors(Scanner in) throws SyntaxException {
        while(in.hasNext()) {
            String line = in.nextLine();
            if (Pattern.compile("\t.*label").matcher(line).find() && line.contains("->")) {
                String firstNode = line.substring(1, line.indexOf(" "));
                String secondPart = line.substring(line.indexOf("->") + 3);
                String secondNode = secondPart.substring(0, secondPart.indexOf(" "));
                RTCPState firstRTCPState = findStateById(firstNode);
                RTCPState secondRTCPState = findStateById(secondNode);
                String label = line.substring(line.indexOf("(") + 1, line.indexOf(","));
                firstRTCPState.addSuccessor(secondRTCPState, label);
                transitions.add(label);
            }
        }
    }

    private RTCPState findStateById(String idText) throws SyntaxException {

        int id;
        try {
            id = Integer.valueOf(idText);
        } catch (NumberFormatException e) {
            throw new SyntaxException("Wrong state id=" + idText);
        }

        for (RTCPState RTCPState : RTCPStates) {
            if (RTCPState.getId() == id) {
                return RTCPState;
            }
        }
        throw new SyntaxException("Cannot find state with idText=" + idText);
    }

    private void findMarking(Scanner in) {
        while(in.hasNext()) {
            String line = in.nextLine();
            if (Pattern.compile("\t.*label").matcher(line).find() && !line.contains("->")) {

                String stateId = line.substring(1, line.indexOf(" "));

                int markingTextStartIndex = line.indexOf("\"") +1;
                int markingTextEndIndex = line.indexOf("\\n") - 1;

                if (line.startsWith("\t0")) {
                    markingTextStartIndex = line.indexOf("\\n") + 2;
                    markingTextEndIndex = line.lastIndexOf("\\n") - 1;
                }

                int timeMarkingTextStartIndex = line.lastIndexOf("\\n") + 2;
                int timeMarkingTextEndIndex = line.lastIndexOf("\"") - 1;

                String markingText = line.substring(markingTextStartIndex + 1, markingTextEndIndex);
                String timeMarkingText = line.substring(timeMarkingTextStartIndex + 1, timeMarkingTextEndIndex);
                findState(stateId, markingText, timeMarkingText);
            } else {
                continue;
            }
        }
    }

    private void findState(String stateId, String markingText, String timeMarkingText) {

        RTCPState RTCPState = new RTCPState(Integer.valueOf(stateId));
        List<String> markingTexts = getMarkingText(markingText);
        List<String> timeMarkingTexts = getTimeMarkingText(timeMarkingText);

        for (int i = 0; i < RTCPPlaces.size(); i++) {
            String placeMarkingText = markingTexts.get(i);
            RTCPPlace RTCPPlace = RTCPPlaces.get(i);
            Marking placeMarking = new Marking();
            long timeMarking = Long.valueOf(timeMarkingTexts.get(i));
            placeMarking.setTimeMarking(timeMarking);

            if (timeMarking > omega) {
                omega = timeMarking;
            }
            if (timeMarking < minTimeOmega) {
                minTimeOmega = timeMarking;
            }
            for(String text: placeMarkingText.split("\\+")) {
                if (text.charAt(0) == '-') {
                    continue;
                }
                long markingValue = 1;
                if (text.charAt(0) != '(') {
                    markingValue = Long.valueOf(text.substring(0, text.indexOf("(")));
                    text = text.substring(text.indexOf("("));
                }

                if (markingValue > omega) {
                    omega = markingValue;
                }

                String mark = text.replace("(", "").replace(")", "").replace(",", "_");
                placeMarking.addMarking(mark, markingValue);
                RTCPPlace.addMarking(mark);
            }

            RTCPState.addMarking(RTCPPlace, placeMarking);
        }
        RTCPStates.add(RTCPState);
    }
    private List<String> getTimeMarkingText(String timeMarkingText) {

        List<String> list = new ArrayList<String>();
        timeMarkingText = timeMarkingText.replace("(", "").replace(")", "").replace(" ", "");
        String[] texts = timeMarkingText.split(",");
        for (String string: texts) {
            list.add(string);
        }

        return list;
    }

    private List<String> getMarkingText(String markingText) {

        List<String> list = new ArrayList<String>();

        int bracklesCounter = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < markingText.length(); i++) {
            char charAt = markingText.charAt(i);
            if (charAt == '(') {
                bracklesCounter++;
            } else if (charAt == ')') {
                bracklesCounter--;
            } else if (charAt == ',' && bracklesCounter == 0) {
                list.add(sb.toString().replace(" ", ""));
                sb = new StringBuilder();
                continue;
            }
            sb.append(charAt);
        }
        if (sb.toString().length() > 0) {
            list.add(sb.toString().replace(" ", ""));
        }
        return list;
    }

    private void findPlaces(Scanner in) {
        while(in.hasNext()) {
            String line = in.nextLine();
            if (line.startsWith("\t0")) {

                int placeTextStartIndex = line.indexOf("\"");
                int placeTextEndIndex = line.indexOf("\\n");
                String placesText = line.substring(placeTextStartIndex + 1, placeTextEndIndex);
                setPlacesList(placesText.split(","));
                break;
            } else {
                continue;
            }
        }
    }

    private void setPlacesList(String[] strings) {
        for (String placeText: strings) {
            placeText = placeText.replace("(", "").replace(")", "").replace(" ", "").replace(",", "");
            RTCPPlace RTCPPlace = new RTCPPlace(placeText);
            RTCPPlaces.add(RTCPPlace);
        }
    }


    public static void main(String... args) {
        RTCPParser parser2 = new RTCPParser();
        System.out.println("########################################################################");
        try {
            parser2.parseFile("?");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SyntaxException e) {
            e.printStackTrace();
        }
    }
}
