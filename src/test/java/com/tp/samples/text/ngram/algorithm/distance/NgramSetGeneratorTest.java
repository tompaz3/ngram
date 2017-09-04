package com.tp.samples.text.ngram.algorithm.distance;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link NgramSetGenerator} tests.
 *
 * @author Tomasz Pazdziurek
 */
public class NgramSetGeneratorTest {

    /**
     * {@link NgramSetGenerator#createNgram(String, int)} test.
     */
    @Test
    public void testCreateNgram() {
        NgramSetGenerator generator = NgramSetGenerator.getInstance();
        List<String> ngramSets = generator.createNgram("VODKA", 3);
        assertThat(ngramSets).containsOnly("VOD", "ODK", "DKA");
    }
}
