package besthear.tools;

/**
 * Not really fast but working complex number implementation.
 * @author Dawid
 */
public class Complex {
    
    private double re;
    private double im;
    
    /**
     * Constructor used for EXPONENTIAL FORM!!!!
     * @param module
     * @param angle 
     */
    public Complex(double re, double im)
    {
        this.re = re;
        this.im = im;
    }
    
    public void add(Complex added)
    {
        re += added.re;
        im += added.im;
    }
    
    public double module()
    {
        double x = Math.pow(re, 2.0);
        double y = Math.pow(im, 2.0);
        double sum = x + y;
        double result = Math.sqrt(sum);
        return result;
    }
    
    // a static version of plus
    public static Complex plus(Complex a, Complex b) {
        double real = a.re + b.re;
        double imag = a.im + b.im;
        Complex sum = new Complex(real, imag);
        return sum;
    }
    
    public double abs()   { return Math.hypot(re, im); }  // Math.sqrt(re*re + im*im)
    public double phase() { return Math.atan2(im, re); }  // between -pi and pi

    // return a new Complex object whose value is (this + b)
    public Complex plus(Complex b) {
        Complex a = this;             // invoking object
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag);
    }

    // return a new Complex object whose value is (this - b)
    public Complex minus(Complex b) {
        Complex a = this;
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new Complex(real, imag);
    }

    // return a new Complex object whose value is (this * b)
    public Complex times(Complex b) {
        Complex a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new Complex(real, imag);
    }
}
