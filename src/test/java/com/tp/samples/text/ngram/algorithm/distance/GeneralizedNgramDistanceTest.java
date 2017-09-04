package com.tp.samples.text.ngram.algorithm.distance;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link GeneralizedNgramDistance} test.
 *
 * @author Tomasz Pazdziurek
 */
public class GeneralizedNgramDistanceTest {

    /**
     * {@link GeneralizedNgramDistance#calculateDistance(String, String)} test.
     */
    @Test
    public void testCalculateDistance() {
        GeneralizedNgramDistance ngram = new GeneralizedNgramDistance(2);
        double distance = ngram.calculateDistance("VODKA", "VOTKA");
        assertThat(distance).isEqualByComparingTo(0.73);
    }
}
