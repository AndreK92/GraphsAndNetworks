import java.util.ArrayList;

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
