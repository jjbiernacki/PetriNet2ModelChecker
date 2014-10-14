package pkowalski.utils;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 * WEAIiE, AGH Kraków
 * pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-07-01
 * Time:    20:31:45
 */
public interface ReadOnlyPropertySelector<TargetType, PropertyType> {
    public PropertyType getPropertyValue(TargetType target);
}
