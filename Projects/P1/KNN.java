//Student name: Hengjing ZHang
//Student number: 300288003
import java.util.ArrayList;

public class KNN {
    private PointSet pointSet;
    private int k;
    private PriorityQueueIF<LabelledPoint> priorityQueue;

    public KNN(PointSet pointSet) {
        this.pointSet = pointSet;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getK() {
        return k;
    }

    public void setPriorityQueue(PriorityQueueIF<LabelledPoint> priorityQueue) {
        this.priorityQueue = priorityQueue;
    }

    public ArrayList<LabelledPoint> findNN(LabelledPoint queryPoint) {

        for (int i = 0; i < pointSet.getPointsList().size(); i++) {
            LabelledPoint point = pointSet.getPointsList().get(i);

            double distance = point.distanceTo(queryPoint);

            point.setKey(distance);
            if (priorityQueue.size() < k) {
                priorityQueue.offer(point);
            } else {
                if (distance < priorityQueue.peek().getKey()) {
                    priorityQueue.offer(point);
                    priorityQueue.poll();
                }
            }
        }

        ArrayList<LabelledPoint> neighbors = new ArrayList<>(k);
        while (!priorityQueue.isEmpty()) {
            neighbors.add(priorityQueue.poll());
        }
        return neighbors;
    }

    public static void main(String[] args) {
        int version = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        String dataSetFilename = args[2];
        String queryPointFilename = args[3];


        PointSet queryPts = new PointSet(PointSet.read_ANN_SIFT(queryPointFilename));
        PointSet pointSet = new PointSet(PointSet.read_ANN_SIFT(dataSetFilename));
        KNN knn = new KNN(pointSet);
        knn.setK(k);
        if (version == 1) {
            knn.setPriorityQueue(new PriorityQueue1(knn.getK() + 1));
        } else if (version == 2) {
            knn.setPriorityQueue(new PriorityQueue2(knn.getK() + 1));
        } else if (version == 3) {
            knn.setPriorityQueue(new PriorityQueue3(knn.getK() + 1));
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < queryPts.getPointsList().size(); i ++) {
            ArrayList<LabelledPoint> neighbors = knn.findNN(queryPts.getPointsList().get(i));
            StringBuffer sb = new StringBuffer();
            sb.append(queryPts.getPointsList().get(i).getLabel());
            sb.append(" : ");
            for (LabelledPoint neighbor : neighbors) {
                sb.append(neighbor.getLabel());
                sb.append(", ");
            }
            sb.setLength(sb.length() - 2);
            System.out.println(sb);
        }
        long endTime = System.currentTimeMillis(); // end timer
        System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");
    }
}
