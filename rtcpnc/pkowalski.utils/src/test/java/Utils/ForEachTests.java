package Utils;

import org.junit.Before;
import org.junit.Test;
import pkowalski.utils.Action;
import pkowalski.utils.Utils;


public class ForEachTests extends UtilsTest{

    private Action<Integer> action;

    @Test(expected = IllegalArgumentException.class)
    public void ActionNullTest() throws Exception {
        Utils.ForEach(list,null);
        
    }

    @Override
    @Test(expected = IllegalArgumentException.class)
    public void ListNullTest() throws Exception {
        Utils.ForEach(null, action);
        
    }

    @Override
    public void EmptyListTest() throws Exception {
        list.clear();
        Utils.ForEach(list, action);
    }

    @Override
    public void Test() throws Exception {
        Utils.ForEach(list, action);
    }

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        action = new Action<Integer>() {
            @Override
            public void PerformAction(Integer integer) throws Exception {
                
            }
        };
    }
}
