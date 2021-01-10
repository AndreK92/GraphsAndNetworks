package Algorithmen; 
import java.util.*;
import java.lang.*;
import java.io.*;

import Formen.*;

// Schritt 1: Sortiert alle Kanten Aufsteigend nach Gewichtung
// Schritt 2: Wählt Kante mit kleinster Gewichtung. 
//            Überprüft ob diese Kante einen Zyklus mit dem Spanningtree bildet
// Schritt 3: Wenn kein Zyklus gebildet wird, wird die Kante hinzugefügt
public class Kruskal {

    KantenListe Graph;
    ArrayList<ArrayList<Integer>> kListe;
    int nodeCount = 0;
    int edgeCount = 0;

    public Kruskal(KantenListe kListe)
    {
        this.Graph = kListe;
        this.kListe = kListe.Kanten;
        this.nodeCount = kListe.nodeCount;
        this.edgeCount = kListe.Kanten.size();
    }

    // Subset für union find
    class subset 
    {
        int parent, rank;
    };

    // A utility function to find the subset of an element i
    int find(int parent[], int i)
    {
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }
 
    // A utility function to do union of two subsets
    void Union(int parent[], int x, int y)
    {
        int xset = find(parent, x);
        int yset = find(parent, y);
        parent[xset] = yset;
    }

    // Starte Kruskal Algorithmus
    // Generiert MST
    public void KruskalAlg()
    {
        System.out.println("KRUSKAL"); 

        // Check ob Graph ungerichtet und weighted
        if(Graph.isDirected || !Graph.isWeighted)
        {
            System.out.println("ERROR: Kruskal ist nur mit ungerichteten gewichteten Graphen möglich!"); 
            System.out.println();
            return;
        }

        // Speichert das Resultat, den MST - Minimum Spanning Tree
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        //Schritt 1: Sortiert alle Kanten Aufsteigend nach Gewichtung
        ArrayList<ArrayList<Integer>> kListSort = kListe;
        Collections.sort(kListSort, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> one, ArrayList<Integer> two) {
                return one.get(1).compareTo(two.get(1));
            }
        });
        System.out.println("Sortierte Kanten:"); 
        for (ArrayList<Integer> arrayList : kListSort) {
            System.out.print(arrayList.get(1) + " "); 
        }
        System.out.println(); 
 
        // Allocate memory for creating V subsets
        int parent[] = new int[nodeCount+1];
 
        // Initialize all subsets as single element sets
        for (int i=0; i < nodeCount+1; ++i)
            parent[i]=-1;

        // Anzahl der Kanten -> KnotenAnzahl -1
        for (int i = 0; i < nodeCount - 1; i++) {
            // Schritt 2: Wähle nächst kleine Kante
            ArrayList<Integer> next_edge = kListSort.get(i);
 
            // X: Knoten 1, Y: Knoten 2
            // Check nach Zyklus
            int x = find(parent, next_edge.get(0));
            int y = find(parent, next_edge.get(2));
 
            // Wenn diese Kante mit dem aktuellen MST keinen Zyklus erzeugt
            // Füge sie dem result hinzu
            if (x != y) {
                result.add(next_edge);
                Union(parent, x, y);
            }
            // Else discard the next_edge
        }
 
        // print the contents of result[] to display
        // the built MST
        System.out.println("Following are the edges in "
                           + "the constructed MST");
        int minimumCost = 0;
        for (int i = 0; i < result.size(); ++i)
        {
            System.out.println(result.get(i).get(0) + " -- "
                               + result.get(i).get(2)
                               + " == " + result.get(i).get(1));
            minimumCost += result.get(i).get(1);
        }
        System.out.println("Minimum Cost Spanning Tree "
                           + minimumCost);
    }


}