package pkowalski.utils;


public interface Action <Target> {
    @SuppressWarnings({"RedundantThrows"})
    public void PerformAction(Target target) throws Exception;    
}
