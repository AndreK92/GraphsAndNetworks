public class Adjazenzmatrix {
    public int[][] adjaMatrix;
    public boolean weighted;

    public Adjazenzmatrix(int nodeCount)
    {
        this.adjaMatrix = new int[nodeCount][nodeCount];
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
}