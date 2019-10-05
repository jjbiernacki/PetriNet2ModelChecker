/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.cpn2rtcpn;

/**
 *
 * @author asia
 */
public class Subpage
{
   
    private String transition;
    private String subpage;   
    
    public Subpage(String transition, String page)
    {
        this.transition=transition;
        this.subpage=page;
    }
    
    public String returnTransition()
    {
        return transition;
    }
    
    public String returnSubPage()
    {
        return subpage;
    }
}
