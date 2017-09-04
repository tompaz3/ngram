package com.tp.samples.text.ngram.algorithm.utils;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link AlgorithmUtils} tests.
 *
 * @author Tomasz Pazdziurek
 */
public class AlgorithmUtilsTest {

    /**
     * {@link AlgorithmUtils#maxLength(String, String)} test.
     */
    @Test
    public void testMaxLength() {
        int length = AlgorithmUtils.maxLength("VODKA", "VOTKAA");
        assertThat(length).isEqualByComparingTo(6);
    }

    /**
     * {@link AlgorithmUtils#shorter(String, String)} test.
     */
    @Test
    public void testShorter() {
        String shorter = AlgorithmUtils.shorter("VODKA", "VOTKAA");
        assertThat(shorter.length()).isEqualByComparingTo(5);
    }

    /**
     * {@link AlgorithmUtils#countNgramOccurrences(List, String)} test.
     */
    @Test
    public void testCountNgramOccurences() {
        int count = AlgorithmUtils.countNgramOccurrences(Lists.newArrayList("VO", "OD", "DK", "KA"), "VODKA");
        assertThat(count).isEqualByComparingTo(4);
        int count2 = AlgorithmUtils.countNgramOccurrences(Lists.newArrayList("VO", "OT", "TK", "KA"), "VODKA");
        assertThat(count2).isEqualByComparingTo(2);
    }

}
