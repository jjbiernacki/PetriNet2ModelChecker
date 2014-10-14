package pkowalski.rtcp;


import pkowalski.rtcp.codefactory.CodeFactory;
import pkowalski.rtcp.codefactory.GenerationException;
import pkowalski.rtcp.codefactory.java.JavaCodeFactory;
import pkowalski.rtcp.model.*;
import pkowalski.rtcp.model.impl.RtcpNetFactory;
import pkowalski.rtcp.xml.LoaderException;
import pkowalski.rtcp.xml.NetLoader;
import pkowalski.utils.Action;
import pkowalski.utils.PropertySelector;
import pkowalski.utils.ReadOnlyPropertySelector;
import pkowalski.utils.Utils;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-04-28
 * Time:    21:55:58
 *
 */

public class RtcpRuntimeGenerator {

    private String _outputDirectory;

    String getOutputDirectory() {
        return _outputDirectory;
    }

    public static void main(String args[])
    {
        if (args.length < 2){
            System.out.println("Usage: java -jar rtcpSimulatorGenerator.jar file.xml simulatorName");
            System.exit(1);
        }
        String xmlFileName = args[0];
        String simulatorName = args[1];
        
        
        

        RtcpRuntimeGenerator rtcpRuntimeGenerator = new RtcpRuntimeGenerator();
        try {
            rtcpRuntimeGenerator.GenerateSimulator(
                    xmlFileName,
                    simulatorName
            );
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());            
        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        } catch (LoaderException e) {
            System.out.println("Loading error: " + e.getMessage());
        } catch (GenerationException e) {
            System.out.println("Generation error: " + e.getMessage());
        }


    }

    private final RtcpNetFactory _rtcpNetFactory;
    private CodeFactory _codeFactory;


    public RtcpRuntimeGenerator(){
        // Add your code here:
        super();
        _rtcpNetFactory = new RtcpNetFactory();
    }



    void GenerateSimulator(String modelXmlFile, String simulatorName) throws IOException, LoaderException, GenerationException {
        File xmlFile = new File(modelXmlFile);
        if (xmlFile.exists()){
            System.out.println("Loading RTCP-net model from XML file...");
        }
        else
            throw new FileNotFoundException(xmlFile.getAbsolutePath());
        
        NetLoader netLoader = new NetLoader();


        String _simulatorName;
        _simulatorName = simulatorName;
        _codeFactory = new JavaCodeFactory(_simulatorName);
        _outputDirectory = _simulatorName;

        RtcpNet rtcpNet;

        rtcpNet = netLoader.LoadFromFile(xmlFile);

        System.out.println("RTCP-net model loaded.");

        
        GenerateSimulatorCode(rtcpNet);

    }


    private void DeleteDirectory(File dir) throws IOException {
        List<File> files = Arrays.asList(dir.listFiles());
        
        for (File file : files) {
            if (file.isDirectory())
                DeleteDirectory(file);

            if (file.exists())
                if (!file.delete())
                    throw new IOException("Unable to delete old file or directory: " + file.getAbsolutePath());
        }
        if (dir.exists())
            if( !dir.delete())
                throw new IOException("Unable to delete old file or directory: " + dir.getAbsolutePath());
    }



    private void GenerateSimulatorCode(RtcpNet rtcpNet) throws IOException, GenerationException {
        System.out.println("Generating code");
        ConvertToNonHierarchical(rtcpNet);

        //printRtcpNet(rtcpNet);

        File outputDir = new File(getOutputDirectory());
        if(outputDir.exists()){
            
            DeleteDirectory(outputDir);
        }
        

        System.out.println("Generating tokens' files...");
        GenerateTokens(rtcpNet.getColors());

        Page finalPage = rtcpNet.getPages().get(0);

        final List<Place> places = finalPage.getPlaces();
        final List<Transition> transitions = finalPage.getTransitions();
        final List<Arc> arcs = new ArrayList<Arc>();

        try {
            Utils.ForEach(places, new Action<Place>() {
                @Override
                public void PerformAction(Place place) {
                    arcs.addAll(place.getArcs());
                }
            });
        } catch (Exception e) {
            throw new GenerationException(e);
        }

        System.out.println("Generating places' files...");
        GeneratePlaces(places);

        System.out.println("Generating transitions' files...");
        GenerateTransitions(transitions);

        System.out.println("Generating arcs' files...");
        GenerateArcs(arcs);

        System.out.println("Generating simulator's code file...");
        GenerateSim(places, transitions, arcs);

        PrepareExecutable();
    }

    private void PrepareExecutable() throws IOException {

        _codeFactory.PrepareExecutable(getOutputDirectory());
    }

    private void GenerateSim(List<Place> places, List<Transition> transitions, List<Arc> arcs) throws IOException, GenerationException {

        SaveCodeFile(
                _codeFactory.GenerateSimulator(places, transitions, arcs),
                _codeFactory.GenerateSimulatorFileName()
        );
    }

    private void GenerateArcs(List<Arc> arcs) throws IOException, GenerationException {

        for(Arc arc : arcs){
            SaveCodeFile(
                    _codeFactory.GenerateArc(arc),
                    _codeFactory.GenerateArcFileName(arc)
            );
        }
    }

    private void GenerateTransitions(List<Transition> transitions) throws IOException, GenerationException {
        for (Transition transition : transitions) {
            SaveCodeFile(
                    _codeFactory.GenerateTransitionBinding(transition),
                    _codeFactory.GenerateTransitionBindingFileName(transition)
            );
            SaveCodeFile(
                    _codeFactory.GenerateTransition(transition),
                    _codeFactory.GenerateTransitionFileName(transition)
            );
        }
    }

    private void GeneratePlaces(List<Place> places) throws IOException, GenerationException {

        for (Place place : places) {
            SaveCodeFile(
                    _codeFactory.GeneratePlace(place),
                    _codeFactory.GeneratePlaceFileName(place)
            );
        }
    }

    private void GenerateTokens(List<Color> colors) throws IOException {

        for (Color color : colors) {
            if (color instanceof EnumColor){
                SaveCodeFile(
                    _codeFactory.GenerateEnum((EnumColor)color),
                    _codeFactory.GenerateEnumFileName((EnumColor)color)
                );
                SaveCodeFile(
                    _codeFactory.GenerateEnumToken((EnumColor)color),
                    _codeFactory.GenerateTokenFileName(color)
                );
                
            }
            if (color instanceof BoolColor){
                SaveCodeFile(
                        _codeFactory.GenerateBoolToken((BoolColor) color),
                        _codeFactory.GenerateTokenFileName(color)
                );
            }
            if (color instanceof IntColor){
                SaveCodeFile(
                        _codeFactory.GenerateIntToken((IntColor) color),
                        _codeFactory.GenerateTokenFileName(color)
                );
            }
            if (color instanceof ProductColor){

                SaveCodeFile(
                        _codeFactory.GenerateProductToken((ProductColor) color),
                        _codeFactory.GenerateTokenFileName(color)
                );
            }
        }
    }

    private void SaveCodeFile(String code, String fileName) throws IOException {
        System.out.println("  >> Generating code file: " + fileName);
        File outputDir = new File(getOutputDirectory());

        if (!outputDir.exists()){
            if(!outputDir.mkdir())
                throw new IOException("Unable to create output directory: " + outputDir.getAbsolutePath());
        }

        outputDir = new File(outputDir, "src");
        if (!outputDir.exists()){
            if(!outputDir.mkdir())
                throw new IOException("Unable to create source directory: " + outputDir.getAbsolutePath());
        }

        File codeFile = new File(outputDir, fileName);
        if(!codeFile.createNewFile())
            throw new IOException("Unable to create new code file: " + codeFile.getAbsolutePath());
        Writer writer = new FileWriter(codeFile);
        writer.write(code);
        writer.flush();
        writer.close();

        
    }

       


    private void ConvertToNonHierarchical(RtcpNet rtcpNet) {
        System.out.println(">> Converting model to nonhierarchical...");
        for (Page page : rtcpNet.getRootPages()) {
            ConvertToNonHierarchical(page);
        }

        System.out.println("Building final page...");
        BuildFinalPage(rtcpNet);

        System.out.println("Fusion places merging...");
        FusionMerge(rtcpNet);
    }

    private void BuildFinalPage(RtcpNet rtcpNet){
        Page finalPage = _rtcpNetFactory.BuildPage("FinalPage", -1);
        
        for (Page rootPage : rtcpNet.getRootPages()) {
            for (Place place : rootPage.getPlaces()) {
                place.setPage(finalPage);
                finalPage.getPlaces().add(place);
            }

            for (Transition transition : rootPage.getTransitions()) {

                finalPage.getTransitions().add(transition);
            }
        }

        rtcpNet.getPages().clear();

        rtcpNet.getPages().add(finalPage);
        

    }

    private void FusionMerge(RtcpNet rtcpNet){

        Page finalPage = rtcpNet.getPages().get(0);
        


        for (Fusion fusion : rtcpNet.getFusions()) {
            System.out.println(String.format(
                    ">> Building fusion place: %s",
                    fusion.getName()
            ));
            Place place = _rtcpNetFactory.BuildPlace(fusion.getPatternPlace());
            place.setName(fusion.getName());

            for (Place inFusionPlace : fusion.getPlaces()) {
                for (Arc arc : inFusionPlace.getArcs()) {
                    arc.setPlace(place);
                }
                place.getArcs().addAll(inFusionPlace.getArcs());
                inFusionPlace.getArcs().clear();

            }

            finalPage.getPlaces().removeAll(fusion.getPlaces());
            finalPage.getPlaces().add(place);

        }

        rtcpNet.getFusions().clear();


        System.out.println("Renaming names to unique...");
        MakeNamesUnique(finalPage);

        //printRtcpNet(rtcpNet);
    }

    private void MakeNamesUnique(Page page){

        Map<String, Set<Place>> places = Utils.GroupBy(page.getPlaces(), new ReadOnlyPropertySelector<Place, String>(){
            @Override
            public String getPropertyValue(Place target) {
                return target.getName();
            }
        });

        Map<String, Set<Transition>> transitions = Utils.GroupBy(page.getTransitions(), new ReadOnlyPropertySelector<Transition, String>() {
            @Override
            public String getPropertyValue(Transition target) {
                return target.getName();
            }
        });

        for (Map.Entry<String, Set<Place>> placeEntry : places.entrySet()) {
            if (placeEntry.getValue().size() > 1){
                System.out.println(String.format(">> Found %s places with name \'%s\'. New names:", placeEntry.getValue().size(), placeEntry.getKey()));
                int c = 1;
                for (Place place : placeEntry.getValue()) {
                    String newName = String.format("%sX%s", placeEntry.getKey(), c);
                    place.setName(newName);
                    System.out.println(String.format("  >> \'%s\'", newName));
                    c++;
                }
            }
        }

        for (Map.Entry<String, Set<Transition>> transitionEntry : transitions.entrySet()) {
            if (transitionEntry.getValue().size() > 1){
                System.out.println(String.format(">> Found %s transitions with name \'%s\'. New names:", transitionEntry.getValue().size(), transitionEntry.getKey()));
                int c = 1;
                for (Transition transition : transitionEntry.getValue()) {
                    String newName = String.format("%sX%s", transitionEntry.getKey(), c);
                    transition.setName(newName);
                    System.out.println(String.format("  >> \'%s\'", newName));
                    c++;
                }
            }
        }
    }


    private void _MakeNamesUnique(Page page){
        RemoveNumbersFromEnd(page.getPlaces(), new PropertySelector<Place, String>(){
            @Override
            public void setPropertyValue(Place target, String value) {
                target.setName(value);
            }

            @Override
            public String getPropertyValue(Place target) {
                return target.getName();
            }
        });

        RemoveNumbersFromEnd(page.getTransitions(), new PropertySelector<Transition, String>(){
            @Override
            public void setPropertyValue(Transition target, String value) {
                target.setName(value);
            }

            @Override
            public String getPropertyValue(Transition target) {
                return target.getName();
            }
        });
        
        Collections.sort(page.getPlaces(), new Comparator<Place>(){
            @Override
            public int compare(Place place, Place place1) {
                return place.getName().compareToIgnoreCase(place1.getName());
            }
        });
        Collections.sort(page.getTransitions(), new Comparator<Transition>(){
            @Override
            public int compare(Transition transition, Transition transition1) {
                return transition.getName().compareToIgnoreCase(transition1.getName());
            }
        });
        
        Map<String,  Set<Place>> groupedPlaces =
            Utils.GroupBy(page.getPlaces(), new ReadOnlyPropertySelector<Place, String>(){
                @Override
                public String getPropertyValue(Place target) {
                    return target.getName();
                }
            });

        Map<String,  Set<Transition>> groupedTransitions =
            Utils.GroupBy(page.getTransitions(), new ReadOnlyPropertySelector<Transition, String>(){
                @Override
                public String getPropertyValue(Transition target) {
                    return target.getName();
                }
            });

        for (Map.Entry<String, Set<Place>> entry : groupedPlaces.entrySet()) {
            if (entry.getValue().size() > 1){
                int counter = 1;
                for (Place place : entry.getValue()) {
                    place.setName(entry.getKey() + counter);
                    counter++;
                }
            }
        }

        for (Map.Entry<String, Set<Transition>> entry : groupedTransitions.entrySet()) {
            if (entry.getValue().size() > 1){
                int counter = 1;
                for (Transition place : entry.getValue()) {
                    place.setName(entry.getKey() + counter);
                    counter++;
                }
            }
        }


    }

    private <T> void RemoveNumbersFromEnd(List<T> list, PropertySelector<T, String> propertySelector){
        Pattern pattern = Pattern.compile("(.+)([0-9]+)$");

        for (T o : list) {
            String value = propertySelector.getPropertyValue(o);
            Matcher matcher = pattern.matcher(value);
            if(matcher.matches()){
                value = matcher.group(1);
                propertySelector.setPropertyValue(o, value);
            }
            
        }
    }





