package cpen221.mp1;

import cpen221.mp1.exceptions.NoSuitableSentenceException;
import cpen221.mp1.sentiments.SentimentAnalysis;

import javax.print.Doc;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.BreakIterator;
import java.util.*;

import static java.lang.Character.isLetter;

public class Document {

    /* ------- Task 0 ------- */
    /*  all the basic things  */

    private StringBuilder DocText = new StringBuilder();

    /**
     * Create a new document using a URL
     * @param docId the document identifier
     * @param docURL the URL with the contents of the document
     */
    public Document(String docId, URL docURL) {
        try {
            String documentURL = docURL.toString();
            Scanner urlScanner = new Scanner(new URL(documentURL).openStream());
            while (urlScanner.hasNext()) {
                DocText.append(" ");
                DocText.append(urlScanner.nextLine());
                //System.out.println(urlScanner.nextLine());
            }
        }
        catch (IOException ioe) {
            System.out.println("Problem reading from URL!");
        }
        System.out.println("hi");
    }

    /**
     *
     * @param docId the document identifier
     * @param fileName the name of the file with the contents of
     *                 the document
     */
    public Document(String docId, String fileName) {
        try {
            String fileName2 = new String(fileName);
            // myFile.txt should be in the root directory for your project

            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            for (String fileLine = reader.readLine();
                 fileLine != null;
                 fileLine = reader.readLine()) {
                DocText.append(" ");
                DocText.append(fileLine);
            }
            reader.close();
        }
        catch (IOException ioe) {
            System.out.println("Problem reading file!");
        }
    }

    /* ------- Task 1 ------- */

    public double averageWordLength() {
        String lowDocText = new String(DocText.toString());
        lowDocText = DocText.toString().toLowerCase();
        int sum=0;
        int bad=0;
        ArrayList<String> wordArrayList = new ArrayList<String>();
        for(String word : lowDocText.split(" ")){
            if(word.length() != 0){
                if(word.length() == 1){
                    if(((word.charAt(0) >= 'a' && word.charAt(0) <= 'z'))){
                        wordArrayList.add(word);
                    }
                }
                else {
                    wordArrayList.add(word);
                }
            }
        }

        for(int i=0; i<wordArrayList.size(); i++){
            char[] charArray = wordArrayList.get(i).toCharArray();
            for (int j = 0; j < charArray.length; j++) {
                char ch = charArray[j];
                if (!(isLetter(ch))) {
                    if(!((j==0) || (j==charArray.length-1))){
                        if((isLetter(charArray[j-1]) && isLetter(charArray[j+1]))){
                            System.out.println("special");

                        }
                        else {
                            bad++;
                        }


                    }
                    else {
                        bad++;
                    }

                }

            }
            sum += wordArrayList.get(i).length();
        }

        sum -= bad;

        int wordCount = 0;
        for(int i=0; i<wordArrayList.size(); i++){
            wordCount++;
        }
        System.out.println(sum);
        System.out.println(wordCount);

        return (double) sum/(wordCount);
    }

    public double uniqueWordRatio() {
        String lowDocText = new String(DocText.toString());
        lowDocText = DocText.toString().toLowerCase();
        int sum=0;
        int bad=0;
        ArrayList<String> wordArrayList = new ArrayList<String>();
        for(String word : lowDocText.split(" ")){
            if(word.length() != 0){
                if(word.length() == 1){
                    if(((word.charAt(0) >= 'a' && word.charAt(0) <= 'z'))){
                        wordArrayList.add(word);
                    }
                }
                else {
                    wordArrayList.add(word);
                }
            }
        }
        System.out.println("hi");

       /* Map<String, Integer> UNIQ = new HashMap<String, Integer>();

        for (String c : wordArrayList) {
            if (UNIQ.containsKey(c)) {
                UNIQ.replace(c, UNIQ.get(c) + 1);
            } else {
                UNIQ.put(c, 1);
            }
        }

        int uniqueWords = 0;
        for(String word : UNIQ.keySet()){
            if(UNIQ.get(word) == 1){
                uniqueWords++;
            }
        }

        return UNIQ.size()/wordArrayList.size();*/

        Set<String> set = new HashSet<String>();

        for (String c : wordArrayList) {
            StringBuilder line = new StringBuilder();
            for(int i=0; i<c.length(); i++){
                if(isLetter(c.charAt(i))){
                    line.append(c.charAt(i));
                }
            }
            set.add(line.toString());
        }

        return (double) set.size()/wordArrayList.size();

    }

