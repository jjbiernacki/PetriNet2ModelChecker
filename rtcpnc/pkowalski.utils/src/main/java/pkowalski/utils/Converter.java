package pkowalski.utils;


public interface Converter<TIn, TOut> {
    @SuppressWarnings({"RedundantThrows"})
    public TOut Convert(TIn in) throws Exception;
}
