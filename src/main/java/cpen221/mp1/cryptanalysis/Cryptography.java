package cpen221.mp1.cryptanalysis;

import cpen221.mp1.Document;

import java.security.spec.EncodedKeySpec;

public abstract class Cryptography {

    /**
     * Encrypt a document by replacing the i-th character, c_i, with
     * c_i + A \times \sin{(i \times 2\pi / P + \pi/4)}
     * where A is the amplitude and P is the period for the sine wave.
     * When encrypting text with multiple sentences, exactly one space
     * is used to separate sentences.
     *
     * @param doc the document to encrypt
     * @param length the number of characters to encrypt.
     *               If {@code length} is less than the number of
     *               characters in the document then only that many
     *               characters are encrypted.
     *               If {@code length} is greater than the number
     *               of characters in the document then additional
     *               ' ' are added at the end and encrypted.
     * @param period is the period of the sine wave used for encryption
     *               and {@code period} must be a factor of
     *               {@code length} other than 1 and {@code length} itself.
     * @param amplitude is the amplitude of the encrypting sine wave
     *                  and can be 64, 128, 256 or 512.
     * @return the encrypted array, with exactly one encrypted space
     *                  separating sentences.
     */
    public static int[] encrypt(Document doc, int length, int period, int amplitude) {
        // TODO: implement this method
        //extract the sentences from Document one by one and append it into a StringBuilder

        double CONSTANT_PI = 3.141593;

        StringBuilder string = new StringBuilder();
        int periodCounter = 0;
        for(int i=0; i<doc.numSentences(); i++){
            string.append(doc.getSentence(i+1));
            string.append(" "); //CHECK IF THERE IS SOMETHING AFTER IT
            String checker = doc.getSentence(i+1);
            if(checker.contains(".")) {
                periodCounter++;
            }

        }

        if(length < string.length()) {
            int[] encArray = new int[string.length()];
            for(int i=0; i<length; i++){
                System.out.println(string.charAt(i));
                encArray[i] = (int) (string.charAt(i) + amplitude*Math.sin(i*2*CONSTANT_PI/period + CONSTANT_PI/4.0));
            }
            for(int i=length; i< string.length(); i++){
                System.out.println(string.charAt(i));
                encArray[i] = (int) (string.charAt(i));
            }
            return encArray;
        }
        else {
            int[] encArray2 = new int[length];
            for(int i=0; i<string.length(); i++){
                System.out.println(string.charAt(i));
                encArray2[i] = (int) (string.charAt(i) + amplitude*Math.sin(i*2*CONSTANT_PI/period + CONSTANT_PI/4.0));
            }
            for(int i=string.length(); i<length; i++){
                encArray2[i] = (int) (' ' + amplitude*Math.sin(i*2*CONSTANT_PI/period + CONSTANT_PI/4.0));
            }
            return encArray2;
        }

    }

    /**
     * Decrypt a text that has been encrypted using {@code Cryptography#encrypt}.
     * @param codedText the data to decrypt.
     * @return the decrypted text.
     */
    public static String decrypt(int[] codedText) {
        // TODO: implement this method
        return null;
    }

}
