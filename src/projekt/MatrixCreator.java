package projekt;


public class MatrixCreator {
    private int nbOfSegments;
    private BaseFunction[] functions;
    private double h; // dlugosc przedzialu
    private double k; // stala

    public MatrixCreator(int nbOfSegments, double k) {
        this.nbOfSegments = nbOfSegments;
        functions = new BaseFunction[nbOfSegments + 1];
        h = 1 / (double) nbOfSegments;
        this.k = k;

        for (int i = 0; i <= nbOfSegments; i++)
            functions[i] = new BaseFunction(nbOfSegments, i);
    }

    public double[][] createMatrixA() {
        double[][] result = new double[nbOfSegments + 1][nbOfSegments + 1];

        for (int i = 0; i < nbOfSegments + 1; i++)
            for (int j = 0; j < nbOfSegments + 1; j++) {
               // result[i][j] = calculateBIJIntegrate(functions[j], functions[i]);
                if(i == j)
                    result[i][j] = 2*k*nbOfSegments;
                if(i == j+1)
                    result[i][j] = 0.5 - k*nbOfSegments;
                if(i == j-1)
                    result[i][j] = -0.5 - k*nbOfSegments;
            }

        return result;
    }

    public double[] createMatrixF() {
        double a, b;
        double[] result = new double[nbOfSegments + 1];
        for (int i = 0; i < nbOfSegments + 1; i++) {
            // result[i] = calculateLIIntegrate(functions[i]);
            if (i != 0 && i != nbOfSegments) {
                for (int j = 0; j < 2; j++) {
                    if (j % 2 == 0) {
                        a = nbOfSegments;
                        b = -(i - 1);
                    } else {
                        a = -nbOfSegments;
                        b = -i + 1;
                    }
                    result[i] += (5 * a / 3) * (h * h * h * (i + 1) * (i + 1) * (i + 1) - h * h * h * i * i * i) + 0.5 *
                            (h * h * (i + 1) * (i + 1) - h * h * i * i) * (5 * b - 5 * a) + h * (-5 * b + 5 * a);
                }
            } if(i == 0) {
                a = -nbOfSegments;
                b = -i + 1;
                result[i] += (5 * a / 3) * (h * h * h * (i + 1) * (i + 1) * (i + 1) - h * h * h * i * i * i) + 0.5 *
                        (h * h * (i + 1) * (i + 1) - h * h * i * i) * (5 * b - 5 * a) + h * (-5 * b + 5 * a);
            }if(i == nbOfSegments){
                a = nbOfSegments;
                b = -(i - 1);
                result[i] += (5 * a / 3) * (h * h * h * (i + 1) * (i + 1) * (i + 1) - h * h * h * i * i * i) + 0.5 *
                        (h * h * (i + 1) * (i + 1) - h * h * i * i) * (5 * b - 5 * a) + h * (-5 * b + 5 * a);
            }
        }
        return result;
    }

    private double calculateBIJIntegrate(BaseFunction ei, BaseFunction ej) {
        double result = 0;
        for (int i = 0; i < nbOfSegments; i++) {
            result += ei.getA(i) * ej.getA(i) * 0.5 * (h * h * (i + 1) * (i + 1) - h * h * i * i);
            result += ei.getA(i) * ej.getB(i) * h;
            result += k * ei.getA(i) * ej.getA(i) * h;
        }
        return result;
    }

    private double calculateLIIntegrate(BaseFunction ei) {
        double result = 0;
        for (int i = 0; i < nbOfSegments; i++) {
            /// [x] = h
            /// [x^2/2] = 0.5* (h*h*(i+1)*(i+1) - h*h*i*i)
            /// [x^3/3] = 1/3 *(h*h*h*(i+1)*(i+1)*(i+1) - h*h*h*i*i*i)
            result += (-1) * 5 * ei.getA(i) * 0.5 * (h * h * (i + 1) * (i + 1) - h * h * i * i);
            result += (-1) * 5 * ei.getB(i) * h;
            result += 5 * ei.getA(i) * (1.0 / 3.0) * (h * h * h * (i + 1) * (i + 1) * (i + 1) - h * h * h * i * i * i);
            result += 5 * ei.getB(i) * 0.5 * (h * h * (i + 1) * (i + 1) - h * h * i * i);
            result += 5 * ei.getA(i) * h;

        }
        result += 3 * k * ei.value(1);
        return result;
    }

    public BaseFunction[] getFunctions() {
        return functions;
    }
}
