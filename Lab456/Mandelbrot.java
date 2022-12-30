package Lab456;

import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;

    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -1.5;
        range.width = 3;
        range.height = range.width;

    }

    @Override
    public int numIterations(double x, double y) {
        double zRe = 0;
        double zIm = 0;
        double zRe1 = 0;
        double zIm1 = 0;
        for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
            if (zRe1 + zIm1 < 4) {
                zIm = (2 * zRe * zIm) + y;
                zRe = (zRe1 - zIm1) + x;
                zRe1 = zRe * zRe;
                zIm1 = zIm * zIm;
            }
            else {
                return iteration;
            }
        }
        return -1;
    }
    public String toString() {
        return "Mandelbrot";
    }
}

