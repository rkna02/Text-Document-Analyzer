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
import static cpen221.mp1.cryptanalysis.DFT.dft;
import static cpen221.mp1.cryptanalysis.DFT.dft;
import static cpen221.mp1.cryptanalysis.Cryptography.encrypt;


public class Task6SmokeTests {
    private static Document testDocument1;


    @BeforeAll
    public static void setupTests() throws MalformedURLException {
        testDocument1 = new Document("Task four test document one", "resources/task4TestDoc1.txt");
    }

    @Test
    public void testDftWithIntegerInput() {
        ComplexNumber[] array = new ComplexNumber[3];
        int[] inSignal = new int[3];

        for(int i=0; i<3; i++){ //initialize inSignal to {1,2,3}
            inSignal[i] = i+1;
        }

        for(int i=0; i<3; i++){ //random result array of ComplexNumbers
            array[i] = new ComplexNumber(1,2);
        }


        Assertions.assertEquals(array, dft(inSignal));
    }

    @Test
    public void testDftWithComplexInput() {
        ComplexNumber[] array = new ComplexNumber[4];
        ComplexNumber[] inSignal = new ComplexNumber[4];

        inSignal[0] = new ComplexNumber(1,0);
        inSignal[1] = new ComplexNumber(2,-1);
        inSignal[2] = new ComplexNumber(0,-1);
        inSignal[3] = new ComplexNumber(-1,2);

        for(int i=0; i<4; i++){ //random result array of ComplexNumbers
            array[i] = new ComplexNumber(1,2);
        }


        Assertions.assertEquals(array, dft(inSignal));
    }

    @Test
    public void preliminaryEnc() {
        Assertions.assertEquals(4, encrypt(testDocument1,20,2,64));
    }

    @Test
    public void preliminaryDec() {
        int[] a = {117, 55, 153, 62, 156, -13, 77, -13, 77, -13, 77, -13, 77, -13, 77, -13, 77, -13, 77, -13};

        ComplexNumber[] b = dft(a);
        double max = 0;
        for(int i=0; i<b.length; i++){

            System.out.println(Math.sqrt(b[i].re()*b[i].re() + b[i].im()*b[i].im()));
            if(Math.sqrt(b[i].re()*b[i].re() + b[i].im()*b[i].im()) > max){
                max = Math.sqrt(b[i].re()*b[i].re() + b[i].im()*b[i].im());
            }

        }

        System.out.println(max);

        Assertions.assertEquals(1, ((dft(a))));

    }



}