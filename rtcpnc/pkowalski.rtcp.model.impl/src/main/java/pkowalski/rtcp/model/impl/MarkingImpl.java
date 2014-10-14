package pkowalski.rtcp.model.impl;

import pkowalski.rtcp.model.Marking;
import pkowalski.rtcp.model.MarkingItem;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-10
 * Time:    13:31:13
 *
 */
public class MarkingImpl implements Marking{


    public MarkingImpl(){
        // Add your code here:
        super();
        _itemsSet = new HashSet<MarkingItem>();
    }


    private final HashSet<MarkingItem> _itemsSet;
    @Override
    public Set<MarkingItem> getItemsSet() {
        return _itemsSet;
    }

    @Override
    public boolean equals(Object other){
        if (other == null)
            return false;

        if (! (other instanceof MarkingImpl))
            return false;


        MarkingImpl otherMarking = (MarkingImpl)other;

        if (this.getItemsSet().size() != otherMarking.getItemsSet().size())
            return false;

        for (MarkingItem markingItem : _itemsSet) {
            boolean hasMirror = false;
            for (MarkingItem otherMarkingItem : otherMarking.getItemsSet()) {
                if (markingItem.equals(otherMarkingItem)){
                    hasMirror = true;
                    break;
                }
            }

            if (!hasMirror)
                return false;

        }
        
        return true;

    }
}
