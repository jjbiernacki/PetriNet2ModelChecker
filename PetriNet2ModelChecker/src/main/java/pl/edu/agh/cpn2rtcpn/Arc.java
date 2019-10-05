/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.cpn2rtcpn;


/**
 *
 * @author asia
 */
public class Arc 
{
    private String transend;
    private String placeend;
    private String transendName;
    private String placeendName;
    private String direction; 
    private String inExpr="";
    private String outExpr="";
    private String inTime="";
    private String outTime="";
    
    public Arc(String transend, String placeend, String direction, String expr) 
    {
        this.transend=transend;
        this.placeend=placeend;
        this.direction=direction;
        if(expr.contains("|"))
        {
            int index=expr.indexOf("|");
            inExpr=expr.substring(0, index);
            outExpr=expr.substring(index+1, expr.length());
            if(inExpr.contains("@"))
            {
                int index2=inExpr.indexOf("@"); 
                inTime=inExpr.substring(index2+1, inExpr.length());
                inExpr=inExpr.substring(0, index2);    
            }
            if(outExpr.contains("@"))
            {
                int index2=outExpr.indexOf("@"); 
                outTime=outExpr.substring(index2+1, outExpr.length());
                outExpr=outExpr.substring(0, index2);               
            }            
        }
        else if(direction.equals("PtoT"))   //out
        {
            outExpr=expr;
            if(outExpr.contains("@"))
            {
                int index2=outExpr.indexOf("@"); 
                outTime=outExpr.substring(index2+1, outExpr.length());
                outExpr=outExpr.substring(0, index2);               
            }  
        }
        else
        {
            inExpr=expr; 
            if(inExpr.contains("@"))
            {
                int index2=inExpr.indexOf("@"); 
                inTime=inExpr.substring(index2+1, inExpr.length());
                inExpr=inExpr.substring(0, index2);               
            }  
        }
        inExpr=inExpr.trim();
        outExpr=outExpr.trim();
        inTime=inTime.trim();
        outTime=outTime.trim();
        try
        {
        if(inExpr.contains("("))
        {
            int index=inExpr.indexOf("(");
            if(index!=0)
                if((inExpr.charAt(index-1))>=48 && (inExpr.charAt(index-1))<=57) //cyfra
                {
                    throw new InputErrorException("invalid arc marking");
                }
        }
        if(outExpr.contains("("))
        {
            int index=outExpr.indexOf("(");
            if(index!=0)
                if((outExpr.charAt(index-1))>=48 && (outExpr.charAt(index-1))<=57) //cyfra
                {
                    throw new InputErrorException("invalid arc marking");
                }
        }
        }
        catch(InputErrorException e)
        {
            e.printMessage();
            System.exit(0);
        }
    }
    
    public String getTransEnd()
    {
        return transendName;
    }
    
    public String getPlaceEnd()
    {
        return placeendName;
    }
    
    public String getTransEndId()
    {
        return transend;
    }
    
    public String getPlaceEndId()
    {
        return placeend;
    }
    
    public String getDirection()
    {
        if(direction.equals("BOTHDIR"))
            return "In/Out";
        else if(direction.equals("PtoT"))
            return "Out";
        return "In";
    }
        
    public String getInExpression()
    {
        return inExpr;
    }
    
    public String getOutExpression()
    {
        return outExpr;
    }
    
    public String getInTime()
    {
        return inTime;
    }
    
    public String getOutTime()
    {
        return outTime;
    }
    
    public void setPlaceEnd(String placename)
    {
        placeendName=placename;
    }
        
    public void  setTransEnd(String transname)
    {
        transendName=transname;
    }
}
