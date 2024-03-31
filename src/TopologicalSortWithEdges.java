import java.util.*;

public class TopologicalSortWithEdges {
    public List<Integer> topologicalSort(int[][] edges) {
        // Step 1: Determine numCourses
        int numCourses = 0;
        for (int[] edge : edges) {
            numCourses = Math.max(numCourses, Math.max(edge[0], edge[1]) + 1);
        }

        // Step 2: Create adjacency list representation
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] inDegree = new int[numCourses];
        for (int[] edge : edges) {
            int parent = edge[1];
            int child = edge[0];
            graph.get(parent).add(child);
            inDegree[child]++;
        }

        // Step 3: Add vertices with in-degree of 0 to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Step 4: Process vertices from the queue and update in-degrees
        List<Integer> sortedOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            sortedOrder.add(currentVertex);

            // Reduce in-degree of adjacent vertices
            for (int child : graph.get(currentVertex)) {
                inDegree[child]--;
                if (inDegree[child] == 0) {
                    queue.offer(child);
                }
            }
        }

        // Step 5: Check for cycles
        if (sortedOrder.size() != numCourses) {
            // Graph has a cycle
            return new ArrayList<>();
        }

        // Step 6: Return the sorted order or an empty list if there's a cycle
        return sortedOrder;
    }

    public static void main(String[] args) {
        TopologicalSortWithEdges ts = new TopologicalSortWithEdges();
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {2, 3}};
        List<Integer> result = ts.topologicalSort(edges);
        System.out.println("Topological order: " + result);
    }
}
