package projekt;
import java.util.Scanner;
import org.jfree.ui.RefineryUtilities;

public class Main {
    public static void main(String [] args) {

        System.out.print("Wprowadź stałą k: ");
        Scanner read = new Scanner(System.in);

        while (!read.hasNextDouble()){
            System.out.print("Niepoprawna stała, wprowadź stałą ponownie: ");
            read = new Scanner(System.in);
        }
        double k = read.nextDouble();

        System.out.print("Wprowadź liczbę przedziałów: ");
        read = new Scanner(System.in);


        while(!read.hasNextInt()) {
            System.out.print("Niepoprawna ilość przedziałów, wprowadź jeszcze raz: ");
            read = new Scanner(System.in);
        }

        int n = read.nextInt();

        while(n <= 0 ){
            System.out.print("Niepoprawna ilość przedziałów, wprowadź jeszcze raz: ");
            read = new Scanner(System.in);
            if(read.hasNextInt())
                n = read.nextInt();
        }

        ApproximateFunction u = new ApproximateFunction(n, k);

        final Chart chart = new Chart( "Równanie konwekcji-dyfuzji " + "(k=  " + k + " , n= " + n +")",u);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}
