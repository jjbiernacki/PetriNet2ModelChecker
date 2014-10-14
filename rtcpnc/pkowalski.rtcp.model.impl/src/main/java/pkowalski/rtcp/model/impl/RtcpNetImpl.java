package pkowalski.rtcp.model.impl;

import pkowalski.rtcp.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-06
 * Time:    13:46:05
 *
 */
public class RtcpNetImpl implements RtcpNet{
    public RtcpNetImpl() {
        _colors = new ArrayList<Color>();
        _variables = new ArrayList<Variable>();
        _pages = new ArrayList<Page>();
        _fusions = new ArrayList<Fusion>();
    }

    private final List<Fusion> _fusions;

    @Override
    public List<Fusion> getFusions() {
        return _fusions;
    }


    private final List<Color> _colors;


    @Override
    public List<Color> getColors() {
        return _colors;
    }


    private final List<Variable> _variables;
    @Override
    public List<Variable> getVariables() {
        return _variables;
    }

    private final List<Page> _pages;
    @Override
    public List<Page> getPages() {
        return _pages;
    }

    @Override
    public List<Page> getRootPages() {
        List<Page> rootPages = new ArrayList<Page>();

        for (Page page : _pages) {
            if (page.isRootPage())
                rootPages.add(page);
        }

        return rootPages;
    }


}