    public double hapaxLegomanaRatio() {
        String lowDocText = new String(DocText.toString());
        lowDocText = DocText.toString().toLowerCase();
        int sum=0;
        int bad=0;
        ArrayList<String> wordArrayList = new ArrayList<String>();
        for(String word : lowDocText.split(" ")){
            if(word.length() != 0){
                if(word.length() == 1){
                    if(((word.charAt(0) >= 'a' && word.charAt(0) <= 'z'))){
                        wordArrayList.add(word);
                    }
                }
                else {
                    wordArrayList.add(word);
                }
            }
        }
        System.out.println("hi");

       /* Map<String, Integer> UNIQ = new HashMap<String, Integer>();

        for (String c : wordArrayList) {
            if (UNIQ.containsKey(c)) {
                UNIQ.replace(c, UNIQ.get(c) + 1);
            } else {
                UNIQ.put(c, 1);
            }
        }

        int uniqueWords = 0;
        for(String word : UNIQ.keySet()){
            if(UNIQ.get(word) == 1){
                uniqueWords++;
            }
        }

        return UNIQ.size()/wordArrayList.size();*/

        Map<String, Integer> UNIQ = new HashMap<>();

        for (String c : wordArrayList) {
            StringBuilder line = new StringBuilder();
            for(int i=0; i<c.length(); i++){
                if(isLetter(c.charAt(i))){
                    line.append(c.charAt(i));
                }
            }
            if(UNIQ.containsKey(line.toString())){
                UNIQ.put(line.toString(), UNIQ.get(line.toString()) + 1);
            }
            else{
                UNIQ.put(line.toString(), 0);
            }
        }
        int uniqueCount = 0;
        for(String c : UNIQ.keySet()){
            if(UNIQ.get(c) == 0){
                uniqueCount++;
            }
        }



        return (double) uniqueCount/wordArrayList.size();
    }

    /* ------- Task 2 ------- */

    /**
     * Obtain the number of sentences in the document
     * @return the number of sentences in the document
     */
    public int numSentences() {
        int sentenceCount = 0;
        StringBuilder lowDocText = new StringBuilder();

        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
        iterator.setText(DocText.toString());
        int start = iterator.first();
        for (int end = iterator.next();
             end != BreakIterator.DONE;
             start = end, end = iterator.next()) {

            String sentence = DocText.toString().substring(start, end);
           // System.out.println(sentence);
            sentenceCount++;
        }
        return sentenceCount;
    }
    /**
     * Obtain a specific sentence from the document.
     * Sentences are numbered starting from 1.
     *
     * @param sentence_number the position of the sentence to retrieve,
     * {@code 1 <= sentence_number <= this.getSentenceCount()}
     * @return the sentence indexed by {@code sentence_number}
     */
    public String getSentence(int sentence_number) {
        int sentenceCount = 0;
        ArrayList<String> lowDocText = new ArrayList<>();

        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
        iterator.setText(DocText.toString());
        int start = iterator.first();
        for (int end = iterator.next();
             end != BreakIterator.DONE;
             start = end, end = iterator.next()) {

            String sentence = DocText.toString().substring(start, end);
            lowDocText.add(sentence);

        }

        return lowDocText.get(sentence_number-1).trim(); //remove any trailing spaces
    }

    public double averageSentenceLength() {
        String lowDocText2 = new String(DocText.toString());
        lowDocText2 = DocText.toString().toLowerCase();

        ArrayList<String> wordArrayList = new ArrayList<String>();
        for(String word : lowDocText2.split(" ")){
            if(word.length() != 0){
                if(word.length() == 1){
                    if(((word.charAt(0) >= 'a' && word.charAt(0) <= 'z'))){
                        wordArrayList.add(word);
                    }
                }
                else {
                    wordArrayList.add(word);
                }
            }
        }


        return (double) wordArrayList.size()/numSentences();
    }

    public double averageSentenceComplexity() {
        String lowDocText2 = new String(DocText.toString());
        lowDocText2 = DocText.toString().toLowerCase();

        ArrayList<String> phraseArrayList = new ArrayList<String>();
        for(String phrase : lowDocText2.split("[,;:.!?]")){
            if(phrase.length() != 0){
                if(phrase.length() == 1){
                    if(((phrase.charAt(0) >= 'a' && phrase.charAt(0) <= 'z'))){
                        phraseArrayList.add(phrase);
                        System.out.println(phrase);
                    }
                }
                else {
                    phraseArrayList.add(phrase);
                    System.out.println(phrase);
                }
            }
        }

        return (double) (phraseArrayList.size()-1)/numSentences();
    }

    /* ------- Task 3 ------- */

//    To implement these methods while keeping the class
//    small in terms of number of lines of code,
//    implement the methods fully in sentiments.SentimentAnalysis
//    and call those methods here. Use the getSentence() method
//    implemented in this class when you implement the methods
//    in the SentimentAnalysis class.

//    Further, avoid using the Google Cloud AI multiple times for
//    the same document. Save the results (cache them) for
//    reuse in this class.

//    This approach illustrates how to keep classes small and yet
//    highly functional.

    /**
     * Obtain the sentence with the most positive sentiment in the document
     * @return the sentence with the most positive sentiment in the
     * document; when multiple sentences share the same sentiment value
     * returns the sentence that appears later in the document
     * @throws NoSuitableSentenceException if there is no sentence that
     * expresses a positive sentiment
     */
    public String getMostPositiveSentence() throws NoSuitableSentenceException {
        // TODO: Implement this method
        return null;
    }

    /**
     * Obtain the sentence with the most negative sentiment in the document
     * @return the sentence with the most negative sentiment in the document;
     * when multiple sentences share the same sentiment value, returns the
     * sentence that appears later in the document
     * @throws NoSuitableSentenceException if there is no sentence that
     * expresses a negative sentiment
     */
    public String getMostNegativeSentence() throws NoSuitableSentenceException {
        // TODO: Implement this method
        return null;
    }

}