// --Commented out by Inspection START (31.07.10 15:45):
    private void printRtcpNet(RtcpNet rtcpNet){
        for (Page page : rtcpNet.getPages()) {
            System.out.println("Page: " + page);
            for (Place place : page.getPlaces()) {
                System.out.println("- Place: " + place);
                for (Arc arc : place.getArcs()) {
                    System.out.println("-- Arc: " + arc);
                }
            }
            for (Transition transition : page.getTransitions()) {
                System.out.println("- Transition: " + transition);
            }
        }
    }
// --Commented out by Inspection STOP (31.07.10 15:45)



    private void ConvertToNonHierarchical(Page page) {

        List<Page> pages = CollectPostOrder(page);

        for (Page currentPage : pages) {
            if (!currentPage.isRootPage())
                MoveToSuperPage(currentPage);
        }
        
    }

    private void MoveToSuperPage(Page page) {

        Page superPage = page.getSuperPage();

        System.out.println(String.format(
                "      >> Moving places, transitions and arcs from page %s to superpage %s...",
                page.getName(),
                superPage.getName()
        ));

        // Podstawiane przejście i jego łuki muszą zostać usunięte z nadstrony
        Transition subsitutedTransition = page.getSubstitutedTransition();
        System.out.println(String.format("    >> Removing substituted transition %s from superpage",subsitutedTransition.getName()));
        for (Arc substitutedArc : subsitutedTransition.getArcs()) {
            substitutedArc.getPlace().getArcs().remove(substitutedArc);
            substitutedArc.setPlace(null);
            substitutedArc.setTransition(null);
        }
        subsitutedTransition.getArcs().clear();
        superPage.getTransitions().remove(subsitutedTransition);


        // Przeniesienie wszystkich przejść na nadstronę:
        System.out.println("    >> Moving transitions to superpage:");
        for (Transition transition : page.getTransitions()) {
            
            superPage.getTransitions().add(transition);
            System.out.println("      >> Transition: " + transition.getName());
        }
        page.getTransitions().clear();

        

        // Przeniesienie wszystkich miejsc które nie są portami na nadstronę:
        System.out.println("    >> Moving nonport places to superpage:");
        for (Place place : page.getPlaces()) {
            if (!page.isPort(place)){
                place.setPage(superPage);
                superPage.getPlaces().add(place);
                System.out.println("      >> Place: " + place.getName());
            }
        }
        page.getPlaces().removeAll(superPage.getPlaces());

        // Przepięcie łuków wg przypisań
        System.out.println("    >> Reconnecting arcs:");
        for (Assignment assignment : page.getAssignments()) {
            Place socket = assignment.getSocketPlace();
            Place port = assignment.getPortPlace();
            System.out.println(String.format(
                    "      >> Connecting socket %s with port %s...",
                    socket.getName(),
                    port.getName()
            ));
            for (Arc arc : port.getArcs()) {
                arc.setPlace(socket);
                socket.getArcs().add(arc);
            }

            port.getArcs().clear();
        }

        page.getPlaces().clear();
        page.setSubstitutedTransition(null);

        superPage.getSubPages().remove(page);

    }



    private List<Page> CollectPostOrder(Page page){
        List<Page> pages = new ArrayList<Page>();

        for (Page currentPage : page.getSubPages()) {
            pages.addAll(CollectPostOrder(currentPage));
        }

        pages.add(page);

        return pages;
    }
}
