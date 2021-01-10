package Formen;
import java.util.ArrayList;
import java.io.*;

public class KantenListe {
    public ArrayList<ArrayList<Integer>> Kanten;
    public int nodeCount = 0;

    public ArrayList<Integer> edgeWeights;
    public Boolean isWeighted = false;

    public Boolean isDirected = false;

    public KantenListe(int nC)
    {
        Kanten = new ArrayList<ArrayList<Integer>>();
        edgeWeights = new ArrayList<>();
        nodeCount = nC;
    }

    public void addKante(ArrayList<Integer> k)
    {
        if (isWeighted) {
            edgeWeights.add(k.get(1));
        }
        Kanten.add(k);
    }

    public void writeDOT()
    {
        try {
            FileWriter myWriter = new FileWriter("./GraphVizData/DOT-KListe.dot");
            
            String dir = "";
            if (isDirected) {
                myWriter.write("digraph ethane {\n");
                dir = " -> ";
            }
            else{
                myWriter.write("graph ethane {\n");
                dir = " -- ";
            }

            for (int i = 0; i < Kanten.size(); i++) {
                ArrayList<Integer> is = Kanten.get(i);
                if (isWeighted) {
                    myWriter.write("    "+is.get(0)+dir+is.get(2)+"[label=\""+edgeWeights.get(i)+"\"];\n");
                }
                else{
                    myWriter.write("    "+is.get(0)+dir+is.get(1)+";\n");
                }
            }

            myWriter.write("}");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

    public void writeDOTTEST()
    {
        try {
            FileWriter myWriter = new FileWriter("./GraphVizData/filename.dot");
            myWriter.write("graph ethane {\n    edge [dir=none, color=black] \n");
            myWriter.write("    1 -- 1;\n");
            myWriter.write("}");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

    public void printKantenListe()
    {
        System.out.println("PRINT: KLISTE");
        for (ArrayList<Integer> is : Kanten) {
            for (int i = 0; i < is.size(); i++) {
                System.out.print(" "+is.get(i));
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
