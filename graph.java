import java.util.*;

public class graph {

    public ArrayList<Kanten> graphKanten;
    public int nodeCount;

    public graph(int count){
        graphKanten = new ArrayList<>();
        nodeCount = count;
    }

    public void addKante(Kanten n) {
        graphKanten.add(n);
    }

}