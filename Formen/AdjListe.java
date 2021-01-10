package Formen;
import java.util.*;
import java.io.*;

public class AdjListe {
    public ArrayList<ArrayList<Integer>> adjaListe;

    public ArrayList<Integer> edgeWeights;
    public Boolean isWeighted = false;

    public Boolean isDirected = false;

    public AdjListe(int nodeCount)
    {
        this.adjaListe = new ArrayList<ArrayList<Integer>>();
    }

    public void printAdjListe() 
    {
        System.out.println("PRINT: AdjaListe");
        for (int i = 0; i < adjaListe.size(); i++) {
            ArrayList<Integer> buf = adjaListe.get(i);
            for (int j = 0; j < buf.size(); j++) {
                System.out.print(buf.get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void writeDOT()
    {
        try {
            FileWriter myWriter = new FileWriter("./GraphVizData/DOT-AdjaList.dot");

            String dir = "";
            if (isDirected) {
                myWriter.write("digraph ethane {\n");
                dir = " -> ";
            }
            else{
                myWriter.write("graph ethane {\n");
                dir = " -- ";
            }

            Iterator<Integer> iter = null;
            // ArrayList um doppelte Einträge zu vermeiden
            ArrayList<String> test = new ArrayList();
            for (int i = 0; i < adjaListe.size(); i++) {
                ArrayList<Integer> buf = adjaListe.get(i);
                for (int j = 0; j < buf.size(); j++) {
                    String toWrite      = "";
                    String invToWrite   = "";
                    
                    // Check gewichtet
                    if (isWeighted) {
                        iter = edgeWeights.iterator(); 
                        int weight = edgeWeights.get(i);
                        toWrite = "    "+(i+1)+dir+buf.get(j)+"";
                        invToWrite = "    "+buf.get(j)+dir+(i+1)+"";

                        // Doppelte Einträge zu vermeiden
                        if (!test.contains(invToWrite)) {
                            test.add(toWrite);
                        }
                    }
                    else{
                        toWrite = "    "+(i+1)+dir+buf.get(j)+";\n";
                        invToWrite = "    "+buf.get(j)+dir+(i+1)+";\n";

                        // Doppelte Einträge zu vermeiden
                        if (!test.contains(invToWrite)) {
                            test.add(toWrite);
                        }
                    }
                }
            }

            for (String string : test) {
                if (isWeighted) {
                    string += "[label=\""+iter.next()+"\"];\n";
                }
                myWriter.write(string);
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

    public void convKantenlisteToAdjazenzListe(KantenListe kListe)
    {
        if (kListe.isDirected) {
            isDirected = true;
            convKlisteToAdjaListeDir(kListe);
            return;
        }

        int nodeCount = kListe.nodeCount;

        // Check ob Gewichteter Graph
        if (kListe.isWeighted) {
            // Lese Weight und speichern
            edgeWeights = kListe.edgeWeights;
            isWeighted = true;

            for (int i = 1; i <= nodeCount; i++) {
                ArrayList<Integer> buf = new ArrayList<>();
                for (int j = 1; j <= nodeCount; j++) {
                    // Wenn Knoten gleich, prüfe auf Anzahl, wenn größer 1, dann 1 in der Matrix
                    if (i==j) {
                        for (ArrayList<Integer> js : kListe.Kanten) {
                            if (js.get(0) == i && js.get(2) == i) {
                                buf.add(j);
                            }
                        }
                        continue;
                    }
    
                    // Prüfe ob ein entsprechendes paar vorhanden ist, dann 1 in der Matrix
                    for (ArrayList<Integer> js : kListe.Kanten) {
                        if ((js.get(0) == i && js.get(2) == j) ||
                            (js.get(0) == j && js.get(2) == i)) {
                            buf.add(j);
                        }
                    }
                }
                adjaListe.add(buf);
            }
        }

        else
        {
            for (int i = 1; i <= nodeCount; i++) {
                ArrayList<Integer> buf = new ArrayList<>();
                for (int j = 1; j <= nodeCount; j++) {
    
                    if (i==j) {
                        for (ArrayList<Integer> js : kListe.Kanten) {
                            if (Collections.frequency(js, i) > 1) {
                                buf.add(j);
                            }
                        }
                        continue;
                    }
    
                    for (ArrayList<Integer> js : kListe.Kanten) {
                        if (js.contains(i) && js.contains(j)) {
                            //System.out.println("CHECK ArrayList: "+i+" Contains: "+i+", "+j+"\n");
                            buf.add(j);
                        }
                    }
                }
                adjaListe.add(buf);
            }
        }
    }

    /*Konvertiert DIREKTEN Graph Kantenliste in AdjaListe
    js.get(0) == i && js.get(1) == j ist der unterschied zur anderen Funktion, 
    so werden nur ausgehende Edges in die Liste aufgenommen
    */
    public void convKlisteToAdjaListeDir(KantenListe kListe)
    {
        System.out.println("Covert to DIRECTED List");
        int nodeCount = kListe.nodeCount;

        // Check ob Gewichteter Graph
        if (kListe.isWeighted) {
            // Lese Weight und speichern
            edgeWeights = kListe.edgeWeights;
            isWeighted = true;

            for (int i = 1; i <= nodeCount; i++) {
                ArrayList<Integer> buf = new ArrayList<>();
                for (int j = 1; j <= nodeCount; j++) {
                    // Wenn Knoten gleich, prüfe auf Anzahl, wenn größer 1, dann 1 in der Matrix
                    if (i==j) {
                        for (ArrayList<Integer> js : kListe.Kanten) {
                            if (js.get(0) == i && js.get(2) == i) {
                                buf.add(j);
                            }
                        }
                        continue;
                    }

                    // Prüfe ob ein entsprechendes paar vorhanden ist, dann 1 in der Matrix
                    for (ArrayList<Integer> js : kListe.Kanten) {
                        if ((js.get(0) == i && js.get(2) == j)) {
                            buf.add(j);
                        }
                    }
                }
                adjaListe.add(buf);
            }
        }

        else
        {
            for (int i = 1; i <= nodeCount; i++) {
                ArrayList<Integer> buf = new ArrayList<>();
                for (int j = 1; j <= nodeCount; j++) {
    
                    if (i==j) {
                        for (ArrayList<Integer> js : kListe.Kanten) {
                            if (Collections.frequency(js, i) > 1) {
                                buf.add(j);
                            }
                        }
                        continue;
                    }
    
                    for (ArrayList<Integer> js : kListe.Kanten) {
                        if (js.get(0) == i && js.get(1) == j) {
                            buf.add(j);
                        }
                    }
                }
                adjaListe.add(buf);
            }
        }
    }
}