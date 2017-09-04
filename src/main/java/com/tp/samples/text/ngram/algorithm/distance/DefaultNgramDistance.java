package com.tp.samples.text.ngram.algorithm.distance;

import com.tp.samples.text.ngram.algorithm.utils.AlgorithmUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.tp.samples.text.ngram.algorithm.utils.AlgorithmUtils.DEFAULT_DIVISION_SCALE;
import static com.tp.samples.text.ngram.algorithm.utils.AlgorithmUtils.DEFAULT_ROUNDING_MODE;

/**
 * N-gram default implementation. Implements the base, standard algorithm version,
 * where sets of given n-gram length are generated for two strings and compared.
 *
 * @author Tomasz Pazdziurek
 */
public class DefaultNgramDistance implements NgramDistance {

    private int divisionScale = DEFAULT_DIVISION_SCALE;
    private int ngramLength;
    private final NgramSetGenerator ngramSetGenerator = NgramSetGenerator.getInstance();

    public DefaultNgramDistance(int ngramLength) {
        this.ngramLength = ngramLength;
    }

    public DefaultNgramDistance(int divisionScale, int ngramLength) {
        this(ngramLength);
        this.divisionScale = divisionScale;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateDistance(String s1, String s2) {
        final int maxLength = AlgorithmUtils.maxLength(s1, s2);
        final int ngramsCount = maxLength - ngramLength + 1;
        final int intersectCount = calcIntersectionCount(s1, s2, ngramLength);
        final BigDecimal ngramsCountBD = BigDecimal.valueOf(ngramsCount);
        final BigDecimal intersectCountBD = BigDecimal.valueOf(intersectCount);
        return intersectCountBD.divide(ngramsCountBD, divisionScale, DEFAULT_ROUNDING_MODE).doubleValue();
    }

    private int calcIntersectionCount(String s1, String s2, int ngramLength) {
        List<String> s1Ngram = ngramSetGenerator.createNgram(s1, ngramLength);
        List<String> s2Ngram = ngramSetGenerator.createNgram(s2, ngramLength);
        return intersectCount(s1Ngram, s2Ngram);
    }

    private int intersectCount(List<String> s1Ngrams, List<String> s2Ngrams) {
        if (s1Ngrams == null || s1Ngrams.isEmpty() || s2Ngrams == null || s2Ngrams.isEmpty()) {
            return 0;
        }
        AtomicInteger count = new AtomicInteger(0);
        List<String> ngrams1 = new ArrayList<>(s1Ngrams);
        List<String> ngrams2 = new ArrayList<>(s2Ngrams);
        intersect(ngrams1, ngrams2, count);
        intersect(ngrams2, ngrams1, count);
        return count.get();
    }

    private void intersect(List<String> ngrams1, List<String> ngrams2, AtomicInteger count) {
        Iterator<String> iterator = ngrams1.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (ngrams2.contains(s)) {
                iterator.remove();
                ngrams2.remove(s);
                count.incrementAndGet();
            }
        }
    }
}
