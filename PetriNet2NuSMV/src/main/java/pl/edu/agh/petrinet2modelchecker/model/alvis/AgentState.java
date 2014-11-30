package pl.edu.agh.petrinet2modelchecker.model.alvis;

import pl.edu.agh.petrinet2modelchecker.helper.NuXmvValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DeeperBlue on 2014-11-29.
 */
public class AgentState {

    private String name;
    private Map<String, Am> amStates = new HashMap<String, Am>();
    private Map<String, Integer> pcStates = new HashMap<String, Integer>();
    private List<String> ci = new ArrayList<String>();
    private Map<String, ArrayList<String>> ciStates = new HashMap<String, ArrayList<String>>();
    private Map<Integer, ArrayList<String>> pvValues = new HashMap<Integer, ArrayList<String>>();
    private Map<String, ArrayList<String>> pvStates = new HashMap<String, ArrayList<String>>();

    public AgentState(String agentName) {
        name = agentName;

    }

    public String getName() {
        return name;
    }

    public void addAm(String stateId, Am am) {
        amStates.put(stateId, am);
    }

    public void addPc(String stateId, Integer pc) {
        pcStates.put(stateId, pc);
    }

    public void addCi(String stateId, ArrayList<String> ciList) {
        if (ciList == null) {
            return;
        }
        for (String ci : ciList) {
            if (!this.ci.contains(ci)) {
                this.ci.add(ci);
            }
        }
        ciStates.put(stateId, ciList);
    }

    public void addPv(String stateId, ArrayList<String> pvList) {
        if (pvList == null) {
            return;
        }
        for (int i = 0; i < pvList.size(); i++) {
            if (pvValues.containsKey(i)) {
                ArrayList<String> value = pvValues.get(i);
                for (String pv : pvList) {
                    if (!value.contains(pv)) {
                        value.add(pv);
                    }
                }
            } else {
                pvValues.put(i, pvList);
            }
        }
        pvStates.put(stateId, pvList);
    }

    public Map<String, Am> getAmStates() {
        return amStates;
    }

    public Map<String, Integer> getPcStates() {
        return pcStates;
    }

    public List<String> getCi() {
        return ci;
    }

    public Map<String, ArrayList<String>> getCiStates() {
        return ciStates;
    }

    public Map<Integer, ArrayList<String>> getPvValues() {
        return pvValues;
    }

    public Map<String, ArrayList<String>> getPvStates() {
        return pvStates;
    }
}
