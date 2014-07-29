/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.cpn2rtcpn;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 *
 * @author asia
 */
public class XmlWriter 
{
    
    StringBuilder writer = null;
    
    private static ArrayList<String> declaredColorsStrings;
    private static ArrayList<String> declaredVarsStrings;
    private static ArrayList<Page> pageList;
    
    public XmlWriter(ArrayList<String> declaredColorsStrings, ArrayList<String> declaredVarsStrings, ArrayList<Page> pageList)
    {
        this.declaredColorsStrings=declaredColorsStrings;
        this.declaredVarsStrings=declaredVarsStrings;
        this.pageList=pageList;
    }


    
    public String writeXML()
    {
        writer = new StringBuilder();
        writeHeader();
        writeRTCPNet();
        return writer.toString();
    }
    
    private void writeHeader()
    {
       writer.append("<?xml version=\"1.0\" encoding=\"ISO-8859-2\" ?>\n");
    }
    
    private void writeRTCPNet()
    {
       writer.append("<rtcpnet name=\"\">\n");
       writeDeclarations();
       writePages();
       writeHierarchy();
       writer.append("</rtcpnet>");
    }
   
    private void writeDeclarations()
    {
       writer.append("<declarations>\n");
       writeDeclarationsContent();
       writer.append("</declarations>\n");
    }
    
    private void writeDeclarationsContent()
    {
       for(int i=0; i<declaredColorsStrings.size(); i++)
         writer.append(declaredColorsStrings.get(i).replace("colset", "color")+"\n");
       for(int i=0; i<declaredVarsStrings.size(); i++)
         writer.append(declaredVarsStrings.get(i)+"\n");
    }
    
    private void writePages()
    {
       writer.append("<pages>\n");
       for(int i=0; i<pageList.size(); i++)
           writePage(pageList.get(i));
       writer.append("</pages>\n");
    }
    
    private void writePage(Page page)
    {
        String s1="<page name=\"";
        String s2="\" width=\"1512\" height=\"1512\">\n";

        writer.append(s1+page.getName()+s2);
        writer.append("<places>\n");
        for(int i=0; i<page.getPlacesList().size(); i++) {
            Place p=page.getPlacesList().get(i);
            writePlace(p);
        }
        writer.append("</places>\n");
        writer.append("<transitions>\n");
        for(int i=0; i<page.getTransList().size(); i++) {
            Transition t=page.getTransList().get(i);
            writeTransition(t);
        }
        writer.append("</transitions>\n");
        writer.append("<arcs>\n");
        for(int i=0; i<page.getArcList().size(); i++) {
            Arc a=page.getArcList().get(i);
            a.setPlaceEnd(page.returnPlaceName(a.getPlaceEndId()));
            a.setTransEnd(page.returnTransName(a.getTransEndId()));
            writeArc(a);
        }
        writer.append("</arcs>\n");
        writer.append("</page>\n");

    }
    
    private void writePlace(Place p) 
    {
      writer.append("<place name=\""+p.getText()+"\" type=\""+p.getType()+"\" marking=\""+p.getInitmark()+
                   "\" time=\""+p.getTime()+"\" fusion=\""+p.getFusion()+"\">\n");
      String inf="<icon x=\"0\" y=\"0\" width=\"0\" height=\"0\">\n"+"<nameLabel x=\"0\" y=\"0\"/>\n"+
                   "<typeLabel x=\"0\" y=\"0\"/>\n"+"<markingLabel x=\"0\" y=\"0\"/>\n</icon>\n";
      writer.append(inf);
      writer.append("</place>\n");
    }
    
    private void writeTransition(Transition t)
    {
      String guard=t.getCondition();
      if(t.getCondition().contains("<"))
           guard=t.getCondition().replace("<", "&lt;");
      String subst="no";
      if(t.getSubst()!=null)
          subst="yes";
      writer.append("<transition name=\""+t.getName()+"\" priority=\""+t.getPrioroty()+"\" guard=\""+
              guard+"\" substituted=\""+subst+"\">\n");
      writer.append("<icon x=\"0\" y=\"0\" width=\"0\" height=\"0\">\n" +
                 "<nameLabel x=\"0\" y=\"0\"/>\n" +
                 "<guardLabel x=\"0\" y=\"0\"/>\n" +
                 "</icon>\n");
      writer.append("</transition>\n");
    }
    
    private void writeArc(Arc a)  
    {
          writer.append("<arc place=\""+a.getPlaceEnd()+"\" transition=\""+a.getTransEnd()+"\" direction=\""+a.getDirection()+
                        "\" inExpression=\""+a.getInExpression()+"\"  outExpression=\""+a.getOutExpression()+"\" inTime=\""+a.getInTime()+"\" outTime=\""+a.getOutTime()+"\">\n");  
          writer.append("<icon x=\"0\" y=\"0\">\n" +
                     "</icon>\n");
          writer.append("</arc>\n");
    }
    
    private void writeHierarchy()
    {
        writer.append("<hierarchy>\n");
        writer.append("<nodes>\n");
        writeNodes();
        writer.append("</nodes>\n");
        writer.append("<connections>\n");
        writeConnections();
        writer.append("</connections>\n");
        writer.append("</hierarchy>\n");
    }
    
    private void writeNodes()
    {
        for(int i=0; i<pageList.size(); i++)
        {
        writer.append("<node name=\""+pageList.get(i).getName()+"\" id=\""+i+"\" parameters=\"\">\n<icon x=\"0\" y=\"0\" width=\"0\" height=\"0\">\n"
                + "<nameLabel x=\"0\" y=\"0\"/>\n"
                + "<parametersLabel x=\"0\" y=\"0\"/>\n"
                + "</icon>\n"
                + "</node>\n");

        }
    }    

    
    private void writeConnections()
    {
        for(int i=0; i<pageList.size(); i++)
        {
            if(!pageList.get(i).returnSubpages().isEmpty())
            {
                for(int j=0; j<pageList.get(i).returnSubpages().size(); j++) {
                    String subpageId=(pageList.get(i).returnSubpages().get(j).returnSubPage());
                    int l=pageIndexOnList(subpageId);
                     writer.append("<connection superpageId=\""+i+"\" subpageId=\""+
                                  l+"\" transition=\""+
                                  pageList.get(i).returnTransName(pageList.get(i).returnSubpages().get(j).returnTransition())+
                                  "\" assignments=\"\">\n");
                     writer.append("<icon transitionX=\"305\" transitionY=\"150\" assignmentsX=\"217\" assignmentsY=\"127\">\n</icon>\n</connection>\n");
                }

            }
        }
    }
    
    private String getPageName(String pageid)
    {
        for(int i=0; i<pageList.size(); i++)
        {
            if(pageList.get(i).getId().equals(pageid))
                return pageList.get(i).getName();
        }
        return null;
    }
    
    private int pageIndexOnList(String subpage)
    {
        for(int i=0; i<pageList.size(); i++)
        {
            if(pageList.get(i).getId().equals(subpage))
                return i;
        }
        return -1;
    }
}
