package com.tp.samples.text.ngram.algorithm.distance;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link DefaultNgramDistance} tests.
 *
 * @author Tomasz Pazdziurek
 */
public class DefaultNgramDistanceTest {

    @Test
    public void testCalculateDistance() {
        DefaultNgramDistance ngram = new DefaultNgramDistance(2);
        double distance = ngram.calculateDistance("VODKA", "VOTKA");
        assertThat(distance).isEqualByComparingTo(0.5);
    }
}
