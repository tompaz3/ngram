package com.tp.samples.text.ngram.algorithm.utils;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Util class for N-gram algorithms.
 *
 * @author Tomasz Pazdziurek
 */
public final class AlgorithmUtils {

    /**
     * Default scale for division operations executed while calculating text distance.
     */
    public static final int DEFAULT_DIVISION_SCALE = 8;

    /**
     * Rounding mode for division operations executed while calculating text distance.
     */
    public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;

    private AlgorithmUtils() {
    }

    /**
     * Returns maximum length of two given strings.
     *
     * @param s1 string.
     * @param s2 string.
     * @return maximum length of two given strings.
     */
    public static int maxLength(String s1, String s2) {
        int s1Length = length(s1);
        int s2Length = length(s2);
        return Math.max(s1Length, s2Length);
    }

    private static int length(String str) {
        return str == null || str.isEmpty() ? 0 : str.length();
    }

    /**
     * Returns shorter string.
     *
     * @param s1 string.
     * @param s2 string.
     * @return shorter string.
     */
    public static String shorter(String s1, String s2) {
        int s1Length = length(s1);
        int s2Length = length(s2);
        return s1Length > s2Length ? s2 : s1;
    }

    /**
     * Counts occurrences of words from {@code ngramList} in given {@code text}.
     *
     * @param ngramList list of N-grams.
     * @param text      text.
     * @return occurrences no. of words from {@code ngramList} in given {@code text}.
     */
    public static int countNgramOccurrences(List<String> ngramList, String text) {
        int count = 0;
        List<String> stringList = new ArrayList<>(ngramList);
        Iterator<String> iterator = stringList.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (text.contains(s)) {
                iterator.remove();
                count++;
            }
        }
        return count;
    }
}
