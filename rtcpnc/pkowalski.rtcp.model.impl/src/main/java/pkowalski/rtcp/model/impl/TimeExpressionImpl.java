package pkowalski.rtcp.model.impl;

import pkowalski.rtcp.model.TimeExpression;
import pkowalski.rtcp.model.expression.ExpressionNode;
import pkowalski.rtcp.model.impl.expression.impl.ExpressionImpl;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-16
 * Time:    22:43:57
 *
 */
class TimeExpressionImpl extends ExpressionImpl implements TimeExpression{

    public TimeExpressionImpl(String rawExpressionString, ExpressionNode expressionTree) {
        // Add your code here:
        super(rawExpressionString, expressionTree);
        
    }

}
