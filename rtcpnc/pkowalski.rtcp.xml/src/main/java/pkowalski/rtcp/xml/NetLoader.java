package pkowalski.rtcp.xml;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-04-28
 * Time:    21:54:44
 *
 */

import org.antlr.runtime.RecognitionException;
import pkowalski.rtcp.model.*;
import pkowalski.rtcp.model.impl.RtcpNetFactory;
import pkowalski.rtcp.model.syntax.InvalidStructureException;
import pkowalski.rtcp.model.syntax.RtcpDeclarationsFactory;

import javax.xml.bind.JAXB;
import java.io.File;
import java.util.*;

public class NetLoader {
    private RtcpNetFactory _rtcpNetFactory;
    private RtcpDeclarationsFactory _rtcpDeclarationsFactory;

    private HashMap<String, Rtcpnet.Pages.Page> _xmlPages;
    private HashMap<Integer, Page> _pageHashMap;
    private HashMap<String, Fusion> _fusionsMap;
    private HashMap<Integer, Map<String, String>> _pageParametersHashMap;

    public NetLoader(){

        Init();

        
    }


    private void Init(){
        _rtcpNetFactory = new RtcpNetFactory();
        _rtcpDeclarationsFactory = new RtcpDeclarationsFactory(_rtcpNetFactory);
        _xmlPages = new HashMap<String, Rtcpnet.Pages.Page>();
        _pageHashMap = new HashMap<Integer, Page>();
        _fusionsMap = new HashMap<String, Fusion>();
        _pageParametersHashMap = new HashMap<Integer, Map<String, String>>();
        
    }

    void MapXmlPages(Rtcpnet.Pages xmlPages){
        System.out.println(">> Reading pages:");
        for (Rtcpnet.Pages.Page xmlPage : xmlPages.getPage()){
            System.out.println("  >> page: " + xmlPage.getName());
            _xmlPages.put(xmlPage.getName(), xmlPage);              
        }
    }

    public RtcpNet LoadFromFile(File file) throws LoaderException {
        Rtcpnet xmlRtcpNet = JAXB.unmarshal(file, Rtcpnet.class);
        MapXmlPages(xmlRtcpNet.getPages());

        RtcpNet rtcpNet = _rtcpNetFactory.BuildRtcpNet();
        try{
            _rtcpDeclarationsFactory.BuildRtcpDeclarations(rtcpNet, xmlRtcpNet.getDeclarations());
        }
        catch (RecognitionException ex){
            throw new LoaderException("Invalid declarations syntax", ex);
        } catch (InvalidStructureException e) {
            throw new LoaderException("Invalid declarations syntax", e);
        }

        System.out.println(">> Building pages:");
        for (Rtcpnet.Hierarchy.Nodes.Node xmlNode : xmlRtcpNet.getHierarchy().getNodes().getNode()) {
            Rtcpnet.Pages.Page xmlPage = _xmlPages.get(xmlNode.getName());
            try{
                Page page = BuildPageInstance(xmlPage, xmlNode.getId().intValue() ,xmlNode.getParameters());
                rtcpNet.getPages().add(page);
            }
            catch (RecognitionException ex){

                throw new LoaderException(ex);
            }
            catch (InvalidStructureException ex){
                
                throw new LoaderException(ex);
            }
        }

        for (Rtcpnet.Hierarchy.Connections.Connection xmlConnection : xmlRtcpNet.getHierarchy().getConnections().getConnection()) {
            try{
                BuildHierarchy(xmlConnection);
            }
            catch(InvalidStructureException ex){
                
                throw new LoaderException(ex);
            }
        }

        rtcpNet.getFusions().addAll(_fusionsMap.values());

        return rtcpNet;


        
    }

