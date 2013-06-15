/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package besthear.tools;

/**
 *
 * @author Dawid
 */
public class FFT {
    
    byte[] samples;
    int bytes, size;
    double freq;
    double[] transform;
    /**
     * Create simple Discrete Fourier Transformation.
     * @param samples Samples provides in bytes.
     * @param bytes Bytes of sampled sound.
     * @param freq Frequency of sound.
     */
    public FFT(byte[] samples, int bytes, double freq)
    {
        if(samples == null) return;
        this.samples = samples;
        this.bytes = bytes;
        this.freq = freq;
        this.size = samples.length / 2;
        this.transform = new double[size];
    }
    
    public double[] calculate()
    {
        Complex[] input = new Complex[size];
        for(int i=0; i < size; i++)
        {
            double sample = samples[2*i] * (1<<8) + samples[(2*i) + 1]; 
            input[i] = new Complex(sample, 0);
        }
        Complex[] result = fft(input);
        for(int i=0; i < result.length; i++)
            transform[i] = result[i].abs();
        return transform;
    }
    
    public double calculate(int k)
    {
        Complex number = new Complex(samples[0], 0);
        for(int i = 1; i < size; i++)
        {
           double sample = samples[2*i] * (1<<8) + samples[(2*i) + 1];
           number.add(new Complex(sample, -(2* Math.PI * i * k)/(double)size));
        }
        return number.module();
    }
    
    public int maxFreq()
    {
        int max = 0;
        for(int i=0; i < transform.length / 2; i++)
        {
            if(transform[max] < transform[i])
                max = i;
        }
        return (int)((max * freq)/(size));
    }
    
    
    public static Complex[] fft(Complex[] x) {
        int N = x.length;

        // base case
        if (N == 1) return new Complex[] { x[0] };

        // radix 2 Cooley-Tukey FFT
        if (N % 2 != 0) { throw new RuntimeException("N is not a power of 2"); }

        // fft of even terms
        Complex[] even = new Complex[N/2];
        for (int k = 0; k < N/2; k++) {
            even[k] = x[2*k];
        }
        Complex[] q = fft(even);

        // fft of odd terms
        Complex[] odd  = even;  // reuse the array
        for (int k = 0; k < N/2; k++) {
            odd[k] = x[2*k + 1];
        }
        Complex[] r = fft(odd);

        // combine
        Complex[] y = new Complex[N];
        for (int k = 0; k < N/2; k++) {
            double kth = -2 * k * Math.PI / N;
            Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
            y[k]       = q[k].plus(wk.times(r[k]));
            y[k + N/2] = q[k].minus(wk.times(r[k]));
        }
        return y;
    }
}
