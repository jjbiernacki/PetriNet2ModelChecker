package pkowalski.junit.utils;


public interface Property<TTarget,TProperty> {
    public TProperty getValue(TTarget target) throws Exception;
    public void setValue(TTarget target, TProperty value) throws Exception;

}
