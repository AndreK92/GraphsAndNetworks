package Algorithmen; 
import java.util.*; 
import java.lang.*; 
import java.io.*; 

import Formen.*;

// Schritt 1: Erstelle ein sptSet, das alle Knoten enthält, die im SPT (Shortest Path Tree) sind. Also alle Knoten deren minimale Kosten bereits berechnet wurden.
// Schritt 2: Alle Knoten werden mit den Kosten Integer.MAX_VALUE initiert. Der Source Knoten bekommt die Kosten 0, wird also als erstes ausgewählt
// Schritt 3: Solange das sptSet noch nicht alle Knoten enthält
//      a) Wähle einen Knoten U der nicht im sptSet ist, und die minimalen Kosten hat
//      b) Füge diesen Knoten U dem sptSet hinzu
//      c) Aktualisiere die Kosten aller adjazenten Knoten V um die des Knotens U
//         Für jeden Adjaz Knoten V, wenn Summe der Kosten von U und Gewichtung der Kante U - V kleiner als Kosten von V --> aktualisiere Kosten von V
public class Dijkstra { 

    Adjazenzmatrix Graph;
    public int[][] adjaMatrix;
    public int nodeCount = 0;

    public Dijkstra(Adjazenzmatrix adjaMatrix)
    {
        this.Graph = adjaMatrix;
        this.nodeCount = adjaMatrix.nodeCount;
        this.adjaMatrix = adjaMatrix.adjaMatrix;
    }

    // Findet den Knoten mit dem kleinsten Kosten
    // Benutzt dafür Knoten die noch nicht im sptSet sind
    int minDistance(int dist[], Boolean visited[]) 
    { 
        // Setze min auf höchsten INT Wert
        int min = Integer.MAX_VALUE;
        int min_index = -1; 
  
        // Durchläuft alle Knoten
        // Setzt min / index auf Knoten mit kleinsten Kosten
        for (int v = 0; v < nodeCount; v++) 
            if (dist[v]     <= min && 
                visited[v]   == false) { 
                min = dist[v]; 
                min_index = v; 
            } 
  
        // Return minIndex
        return min_index; 
    } 
  
    // Starte Dijkstra Algorithmus SSSP (Single Source Shortest Path)
    public void dijkstra(int graph[][], int src) 
    {
        System.out.println("DIJKSTRA"); 

        // Check ob Graph weighted
        if(!Graph.isWeighted)
        {
            System.out.println("ERROR: Dijkstra ist nur mit gewichteten Graphen möglich!"); 
            System.out.println();
            return;
        }

        // dist enthält die kleinste Distanz von Source zu Knoten i
        int shortDist[] = new int[nodeCount+1]; 
  
        // sptSet[i] ist TRUE falls Knoten i schon im SPT ist
        Boolean visited[] = new Boolean[nodeCount+1]; 
  
        // Initiere Distanzen auf Integer.MAX_VALUE / stpSet false
        for (int i = 0; i <= nodeCount; i++) { 
            shortDist[i] = Integer.MAX_VALUE; 
            visited[i] = false; 
        } 
  
        // Distanz des Source Knotens = 0, so wird Source als erstes gewählt
        shortDist[src] = 0; 
  
        // Durchlaufe alle Knoten und finde kürzte Distanz für all Knoten
        for (int count = 0; count < nodeCount; count++) { 

            // Wähle den Knoten U mit der kleinsten Distanz zu Source
            int u = minDistance(shortDist, visited); 
  
            // Markiert Knoten U als bearbeitet
            visited[u] = true; 
  
            // Aktualisiere Distanz der Adjaz Knoten V von U
            for (int v = 0; v <= nodeCount; v++) 
            {
                // Aktualisert nur wenn:
                if (
                !visited[v] &&                          // Adjaz Knoten V ist nicht in sptSet
                graph[u][v] != 0 &&                     // Kante zwischen Knoten V und U existiert
                shortDist[u] != Integer.MAX_VALUE &&    // Gewichtung von Source zu V über U ist kleiner als dist[v]
                shortDist[u] + graph[u][v] < shortDist[v]) 
                {
                    shortDist[v] = shortDist[u] + graph[u][v]; 
                }
            }
        } 
  
        System.out.println("Knoten \t\t Distanz zu Knoten: "+src); 
        for (int i = 1; i <= nodeCount; i++)
        {
            System.out.println(i + " \t\t " + shortDist[i]); 
            if (i==10) {
                System.out.println("Stoppe PRINT zu viele Knoten fürs Terminal ..."); 
                break;
            }
        }
    } 
} 