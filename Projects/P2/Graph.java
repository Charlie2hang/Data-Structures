

import java.util.*;

public class Graph {
    private class Vertex{
        private int num;
        private String name;

        public Vertex(int num, String name) {
            this.num = num;
            this.name = name;
        }
    }

    private class Edge{
        private Vertex v1, v2;
        private int weight;

        public Edge(Vertex v1, Vertex v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }
    }

    private static class Entry implements Comparable<Entry>{
        private int label;
        private Vertex vertex;

        public Entry(int label, Vertex vertex) {
            this.label = label;
            this.vertex = vertex;
        }

        @Override
        public int compareTo(Entry o) { return Integer.compare(label, o.label); }
    }

    private Vertex[] vertices;
    private Edge[] edges;

    public Graph(int n, int m) {
        vertices = new Vertex[n];
        edges = new Edge[m];
    }

    public void addVertex(int i, int num, String name) { vertices[i] = new Vertex(num, name); }

    public void addEdge(int i, Vertex v1, Vertex v2, int weight) { edges[i] = new Edge(v1, v2, weight); }
    public Vertex getVertex(int i) { return vertices[i]; }

    public List<Edge> getIncidentEdges(int v) {
        List<Edge> incidentEdges = new LinkedList<>();
        for (Edge edge :edges) {
            if (edge.v1.num == v) {
                incidentEdges.add(edge);
            }
        }
        return incidentEdges;
    }

    public List<Integer> getAllStations(int v) {
        List<Integer> stations = new LinkedList<>();
        stations.add(v);

        List<Edge> incidentEdges = getIncidentEdges(v);
        Stack<Edge> stack = new Stack<>();
        for (Edge incidentEdge : incidentEdges){ stack.push(incidentEdge); }

        while (!stack.isEmpty()) {
            Edge edge = stack.pop();
            if ( edge.weight != -1 && !stations.contains(edge.v2.num)) {
                stations .add(edge.v2.num);
                incidentEdges = getIncidentEdges(edge.v2.num);
                for (Edge incidentEdge : incidentEdges) { stack.push(incidentEdge);}
            }
        }
        return stations;
    }

    public List<Integer> getShortestPath(int v1, int v2) {
        PriorityQueue<Entry> priorityQueue = new PriorityQueue<>(vertices.length);

        int[] D = new int[vertices.length];
        int[] array = new int[vertices.length];

        for (int i = 0; i < vertices.length; i ++) {
            Vertex vertex = vertices[i];
            if (vertex.num == v1) {
                D[vertex.num] = 0;
                priorityQueue.offer(new Entry(0, vertex));
                array[vertex.num] = 0;
            } else {
                D[vertex.num] = Integer.MAX_VALUE;
                priorityQueue.offer(new Entry(Integer.MAX_VALUE, vertex));
                array[vertex.num] = -1;
            }
        }

        while (!priorityQueue.isEmpty()) {
            Entry entry = priorityQueue.poll();
            int u = entry.vertex.num;
            if (u == v2) {
                break;
            }
            List<Edge> incidentEdges = getIncidentEdges(u);
            for (Edge incidentEdge : incidentEdges) {
                int z = incidentEdge.v2.num;
                int weight = incidentEdge.weight;

                if (incidentEdge.weight == -1) { weight = 90; }

                if (D[u] + weight < D[z]) {
                    D[z] = D[u] + weight;
                    array[z] = u;
                    Iterator<Entry> iterator = priorityQueue.iterator();
                    while (iterator.hasNext()) { // update
                        entry = iterator.next();
                        if (entry.vertex.num == z) {
                            priorityQueue.remove(entry);
                            priorityQueue.offer(new Entry(D[z], entry.vertex));
                            break;
                        }
                    }
                }
            }
        }

        List<Integer> path = new LinkedList<>();
        int vertex = v2;
        while (vertex != v1) {
            path.add(vertex);
            vertex = array[vertex];
        }
        path.add(v1);
        Collections.reverse(path);
        System.out.println("Time = " + D[v2]);
        return path;
    }


    public List<Integer> getShortestPath(int v1, int v2, int v3) {
        List<Integer> stations = getAllStations(v3);
        PriorityQueue<Entry> priorityQueue = new PriorityQueue<>(vertices.length);

        int[] D = new int[vertices.length];
        int[] array = new int[vertices.length];

        for (int i = 0; i < vertices.length; i ++) {
            Vertex vertex = vertices[i];
            if (vertex.num == v1) {
                D[vertex.num] = 0;
                priorityQueue.offer(new Entry(0, vertex));
                array[vertex.num] = 0;
            }  else if (!stations.contains(vertex.num)) {
                D[vertex.num] = Integer.MAX_VALUE;
                priorityQueue.offer(new Entry(Integer.MAX_VALUE,vertex));
                array[vertex.num] = -1;
            }
        }

        while (!priorityQueue.isEmpty()) {
            Entry entry = priorityQueue.poll();
            int u = entry.vertex.num;
            if (u == v2) { break; };

            List<Edge> incidentEdges = getIncidentEdges(u);
            for (Edge incidentEdge : incidentEdges) {
                int z = incidentEdge.v2.num;
                if (!stations.contains(z)) {
                    int weight =incidentEdge.weight;
                    if (incidentEdge.weight == -1) {
                        weight = 90;
                    }
                    if (D[u] + weight < D[z]) {
                        D[z] = D[u] + weight;
                        array[z] = u;
                        Iterator<Entry> iterator = priorityQueue.iterator();
                        while (iterator.hasNext()) { // update
                            entry = iterator.next();
                            if (entry.vertex.num == z) {
                                priorityQueue.remove(entry);
                                priorityQueue.offer(new Entry(D[z], entry.vertex));
                                break;
                            }
                        }
                    }
                }
            }
        }

        List<Integer> path = new LinkedList<>();
        int vertex = v2;
        while (vertex != v1) {
            path.add(vertex);
            vertex = array[vertex];
        }
        path.add(v1);
        Collections.reverse(path);
        System.out.println("Time = " + D[v2]);
        return path;
    }
}
