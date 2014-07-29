package pl.edu.agh.cpn2rtcpn;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author asia
 */
public class XmlParse {

    /**
     * @param args the command line arguments
     */
    static Document document;
    static ArrayList<String> declaredColorsStrings;
    static ArrayList<String> declaredVarsStrings;
    static ArrayList<Page> pageList;

    public static void main(String[] argv) 
    {
        if (argv.length != 1)
        {
            System.err.println("arguments: inputfile");
            System.exit(1);
        }
        new XmlParse().parse(argv[0]);
        
    }

    private String parse(String inputfile) {


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try
        {
            DocumentBuilder builder = factory.newDocumentBuilder();
            File f = new File(inputfile);
            if(f.exists()==false)
            {
                System.out.println("Brak pliku wejściowego\n");
                System.exit(0);
            }
            document = builder.parse(f);

            declaredColorsStrings=new ArrayList<String>();
            declaredVarsStrings=new ArrayList<String>();
            pageList=new ArrayList<Page>();
            NodeList docChildren=document.getChildNodes();
            Node workspaceElements=docChildren.item(1);
            NodeList workspaceElementsChildren=workspaceElements.getChildNodes();
            Node cpnet=null;
            for(int i=0; i<workspaceElementsChildren.getLength(); i++)
                if("cpnet".equals(workspaceElementsChildren.item(i).getNodeName()))
                    cpnet=workspaceElementsChildren.item(i);
            NodeList cpnetChildren=cpnet.getChildNodes();
            Node globbox=null;
            ArrayList<Node> pages=new ArrayList<Node>();
            Node instances=null;
            Node options=null;
            Node binders=null;
            Node monitorblock=null;
            for(int i=0; i<cpnetChildren.getLength(); i++)
            {
                if("globbox".equals(cpnetChildren.item(i).getNodeName()))
                {
                    globbox=cpnetChildren.item(i);
                    //break;
                }
                if("page".equals(cpnetChildren.item(i).getNodeName()))
                {
                    pages.add(cpnetChildren.item(i));
                    //break;
                }
                if("instances".equals(cpnetChildren.item(i).getNodeName()))
                {
                    instances=cpnetChildren.item(i);
                    //break;
                }
                if("options".equals(cpnetChildren.item(i).getNodeName()))
                {
                    options=cpnetChildren.item(i);
                    //break;
                }
                if("binders".equals(cpnetChildren.item(i).getNodeName()))
                {
                    binders=cpnetChildren.item(i);
                    //break;
                }
            }

            //GLOBBOX
            Node blockPriorities=null;
            Node blockDeclarations=null;
            NodeList globboxChildren=globbox.getChildNodes();
            for(int i=0; i<globboxChildren.getLength(); i++)
            {
                if("block".equals(globboxChildren.item(i).getNodeName()))
                {
                    NodeList globboxChildrenChildren=globboxChildren.item(i).getChildNodes();
                    for(int ii=0; ii<globboxChildrenChildren.getLength(); ii++)
                    {
                        if("id".equals(globboxChildrenChildren.item(ii).getNodeName()))
                        {
                            if(globboxChildrenChildren.item(ii).getFirstChild().getTextContent().matches("Standard priorities"))
                                blockPriorities=globboxChildren.item(i);
                            else if(globboxChildrenChildren.item(ii).getFirstChild().getTextContent().matches("Standard declarations"))
                                blockDeclarations=globboxChildren.item(i);
                        }
                    }
                }
            }
            //deklaracje - kolory i zmienne
            NodeList blockDeclarationsChildren=blockDeclarations.getChildNodes();
            ArrayList<Node> declaredColors=new ArrayList<Node>();
            ArrayList<Node> declaredVars=new ArrayList<Node>();
            for(int i=0; i<blockDeclarationsChildren.getLength(); i++)
            {
                if("color".equals(blockDeclarationsChildren.item(i).getNodeName()))
                    declaredColors.add(blockDeclarationsChildren.item(i));
                else if("var".equals(blockDeclarationsChildren.item(i).getNodeName()))
                    declaredVars.add(blockDeclarationsChildren.item(i));
            }
            for(int i=0; i<declaredColors.size(); i++)
            {
                Node n=declaredColors.get(i);
                NodeList children=n.getChildNodes();
                for(int ii=0; ii<children.getLength(); ii++)
                {
                    if("layout".equals(children.item(ii).getNodeName()))
                        declaredColorsStrings.add(children.item(ii).getFirstChild().getTextContent());
                }
            }
            for(int i=0; i<declaredVars.size(); i++)
            {
                Node n=declaredVars.get(i);
                NodeList children=n.getChildNodes();
                for(int ii=0; ii<children.getLength(); ii++)
                {
                    if("layout".equals(children.item(ii).getNodeName()))
                        declaredVarsStrings.add(children.item(ii).getFirstChild().getTextContent());
                }
            }

            //PAGES
            for(int i=0; i<pages.size(); i++)
            {
                //PAGE
                Node page=pages.get(i);
                String pageid=(((Element)page).getAttribute("id"));
                Node pageName=null;
                String pageNameString="";
                ArrayList<Place> places=new ArrayList<Place>();
                ArrayList<Transition> transitions=new ArrayList<Transition>();
                ArrayList<Arc> arcs=new ArrayList<Arc>();
                ArrayList<Node> placesNodes=new ArrayList<Node>();
                ArrayList<Node> transNodes=new ArrayList<Node>();
                ArrayList<Node> arcNodes=new ArrayList<Node>();
                ArrayList<Subpage> subpages=new ArrayList<Subpage>();
                NodeList pageChildren=page.getChildNodes();
                for(int ii=0; ii<pageChildren.getLength(); ii++)
                {
                    if(pageChildren.item(ii).getNodeName().equals("pageattr"))
                      pageName=pageChildren.item(ii);
                    else if(pageChildren.item(ii).getNodeName().equals("place"))
                      placesNodes.add(pageChildren.item(ii));
                    else if(pageChildren.item(ii).getNodeName().equals("trans"))
                        transNodes.add(pageChildren.item(ii));
                    else if(pageChildren.item(ii).getNodeName().equals("arc"))
                        arcNodes.add(pageChildren.item(ii));
                }
                //page name
                pageNameString=(((Element)pageName).getAttribute("name"));
                //places
                for(int ii=0; ii<placesNodes.size(); ii++)
                {
                    //place
                    Node place=placesNodes.get(ii);
                    String placeId=(((Element)place).getAttribute("id"));
                    String text="";
                    String type="";
                    String initMark="";
                    String fusion="";
                    NodeList placeChildren=place.getChildNodes();
                    for(int j=0; j<placeChildren.getLength(); j++)
                    {
                       if(placeChildren.item(j).getNodeName().equals("text"))
                           text=placeChildren.item(j).getTextContent();
                       else if(placeChildren.item(j).getNodeName().equals("type"))
                       {
                           NodeList placeChildrenChildren=placeChildren.item(j).getChildNodes();
                           for(int jj=0; jj<placeChildrenChildren.getLength(); jj++)
                               if(placeChildrenChildren.item(jj).getNodeName().equals("text"))
                                 type=placeChildrenChildren.item(jj).getTextContent();

                       }
                       else if(placeChildren.item(j).getNodeName().equals("initmark"))
                       {
                           NodeList placeChildrenChildren=placeChildren.item(j).getChildNodes();
                           for(int jj=0; jj<placeChildrenChildren.getLength(); jj++)
                               if(placeChildrenChildren.item(jj).getNodeName().equals("text"))
                                 initMark=placeChildrenChildren.item(jj).getTextContent();
                       }
                       else if(placeChildren.item(j).getNodeName().equals("fusioninfo"))
                       {
                           fusion=(((Element)placeChildren.item(j)).getAttribute("name"));
                       }

                    }
                    //place
                    Place p=new Place(placeId, text, type, initMark, fusion);
                    places.add(p);
                }
                //transitions
                for(int ii=0; ii<transNodes.size(); ii++)
                {
                    //transition
                    Node trans=transNodes.get(ii);
                    String transId=(((Element)trans).getAttribute("id"));
                    String text="";
                    String substr=null;
                    String priority="";
                    String condition="";
                    String subpage="";
                    NodeList transChildren=trans.getChildNodes();
                    for(int j=0; j<transChildren.getLength(); j++)
                    {
                       if(transChildren.item(j).getNodeName().equals("text"))
                           text=transChildren.item(j).getTextContent();
                       else if(transChildren.item(j).getNodeName().equals("priority"))
                       {
                           NodeList placeChildrenChildren=transChildren.item(j).getChildNodes();
                           for(int jj=0; jj<placeChildrenChildren.getLength(); jj++)
                               if(placeChildrenChildren.item(jj).getNodeName().equals("text"))
                                 priority=placeChildrenChildren.item(jj).getTextContent();
                       }
                       else if(transChildren.item(j).getNodeName().equals("cond"))
                       {
                           NodeList placeChildrenChildren=transChildren.item(j).getChildNodes();
                           for(int jj=0; jj<placeChildrenChildren.getLength(); jj++)
                              if(placeChildrenChildren.item(jj).getNodeName().equals("text"))
                               {
                                 condition=placeChildrenChildren.item(jj).getTextContent();
                                 if(condition.length()>0)
                                 if(condition.charAt(condition.length()-1)==']' && condition.charAt(0)=='[')
                                 {condition=condition.substring(1, condition.length()-1);
                                 }

                               }
                       }
                       else if(transChildren.item(j).getNodeName().equals("subst"))
                       {
                           subpage=(((Element)transChildren.item(j)).getAttribute("subpage"));
                           Subpage subp=new Subpage(transId, subpage);
                           subpages.add(subp);
                           substr=subpage;
                       }
                    }
                    //transition
                    Transition t=new Transition(transId, text, substr, priority, condition);
                    transitions.add(t);
                }
                //arcs
                for(int ii=0; ii<arcNodes.size(); ii++)
                {
                    //arc
                    Node arc=arcNodes.get(ii);
                    String transend="";
                    String placeend="";
                    String annot="";
                    String direction="";
                    direction=(((Element)arc).getAttribute("orientation"));
                    NodeList arcChildren=arc.getChildNodes();
                    for(int j=0; j<arcChildren.getLength(); j++)
                    {
                       if(arcChildren.item(j).getNodeName().equals("transend"))
                       {
                            transend=((Element)arcChildren.item(j)).getAttribute("idref");
                       }
                       else if(arcChildren.item(j).getNodeName().equals("placeend"))
                       {
                           placeend=((Element)arcChildren.item(j)).getAttribute("idref");
                       }
                       else if(arcChildren.item(j).getNodeName().equals("annot"))
                       {
                           NodeList arcChildrenChildren=arcChildren.item(j).getChildNodes();
                           for(int jj=0; jj<arcChildrenChildren.getLength(); jj++)
                               if(arcChildrenChildren.item(jj).getNodeName().equals("text"))
                                    annot=arcChildrenChildren.item(jj).getTextContent();
                       }
                    }
                    //arc
                    Arc ar=new Arc(transend, placeend, direction, annot);
                    arcs.add(ar);


                }
            //page
            Page pag=new Page(pageid, pageNameString, places, transitions, arcs, subpages);
            pageList.add(pag);
            }

        }


        catch (SAXParseException spe)
        {
            // Error generated by the parser
            System.out.println("\n** Parsing error" + ", line " +
                spe.getLineNumber() + ", uri " + spe.getSystemId());
            System.out.println("   " + spe.getMessage());

            // Use the contained exception, if any
            Exception x = spe;

            if (spe.getException() != null) {
                x = spe.getException();
            }

            x.printStackTrace();
        } catch (SAXException sxe) {
            // Error generated during parsing)
            Exception x = sxe;

            if (sxe.getException() != null) {
                x = sxe.getException();
            }

            x.printStackTrace();
        } catch (ParserConfigurationException pce) {
            // Parser with specified options can't be built
            pce.printStackTrace();
        } catch (IOException ioe) {
            // I/O error
            ioe.printStackTrace();
        }

        XmlWriter xmlw=new XmlWriter(declaredColorsStrings, declaredVarsStrings, pageList);
        return xmlw.writeXML();
    }


}
