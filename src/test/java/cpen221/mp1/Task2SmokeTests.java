package cpen221.mp1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import cpen221.mp1.exceptions.NoSuitableSentenceException;
import java.net.URL;

//import static cpen221.mp1.sentiments.SentimentAnalysis.getMostNegativeSentence;
//import static cpen221.mp1.sentiments.SentimentAnalysis.getMostPositiveSentence;

public class Task2SmokeTests {

    private static Document testDocument1;
    private static Document testDocument2;
    private static Document testDocument3;
    private static Document testDocument4;
    private static Document empty;

    @BeforeAll
    public static void setupTests() throws MalformedURLException {
        testDocument1 = new Document("The Ant and The Cricket", "resources/antcrick.txt");
        testDocument2 = new Document("The Ant and The Cricket", new URL("http://textfiles.com/stories/antcrick.txt"));
        testDocument3 = new Document("Williams", "resources/shakes.txt");
        testDocument4 = new Document("Have a good day", "resources/day.txt");
        empty = new Document( "empty", "resources/emptyTest.txt");
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
    public void testSentimentsPos() {
        try {Assertions.assertEquals("\"Well, try dancing now!\"", testDocument1.getMostPositiveSentence());}
        catch(NoSuitableSentenceException nse){
            System.out.println("oof");
        }
    }

    @Test
    public void testSentimentsNeg() {
        try {Assertions.assertEquals("Then the snow fell and she could find nothing at all to eat.", testDocument1.getMostNegativeSentence());}
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

    public void testException() {
        boolean flag = false;
        try {Assertions.assertEquals("\"Well, try dancing now!\"", empty.getMostPositiveSentence());}
        catch(NoSuitableSentenceException nse){
            System.out.println("oof");
            flag = true;
        }

        Assertions.assertEquals(true, flag);

    }

}