package pl.edu.agh.cpn2rtcpn;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asia
 */
public class Transition 
{
    private String id;
    private String name;
    private String subst;
    private String condition;
    private String priority;
    
    public Transition(String id, String text, String subst, String priority, String condition)
    {
        this.id=id;
        this.name=text;
        this.subst=subst;
        this.condition=condition;
        this.priority=priority;
    }
        
    public String getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getSubst()
    {
        return subst;
    }
        
    public String getCondition()
    {
        return condition;
    }
    
    public String getPrioroty()
    {
        if(priority=="")
            return "0";
        return priority;
    }
    
}
