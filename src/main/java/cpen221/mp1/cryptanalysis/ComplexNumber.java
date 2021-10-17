package cpen221.mp1.cryptanalysis;

public class ComplexNumber {

    private double realPart;
    private double complexPart;
    public ComplexNumber(double real, double imaginary) {
        realPart = real;
        complexPart = imaginary;
    }

    public ComplexNumber() {
        realPart = 0;
        complexPart = 0;
    }

    public ComplexNumber(ComplexNumber seed) {
        realPart = seed.re();
        complexPart = seed.im();
    }

    public void add(ComplexNumber other) {
        realPart += other.re();
        complexPart += other.im();
    }

    public void multiply(ComplexNumber other) {
        double temp = realPart;
        realPart = realPart*other.re() - complexPart*other.im();
        complexPart = complexPart*other.re()+temp*other.im();
    }

    public String toString() {
        StringBuilder rep = new StringBuilder();
        rep.append(realPart);
        rep.append('+');
        rep.append(complexPart);
        rep.append('i');
        String actualRep = new String();
        actualRep = rep.toString();
        return actualRep;
    }

    public double re() {
        return realPart;
    }

    public double im() {
        return complexPart;
    }

    public boolean equals(Object other) {
        if(other == this){
            return true;
        }
        if(!(other instanceof ComplexNumber)){
            return false;
        }

        ComplexNumber test = (ComplexNumber) other;

        if(test.re() == this.re() && test.im() == this.im()){
            return true;
        }
        else {
            return false;
        }

    }

    public int hashCode() {
        final int PRIME = 8775973;
        int result = 1;
        result = PRIME*((int) this.re() + (int) this.im()); //maybe use ***Part instead of this.method()? idk
        return result;
    }

}