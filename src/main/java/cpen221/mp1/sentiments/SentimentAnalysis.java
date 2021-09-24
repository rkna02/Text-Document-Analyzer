package cpen221.mp1.sentiments;

import com.google.cloud.language.v1.AnalyzeSentimentResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import cpen221.mp1.exceptions.NoSuitableSentenceException;

public class SentimentAnalysis {

    public static Sentiment getMostPositiveSentence(Document document)
        throws NoSuitableSentenceException {
        // TODO: Implement this method
        return null;
    }

    public static Sentiment getMostNegativeSentence(Document document)
        throws NoSuitableSentenceException {
        // TODO: Implement this method
        return null;
    }

}
