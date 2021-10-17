package cpen221.mp1.similarity;

import cpen221.mp1.Document;
import java.util.ArrayList;
import java.util.HashSet;
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

        Set<String> words = this.wordAppearances(doc1, doc2); //union of words from doc1 with that of doc2
        double sum = 0.0;

        for (String w: words) {
            double pi = this.Probability(doc1,w);

            double qi = this.Probability(doc2,w);
            double mi = (pi + qi) / 2.0;

            double exp1=0.0;
            double exp2=0.0;


            exp1 = (pi == 0) ? 0.0 : pi * (Math.log(pi / mi) / Math.log(2.0));

            exp2 = (qi == 0) ? 0.0 : qi * (Math.log(qi / mi) / Math.log(2.0));

            sum += exp1 + exp2;
        }

        return sum/2;
    }


    /**
     * Compute the probability of a word appearing in the document
     * @param word which is a word
     * @param document which is a Document
     * @return The probability of the word appearing in the document
     */
    private double Probability(Document document, String word) {

        ArrayList<String> wordArrayList = new ArrayList<String>(); //get the words in document
        for(int i=1; i<=document.numSentences(); i++){
            for(String wor : document.getSentence(i).split(" ")){
                if(filter(wor) != ""){
                    wordArrayList.add(filter(wor));
                }
            }
        }

        double count = 0.0;

        for (String s: wordArrayList) { //find the number of its occurrences
            if (word.equals(s)) {
                count = count + 1.0;
            }
        }
        return count / wordArrayList.size();
    }

    /**
     * Stores all the words appearing in both documents in a set
     * @param doc1, the first Document, not null
     * @param doc2, second Document, not null
     * @return A set containing all the words from doc1 and doc2
     */
    private Set<String> wordAppearances(Document doc1, Document doc2) {
        Set<String> wordAppearances = new HashSet<>();

        for(int i=1; i<=doc1.numSentences(); i++){  //add words from doc1
            for(String wor : doc1.getSentence(i).split(" ")){
                if(filter(wor) != ""){
                    wordAppearances.add(filter(wor));
                }
            }
        }

        for(int i=1; i<=doc2.numSentences(); i++){ //add words from doc2
            for(String wor : doc2.getSentence(i).split(" ")){
                if(wor.length() != 0){
                    if(filter(wor) != ""){
                        wordAppearances.add(filter(wor));
                    }
                }
            }
        }

        return wordAppearances;
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

    /**
     * Helper method which filters an input string to remove garbage characters as per MP1 requirements
     * In my implementation, ######hello is a valid filtered string.
     * @param raw which is the unfiltered input String
     * @return a new String with the filtered String
     */
    private String filter(String raw) {

        boolean poundFlag = true;
        boolean allBad = true;

        StringBuilder sb = new StringBuilder(raw);

        int frontInd = 0;
        int rearInd = 0;

        for(int i=raw.length(); i>0; i--){ //scan from rear
            char ch = raw.charAt(i-1);
            if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')) {
                rearInd = i-1;
                allBad = false;
                break;
            }
        }

        if(allBad) { //if all punctuation then return empty
            return "";
        }

        for(int i=0; i<raw.length(); i++){ //scan from front
            char ch = raw.charAt(i);
            if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9') || (ch == '#')) {
                frontInd = i;
                break;
            }
        }

        for(int i=frontInd; i<=rearInd; i++){


            char ch = raw.charAt(i);

            if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')) {
                poundFlag = true;
                break;
            }
            else {
                poundFlag = false;
            }

        }

        if(poundFlag == false){
            return "";
        }

        return sb.substring(frontInd, rearInd+1).toString().toLowerCase();

    }

}