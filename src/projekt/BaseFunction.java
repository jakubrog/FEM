package projekt;

public class BaseFunction {
    private double a[]; // wartosci wspolczynnika a w poszczegolnych segmentach
    private double b[]; // wartości wspolczynnika b w poszczególnych segmentach
    private double h;
    private double segmentNb;

    public BaseFunction(int nbOfSegments, int segmentNb) {
        a = new double[nbOfSegments];
        b = new double[nbOfSegments];
        h = 1 / (double) nbOfSegments;
        this.segmentNb = nbOfSegments;
        for(int i = 0 ; i<nbOfSegments; i++)
            a[i] = b[i] = 0;

        if (segmentNb - 1 >= 0 ) {
            a[segmentNb - 1] = nbOfSegments;
            b[segmentNb - 1] = -(segmentNb - 1);
        }
        if(segmentNb < nbOfSegments ){
            a[segmentNb] = -nbOfSegments;
            b[segmentNb] = segmentNb + 1;
        }


    }

    public double getA(int segmentNb) {
        return a[segmentNb];
    }

    public double getB(int segmentNb) {
        return b[segmentNb];
    }

    public double value(double x) {
        for (int i = 0; i < segmentNb; i++)
            if (x >= i * h && x <= (i + 1) * h)
                return a[i] * x + b[i];
        return 0;
    }

}