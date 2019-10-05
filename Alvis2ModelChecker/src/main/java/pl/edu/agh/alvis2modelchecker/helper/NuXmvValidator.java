package pl.edu.agh.alvis2modelchecker.helper;

import java.util.ArrayList;

/**
 * Created by DeeperBlue on 2014-11-29.
 */
public final class NuXmvValidator {

    public static void validName(String name) throws Exception {
        if (name == null || name.length() == 0) {
            throw new Exception("Agent name cannot be empty.");
        }
        char firstLetter = name.charAt(0);
        if (!((firstLetter >= 'a' && firstLetter <= 'z') || (firstLetter >= 'A' && firstLetter <= 'Z'))) {
            throw new Exception(String.format("Agent name cannot start with %s.", String.valueOf(firstLetter)));
        }

        for (int i = 1; i < name.length(); i++) {
            char letter = name.charAt(0);
            if (!((letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z') || (letter >= '0' && letter <= '9'))) {
                throw new Exception(String.format("Agent name cannot contain %s.", String.valueOf(letter)));
            }
        }
    }

    public static ArrayList<String> ciList(String ci) {
        String validCiList = ci.replace("[", "");
        validCiList = validCiList.replace("]", "");
        if (validCiList != null && validCiList.length() > 0) {
            ArrayList<String> tmpCiList =  new ArrayList<String>();
            int bracketCounter = 0;
            int lastCommaIndex = -1;
            for (int i = 0; i < validCiList.length(); i++) {
                char character = validCiList.charAt(i);
                if (bracketCounter == 0 && character == ',') {
                    tmpCiList.add(validCiList.substring(lastCommaIndex + 1, i));
                    lastCommaIndex = i;
                } else if (character == ')') {
                    bracketCounter--;
                } else if (character == '(') {
                    bracketCounter++;
                }
                if (i == validCiList.length() - 1) {
                    tmpCiList.add(validCiList.substring(lastCommaIndex + 1, validCiList.length()));
                }
            }
            if (tmpCiList.isEmpty()) {
                return null;
            }
            ArrayList ciList =  new ArrayList();
            for (String tmpCi: tmpCiList) {
                tmpCi = tmpCi.replace("(", "$");
                tmpCi = tmpCi.replace(")", "$");
                tmpCi = tmpCi.replace(".", "-");
                tmpCi = tmpCi.replace("|", "-");
                tmpCi = tmpCi.replace(",", "_");
                ciList.add(tmpCi);
            }
            return ciList;
        } else {
         return null;
        }
    }

    public static ArrayList<String> pvList(String pv) {
        String validPv = pv;
        if (validPv.charAt(0) == '(') {
            validPv = validPv.substring(1);
        }
        if (validPv.charAt(validPv.length() - 1) == ')') {
            validPv = validPv.substring(0, validPv.length() - 1);
        }
        String[] pvArray = validPv.split(",");

        ArrayList list = new ArrayList();
        for (String element: pvArray) {
            if (!element.isEmpty()) {
                list.add(element);
            }
        }

        return list;
    }
}
