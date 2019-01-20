package projekt;
import Jama.Matrix;

public class ApproximateFunction {

    private MatrixCreator creator;
    private Matrix ans;
    private BaseFunction [] functions;

    public ApproximateFunction(int segmentsNb, double k){
        creator = new MatrixCreator(segmentsNb,k);
        functions = creator.getFunctions();
        Matrix lhs = new Matrix(creator.createMatrixA());
        Matrix rhs = new Matrix(creator.createMatrixF(), functions.length);
        ans = lhs.solve(rhs);
    }

    public double getValue(double x){
        double value = 0;
        for(int j = 0 ; j < functions.length   ; j++) {
            value += functions[j].value(x) * ans.get(j, 0);
        }

        return value + (1-x)*5;
    }


}
