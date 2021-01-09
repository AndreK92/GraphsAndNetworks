import java.util.*;

public class graph2 {

    public ArrayList<Kanten> graphKanten;
    public int nodeCount;

    public graph2(int count){
        graphKanten = new ArrayList<>();
        nodeCount = count;
    }

    public void addKante(Kanten n) {
        graphKanten.add(n);
    }

}