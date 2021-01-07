import java.util.*;
import java.io.*;

public class Adjazenzmatrix {
    public int[][] adjaMatrix;
    public boolean weighted;

    public Adjazenzmatrix(int nodeCount)
    {
        this.adjaMatrix = new int[nodeCount+1][nodeCount+1];
        for (int i = 0; i < nodeCount-1; i++) {
            for (int j = 0; j < nodeCount-1; j++) {
                this.adjaMatrix[i][j] = 0;
            }
        }
    }

    public int[][] getAdjazenzmatrix() {
        return this.adjaMatrix;
    }

    public boolean isWeighted()
    {
        return weighted;
    }

    public void setWeight(boolean weight)
    {
        this.weighted = weight;
    }

    public void printAdjMatrix() {
        // for (int[] x : adjaMatrix)
        // {
        //     for (int y : x)
        //     {
        //         System.out.print(y + " ");
        //     }
        //     System.out.println();
        // }

        for (int i = 1; i < adjaMatrix.length; i++) {
            for (int j = 1; j < adjaMatrix.length; j++) {
                System.out.print(adjaMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void writeDOT()
    {
        try {
            FileWriter myWriter = new FileWriter("./GraphVizData/DOT-AdjaMatrix.dot");
            myWriter.write("graph ethane {\n    edge [dir=none, color=black] \n");

            // j = i  weil matrix sich quasi spiegelt und so doppelte einträge vermieden werden
            for (int i = 1; i < adjaMatrix.length; i++) {
                for (int j = i; j < adjaMatrix.length; j++) {
                    System.out.print(adjaMatrix[i][j] + " ");

                    if (adjaMatrix[i][j] == 1) {
                        myWriter.write("    "+i+" -- "+j+";\n");
                    }
                }
                System.out.println();
            }

            myWriter.write("}");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public int[][] convKantenlisteToAdjazenzmatrix(KantenListe kListe)
    {
        int nodeCount = kListe.nodeCount;

        for (int i = 1; i <= nodeCount; i++) {
            for (int j = 1; j <= nodeCount; j++) {

                // Wenn Knoten gleich, prüfe auf Anzahl, wenn größer 1, dann 1 in der Matrix
                if (i==j) {
                    for (ArrayList<Integer> js : kListe.Kanten) {
                        if (Collections.frequency(js, i) > 1) {
                            adjaMatrix[i][j] = 1;
                        }
                    }
                    continue;
                }

                // Prüfe ob ein entsprechendes paar vorhanden ist, dann 1 in der Matrix
                for (ArrayList<Integer> js : kListe.Kanten) {
                    if (js.contains(i) && js.contains(j)) {
                        adjaMatrix[i][j] = 1;
                    }
                }
            }
        }

        return adjaMatrix;
    }
}