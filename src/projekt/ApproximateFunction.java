package projekt;

public class ApproximateFunction {
    private Gaussian gaus = new Gaussian();
    private MatrixCreator creator;
    private double [] result;
    private int segmentsNb;
    private int h;
    private BaseFunction [] functions;

    public ApproximateFunction(int segmentsNb, double k){
        creator = new MatrixCreator(segmentsNb,k);
        result = gaus.getResult(creator.createMatrixA(), creator.createMatrixF(), segmentsNb);
        this.segmentsNb = segmentsNb;
        h = 1/segmentsNb;
        functions = creator.getFunctions();

    }

    public double getValue(double x){
        double value = 0;
        for(int j = 0 ; j < functions.length ; j++)
                    value +=  functions[j].value(x)*result[j];
        return value + (1-x)*5;
    }


}
