package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1260 {

    static class Graph {
        Node[] nodes;
        int v;  // starting node (1-based index)

        Graph(int n, int v, boolean reversed) {
            this.nodes = new Node[n];
            this.v = v;

            for (int i = 0; i < n; i++) {
                this.nodes[i] = new Node(i + 1, reversed);
            }
        }

        void addEdge(int a, int b) {
            Node x = nodes[a - 1];  // convert 1-based index to 0-based
            Node y = nodes[b - 1];  // convert 1-based index to 0-based
            x.addAdjacentNode(y);
            y.addAdjacentNode(x);  // undirected edge
        }

        Node getFirstNode() {
            return nodes[v - 1];
        }
    }

    static class Node implements Comparable<Node> {
        int id;
        boolean reversed;
        boolean visited;
        Queue<Node> adjNode;

        Node(int id, boolean reversed) {
            this.id = id;
            this.reversed = reversed;
            this.visited = false;
            this.adjNode = new PriorityQueue<>();
        }

        int getId() {
            return id;
        }

        Node getNextAdjacentNode() {
            Node node = adjNode.poll();

            if (node != null) {
                if (node.visited) {
                    return getNextAdjacentNode();
                } else {
                    return node;
                }
            }
            return null;
        }

        void addAdjacentNode(Node node) {
            this.adjNode.add(node);
        }

        void visit() {
            this.visited = true;
        }

        boolean isVisited() {
            return this.visited;
        }

        @Override
        public int compareTo(Node other) {
            return reversed ? other.id - this.id : this.id - other.id;
        }
    }

    public static void main(String[] args) {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int n = input[0];
            int m = input[1];
            int v = input[2];

            // In DFS, priority queue must be reversed (in descending order)
            // to visit the node with smaller index first.
            Graph g1 = new Graph(n, v, true);

            Graph g2 = new Graph(n, v, false);

            for (int i = 0; i < m; i++) {
                int[] e = Arrays.stream(reader.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                g1.addEdge(e[0], e[1]);
                g2.addEdge(e[0], e[1]);
            }
            dfs(g1);
            bfs(g2);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void dfs(Graph g) {
        Stack<Node> stack = new Stack<>();
        stack.push(g.getFirstNode());

        while (!stack.isEmpty()) {
            Node node = stack.pop();

            if (node.isVisited()) continue;

            node.visit();
            System.out.printf("%d ", node.getId());
            Node adjNode = node.getNextAdjacentNode();

            while (adjNode != null) {
                stack.push(adjNode);
                adjNode = node.getNextAdjacentNode();
            }
        }
        System.out.println();
    }

    private static void bfs(Graph g) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(g.getFirstNode());

        while (!queue.isEmpty()) {
            Node node = queue.remove();

            if (node.isVisited()) continue;

            node.visit();
            System.out.printf("%d ", node.getId());
            Node adjNode = node.getNextAdjacentNode();

            while (adjNode != null) {
                queue.add(adjNode);
                adjNode = node.getNextAdjacentNode();
            }
        }
        System.out.println();
    }

}
