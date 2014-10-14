package pkowalski.rtcp.model.syntax;

import org.antlr.runtime.tree.Tree;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-29
 * Time:    16:15:43
 *
 */
class CompactTree implements Tree{


    public CompactTree(int type, String text){
        // Add your code here:
        super();
        _type = type;
        _text = text;
        Init();
    }


    private void Init(){
        _children = new ArrayList<Tree>();
    }
    private List<Tree> _children;

    @Override
    public Tree getChild(int i) {
        return _children.get(i);
    }

    @Override
    public int getChildCount() {
        return _children.size();
    }

    @Override
    public void addChild(Tree tree) {
        _children.add(tree);
    }

    @Override
    public boolean isNil() {
        throw new NotImplementedException();
    }

    @Override
    public int getTokenStartIndex() {
        throw new NotImplementedException();
    }

    @Override
    public void setTokenStartIndex(int i) {
        throw new NotImplementedException();
    }

    @Override
    public int getTokenStopIndex() {
        throw new NotImplementedException();
    }

    @Override
    public void setTokenStopIndex(int i) {
        throw new NotImplementedException();
    }

    @Override
    public Tree dupTree() {
        throw new NotImplementedException();
    }

    @Override
    public Tree dupNode() {
        throw new NotImplementedException();
    }

    private final int _type;

    @Override
    public int getType() {
        return _type;
    }

    private final String _text;

    @Override
    public String getText() {
        return _text;
    }

    @Override
    public int getLine() {
        throw new NotImplementedException();
    }

    @Override
    public int getCharPositionInLine() {
        throw new NotImplementedException();
    }

    @Override
    public String toStringTree() {
        throw new NotImplementedException();
    }
}
