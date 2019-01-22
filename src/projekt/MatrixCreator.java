package projekt;


import java.lang.reflect.Array;
import java.util.Arrays;

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
                result[i][j] = 0;
                if (i == j)
                    result[i][j] = 2 * k * nbOfSegments;
                if (i == j + 1)
                    result[i][j] = 0.5 - k * nbOfSegments;
                if (i == j - 1)
                    result[i][j] = -0.5 - k * nbOfSegments;
            }
        for (int i = 0; i < nbOfSegments + 1; i++){
           result[i][0] = 0;
            result[0][i] = 0;
        }
        result[0][0] = 1;

        result[nbOfSegments][nbOfSegments] = k*nbOfSegments + 0.5;
/*
        for(int i =0 ; i<=nbOfSegments ;i++) {
            for (int j = 0; j <= nbOfSegments; j++)
                System.out.print(result[i][j] + "    ");
            System.out.println(" ");
        }
*/
        return result;
    }

    public double[] createMatrixF() {
        double a, b, h_min, h_max;
        double[] result = new double[nbOfSegments + 1];
        for (int i = 0; i < nbOfSegments + 1; i++) {
            if (i != 0 && i != nbOfSegments) {
                for (int j = 0; j < 2; j++) {
                    if (j % 2 == 0) {
                        a = nbOfSegments;
                        b = -(i - 1);
                        h_min = h * i ;
                        h_max = h *(i+1);
                    } else {
                        a = -nbOfSegments;
                        b = i + 1;
                        h_min = h * i ;
                        h_max = h *(i+1);
                    }
                    result[i] += (5 * a / 3) * (h_max*h_max*h_max - h_min*h_min*h_min) + 0.5 *
                            (h_max*h_max - h_min*h_min) * (5 * b - 5 * a) + h * (-5 * b + 5 * a);
                }
            }
            if (i == 0) {
                a = -nbOfSegments;
                b = i + 1;
                result[i] += (5 * a / 3) * (h * h * h * (i + 1) * (i + 1) * (i + 1) - h * h * h * i * i * i) + 0.5 *
                        (h * h * (i + 1) * (i + 1) - h * h * i * i) * (5 * b - 5 * a) + h * (-5 * b + 5 * a);
            }
            if (i == nbOfSegments) {
                a = nbOfSegments;
                b = -(i - 1);
                result[i] += (5 * a / 3) * (h * h * h * i*i*i - h * h * h * (i-1) * (i-1) * (i-1)) + 0.5 *
                        (h * h * i * i  - h * h * (i-1) * (i-1) ) * (5 * b - 5 * a) + h * (-5 * b + 5 * a)
                        + 3 * k;
            }
        }
        return result;
    }


    public BaseFunction[] getFunctions() {
        return functions;
    }
}
