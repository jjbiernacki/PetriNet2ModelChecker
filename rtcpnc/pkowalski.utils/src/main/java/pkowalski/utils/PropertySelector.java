package pkowalski.utils;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 * WEAIiE, AGH Kraków
 * pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-07-01
 * Time:    20:29:45
 */
public interface PropertySelector<TargetType, PropertyType> extends ReadOnlyPropertySelector<TargetType, PropertyType>{
    public void setPropertyValue(TargetType target, PropertyType value);
}
