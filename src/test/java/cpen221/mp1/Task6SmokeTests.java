package cpen221.mp1;
import cpen221.mp1.cryptanalysis.ComplexNumber;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeAll;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.net.MalformedURLException;

import static cpen221.mp1.cryptanalysis.Cryptography.decrypt;
import static cpen221.mp1.cryptanalysis.DFT.dft;
import static cpen221.mp1.cryptanalysis.DFT.dft;
import static cpen221.mp1.cryptanalysis.Cryptography.encrypt;
import static cpen221.mp1.cryptanalysis.Untangler.areTangled;


public class Task6SmokeTests {
    private static Document testDocument1;

    @BeforeAll
    public static void setupTests() throws MalformedURLException {
        testDocument1 = new Document("Task six test", "resources/task6testDoc.txt");
    }

    @Test
    public void ComplexToString() {
        ComplexNumber test = new ComplexNumber(1,2);
        Assertions.assertEquals("1.0+2.0i", test.toString());
    }

    @Test
    public void ComplexToStringButNegative() {
        ComplexNumber test = new ComplexNumber(1,-2);
        Assertions.assertEquals("1.0+-2.0i", test.toString());
    }

    @Test
    public void ComplexEquals() {
        ComplexNumber test = new ComplexNumber(1,-2);
        ComplexNumber test2 = new ComplexNumber(1,-2);
        Assertions.assertEquals(true, test.equals(test2));
    }

    @Test
    public void ComplexHashCode() {
        ComplexNumber test = new ComplexNumber(1,-2);
        ComplexNumber test2 = new ComplexNumber(1,-2);
        Assertions.assertEquals(test.hashCode(), test2.hashCode());
    }

    @Test
    public void ComplexNotEquals() {
        ComplexNumber test = new ComplexNumber(1,-2);
        ComplexNumber test2 = new ComplexNumber(1,2);
        Assertions.assertEquals(false, test.equals(test2));
    }

    @Test
    public void ComplexSeed() {
        ComplexNumber test = new ComplexNumber(1,-2);
        ComplexNumber test2 = new ComplexNumber(test);
        Assertions.assertEquals(true, test.equals(test2));
    }

    @Test
    public void ComplexDifferentTypeNotEquals() {
        ComplexNumber test = new ComplexNumber(1,-2);
        String test2 = new String();
        test2 = "test";
        Assertions.assertEquals(false, test.equals(test2));
    }

    @Test
    public void testDftWithIntegerInput() {
        ComplexNumber[] array = new ComplexNumber[3];
        int[] inSignal = new int[3];

        for(int i=0; i<3; i++){ //initialize inSignal to {1,2,3}
            inSignal[i] = i+1;
        }

        array = dft(inSignal);

        Assertions.assertEquals(array[0].re(), 6, 0.001);
        Assertions.assertEquals(array[0].im(), 0, 0.001);

        Assertions.assertEquals(array[1].re(), -1.5, 0.001);
        Assertions.assertEquals(array[1].im(), 0.8660263275447593, 0.001);

        Assertions.assertEquals(array[2].re(), -1.5, 0.001);
        Assertions.assertEquals(array[2].im(), -0.8660263275447593, 0.001);

    }

    @Test
    public void testDftWithComplexInput() {
        ComplexNumber[] array = new ComplexNumber[4];
        ComplexNumber[] inSignal = new ComplexNumber[4];

        inSignal[0] = new ComplexNumber(1,0);
        inSignal[1] = new ComplexNumber(2,-1);
        inSignal[2] = new ComplexNumber(0,-1);
        inSignal[3] = new ComplexNumber(-1,2);

        array = dft(inSignal);

        Assertions.assertEquals(array[0].re(), 2, 0.001);
        Assertions.assertEquals(array[0].im(), 0, 0.001);

        Assertions.assertEquals(array[1].re(), -2, 0.001);
        Assertions.assertEquals(array[1].im(), -2, 0.001);

        Assertions.assertEquals(array[2].re(), 0, 0.001);
        Assertions.assertEquals(array[2].im(), -2, 0.001);

        Assertions.assertEquals(array[3].re(), 4, 0.001);
        Assertions.assertEquals(array[3].im(), 4, 0.001);

    }

    @Test
    public void preliminaryEnc() {
        int arr[] = {163, 215, 87, -18, 52, 122, 201, 90, -12, 49, 190, 147, 11, -94, -26, 122, 146, 11, -94, -26};
        //Assertions.assertEquals(arr, encrypt(testDocument1,20,5,128));
        int arr2[] = encrypt(testDocument1,20,5,128);

        for(int i=0; i<arr.length; i++){
            Assertions.assertEquals(arr[0],arr2[0]);
        }
    }

    @Test
    public void preliminaryDec512() {
        int[] a = encrypt(testDocument1,20,2,512);
        Assertions.assertEquals("Hello World!        ", decrypt(a));
    }

    @Test
    public void preliminaryDec64() {
        int[] a = encrypt(testDocument1,20,4,64);
        Assertions.assertEquals("Hello World!        ", decrypt(a));
    }

    @Test
    public void preliminaryDec128() {
        int[] a = encrypt(testDocument1,20,4,128);
        Assertions.assertEquals("Hello World!        ", decrypt(a));
    }

    @Test
    public void preliminaryDec256() {
        int[] a = encrypt(testDocument1,20,4,256);
        Assertions.assertEquals("Hello World!        ", decrypt(a));
    }

    @Test
    public void lengthIsLessDec256() {
        int arr[] = {253, -80, 289, -73, 292, -149, 268, -70, 114, 108, 100, 33, 32};
        //Assertions.assertEquals(arr, encrypt(testDocument1,20,5,128));
        int arr2[] = encrypt(testDocument1,8,2,256);

        for(int i=0; i<arr.length; i++){
            Assertions.assertEquals(arr[0],arr2[0]);
        }
    }

    @Test
    public void TangleTestTrue() {
        Assertions.assertEquals(true,areTangled("111011111110", "110","111"));
    }

    @Test
    public void TangleTestFalse() {
        Assertions.assertEquals(false,areTangled("1101118","110","111"));
    }

}