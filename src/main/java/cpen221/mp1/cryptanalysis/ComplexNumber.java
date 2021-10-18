package cpen221.mp1.cryptanalysis;

public class ComplexNumber {

    private double realPart;
    private double complexPart;

    /**
     *Constructor
     * @param real the real part of the complex number
     * @param imaginary the imaginary part of the complex number
     */
    public ComplexNumber(double real, double imaginary) {
        realPart = real;
        complexPart = imaginary;
    }

    /**
     *Constructor, returns ComplexNumber = 0+0i
     */
    public ComplexNumber() {
        realPart = 0;
        complexPart = 0;
    }

    /**Returns a new copy of an existing ComplexNumber
     *@param seed a ComplexNumber
     */
    public ComplexNumber(ComplexNumber seed) {
        realPart = seed.re();
        complexPart = seed.im();
    }

    /**Adds current ComplexNumber with another
     *@param other a ComplexNumber to be added
     */
    public void add(ComplexNumber other) {
        realPart += other.re();
        complexPart += other.im();
    }

    /**Multiplies current ComplexNumber with another
     *@param other a ComplexNumber to be multiplied
     */
    public void multiply(ComplexNumber other) {
        double temp = realPart;
        realPart = realPart*other.re() - complexPart*other.im();
        complexPart = complexPart*other.re()+temp*other.im();
    }

    /**Expresses ComplexNumber as a String, in the form a+bi
     * where a is the real part
     * and b is the imaginary part
     * @return String the resulting String
     */
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

    /**Returns real part of ComplexNumber
     * @return double which is the real part
     */
    public double re() {
        return realPart;
    }

    /**Returns imaginary part of ComplexNumber
     * @return double which is the imaginary part
     */
    public double im() {
        return complexPart;
    }

    /**Compares current ComplexNumber object with another object
     * @param other object to be compared
     * @return boolean the result of the comparison
     */
    public boolean equals(Object other) {
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

    /**Returns the hashCode() of this ComplexNumber
     * @return int the hashCode
     */
    public int hashCode() {
        final int PRIME = 8775973;
        int result = 1;
        result = PRIME*((int) this.re() + (int) this.im()); //maybe use ***Part instead of this.method()? idk
        return result;
    }

}