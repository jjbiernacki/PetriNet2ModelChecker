package pkowalski.utils;


public interface Func<TOut, TArg1> {
    @SuppressWarnings({"RedundantThrows"})
    public TOut Invoke(TArg1 arg1) throws Exception;
}
