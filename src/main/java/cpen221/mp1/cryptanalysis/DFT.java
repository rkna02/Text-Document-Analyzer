package cpen221.mp1.cryptanalysis;

public abstract class DFT {

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
