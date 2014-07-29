package pl.edu.agh.cpn2rtcpn;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asia
 */
public class Place 
{
    private String id;
    private String text;
    private String type;
    private String fusion;
    private String initmark;
    private String time;
    
    public Place(String id, String text, String type, String initmark_, String fusion)
    {
        this.id=id;
        this.text=text;
        this.type=type;
        this.initmark=initmark_;
        if(initmark.startsWith("("))
            this.initmark=initmark.replace("(", "1(");
        this.fusion=fusion;
        this.time="0";
        if(initmark.contains("@"))
        {
                int index=initmark.indexOf("@"); 
                time=initmark.substring(index+1, initmark.length());
                initmark=initmark.substring(0, index);  
        }  
        initmark=initmark.trim();
    }
    
    public String getId()
    {
        return id;
    }
        
    public String getText()
    {
        return text;
    }
    
    public String getType()
    {
        return type;
    }
    
    public String getInitmark()
    {
        return initmark;
    }
    
    public String getFusion()
    {
        return fusion;
    }
    
    public String getTime()
    {
        return time;
    }
}
