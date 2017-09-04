package com.tp.samples.text.ngram.algorithm.distance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class responsible for N-gram algorithm sets generation.
 *
 * @author Tomasz Pazdziurek
 */
final class NgramSetGenerator {

    private static final NgramSetGenerator INSTANCE = new NgramSetGenerator();

    private NgramSetGenerator() {
    }

    public static NgramSetGenerator getInstance() {
        return INSTANCE;
    }

    /**
     * Generates N-gram sets for given {@code ngramLength}.
     *
     * @param text        the text which N-gram sets are based on.
     * @param ngramLength length of N-gram sets (1 - unigram, 2 - bigram, 3 - trigram, etc.).
     * @return list representing N-gram sets of {@code ngramLength} length created for the given {@code text}.
     */
    List<String> createNgram(String text, int ngramLength) {
        if (text == null || text.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> ngramList = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            // loop for N-gram sets generation
            ngramCalculation:
            {
                StringBuilder currNgram = new StringBuilder();
                // iterates over characters at indexes prior to the current 'i' index
                for (int ng = ngramLength - 1; ng >= 0; ng--) {
                    int idx = i - ng;
                    if (text.length() > idx && idx >= 0) {
                        currNgram.append(text.charAt(idx));
                    } else { // do not create N-gram set for indexes prior to the current 'i' index, that do not exist
                        break ngramCalculation;
                    }
                }
                ngramList.add(currNgram.toString());
            }
        }
        return ngramList;
    }
}
