package pl.edu.agh.cpn2rtcpn;

import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asia
 */
public class Page 
{
    private String id;
    private String name;
    private ArrayList<Place> placesList;
    private ArrayList<Transition> transList;
    private ArrayList<Arc> arcList;
    private ArrayList<Subpage> subpages;
    
    public Page(String pageid, String name, ArrayList<Place> pageList, ArrayList<Transition> transList, ArrayList<Arc> arcList, ArrayList<Subpage> subpages)
    {
        this.id=pageid;
        this.name=name;
        this.placesList=pageList;
        this.transList=transList;
        this.arcList=arcList;
        this.subpages=subpages;
    }
    
    public String getId()
    {
        return id;
    }
        
    public String getName()
    {
        return name;
    }
    
    public ArrayList<Place> getPlacesList()
    {
        return placesList;
    }
    
    public ArrayList<Transition> getTransList()
    {
        return transList;
    }
    
    public ArrayList<Arc> getArcList()
    {
        return arcList;
    }
    
    public String returnPlaceName(String placeId)
    {
        for(Place place: placesList)
            if(place.getId().equals(placeId))
                return place.getText();
        return null;
    }
    
    public String returnTransName(String transId)
    {
        for(Transition trans: transList)
            if(trans.getId().equals(transId))
                return trans.getName();
        return null;
    }
    
    public ArrayList<Subpage> returnSubpages()
    {
        return subpages;
    }
}
