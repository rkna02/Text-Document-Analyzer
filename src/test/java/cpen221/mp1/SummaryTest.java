package cpen221.mp1;


import cpen221.mp1.similarity.DocumentSimilarity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.net.MalformedURLException;
import cpen221.mp1.exceptions.NoSuitableSentenceException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import static cpen221.mp1.similarity.GroupingDocuments.groupBySimilarity;

public class SummaryTest extends DocumentSimilarity{

    private static Document testDocument1;
    private static Document testDocument2;
    private static Document testDocument3;
    private static Document testDocument4;

    private static Document DtestDocument1;
    private static Document DtestDocument2;
    private static Document DtestDocument3;
    private static Document DtestDocument4;
    private static Document DtestDocument5;

    private static Document garbage;
    private static Document empty;

    private Set<Document> testSet1;
    private Set<Document> testSet2;
    private Set<Document> testSet3;
    private Set<Document> testSet4;
    private Set<Set<Document>> bigSet;

    @BeforeAll
    public static void setupTests() throws MalformedURLException {
        testDocument1 = new Document("The Ant and The Cricket", "resources/antcrick.txt");
        testDocument2 = new Document("The Ant and The Cricket", new URL("http://textfiles.com/stories/antcrick.txt"));
        testDocument3 = new Document("Williams", "resources/shakes.txt");
        testDocument4 = new Document("Have a good day", "resources/day.txt");

        DtestDocument1 = new Document("Task four test document one", "resources/task4TestDoc1.txt");
        DtestDocument2 = new Document("Task four test document two", "resources/task4TestDoc2.txt");
        DtestDocument3 = new Document("Task four test document three", "resources/task4TestDoc3.txt");
        DtestDocument4 = new Document("Task four test document four", "resources/task4TestDoc4.txt");
        DtestDocument5 = new Document("The Ant and The Cricket", "resources/antcrick.txt");

        garbage = new Document( "garbge", "resources/garbageText.txt");
        empty = new Document( "empty", "resources/emptyTest.txt");
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


    // ------------------------------------------------------------------------------------------------------------

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

    @Test //MISSING
    public void testException() {
        boolean flag = false;
        try {Assertions.assertEquals("\"Well, try dancing now!\"", empty.getMostPositiveSentence());}
        catch(NoSuitableSentenceException nse){
            System.out.println("oof");
            flag = true;
        }

        Assertions.assertEquals(true, flag);

    }

    @Test
    public void testHapaxLegomanaRatio2() {
        Assertions.assertEquals(0.875, testDocument3.hapaxLegomanaRatio(), 0.005);
    }


    @Test
    public void testHapaxLegomanaRatio3() {
        Assertions.assertEquals(0.826086957, testDocument4.hapaxLegomanaRatio(), 0.005);
    }

    // ------------------------------------------------------------------------------------------------------------

    @Test
    public void antcrick() {
        Assertions.assertEquals(0.0, documentDivergence(DtestDocument5,DtestDocument5), 0.005);
    }

    @Test
    public void jsDivergenceSameDocument() {
        Assertions.assertEquals(0.0, documentDivergence(DtestDocument1,DtestDocument1), 0.005);
    }

    @Test
    public void divergenceSameDocument() {
        Assertions.assertEquals(0.0, documentDivergence(DtestDocument1,DtestDocument1), 0.005);
    }

    @Test
    public void DivergenceSmallDifference() {
        Assertions.assertEquals(6.5562, documentDivergence(DtestDocument1,DtestDocument2), 0.005);
    }

    @Test
    public void DivergenceBigDifference() {
        Assertions.assertEquals(44.4348, documentDivergence(DtestDocument1,DtestDocument3), 0.005);
    }

    @Test
    public void Divergence() {
        Assertions.assertEquals(83.6685, documentDivergence(DtestDocument1,DtestDocument4), 0.005);
    }

    @Test
    public void jsDivergence() {
        Assertions.assertEquals(0.786, jsDivergence(DtestDocument1,DtestDocument4), 0.005);
    }

    @Test
    public void garbageTesting() { //MISSING
        Assertions.assertEquals(64.75000000000003, documentDivergence(DtestDocument1,garbage), 0.005);
    }

    // ------------------------------------------------------------------------------------------------------------

    @Test
    public void groupingFourDocumentsInThree() {
        testSet1 = new HashSet<>();
        testSet1.add(DtestDocument1);
        testSet1.add(DtestDocument2);

        testSet2 = new HashSet<>();
        testSet2.add(DtestDocument3);

        testSet3 = new HashSet<>();
        testSet3.add(DtestDocument4);


        bigSet = new HashSet<>();
        bigSet.add(testSet1);
        bigSet.add(testSet2);
        bigSet.add(testSet3);


        testSet4 = new HashSet<>();
        testSet4.add(DtestDocument1);
        testSet4.add(DtestDocument2);
        testSet4.add(DtestDocument3);
        testSet4.add(DtestDocument4);

        Assertions.assertEquals(bigSet, groupBySimilarity(testSet4, 3));
    }

    @Test
    public void documentInTheirOwnGroup() {
        testSet1 = new HashSet<>();
        testSet2 = new HashSet<>();
        testSet1.add(DtestDocument1);
        testSet2.add(DtestDocument2);

        testSet3 = new HashSet<>();
        testSet3.add(DtestDocument1);
        testSet3.add(DtestDocument2);

        Set<Set<Document>> bigSet = new HashSet<>();
        bigSet.add(testSet1);
        bigSet.add(testSet2);
        Assertions.assertEquals(bigSet, groupBySimilarity(testSet3, 2));

    }

    @Test
    public void allInOne() {
        testSet1 = new HashSet<>();
        testSet1.add(DtestDocument1);
        testSet1.add(DtestDocument2);
        testSet1.add(DtestDocument3);

        testSet3 = new HashSet<>();
        testSet3.add(DtestDocument1);
        testSet3.add(DtestDocument2);
        testSet3.add(DtestDocument3);


        Set<Set<Document>> bigSet = new HashSet<>();
        bigSet.add(testSet1);
        Assertions.assertEquals(bigSet, groupBySimilarity(testSet3, 1));
    }

    @Test
    public void randomTesting() {
        testSet1 = new HashSet<>();
        testSet1.add(DtestDocument4);

        testSet2 = new HashSet<>();
        testSet2.add(DtestDocument1);
        testSet2.add(DtestDocument2);
        testSet2.add(DtestDocument3);



        testSet3 = new HashSet<>();
        testSet3.add(DtestDocument1);
        testSet3.add(DtestDocument2);
        testSet3.add(DtestDocument3);
        testSet3.add(DtestDocument4);


        Set<Set<Document>> bigSet = new HashSet<>();
        bigSet.add(testSet1);
        bigSet.add(testSet2);
        Assertions.assertEquals(bigSet, groupBySimilarity(testSet3, 2));
    }



}