    private void BuildHierarchy(Rtcpnet.Hierarchy.Connections.Connection xmlConnection) throws InvalidStructureException {
        String superpageIdS = xmlConnection.getSuperpageId();
        String subpageIdS = xmlConnection.getSubpageId();
        String substitutedTransitionName = xmlConnection.getTransition();
        String assignmentsS = xmlConnection.getAssignments();

        int superpageId = Integer.parseInt(superpageIdS);
        int subpageId = Integer.parseInt(subpageIdS);

        Page superPage;
        Page subPage;

        if (!_pageHashMap.containsKey(superpageId)){
            throw new InvalidStructureException("No page with given ID: " + superpageIdS);
        }
        else{
            superPage = _pageHashMap.get(superpageId);
        }

        if (!_pageHashMap.containsKey(subpageId)){
            throw new InvalidStructureException("No page with given ID: " + subpageIdS);
        }
        else{
            subPage = _pageHashMap.get(subpageId);
        }

        Transition substitutedTransition = superPage.findTransition(substitutedTransitionName);
        if (substitutedTransition == null)
            throw new InvalidStructureException(String.format("Superpage \'%s#%s\' has no transition\'%s\'", superPage.getName(), superpageId, substitutedTransitionName));


        subPage.setSubstitutedTransition(substitutedTransition);

        List<SocketPortPair> parsedAssignments = ParseAssignments(assignmentsS);
        Map<String, String> socketToPortMap = new HashMap<String, String>();

        Map<String , String> socketsParametersMap = _pageParametersHashMap.get(superpageId);
        Map<String , String> portsParametersMap = _pageParametersHashMap.get(subpageId);

        for (SocketPortPair pair : parsedAssignments) {
            socketToPortMap.put(
                    ReplaceParameters(pair.getSocketName(), socketsParametersMap),
                    ReplaceParameters(pair.getPortName(), portsParametersMap)
            );
        }

        for (Place socket : superPage.getSockets()) {
            String portName;
            if (socketToPortMap.containsKey(socket.getName()))
                portName = socketToPortMap.get(socket.getName());
            else
                portName = socket.getName();

            Place port = subPage.findPlace(portName);

            if (port == null)
                throw new InvalidStructureException(String.format("Page %s has no port place: %s", subPage.getName(), portName) );

            subPage.getAssignments().add(_rtcpNetFactory.BuildAssignment(socket, port));
        }
        
        subPage.setSuperPage(superPage);
        superPage.getSubPages().add(subPage);
    }

    List<SocketPortPair> ParseAssignments(String assignments){
        List<SocketPortPair> socketPortPairs = new ArrayList<SocketPortPair>();

        for (StringTokenizer level1Tokenizer = new StringTokenizer(assignments, ",", false); level1Tokenizer.hasMoreTokens();) {
            String assignment = level1Tokenizer.nextToken().trim();

            StringTokenizer level2Tokenizer = new StringTokenizer(assignment, "=", false);

            SocketPortPair socketPortPair = new SocketPortPair();
            socketPortPair.setSocketName(level2Tokenizer.nextToken().trim());
            socketPortPair.setPortName(level2Tokenizer.nextToken().trim());

            socketPortPairs.add(socketPortPair);
            

        }

        return socketPortPairs;
    }

    Page BuildPageInstance(Rtcpnet.Pages.Page xmlPage, int id, String params)throws RecognitionException, InvalidStructureException{
        System.out.println("  >> Page: " + xmlPage.getName() + " with parameters: " + params);
        Map<String, String> parametersMap = ParseParameters(params);

        String nameS = ReplaceParameters(xmlPage.getName(), parametersMap);
        Page page = _rtcpNetFactory.BuildPage(nameS, id);

        System.out.println("    >> Building places:");
        for (Rtcpnet.Pages.Page.Places.Place xmlPlace : xmlPage.getPlaces().getPlace()) {
            Place place = BuildPlaceInstance(xmlPlace, parametersMap,page);
            page.getPlaces().add(place);
        }

        System.out.println("    >> Building transitions:");
        for (Rtcpnet.Pages.Page.Transitions.Transition xmlTransition : xmlPage.getTransitions().getTransition()) {
            Transition transition = BuildTransitionInstance(xmlTransition, parametersMap);
            page.getTransitions().add(transition);
        }

        System.out.println("    >> Building arcs:");
        for (Rtcpnet.Pages.Page.Arcs.Arc xmlArc : xmlPage.getArcs().getArc()) {
            /*Arc arc = */BuildArcInstance(xmlArc, parametersMap, page);
            //page.getArcs().add(arc);
        }

        _pageHashMap.put(id, page);
        _pageParametersHashMap.put(id, parametersMap);



        return page;
        
    }

