
package Algorithmen; 
import java.util.*;
import java.io.*;

import Formen.*;

// Topologische Sortierung mit einer AdjaListe
// Für einen Gerichteten Graphen G gibt es eine anordnung seiner Knoten, sodass
// jede gerichtete Kante U -> V wobei U vor V angeordnet ist
public class TopSort {

    AdjListe Graph;
    ArrayList<ArrayList<Integer>> adjaListe;
    int nodeCount = 0;

    public TopSort(AdjListe adjListe)
    {
        this.Graph = adjListe;
        this.adjaListe = adjListe.adjaListe;
        this.nodeCount = adjListe.adjaListe.size()+1;
    }

    // Rekursive Funktion, die von topSort() verwendet wird
    void topSortU(int v, boolean nodeVisited[], Stack<Integer> stack) 
    { 
        // Den aktuellen Knoten als überprüft markieren
        nodeVisited[v] = true; 
        // Aktueller Knoten
        int i; 

        // Rekursion für alle Knoten Adjazent zu aktuellem Knoten
        // Knoten wird nur in den Stack gepusht, wenn alle adjazenten Knoten schon im Stack sind
        Iterator<Integer> it = adjaListe.get(v-1).iterator(); 
        while (it.hasNext()) { 
            // Wählt adjazenten Knoten und führt Rekursion aus, wenn noch nicht überprüft
            i = it.next(); 
            if (!nodeVisited[i]) 
                topSortU(i, nodeVisited, stack); 
        } 

        // Füge aktuellen Knoten dem Stack hinzu
        stack.push(v); 
    } 
  
    // TopSort Funktion, die topSortU() benutzt
    public void topSort() 
    { 
        System.out.println("TOPSORT");
        Stack<Integer> stack = new Stack<Integer>(); 
  
        // Check ob Graph Gerichtet ist
        if(!Graph.isDirected)
        {
            System.out.println("ERROR: TopSort ist nur mit Gerichteten Graphen möglich!"); 
            System.out.println();
            return;
        }

        // Markiert alle Knoten als nicht überprüft
        boolean visited[] = new boolean[nodeCount]; 
        for (int i = 0; i < nodeCount; i++) 
            visited[i] = false; 
  
        // Starte topSort über topSortU
        // Durchläuft alle Knoten nacheinander 1,2,3,4,5 ..
        for (int i = 1; i < nodeCount; i++) 
            if (visited[i] == false) 
                topSortU(i, visited, stack); 
  
        // Ausgabe
        while (stack.empty() == false) 
            System.out.print(stack.pop() + " "); 
        System.out.println();
    } 
}
