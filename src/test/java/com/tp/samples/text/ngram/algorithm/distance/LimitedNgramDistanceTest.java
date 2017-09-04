package com.tp.samples.text.ngram.algorithm.distance;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link LimitedNgramDistance} tests.
 *
 * @author Tomasz Pazdziurek
 */
public class LimitedNgramDistanceTest {

    /**
     * {@link LimitedNgramDistance#calculateDistance(String, String)} test.
     */
    @Test
    public void testCalculateDistance() {
        LimitedNgramDistance ngram = new LimitedNgramDistance(1, 5);
        double distance = ngram.calculateDistance("VODKA", "VOTKA");
        assertThat(distance).isEqualByComparingTo(0.4);
    }
}
