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

    // Finde die Wurzel des Knotens.
    // Durchsucht Rekursiv die subsets, wenn gefunden return
    int find(int parent[], int i)
    {
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }
 
    // Vereinigt zwei subsets deren Wurzeln gleich sind
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
        int count = 0;
        for (ArrayList<Integer> arrayList : kListSort) {
            if (count > 50) {
                System.out.println("Stoppe PRINT zu viele Knoten fürs Terminal ..."); 
                break;
            }
            System.out.print(arrayList.get(1) + " "); 
            count++;
        }
        System.out.println(); 
 
        // Subsets
        int parent[] = new int[nodeCount+1];
 
        // Setze alle subsets auf -1
        for (int i = 0; i < nodeCount+1; ++i)
            parent[i]=-1;

        // Durchläuft alle Kanten des Graphen
        for (int i = 0; i < edgeCount; i++) {
            // Schritt 2: Wähle nächst kleine Kante
            ArrayList<Integer> next_edge = kListSort.get(i);
 
            // X: Knoten 1, Y: Knoten 2
            // Suche nach Wurzeln der beiden Knoten
            int x = find(parent, next_edge.get(0));
            int y = find(parent, next_edge.get(2));
 
            // Wenn diese Kante mit dem aktuellen MST keinen Zyklus erzeugt
            // Füge sie dem result hinzu, wenn beide Knoten nicht die gleiche Wurzel haben
            if (x != y) {
                result.add(next_edge);
                Union(parent, x, y);
            }
            // Verwerfe Kante
        }
 
        System.out.println("Kanten des MST");
        int minCost = 0;
        for (int i = 0; i < result.size(); ++i)
        {
            if (i > 50) {
                System.out.println("Stoppe PRINT zu viele Knoten fürs Terminal ..."); 
                break;
            }

            System.out.println(result.get(i).get(0) + " -- "
                               + result.get(i).get(2)
                               + " == " + result.get(i).get(1));
            minCost += result.get(i).get(1);
        }
        System.out.println("Minimale Kosten "+ minCost);
    }
}