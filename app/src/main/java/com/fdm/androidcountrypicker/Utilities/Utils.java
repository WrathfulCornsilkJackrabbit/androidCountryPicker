package com.fdm.androidcountrypicker.Utilities;

import java.util.HashMap;

/**
 * Created by foxdarkmaster on 05-05-2017.
 */

public class Utils {
    public static String getResultValue(HashMap<String, String> value) {
        return value.get(Utils.getResultKey(value));
    }

    public static String getResultKey(HashMap<String, String> value) {
        String resultKey = value.keySet()
                .toString()
                .replace("[", "")
                .replace("]", "");

        return resultKey;
    }
}
