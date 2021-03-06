import java.util.*;

public class FindCycle {

    static boolean visited[];
    static ArrayList<ArrayList<Integer>> adjListArray;

    public static void main(String ... args) {
        int brr[][] = RandomGraphGenerator.generateRandomGraph(6, false);

        adjListArray = convert(brr);
        visited = new boolean[brr.length];

        System.out.println("Cycle present in brr ? " + isCyclic(adjListArray));
    }

    static boolean isCyclicUtil(int v, boolean visited[], int parent)
    {
        // Mark the current node as visited
        visited[v] = true;
        int i;
 
        // Recur for all the vertices
        // adjacent to this vertex
        Iterator<Integer> it =
            adjListArray.get(v).iterator();
        while (it.hasNext())
        {
            i = it.next();
 
            // If an adjacent is not
            // visited, then recur for that
            // adjacent
            if (!visited[i])
            {
                if (isCyclicUtil(i, visited, v))
                    return true;
            }
 
            // If an adjacent is visited
            // and not parent of current
            // vertex, then there is a cycle.
            else if (i != parent)
                return true;
        }
        return false;
    }
 
    // Returns true if the graph
    // contains a cycle, else false.
    static boolean isCyclic(ArrayList<ArrayList<Integer>> adjListArray)
    {
         
        // Mark all the vertices as
        // not visited and not part of
        // recursion stack
        int V = adjListArray.size();
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;
 
        // Call the recursive helper
        // function to detect cycle in
        // different DFS trees
        for (int u = 0; u < V; u++)
        { 
         
            // Don't recur for u if already visited
            if (!visited[u])
                if (isCyclicUtil(u, visited, -1))
                    return true;
        }
 
        return false;
    }

    // Function to convert adjacency
    // list to adjacency matrix
    static ArrayList<ArrayList<Integer>> convert(int[][] a)
    {
        // no of vertices
        int l = a[0].length;
        ArrayList<ArrayList<Integer>> adjListArray = new ArrayList<ArrayList<Integer>>(l);
 
        // Create a new list for each
        // vertex such that adjacent
        // nodes can be stored
        for (int i = 0; i < l; i++) {
            adjListArray.add(new ArrayList<Integer>());
        }
         
        int i, j;
        for (i = 0; i < a[0].length; i++) {
            for (j = 0; j < a.length; j++) {
                if (a[i][j] == 1) {
                    adjListArray.get(i).add(j);
                }
            }
        }
         
        return adjListArray;
    }
}
