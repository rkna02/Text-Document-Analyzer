package cpen221.mp1.similarity;

import cpen221.mp1.Document;

public class DocumentSimilarity {

    /* DO NOT CHANGE THESE WEIGHTS */
    private final int WT_AVG_WORD_LENGTH      = 5;
    private final int WT_UNIQUE_WORD_RATIO    = 15;
    private final int WT_HAPAX_LEGOMANA_RATIO = 25;
    private final int WT_AVG_SENTENCE_LENGTH  = 1;
    private final int WT_AVG_SENTENCE_CPLXTY  = 4;
    private final int WT_JS_DIVERGENCE        = 50;
    /* ---- END OF WEIGHTS ------ */

    /* ------- Task 4 ------- */

    /**
     * Compute the Jensen-Shannon Divergence between the given documents
     * @param doc1 the first document, is not null
     * @param doc2 the second document, is not null
     * @return the Jensen-Shannon Divergence between the given documents
     */
    public double jsDivergence(Document doc1, Document doc2) {
        // TODO: Implement this method
        return 0.0;
    }

    /**
     * Compute the Document Divergence between the given documents
     * @param doc1 the first document, is not null
     * @param doc2 the second document, is not null
     * @return the Document Divergence between the given documents
     */
    public double documentDivergence(Document doc1, Document doc2) {
        // TODO: Implement this method
        // Use the provided weights in computing the document divergence
        return 0.0;
    }

}
