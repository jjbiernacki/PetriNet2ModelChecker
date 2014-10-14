package pkowalski.rtcp.runtime.coverability.comparators;

/**
 * Created by IntelliJ IDEA.
 * User: lordjim
 * Date: 11.06.11
 * Time: 14:57
 * To change this template use File | Settings | File Templates.
 */
public interface EqualityComparator <T> {
    public boolean AreEqual(T objA, T objB);
    public String getName();
}
