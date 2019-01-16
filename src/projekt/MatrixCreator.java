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
            for (int j = 0; j < nbOfSegments + 1; j++)
                result[i][j] = calculateBIJIntegrate(functions[i], functions[j]);
        return result;
    }

    public double [] createMatrixF(){
        double [] result = new double [nbOfSegments + 1];
        for(int i = 0; i< nbOfSegments + 1 ; i++)
            result [i] = calculateLIIntegrate(functions[i]);

        return result;
    }

    private double calculateBIJIntegrate(BaseFunction ei, BaseFunction ej) {
        double result = 0;
        for (int i = 0; i < nbOfSegments; i++) // rozbicie całki na segmenty
            result += ei.getA(i) * (h * (ej.getB(i) + k * ej.getA(i)) +
                    0.5 * ej.getA(i) * (h * h * (i + 1) * (i + 1) - h * h * i * i));

        return result;
    }

    private double calculateLIIntegrate(BaseFunction ei) {
        double result = 0;
        for (int i = 0; i < nbOfSegments; i++) {
            result += (1.0 / 3.0) * (Math.pow(h, 3) * Math.pow(i + 1, 3) - Math.pow(h, 3) * Math.pow(i, 3)) * ei.getA(i);
            result += h * (5 * k * ei.getA(i) - 5 * ei.getB(i));
            result += 0.5 * (h * h * (i + 1) * (i + 1) - h * h * i * i);
        }
        result += k * ei.value(1);

        return result;
    }
    public BaseFunction[] getFunctions(){
        return functions;
    }
}
