package cpen221.mp1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import cpen221.mp1.exceptions.NoSuitableSentenceException;
import java.net.URL;

import static cpen221.mp1.sentiments.SentimentAnalysis.getMostPositiveSentence;

public class Task2SmokeTests {

    private static Document testDocument1;
    private static Document testDocument2;
    private static Document testDocument3;
    private static Document testDocument4;

    @BeforeAll
    public static void setupTests() throws MalformedURLException {
        testDocument1 = new Document("The Ant and The Cricket", "resources/antcrick.txt");
        testDocument2 = new Document("The Ant and The Cricket", new URL("http://textfiles.com/stories/antcrick.txt"));
        testDocument3 = new Document("Williams", "resources/shakes.txt");
        testDocument4 = new Document("Have a good day", "resources/day.txt");
    }

    @Test
    public void testAvgWordLength() {
        Assertions.assertEquals(4.08, testDocument2.averageWordLength(), 0.005);
    }


    @Test
    public void testUniqueWordRatio() {
        Assertions.assertEquals(0.524, testDocument2.uniqueWordRatio(), 0.005);
    }


    @Test
    public void testHapaxLegomanaRatio() {
        Assertions.assertEquals(0.355, testDocument1.hapaxLegomanaRatio(), 0.005);
    }

    @Test
    public void testSentiments() {
        try {Assertions.assertEquals("test", getMostPositiveSentence());}
        catch(NoSuitableSentenceException nse){
            System.out.println("oof");
        }
    }

    @Test
    public void testHapaxLegomanaRatio2() {
        Assertions.assertEquals(0.875, testDocument3.hapaxLegomanaRatio(), 0.005);
    }


    @Test
    public void testHapaxLegomanaRatio3() {
        Assertions.assertEquals(0.826086957, testDocument4.hapaxLegomanaRatio(), 0.005);
    }


}