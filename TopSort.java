import java.util.*;
import java.io.*;

public class TopSort {
    ArrayList<ArrayList<Integer>> Graph;
    int vertCount = 0;

    public TopSort(ArrayList<ArrayList<Integer>> Graph)
    {
        this.Graph = Graph;
        this.vertCount = Graph.size()+1;
    }

    // A recursive function used by topologicalSort 
    void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) 
    { 
        // Mark the current node as visited. 
        visited[v] = true; 
        Integer i; 
  
        // Recur for all the vertices adjacent 
        // to thisvertex 
        Iterator<Integer> it = Graph.get(v-1).iterator(); 
        while (it.hasNext()) { 
            i = it.next(); 
            if (!visited[i]) 
                topologicalSortUtil(i, visited, stack); 
        } 
  
        // Push current vertex to stack 
        // which stores result 
        stack.push(new Integer(v)); 
    } 
  
    // The function to do Topological Sort. 
    // It uses recursive topologicalSortUtil() 
    void topologicalSort() 
    { 
        Stack<Integer> stack = new Stack<Integer>(); 
  
        // Mark all the vertices as not visited 
        boolean visited[] = new boolean[vertCount]; 
        for (int i = 0; i < vertCount; i++) 
            visited[i] = false; 
  
        // Call the recursive helper 
        // function to store 
        // Topological Sort starting 
        // from all vertices one by one 
        for (int i = 1; i < vertCount; i++) 
            if (visited[i] == false) 
                topologicalSortUtil(i, visited, stack); 
  
        // Print contents of stack 
        while (stack.empty() == false) 
            System.out.print(stack.pop() + " "); 
    } 
}
