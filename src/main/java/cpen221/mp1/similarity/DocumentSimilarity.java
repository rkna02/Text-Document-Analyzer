package cpen221.mp1.similarity;

import cpen221.mp1.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;
import java.net.URL;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static java.lang.Character.isLetter;

public class DocumentSimilarity {

    /* DO NOT CHANGE THESE WEIGHTS */
    private final int WT_AVG_WORD_LENGTH      = 5;
    private final int WT_UNIQUE_WORD_RATIO    = 15;
    private final int WT_HAPAX_LEGOMANA_RATIO = 25;
    private final int WT_AVG_SENTENCE_LENGTH  = 1;
    private final int WT_AVG_SENTENCE_CPLXTY  = 4;
    private final int WT_JS_DIVERGENCE        = 50;
    /* ---- END OF WEIGHTS ------ */

    private final double[] w = new double[]{1.0, 4.0, 5.0, 15.0, 25.0};

    /* ------- Task 4 ------- */

    /**
     * Compute the Jensen-Shannon Divergence between the given documents
     * @param doc1 the first document, is not null
     * @param doc2 the second document, is not null
     * @return the Jensen-Shannon Divergence between the given documents
     */
    public double jsDivergence(Document doc1, Document doc2) {

        Set<String> words = doc1.wordAppearances(doc2);
        double sum = 0.0;

        for (String c: words) {
            double pi = doc1.Probability(c);
            double qi = doc2.Probability(c);
            double mi = (pi + qi) / 2.0;
            sum += pi * (Math.log(pi / mi) / Math.log(2.0)) + qi * (Math.log(qi / mi) / Math.log(2.0));
        }

        return sum / 2.0;
    }

    /**
     * Compute the Document Divergence between the given documents
     * @param doc1 the first document, is not null
     * @param doc2 the second document, is not null
     * @return the Document Divergence between the given documents
     */
    public double documentDivergence(Document doc1, Document doc2) {

        double firstTerm = 0.0;
        double secondTerm = WT_JS_DIVERGENCE * jsDivergence(doc1, doc2);
        double[] m1 = new double[5];
        double[] m2 = new double[5];
        m1[0] = doc1.averageSentenceLength();
        m1[1] = doc1.averageSentenceComplexity();
        m1[2] = doc1.averageWordLength();
        m1[3] = doc1.uniqueWordRatio();
        m1[4] = doc1.hapaxLegomanaRatio();
        m2[0] = doc2.averageSentenceLength();
        m2[1] = doc2.averageSentenceComplexity();
        m2[2] = doc2.averageWordLength();
        m2[3] = doc2.uniqueWordRatio();
        m2[4] = doc2.hapaxLegomanaRatio();

        for (int i = 0; i < 5; i++) {
            firstTerm += w[i] * Math.abs((m1[i] - m2[i]));
        }

        return firstTerm + secondTerm;
    }

}