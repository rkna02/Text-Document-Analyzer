package cpen221.mp1;

import cpen221.mp1.exceptions.NoSuitableSentenceException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.BreakIterator;
import java.util.*;


public class Document {

    /* ------- Task 0 ------- */
    /*  all the basic things  */

    private StringBuilder DocText = new StringBuilder();
    private String lowDocText = new String();
    private ArrayList<String> wordArrayList = new ArrayList<>();
    private  String docId;
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
        init();
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
            for (String fileLine = reader.readLine(); fileLine != null; fileLine = reader.readLine()) {
                DocText.append(" ");
                DocText.append(fileLine);
            }
            reader.close();
        }
        catch (IOException ioe) {
            System.out.println("Problem reading file!");
        }
        init();
    }

    /* ------- Task 1 ------- */

    /**Helper method:
     *Initializes wordArrayList which is a list of the words of the document
     */
    private void init() {
        lowDocText = DocText.toString().toLowerCase();

        for(String word : lowDocText.split(" ")){
            if(filter(word) != ""){
                wordArrayList.add(filter(word));
            }
        }
    }

    /**
     * @return the average length of the words in the document
     */
    public double averageWordLength() {

        int fakeWord = 0;
        int sum =0; //total length of all words in terms of characters
        for(int i=0; i<wordArrayList.size(); i++){
            char[] charArray = wordArrayList.get(i).toCharArray();
            for (int j = 0; j < charArray.length; j++) {
                char ch = charArray[j];
                if (!(isLetter(ch))) {
                    if(!((j==0) || (j==charArray.length-1))){
                        if((isLetter(charArray[j-1]) && isLetter(charArray[j+1]))){
                          //  System.out.println("special");
                        }
                        else {
                            fakeWord++;
                        }
                    }
                    else {
                        fakeWord++;
                    }
                }
            }
            sum += wordArrayList.get(i).length();
        }

        sum -= fakeWord;

        int wordCount = 0;
        for(int i=0; i<wordArrayList.size(); i++){
            wordCount++;
        }
      //  System.out.println(sum);
      //  System.out.println(wordCount);

        return (double) sum/(wordCount);
    }

    /**
     * @return the ratio of unique words (different words) to the total number of words in the document
     */
    public double uniqueWordRatio() {

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
    /**
     * @return the hapaxLegomanaRatio which is the ratio of words that only occur exactly once to that total number of words
     */
    public double hapaxLegomanaRatio() {
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


        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
        iterator.setText(DocText.toString());
        int start = iterator.first();
        for (int end = iterator.next();
             end != BreakIterator.DONE;
             start = end, end = iterator.next()) {

            String sentence = DocText.toString().substring(start, end);
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
        ArrayList<String> lowDocText2 = new ArrayList<>();

        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
        iterator.setText(DocText.toString());
        int start = iterator.first();
        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {

            String sentence = DocText.toString().substring(start, end);
            lowDocText2.add(sentence);
            //System.out.println(sentence);

        }

        return lowDocText2.get(sentence_number-1).trim(); //remove any trailing spaces, -1 to compensate for the 1 indexing
    }

    /**
     * @return the average sentence length
     */
    public double averageSentenceLength() {
        //System.out.println(wordArrayList.size() + " " + numSentences());
        return (double) wordArrayList.size()/numSentences();
    }

    /**
     * @return the average sentence complexity, which is the average number of phrases per sentence
     */
    public double averageSentenceComplexity() {
        String lowDocText2 = new String(DocText.toString());
        lowDocText2 = DocText.toString().toLowerCase();
        int count=0;
        ArrayList<String> phraseArrayList = new ArrayList<String>();
        for(String phrase : lowDocText2.split("[,;:.!?]")){
            if(phrase.length() != 0){ //makes sure phrase to be added actually contains letters
                for(int j=0; j<phrase.length(); j++){
                    if(((phrase.charAt(j) >= 'a' && phrase.charAt(j) <= 'z'))){
                        phraseArrayList.add(phrase);
                        count++;
                        break;
                    }
                }
            }
        }
        return (double) (phraseArrayList.size()) / numSentences();
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
        return cpen221.mp1.sentiments.SentimentAnalysis.getMostPositiveSentence(this);
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
        return cpen221.mp1.sentiments.SentimentAnalysis.getMostNegativeSentence(this);
    }

    /**
     * Checks if input character is a valid letter (capital or lower-case accepted)
     * @param input, a character input to analyze
     * @return boolean true or false
     */
    private boolean isLetter(char input) {
        if((input >= 'A' && input <= 'Z') || (input >= 'a' && input <= 'z')  ){
            return true;
        }
        return false;
    }

    /**
     * Helper method which filters an input string to remove garbage characters as per MP1 requirements
     * In my implementation, ######hello is a valid filtered string.
     * @param raw which is the unfiltered input String
     * @return a new String with the filtered String
     */
    private String filter(String raw) {

        boolean poundFlag = true;
        boolean allBad = true;

        StringBuilder sb = new StringBuilder(raw);

        int frontInd = 0;
        int rearInd = 0;

        for(int i=raw.length(); i>0; i--){ //scan from rear
            char ch = raw.charAt(i-1);
            if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')) {
                rearInd = i-1;
                allBad = false;
                break;
            }
        }

        if(allBad) { //if all punctuation then return empty
            return "";
        }

        for(int i=0; i<raw.length(); i++){ //scan from front
            char ch = raw.charAt(i);
            if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9') || (ch == '#')) {
                frontInd = i;
                break;
            }
        }

        for(int i=frontInd; i<=rearInd; i++){


            char ch = raw.charAt(i);

            if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')) {
                poundFlag = true;
                break;
            }
            else {
                poundFlag = false;
            }

        }

        if(poundFlag == false){
            return "";
        }

        return sb.substring(frontInd, rearInd+1).toString().toLowerCase();

    }
}