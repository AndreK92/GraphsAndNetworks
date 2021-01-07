import java.util.ArrayList;
import java.io.*;

public class KantenListe {
    ArrayList<ArrayList<Integer>> Kanten;
    int nodeCount = 0;

    public KantenListe(int nC)
    {
        Kanten = new ArrayList<ArrayList<Integer>>();
        nodeCount = nC;
    }

    public void addKante(ArrayList<Integer> k)
    {
        Kanten.add(k);
    }

    public void writeDOT()
    {
        try {
            FileWriter myWriter = new FileWriter("./GraphVizData/DOT-KListe.dot");
            myWriter.write("graph ethane {\n    edge [dir=none, color=black] \n");

            for (ArrayList<Integer> is : Kanten) {
                myWriter.write("    "+is.get(0)+" -- "+is.get(1)+";\n");
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
