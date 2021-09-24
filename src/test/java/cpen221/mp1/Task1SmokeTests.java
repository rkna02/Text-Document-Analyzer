package cpen221.mp1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Task1SmokeTests {

    private static Document testDocument1;
    private static Document testDocument2;

    @BeforeAll
    public static void setupTests() throws MalformedURLException {
        testDocument1 = new Document("The Ant and The Cricket", "resources/antcrick.txt");
        testDocument2 = new Document("The Ant and The Cricket", new URL("http://textfiles.com/stories/antcrick.txt"));
    }

    @Test
    public void testAvgSentenceLength() {
        Assertions.assertEquals(10.388, testDocument1.averageSentenceLength(), 0.005);
    }

    @Test
    public void testAvgSentenceComplexity() {
        Assertions.assertEquals(1.75, testDocument2.averageSentenceComplexity(), 0.005);
    }

    @Test
    public void testSentences() {
        Assertions.assertEquals(36, testDocument1.numSentences());
        Assertions.assertEquals("\"There's plenty of summer to come,\" replied the cricket, \"and lots of time to fill the pantry before winter.", testDocument2.getSentence(5));
    }

}
