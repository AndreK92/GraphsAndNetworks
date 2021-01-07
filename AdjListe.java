import java.util.*;

public class AdjListe {
    public ArrayList<ArrayList<Integer>> adjaListe;
    public boolean weighted;

    public AdjListe(int nodeCount)
    {
        this.adjaListe = new ArrayList<ArrayList<Integer>>();
    }

    public void printAdjListe() {

        for (int i = 0; i < adjaListe.size(); i++) {
            ArrayList<Integer> buf = adjaListe.get(i);
            for (int j = 0; j < buf.size(); j++) {
                System.out.print(buf.get(j) + " ");
            }
            System.out.println();
        }
    }

    public void convKantenlisteToAdjazenzListe(KantenListe kListe)
    {
        int nodeCount = kListe.nodeCount;

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
        //System.out.println(""+adjaListe.get(0).get(0));
        //System.out.println();
    }
}