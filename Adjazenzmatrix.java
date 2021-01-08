import java.util.*;
import java.io.*;

public class Adjazenzmatrix {
    public int[][] adjaMatrix;

    public ArrayList<Integer> edgeWeights;
    Boolean isWeighted = false;

    Boolean isDirected = false;

    public Adjazenzmatrix(int nodeCount)
    {
        this.edgeWeights = new ArrayList<>();
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
        return isWeighted;
    }

    public void setWeight(boolean weight)
    {
        this.isWeighted = weight;
    }

    public void printAdjMatrix() {
        System.out.println("PRINT: AdjaMatrix");
        for (int i = 1; i < adjaMatrix.length; i++) {
            for (int j = 1; j < adjaMatrix.length; j++) {
                System.out.print(adjaMatrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.print("\n");
    }

    public void writeDOT()
    {
        try {
            FileWriter myWriter = new FileWriter("./GraphVizData/DOT-AdjaMatrix.dot");

            String dir = "";
            if (isDirected) {
                myWriter.write("digraph ethane {\n");
                dir = " -> ";
            }
            else{
                myWriter.write("graph ethane {\n");
                dir = " -- ";
            }

            Iterator<Integer> iter = edgeWeights.iterator(); 
            // j = i  weil matrix sich quasi spiegelt und so doppelte einträge vermieden werden
            for (int i = 1; i < adjaMatrix.length; i++) {
                for (int j = i; j < adjaMatrix.length; j++) {
                    System.out.print(adjaMatrix[i][j] + " ");

                    if (adjaMatrix[i][j] == 1) {
                        if (isWeighted) {
                            myWriter.write("    "+i+dir+j+"[label=\""+iter.next()+"\"];\n");
                        }
                        else{
                            myWriter.write("    "+i+dir+j+";\n");
                        }
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

    public void convKantenlisteToAdjazenzmatrix(KantenListe kListe)
    {
        if (kListe.isDirected) {
            isDirected = true;
            convKListeToAdjazMatrix(kListe);
            return;
        }

        int nodeCount = kListe.nodeCount;

        // Check ob Gewichteter Graph
        if (kListe.isWeighted) {
            // Lese Weight und speichern
            edgeWeights = kListe.edgeWeights;
            isWeighted = true;

            for (int i = 1; i <= nodeCount; i++) {
                for (int j = 1; j <= nodeCount; j++) {
    
                    // Wenn Knoten gleich, prüfe auf Anzahl, wenn größer 1, dann 1 in der Matrix
                    if (i==j) {
                        for (ArrayList<Integer> js : kListe.Kanten) {
                            if (js.get(0) == i && js.get(2) == i) {
                                adjaMatrix[i][j] = 1;
                            }
                        }
                        continue;
                    }
    
                    // Prüfe ob ein entsprechendes paar vorhanden ist, dann 1 in der Matrix
                    for (ArrayList<Integer> js : kListe.Kanten) {
                        if ((js.get(0) == i && js.get(2) == j) ||
                            (js.get(0) == j && js.get(2) == i)) {
                            adjaMatrix[i][j] = 1;
                        }
                    }
                }
            }
            System.out.println();
        }
        else{
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
            System.out.println();
        }
    }

    // Konvertiert DIREKTEN Graph Kantenliste in AdjaMatrix
    // j = i ist der unterschied zur anderen FUnktion, Matrix wird quasi in der hälfte geteilt
    public int[][] convKListeToAdjazMatrix(KantenListe kListe)
    {
        System.out.println("Covert to DIRECTED Matrix");
        int nodeCount = kListe.nodeCount;

        // Check ob Gewichteter Graph
        if (kListe.isWeighted) {
            // Lese Weight und speichern
            edgeWeights = kListe.edgeWeights;
            isWeighted = true;

            for (int i = 1; i <= nodeCount; i++) {
                for (int j = i; j <= nodeCount; j++) {
    
                    // Wenn Knoten gleich, prüfe auf Anzahl, wenn größer 1, dann 1 in der Matrix
                    if (i==j) {
                        for (ArrayList<Integer> js : kListe.Kanten) {
                            if (js.get(0) == i && js.get(2) == i) {
                                adjaMatrix[i][j] = 1;
                            }
                        }
                        continue;
                    }
    
                    // Prüfe ob ein entsprechendes paar vorhanden ist, dann 1 in der Matrix
                    for (ArrayList<Integer> js : kListe.Kanten) {
                        if ((js.get(0) == i && js.get(2) == j) ||
                            (js.get(0) == j && js.get(2) == i)) {
                            adjaMatrix[i][j] = 1;
                        }
                    }
                }
            }
            System.out.println();
        }
        else{
            for (int i = 1; i <= nodeCount; i++) {
                for (int j = i; j <= nodeCount; j++) {

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
            System.out.println();
        }


        return adjaMatrix;
    }
}