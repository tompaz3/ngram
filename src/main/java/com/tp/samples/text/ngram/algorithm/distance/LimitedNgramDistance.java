package com.tp.samples.text.ngram.algorithm.distance;

import com.tp.samples.text.ngram.algorithm.utils.AlgorithmUtils;

import java.math.BigDecimal;
import java.util.List;

import static com.tp.samples.text.ngram.algorithm.utils.AlgorithmUtils.DEFAULT_DIVISION_SCALE;
import static com.tp.samples.text.ngram.algorithm.utils.AlgorithmUtils.DEFAULT_ROUNDING_MODE;

/**
 * Implements N-gram algorithm version which splits first word into
 * sets of n-grams of lengths between two given bounds.
 *
 * @author Tomasz Pazdziurek
 */
public class LimitedNgramDistance implements NgramDistance {

    private static final BigDecimal BOUNDED_NUMERATOR = BigDecimal.valueOf(2);

    private final NgramSetGenerator ngramSetGenerator = NgramSetGenerator.getInstance();

    private int divisionScale = DEFAULT_DIVISION_SCALE;
    private int lowerLimit;
    private int upperLimit;

    public LimitedNgramDistance(int lowerLimit, int upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public LimitedNgramDistance(int divisionScale, int lowerLimit, int upperLimit) {
        this(lowerLimit, upperLimit);
        this.divisionScale = divisionScale;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateDistance(String s1, String s2) {
        final int maxLength = AlgorithmUtils.maxLength(s1, s2);
        validateBounds(lowerLimit, upperLimit, maxLength);
        final int ngramsCount = boundedNgramsCount(lowerLimit, upperLimit, maxLength);
        final int intersectionCount = calcBoundIntersectionCount(s1, s2, lowerLimit, upperLimit);
        final BigDecimal intersectionCountBD = BigDecimal.valueOf(intersectionCount);
        final BigDecimal ngramsCountBD = BigDecimal.valueOf(ngramsCount);
        return BOUNDED_NUMERATOR.multiply(intersectionCountBD)
                .divide(ngramsCountBD, divisionScale, DEFAULT_ROUNDING_MODE)
                .doubleValue();
    }

    private void validateBounds(int lowerBound, int upperBound, int maxLength) {
        if (lowerBound <= 0 || lowerBound > upperBound || upperBound > maxLength) {
            throw new IllegalArgumentException(String.format("Invalid bound values: %d\t%d", lowerBound, upperBound));
        }
    }

    private int boundedNgramsCount(int lowerBound, int upperBound, int maxLength) {
        return (maxLength - lowerBound + 1) * (maxLength - lowerBound + 2) - (maxLength - upperBound + 1) * (maxLength - upperBound);
    }

    private int calcBoundIntersectionCount(String s1, String s2, int lowerBound, int upperBound) {
        final String shorter = AlgorithmUtils.shorter(s1, s2);
        final String longer = shorter.equals(s1) ? s2 : s1;

        int count = 0;
        for (int i = lowerBound; i <= upperBound; i++) {
            List<String> ngramList = ngramSetGenerator.createNgram(shorter, i);
            count += AlgorithmUtils.countNgramOccurrences(ngramList, longer);
        }
        return count;
    }
}
