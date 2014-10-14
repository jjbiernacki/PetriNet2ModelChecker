package pkowalski.rtcp.model.expression;

import pkowalski.rtcp.model.Variable;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-16
 * Time:    21:52:27
 *
 */
public interface Expression {
    public ExpressionNode getExpressionTree();
    public List<Variable> getVariables();
}
