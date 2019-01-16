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
        this.segmentNb = segmentNb;

        if (segmentNb == nbOfSegments) {  // definiowanie ostatniej funkcji która nie jest tozsamosciowo rowna zero na zadnym przediale
            for (int i = 0; i < nbOfSegments; i++) {
                if (i % 2 == 0) {
                    a[i] = (1 / h);
                    b[i] = -i;
                } else {
                    a[i] = (-1 / h);
                    b[i] = 1 + i;
                }
            }
        } else {  // definiowanie wszystkich funkcji
            for (int i = 0; i < nbOfSegments; i++) {
                a[i] = 0;
                b[i] = 0;
            }
            if (segmentNb % 2 != 0) {
                a[segmentNb] = (1 / h);
                b[segmentNb] = -segmentNb;
            } else {
                a[segmentNb] = (-1 / h);
                b[segmentNb] = 1 + segmentNb;
            }

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
            if (x > i * h && x <= (i + 1) * h)
                return a[i] * x + b[i];
        return 0;
    }

}