import java.lang.Math;
import java.util.*;

public class RandomGraphGenerator {
    
    public static int[][] generateRandomGraph(int numNodes, boolean selfCycles) {
        int[][] arr = new int[numNodes][numNodes];
        // double[] rand = new double[numNodes * numNodes];
        // double[] rand2 = new double[numNodes * numNodes];

        System.out.println("number of nodes are - " + numNodes);

        int numOfEdges = 0;

        for (int i=0; i<numNodes; i++) {
            for (int j = (selfCycles ? -1 : 0); j<i; j++) {
                int k=j+1;
                double randNumber = Math.random();
                boolean isEdge = randNumber > 0.5;

                arr[i][k] = isEdge ? 1 : 0;
                numOfEdges = isEdge ? (numOfEdges + 1) : numOfEdges;
            }
        }

        for (int i=0; i<numNodes; i++) {
            for (int j=(numNodes-1); j>i; j--) {
                arr[i][j] = arr[j][i];
            }
        }

        System.out.println("number of edges are - " + numOfEdges);
        System.out.println();

        return arr;
    }

    public static void main(String... args) {
        
        int numNodes = 10;
        boolean selfCyclesAllowed = true;

        System.out.println("Graph being generated for " + numNodes + " nodes And Self Cycles are " + (selfCyclesAllowed ? "allowed" : "not allowed"));
        System.out.println();
        System.out.println();
        int[][] arr = generateRandomGraph(numNodes, selfCyclesAllowed);
        for (int i=0; i<arr.length; i++) {
            for (int j=0; j<arr.length; j++) {
                System.out.print(arr[i][j] + "   ");
            }
            System.out.println();
        }
    }
}