    Place FindPlaceOnPage(Page page, String placeName) throws InvalidStructureException {
        Place returnPlace = null;
        List<Place> places = page.getPlaces();

        for (Place place : places) {
            if (place.getName().equals(placeName)){
                returnPlace = place;
                break;
            }
        }
        if (returnPlace == null)
            throw new InvalidStructureException(String.format("Place %s not found on page %s", placeName, page.getName()));
        else
            return returnPlace;
    }

    Transition FindTransitionOnPage(Page page, String transitionName) throws InvalidStructureException {
        for (Transition transition : page.getTransitions()) {
            if (transition.getName().equals(transitionName))
                return transition;
        }

        throw new InvalidStructureException(String.format("No transition %s found on page %s", transitionName, page.getName()));

    }

    private void BuildArcInstance(Rtcpnet.Pages.Page.Arcs.Arc xmlArc, Map<String, String> parametersMap, Page page) throws RecognitionException, InvalidStructureException {
                
        String placeS = ReplaceParameters(xmlArc.getPlace(), parametersMap);
        String transitionS = ReplaceParameters(xmlArc.getTransition(), parametersMap);
        String inExpressionS = ReplaceParameters(xmlArc.getInExpression(), parametersMap);
        String outExpressionS = ReplaceParameters(xmlArc.getOutExpression(), parametersMap);
        String inTimeS = ReplaceParameters(xmlArc.getInTime(),parametersMap);
        String outTimeS = ReplaceParameters(xmlArc.getOutTime(), parametersMap);

        Place place = FindPlaceOnPage(page, placeS);

        Transition transition = FindTransitionOnPage(page, transitionS);
        ArcDirection arcDirection = ParseArcDirection(xmlArc.getDirection());

        System.out.println(String.format("      >> Arc between place %s and transition %s (%s)", placeS, transitionS, xmlArc.getDirection()));

        WeightExpression inExpression = null;
        WeightExpression outExpression = null;

        TimeExpression inTimeExpression = null;
        TimeExpression outTimeExpression = null;


        if (!transition.isSubstituted()){
            if (arcDirection == ArcDirection.In || arcDirection == ArcDirection.InOut){
                inExpression = _rtcpDeclarationsFactory.BuildWeightExpression(inExpressionS, place.getColor());
                inTimeExpression = _rtcpDeclarationsFactory.BuildTimeExpression(inTimeS);
            }

            if (arcDirection == ArcDirection.Out || arcDirection == ArcDirection.InOut){
                outExpression = _rtcpDeclarationsFactory.BuildWeightExpression(outExpressionS, place.getColor());
                outTimeExpression = _rtcpDeclarationsFactory.BuildTimeExpression(outTimeS);
            }
        }


        _rtcpNetFactory.BuildArc(/*page,*/ place, transition, arcDirection, inExpression, outExpression, inTimeExpression, outTimeExpression);



        
    }

    Transition BuildTransitionInstance(Rtcpnet.Pages.Page.Transitions.Transition xmlTransition, Map<String, String> parametersMap) throws RecognitionException, InvalidStructureException {
        System.out.println("      >> Building transition: " + xmlTransition.getName());
        String nameS = ReplaceParameters(xmlTransition.getName(), parametersMap);
        String priorityS = ReplaceParameters(xmlTransition.getPriority(), parametersMap);
        String guardS = ReplaceParameters(xmlTransition.getGuard(), parametersMap);


        Integer priority = Integer.parseInt(priorityS);
        GuardExpression guardExpression = _rtcpDeclarationsFactory.BuildGuardExpression(guardS);
        boolean isSubstituted = ParseSubstituted(xmlTransition.getSubstituted());

        Transition transition;
        transition = _rtcpNetFactory.BuildTransition(nameS, guardExpression, priority, isSubstituted);
        return transition;
    }

    private boolean ParseSubstituted(String substituted) throws InvalidStructureException {
        
        if (substituted.equals("yes")){
            return true;
        }
        if (substituted.equals("no")){
            return false;
        }

        throw new InvalidStructureException("Subsititution definition: \"yes/no\" value expected.");
    }

