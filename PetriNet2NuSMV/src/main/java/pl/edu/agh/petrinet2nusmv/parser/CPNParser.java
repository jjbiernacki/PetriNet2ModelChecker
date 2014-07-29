package pl.edu.agh.petrinet2nusmv.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pl.edu.agh.petrinet2nusmv.exceptions.SyntaxException;
import pl.edu.agh.petrinet2nusmv.logger.Logger;
import pl.edu.agh.petrinet2nusmv.model.color.*;
import pl.edu.agh.petrinet2nusmv.utils.MarkingParser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Parser pliku .kts do obiektu klasy grafu osiągalności ReachabilityGraph
 * @author abiernacka, jbiernacki
 *
 */
public class CPNParser {

    private List<Color> colors = new ArrayList<Color>();
    private List<Place> places = new ArrayList<Place>();
    private List<SSNode> ssNodes = new ArrayList<SSNode>();

	public ReachabilityGraph parseFile(final String filepath) throws IOException, SyntaxException, ParserConfigurationException, SAXException {
        File fXmlFile = new File(filepath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setValidating(false);
        dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        NodeList cpnets = doc.getElementsByTagName("cpnet");
        Node cpnet = cpnets.item(0);
        NodeList cpnetChildren = cpnet.getChildNodes();
        findColors(cpnetChildren);

        Logger.d("Colors:");
        for(Color c: colors) {
            Logger.d(c.getName());
        }
        findPlaces(cpnetChildren);

        for(Place p: places) {
            Logger.d(p.toString());
        }

        findSSNodes(cpnetChildren);
        conectSSNodes(cpnetChildren);


        for(SSNode s: ssNodes) {
            parseSSNodeContent(s);
        }
        for(SSNode s: ssNodes) {
            Logger.d(s.toString());
        }


        ReachabilityGraph reachabilityGraph = new ReachabilityGraph();
        reachabilityGraph.setPlaces(places);
        TreeSet<SSNode> states = new TreeSet<SSNode>();
        states.addAll(ssNodes);
        reachabilityGraph.setStates(states);
        return reachabilityGraph;

	}

    private void parseSSNodeContent(SSNode ssNode) throws SyntaxException {
        String[] tmpLines = ssNode.getText().split("\n");
        ArrayList<String> lines = new ArrayList<String>();
        String newLine = "";
        for(int i = 1; i < tmpLines.length; i++) {
            String lineToCheck = tmpLines[i];
            if(lineToCheck.contains("'")) {
                if(newLine.length() > 0) {
                    lines.add(newLine);
                }
                newLine = lineToCheck;
            } else {
                newLine += lineToCheck;
            }
        }
        if(newLine.length() > 0) {
            lines.add(newLine);
        }

        for(String line: lines) {
            line = line.replaceFirst("^.*'", "");
            //System.out.println(line);
            String textContainingPlaceName = line.substring(0, line.indexOf(':'));
            String placeName = textContainingPlaceName.substring(0, textContainingPlaceName.lastIndexOf(" "));
            Place place = findPlaceByName(placeName);
           // System.out.println(place);
            Marking marking = new Marking();
            if(place == null) throw new SyntaxException("SSNode has invalid place" + line);
            String textContainingMarking = line.substring(line.indexOf(':') + 2);

            if(textContainingMarking.equals("empty")) {
                continue;
            }
            String[] markingText = textContainingMarking.split("\\+\\+");

            for(String markPart: markingText) {
                //System.out.println("MarkPart" + markPart);
                String[] counterAndValue = markPart.split("`");
                long value = Long.valueOf(counterAndValue[0]);
                String key = MarkingParser.getMarkingKey(counterAndValue[1], place.getColor().getColorType());
                marking.addMarking(key, value);
            }
            ssNode.addMarking(place, marking);
        }
    }

    private Place findPlaceByName(String placeName) {
        for(Place p: places) {
            if(placeName.equals(p.getName())) {
                return p;
            }
        }
        return null;
    }

    private void conectSSNodes(NodeList cpnetChildren) throws SyntaxException {
        for (int i = 0; i < cpnetChildren.getLength(); i++) {
            Node nNode = cpnetChildren.item(i);
            if (nNode.getNodeName().equals("page")) {
                NodeList ssarcList = ((Element)nNode).getElementsByTagName("ssarc");
                for (int j = 0; j < ssarcList.getLength(); j++) {
                    Element ssarc = (Element) ssarcList.item(j);
                    SSNode src = findSSNodeById(((Element) ssarc.getElementsByTagName("source").item(0)).getAttribute("idref"));
                    SSNode dst = findSSNodeById(((Element) ssarc.getElementsByTagName("destination").item(0)).getAttribute("idref"));
                    if(src != null && dst != null) {
                        src.addSuccessor(dst);
                    } else {
                        throw new SyntaxException("SSArc has no src or dst");
                    }
                }
            }
        }
    }

    private SSNode findSSNodeById(String id) {
        for(SSNode s: ssNodes) {
            if(s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }


    private void findSSNodes(NodeList cpnetChildren) throws SyntaxException {
        for (int i = 0; i < cpnetChildren.getLength(); i++) {
            Node nNode = cpnetChildren.item(i);
            if (nNode.getNodeName().equals("page")) {
                NodeList ssnodesList = ((Element)nNode).getElementsByTagName("ssnode");
                for (int j = 0; j < ssnodesList.getLength(); j++) {
                    Element ssnodeEl = (Element) ssnodesList.item(j);
                    SSNode ssNode = new SSNode();
                    ssNode.setId(ssnodeEl.getAttribute("id"));
                    ssNode.setOrder(Long.valueOf(ssnodeEl.getAttribute("number")));
                    ssNode.setText(ssnodeEl.getElementsByTagName("text").item(0).getTextContent());
                    ssNodes.add(ssNode);
                }
            }
        }
    }


    private void findPlaces(NodeList cpnetChildren) throws SyntaxException {
        for (int i = 0; i < cpnetChildren.getLength(); i++) {
            Node nNode = cpnetChildren.item(i);
            if (nNode.getNodeName().equals("page")) {
                NodeList placesList = ((Element)nNode).getElementsByTagName("place");
                for (int j = 0; j < placesList.getLength(); j++) {
                    Element placeNode = (Element) placesList.item(j);
                    Place place = new Place();
                    place.setName(placeNode.getElementsByTagName("text").item(0).getTextContent());
                    Color c = findColor(((Element) placeNode.getElementsByTagName("type").item(0)).getElementsByTagName("text").item(0).getTextContent());
                    if(c == null) throw new SyntaxException("Place " +place.getName() + " has no color set!");
                    place.setColor(c);
                    places.add(place);
                }
            }
        }
    }

    private Color findColor(String name) {
        for(Color c: colors) {
            if(c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }


    private void findColors(NodeList cpnetChildren) throws SyntaxException {
        for (int i = 0; i < cpnetChildren.getLength(); i++) {
            Node nNode = cpnetChildren.item(i);
            if (nNode.getNodeName().equals("globbox")) {
                NodeList globboxChildren = nNode.getChildNodes();
                for (int j = 0; j < globboxChildren.getLength(); j++) {
                    Node gbNode = globboxChildren.item(j);
                    if (gbNode.getNodeName().equals("color")) {
                        addColor((Element)gbNode);

                    } else if(gbNode.getNodeName().equals("block")) {
                        NodeList blockChildren = gbNode.getChildNodes();
                        for (int p = 0; p < blockChildren.getLength(); p++) {
                            Node blockNode = blockChildren.item(p);
                            if (blockNode.getNodeName().equals("color")) {
                                addColor((Element)blockNode);

                            }
                        }
                    }
                }
            }
        }
    }

    private void addColor(Element colorNode) throws SyntaxException {
        Color color = new Color();
        //find name:
        color.setName(colorNode.getElementsByTagName("id").item(0).getTextContent());
        //find type:
        if(colorNode.getElementsByTagName("int") != null && colorNode.getElementsByTagName("int").getLength() > 0) {
            color.setColorType(ColorType.INTEGER);
        } else if(colorNode.getElementsByTagName("enum") != null && colorNode.getElementsByTagName("enum").getLength() > 0) {
            color.setColorType(ColorType.ENUM);
        } else if(colorNode.getElementsByTagName("product") != null && colorNode.getElementsByTagName("product").getLength() > 0) {
            color.setColorType(ColorType.PRODUCT);
        } else if(colorNode.getElementsByTagName("string") != null && colorNode.getElementsByTagName("string").getLength() > 0) {
            color.setColorType(ColorType.STRING);
        } else if(colorNode.getElementsByTagName("unit") != null && colorNode.getElementsByTagName("unit").getLength() > 0) {
            color.setColorType(ColorType.UNIT);
        } else if(colorNode.getElementsByTagName("bool") != null && colorNode.getElementsByTagName("bool").getLength() > 0) {
            color.setColorType(ColorType.BOOL);
        } else if(colorNode.getElementsByTagName("index") != null && colorNode.getElementsByTagName("index").getLength() > 0) {
            color.setColorType(ColorType.INDEX);
        }  else if(colorNode.getElementsByTagName("intinf") != null && colorNode.getElementsByTagName("intinf").getLength() > 0) {
            color.setColorType(ColorType.INTINF);
        }  else if(colorNode.getElementsByTagName("real") != null && colorNode.getElementsByTagName("real").getLength() > 0) {
            color.setColorType(ColorType.REAL);
        }  else if(colorNode.getElementsByTagName("time") != null && colorNode.getElementsByTagName("time").getLength() > 0) {
            color.setColorType(ColorType.TIME);
        } else {
            if(colorNode.getElementsByTagName("alias") != null && colorNode.getElementsByTagName("alias").getLength() > 0) {
                Element alias = (Element)colorNode.getElementsByTagName("alias").item(0);
                String aliasName = alias.getElementsByTagName("id").item(0).getTextContent();
                Color aliasColor = findColor(aliasName);
                if(aliasColor == null) {
                    throw new SyntaxException("Color alias not recognized = " + aliasName);
                } else {
                    color.setColorType(aliasColor.getColorType());
                }
            } else {
                throw new SyntaxException("Color not recognized " + colorNode.getAttribute("id"));
            }
        }
        colors.add(color);
    }


}
