import java.util.*;

public class TopologicalSortAttempt {
    public static List<Integer> kahnAlgorithm(Map<Integer, List<Integer>> graph) {
        int n = graph.size();
        int[] inDegree = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        // Calculate in-degree of each vertex
        for (List<Integer> neighbors : graph.values()) {
            for (int neighbor : neighbors) {
                inDegree[neighbor]++;
            }
        }

        // Add vertices with in-degree 0 to the queue
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Process vertices in the queue
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(vertex);

            // Reduce in-degree of neighbors and add them to the queue if in-degree becomes 0
            for (int neighbor : graph.getOrDefault(vertex, new ArrayList<>())) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Check if the graph contains a cycle
        if (result.size() != n) {
            throw new IllegalArgumentException("The graph contains a cycle.");
        }

        return result;
    }

    public static void main(String[] args) {
        // Example usage
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(3));
        graph.put(2, Arrays.asList(3));
        graph.put(3, Arrays.asList(4, 5));
        graph.put(4, Arrays.asList(6));
        graph.put(5, Arrays.asList(6));
        graph.put(6, new ArrayList<>());

        List<Integer> sortedOrder = kahnAlgorithm(graph);
        System.out.println("Topological Sorting Order: " + sortedOrder);
    }
}