    Place BuildPlaceInstance(Rtcpnet.Pages.Page.Places.Place xmlPlace, Map<String, String> parametersMap, Page page)throws RecognitionException, InvalidStructureException{
        System.out.println("      >> Place: " + xmlPlace.getName());
        String nameS = ReplaceParameters(xmlPlace.getName(), parametersMap);
        String fusionS = ReplaceParameters(xmlPlace.getFusion(), parametersMap);
        String markingS = ReplaceParameters(xmlPlace.getMarking(), parametersMap);
        //String portS = ReplaceParameters(xmlPlace.getPort(), parametersMap);
        String typeS = ReplaceParameters(xmlPlace.getType(), parametersMap);
        String timeS = ReplaceParameters(xmlPlace.getTime(), parametersMap);

        
        Color color = _rtcpDeclarationsFactory.GetDefinedColor(typeS);
        Marking marking = _rtcpDeclarationsFactory.BuildMarking(markingS, color);



        //PortType portType = ParsePortType(portS);
        Integer time = Integer.parseInt(timeS);
        Fusion fusion = null;
        if (!fusionS.isEmpty()){
            fusion = GetFusion(fusionS);
        }

        Place place = _rtcpNetFactory.BuildPlace(nameS, color, fusion, marking, page, /*portType,*/ time);

        if (fusion != null){
            AddPlaceToFusion(fusion, place);
        }

        return place;




    }

    void AddPlaceToFusion(Fusion fusion, Place place) throws InvalidStructureException {
        if (fusion.getPlaces().size() > 0){
            Place inFusionPlace = fusion.getPlaces().get(0);
            boolean isCorrect;
            isCorrect =
                    (inFusionPlace.getMarking().equals(place.getMarking()))
                &&  (inFusionPlace.getColor().equals(place.getColor()))
                //&&  (inFusionPlace.getPortType().equals(place.getPortType()))
                &&  (inFusionPlace.getTime().equals(place.getTime()));
            if (!isCorrect)
                throw new InvalidStructureException(String.format("Miejsca należące do jednej fuzji miejsc (\"%s\") różnią się kolorem lub znakowaniem", fusion.getName()));

        }

        fusion.getPlaces().add(place);
    }

    ArcDirection ParseArcDirection(String value) throws InvalidStructureException {
        if (value.equals("In")){
            return ArcDirection.In;
        }
        if (value.equals("Out")){
            return ArcDirection.Out;
        }
        if (value.equals("In/Out")){
            return ArcDirection.InOut;
        }

        throw new InvalidStructureException("Invalid arc direction value: " + value);

    }

    PortType ParsePortType(String value) throws InvalidStructureException {
        if (value.equals("None")){
            return PortType.None;
        }
        if (value.equals("In")){
            return PortType.In;
        }
        if (value.equals("Out")){
            return PortType.Out;
        }
        if (value.equals("In/Out")){
            return PortType.InOut;
        }

        throw new InvalidStructureException("Unknown port type value: " + value);
    }

    Fusion GetFusion(String fusionName){
        if (_fusionsMap.containsKey(fusionName)){
            return _fusionsMap.get(fusionName);
        }
        else{
            Fusion fusion = _rtcpNetFactory.BuildFusion(fusionName);
            _fusionsMap.put(fusionName, fusion);
            return fusion;
        }
    }



    String ReplaceParameters(String value, Map<String, String> parameters){
        for (String key : parameters.keySet()) {
            value = value.replaceAll(key, parameters.get(key));
        }

        return value;
    }

    Map<String, String> ParseParameters(String parameters){
        HashMap<String, String> parametersMap = new HashMap<String, String>();

        for (StringTokenizer tokenizer = new StringTokenizer(parameters,",", false); tokenizer.hasMoreTokens();) {
            String keyValuePair = tokenizer.nextToken();

            String parameterName = null;
            String parameterValue = null;

            for (StringTokenizer keyValuePairTokenizer = new StringTokenizer(keyValuePair, "=", false); keyValuePairTokenizer.hasMoreTokens();) {
                String s = keyValuePairTokenizer.nextToken().trim();
                if (s.startsWith("%")){
                    parameterName = s.trim();
                }
                else
                    parameterValue = s.trim();
                                
            }

            if (parameterName != null && parameterValue != null)
                parametersMap.put(parameterName, parameterValue);


        }


        return parametersMap;

    }

}
