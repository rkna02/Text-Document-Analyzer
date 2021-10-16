package cpen221.mp1;

import cpen221.mp1.similarity.DocumentSimilarity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Task4SmokeTests extends DocumentSimilarity {

    private static Document testDocument1;
    private static Document testDocument2;
    private static Document testDocument3;
    private static Document testDocument4;
    private static Document testDocument5;

    @BeforeAll
    public static void setupTests() throws MalformedURLException {
        testDocument1 = new Document("Task four test document one", "resources/task4TestDoc1.txt");
        testDocument2 = new Document("Task four test document two", "resources/task4TestDoc2.txt");
        testDocument3 = new Document("Task four test document three", "resources/task4TestDoc3.txt");
        testDocument4 = new Document("Task four test document four", "resources/task4TestDoc4.txt");
        testDocument5 = new Document("The Ant and The Cricket", "resources/antcrick.txt");

    }

    @Test
    public void antcrick() {
        Assertions.assertEquals(0.0, documentDivergence(testDocument5,testDocument5), 0.005);
    }

    @Test
    public void jsDivergenceSameDocument() {
        Assertions.assertEquals(0.0, documentDivergence(testDocument1,testDocument1), 0.005);
    }

    @Test
    public void divergenceSameDocument() {
        Assertions.assertEquals(0.0, documentDivergence(testDocument1,testDocument1), 0.005);
    }

    @Test
    public void DivergenceSmallDifference() {
        Assertions.assertEquals(12.052927553780764, documentDivergence(testDocument1,testDocument2), 0.005);
    }

    @Test
    public void DivergenceBigDifference() {
        Assertions.assertEquals(20.181537079864253, documentDivergence(testDocument1,testDocument3), 0.005);
    }

    @Test
    public void DivergenceNoRepeatedWords() {
        //System.out.println(documentDivergence(testDocument1,testDocument4));
        Assertions.assertEquals(0.0, documentDivergence(testDocument1,testDocument4), 0.005);
    } //44.401

    @Test
    public void ProbabilityTester() {
        //System.out.println(documentDivergence(testDocument1,testDocument4));
        Assertions.assertEquals(0.0909090909090909, Probability(testDocument1, "we"), 0.005);
    }

    @Test
    public void FilterTester() {
        Assertions.assertEquals("#%$%$%$%je%rry", filter("#%$%$%$%Je%rry%$%$%"));
    }
}