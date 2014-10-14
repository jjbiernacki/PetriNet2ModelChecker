package pkowalski.rtcp.model.expression;

import pkowalski.rtcp.model.Color;
import pkowalski.rtcp.model.Variable;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-13
 * Time:    20:04:42
 *
 */
public interface ExpressionNode {
    public List<ExpressionNode> getSubNodes();

    public NodeType getNodeType();

    /*
     * Only if getNodeType() == NodeType.CONSTANT_INTEGER
     *
     */
    public Integer getConstantInteger();

    /*
     * Only if getNodeType() == NodeType.OPERATOR
     */
    public OperatorType getOperatorType();

    /*
     * Only if getNodeType() == NodeType.VARIABLE
     *
     */
    public Variable getVariable();

    /*
     * Only if getNodeType() == NodeType.VARIABLE or NodeType.EXPRESSION
     * 
     */
    public Color getColor();

    public ExpressionResultType getExpressionResultType();

    public ExpressionType getExpressionType();
}
