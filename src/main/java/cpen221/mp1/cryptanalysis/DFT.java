package cpen221.mp1.cryptanalysis;

import cpen221.mp1.exceptions.NoSuitableSentenceException;

public abstract class DFT {

    /**
     * Performs DFT on a complex number array
     * @param inSignal, the ComplexNumber array
     * @return the result of the DFT in an array of ComplexNumbers
     */
    public static ComplexNumber[] dft(ComplexNumber[] inSignal) {


        double CONSTANT_PI = 3.141593;

        ComplexNumber[] complexArray = new ComplexNumber[inSignal.length];

        for(int k=0; k<inSignal.length; k++) {
            ComplexNumber sum = new ComplexNumber();
            for(int j=0; j<inSignal.length; j++){
                double rePart = Math.cos(2.0*CONSTANT_PI*k*j/(inSignal.length));
                double imPart = Math.sin(2.0*CONSTANT_PI*k*j/(inSignal.length));
                imPart *= -1;

                ComplexNumber temp = new ComplexNumber(rePart, imPart);
                temp.multiply(inSignal[j]);

                sum.add(temp);
            }
            complexArray[k] = sum;
        }

        return complexArray;


    }

    /**
     * Performs DFT on an integer number array
     * @param inSignal, the integer array
     * @return the result of the DFT in an array of ComplexNumbers
     */
    public static ComplexNumber[] dft(int[] inSignal) {

        double CONSTANT_PI = 3.141593;

        ComplexNumber[] complexArray = new ComplexNumber[inSignal.length];

        for(int k=0; k<inSignal.length; k++) {
            ComplexNumber sum = new ComplexNumber();
            for(int j=0; j<inSignal.length; j++){
                double rePart = Math.cos(2.0*CONSTANT_PI*k*j/(inSignal.length));
                double imPart = Math.sin(2.0*CONSTANT_PI*k*j/(inSignal.length));
                imPart *= -1;

                ComplexNumber temp = new ComplexNumber(rePart, imPart);
                temp.multiply(new ComplexNumber(inSignal[j],0));

                sum.add(temp);
            }
            complexArray[k] = sum;
        }

        return complexArray;
    }

}
