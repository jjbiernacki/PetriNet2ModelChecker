package pkowalski.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 * WEAIiE, AGH Kraków
 * pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-07-01
 * Time:    20:38:18
 */
public abstract class Utils {
    public static <KeyType,ElementType> Map<KeyType, Set<ElementType>> GroupBy(List<ElementType> list, ReadOnlyPropertySelector<ElementType, KeyType> keySelector ){
        AssertNullParameter(list,"list");
        AssertNullParameter(keySelector, "keySelector");
        
        HashMap<KeyType, Set<ElementType>> hashmap = new HashMap<KeyType, Set<ElementType>>();

        for (ElementType e : list) {
            KeyType key = keySelector.getPropertyValue(e);
            Set<ElementType> set;
            if (hashmap.containsKey(key))
                set = hashmap.get(key);
            else{
                set = new HashSet<ElementType>();
                hashmap.put(key, set);
            }

            set.add(e);
        }

        return hashmap;
    }

    public static <Target> Map<Target, Integer> GroupByMultiplicity(List<Target> list){
        AssertNullParameter(list,"list");
        Map<Target, Integer> targetIntegerMap = new HashMap<Target, Integer>();

        for (Target target : list) {
            if (targetIntegerMap.containsKey(target)) {
                Integer count = targetIntegerMap.get(target);
                
                targetIntegerMap.remove(target);
                targetIntegerMap.put(target, count + 1);
            }
            else{
                targetIntegerMap.put(target, 1);
            }
        }

        return targetIntegerMap;
    }

    public static <Target> void ForEach(List<Target> list, Action<Target> action) throws Exception {
        AssertNullParameter(list, "list");
        AssertNullParameter(action, "action");
        for (Target target : list) {
            action.PerformAction(target);
        }
    }

    public static <Target, Key> List<Target> GetDistinct(List<Target> list, ReadOnlyPropertySelector<Target, Key> keySelector){
        AssertNullParameter(list,"list");
        AssertNullParameter(keySelector, "keySelector");
        List<Target> distinctList = new ArrayList<Target>();
        List<Key> keys = new ArrayList<Key>();
        for(Target target : list){
            Key key = keySelector.getPropertyValue(target);
            if (!keys.contains(key)){
                distinctList.add(target);
                keys.add(key);
            }
        }
        
        return distinctList;
    }

    public static <TIn, TOut> List<TOut> ConvertAll(List<TIn> list, Converter<TIn, TOut> converter) throws Exception{
        AssertNullParameter(list, "list");
        AssertNullParameter(converter, "converter");
        
        List<TOut> outList = new ArrayList<TOut>();

        for (TIn in : list) {
            outList.add(converter.Convert(in));
        }

        return outList;
    }

    private static void AssertNullParameter(Object parameter, String parameterName){
        if (null == parameter){
            String message;
            if (null == parameterName)
                message = "Parameter cannot be null.";
            else
                message = String.format("Parameter \'%s\' cannot be null.",parameterName);

            throw new IllegalArgumentException(message);
        }

    }

    public static <TIn, TOut> List<TOut> ConvertIf(List<TIn>list, Converter<TIn, TOut> converter, Func<Boolean, TIn> predicate)throws Exception{
        AssertNullParameter(list, "list");
        AssertNullParameter(converter, "converter");
        AssertNullParameter(predicate, "predicate");

        List<TOut> outList = new ArrayList<TOut>();

        for (TIn in : list) {
            if (predicate.Invoke(in))
                outList.add(converter.Convert(in));
        }

        return outList;
    }

    @SuppressWarnings({"SameParameterValue"})
    public static String JoinStrings(String separator, List<String> strings){
        AssertNullParameter(separator, "separator");
        AssertNullParameter(strings,"strings");
        StringBuilder sb = new StringBuilder();

        for (String s : strings) {
            if (sb.length()>0)
                sb.append(separator);
            sb.append(s);
        }

        return sb.toString();
    }

    public static <TTarget> List<TTarget> Where(List<TTarget> list, Func<Boolean, TTarget> predicate) throws Exception {
        AssertNullParameter(list,"list");
        AssertNullParameter(predicate,"predicate");
        
        List<TTarget> retList = new ArrayList<TTarget>();
        for(TTarget target : list){
            if (predicate.Invoke(target))
                retList.add(target);
        }
        return retList;
    }

    public static <TTarget> TTarget SingleOrDefault(List<TTarget> list, Func<Boolean, TTarget> predicate) throws Exception {
        List<TTarget> filtered = Where(list, predicate);

        switch (filtered.size()){
            case 0: return null;
            case 1: return filtered.get(0);
            default: throw new Exception("Sequence contains more than one element!");
        }


    }

    public static void CopyFile(File src, File dst) throws IOException {
        FileChannel in = null, out = null;
        try{
            in = new FileInputStream(src).getChannel();
            out = new FileOutputStream(dst).getChannel();

            long size = in.size();
            MappedByteBuffer buff = in.map(FileChannel.MapMode.READ_ONLY, 0, size);
            out.write(buff);
        }
        finally {
            in.close();
            out.close();
        }

    }

}
