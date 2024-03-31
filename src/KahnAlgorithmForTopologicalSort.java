import java.util.*;

public class KahnAlgorithmForTopologicalSort {
    public List<Integer> topologicalSort(int numCourses, int[][] prerequisites) {
        List<Integer> sortedOrder = new ArrayList<>();

        // Step 1: Calculate in-degrees for all vertices
        int[] inDegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            inDegree[prerequisite[0]]++;
        }

        // Step 2: Add all vertices with in-degree of 0 to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Step 3: Process vertices from the queue and update in-degrees
        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            sortedOrder.add(currentVertex);

            // Reduce in-degree of adjacent vertices
            for (int[] prerequisite : prerequisites) {
                if (prerequisite[1] == currentVertex) {
                    inDegree[prerequisite[0]]--;
                    if (inDegree[prerequisite[0]] == 0) {
                        queue.offer(prerequisite[0]);
                    }
                }
            }
        }

        // Step 4: Check for cycles
        if (sortedOrder.size() != numCourses) {
            // Graph has a cycle
            return new ArrayList<>();
        }

        return sortedOrder;
    }

    public static void main(String[] args) {
        KahnAlgorithmForTopologicalSort ts = new KahnAlgorithmForTopologicalSort();
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        List<Integer> result = ts.topologicalSort(4, prerequisites);
        System.out.println("Topological order: " + result);
    }
}
