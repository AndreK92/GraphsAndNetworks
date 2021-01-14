package Algorithmen; 
import java.util.*; 
import java.lang.*; 
import java.io.*; 

import Formen.*;

// Schritt 1: Erstelle ein resSptSet, das alle Knoten enthält, die im SPT (Shortest Path Tree) sind. Also alle Knoten deren minimale Distanz bereits berechnet wurden.
// Schritt 2: Alle Knoten werden mit den Distanz Integer.MAX_VALUE initiert. Der Source Knoten bekommt die Distanz 0, wird also als erstes ausgewählt
// Schritt 3: Solange das resSptSet noch nicht alle Knoten enthält
//      Wähle einen Knoten U der nicht im resSptSet ist, und die minimalen Distanz hat
//      Füge diesen Knoten U dem resSptSet hinzu
//      Aktualisiere die Distanz aller adjazenten Knoten V um die des Knotens U
//         Für jeden Adjaz Knoten V, wenn Summe der Distanz von U und Gewichtung der Kante U - V kleiner als Distanz von V --> aktualisiere Distanz von V
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

    // Findet den Knoten mit dem kleinster Distanz
    // Benutzt dafür Knoten die noch nicht im resSptSet sind
    int minDistance(int dist[], Boolean visited[]) 
    { 
        // Setze min auf höchsten INT Wert
        int min = Integer.MAX_VALUE;
        int minIndex = -1; 
  
        // Durchläuft alle Knoten
        // Setzt min / index auf Knoten mit kleinster Distanz
        for (int v = 0; v < nodeCount; v++) 
            if (dist[v]     <= min && 
                visited[v]   == false) { 
                min = dist[v]; 
                minIndex = v; 
            } 
  
        // Return minIndex
        return minIndex; 
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
  
        // resSptSet[i] ist TRUE falls Knoten i schon im SPT ist
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

            // Wähle den Knoten U mit der kleinster Distanz zu Source (Erster Knoten ist Source weil 0)
            int u = minDistance(shortDist, visited); 
  
            // Markiert Knoten U als bearbeitet
            visited[u] = true; 
  
            // Aktualisiere Distanz der Adjaz Knoten V von U
            for (int v = 0; v <= nodeCount; v++) 
            {
                // Aktualisert nur wenn:
                if (
                !visited[v] &&                          // Adjaz Knoten V ist nicht in resSptSet
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
            if (i > 50) {
                System.out.println("Stoppe PRINT zu viele Knoten fürs Terminal ..."); 
                break;
            }
        }
    } 
} 