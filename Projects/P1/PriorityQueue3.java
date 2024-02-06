//Student name: Hengjing ZHang
//Student number: 300288003
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueue3 implements PriorityQueueIF<LabelledPoint>{
    private PriorityQueue<LabelledPoint> priorityQueue;
    private int capacity;
    private int size;

    public PriorityQueue3(int capacity) {
        this.capacity = capacity;
        priorityQueue = new PriorityQueue<>(capacity, new Comparator<LabelledPoint>() {
            @Override
            public int compare(LabelledPoint o1, LabelledPoint o2) {
                if (o1.getKey() < o2.getKey()) {
                    return 1;
                } else if (o1.getKey() == o2.getKey()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
    }

    public PriorityQueue3(ArrayList<LabelledPoint> labelledPoints, int capacity) {
        this.capacity = capacity;
        this.size = labelledPoints.size();
        priorityQueue = new PriorityQueue<>(capacity, new Comparator<LabelledPoint>() {
            public int compare(LabelledPoint o1, LabelledPoint o2) {
                if (o1.getKey() < o2.getKey()) {
                    return 1;
                } else if (o1.getKey() == o2.getKey()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        for (LabelledPoint point : labelledPoints) {
            priorityQueue.offer(point);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean offer(LabelledPoint labelledPoint) {
        if (size < capacity) {
            size ++;
            return priorityQueue.offer(labelledPoint);
        }
        return false;
    }

    @Override
    public LabelledPoint poll() {
        if (isEmpty()) {
            return null;
        }
        size --;
        return priorityQueue.poll();
    }

    @Override
    public LabelledPoint peek() {
        if (isEmpty()) {
            return null;
        }
        return priorityQueue.peek();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
