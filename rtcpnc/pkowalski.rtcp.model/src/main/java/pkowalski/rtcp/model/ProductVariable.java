package pkowalski.rtcp.model;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 *          WEAIiE, AGH Kraków
 *          pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-09
 * Time:    22:53:23
 *
 */
public interface ProductVariable extends Variable{
    public List<ScalarVariable> getValue() throws VariableNotInitialized;
}
