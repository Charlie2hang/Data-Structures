
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ParisMetro {
    private static Graph graph;


    // A static method reads the input file
    public static void readMetro(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            line = line.replace("\uFEFF", "");
            String[] info = line.split(" ");
            graph = new Graph(Integer.parseInt(info[0]),Integer.parseInt(info[1]));

            int i = 0;
            while ((line = reader.readLine()) != null) {
                if (line.equals("$")) {
                    break;
                }
                info = line.split(" ", 2);
                graph.addVertex(i, Integer.parseInt(info[0]), info[1]);
                i ++;
            }

            i = 0;
            while ((line = reader.readLine()) != null) {
                info = line.split(" ");
                int v1 = Integer.parseInt(info[0]);
                int v2 = Integer.parseInt(info[1]);
                int weight = Integer.parseInt(info[2]);
                graph.addEdge(i, graph.getVertex(v1), graph.getVertex(v2), weight);
                i ++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        readMetro("metro.txt");
        System.out.println(graph.getShortestPath(0, 288));
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println(graph.getShortestPath(0, 288, 3));
    }
}
