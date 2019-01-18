package projekt;


public class Gaussian {


    public double [] getResult(double [][] A, double L[], int segmentsNb){

//rozmiar tablicy
        int n=segmentsNb + 1;


        double copyA [][] = new double [A.length][A.length];

//kopiuj A do rozwiazania do copyA

        for (int i = 0; i<A.length; i++)
            for (int j = 0; j<A.length; j++)
                copyA[i][j]=A[i][j];


//kopiuj wektor wyników
        double wCopy [] = new double [L.length];
        for (int i=0; i<L.length; i++){
            wCopy[i]=L[i];
        }

//wyswietl  A do rozwiazania
        for (int j = 0; j<n; j++){
            for (int k=0; k<n; k++){
                System.out.print(A[j][k]+"\t");
            }
            System.out.println("");
        }
        System.out.println("");
        //wyniki
        for (int k=0; k<n; k++){
            System.out.print(L[k]+"\t");
        }
        System.out.println("");


//Metoda Eliminacji Gaussa
        for (int i = 0; i<n-1; i++){
            for (int j = i+1; j<=n-1; j++){
                for (int k = 0; k<n; k++){
                    copyA[j][k]=A[j][k]-(A[i][k]*(A[j][i]/A[i][i]));
                }

                wCopy[j]=L[j]-(L[i]*(A[j][i]/A[i][i]));

                for (int ii = 0; ii<copyA.length; ii++){
                    for (int jj = 0; jj<copyA.length; jj++){
                        A[ii][jj]=copyA[ii][jj];
                    }
                    L[ii]=wCopy[ii];
                }
            }
        }

//Wypisanie wyników
        System.out.println("");
        System.out.println("");
        for (int j = 0; j<n; j++){
            System.out.println(L[j]);
        }
        return L;
    }
}


