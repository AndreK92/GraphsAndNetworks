import java.util.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class parsing {

    public graph readGraph;

    // graphData --> Array of ArrayLists
    // graphData[x]         --> ROW - Node
    // graphData[x].get(y)  --> ArrayList of Edge y of Node x
    // public static class graph{
    //     int nodeCount = 0;
    //     ArrayList<String>[] graphData = null;

    //     public graph(int count, ArrayList<String>[] data)
    //     {
    //         nodeCount = count;
    //         graphData = data;
    //     }

    //     // Simple output to Terminal
    //     public String showData()
    //     {
    //         // create a StringBuilder object 
    //         StringBuilder ret = new StringBuilder(); 
    //         ret.append("SimpleOutput:\n");
    //         // Parse graph data
    //         for (int i = 1; i < nodeCount; i++) { 
    //             //System.out.println("Edges of Node: "+i+"\n");
    //             ret.append("Node: "+i+" -> ");
    //             for (int j = 0; j < graphData[i].size(); j++) { 
    //                 //System.out.print(graphData[i].get(j) + " "); 
    //                 ret.append(graphData[i].get(j) + " ");
    //             } 
    //             //System.out.println(); 
    //             ret.append("\n");
    //         } 
    //         return ret.toString();
    //     }

    //     // Outputs data as EdgeList
    //     public String showEdgeList()
    //     {
    //         // create a StringBuilder object 
    //         StringBuilder ret = new StringBuilder(); 
    //         ret.append("EdgeList:\n");
    //         // Parse graph data
    //         for (int i = 1; i < nodeCount; i++) { 
    //             for (int j = 0; j < graphData[i].size(); j++) { 
    //                 ret.append(i + " "+graphData[i].get(j) + "\n");
    //             } 
    //             //System.out.println(); 
    //             //ret.append("\n");
    //         } 
    //         return ret.toString();
    //     }

    //     // Outputs data as Inzidenzenmatrix
    //     public String showIncidenceMatrix()
    //     {
    //         // create a StringBuilder object 
    //         StringBuilder ret = new StringBuilder(); 
    //         ret.append("IncidenceMatrix:\n");

    //         int edgeCount = 0;
    //         for (int i = 1; i < graphData.length; i++) {
    //             edgeCount += graphData[i].size();
    //             ret.append("E"+i+" ");
    //         }
    //         ret.append("\n");

    //         //System.out.print("EDGECOUNT: "+edgeCount+"\n");

    //         // Parse graph data
    //         for (int i = 1; i < nodeCount; i++) { 
    //             ret.append("V"+i+" ");
    //             for (int j = 1; j < graphData[i].size(); j++) {
    //                 String edge = graphData[i].get(j);

    //                 for (int e = 1; e < edgeCount; e++) { 
    //                     if(Integer.parseInt(graphData[i].get(j)) == i) ret.append("1");
    //                     else ret.append("0");
    //                 }
    //             }
    //             ret.append("\n");
    //         }
    //         ret.append("\n");
    //         return ret.toString();
    //     }
    // }

    // Read file file and return graph with Kanten
    private static graph readFileGraph(String file)
    {
        int nodeCount = 0;
        ArrayList<String>[] readData;
        graph ret = null;
        //node newNode;

        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            
            // Read nodeCount and init array of ArrayList with n rows
            // Rows are NODES
            nodeCount = Integer.parseInt(myReader.nextLine());

            ret = new graph(nodeCount);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                System.out.println(data);

                String[] edge = data.split(" ");

                if(edge.length > 1)
                {
                    // Not weighted
                    if (edge.length == 2) {
                        System.out.println("NOT weighted");

                        // Save to Kanten
                        int startNode = Integer.parseInt(edge[0]);
                        int endNode = Integer.parseInt(edge[1]);

                        // Create new kante with StartNode as Nodename
                        Kanten test = new Kanten(startNode, endNode, -1);

                        ret.addKante(test);
                        
                    }

                    // Weighted
                    if (edge.length == 3) {
                        System.out.println("Weighted");

                        // Save to Kanten
                        int startNode = Integer.parseInt(edge[0]);
                        int endNode = Integer.parseInt(edge[2]);
                        int weight = Integer.parseInt(edge[1]);

                        // Create new kante with StartNode as Nodename
                        Kanten test = new Kanten(startNode, endNode, weight);

                        ret.addKante(test);
                        
                    }

                }
            }
            myReader.close();

            // ret = new graph(nodeCount,readData);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return ret;
    }

    // Read file file and return graph with Kanten
    private static KantenListe readFileKanteliste(String file)
    {
        ArrayList<Integer> nodeCount = new ArrayList<>();
        ArrayList<String>[] readData;
        KantenListe ret = null;
        Boolean weighted = false;
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
                        System.out.println("Weighted");
                        weighted = true;
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

        // ReadFile to Kantenliste
        KantenListe testKantenliste = readFileKanteliste("./Beispielgraphen-20201120/test.txt");
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
    }
}