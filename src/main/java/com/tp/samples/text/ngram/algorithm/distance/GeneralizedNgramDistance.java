package com.tp.samples.text.ngram.algorithm.distance;

import com.tp.samples.text.ngram.algorithm.utils.AlgorithmUtils;

import java.math.BigDecimal;
import java.util.List;

import static com.tp.samples.text.ngram.algorithm.utils.AlgorithmUtils.DEFAULT_DIVISION_SCALE;
import static com.tp.samples.text.ngram.algorithm.utils.AlgorithmUtils.DEFAULT_ROUNDING_MODE;

/**
 * Implementation of generalized Ngram version - compares strings using sets from 1-element to n-elements.
 *
 * @author Tomasz Pazdziurek
 */
public class GeneralizedNgramDistance implements NgramDistance {

    private static final BigDecimal GENERALIZED_NUMERATOR = BigDecimal.valueOf(2);

    private final NgramSetGenerator ngramSetGenerator = NgramSetGenerator.getInstance();
    private int divisionScale = DEFAULT_DIVISION_SCALE;

    public GeneralizedNgramDistance() {

    }

    public GeneralizedNgramDistance(int divisionScale) {
        this.divisionScale = divisionScale;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateDistance(String s1, String s2) {
        final int maxLength = AlgorithmUtils.maxLength(s1, s2);
        final double ngramsCount = ngramsCount(maxLength);
        final int intersectionCount = calcBoundIntersectionCount(s1, s2);
        return GENERALIZED_NUMERATOR.multiply(BigDecimal.valueOf(intersectionCount))
                .divide(BigDecimal.valueOf(ngramsCount), divisionScale, DEFAULT_ROUNDING_MODE)
                .doubleValue();
    }

    private double ngramsCount(int maxLength) {
        return Math.pow(maxLength, 2) + maxLength;
    }

    private int calcBoundIntersectionCount(String s1, String s2) {
        final String shorter = AlgorithmUtils.shorter(s1, s2);
        final String longer = shorter.equals(s1) ? s2 : s1;

        int count = 0;
        for (int i = 0; i < shorter.length(); i++) {
            List<String> ngramList = ngramSetGenerator.createNgram(shorter, i);
            count += AlgorithmUtils.countNgramOccurrences(ngramList, longer);
        }
        return count;
    }
}
