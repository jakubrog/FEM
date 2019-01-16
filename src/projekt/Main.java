package projekt;
import org.jfree.ui.RefineryUtilities;

public class Main {
    public static void main(String [] args){
        ApproximateFunction u = new ApproximateFunction(2, 1);
        System.out.println(u.getValue(0.2));

        final Chart chart = new Chart("Równanie konwekcji-dyfuzji",u);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}
