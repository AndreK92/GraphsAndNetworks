import java.util.*;
import java.lang.*;
import java.io.*;

// 1. Sort all the edges in non-decreasing order of their weight. 
// 2. Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far. If cycle is not formed, include this edge. Else, discard it. 
// 3. Repeat step#2 until there are (V-1) edges in the spanning tree.
public class Kruskal {

    ArrayList<ArrayList<Integer>> kListe;
    int nodeCount = 0;
    int edgeCount = 0;

    public Kruskal(KantenListe kListe)
    {
        this.kListe = kListe.Kanten;
        this.nodeCount = kListe.nodeCount;
        this.edgeCount = kListe.Kanten.size();
    }

    // A class to represent a graph edge
    class Edge implements Comparable<Edge> 
    {
        int src, dest, weight;
 
        // Comparator function used for 
        // sorting edgesbased on their weight
        public int compareTo(Edge compareEdge)
        {
            return this.weight - compareEdge.weight;
        }
    };

    // A class to represent a subset for 
    // union-find
    class subset 
    {
        int parent, rank;
    };

        // A utility function to find set of an 
    // element i (uses path compression technique)
    int find(subset subsets[], int i)
    {
        // find root and make root as parent of i 
        // (path compression)
        if (subsets[i].parent != i)
            subsets[i].parent
                = find(subsets, subsets[i].parent);
 
        return subsets[i].parent;
    }
 
    // A function that does union of two sets 
    // of x and y (uses union by rank)
    void Union(subset subsets[], int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
 
        // Attach smaller rank tree under root 
        // of high rank tree (Union by Rank)
        if (subsets[xroot].rank 
            < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank 
                 > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
 
        // If ranks are same, then make one as 
        // root and increment its rank by one
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    Edge edge[]; // collection of all edges

    // The main function to construct MST using Kruskal's
    // algorithm
    void KruskalMST()
    {
        System.out.println("KRUSKAL"); 

        // Tnis will store the resultant MST
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
       
        // An index variable, used for result[]
        int e = 0; 
       
        // An index variable, used for sorted edges
        // int i = 0; 
        // for (i = 0; i < nodeCount; ++i)
        //     result.add(new ArrayList<Integer>());
 
        // Step 1:  Sort all the edges in non-decreasing
        // order of their weight.  If we are not allowed to
        // change the given graph, we can create a copy of
        // array of edges
        ArrayList<ArrayList<Integer>> kListSort = kListe;
        Collections.sort(kListSort, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> one, ArrayList<Integer> two) {
                return one.get(1).compareTo(two.get(1));
            }
        });
        for (ArrayList<Integer> arrayList : kListSort) {
            System.out.print(arrayList.get(1) + " "); 
        }
        System.out.println(); 
        //Arrays.sort(edge);
 
        // Allocate memory for creating V ssubsets
        subset subsets[] = new subset[nodeCount];
        for (int i = 0; i < nodeCount; ++i)
            subsets[i] = new subset();
 
        // Create V subsets with single elements
        for (int v = 0; v < nodeCount; ++v) 
        {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }
 
        int i = 0; // Index used to pick next edge
 
        // Number of edges to be taken is equal to V-1
        while (e < nodeCount - 1) 
        {
            // Step 2: Pick the smallest edge. And increment
            // the index for next iteration
            ArrayList<Integer> next_edge = kListSort.get(i++);
 
            int x = find(subsets, next_edge.get(0));
            int y = find(subsets, next_edge.get(2));
 
            // If including this edge does't cause cycle,
            // include it in result and increment the index
            // of result for next edge
            if (x != y) {
                e++;
                result.add(next_edge);
                Union(subsets, x, y);
            }
            // Else discard the next_edge
        }
 
        // print the contents of result[] to display
        // the built MST
        System.out.println("Following are the edges in "
                           + "the constructed MST");
        int minimumCost = 0;
        for (i = 0; i < e; ++i)
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