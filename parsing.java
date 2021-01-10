import java.util.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

import Algorithmen.*;
import Formen.*;

public class parsing {

    // Read file file and return graph with Kanten
    private static KantenListe readFileKanteliste(String file)
    {
        ArrayList<Integer> nodeCount = new ArrayList<>();
        ArrayList<String>[] readData;
        KantenListe ret = null;
        //node newNode;

        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            
            // Read nodeCount and init array of ArrayList with n rows
            // Rows are NODES

            ret = new KantenListe(Integer.parseInt(myReader.nextLine()));

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                //System.out.println(data);
                String[] buf = data.split(" ");
                ArrayList<Integer> edge = new ArrayList<>();
                for (int i = 0; i < buf.length; i++) {
                    edge.add(Integer.parseInt(buf[i]));
                }

                if(edge.size() > 1)
                {
                    if (edge.size() == 3) {
                        //System.out.println("Weighted");
                        ret.isWeighted = true;
                    }
                    ret.addKante(edge);
                }
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return ret;
    }

    public static void main(String[] args) {

        String file = "";

        // ReadFile to Kantenliste
        file = "./Beispielgraphen-20201120/test.txt";
        //file = "./Beispielgraphen-20201120/testWeighted.txt";
        //file = "./Beispielgraphen-20201120/testWeightedKrusk.txt";

        //file = "./Beispielgraphen-20201120/bellmannford.txt";
        //file = "./Beispielgraphen-20201120/petersen.txt";
        //file = "./Beispielgraphen-20201120/k3_3.txt";
        //file = "./Beispielgraphen-20201120/k5.txt";
        //file = "./Beispielgraphen-20201120/ladder.txt";

        KantenListe testKantenliste = readFileKanteliste(file);
        //testKantenliste.isDirected = true;

        testKantenliste.printKantenListe();
        testKantenliste.writeDOT();
        
        //InzidenzMatrix test = new InzidenzMatrix();
        //int[][] newIncidenceArray = test.convertKantenlisteToInzidenzMatrix(testKantenliste);
        //System.out.println(newIncidenceArray.toString());

        Adjazenzmatrix adjaMatrix = new Adjazenzmatrix(testKantenliste.nodeCount);
        adjaMatrix.convKantenlisteToAdjazenzmatrix(testKantenliste);
        adjaMatrix.printAdjMatrix();
        adjaMatrix.writeDOT();

        AdjListe adjListe = new AdjListe(testKantenliste.nodeCount);
        adjListe.convKantenlisteToAdjazenzListe(testKantenliste);
        adjListe.printAdjListe();
        adjListe.writeDOT();

        // Topsort mit der AdjaListe
        TopSort tSort = new TopSort(adjListe);
        tSort.topSort();

        // Kruskal mit der KListe
        Kruskal kruskal = new Kruskal(testKantenliste);
        kruskal.KruskalMST();

        Dijkstra dijkstra = new Dijkstra(adjaMatrix); 
        dijkstra.dijkstra(adjaMatrix.adjaMatrix, 1);
    }
}