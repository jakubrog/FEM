package projekt;
import Jama.Matrix;

public class ApproximateFunction {
    private Gaussian gaus = new Gaussian();

    private MatrixCreator creator;
    private double [] result;

    private BaseFunction [] functions;

    public ApproximateFunction(int segmentsNb, double k){
        creator = new MatrixCreator(segmentsNb,k);
        result = gaus.getResult(creator.createMatrixA(), creator.createMatrixF(), segmentsNb);
        functions = creator.getFunctions();
    }

    public double getValue(double x){
        double value = 0;
       // System.out.print("u(x) = ");
        for(int j = 0 ; j < functions.length ; j++) {
            value += functions[j].value(x) * result[j];
           // System.out.println("(a" + j + "X" + " b" +j +") * " + result[j] +" ");
        }

        return value + (1-x)*5;
    }


}
