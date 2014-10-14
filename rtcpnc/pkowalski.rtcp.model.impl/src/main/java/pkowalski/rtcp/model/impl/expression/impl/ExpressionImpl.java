package pkowalski.rtcp.model.impl.expression.impl;

import pkowalski.rtcp.model.Variable;
import pkowalski.rtcp.model.expression.Expression;
import pkowalski.rtcp.model.expression.ExpressionNode;
import pkowalski.rtcp.model.expression.NodeType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-16
 * Time:    21:53:58
 *
 */
public abstract class ExpressionImpl implements Expression{

    protected ExpressionImpl(String rawExpressionString){
        // Add your code here:
        super();
        _rawExpressionString = rawExpressionString;
    }


    protected ExpressionImpl(String rawExpressionString, ExpressionNode expressionTree) {
        // Add your code here:
        super();
        _rawExpressionString = rawExpressionString;
        _expressionNode = expressionTree;
    }


    private ExpressionNode _expressionNode;

    @Override
    public ExpressionNode getExpressionTree() {
        return _expressionNode;
    }

    private final String _rawExpressionString;

    @Override
    public String toString(){
        return _rawExpressionString;
    }

    @Override
    public List<Variable> getVariables(){
        return ExtractVariables(getExpressionTree());
    }

    List<Variable> ExtractVariables(ExpressionNode expressionNode){
        List<Variable> variables = new ArrayList<Variable>();
        if (expressionNode.getNodeType() == NodeType.VARIABLE)
            variables.add(expressionNode.getVariable());
        
        for (ExpressionNode node : expressionNode.getSubNodes()) {
            variables.addAll(ExtractVariables(node));
        }
        
        return variables;
    }
}
