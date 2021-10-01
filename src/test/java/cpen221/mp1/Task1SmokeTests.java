package cpen221.mp1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Task1SmokeTests {

    private static Document testDocument1;
    private static Document testDocument2;
    private static Document testDocument3;
    private static Document testDocument4;
    private static Document testDocument5;

    @BeforeAll
    public static void setupTests() throws MalformedURLException {
        testDocument1 = new Document("The Ant and The Cricket", "resources/antcrick.txt");
        testDocument2 = new Document("The Ant and The Cricket", new URL("http://textfiles.com/stories/antcrick.txt"));
        testDocument3 = new Document("Williams", "resources/shakes.txt");
        testDocument4 = new Document("Computer Engineering", "resources/Computer Engineering.txt");
        testDocument5 = new Document("A day at school", "resources/A day at school.txt");
    }

    @Test
    public void testAvgSentenceLength() {
        Assertions.assertEquals(10.027, testDocument1.averageSentenceLength(), 0.005);
    }

    @Test
    public void testAvgSentenceComplexity() {
        Assertions.assertEquals(1.702, testDocument1.averageSentenceComplexity(), 0.005);
    }

    @Test
    public void testSentences() {
        Assertions.assertEquals(37, testDocument1.numSentences());
        Assertions.assertEquals("\"We can't do that,\" they said, \"We must store away food for the winter.", testDocument2.getSentence(5));
    }


    @Test
    public void testAvgSentenceLength2() {
        Assertions.assertEquals( 8,testDocument3.averageSentenceLength(), 0.005);
    }

    @Test
    public void testAvgSentenceComplexity2() {
        Assertions.assertEquals(1, testDocument3.averageSentenceComplexity(), 0.005);
    }

    @Test
    public void testSentences2() {
        Assertions.assertEquals(2, testDocument3.numSentences());
        Assertions.assertEquals("/ Thou art more lovely and more temperate.", testDocument3.getSentence(2));
    }

    @Test
    public void testAvgSentenceLength3() {
        // TODO: Implement test with testdocument4
    }

    @Test
    public void testAvgSentenceComplexity3() {
        // TODO: Implement test with testdocument4
    }

    @Test
    public void testSentences3() {
        // TODO: Implement test with testdocument4
    }

    @Test
    public void testAvgSentenceLength4() {
        // TODO: Implement test with testdocument5
    }

    @Test
    public void testAvgSentenceComplexity4() {
        // TODO: Implement test with testdocument5
    }
    
    @Test
    public void testSentences4() {
        // TODO: Implement test with testdocument5
    }

}
