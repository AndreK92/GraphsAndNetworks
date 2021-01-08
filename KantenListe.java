import java.util.ArrayList;
import java.io.*;

public class KantenListe {
    ArrayList<ArrayList<Integer>> Kanten;
    int nodeCount = 0;

    public ArrayList<Integer> edgeWeights;
    Boolean isWeighted = false;

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
            myWriter.write("graph ethane {\n    edge [dir=none, color=black] \n");

            for (int i = 0; i < Kanten.size(); i++) {
                ArrayList<Integer> is = Kanten.get(i);
                if (isWeighted) {
                    myWriter.write("    "+is.get(0)+" -- "+is.get(2)+"[label=\""+edgeWeights.get(i)+"\"];\n");
                }
                else{
                    myWriter.write("    "+is.get(0)+" -- "+is.get(1)+";\n");
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
        for (ArrayList<Integer> is : Kanten) {
            for (int i = 0; i < is.size(); i++) {
                System.out.print(" "+is.get(i));
            }
            System.out.print("\n");
        }
    }
}
