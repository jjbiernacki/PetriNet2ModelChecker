package pkowalski.rtcp.model;

import pkowalski.rtcp.model.expression.Expression;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-06
 * Time:    12:51:53
 *
 */
public interface GuardExpression extends Expression{
    public boolean IsDefault();
}