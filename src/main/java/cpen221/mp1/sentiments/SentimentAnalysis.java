package cpen221.mp1.sentiments;

import com.google.cloud.language.v1.AnalyzeSentimentResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import cpen221.mp1.exceptions.NoSuitableSentenceException;

import java.io.IOException;

public class SentimentAnalysis {

    public static String getMostPositiveSentence(cpen221.mp1.Document document1)
            throws NoSuitableSentenceException {

        try (LanguageServiceClient language = LanguageServiceClient.create()) {

           // cpen221.mp1.Document doc_1 = new cpen221.mp1.Document("The Ant and The Cricket", "resources/antcrick.txt" );

            cpen221.mp1.Document doc_1 = document1;

            String most_positive_sentence = new String();

            float score_value =0;
            for ( int i =1; i <= doc_1.numSentences(); i++){
                String txt = doc_1.getSentence(i);
                Document doc = Document.newBuilder().setContent(txt).setType(Type.PLAIN_TEXT).build();
                AnalyzeSentimentResponse response = language.analyzeSentiment(doc);
                Sentiment sentiment = response.getDocumentSentiment();

                if (sentiment != null) {
                    System.out.println(sentiment.getScore());
                    System.out.println(sentiment.getMagnitude());
                    System.out.println(txt);
                    System.out.println("");
                }

                if(sentiment.getScore() >=score_value){
                    score_value=sentiment.getScore();
                    most_positive_sentence = txt;
                }
            }

            return most_positive_sentence;
        }

        catch (IOException ioe) {
            System.out.println(ioe);
            throw new RuntimeException("Unable to communicate with Sentiment Analyzer!");
        }
    }

    public static String getMostNegativeSentence(cpen221.mp1.Document document2)
            throws NoSuitableSentenceException {
        // TODO: Implement this method

        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            //cpen221.mp1.Document doc_2 = new cpen221.mp1.Document("The Ant and The Cricket", "resources/antcrick.txt" );
            cpen221.mp1.Document doc_2 = document2;
            String most_negative_sentence = new String();

            float score_value =0;
            for ( int i =1; i <= doc_2.numSentences(); i++){
                String txt = doc_2.getSentence(i);
                Document doc = Document.newBuilder().setContent(txt).setType(Type.PLAIN_TEXT).build();
                AnalyzeSentimentResponse response = language.analyzeSentiment(doc);
                Sentiment sentiment = response.getDocumentSentiment();

                if(sentiment != null) {
                    System.out.println(sentiment.getScore());
                    System.out.println(sentiment.getMagnitude());
                }

                if(sentiment.getScore() <=score_value){
                    score_value=sentiment.getScore();
                    most_negative_sentence = txt;
                }
            }

            return most_negative_sentence;
        }

        catch (IOException ioe) {
            System.out.println(ioe);
            throw new RuntimeException("Unable to communicate with Sentiment Analyzer!");
        }

    }

}