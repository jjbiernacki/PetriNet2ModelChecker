package pl.edu.agh.petrinet2nusmv.utils;

import pl.edu.agh.petrinet2nusmv.model.color.Color;
import pl.edu.agh.petrinet2nusmv.model.color.ColorType;

/**
 * Created with IntelliJ IDEA.
 * User: abiernacka
 * Date: 14.02.14
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */
public class MarkingParser {

    public static String getMarkingKey(String text, ColorType color) {
        text = text.replace("~", "-");
        switch (color) {
            case PRODUCT:
                String result1 = text.replace("(", "#");
                result1 = result1.replace(")", "#");
                result1 = result1.replace(",", "_");
                result1 = result1.replace(" ", "");
                result1 = result1.replace("\"", "");
                return result1;
            case INDEX:
                String result2 = text.replace("(", "_");
                result2 = result2.replace(")", "");
                result2 = result2.replace(" ", "");
                result2 = result2.replace("\"", "");
                return result2;
            case INTEGER:
            case ENUM:
            case INTINF:
            case TIME:
            case BOOL:
                text = text.replace(" ", "");
                text = text.replace("\"", "");
                return text;
            case UNIT:
                return "unit";
            case STRING:
                text = text.replace(" ", "");
                text = text.replace("\"", "");
                return text.replace("\"", "");
            case REAL:
                text = text.replace(" ", "");
                text = text.replace("\"", "");
                text = text.replace(".", "_");
                return text;
        }
        return null;
    }

}
