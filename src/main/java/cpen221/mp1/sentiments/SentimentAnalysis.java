package cpen221.mp1.sentiments;

import com.google.cloud.language.v1.AnalyzeSentimentResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import cpen221.mp1.exceptions.NoSuitableSentenceException;

import java.io.IOException;

public class SentimentAnalysis {

    public static String getMostPositiveSentence()
            throws NoSuitableSentenceException {

        String text = "                       THE ANT AND THE CRICKET\n" +
                "\n" +
                "   Once upon a time... one hot summer, a cricket sang cheerfully on the branch\n" +
                "of a tree, while down below, a long line of ants struggled damely under the\n" +
                "weight of their load of grains; and between one song and the next, the cricket\n" +
                "spoke to the ants. \"Why are you working so hard? Come into the shade, away from\n" +
                "the sun, and sing a song with me.\" But the tireless ants went on with the\n" +
                "work... \"We can't do that,\" they said, \"We must store away food for the winter.\n" +
                "When the weather`s cold and the ground white with snow, there's nothing to eat,\n" +
                "and we'll survive the winter only if the pantry is full.\"\n" +
                "   \"There's plenty of summer to come,\" replied the cricket, \"and lots of time\n" +
                "to fill the pantry before winter. I'd rather sing! How can anione work in this\n" +
                "heat and sun?\"\n" +
                "   And so all summer, the cricket sang while the ants laboured. But the days\n" +
                "turned into weeks and the weeks into months. Autumn came, the leaves began to\n" +
                "fall and the cricket left the bare tree. The grass too was turning thun and\n" +
                "yellow. One morning, the cricket woke shivering with cold. An early frost\n" +
                "tinged the fields with white and turned the last of the green leaves brown:\n" +
                "winter had come at last. The cricket wandered, feeding on the few dry stalks\n" +
                "left on the hard frozen ground. Then the snow fell and she could find nothing\n" +
                "at all to eat. Trembling and famished, she thought sadly of the warmth and her\n" +
                "summer songs. One evening, she saw a speck of light in the distance, and\n" +
                "trampling through the thick snow, made her way towards it.\n" +
                "   \"Open the door! Please open the door! I'm starving. Give me some food!\" An\n" +
                "ant leant out of the window.\n" +
                "   \"Who's there? Who is it?\"\n" +
                "   \"It's me - the cricket. I'm cold and hungry, with no roof over my head.\"\n" +
                "   \"The cricket? Yes! I remember you. And what were you doing all summer\n" +
                "while we were getting ready for winter?\"\n" +
                "   \"Me? I was singing and filling the whole earth and sky with my song!\"\n" +
                "   \"Singing, eh?\" said the ant. \"Well, try dancing now!\"\n" +
                "\n"; // the text for analysis

        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            Document doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();
            AnalyzeSentimentResponse response = language.analyzeSentiment(doc);
            Sentiment sentiment = response.getDocumentSentiment();
            if (sentiment != null) {
                System.out.println(sentiment.getScore());
                System.out.println(sentiment.getMagnitude());
            }
        }
        catch (IOException ioe) {
            System.out.println(ioe);
            throw new RuntimeException("Unable to communicate with Sentiment Analyzer!");
        }
        return null;
    }

    public static String getMostNegativeSentence(Document document)
            throws NoSuitableSentenceException {
        // TODO: Implement this method
        return null;
    }

}