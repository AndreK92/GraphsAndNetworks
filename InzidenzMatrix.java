import java.util.ArrayList;

public class InzidenzMatrix {
    public int[][] inzMatrix;

    public void InzidenzeMatrix() {

    }

    public void printKantenlisteToInzidenzMatrix(KantenListe kListe)
    {
        int nodeCount = kListe.nodeCount;
        int edgeCount = 0;

        for (int i = 0; i < kListe.Kanten.size(); i++) {
            edgeCount += kListe.Kanten.get(i).size() / 2; 
        }

        inzMatrix = new int[nodeCount][edgeCount];

        for (ArrayList ed : kListe.Kanten) {
            System.out.print("  "+ed.toString());
        }
        System.out.print("\n");
        for (int i = 1; i < nodeCount+1; i++) {

            System.out.print(i+" |   ");
            for (int j = 0; j < edgeCount; j++) {
                ArrayList<Integer> nodeKanten = kListe.Kanten.get(j);

                if (nodeKanten.contains(i)) {
                    System.out.print("1      ");
                }
                else {
                    System.out.print("0      ");
                }
            }
            System.out.print("\n");
        }

    }

    public int[][] convertKantenlisteToInzidenzMatrix(KantenListe kListe)
    {
        int nodeCount = kListe.nodeCount;
        int edgeCount = 0;

        for (int i = 0; i < kListe.Kanten.size(); i++) {
            edgeCount += kListe.Kanten.get(i).size() / 2; 
        }

        inzMatrix = new int[nodeCount][edgeCount];

        for (int i = 0; i < nodeCount; i++) {


            for (int j = 0; j < edgeCount; j++) {
                ArrayList<Integer> nodeKanten = kListe.Kanten.get(j);

                if (nodeKanten.contains(i)) {
                    inzMatrix[i][j] = 1;
                }
                else {
                    inzMatrix[i][j] = 0;
                }
            }
        }
        System.out.println("");
        return inzMatrix;
    }

    public int[][] getIncidenceMatrix()
    {
        return inzMatrix;
    }
}
