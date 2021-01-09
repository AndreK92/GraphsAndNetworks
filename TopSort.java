import java.util.*;
import java.io.*;


// Topologische Sortierung mit einer AdjaListe
public class TopSort {
    //
    ArrayList<ArrayList<Integer>> adjaListe;
    int nodeCount = 0;

    public TopSort(ArrayList<ArrayList<Integer>> Graph)
    {
        this.adjaListe = Graph;
        this.nodeCount = Graph.size()+1;
    }

    // Rekursive Funktion, die von topSot() verwendet wird
    void topSortU(int v, boolean nodeVisited[], Stack<Integer> stack) 
    { 

        // Den aktuellen Knoten als überprüft markieren
        nodeVisited[v] = true; 
        // Aktueller Knoten
        Integer i; 

        // Rekursion für alle Knoten Adjazent zu aktuellem Knoten
        Iterator<Integer> it = adjaListe.get(v-1).iterator(); 
        while (it.hasNext()) { 
            i = it.next(); 
            if (!nodeVisited[i]) 
                topSortU(i, nodeVisited, stack); 
        } 

        // Füge aktuellen Knoten dem Stack hinzu
        stack.push(new Integer(v)); 
    } 
  
    // TopSort Funktion, die topSortU() benutzt
    void topSort() 
    { 
        Stack<Integer> stack = new Stack<Integer>(); 
  
        // Markiert alle Knoten als nicht überprüft
        boolean visited[] = new boolean[nodeCount]; 
        for (int i = 0; i < nodeCount; i++) 
            visited[i] = false; 
  
        // Starte topSort über topSortU
         // Durchläuft alle Knoten nacheinander 1,2,3,4,5
        for (int i = 1; i < nodeCount; i++) 
            if (visited[i] == false) 
                topSortU(i, visited, stack); 
  
        // Ausgabe
        while (stack.empty() == false) 
            System.out.print(stack.pop() + " "); 
    } 
}
