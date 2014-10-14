package pkowalski.rtcp.model.impl;

import pkowalski.rtcp.model.GuardExpression;
import pkowalski.rtcp.model.expression.ExpressionNode;
import pkowalski.rtcp.model.impl.expression.impl.ExpressionImpl;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-16
 * Time:    20:08:47
 *
 */
public class GuardExpressionImpl extends ExpressionImpl implements GuardExpression{

    public GuardExpressionImpl(String rawExpressionString) {
        // Add your code here:
        super(rawExpressionString);
        _default = true;
    }


    public GuardExpressionImpl(String rawExpressionString,ExpressionNode expressionTree) {
        // Add your code here:
        super(rawExpressionString, expressionTree);
        _default = false;
    }


    private final boolean _default;

    @Override
    public boolean IsDefault() {
        return _default;
    }


}
