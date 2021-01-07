import java.util.ArrayList;

public class Adjazenzmatrix {
    public int[][] adjaMatrix;
    public boolean weighted;

    public Adjazenzmatrix(int nodeCount)
    {
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
        return weighted;
    }

    public void setWeight(boolean weight)
    {
        this.weighted = weight;
    }

    public void printAdjMatrix() {
        // for (int[] x : adjaMatrix)
        // {
        //     for (int y : x)
        //     {
        //         System.out.print(y + " ");
        //     }
        //     System.out.println();
        // }

        for (int i = 1; i < adjaMatrix.length; i++) {
            for (int j = 1; j < adjaMatrix.length; j++) {
                System.out.print(adjaMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] convKantenlisteToAdjazenzmatrix(KantenListe kListe)
    {
        int nodeCount = kListe.nodeCount;

        for (int i = 1; i <= nodeCount; i++) {
            for (int j = 1; j <= nodeCount; j++) {

                if (i==j) {
                    continue;
                }

                for (ArrayList<Integer> js : kListe.Kanten) {
                    if (js.contains(i)&&js.contains(j)) {
                        //System.out.println("CHECK ArrayList: "+i+" Contains: "+(i+1)+", "+j+"\n");
                        adjaMatrix[i][j] = 1;
                    }
                }
            }
        }

        return adjaMatrix;
    }
}