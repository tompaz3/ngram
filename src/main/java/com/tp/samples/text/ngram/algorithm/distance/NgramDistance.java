package com.tp.samples.text.ngram.algorithm.distance;

/**
 * Ngram distance declaration.
 *
 * @author Tomasz Pazdziurek
 */
public interface NgramDistance {

    /**
     * Calculates text distance for the given strings.
     *
     * @param s1 first string.
     * @param s2 second string.
     * @return value representing text distance.
     */
    double calculateDistance(String s1, String s2);
}
