package cpen221.mp1;

import cpen221.mp1.exceptions.NoSuitableSentenceException;
import cpen221.mp1.sentiments.SentimentAnalysis;

import java.net.URL;

public class Document {

    /* ------- Task 0 ------- */
    /*  all the basic things  */

    /**
     * Create a new document using a URL
     * @param docId the document identifier
     * @param docURL the URL with the contents of the document
     */
    public Document(String docId, URL docURL) {
        // TODO: Implement this constructor
    }

    /**
     *
     * @param docId the document identifier
     * @param fileName the name of the file with the contents of
     *                 the document
     */
    public Document(String docId, String fileName) {
        // TODO: Implement this constructor
    }

    /**
     * Obtain the identifier for this document
     * @return the identifier for this document
     */
    public String getDocId() {
        // TODO: Implement this method
        return null;
    }

    /* ------- Task 1 ------- */

    public double averageWordLength() {
        // TODO: Implement this method
        return 0.0;
    }

    public double uniqueWordRatio() {
        // TODO: Implement this method
        return 0.0;
    }

    public double hapaxLegomanaRatio() {
        // TODO: Implement this method
        return 0.0;
    }

    /* ------- Task 2 ------- */

    /**
     * Obtain the number of sentences in the document
     * @return the number of sentences in the document
     */
    public int numSentences() {
        // TODO: Implement this method
        return 0;
    }

    /**
     * Obtain a specific sentence from the document.
     * Sentences are numbered starting from 1.
     *
     * @param sentence_number the position of the sentence to retrieve,
     * {@code 1 <= sentence_number <= this.getSentenceCount()}
     * @return the sentence indexed by {@code sentence_number}
     */
    public String getSentence(int sentence_number) {
        // TODO: Implement this method
        return null;
    }

    public double averageSentenceLength() {
        // TODO: Implement this method
        return 0.0;
    }

    public double averageSentenceComplexity() {
        // TODO: Implement this method
        return 0.0;
    }

    /* ------- Task 3 ------- */

//    To implement these methods while keeping the class
//    small in terms of number of lines of code,
//    implement the methods fully in sentiments.SentimentAnalysis
//    and call those methods here. Use the getSentence() method
//    implemented in this class when you implement the methods
//    in the SentimentAnalysis class.

//    Further, avoid using the Google Cloud AI multiple times for
//    the same document. Save the results (cache them) for
//    reuse in this class.

//    This approach illustrates how to keep classes small and yet
//    highly functional.

    /**
     * Obtain the sentence with the most positive sentiment in the document
     * @return the sentence with the most positive sentiment in the
     * document; when multiple sentences share the same sentiment value
     * returns the sentence that appears later in the document
     * @throws NoSuitableSentenceException if there is no sentence that
     * expresses a positive sentiment
     */
    public String getMostPositiveSentence() throws NoSuitableSentenceException {
        // TODO: Implement this method
        return null;
    }

    /**
     * Obtain the sentence with the most negative sentiment in the document
     * @return the sentence with the most negative sentiment in the document;
     * when multiple sentences share the same sentiment value, returns the
     * sentence that appears later in the document
     * @throws NoSuitableSentenceException if there is no sentence that
     * expresses a negative sentiment
     */
    public String getMostNegativeSentence() throws NoSuitableSentenceException {
        // TODO: Implement this method
        return null;
    }

}
